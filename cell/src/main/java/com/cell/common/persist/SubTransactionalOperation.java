package com.cell.common.persist;

import com.cell.common.utils.ModelEntityId;
import java.util.List;
import java.util.Objects;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

public final class SubTransactionalOperation {

  public SubTransactionalOperation() {}

  public static <T> void save(final T entity) {
    final Session session = SessionFactoryUtil.sessionFactory().getCurrentSession();
    session.persist(entity);
  }

  public static <T> T findById(final ModelEntityId id, final Class<T> modelClass) {
    final Session session = SessionFactoryUtil.sessionFactory().getCurrentSession();
    final T model = id == null ? null : modelClass.cast(session.get(modelClass, id.getObjectId()));
    return model;
  }

  public static <T> T findUnique(final DetachedCriteria criteria) {
    final Session session = SessionFactoryUtil.sessionFactory().getCurrentSession();
    final T entity = (T) criteria.getExecutableCriteria(session).uniqueResult();
    return entity;
  }

  public static <T> List<T> findAll(final DetachedCriteria criteria) {
    final Session session = SessionFactoryUtil.sessionFactory().getCurrentSession();
    final List<T> results = criteria.getExecutableCriteria(session).list();
    return results;
  }

  public static <T> void delete(final T entity) {
    if (Objects.isNull(entity)) {
      return;
    }
    final Session session = SessionFactoryUtil.sessionFactory().getCurrentSession();
    session.delete(entity);
  }

  public static <T> void update(final T entity) {
    final Session session = SessionFactoryUtil.sessionFactory().getCurrentSession();
    session.update(entity);
  }
}
