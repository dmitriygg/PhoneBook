package ru.grigoryevdev.telephonebook.server.dao;

import java.util.List;

import ru.grigoryevdev.telephonebook.shared.domain.Phone;

public interface PhoneBookDAO {

	public void addPhone(Phone phone);

	public List<Phone> listPhone();

	public void removePhone(Integer id);

}
