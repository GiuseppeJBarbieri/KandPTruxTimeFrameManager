package view_dashboard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import alerts.Alert_Box;
import client_to_server_controller.Client_Controller;
import event_objects.Remove_Store_Event_Object;
import event_objects.Search_All_TimeFrame_Event_Object;
import event_objects.Search_TimeFrame_Event_Object;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model_trucking.Remove_TimeFrame_Event_Object;
import model_trucking.Search_Store_Event_Object;
import model_trucking.Store_Model;
import model_trucking.TimeFrame_Model;
import models.User_Account_Information;
import view_add_store.Show_Add_Store;
import view_add_timeframe.Show_Add_TimeFrame;
import view_edit_store.Show_Edit_Store;
import view_edit_timeframe.Show_Edit_TimeFrame;
import view_main.Main_View_Controller;

public class Dashboard_Controller implements Initializable {

	@FXML
	private MenuButton inventoryMenuBtn, productsMenuBtn;
	@FXML
	private TextField searchTxt;
	/* Product Table */
	@FXML
	private DatePicker datePicker;
	@FXML
	private TableView<Store_Model> storeTableView;
	@FXML
	private TableColumn<Store_Model, String> storeNameCol, storeAddressCol, storeEmailCol, storePhoneNumberCol,
			storeFaxCol, storeIDcol;
	/* Product Table */
	@FXML
	private TableView<TimeFrame_Model> timeFrameTableView;
	@FXML
	private TableColumn<TimeFrame_Model, String> orderIdCol, customerNameCol, customerTownCol, customerPhoneNumberCol,
			driverCol, timeFrameStartCol, timeFrameEndCol, orderDateCol;
	@FXML
	private TableColumn<TimeFrame_Model, Double> codCol;
	/* Product Menu */
	@FXML
	private MenuItem saveStoreTableMenuItem, addStoreMenuItem, editStoreMenuItem, removeStoreMenuItem,
			emailStoreMenuItem, emailAllStoresMenuItem;

	/* Inventory Menu */
	@FXML
	private MenuItem saveTimeFrameMenuItem, addTimeFrameMenuItem, editTimeFrameMenuItem, removeTimeFrameMenuItem;

	private Client_Controller clientController;
	private ArrayList<Store_Model> storeList;
	private ArrayList<TimeFrame_Model> timeFrameList;
	private ArrayList<TimeFrame_Model> emailTimeFrameList;
	ArrayList<TimeFrame_Model> allTimeFrames;

	private Main_View_Controller main_View_Controller;
	private User_Account_Information usermodel;
	private AnchorPane mainViewPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		emailTimeFrameList = new ArrayList<>();
		setProductTableColumnFactory();
		setInventoryTableColumnFactory();
		searchTxtFieldListener();
		menuListeners();
		storeTableListener();
		datePicker.setValue(LocalDate.now());
		setDatePickerListener();
		timeFrameList = new ArrayList<>();
	}

	private void setDatePickerListener() {
		datePicker.setOnAction(e -> {
			LocalDate localDate = datePicker.getValue();
			ArrayList<TimeFrame_Model> timeFrameList2 = new ArrayList<>();
			System.out.println(localDate.toString());

			for (int i = 0; i < timeFrameList.size(); i++) {
				if (timeFrameList.get(i).getOrderDate().equals(localDate.toString())) {
					timeFrameList2.add(timeFrameList.get(i));
				}
			}
			ObservableList<TimeFrame_Model> tableList = FXCollections.observableArrayList(timeFrameList2);
			timeFrameTableView.setItems(tableList);
		});
	}

	private void storeTableListener() {
		storeTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				Search_TimeFrame_Event_Object itemObj = new Search_TimeFrame_Event_Object(newSelection.getStoreID());
				clientController.setDashboardController(this);
				clientController.sendMessage(itemObj);
			}

		});
	}

	

	/* All menu options for products table */
	private void menuListeners() {
		saveStoreTableMenuItem.setOnAction(e -> saveTable());

		saveTimeFrameMenuItem.setOnAction(e -> saveTimeTbl(timeFrameList));

		addStoreMenuItem
				.setOnAction(e -> new Show_Add_Store(main_View_Controller, mainViewPane, clientController, usermodel));

		editStoreMenuItem.setOnAction(e -> {
			if (storeTableView.getSelectionModel().getSelectedItem() != null) {
				new Show_Edit_Store(main_View_Controller, mainViewPane, clientController, usermodel,
						storeTableView.getSelectionModel().getSelectedItem());
			} else {
				Alert_Box alertBox = new Alert_Box();
				alertBox.errorAlertBox("Error", "No Store selected!", "Click ok to continue...");
			}
		});

		removeStoreMenuItem.setOnAction(e -> {
			if (storeTableView.getSelectionModel().getSelectedItem() == null) {
				Alert_Box alertBox = new Alert_Box();
				alertBox.errorAlertBox("Error", "No Store selected!", "Click ok to continue...");
			} else {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Remove Store Confimation");
				alert.setHeaderText("You are about to remove "
						+ storeTableView.getSelectionModel().getSelectedItem().getStoreName());
				alert.setContentText("Are you sure?");

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
					Remove_Store_Event_Object evobj = new Remove_Store_Event_Object(
							storeTableView.getSelectionModel().getSelectedItem().getStoreID());
					clientController.sendMessage(evobj);
					searchStore("");
				} else {
					alert.close();
				}

			}
		});

		/* INVENTORY MENU ITEM LISTENERS */

		emailStoreMenuItem.setOnAction(e -> {
			LocalDate today = LocalDate.now();

		});

		emailAllStoresMenuItem.setOnAction(e -> {
			Search_All_TimeFrame_Event_Object itemObj = new Search_All_TimeFrame_Event_Object("");
			clientController.setDashboardController(this);
			clientController.sendMessage(itemObj);
		});

		addTimeFrameMenuItem.setOnAction(e -> {

			if (storeTableView.getSelectionModel().getSelectedItem() != null) {
				new Show_Add_TimeFrame(main_View_Controller, mainViewPane, clientController, usermodel,
						storeTableView.getSelectionModel().getSelectedItem());
			} else {
				Alert_Box alertBox = new Alert_Box();
				alertBox.errorAlertBox("Error", "No Store selected!", "Click ok to continue...");
			}
		});

		editTimeFrameMenuItem.setOnAction(e -> {
			if (timeFrameTableView.getSelectionModel().getSelectedItem() != null) {
				new Show_Edit_TimeFrame(main_View_Controller, mainViewPane, clientController, usermodel,
						timeFrameTableView.getSelectionModel().getSelectedItem(),
						storeTableView.getSelectionModel().getSelectedItem());
			} else {
				Alert_Box alertBox = new Alert_Box();
				alertBox.errorAlertBox("Error", "No Time Frame selected!", "Click ok to continue...");
			}
		});

		removeTimeFrameMenuItem.setOnAction(e -> {
			if (storeTableView.getSelectionModel().getSelectedItem() == null) {
				Alert_Box alertBox = new Alert_Box();
				alertBox.errorAlertBox("Error", "No Time Frame selected!", "Click ok to continue...");
			} else {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Remove Time Frame Confimation");
				alert.setHeaderText("You are about to remove "
						+ timeFrameTableView.getSelectionModel().getSelectedItem().getName());
				alert.setContentText("Are you sure?");

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
					Remove_TimeFrame_Event_Object evobj = new Remove_TimeFrame_Event_Object(
							timeFrameTableView.getSelectionModel().getSelectedItem().getOrderID());
					clientController.sendMessage(evobj);
					searchStore("");
				} else {
					alert.close();
				}

			}
		});
	}

	private void searchTxtFieldListener() {
		searchTxt.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				searchStore(searchTxt.getText());
			}
		});
	}

	/* SEND TO SERVER MEHODS */
	public void searchStore(String store) {
		Search_Store_Event_Object itemObj = new Search_Store_Event_Object(store);
		clientController.setDashboardController(this);
		clientController.sendMessage(itemObj);
	}

	/* TABLE VIEW METHODS */
	private void setProductTableColumnFactory() {
		storeNameCol.setCellValueFactory(new PropertyValueFactory<Store_Model, String>("storeName"));
		storeAddressCol.setCellValueFactory(new PropertyValueFactory<Store_Model, String>("address"));
		storeEmailCol.setCellValueFactory(new PropertyValueFactory<Store_Model, String>("email"));
		storePhoneNumberCol.setCellValueFactory(new PropertyValueFactory<Store_Model, String>("phoneNumber"));
		storeFaxCol.setCellValueFactory(new PropertyValueFactory<Store_Model, String>("fax"));
		storeIDcol.setCellValueFactory(new PropertyValueFactory<Store_Model, String>("storeID"));
	}

	private void setInventoryTableColumnFactory() {
		orderIdCol.setCellValueFactory(new PropertyValueFactory<TimeFrame_Model, String>("orderID"));
		customerNameCol.setCellValueFactory(new PropertyValueFactory<TimeFrame_Model, String>("name"));
		customerTownCol.setCellValueFactory(new PropertyValueFactory<TimeFrame_Model, String>("town"));
		customerPhoneNumberCol.setCellValueFactory(new PropertyValueFactory<TimeFrame_Model, String>("phoneNumber"));
		driverCol.setCellValueFactory(new PropertyValueFactory<TimeFrame_Model, String>("driver"));
		timeFrameStartCol.setCellValueFactory(new PropertyValueFactory<TimeFrame_Model, String>("timeFrameStart"));
		timeFrameEndCol.setCellValueFactory(new PropertyValueFactory<TimeFrame_Model, String>("timeFrameEnd"));
		orderDateCol.setCellValueFactory(new PropertyValueFactory<TimeFrame_Model, String>("orderDate"));
		codCol.setCellValueFactory(new PropertyValueFactory<TimeFrame_Model, Double>("cod"));
	}

	/* Setters */
	
	
	public void setAllTimeFrames(ArrayList<TimeFrame_Model> allTimeFrames) {
		this.allTimeFrames = allTimeFrames;
	}

	public void setClientController(Client_Controller clientController) {
		this.clientController = clientController;
		clientController.setDashboardController(this);
	}

	public void setStoreList(ArrayList<Store_Model> storeList) {
		this.storeList = storeList;
		ObservableList<Store_Model> tableList = FXCollections.observableArrayList(storeList);
		storeTableView.setItems(tableList);
	}

	public void setTimeFrameList(ArrayList<TimeFrame_Model> timeFrameList) {
		this.timeFrameList = timeFrameList;
		LocalDate localDate = datePicker.getValue();
		ArrayList<TimeFrame_Model> timeFrameList2 = new ArrayList<>();
		System.out.println(localDate.toString());

		for (int i = 0; i < timeFrameList.size(); i++) {
			if (timeFrameList.get(i).getOrderDate().equals(localDate.toString())) {
				timeFrameList2.add(timeFrameList.get(i));
			}
		}
		ObservableList<TimeFrame_Model> tableList = FXCollections.observableArrayList(timeFrameList2);
		timeFrameTableView.setItems(tableList);

	}

	public void setMainViewController(Main_View_Controller main_View_Controller) {
		this.main_View_Controller = main_View_Controller;
	}

	public void setUserAccountModel(User_Account_Information usermodel) {
		this.usermodel = usermodel;
	}

	public void setMainViewPane(AnchorPane main_view_pane) {
		this.mainViewPane = main_view_pane;
	}

	/* Save Table to excel */
	/*
	 * These are for Saving to table
	 */
	private static String[] columns = { "Store Name", "Address", "Email", "Phone Number", "Fax Number" };

	private void saveTable() {
		ArrayList<Store_Model> storeList2 = new ArrayList<>();
		ObservableList<Store_Model> tableList = storeTableView.getItems();
		for (Store_Model f : tableList) {
			storeList2.add(f);
		}
		Workbook workbook = new XSSFWorkbook();
		@SuppressWarnings("unused")
		CreationHelper createHelper = workbook.getCreationHelper();
		Sheet sheet = workbook.createSheet("Products");
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.BLACK.getIndex());
		headerFont.setFontName("Verdana");

		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		Row rowH1 = sheet.createRow(0);
		Cell cellH1 = rowH1.createCell(0);
		cellH1.setCellValue("Elantech LLC");
		cellH1.setCellStyle(headerCellStyle);

		Row rowH2 = sheet.createRow(1);
		Cell cellH2 = rowH2.createCell(0);
		cellH2.setCellValue("Product Sheet");
		cellH2.setCellStyle(headerCellStyle);
		Row rowH3 = sheet.createRow(2);
		Cell cellH3 = rowH3.createCell(0);
		cellH3.setCellValue(usermodel.getFirst_name() + " " + usermodel.getLast_name());
		cellH3.setCellStyle(headerCellStyle);
		Row rowH4 = sheet.createRow(3);
		Cell cellH4 = rowH4.createCell(0);
		cellH4.setCellValue(usermodel.getEmail_address());
		cellH4.setCellStyle(headerCellStyle);

		Row headerRow = sheet.createRow(5);
		for (int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
			cell.setCellStyle(headerCellStyle);
		}

		int rowNum = 6;
		for (Store_Model e : storeList2) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(e.getStoreName());
			row.createCell(1).setCellValue(e.getAddress());
			row.createCell(2).setCellValue(e.getEmail());
			row.createCell(3).setCellValue(e.getPhoneNumber());
			row.createCell(4).setCellValue(e.getFax());

		}

		// Resize all columns to fit the content size
		for (int i = 0; i < columns.length; i++) {
			sheet.autoSizeColumn(i);
		}

		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XLSX files (*.xlsx)", "*.xlsx");
		fileChooser.getExtensionFilters().add(extFilter);
		Stage fileChooseStage = new Stage();
		// Show save file dialog
		File file = fileChooser.showSaveDialog(fileChooseStage);

		if (file != null) {
			saveFile(workbook, file);
		}
		// Closing the workbook
		try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
			// log.debug(e);
		}

	}

	private static String[] invColumns = { "Customer Name", "Town", "Phone Number", "Driver", "TimeFrame" };

	private void saveTimeTbl(ArrayList<TimeFrame_Model> inventoryList) {
		if (timeFrameTableView.getSelectionModel().getSelectedItem() != null) {
			Workbook workbook = new XSSFWorkbook();
			@SuppressWarnings("unused")
			CreationHelper createHelper = workbook.getCreationHelper();
			Sheet sheet = workbook.createSheet("Products");
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setFontHeightInPoints((short) 14);
			headerFont.setColor(IndexedColors.BLACK.getIndex());
			headerFont.setFontName("Verdana");

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			Row rowH1 = sheet.createRow(0);
			Cell cellH1 = rowH1.createCell(0);
			cellH1.setCellValue("Elantech LLC");
			cellH1.setCellStyle(headerCellStyle);

			Row rowH2 = sheet.createRow(1);
			Cell cellH2 = rowH2.createCell(0);
			cellH2.setCellValue("Product Sheet");
			cellH2.setCellStyle(headerCellStyle);
			Row rowH3 = sheet.createRow(2);
			Cell cellH3 = rowH3.createCell(0);
			cellH3.setCellValue(usermodel.getFirst_name() + " " + usermodel.getLast_name());
			cellH3.setCellStyle(headerCellStyle);
			Row rowH4 = sheet.createRow(3);
			Cell cellH4 = rowH4.createCell(0);
			cellH4.setCellValue(usermodel.getEmail_address());
			cellH4.setCellStyle(headerCellStyle);

			Row headerRow = sheet.createRow(5);
			for (int i = 0; i < columns.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(columns[i]);
				cell.setCellStyle(headerCellStyle);
			}

			Row prodRow = sheet.createRow(6);
			Store_Model prodMod = storeTableView.getSelectionModel().getSelectedItem();
			prodRow.createCell(0).setCellValue(prodMod.getStoreName());
			prodRow.createCell(1).setCellValue(prodMod.getAddress());
			prodRow.createCell(2).setCellValue(prodMod.getEmail());
			prodRow.createCell(3).setCellValue(prodMod.getPhoneNumber());
			prodRow.createCell(4).setCellValue(prodMod.getFax());
			Row headerRow2 = sheet.createRow(9);
			for (int i = 0; i < invColumns.length; i++) {
				Cell cell = headerRow2.createCell(i);
				cell.setCellValue(columns[i]);
				cell.setCellStyle(headerCellStyle);
			}

			int rowNum = 10;
			for (TimeFrame_Model e : inventoryList) {
				Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(e.getName());
				row.createCell(1).setCellValue(e.getTown());
				row.createCell(2).setCellValue(e.getPhoneNumber());
				row.createCell(3).setCellValue(e.getDriver());
				row.createCell(4).setCellValue(e.getTimeFrameStart() + " - " + e.getTimeFrameEnd());
				row.createCell(5).setCellValue(e.getOrderDate());

			}

			// Resize all columns to fit the content size
			for (int i = 0; i < columns.length; i++) {
				sheet.autoSizeColumn(i);
			}

			FileChooser fileChooser = new FileChooser();

			// Set extension filter
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XLSX files (*.xlsx)", "*.xlsx");
			fileChooser.getExtensionFilters().add(extFilter);
			Stage fileChooseStage = new Stage();
			// Show save file dialog
			File file = fileChooser.showSaveDialog(fileChooseStage);

			if (file != null) {
				saveFile(workbook, file);
			}
			// Closing the workbook
			try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
				// log.debug(e);
			}
		} else {
			Alert_Box alertBox = new Alert_Box();
			alertBox.errorAlertBox("Error", "No product selected!", "Click ok to continue...");
		}
	}

	private void saveFile(Workbook workbook, File file) {
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(file);
			workbook.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			// log.debug(e);
		} catch (IOException e) {
			e.printStackTrace();
			// log.debug(e);
		}
	}
}
