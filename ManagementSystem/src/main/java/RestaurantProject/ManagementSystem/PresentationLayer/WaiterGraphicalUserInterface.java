package RestaurantProject.ManagementSystem.PresentationLayer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import RestaurantProject.ManagementSystem.BusinessLayer.MenuItem;
import RestaurantProject.ManagementSystem.BusinessLayer.Order;
import RestaurantProject.ManagementSystem.BusinessLayer.RestaurantProcessing;

public class WaiterGraphicalUserInterface extends JPanel {

	private JLabel select = new JLabel("Select an operation: ");
	private JButton newOrder = new JButton("Add new order");
	private JButton viewAll = new JButton("View all orders");

	private JButton add = new JButton("Add");
	private JButton finish = new JButton("Finish");
	private JCheckBox computeBill = new JCheckBox("Compute Bill");
	private RestaurantProcessing restaurant;
	private JTable t;
	private JScrollPane scrollPane = new JScrollPane(t);
	private JLabel table = new JLabel("Select a table: ");
	private JCheckBox b1 = new JCheckBox("1");
	private JCheckBox b2 = new JCheckBox("2");
	private JCheckBox b3 = new JCheckBox("3");
	private JCheckBox b4 = new JCheckBox("4");
	private JCheckBox b5 = new JCheckBox("5");
	private ButtonGroup group = new ButtonGroup();
	private List<MenuItem> values;
	JPanel p = new JPanel();
	JPanel order = new JPanel();
	JPanel p4 = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p6 = new JPanel();

	private JPanel viewOrders = new JPanel();
	private JList ordersList;
	private JPanel p15 = new JPanel();

	public WaiterGraphicalUserInterface(RestaurantProcessing restaurantprocess) {
		this.restaurant = restaurantprocess;
		t = MainWindow.createTable(restaurant.getNames(), restaurant.getPrices());
		ordersList = new JList(restaurant.getOrderIDs().toArray());
		values = new ArrayList<MenuItem>();
		BorderLayout layout = new BorderLayout();
		this.setLayout(layout);

		group.add(b1);
		group.add(b2);
		group.add(b3);
		group.add(b4);
		group.add(b5);
		p6.add(table);
		p6.add(b1);
		p6.add(b2);
		p6.add(b3);
		p6.add(b4);
		p6.add(b5);

		p.add(select);
		p.add(newOrder);
		p.add(viewAll);

		BorderLayout layout2 = new BorderLayout();
		p2.add(add);
		p3.add(finish);
		p3.add(computeBill);
		p4.add(scrollPane);
		order.setLayout(layout2);
		order.add(p6, BorderLayout.LINE_START);
		order.add(p4, BorderLayout.PAGE_START);
		order.add(p2, BorderLayout.CENTER);
		order.add(p3, BorderLayout.PAGE_END);

		GridLayout layout3 = new GridLayout(0, 1);
		p15.add(ordersList);
		viewOrders.add(p15);

		this.add(p, BorderLayout.PAGE_START);
		this.setVisible(true);
		this.addNewOrderListener();
		this.addViewAllListener();
		this.addAddListener();
		this.addFinishListener();
	}

	public void addNewOrderListener() {
		newOrder.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				scrollPane.removeAll();
				p4.remove(scrollPane);
				t = MainWindow.createTable(restaurant.getNames(), restaurant.getPrices());
				scrollPane = new JScrollPane(t);
				scrollPane.setPreferredSize(new Dimension(250, 200));
				scrollPane.revalidate();
				p4.add(scrollPane);
				order.updateUI();
				add(order, BorderLayout.CENTER);
				order.setVisible(true);
				viewOrders.setVisible(false);
				updateUI();
			}
		});
	}

	public void addViewAllListener() {
		viewAll.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ordersList.setListData(restaurant.getOrderIDs().toArray());
				add(viewOrders, BorderLayout.CENTER);
				order.setVisible(false);
				viewOrders.setVisible(true);
				updateUI();
			}

		});
	}

	public void addAddListener() {
		add.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int row = t.getSelectedRow();
				values.add(restaurant.getMenus().get(row));
			}
		});
	}

	public void addFinishListener() {
		finish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Order newOrder = new Order();
				if (b1.isSelected())
					newOrder.setTable(Integer.parseInt(b1.getText()));
				else if (b2.isSelected())
					newOrder.setTable(Integer.parseInt(b2.getText()));
				else if (b3.isSelected())
					newOrder.setTable(Integer.parseInt(b3.getText()));
				else if (b4.isSelected())
					newOrder.setTable(Integer.parseInt(b4.getText()));
				else {
					if (b5.isSelected())
						newOrder.setTable(Integer.parseInt(b5.getText()));
					else {
						JOptionPane.showMessageDialog(null, "Error! Select a table!", "ORDER",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}

				createNewOrder(newOrder, values);
				if (computeBill.isSelected()) {
					generateBill(newOrder);
					JOptionPane.showMessageDialog(null, "Order succesfully added and bill created!", "ORDER",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Order succesfully added!", "ORDER",
							JOptionPane.INFORMATION_MESSAGE);
				}
				values.clear();
			}
		});
	}

	public void createNewOrder(Order key, List<MenuItem> value) {
		restaurant.createNewOrder(key, value);
	}

	public void generateBill(Order order) {
		restaurant.generateBill(order);

	}
}
