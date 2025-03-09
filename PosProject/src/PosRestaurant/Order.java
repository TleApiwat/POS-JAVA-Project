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
    public static String dbPath = "jdbc:sqlite:" + "sample.db";
   

	public Order(int id, String name2, double totalAmount2, String orderStatus2, LocalDateTime dateTime) {
		// TODO Auto-generated constructor stub
	}

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
	public void saveOrder() {
        try (Connection conn = DriverManager.getConnection(dbPath);
             PreparedStatement stmt = conn.prepareStatement(
                 "INSERT INTO orders (order_id, name, total_amount, order_status, order_date) " +
                 "VALUES (?, ?, ?, ?, ?)")) {
            
            stmt.setInt(1, this.orderId);
            stmt.setString(2, this.name);
            stmt.setDouble(3, this.totalAmount);
            stmt.setString(4, this.orderStatus);
            
            // Convert LocalDateTime to java.sql.Timestamp for storage
            Timestamp timestamp = Timestamp.valueOf(this.orderDate);
            stmt.setTimestamp(5, timestamp);
            
            stmt.executeUpdate();
            System.out.println("Order saved successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Method to get all orders from the database
    public static List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        
        try (Connection conn = DriverManager.getConnection(dbPath);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM orders")) {
            
            while (rs.next()) {
                int id = rs.getInt("order_id");
                String name = rs.getString("name");
                double totalAmount = rs.getDouble("total_amount");
                String orderStatus = rs.getString("order_status");
                
                // Convert from sqlTimestamp to LocalDateTime
                Timestamp timestamp = rs.getTimestamp("order_date");
                LocalDateTime dateTime = timestamp.toLocalDateTime();
                
                orders.add(new Order(id, name, totalAmount, orderStatus, dateTime));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return orders;
    }
    
    // Method to get a single order by ID
    public static Order getOrderById(int orderId) {
        try (Connection conn = DriverManager.getConnection(dbPath);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM orders WHERE order_id = ?")) {
            
            stmt.setInt(1, orderId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    double totalAmount = rs.getDouble("total_amount");
                    String orderStatus = rs.getString("order_status");
                    Timestamp timestamp = rs.getTimestamp("order_date");
                    LocalDateTime dateTime = timestamp.toLocalDateTime();
                    
                    return new Order(orderId, name, totalAmount, orderStatus, dateTime);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null; // Order not found
    }public static boolean removeItems(int orderId) {
        String sql = "DELETE FROM orders WHERE order_id = ?";
        int rowsAffected = 0;
        
        try (Connection conn = DriverManager.getConnection(dbPath);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Set the order ID parameter
            stmt.setInt(1, orderId);
            
            // Execute the delete operation
            rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Order #" + orderId + " removed successfully");
                return true;
            } else {
                System.out.println("No order found with ID: " + orderId);
                return false;
            }
            
        } catch (SQLException e) {
            System.out.println("Error removing order: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    
}

}