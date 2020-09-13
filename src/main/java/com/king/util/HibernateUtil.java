package com.king.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sf;
    private static StandardServiceRegistry builder;
    public static SessionFactory getSessionFactory() {
        if (sf == null) {
            Configuration conf = new Configuration().configure();
            conf.addAnnotatedClass(com.king.model.Student.class);
            builder = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
            sf = conf.buildSessionFactory(builder);
        }
        return sf;
    }
    public static void shutdown(){
        if( builder != null)
            StandardServiceRegistryBuilder.destroy(builder);
    }
}
