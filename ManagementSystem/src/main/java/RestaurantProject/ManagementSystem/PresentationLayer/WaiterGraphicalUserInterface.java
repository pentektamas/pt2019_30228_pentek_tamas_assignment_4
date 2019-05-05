package RestaurantProject.ManagementSystem.PresentationLayer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.TableModel;

import RestaurantProject.ManagementSystem.BusinessLayer.MenuItem;
import RestaurantProject.ManagementSystem.BusinessLayer.Order;
import RestaurantProject.ManagementSystem.BusinessLayer.Restaurant;
import RestaurantProject.ManagementSystem.BusinessLayer.RestaurantProcessing;

public class WaiterGraphicalUserInterface extends JPanel implements RestaurantProcessing {

	private JLabel select = new JLabel("Select an operation: ");
	private JButton newOrder = new JButton("Add new order");
	private JButton viewAll = new JButton("View all orders");
	private JButton bill = new JButton("Create a bill");

	private JButton add = new JButton("Add");
	private JButton finish = new JButton("Finish");
	private Restaurant restaurant;// = new Restaurant();
	private JTable t;// = MainWindow.createTable(restaurant.getNames(), restaurant.getPrices());
	private JScrollPane scrollPane = new JScrollPane(t);
	private JLabel table = new JLabel("Select a table: ");
	private JCheckBox b1 = new JCheckBox("1");
	private JCheckBox b2 = new JCheckBox("2");
	private JCheckBox b3 = new JCheckBox("3");
	private JCheckBox b4 = new JCheckBox("4");
	private JCheckBox b5 = new JCheckBox("5");
	private ButtonGroup group = new ButtonGroup();
	private ArrayList<MenuItem> values = new ArrayList<MenuItem>();
	// price for an Order???????????????????
	JPanel p = new JPanel();
	JPanel order = new JPanel();
	JPanel p4 = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p6 = new JPanel();

	private JLabel bills = new JLabel("Create a bill for: ");
	JList orders;
	private JPanel billGenerator = new JPanel();
	private JPanel p11 = new JPanel();
	private JPanel p12 = new JPanel();
	private JButton create=new JButton("Create");
	private JPanel p13=new JPanel();

	public WaiterGraphicalUserInterface(Restaurant restaurant) {
		this.restaurant = restaurant;
		t = MainWindow.createTable(restaurant.getNames(), restaurant.getPrices());
		orders = new JList(restaurant.getOrderIDs().toArray());

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
		p.add(bill);

		BorderLayout layout2 = new BorderLayout();
		p2.add(add);
		p3.add(finish);
		p4.add(scrollPane);
		order.setLayout(layout2);
		order.add(p6, BorderLayout.LINE_START);
		order.add(p4, BorderLayout.PAGE_START);
		order.add(p2, BorderLayout.CENTER);
		order.add(p3, BorderLayout.PAGE_END);

		GridLayout layout3 = new GridLayout(0, 1);
		billGenerator.setLayout(layout3);
		p12.add(bills);
		p11.add(orders);
		p13.add(create);
		billGenerator.add(p12);
		billGenerator.add(p11);
		billGenerator.add(p13);

		// scrollPane.setPreferredSize(new Dimension(250,200));
		// scrollPane.setAutoscrolls(true);
		this.add(p, BorderLayout.PAGE_START);
		// this.setSize(new Dimension(300,200));
		this.setVisible(true);
		this.addNewOrderListener();
		this.addViewAllListener();
		this.addBillListener();
		this.addAddListener();
		this.addFinishListener();
	}

	public void addNewOrderListener() {
		newOrder.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				scrollPane.removeAll();
				p4.remove(scrollPane);
				t = MainWindow.createTable(restaurant.getNames(), restaurant.getPrices());
				// addTableListener();
				scrollPane = new JScrollPane(t);
				scrollPane.setPreferredSize(new Dimension(250, 200));
				scrollPane.revalidate();
				p4.add(scrollPane);
				order.updateUI();
				add(order, BorderLayout.CENTER);
				order.setVisible(true);
				billGenerator.setVisible(false);
				updateUI();
				for (String s : restaurant.getNames()) {
					System.out.println("ssss " + s);
				}
			}
		});
	}

	public void addViewAllListener() {
		viewAll.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				order.setVisible(false);
				billGenerator.setVisible(false);
			}

		});
	}

	public void addBillListener() {
		bill.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				orders.setListData(restaurant.getOrderIDs().toArray());
				add(billGenerator, BorderLayout.CENTER);
				order.setVisible(false);
				billGenerator.setVisible(true);
				updateUI();
			}

		});
	}

	/*
	 * public void addTableListener() { t.addMouseListener(new MouseListener() {
	 * 
	 * public void mouseClicked(MouseEvent e) { int selectedRow =
	 * t.getSelectedRow(); //TableModel model = t.getModel();
	 * //menuName.setText((String) model.getValueAt(selectedRow, 0));
	 * //priceName.setText(model.getValueAt(selectedRow, 1) + ""); }
	 * 
	 * public void mousePressed(MouseEvent e) { }
	 * 
	 * public void mouseReleased(MouseEvent e) { }
	 * 
	 * public void mouseEntered(MouseEvent e) { }
	 * 
	 * public void mouseExited(MouseEvent e) { }
	 * 
	 * }); }
	 */

	public void addAddListener() {
		add.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int row = t.getSelectedRow();
				System.out.println("ROWNR IS: " + row);
				values.add(restaurant.getMenus().get(row));
				System.out.println("ONE: " + restaurant.getMenus().get(row).getName());
				System.out.println("TWO: " + restaurant.getMenus().get(row).getPrice());
			}
		});
	}

	public void addFinishListener() {
		finish.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Order newOrder = new Order();
				if (b1.isSelected())
					newOrder.setTable(Integer.parseInt(b1.getText()));
				if (b2.isSelected())
					newOrder.setTable(Integer.parseInt(b2.getText()));
				if (b3.isSelected())
					newOrder.setTable(Integer.parseInt(b3.getText()));
				if (b4.isSelected())
					newOrder.setTable(Integer.parseInt(b4.getText()));
				if (b5.isSelected())
					newOrder.setTable(Integer.parseInt(b5.getText()));

				System.out.println(newOrder.hashCode());
				createNewOrder(newOrder, values);
				ArrayList<MenuItem> m = restaurant.getOrders().get(newOrder.hashCode());
				if (m != null) {
					for (MenuItem f : m) {
						System.out.println(f.getName() + " " + f.getPrice());
					}
				}
				System.out.println("MAP: " + restaurant.getOrders().size());

				for (Order variableName : restaurant.getOrders().keySet()) {
					Order variableKey = variableName;
					ArrayList<MenuItem> variableValue = restaurant.getOrders().get(variableName);

					System.out.println("OrderID: " + variableKey.getOrderID());
					System.out.println("Date: " + variableKey.getDate());
					System.out.println("Table: " + variableKey.getTable());
					if (variableValue != null) {

						for (MenuItem df : variableValue)
							System.out.println("Number: " + df.getName() + "  " + df.getPrice());
					}
				}
				restaurant.createBill(newOrder);
				values.clear();
				JOptionPane.showMessageDialog(null, "Order succesfully added!", "ORDER",
						JOptionPane.INFORMATION_MESSAGE);
			}

		});
	}
	
	public void addCreateBillListener() {
		create.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				//int index=(Integer) orders.getSelectedValue();
				//String value=orders.getSelectedValue();
			//	value.substring(0, 3);
			}
			
		});
	}

	public void createNewMenu(MenuItem m) {
	}

	public void deleteMenuItem(MenuItem m) {
	}

	public void editMenuItem(int nrRow) {
	}

	public void createNewOrder(Order key, ArrayList<MenuItem> value) {
		restaurant.addNewOrder(key, value);
	}

	public void generateBill() {
		// TODO Auto-generated method stub

	}

	public void computePrice(Order o) {
		// TODO Auto-generated method stub

	}
}
