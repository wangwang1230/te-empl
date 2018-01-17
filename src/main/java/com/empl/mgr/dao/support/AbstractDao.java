package com.empl.mgr.dao.support;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao<T> {

	@Autowired
	private SessionFactory sessionFactory;

	public abstract Class<T> getEntityClass();

	public Session findSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return findSession().createCriteria(getEntityClass()).list();
	}

	@SuppressWarnings("unchecked")
	public T findUniqueByProperty(String pro, Object val) {
		return (T) findSession().createCriteria(getEntityClass()).add(Restrictions.eq(pro, val)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<T> findByProperty(String pro, Object val) {
		return findSession().createCriteria(getEntityClass()).add(Restrictions.eq(pro, val)).list();
	}

	public int findCounnt() {
		String query = "select count(*) from " + getEntityClass().getName();
		return Integer.parseInt(findSession().createQuery(query).uniqueResult().toString());
	}

	public int findCountLike(String pro, String val) {
		String query = "select count(*) from " + getEntityClass().getName() + " where " + pro + " like '%" + val + "%'";
		return Integer.parseInt(findSession().createQuery(query).uniqueResult().toString());
	}

	public int findCountByProperty(String pro, Object searchValue) {
		String query = "select count(*) from " + getEntityClass().getName() + " where " + pro + " = '" + searchValue
				+ "'";
		return Integer.parseInt(findSession().createQuery(query).uniqueResult().toString());
	}

	public void deleteByProperty(String pro, Object value) {
		String query = "delete from " + getEntityClass().getName() + " where " + pro + "=" + value.toString();
		findSession().createQuery(query).executeUpdate();
	}

	public void deleteByPropertyString(String pro, Object val) {
		String query = "delete from " + getEntityClass().getName() + " where " + pro + "='" + val.toString() + "'";
		findSession().createQuery(query).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public T findById(long id) {
		return (T) findSession().get(getEntityClass().getName(), id);
	}

	public void save(Object obj) {
		findSession().save(obj);
	}

	public void delete(Object obj) {
		findSession().delete(obj);
	}

}
