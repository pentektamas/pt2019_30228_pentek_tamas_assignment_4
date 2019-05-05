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

import RestaurantProject.ManagementSystem.BusinessLayer.BaseProduct;
import RestaurantProject.ManagementSystem.BusinessLayer.CompositeProduct;
import RestaurantProject.ManagementSystem.BusinessLayer.MenuItem;
import RestaurantProject.ManagementSystem.BusinessLayer.Order;
import RestaurantProject.ManagementSystem.BusinessLayer.Restaurant;
import RestaurantProject.ManagementSystem.BusinessLayer.RestaurantProcessing;

public class AdministratorGraphicalUserInterface extends JPanel implements RestaurantProcessing {

	private JLabel select = new JLabel("Select an operation: ");
	private JButton newMenu = new JButton("Add new menu");
	private JButton editMenu = new JButton("Edit menu");
	private JButton deleteMenu = new JButton("Delete menu");
	private JButton viewAll = new JButton("View all menus");
	private Restaurant restaurant;// = new Restaurant();

	private JLabel components = new JLabel("The number of the components of the menu: ");
	private JTextField nrComponents = new JTextField();
	private JButton ok2 = new JButton("OK");
	private JPanel add = new JPanel();
	private JButton addButton = new JButton("Add");
	private List<JLabel> labels = new ArrayList<JLabel>();
	private List<JTextField> texts = new ArrayList<JTextField>();
	private List<JLabel> labels2 = new ArrayList<JLabel>();
	private List<JTextField> texts2 = new ArrayList<JTextField>();
	JTextField text = new JTextField();

	private JLabel selectitem = new JLabel("Select an item: ");
	private JList menus;// = new JList(restaurant.getNames().toArray());
	private JTable t;// = MainWindow.createTable(restaurant.getNames(), restaurant.getPrices());
	private JTable t2;// = MainWindow.createTable(restaurant.getNames(), restaurant.getPrices());
	private JButton ok = new JButton("OK");
	private JPanel delete = new JPanel();

	private JLabel selectitemEdit = new JLabel("Select an item: ");
	private JPanel edit = new JPanel();
	// private Object[][] objects;
	private JScrollPane scrollPane = new JScrollPane(t);
	private JScrollPane scrollPane2 = new JScrollPane(t2);
	private JLabel menu = new JLabel("Menu: ");
	private JLabel price = new JLabel("Price: ");
	private JTextField menuName = new JTextField();
	private JTextField priceName = new JTextField();
	private JButton update = new JButton("Update");

	private JPanel view = new JPanel();

	public AdministratorGraphicalUserInterface(Restaurant restaurant) {
		this.restaurant = restaurant;
		menus = new JList(restaurant.getNames().toArray());
		t = MainWindow.createTable(restaurant.getNames(), restaurant.getPrices());
		t2 = MainWindow.createTable(restaurant.getNames(), restaurant.getPrices());
		JPanel p = new JPanel();
		this.setLayout(new BorderLayout());
		p.add(select);
		p.add(newMenu);
		p.add(editMenu);
		p.add(deleteMenu);
		p.add(viewAll);

		JPanel p1 = new JPanel();
		BorderLayout layout = new BorderLayout();
		GridLayout layout2 = new GridLayout(0, 1);
		nrComponents.setPreferredSize(new Dimension(50, 25));
		menuName.setPreferredSize(new Dimension(50, 25));
		priceName.setPreferredSize(new Dimension(50, 25));
		JPanel p10 = new JPanel();
		p10.setLayout(layout2);
		p10.add(menu);
		p10.add(menuName);
		p10.add(price);
		p10.add(priceName);
		p10.add(update);
		p1.add(components);
		p1.add(nrComponents);
		p1.add(ok2);
		add.setLayout(layout);
		add.add(p1, BorderLayout.PAGE_START);

		delete.add(selectitem);
		delete.add(menus);
		delete.add(ok);

		edit.add(p10);

		this.add(p, BorderLayout.PAGE_START);
		this.setVisible(true);
		this.addNewMenuListener();
		this.addEditMenuListener();
		this.addDeleteMenuListener();
		this.addViewAllListener();
		this.addOKListener();
		this.addOK2Listener();
		this.addADDButtonListener();
		this.addUpdateListener();
	}

	public void addNewMenuListener() {
		newMenu.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				add(add, BorderLayout.CENTER);
				delete.setVisible(false);
				add.setVisible(true);
				edit.setVisible(false);
				view.setVisible(false);
				updateUI();
			}
		});
	}

	public void addEditMenuListener() {
		editMenu.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				edit.remove(scrollPane);
				scrollPane.removeAll();
				t = MainWindow.createTable(restaurant.getNames(), restaurant.getPrices());
				addTableListener();
				scrollPane = new JScrollPane(t);
				scrollPane.setPreferredSize(new Dimension(275, 250));
				edit.add(scrollPane);
				// view.add(scrollPane);
				edit.updateUI();
				add(edit, BorderLayout.CENTER);
				delete.setVisible(false);
				add.setVisible(false);
				edit.setVisible(true);
				view.setVisible(false);
				updateUI();
			}
		});
	}

	public void addDeleteMenuListener() {
		deleteMenu.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				menus.setListData(restaurant.getNames().toArray());
				add(delete, BorderLayout.CENTER);
				delete.setVisible(true);
				add.setVisible(false);
				edit.setVisible(false);
				view.setVisible(false);
				updateUI();

				for (String s : restaurant.getNames()) {
					System.out.println("NAME: " + s);
				}
				for (Integer m : restaurant.getPrices()) {
					System.out.println("PRICE: " + m);
				}
			}
		});
	}

	public void addViewAllListener() {
		viewAll.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				delete.setVisible(false);
				add.setVisible(false);
				edit.setVisible(false);
				view.setVisible(true);
				view.remove(scrollPane2);
				scrollPane2.removeAll();
				t2 = MainWindow.createTable(restaurant.getNames(), restaurant.getPrices());
				scrollPane2 = new JScrollPane(t2);
				scrollPane2.setPreferredSize(new Dimension(275, 250));
				view.add(scrollPane2);
				view.updateUI();
				add(view, BorderLayout.CENTER);
				updateUI();

			}
		});
	}

	public void addOKListener() {
		ok.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (menus.getLastVisibleIndex() == -1)
					JOptionPane.showMessageDialog(null, "No more menus!", "ERROR!", JOptionPane.ERROR_MESSAGE);
				String x = (String) menus.getSelectedValue();
				for (MenuItem m : restaurant.getMenus()) {
					if (m.getName().equals(x)) {
						deleteMenuItem(m);
						break;
					}
				}
				menus.setListData(restaurant.getNames().toArray());
			}
		});
	}

	public void addOK2Listener() {
		ok2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JPanel p3 = new JPanel();
				JPanel p4 = new JPanel();
				GridLayout layout2 = new GridLayout(0, 1);
				p3.setLayout(layout2);
				int nr = Integer.parseInt(nrComponents.getText());
				if (nr > 1) {
					JLabel lab = new JLabel("Menu's name: ");
					text.setPreferredSize(new Dimension(150, 25));
					JPanel temp = new JPanel();
					temp.add(lab);
					temp.add(text);
					p4.add(temp, BorderLayout.PAGE_START);
				}
				for (int i = 0; i < nr; i++) {
					JLabel l = new JLabel("Component ( " + (i + 1) + " ) : ");
					JTextField t = new JTextField();
					JLabel l2 = new JLabel("Price ( " + (i + 1) + " ) : ");
					JTextField t2 = new JTextField();
					t.setPreferredSize(new Dimension(150, 25));
					t2.setPreferredSize(new Dimension(50, 25));
					labels.add(l);
					texts.add(t);
					labels2.add(l2);
					texts2.add(t2);
					JPanel p2 = new JPanel();
					p2.add(labels.get(i));
					p2.add(texts.get(i));
					p2.add(labels2.get(i));
					p2.add(texts2.get(i));
					p3.add(p2);
				}
				add.add(p3, BorderLayout.CENTER);
				p4.add(addButton);
				add.add(p4, BorderLayout.PAGE_END);
				updateUI();
				add.updateUI();
			}
		});
	}

	public void addADDButtonListener() {
		addButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (texts.size() == 1) {
					MenuItem m = new BaseProduct(texts.get(0).getText(), Integer.parseInt(texts2.get(0).getText()));
					createNewMenu(m);

					System.out.println(m.getName() + " " + m.getPrice());
				} else {
					MenuItem m1 = new CompositeProduct(text.getText());
					for (int i = 0; i < texts.size(); i++) {
						m1.add(new BaseProduct(texts.get(i).getText(), Integer.parseInt(texts2.get(i).getText())));
					}
					createNewMenu(m1);
					System.out.println(m1.getName() + "  " + m1.getPrice());
				}
			}
		});
	}

	public void addTableListener() {
		System.out.println("hERE I AM");
		t.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				int selectedRow = t.getSelectedRow();
				TableModel model = t.getModel();
				menuName.setText((String) model.getValueAt(selectedRow, 0));
				priceName.setText(model.getValueAt(selectedRow, 1) + "");
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}
		});
	}

	public void addUpdateListener() {
		update.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int selectedRow = t.getSelectedRow();
				TableModel model = t.getModel();
				model.setValueAt(menuName.getText(), selectedRow, 0);
				model.setValueAt(Integer.parseInt(priceName.getText()), selectedRow, 1);
				editMenuItem(selectedRow);
				// restaurant.getMenus().get(selectedRow).setName(menuName.getText());
				// restaurant.getMenus().get(selectedRow).setPrice(Integer.parseInt(priceName.getText()));
				/*
				 * for (String s : restaurant.getNames()) { System.out.println("NAME: " + s); }
				 * for (Integer m : restaurant.getPrices()) { System.out.println("PRICE: " + m);
				 * }
				 */
			}
		});
	}

	/*
	 * public JTable createTable(List<String> names, List<Integer> prices) {
	 * Object[][] objects; Object[] columnNames = { "Menus", "Prices" }; objects =
	 * new Object[names.size()][2]; for (int i = 0; i < names.size(); i++) {
	 * objects[i][0] = names.get(i); objects[i][1] = prices.get(i); } JTable table =
	 * new JTable(objects, columnNames); return table; }
	 */

	public void createNewMenu(MenuItem m) {
		restaurant.addNewMenu(m);
		for (MenuItem men : restaurant.getMenus()) {
			System.out.println(men.getName());
		}
	}

	public void deleteMenuItem(MenuItem m) {
		restaurant.deleteMenu(m);
		for (MenuItem men : restaurant.getMenus()) {
			System.out.println(men.getName());
		}
	}

	public void editMenuItem(int nrRow) {
		restaurant.getMenus().get(nrRow).setName(menuName.getText());
		restaurant.getMenus().get(nrRow).setPrice(Integer.parseInt(priceName.getText()));
		for (MenuItem men : restaurant.getMenus()) {
			System.out.println("NAMES: " + men.getName());
		}
		for (MenuItem men : restaurant.getMenus()) {
			System.out.println("PRICE: " + men.getPrice());
		}
	}

	public void createNewOrder(Order key, ArrayList<MenuItem> value) {
	}

	public void generateBill() {
	}

	public void computePrice(Order o) {
	}
}
