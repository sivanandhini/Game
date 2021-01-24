package com.kgisl.di.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kgisl.di.modal.User;

@Repository
public class UserDao {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public UserDao(SessionFactory sessionFactory){
		
		this.sessionFactory = sessionFactory;
	 
	}

	public List<User> getAllUsers() {
		
		sessionFactory.getCurrentSession().createSQLQuery("CREATE TABLE IF NOT EXISTS `usr_user` (" +
                "`user_id` INT(11) NOT NULL AUTO_INCREMENT, " +
                "`user_first_name` VARCHAR(200) NULL DEFAULT NULL," +
                "`user_last_name` VARCHAR(200) NULL DEFAULT NULL," +
                "`user_phone_number` INT(11) NOT NULL," + 
                "`user_email` VARCHAR(50) NULL DEFAULT NULL," +
                "`user_gender` VARCHAR(50) NULL DEFAULT NULL," + 
                "PRIMARY KEY (`user_id`))").executeUpdate();

        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
		
		CriteriaQuery<User> query = builder.createQuery(User.class);
		
		Root<User> root = query.from(User.class);
		
		return sessionFactory.getCurrentSession().createQuery(query
				.select(root))
				.getResultList();
	}

}
