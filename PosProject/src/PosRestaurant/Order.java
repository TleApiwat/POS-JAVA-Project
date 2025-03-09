package PosRestaurant;

import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.*;


public class Order {
	private int orderId;
	private Map<Integer, String> items = new HashMap<>();
	private Map<String, String> itemsDate = new HashMap<>();
	private double totalAmount;
	private String orderStatus;
	private String Orderdate;

	
	LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    String formattedDate = now.format(formatter);
	
	public int getOrderId() {return orderId;}

	public void setOrderId(int orderId) {this.orderId = orderId;}

	public Map<Integer, String> getItems() {return items;}

	public void setItems(Map<Integer, String> items) {this.items = items;}

	public double getTotalAmount() {return totalAmount;}

	public void setTotalAmount(double totalAmount) {this.totalAmount = totalAmount;}

	public String getOrderStatus() {return orderStatus;}

	public void setOrederStatus(String orderStatus) {this.orderStatus = orderStatus;}
	
	public String getOrderDate() {Orderdate = formattedDate; return Orderdate;}
	
	public void addItem() {
		
	}

}
