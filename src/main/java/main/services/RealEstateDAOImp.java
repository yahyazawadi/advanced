package main.services;

import main.models.RealEstate;
import main.interfaces.RealEstateDAO;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import main.util.HibernateUtil;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;


public class RealEstateDAOImp implements RealEstateDAO {
    HibernateUtil hibernateUtil;
    SessionFactory sessionFactory;
    public RealEstateDAOImp() {
        hibernateUtil = HibernateUtil.getInstance();
        sessionFactory = hibernateUtil.getSessionFactory(); // Fix: assign to class-level field
    }
    @Override
    public void save(RealEstate realEstate) {
        Session session = null;
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(realEstate);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            e.printStackTrace(); // Replace with proper logging
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void update(RealEstate realEstate) {
        SessionFactory sessionFactory= HibernateUtil.getSessionFactory();
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.update(realEstate);
        session.getTransaction().commit();
        sessionFactory.close();
    }


    @Override
    public void delete(int id) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        RealEstate realEstate = session.get(RealEstate.class, id);
        session.delete(realEstate);
        session.getTransaction().commit();

    }
    @Override
    public RealEstate getRealEstateById(int id) {
        SessionFactory sessionFactory= HibernateUtil.getSessionFactory();
        Session session=sessionFactory.openSession();
        return session.get(RealEstate.class, id);
    }
    @Override
    public void start(Stage primaryStage) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("choose images");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

        File selectedFile = fileChooser.showOpenDialog(primaryStage);

        if (selectedFile != null) {
            uploadImageToDatabase(selectedFile);
        } else {
            System.out.println("no images selected");
        }



    }
    private byte[] readImageBytes(File file) {
        try {
            return Files.readAllBytes(file.toPath()); // تحويل الصورة إلى byte[]
        } catch (IOException e) {
            System.out.println(" failed to read images " + e.getMessage());
            return null;
        }
    }
    private void uploadImageToDatabase(File file) {
        byte[] imageBytes = readImageBytes(file);
        try {
            imageBytes = Files.readAllBytes(file.toPath()); // تحويل الصورة إلى byte[]
            System.out.println("Image uploaded" + file.getName());
        } catch (IOException e) {
            System.out.println("failed" + e.getMessage());
        }
    }


    @Override
    public List<RealEstate> getAllRealEstates() {
        Session session = null;
        List<RealEstate> realEstates = null;
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            realEstates = session.createQuery("FROM RealEstate", RealEstate.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            e.printStackTrace(); // Replace with proper logging
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return realEstates;
    }


    public List<RealEstate> searchRealEstate(String name) {
        Session session = sessionFactory.openSession();
        List<RealEstate> realEstates = null;

        try {
            session.beginTransaction();


            String hql = "FROM RealEstate WHERE lower(NameOfProperty) LIKE :name";
            realEstates = session.createQuery(hql, RealEstate.class)
                    .setParameter("name", "%" + name.toLowerCase() + "%")
                    .getResultList();

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace(); // يمكنك استبداله بتسجيل أفضل إذا أردت
        } finally {
            session.close();
        }


        realEstates.forEach(realEstate -> {
            System.out.println("Property ID: " + realEstate.getId());
            System.out.println("Name: " + realEstate.getNameOfProperty());
            System.out.println("Price: " + realEstate.getPrice());
            System.out.println("-----------------------------------");
        });

        return realEstates;
    }
    @Override
    public List<RealEstate> filterRealEstates(String priceRange, String propertyType) {
        Session session = sessionFactory.openSession();
        List<RealEstate> filteredResults = null;

        try {
            session.beginTransaction();
            String hql = "FROM RealEstate WHERE 1=1";

            if (priceRange != null) {
                if (priceRange.equals("Under $100,000")) {
                    hql += " AND price < 100000";
                } else if (priceRange.equals("$100,000 - $500,000")) {
                    hql += " AND price BETWEEN 100000 AND 500000";
                } else if (priceRange.equals("Above $500,000")) {
                    hql += " AND price > 500000";
                }
            }

            if (propertyType != null) {
                hql += " AND Type = :propertyType";
            }

            filteredResults = session.createQuery(hql, RealEstate.class)
                    .setParameter("propertyType", propertyType)
                    .getResultList();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return filteredResults;
    }
}
