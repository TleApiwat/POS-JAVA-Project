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
	private String orederStatus;
	private String Orderdate;
	 private static final String URL = "jdbc:mysql://localhost:3306/your_database_name";
	    private static final String USER = "your_username";
	    private static final String PASSWORD = "your_password";
	
	  
	LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    String formattedDate = now.format(formatter);
	
	public int getOrderId() {return orderId;}

	public void setOrderId(int orderId) {this.orderId = orderId;}

	public Map<Integer, String> getItems() {return items;}

	public void setItems(Map<Integer, String> items) {this.items = items;}

	public double getTotalAmount() {return totalAmount;}

	public void setTotalAmount(double totalAmount) {this.totalAmount = totalAmount;}

	public String getOrederStatus() {return orederStatus;}

	public void setOrederStatus(String orederStatus) {this.orederStatus = orederStatus;}
	
	
	
	public void addItem() {
		
	}private static void createTables() throws SQLException {
        String ordersTable = "CREATE TABLE IF NOT EXISTS Orders (" +
                "order_id INT PRIMARY KEY, " +
                "order_status VARCHAR(50), " +
                "order_date DATE)";

String orderItemsTable = "CREATE TABLE IF NOT EXISTS OrderItems (" +
                    "order_item_id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "order_id INT, " +
                    "item_id INT, " +
                    "item_name VARCHAR(255), " +
                    "FOREIGN KEY (order_id) REFERENCES Orders(order_id))";

String orderItemDatesTable = "CREATE TABLE IF NOT EXISTS OrderItemDates (" +
                        "item_id INT, " +
                        "item_name VARCHAR(255), " +
                        "item_date DATE, " +
                        "PRIMARY KEY (item_id, item_name))";

try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
conn.createStatement().execute(ordersTable);
conn.createStatement().execute(orderItemsTable);
conn.createStatement().execute(orderItemDatesTable);
}
}

private static void saveOrderToDatabase(Order order) throws SQLException {
try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
conn.setAutoCommit(false);

// Prepare and execute order insertion
String orderInsert = "INSERT INTO Orders (order_id, order_status, order_date) VALUES (?, ?, ?)";
try (PreparedStatement orderStmt = conn.prepareStatement(orderInsert)) {
   orderStmt.setInt(1, order.getOrderId());
   orderStmt.setString(2, order.getOrderStatus());
   orderStmt.setDate(3, java.sql.Date.valueOf(order.getOrderDate()));
   orderStmt.executeUpdate();
}

// Prepare and execute order items insertion
String itemInsert = "INSERT INTO OrderItems (order_id, item_id, item_name) VALUES (?, ?, ?)";
try (PreparedStatement itemStmt = conn.prepareStatement(itemInsert)) {
   for (Map.Entry<Integer, String> item : order.items.entrySet()) {
       itemStmt.setInt(1, order.getOrderId());
       itemStmt.setInt(2, item.getKey());
       itemStmt.setString(3, item.getValue());
       itemStmt.executeUpdate();
   }
}

// Prepare and execute item dates insertion
String dateInsert = "INSERT INTO OrderItemDates (item_id, item_name, item_date) VALUES (?, ?, ?)";
try (PreparedStatement dateStmt = conn.prepareStatement(dateInsert)) {
   for (Map.Entry<Integer, String> itemDate : order.itemsDate.entrySet()) {
       dateStmt.setInt(1, itemDate.getKey());
       dateStmt.setString(2, order.items.get(itemDate.getKey()));
       dateStmt.setDate(3, java.sql.Date.valueOf(itemDate.getValue()));
       dateStmt.executeUpdate();
   }
}

conn.commit();
}
}
}



