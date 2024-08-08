package com.hibtest.hib_jpa_test;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static SessionFactory buildSessionFactory() {
        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration().buildSessionFactory();
            } catch (Throwable ex) {
                throw new ExceptionInInitializerError(ex);
            }
        }
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        return buildSessionFactory();
    }

    public static void shutdown() {
        sessionFactory.close();
    }
}
