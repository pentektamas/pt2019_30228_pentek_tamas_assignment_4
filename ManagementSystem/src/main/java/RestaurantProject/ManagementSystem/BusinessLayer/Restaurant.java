package RestaurantProject.ManagementSystem.BusinessLayer;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Restaurant {

	private Map<Order, List<MenuItem>> orders;
	private List<MenuItem> menus;

	public Restaurant() {
		menus = new ArrayList<MenuItem>();
		orders = new HashMap<Order, List<MenuItem>>();
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

	public Map<Order, List<MenuItem>> getOrders() {
		return orders;
	}

	public void setOrders(Map<Order, List<MenuItem>> orders) {
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

	public void addNewOrder(Order key, List<MenuItem> value) {
		orders.put(key, value);

	}

	public List<String> getOrderIDs() {
		List<String> keys = new ArrayList<String>();
		for (Order key : this.orders.keySet()) {
			System.out.println("OrderID: " + key.getOrderID());
			keys.add(key.getOrderID() + "   " + "( Table " + key.getTable() + " )   ");
			Collections.sort(keys);
		}
		return keys;
	}

	public void createBill(Order order) {
		Writer writer = null;
		Integer totalAmount = 0;
		String text = ("\t\t\t\tBILL\n\n\t\t\tGordon Ramsay's Restaurant\t\t\t" + "\n\t\t\tLondon, Main Street 10\n\n");
		try {
			writer = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream("bill" + order.getOrderID() + ".txt"), "utf-8"));
			writer.write(text + "\n");
			// writer.write("Menu\t\tQuantity Price Total\n\n");
			writer.write("Menu\t\tQuantity     Price\n\n");
			List<MenuItem> variableValue = this.orders.get(order);
			System.out.println("SIZE SI IS: " + variableValue.size());
			for (MenuItem mm : variableValue) {
				System.out.println("STOP: " + mm.getName() + mm.getPrice());
			}

			System.out.println("2OrderID: " + order.getOrderID());
			System.out.println("2Date: " + order.getDate());
			System.out.println("2Table: " + order.getTable());
			if (variableValue != null) {

				for (MenuItem df : variableValue) {
					System.out.println("HERE");
					writer.write(df.getName());
					if (df.getName().length() > 7)
						writer.write("\t   ");
					else
						writer.write("\t\t   ");
					writer.write("1  \t\t");
					// writer.write(df.getPrice() + "\t ");
					writer.write(df.getPrice() + "\n");
					totalAmount += df.computePrice();
				}
			}
			/*
			 * for (Order variableKey : this.orders.keySet()) { if (variableKey.getOrderID()
			 * == order.getOrderID()) { ArrayList<MenuItem> variableValue =
			 * this.orders.get(variableKey); System.out.println("SIZE SI IS: " +
			 * variableValue.size()); for (MenuItem mm : variableValue) {
			 * System.out.println("STOP: " + mm.getName() + mm.getPrice()); }
			 * 
			 * System.out.println("2OrderID: " + variableKey.getOrderID());
			 * System.out.println("2Date: " + variableKey.getDate());
			 * System.out.println("2Table: " + variableKey.getTable()); if (variableValue !=
			 * null) {
			 * 
			 * for (MenuItem df : variableValue) { System.out.println("HERE");
			 * writer.write(df.getName()); if (df.getName().length() > 7)
			 * writer.write("\t   "); else writer.write("\t\t   "); writer.write("1  \t\t");
			 * writer.write(df.getPrice() + "\t  "); writer.write(df.getPrice() + "\n");
			 * totalAmount += df.computePrice(); } } } }
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
