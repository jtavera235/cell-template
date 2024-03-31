package com.cell.common.persist;

import com.cell.services.accounts.persist.AccountModel;
import java.sql.Connection;
import java.sql.DriverManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public final class SessionFactoryUtil {

  private static SessionFactory sessionFactory = null;

  static {
    final Configuration configuration = new Configuration();
    configuration.configure("hibernate.cfg.xml");
    configuration.addAnnotatedClass(AccountModel.class);
    final StandardServiceRegistryBuilder ssrb =
        new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
    sessionFactory = configuration.buildSessionFactory(ssrb.build());
  }

  public static void init() {}

  public static SessionFactory sessionFactory() {
    return sessionFactory;
  }
}
