package PosRestaurant;

import java.util.List;

public class PosMainSystem {
	 	private List menu;
	    private List orders;
	    private Kitchen kitchen;
	    private List payments;
	    private Dashboard dashboard;
	    
		public PosMainSystem(List menu, List orders, Kitchen kitchen, List payments, Dashboard dashboard) {
			super();
			this.menu = menu;
			this.orders = orders;
			this.kitchen = kitchen;
			this.payments = payments;
			this.dashboard = dashboard;
		}
}
