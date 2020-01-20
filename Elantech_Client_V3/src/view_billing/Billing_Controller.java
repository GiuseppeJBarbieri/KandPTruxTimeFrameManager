package view_billing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
import event_objects.Search_Store_Billing_Event_Object;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model_trucking.Billing_Model;
import model_trucking.Store_Model;
import model_trucking.TimeFrame_Model;
import models.User_Account_Information;
import view_main.Main_View_Controller;

public class Billing_Controller implements Initializable {
	@FXML
	private TextField totalTxt, billingCostTxt;
	@FXML
	private TableView<Store_Model> storeTableView;
	@FXML
	private TableColumn<Store_Model, String> storeNameCol, storeAddressCol, storeEmailCol, storePhoneNumberCol,
			storeFaxCol, storeIDCol;
	@FXML
	private TableView<Billing_Model> billingTableView;
	@FXML
	private TableColumn<Billing_Model, String> storeIDBillCol, storeNameBillCol;
	@FXML
	private TableColumn<Billing_Model, Double> billingCostCol;
	@FXML
	private MenuItem removeStoreMenuItem, printMenuItem;
	@FXML
	private DatePicker datePicker;
	@FXML
	private Button addToBillingBtn;

	private Main_View_Controller main_View_Controller;
	private Client_Controller clientController;
	private User_Account_Information user_Account_Model;
	private AnchorPane main_view_pane;
	private ArrayList<Billing_Model> billingList;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		billingList = new ArrayList<>();
		setStoreTableColumnFactory();
		setBillingTableColumnFactory();
		addToBillingBtn.setOnAction(e -> addToBillingTable());

		billingCostTxt.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					billingCostTxt.setText(newValue.replaceAll("[^\\d\\.]", ""));
				}
			}
		});
		removeStoreMenuItem.setOnAction(e -> removeStore());

		printMenuItem.setOnAction(e -> printBillingList());
	}

	private void removeStore() {
		String storeID = billingTableView.getSelectionModel().getSelectedItem().getStoreId();
		for (int i = 0; i < billingList.size(); i++) {
			if (storeID.equals(billingTableView.getSelectionModel().getSelectedItem().getStoreId())) {
				billingList.remove(i);
				break;
			}
		}

		ObservableList<Billing_Model> tableList = FXCollections.observableArrayList(billingList);
		billingTableView.setItems(tableList);
	}

	private static String[] columns = { "Store Name", "Billing Amount" };
	private void printBillingList() {

		Workbook workbook = new XSSFWorkbook();
		@SuppressWarnings("unused")
		CreationHelper createHelper = workbook.getCreationHelper();
		Sheet sheet = workbook.createSheet("Billing");
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.BLACK.getIndex());
		headerFont.setFontName("Verdana");

		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		Row rowH1 = sheet.createRow(0);
		Cell cellH1 = rowH1.createCell(0);
		cellH1.setCellValue("ITC Trucking");
		cellH1.setCellStyle(headerCellStyle);

		Row rowH2 = sheet.createRow(1);
		Cell cellH2 = rowH2.createCell(0);
		cellH2.setCellValue("Billing Sheet");
		cellH2.setCellStyle(headerCellStyle);
		

		Row headerRow = sheet.createRow(3);
		for (int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
			cell.setCellStyle(headerCellStyle);
		}

		int rowNum = 4;
		for (Billing_Model e : billingList) {
			Row row = sheet.createRow(rowNum);
			row.createCell(0).setCellValue(e.getStoreName());
			row.createCell(1).setCellValue("$" + Double.toString(e.getCost()));
			rowNum++;
		}

		Font totalFont = workbook.createFont();
		totalFont.setBold(true);
		totalFont.setColor(IndexedColors.BLACK.getIndex());
		totalFont.setFontName("Verdana");
		
		rowNum++;
		Row row = sheet.createRow(rowNum);
		row.createCell(0).setCellValue("Total:");
		row.getCell(0).setCellStyle(headerCellStyle);
		row.createCell(1).setCellValue(totalTxt.getText());
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

	public void setMainViewController(Main_View_Controller main_View_Controller) {
		this.main_View_Controller = main_View_Controller;
	}

	public void setClientController(Client_Controller client) {
		this.clientController = client;
	}

	public void setUserAccountModel(User_Account_Information user_Account_Model) {
		this.user_Account_Model = user_Account_Model;
	}

	public void setMainViewPane(AnchorPane main_view_pane) {
		this.main_view_pane = main_view_pane;
	}

	public void searchAllStores(String string) {
		Search_Store_Billing_Event_Object itemObj = new Search_Store_Billing_Event_Object(string);
		clientController.setBillingController(this);
		clientController.sendMessage(itemObj);
		LocalDate previousSaturday = LocalDate.now(ZoneId.of("America/Montreal"))
				.with(TemporalAdjusters.previous(DayOfWeek.SATURDAY));

		datePicker.setValue(previousSaturday);
	}

	public void setStoreTable(ArrayList<Store_Model> storeList) {
		ObservableList<Store_Model> tableList = FXCollections.observableArrayList(storeList);
		storeTableView.setItems(tableList);
	}

	public void addToBillingTable() {
		boolean found = false;
		for (int i = 0; i < billingList.size(); i++) {
			if (billingList.get(i).getStoreId()
					.equals(storeTableView.getSelectionModel().getSelectedItem().getStoreID())) {
				found = true;
				Alert_Box alertBox = new Alert_Box();
				alertBox.errorAlertBox("Error", "Store already added!", "Click ok to continue...");
				break;
			}
		}

		if (found == false) {
			billingList.add(new Billing_Model(storeTableView.getSelectionModel().getSelectedItem().getStoreID(),
					storeTableView.getSelectionModel().getSelectedItem().getStoreName(),
					Double.parseDouble(billingCostTxt.getText())));
			ObservableList<Billing_Model> tableList = FXCollections.observableArrayList(billingList);
			billingTableView.setItems(tableList);
			double total = 0;
			for (int i = 0; i < billingList.size(); i++) {
				total += billingList.get(i).getCost();
			}
			totalTxt.setText("$" + Double.toString(total));
			billingCostTxt.clear();
		}
	}

	private void setStoreTableColumnFactory() {
		storeNameCol.setCellValueFactory(new PropertyValueFactory<Store_Model, String>("storeName"));
		storeAddressCol.setCellValueFactory(new PropertyValueFactory<Store_Model, String>("address"));
		storeEmailCol.setCellValueFactory(new PropertyValueFactory<Store_Model, String>("email"));
		storePhoneNumberCol.setCellValueFactory(new PropertyValueFactory<Store_Model, String>("phoneNumber"));
		storeFaxCol.setCellValueFactory(new PropertyValueFactory<Store_Model, String>("fax"));
		storeIDCol.setCellValueFactory(new PropertyValueFactory<Store_Model, String>("storeID"));
	}

	private void setBillingTableColumnFactory() {
		storeIDBillCol.setCellValueFactory(new PropertyValueFactory<Billing_Model, String>("storeId"));
		storeNameBillCol.setCellValueFactory(new PropertyValueFactory<Billing_Model, String>("storeName"));
		billingCostCol.setCellValueFactory(new PropertyValueFactory<Billing_Model, Double>("cost"));
	}
}
