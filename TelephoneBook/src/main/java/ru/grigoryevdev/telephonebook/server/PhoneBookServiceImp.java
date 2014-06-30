package ru.grigoryevdev.telephonebook.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.grigoryevdev.telephonebook.server.dao.PhoneBookDAO;
import ru.grigoryevdev.telephonebook.shared.domain.Phone;
import ru.grigoryevdev.telephonebook.shared.service.PhoneBookService;

@Service("phoneBookService")
public class PhoneBookServiceImp implements PhoneBookService {

	@Autowired
	private PhoneBookDAO phoneBookDAO;

	@Transactional
	public void addPhone(Phone phone) {
		phoneBookDAO.addPhone(phone);
	}

	@Transactional
	public List<Phone> listPhone() {
		return phoneBookDAO.listPhone();
	}

	@Transactional
	public void removePhone(Integer id) {
		phoneBookDAO.removePhone(id);
	}

}
