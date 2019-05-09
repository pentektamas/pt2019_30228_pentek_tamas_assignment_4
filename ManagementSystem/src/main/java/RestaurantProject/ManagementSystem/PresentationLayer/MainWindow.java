package RestaurantProject.ManagementSystem.PresentationLayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.*;

import RestaurantProject.ManagementSystem.BusinessLayer.MenuItem;
import RestaurantProject.ManagementSystem.BusinessLayer.Restaurant;
import RestaurantProject.ManagementSystem.DataLayer.RestaurantSerializator;

public class MainWindow extends JFrame {

	private JLabel login = new JLabel("Log in as: ");
	private JButton administrator = new JButton("Administrator");
	private JButton waiter = new JButton("Waiter\\Waitress");
	private JButton chef = new JButton("Chef");
	private JButton back = new JButton("Back");
	private Restaurant restaurant = new Restaurant();
	private WaiterGraphicalUserInterface waiterPanel = new WaiterGraphicalUserInterface(restaurant);
	private AdministratorGraphicalUserInterface administratorPanel = new AdministratorGraphicalUserInterface(
			restaurant);
	private ChefGraphicalUserInterface chefPanel = new ChefGraphicalUserInterface();
	RestaurantSerializator ser = new RestaurantSerializator();

	@SuppressWarnings("deprecation")
	public MainWindow() {
		restaurant.addObserver(chefPanel);
		final JPanel p = new JPanel();
		final JPanel p1 = new JPanel();
		final JPanel p2 = new JPanel();
		p2.add(back);
		administrator.setBackground(Color.RED);
		waiter.setBackground(Color.GREEN);
		chef.setBackground(Color.GRAY);
		p1.add(login);
		p1.add(administrator);
		p1.add(waiter);
		p1.add(chef);
		p.setLayout(new BorderLayout());
		p.add(p1, BorderLayout.PAGE_START);
		this.setContentPane(p);
		this.pack();
		this.setSize(new Dimension(600, 450));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(500, 200);
		this.setTitle("Restaurant Management");

		administrator.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				p.removeAll();
				p.add(p2, BorderLayout.PAGE_END);
				p.add(administratorPanel, BorderLayout.PAGE_START);
				p.updateUI();
			}
		});

		waiter.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				p.removeAll();
				p.add(p2, BorderLayout.PAGE_END);
				p.add(waiterPanel, BorderLayout.PAGE_START);
				p.updateUI();
			}
		});

		chef.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				p.removeAll();
				p.add(p2, BorderLayout.PAGE_END);
				p.add(chefPanel, BorderLayout.PAGE_START);
				p.updateUI();
			}
		});

		back.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				p.removeAll();
				p.add(p1, BorderLayout.PAGE_START);
				p.updateUI();
			}
		});

		this.addWindowListener(new WindowListener() {

			public void windowOpened(WindowEvent e) {
				List<MenuItem> menus = ser.deserialization();
				restaurant.setMenus(menus);
			}

			public void windowClosing(WindowEvent e) {
				ser.serialization(restaurant.getMenus());
			}

			public void windowClosed(WindowEvent e) {
			}

			public void windowIconified(WindowEvent e) {
			}

			public void windowDeiconified(WindowEvent e) {
			}

			public void windowActivated(WindowEvent e) {
			}

			public void windowDeactivated(WindowEvent e) {
			}
		});
	}

	public static JTable createTable(List<String> names, List<Integer> prices) {
		Object[][] objects;
		Object[] columnNames = { "Menus", "Prices" };
		objects = new Object[names.size()][2];
		for (int i = 0; i < names.size(); i++) {
			objects[i][0] = names.get(i);
			objects[i][1] = prices.get(i);
		}
		JTable table = new JTable(objects, columnNames);
		return table;
	}
}
