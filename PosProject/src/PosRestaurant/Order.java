package PosRestaurant;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
public class Order {
	private int orderId;
	List<String> Menuitem = new ArrayList<>();
	private double totalAmount;
	private String orederStatus;
	private Date orderDate;
}
