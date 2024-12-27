package com.example.real_estate_application.models.services;

import com.example.real_estate_application.models.RealEstate;
import com.example.real_estate_application.models.interfaces.RealEstateDAO;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.example.real_estate_application.util.HibernateUtil;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


public class RealEstateDAOImp implements RealEstateDAO {
    HibernateUtil hibernateUtil;
    SessionFactory sessionFactory;
    public RealEstateDAOImp() {
        hibernateUtil=HibernateUtil.getInstance();
        SessionFactory sessionFactory=hibernateUtil.getSessionFactory();
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
        // إنشاء نافذة لاختيار الملف
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("اختر صورة لتحميلها");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

        // إظهار نافذة اختيار الملف وانتظار النتيجة
        File selectedFile = fileChooser.showOpenDialog(primaryStage);

           if (selectedFile != null) {
               uploadImageToDatabase(selectedFile);
           } else {
               System.out.println("لم يتم اختيار أي ملف.");
           }



    }
    private byte[] readImageBytes(File file) {
        try {
            return Files.readAllBytes(file.toPath()); // تحويل الصورة إلى byte[]
        } catch (IOException e) {
            System.out.println("فشل في قراءة الصورة: " + e.getMessage());
            return null;
        }
    }
    private void uploadImageToDatabase(File file) {
        byte[] imageBytes = readImageBytes(file);
        try {
            imageBytes = Files.readAllBytes(file.toPath()); // تحويل الصورة إلى byte[]
            System.out.println("تم تحميل الصورة بنجاح: " + file.getName());
        } catch (IOException e) {
            System.out.println("فشل في تحميل الصورة: " + e.getMessage());
        }
    }
}
