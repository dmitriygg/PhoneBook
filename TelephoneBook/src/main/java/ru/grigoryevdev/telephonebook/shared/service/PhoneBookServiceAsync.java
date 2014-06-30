package ru.grigoryevdev.telephonebook.shared.service;

import java.util.List;

import ru.grigoryevdev.telephonebook.shared.domain.Phone;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PhoneBookServiceAsync {

	void addPhone(Phone phone, AsyncCallback<Void> callback);

	void listPhone(AsyncCallback<List<Phone>> callback);

	void removePhone(Integer id, AsyncCallback<Void> callback);

}
