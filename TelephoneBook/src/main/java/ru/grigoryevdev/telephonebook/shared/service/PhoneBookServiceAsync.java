package ru.grigoryevdev.telephonebook.shared.service;

import java.util.List;

import ru.grigoryevdev.telephonebook.shared.domain.Phone;
import ru.grigoryevdev.telephonebook.shared.domain.Street;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PhoneBookServiceAsync {

	void addPhone(Phone phone, AsyncCallback<Void> callback);

	void listPhone(AsyncCallback<List<Phone>> callback);

	void removePhone(Integer id, AsyncCallback<Void> callback);

	void addStreet(Street street, AsyncCallback<Void> callback);

	void listStreet(AsyncCallback<List<Street>> callback);

	void removeStreet(Integer id, AsyncCallback<Void> callback);

}
