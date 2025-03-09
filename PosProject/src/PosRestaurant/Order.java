package PosRestaurant;

import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.*;

public class Order {
	private int orderId;
	private double totalAmount;
	private String orderStatus;
	private String orderDate;
	private String name;
	
	LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    String formattedDate = now.format(formatter);
    String dbPath = "jdbc:sqlite:" + "sample.db";
   

	public int getOrderId() { return orderId; }

	public void setOrderId(int orderId) { this.orderId = orderId; }

	public double getTotalAmount() { return totalAmount; }

	public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

	public String getOrderStatus() { return orderStatus; }

	public void setOrderStatus(String orderStatus) { this.orderStatus = orderStatus; }
	
	public String getOrderDate() {return orderDate; }

	public void setOrderDate(){orderDate = formattedDate;}
	
	public String getName() { return name; }
	
	public void setName(String name) { this.name = name; }

	public void createDatafile() {
		
		try (Connection conn = DriverManager.getConnection(dbPath);
	             Statement stmt = conn.createStatement()) {
	            String sql = "CREATE TABLE IF NOT EXISTS records " +
	                         "(id INT PRIMARY KEY, " +
	                         " name VARCHAR(100), " +
	                         " record_date DATE)";
	            stmt.executeUpdate(sql);
	            System.out.println("Table created successfully");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
		

}