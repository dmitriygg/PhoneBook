package ru.grigoryevdev.telephonebook.shared.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import ru.grigoryevdev.telephonebook.shared.domain.Phone;

@RemoteServiceRelativePath("springGwtServices/phoneBookService")
public interface PhoneBookService extends RemoteService {

	public void addPhone(Phone phone);

	public List<Phone> listPhone();

	public void removePhone(Integer id);

}
