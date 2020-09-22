package com.javachinna.repo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.javachinna.model.UserDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@Transactional
public class UserInfoRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<UserDTO> getUerInfo() {
		Session session = null;
		Transaction transaction = null;
		List<UserDTO> list = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Query query = session.createNativeQuery("{call SP_USER_INFO()}").setResultTransformer(Transformers.aliasToBean(UserDTO.class));
			list = query.getResultList();
			transaction.commit();
		} catch (Exception e) {
			log.error("Exception occurred", e);
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return list;
	}
}
