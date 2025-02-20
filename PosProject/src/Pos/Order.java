package Pos;

import java.util.ArrayList;

public class Order extends Restaurant{
	private ArrayList<String> items = new ArrayList<String>();
	private double total_price;
	private boolean is_sent_to_kitchen;
}
