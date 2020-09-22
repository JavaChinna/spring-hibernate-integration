package com.javachinna.repo;

import java.util.stream.Stream;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.javachinna.model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@Transactional
public class UserRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public Stream<User> getUers() {
		Session session = null;
		Transaction transaction = null;
		Stream<User> users = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			users = session.createQuery("select u from User u", User.class).stream();
			transaction.commit();
		} catch (Exception e) {
			log.error("Exception occurred", e);
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return users;
	}
}
