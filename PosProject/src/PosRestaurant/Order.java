package PosRestaurant;

import java.util.*;

public class Order {
	private int orderId;
	private Map<Integer, String> items = new HashMap<>();
	private double totalAmount;
	private String orederStatus;
	private Date orderDate;
	
	public int getOrderId() {return orderId;}

	public void setOrderId(int orderId) {this.orderId = orderId;}

	public Map<Integer, String> getItems() {return items;}

	public void setItems(Map<Integer, String> items) {this.items = items;}

	public double getTotalAmount() {return totalAmount;}

	public void setTotalAmount(double totalAmount) {this.totalAmount = totalAmount;}

	public String getOrederStatus() {return orederStatus;}

	public void setOrederStatus(String orederStatus) {this.orederStatus = orederStatus;}
	
	public Date getOrderDate() {return orderDate;}

	public void setOrderDate(Date orderDate) {this.orderDate = orderDate;}

	public void addItem(String items){
		
	} 
}
