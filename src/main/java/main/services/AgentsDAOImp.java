//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package main.services;

import java.util.List;
import main.interfaces.AgentsDAO;
import main.models.Agents;
import main.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.mindrot.jbcrypt.BCrypt;

public class AgentsDAOImp implements AgentsDAO {
    private final SessionFactory sessionFactory;

    public AgentsDAOImp() {
        HibernateUtil hibernateUtil = HibernateUtil.getInstance();
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void save(Agents agents) {
        Transaction transaction = null;

        try {
            try (Session session = this.sessionFactory.openSession()) {
                transaction = session.beginTransaction();
                session.save(agents);
                transaction.commit();
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            throw new RuntimeException("Error saving agent to the database: " + e.getMessage(), e);
        }
    }

    public List<Agents> getAllAgents() {
        try (Session session = this.sessionFactory.openSession()) {
            Query<Agents> query = session.createQuery("FROM Agents", Agents.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error getting all agents", e);
        }
    }

    public void update(Agents agents) {
        Transaction transaction = null;

        try {
            try (Session session = this.sessionFactory.openSession()) {
                transaction = session.beginTransaction();
                session.update(agents);
                transaction.commit();
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            throw new RuntimeException("Error updating agent", e);
        }
    }

    public void delete(Agents agents) {
        Transaction transaction = null;

        try {
            try (Session session = this.sessionFactory.openSession()) {
                transaction = session.beginTransaction();
                session.delete(agents);
                transaction.commit();
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            throw new RuntimeException("Error deleting agent", e);
        }
    }

    public void updatePassword(String email, String newPassword) {
        Transaction transaction = null;

        try {
            try (Session session = this.sessionFactory.openSession()) {
                transaction = session.beginTransaction();
                Agents agent = (Agents)session.createQuery("FROM Agents WHERE email = :email", Agents.class).setParameter("email", email).uniqueResult();
                if (agent == null) {
                    throw new RuntimeException("Agent with email " + email + " not found.");
                }

                System.out.println("New Password (Input): " + newPassword);
                if (newPassword.startsWith("$2a$")) {
                    agent.setPassword(newPassword);
                    System.out.println("Password is already hashed. Skipping rehash.");
                } else {
                    String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
                    agent.setPassword(hashedPassword);
                    System.out.println("Password hashed: " + hashedPassword);
                }

                session.update(agent);
                transaction.commit();
                System.out.println("Password updated successfully for email: " + email);
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            throw new RuntimeException("Error updating password: " + e.getMessage(), e);
        }
    }
}
