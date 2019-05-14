package RestaurantProject.ManagementSystem.PresentationLayer;

import java.awt.*;
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
import RestaurantProject.ManagementSystem.BusinessLayer.RestaurantProcessing;

public class AdministratorGraphicalUserInterface extends JPanel {

	private JLabel select = new JLabel("Select an operation: ");
	private JButton newMenu = new JButton("Add new menu");
	private JButton editMenu = new JButton("Edit menu");
	private JButton deleteMenu = new JButton("Delete menu");
	private JButton viewAll = new JButton("View all menus");
	private RestaurantProcessing restaurant;

	private JLabel components = new JLabel("The number of the components of the menu: ");
	private JPanel add = new JPanel();
	private JButton addButton = new JButton("Add");
	private List<JLabel> labels = new ArrayList<JLabel>();
	private List<JTextField> texts = new ArrayList<JTextField>();
	private List<JLabel> labels2 = new ArrayList<JLabel>();
	private List<JTextField> texts2 = new ArrayList<JTextField>();
	private JTextField text = new JTextField();
	private JButton base = new JButton("Base Product");
	private JButton composite = new JButton("Composite Product");

	private JLabel selectitem = new JLabel("Select an item: ");
	private JList menus;
	private JTable t;
	private JTable t2;
	private JButton ok = new JButton("OK");
	private JPanel delete = new JPanel();

	private JLabel selectitemEdit = new JLabel("Select an item: ");
	private JPanel edit = new JPanel();
	private JScrollPane scrollPane = new JScrollPane(t);
	private JScrollPane scrollPane2 = new JScrollPane(t2);
	private JLabel menu = new JLabel("Menu: ");
	private JLabel price = new JLabel("Price: ");
	private JTextField menuName = new JTextField();
	private JTextField priceName = new JTextField();
	private JButton update = new JButton("Update");

	private JPanel view = new JPanel();
	private JPanel p1 = new JPanel();
	private JTable table33 = new JTable();
	private JScrollPane scroll33 = new JScrollPane();
	private JPanel p11 = new JPanel();

	private JLabel l = new JLabel("Component : ");
	private JTextField t1 = new JTextField();
	private JLabel l2 = new JLabel("Price : ");
	private JTextField t3 = new JTextField();

	public AdministratorGraphicalUserInterface(RestaurantProcessing restaurantprocess) {
		this.restaurant = restaurantprocess;
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

		BorderLayout layout = new BorderLayout();
		GridLayout layout2 = new GridLayout(0, 1);
		menuName.setPreferredSize(new Dimension(50, 25));
		priceName.setPreferredSize(new Dimension(50, 25));
		JPanel p10 = new JPanel();
		p10.setLayout(layout2);
		p10.add(menu);
		p10.add(menuName);
		p10.add(price);
		p10.add(priceName);
		p10.add(update);
		p1.add(base);
		p1.add(composite);
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
		this.addADDButtonListener();
		this.addUpdateListener();
		this.addBaseButtonListener();
		this.addCompositeButtonListener();
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
				scrollPane.setPreferredSize(new Dimension(350, 250));
				edit.add(scrollPane);
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
				scrollPane2.setPreferredSize(new Dimension(350, 250));
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

	public void addBaseButtonListener() {
		base.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				p11.removeAll();
				JPanel p4 = new JPanel();
				l = new JLabel("Component : ");
				t1 = new JTextField();
				l2 = new JLabel("Price : ");
				t3 = new JTextField();
				t1.setPreferredSize(new Dimension(150, 25));
				t3.setPreferredSize(new Dimension(50, 25));
				texts.add(t1);
				texts2.add(t3);
				p11.removeAll();
				p11.add(l);
				p11.add(t1);
				p11.add(l2);
				p11.add(t3);
				p11.setVisible(true);
				scroll33.setVisible(false);
				add.removeAll();
				add.add(p1, BorderLayout.PAGE_START);
				add.add(p11, BorderLayout.CENTER);
				p4.add(addButton);
				add.add(p4, BorderLayout.PAGE_END);
				add.updateUI();
				updateUI();
			}
		});
	}

	public void addCompositeButtonListener() {
		composite.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				p11.setVisible(false);
				JPanel p4 = new JPanel();
				JLabel lab = new JLabel("Menu's name: ");
				text = new JTextField();
				text.setPreferredSize(new Dimension(150, 25));
				JPanel temp = new JPanel();
				temp.add(lab);
				temp.add(text);
				p4.add(temp, BorderLayout.PAGE_START);
				scroll33.removeAll();
				List<String> names = new ArrayList<String>();
				List<Integer> prices = new ArrayList<Integer>();
				names.add(" ");
				names.add(" ");
				names.add(" ");
				names.add(" ");
				names.add(" ");
				names.add(" ");
				prices.add(0);
				prices.add(0);
				prices.add(0);
				prices.add(0);
				prices.add(0);
				prices.add(0);
				p4.add(addButton);
				table33 = MainWindow.createTable(names, prices);
				scroll33 = new JScrollPane(table33);
				scroll33.setPreferredSize(new Dimension(200, 200));
				JPanel table = new JPanel();
				table.add(scroll33);
				add.removeAll();
				add.add(p1, BorderLayout.PAGE_START);
				add.add(table, BorderLayout.CENTER);
				add.add(p4, BorderLayout.PAGE_END);
				add.updateUI();
				updateUI();
			}
		});
	}

	public void addADDButtonListener() {
		addButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (texts.size() == 1) {
					if (texts.get(0).getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Component's name is INVALID!", "ERROR New Menu!",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					if (texts2.get(0).getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Component's price is INVALID!", "ERROR New Menu!",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					MenuItem m = new BaseProduct(texts.get(0).getText(), Integer.parseInt(texts2.get(0).getText()));

					for (MenuItem menu : restaurant.getMenus()) {
						if (menu.getName().equals(m.getName()) && menu.getPrice() == m.getPrice()) {
							JOptionPane.showMessageDialog(null, "Duplicate Menu Item!", "ERROR New Menu!",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
					JOptionPane.showMessageDialog(null, "New base product added successfully!", "New Menu!",
							JOptionPane.INFORMATION_MESSAGE);
					createNewMenu(m);
					texts.clear();
					texts2.clear();
					add.updateUI();
				} else {
					if (text.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Menu's name is INVALID!", "ERROR New Menu!",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					MenuItem m1 = new CompositeProduct(text.getText());
					TableModel model = table33.getModel();
					List<String> names = new ArrayList<String>();
					List<Integer> prices = new ArrayList<Integer>();
					for (int i = 0; i < 6; i++) {
						if (model.getValueAt(0, 0).equals(" ") || model.getValueAt(0, 1) == Integer.valueOf(0)) {
							JOptionPane.showMessageDialog(null, "Menu's components are INVALID!", "ERROR New Menu!",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						if (model.getValueAt(i, 0).equals(" ") || model.getValueAt(i, 1) == Integer.valueOf(0))
							break;
						names.add((String) model.getValueAt(i, 0));
						prices.add(Integer.parseInt((String) model.getValueAt(i, 1)));
					}
					for (int i = 0; i < names.size(); i++) {
						m1.add(new BaseProduct(names.get(i), prices.get(i)));
					}

					for (MenuItem menu : restaurant.getMenus()) {
						if (menu.getName().equals(m1.getName()) && menu.getPrice() == m1.getPrice()) {
							JOptionPane.showMessageDialog(null, "Duplicate Menu Item!", "ERROR New Menu!",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
					}

					createNewMenu(m1);
					JOptionPane.showMessageDialog(null, "New menu added successfully!", "New Menu!",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}

	public void addTableListener() {
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
				if (menuName.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Menu's name is INVALID!", "Edit Menu!",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (priceName.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Menu's price is INVALID!", "Edit Menu!",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				model.setValueAt(menuName.getText(), selectedRow, 0);
				model.setValueAt(Integer.parseInt(priceName.getText()), selectedRow, 1);
				editMenuItem(selectedRow);
			}
		});
	}

	public void createNewMenu(MenuItem m) {
		restaurant.createNewMenu(m);
	}

	public void deleteMenuItem(MenuItem m) {
		restaurant.deleteMenuItem(m);
	}

	public void editMenuItem(int nrRow) {
		restaurant.editMenuItem(nrRow, menuName.getText(), Integer.parseInt(priceName.getText()));
	}
}
