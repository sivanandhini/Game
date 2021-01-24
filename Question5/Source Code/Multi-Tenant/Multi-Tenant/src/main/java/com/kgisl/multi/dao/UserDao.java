package com.kgisl.multi.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.kgisl.multi.model.User;

@Repository
public class UserDao {

	@Autowired
	@Qualifier("tenantSessionFactory")
	private SessionFactory sessionFactory;

	public List<User> getUserByName(String username) {

		CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();

		CriteriaQuery<User> query = builder.createQuery(User.class);

		Root<User> root = query.from(User.class);

		return sessionFactory.getCurrentSession()
				.createQuery(
						query.select(root)
						.where(builder.equal(root.get("username"), username)))
				.list();
	}
	
}
