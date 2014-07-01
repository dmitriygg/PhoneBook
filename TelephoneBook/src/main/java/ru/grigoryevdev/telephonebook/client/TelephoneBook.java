package ru.grigoryevdev.telephonebook.client;

import java.util.List;

import ru.grigoryevdev.telephonebook.shared.domain.Phone;
import ru.grigoryevdev.telephonebook.shared.domain.Street;
import ru.grigoryevdev.telephonebook.shared.service.PhoneBookService;
import ru.grigoryevdev.telephonebook.shared.service.PhoneBookServiceAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class TelephoneBook implements EntryPoint {

	private PhoneBookServiceAsync phoneBookService = GWT
			.create(PhoneBookService.class);
	private Label errorLabelStreet = new Label("Street");
	private Label errorLabelPhone = new Label("Phone");	
	private Button addButton = new Button("Add");
	private FlexTable phonesFlexTable = new FlexTable();
	private VerticalPanel mainPanel = new VerticalPanel();
	private HorizontalPanel addPanel = new HorizontalPanel();
	private TextBox firstName = new TextBox();
	private TextBox lastName = new TextBox();
	private TextBox patronymic = new TextBox();
	private TextBox phoneNumber = new TextBox();
	private TextBox street = new TextBox();
	private TextBox houseNumber = new TextBox();
	private TextBox flatNumber = new TextBox();

	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	public void onModuleLoad() {

		initWidgets();
		initHandlers();
		refreshPhonesTable();
	}

	private void initWidgets() {
		phonesFlexTable.setText(0, 0, "Фамилия");
		phonesFlexTable.setText(0, 1, "Имя");
		phonesFlexTable.setText(0, 2, "Отчество");
		phonesFlexTable.setText(0, 3, "Телефон");
		phonesFlexTable.setText(0, 4, "Улица");
		phonesFlexTable.setText(0, 5, "Номер дома");
		phonesFlexTable.setText(0, 6, "Номер квартиры");
		phonesFlexTable.setCellPadding(6);
		phonesFlexTable.getRowFormatter().addStyleName(0, "watchListHeader");
		phonesFlexTable.addStyleName("watchList");

		mainPanel.add(phonesFlexTable);
		RootPanel.get("phoneNumberList").add(mainPanel);
		RootPanel.get().add(errorLabelStreet);		
		addPanel.add(lastName);
		addPanel.add(firstName);		
		addPanel.add(patronymic);
		addPanel.add(phoneNumber);
		addPanel.add(street);
		addPanel.add(houseNumber);
		addPanel.add(flatNumber);
		addPanel.add(addButton);
		RootPanel.get().add(addPanel);
		RootPanel.get().add(errorLabelPhone);
	}

	private void initHandlers() {

		addButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent arg0) {
				Street streetName = new Street();
				streetName.setStreet(street.getText());
																				
				Phone phone = new Phone();
				
				phone.setlastName(lastName.getText());
				phone.setfirstName(firstName.getText());				
				phone.setPatronymic(patronymic.getText());
				phone.setTelephone(phoneNumber.getText());				
			    phone.setStreet(streetName);
				phone.setHouseNumber(Integer.parseInt(houseNumber.getText()));
				phone.setFlatNumber(Integer.parseInt(flatNumber.getText()));

				phoneBookService.addPhone(phone, new AsyncCallback<Void>() {
					
					public void onSuccess(Void arg0) {
						errorLabelPhone.setText("Ok!");
						
						lastName.setText("");
						firstName.setText("");
						patronymic.setText("");
						phoneNumber.setText("");
						street.setText("");
						houseNumber.setText("");
						flatNumber.setText("");
						
						refreshPhonesTable();						
					}
					
					public void onFailure(Throwable caught) {
						errorLabelPhone.setText(SERVER_ERROR);
						
					}
				});
			}
		});
	}

	private void refreshPhonesTable() {

		phoneBookService.listPhone(new AsyncCallback<List<Phone>>() {

			public void onSuccess(final List<Phone> phoneList) {
				phonesFlexTable.removeAllRows();
				initWidgets();
				
				for (final Phone phone : phoneList) {
					Button removePhoneButton = new Button("x");
					removePhoneButton.addStyleDependentName("remove");
					removePhoneButton.addClickHandler(new ClickHandler() {
				      public void onClick(ClickEvent event) {
				    	  removePhoneFromList(phone);		
				    	  int removedIndex = phoneList.indexOf(phone);
					        phoneList.remove(removedIndex);
					        phonesFlexTable.removeRow(removedIndex+1);
				      }					
				    });
					
					int row = phonesFlexTable.getRowCount();
					phonesFlexTable.setText(row, 0, phone.getlastName()); 							
					phonesFlexTable.setText(row, 1, phone.getfirstName()); 
					phonesFlexTable.setText(row, 2, phone.getPatronymic());
					phonesFlexTable.setText(row, 3, phone.getTelephone());					
					phonesFlexTable.setText(row, 4, phone.getStreet().getStreet()
							.toString());
					phonesFlexTable.setText(row, 5, phone.getHouseNumber()
							.toString());
					phonesFlexTable.setText(row, 6, phone.getFlatNumber()
							.toString());
					phonesFlexTable.setWidget(row, 7, removePhoneButton);
				}

			}

			public void onFailure(Throwable caught) {
				errorLabelPhone.setText(SERVER_ERROR);
			}
		});
	}
	
	private void removePhoneFromList(Phone phone) {		
		
		phoneBookService.removePhone(phone.getId(), new AsyncCallback<Void>() {
			
			public void onSuccess(Void arg0) {
				errorLabelPhone.setText("Phone remove");
				
			}
			
			public void onFailure(Throwable arg0) {
				errorLabelPhone.setText("Error remove");
				
			}
		});
		
	}

}
