package ru.grigoryevdev.telephonebook.server.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.grigoryevdev.telephonebook.shared.domain.Street;

@Repository
public class StreetDAOImpl implements StreetDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	public void addStreet(Street street) {
		sessionFactory.getCurrentSession().save(street);
	}
	
	@SuppressWarnings("unchecked")
	public List<Street> listStreet() {
		return sessionFactory.getCurrentSession().createQuery("from Street")
				.list();
	}

	public void removeStreet(Integer id) {
		Street street = (Street) sessionFactory.getCurrentSession().load(
				Street.class, id);
		if (null != street) {
			sessionFactory.getCurrentSession().delete(street);
		}
	}

}
