package main.imp;

import main.interfaces.DashboardAgentsDOA;
import main.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class DashBoardAgentDAOImp implements DashboardAgentsDOA {

    private final SessionFactory sessionFactory;

    public DashBoardAgentDAOImp() {
        HibernateUtil hibernateUtil = HibernateUtil.getInstance();
        this.sessionFactory = hibernateUtil.getSessionFactory();
    }

    @Override
    public int getApartmentCount() {
        return getCountByType("apartment");
    }

    @Override
    public int getLandCount() {
        return getCountByType("plot of land");
    }

    @Override
    public int getHomeCount() {
        return getCountByType("detachedhouse");
    }

    @Override
    public int getAgreementsCount() {
        return getCountByQuery("SELECT COUNT(o) FROM Offer o");
    }

    private int getCountByType(String type) {
        return getCountByQuery(
                "SELECT COUNT(r) FROM RealEstate r WHERE LOWER(r.Type) = :Type AND r.state = :State",
                new String[]{"Type", "State"},
                new Object[]{type.toLowerCase(), "available"}
        );
    }

    private int getCountByQuery(String hql) {
        return getCountByQuery(hql, null, null);
    }

    private int getCountByQuery(String hql, String[] parameterNames, Object[] parameterValues) {
        int count = 0;
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query<Long> query = session.createQuery(hql, Long.class);

            if (parameterNames != null && parameterValues != null) {
                for (int i = 0; i < parameterNames.length; i++) {
                    query.setParameter(parameterNames[i], parameterValues[i]);
                }
            }

            count = query.uniqueResult().intValue();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return count;
    }
}
