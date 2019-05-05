package RestaurantProject.ManagementSystem.BusinessLayer;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class Restaurant {

	private Map<Order, ArrayList<MenuItem>> orders;
	private List<MenuItem> menus;

	public Restaurant() {
		menus = new ArrayList<MenuItem>();
		orders = new HashMap<Order, ArrayList<MenuItem>>();
		MenuItem m = new BaseProduct("Cotlet", 4);
		MenuItem m2 = new BaseProduct("Cartofi", 3);
		MenuItem m3 = new BaseProduct("Salata", 2);
		MenuItem m4 = new BaseProduct("Tiramisu", 5);
		menus.add(m);
		menus.add(m2);
		menus.add(m3);
		menus.add(m4);
	}

	public void addNewMenu(MenuItem menu) {
		menus.add(menu);
	}

	public void deleteMenu(MenuItem menu) {
		menus.remove(menu);
	}

	public Map<Order, ArrayList<MenuItem>> getOrders() {
		return orders;
	}

	public void setOrders(Map<Order, ArrayList<MenuItem>> orders) {
		this.orders = orders;
	}

	public List<MenuItem> getMenus() {
		return menus;
	}

	public void setMenus(List<MenuItem> menus) {
		this.menus = menus;
	}

	public List<String> getNames() {
		List<String> names = new ArrayList<String>();
		for (MenuItem m : menus) {
			names.add(m.getName());
		}
		return names;
	}

	public List<Integer> getPrices() {
		List<Integer> prices = new ArrayList<Integer>();
		for (MenuItem m : menus) {
			prices.add(m.getPrice());
		}
		return prices;
	}

	public void addNewOrder(Order key, ArrayList<MenuItem> value) {
		orders.put(key, value);
	}

	public List<String> getOrderIDs() {
		List<String> keys = new ArrayList<String>();
		for (Order key : this.orders.keySet()) {
			System.out.println("OrderID: " + key.getOrderID());
			keys.add(key.getOrderID() + "   " + "( Table " + key.getTable() + " )   ");
		}
		return keys;
	}

	public void createBill(Order order) {
		Writer writer = null;
		Integer totalAmount = 0;
		String text = ("\t\t\tBILL\n\n\t\t\tGordon Ramsay's Restaurant\t\t\t" + "\nLondon, Main Street 10\n\n");
		try {
			writer = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream("bill" + order.getOrderID() + ".txt"), "utf-8"));
			writer.write(text + "\n");
			writer.write("Menu\t\tQuantity     Price     Total\n\n");
			for (Order variableKey : this.orders.keySet()) {
				//if(variableKey.getOrderID()==order.getOrderID())
				ArrayList<MenuItem> variableValue = this.orders.get(variableKey);

				System.out.println("2OrderID: " + variableKey.getOrderID());
				System.out.println("2Date: " + variableKey.getDate());
				System.out.println("2Table: " + variableKey.getTable());
				if (variableValue != null) {

					for (MenuItem df : variableValue) { // System.out.println("Number: " + df.getName() + " " +
														// df.getPrice());
						System.out.println("HERE");
						writer.write(df.getName());
						if (df.getName().length() > 7)
							writer.write("\t   ");
						else
							writer.write("\t\t   ");
						writer.write("1  \t\t");
						writer.write(df.getPrice() + "\t  ");
						writer.write(df.getPrice() + "\n");
						totalAmount += df.computePrice();
					}
				}
			}
			/*
			 * if (names.get(i).getSelectedItem().toString().length() < 7)
			 * writer.write("\t\t\t   "); else { if
			 * (names.get(i).getSelectedItem().toString().length() > 15)
			 * writer.write("\t   "); else writer.write("\t\t   ");
			 */
			/*
			 * writer.write(textfields.get(i).getText()); writer.write("\t\t ");
			 * writer.write(prices.get(i) + ""); writer.write("\t ");
			 * writer.write(Integer.parseInt(textfields.get(i).getText()) * prices.get(i) +
			 * ""); totalAmount = totalAmount +
			 * Integer.parseInt(textfields.get(i).getText()) * prices.get(i);
			 * writer.write("\n\n");
			 */

			writer.write("\n\n");
			writer.write("\t\t\t\t");
			writer.write("Number of table: " + order.getTable());
			writer.write("\n\n");
			writer.write("\t\t\t\t");
			writer.write("Total amount: " + totalAmount + " RON");
		} catch (IOException ex) {
			System.out.println("IO EXCEPTION - OPEN!");
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				System.out.println("IO EXCEPTION - CLOSE!");
			}
		}
	}

}
