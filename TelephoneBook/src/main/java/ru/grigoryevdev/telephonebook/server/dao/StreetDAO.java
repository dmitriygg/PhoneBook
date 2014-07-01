package ru.grigoryevdev.telephonebook.server.dao;

import java.util.List;

import ru.grigoryevdev.telephonebook.shared.domain.Street;

public interface StreetDAO {
	
	public void addStreet(Street street);

	public List<Street> listStreet();

	public void removeStreet(Integer id);

}
