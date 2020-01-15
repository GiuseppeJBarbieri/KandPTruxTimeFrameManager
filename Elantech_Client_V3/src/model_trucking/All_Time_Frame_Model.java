package model_trucking;

import java.io.Serializable;

public class All_Time_Frame_Model implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3990256652804290632L;
	private String storeName;
	private String storePhoneNum;
	private String orderID;
	private String customerName;
	private String town;
	private String customerPhoneNum;
	private String driver;
	private String timeframeStart;
	private String timeframeEnd;
	private String orderDate;
	private Double cod;
	public All_Time_Frame_Model(String storeName, String storePhoneNum, String orderID, String customerName,
			String town, String customerPhoneNum, String driver, String timeframeStart, String timeframeEnd,
			String orderDate, Double cod) {
		super();
		this.storeName = storeName;
		this.storePhoneNum = storePhoneNum;
		this.orderID = orderID;
		this.customerName = customerName;
		this.town = town;
		this.customerPhoneNum = customerPhoneNum;
		this.driver = driver;
		this.timeframeStart = timeframeStart;
		this.timeframeEnd = timeframeEnd;
		this.orderDate = orderDate;
		this.cod = cod;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getStoreName() {
		return storeName;
	}
	public String getStorePhoneNum() {
		return storePhoneNum;
	}
	public String getOrderID() {
		return orderID;
	}
	public String getCustomerName() {
		return customerName;
	}
	public String getTown() {
		return town;
	}
	public String getCustomerPhoneNum() {
		return customerPhoneNum;
	}
	public String getDriver() {
		return driver;
	}
	public String getTimeframeStart() {
		return timeframeStart;
	}
	public String getTimeframeEnd() {
		return timeframeEnd;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public Double getCod() {
		return cod;
	}
	
	
}
