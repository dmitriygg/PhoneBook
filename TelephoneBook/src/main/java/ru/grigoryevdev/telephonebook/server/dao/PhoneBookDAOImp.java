package ru.grigoryevdev.telephonebook.server.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.grigoryevdev.telephonebook.shared.domain.Phone;

@Repository
public class PhoneBookDAOImp implements PhoneBookDAO {

	@Autowired
	SessionFactory sessionFactory;

	public void addPhone(Phone phone) {
		sessionFactory.getCurrentSession().save(phone);
	}

	@SuppressWarnings("unchecked")
	public List<Phone> listPhone() {
		return sessionFactory.getCurrentSession().createQuery("from Phone")
				.list();
	}

	public void removePhone(Integer id) {
		Phone phone = (Phone) sessionFactory.getCurrentSession().load(
				Phone.class, id);
		if (null != phone) {
			sessionFactory.getCurrentSession().delete(phone);
		}
	}

}
