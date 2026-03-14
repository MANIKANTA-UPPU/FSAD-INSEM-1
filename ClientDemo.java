package com.klef.fsad.exam;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import java.util.Scanner;

public class ClientDemo {
    public static void main(String[] args) {
        SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        
        // --- I. PERFORMING MULTIPLE INSERTS ---
        Session session1 = sf.openSession();
        Transaction t1 = session1.beginTransaction();
        
        // Delivery 1
        Delivery d1 = new Delivery();
        d1.setId(101);
        d1.setName("Smartphone");
        d1.setDate("2026-03-12");
        d1.setStatus("Processing");
        
        // Delivery 2
        Delivery d2 = new Delivery();
        d2.setId(102);
        d2.setName("Gaming Console");
        d2.setDate("2026-03-15");
        d2.setStatus("Shipped");

        // Delivery 3
        Delivery d3 = new Delivery();
        d3.setId(103);
        d3.setName("Office Chair");
        d3.setDate("2026-03-18");
        d3.setStatus("Pending");
        
        session1.save(d1);
        session1.save(d2);
        session1.save(d3);
        
        t1.commit();
        System.out.println("Multiple records inserted successfully.");
        session1.close();

        // --- II. PERFORMING MULTIPLE DELETES (OR CONDITIONAL DELETE) ---
        Session session2 = sf.openSession();
        Transaction t2 = session2.beginTransaction();
        
        // Example: Deleting record with ID 102 using positional parameters
        String hql = "DELETE FROM Delivery WHERE id = ?1";
        Query query = session2.createQuery(hql);
        
        query.setParameter(1, 102); // Deletes the Gaming Console
        int rowsDeleted = query.executeUpdate();
        
        t2.commit();
        System.out.println("Total records deleted: " + rowsDeleted);
        
        session2.close();
        sf.close();
    }
}