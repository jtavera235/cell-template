package com.cell.common.persist;

import com.cell.common.utils.ModelEntityId;
import java.util.List;
import java.util.Objects;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;

public class StandaloneTransactionOperation {

  public static <T> void save(final T entity) {
    final Session session = SessionFactoryUtil.sessionFactory().getCurrentSession();
    final Transaction transaction = session.getTransaction();
    validateTransactionNotActive(transaction);
    transaction.begin();
    try (session) {
      session.persist(entity);
      transaction.commit();
    } finally {
      if (transaction.isActive()) {
        transaction.rollback();
      }
    }
  }

  public static <T> T findById(final ModelEntityId id, final Class<T> modelClass) {
    final Session session = SessionFactoryUtil.sessionFactory().getCurrentSession();
    final Transaction transaction = session.getTransaction();
    validateTransactionNotActive(transaction);
    transaction.begin();
    try {
      final T model = id == null ? null : modelClass.cast(session.get(modelClass, id.getObjectId()));
      transaction.commit();
      return model;
    } finally {
      if (transaction.isActive()) {
        transaction.rollback();
      }
    }
  }

  public static <T> T findUnique(final DetachedCriteria criteria) {
    final Session session = SessionFactoryUtil.sessionFactory().getCurrentSession();
    final Transaction transaction = session.getTransaction();
    validateTransactionNotActive(transaction);
    transaction.begin();
    try {
      final T entity = (T) criteria.getExecutableCriteria(session).uniqueResult();
      transaction.commit();
      return entity;
    } finally {
      if (transaction.isActive()) {
        transaction.rollback();
      }
    }
  }

  public static <T> List<T> findAll(final DetachedCriteria criteria) {
    final Session session = SessionFactoryUtil.sessionFactory().getCurrentSession();
    final Transaction transaction = session.getTransaction();
    validateTransactionNotActive(transaction);
    transaction.begin();
    try {
      final List<T> results = criteria.getExecutableCriteria(session).list();
        transaction.commit();
      return results;
    } finally {
      if (transaction.isActive()) {
        transaction.rollback();
      }
    }
  }

  public static <T> void delete(final T entity) {
    if (Objects.isNull(entity)) {
      return;
    }
    final Session session = SessionFactoryUtil.sessionFactory().getCurrentSession();
    final Transaction transaction = session.getTransaction();
    validateTransactionNotActive(transaction);
    transaction.begin();
    try {
      session.delete(entity);
      transaction.commit();
    } finally {
      if (transaction.isActive()) {
        transaction.rollback();
      }
    }
  }

  public static <T> void update(final T entity) {
    if (Objects.isNull(entity)) {
      return;
    }
    final Session session = SessionFactoryUtil.sessionFactory().getCurrentSession();
    final Transaction transaction = session.getTransaction();
    validateTransactionNotActive(transaction);
    transaction.begin();
    try (session) {
      session.update(entity);
      transaction.commit();
    } finally {
      if (transaction.isActive()) {
        transaction.rollback();
      }
    }
  }

  private static void validateTransactionNotActive(final Transaction transaction) {
    if (transaction.isActive()) {
      transaction.rollback();
      throw new RuntimeException("There already exists a parent transaction - must use"
          + " the parent transaction");
    }
  }

}
