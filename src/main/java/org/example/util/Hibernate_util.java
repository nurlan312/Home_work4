package org.example.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Hibernate_util {

    private static final SessionFactory sessionFactory = buildSession();

    private static SessionFactory buildSession() {
        try {
            return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable e) {
            System.err.println("Сессия не создана!");
            throw new ExceptionInInitializerError(e.getMessage());
        }
    }

    public static SessionFactory getSession() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSession().close();
    }
}
