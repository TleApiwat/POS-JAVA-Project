package Pos;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Restaurant {
	private ArrayList<String> tables = new ArrayList<String>();
	private ArrayList<String> orders = new ArrayList<String>();
	private JFrame frame;

	
	

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Restaurant window = new Restaurant();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Restaurant() {
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
