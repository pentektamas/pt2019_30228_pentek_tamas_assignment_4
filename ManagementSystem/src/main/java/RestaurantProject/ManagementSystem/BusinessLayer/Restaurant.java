package RestaurantProject.ManagementSystem.BusinessLayer;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

import RestaurantProject.ManagementSystem.DataLayer.FileWriter;

/**
 * @invariant isWellFormed()
 */
@SuppressWarnings("deprecation")
public class Restaurant extends Observable implements RestaurantProcessing {

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

	/**
	 * Metoda returneza lungimea listei cu meniuri
	 * 
	 * @return lungimea listei
	 * @pre menus!=null
	 * @post @nochange
	 */
	public int getSizeMenu() {
		assert menus != null;
		assert isWellFormed();
		return this.menus.size();
	}

	/**
	 * Metoda returneza lungimea listei cu comenzi
	 * 
	 * @return lungimea listei
	 * @pre orders!=null
	 * @post @nochange
	 */
	public int getSizeOrder() {
		assert orders != null;
		assert isWellFormed();
		return this.orders.size();
	}

	/**
	 * Metoda returneza true daca m este in lista si false daca nu
	 * 
	 * @param m este un meniu
	 * @return true daca lista contine m, false altfel
	 * @pre true
	 * @post @nochange
	 */
	public boolean contains(MenuItem m) {
		assert true;
		assert isWellFormed();
		return menus.contains(m);
	}

	/**
	 * Metoda returneza true daca perechea(key,value) este in lista si false daca nu
	 * 
	 * @param key   este cheia
	 * @param value este valoarea care va forma perechea(cheie,valoeare)
	 * @return true daca lista contine perechea(key,value), false altfel
	 * @pre true
	 * @post @nochange
	 */
	public boolean contains(Order key, List<MenuItem> value) {
		assert true;
		assert isWellFormed();
		return orders.containsValue(value) && orders.containsKey(key);
	}

	/**
	 * Metoda returneaza comenzile restaurantului
	 * 
	 * @return comenzile restaurantului
	 * @pre orders!=null
	 * @post @nochange
	 */
	public Map<Order, List<MenuItem>> getOrders() {
		assert orders != null;
		assert isWellFormed();
		return orders;
	}

	/**
	 * Metoda returneaza meniul restaurantului
	 * 
	 * @return meniul restaurantului
	 * @pre menus!=null
	 * @post @nochange
	 */
	public List<MenuItem> getMenus() {
		assert menus != null;
		assert isWellFormed();
		return menus;
	}

	/**
	 * Metoda seteaza menus ca si meniul restaurantului
	 * 
	 * @param menus este meniul care va fi meniul restaurantului
	 * @pre menus!=null
	 * @post getSizeMenu()>0
	 */
	public void setMenus(List<MenuItem> menus) {
		assert menus != null;
		assert isWellFormed();
		this.menus = menus;
		assert getSizeMenu() > 0;
		assert isWellFormed();
	}

	/**
	 * Metoda salveaza intr-o lista numele fiecarui meniu
	 * 
	 * @return lista cu numele fiecarui meniu
	 * @pre names.size()==0
	 * @post names.size()>0 && names!=null
	 */
	public List<String> getNames() {
		List<String> names = new ArrayList<String>();
		assert names.size() == 0;
		assert isWellFormed();
		for (MenuItem m : menus) {
			names.add(m.getName());
		}
		assert names.size() > 0 && names != null;
		assert isWellFormed();
		return names;
	}

	/**
	 * Metoda salveaza intr-o lista numele fiecarui meniu
	 * 
	 * @return lista cu numele fiecarui meniu
	 * @pre prices.size()==0
	 * @post getSize()>0 && prices!=null
	 */
	public List<Integer> getPrices() {
		List<Integer> prices = new ArrayList<Integer>();
		assert prices.size() == 0;
		assert isWellFormed();
		for (MenuItem m : menus) {
			prices.add(m.getPrice());
		}
		assert prices.size() > 0 && prices != null;
		assert isWellFormed();
		return prices;
	}

	/**
	 * Metoda returneaza ID de comanda pentru fiecare comanda facuta
	 * 
	 * @return o lista cu ID de comenzi
	 * @pre keys.size()==0
	 * @post keys.size() < 0 && keys != null
	 */
	public List<String> getOrderIDs() {
		List<String> keys = new ArrayList<String>();
		assert keys.size() == 0;
		assert isWellFormed();
		for (Order key : this.orders.keySet()) {
			System.out.println("OrderID: " + key.getOrderID());
			keys.add(key.getOrderID() + "   " + "( Table " + key.getTable() + " )   ");
			Collections.sort(keys);
		}
		assert keys.size() < 0 && keys != null;
		assert isWellFormed();
		return keys;
	}

	/**
	 * Adauga un nou meniu in lista de meniu a restaurantului
	 * 
	 * @param m este noul meniu
	 * @pre m!=null
	 * @post getSizeMenu() == getSizeMenu()@pre+1
	 */
	public void createNewMenu(MenuItem m) {
		assert m != null;
		assert isWellFormed();
		int sizePre = this.getSizeMenu();
		menus.add(m);
		int sizePost = this.getSizeMenu();
		assert sizePost == sizePre + 1;
		assert isWellFormed();
	}

	/**
	 * Sterge un meniu din lista de meniu a restaurantului
	 * 
	 * @param m este meniul pe care vrem sa-l stergem
	 * @pre getSize()>0 && contains(m)==true
	 * @post getSize()== getSize()@pre-1
	 * @post contains(m)==false
	 */
	public void deleteMenuItem(MenuItem m) {
		assert this.getSizeMenu() > 0 && this.contains(m) == true;
		assert isWellFormed();
		int sizePre = this.getSizeMenu();
		menus.remove(m);
		int sizePost = this.getSizeMenu();
		assert sizePost == sizePre - 1;
		assert this.contains(m) == false;
		assert isWellFormed();
	}

	/**
	 * Actualizeaza numele si pretul unui meniu
	 * 
	 * @param nrRow este indexul meniului pe care vrem sa-l actualizam
	 * @param name  este numele meniului
	 * @param price este pretul meniului
	 * @pre nrRow>=0 && name!=null && price>=1
	 * @post (getMenus().get(nrRow).getName().equals(name) &&
	 *       (getMenus().get(nrRow).getPrice()==price)
	 */
	public void editMenuItem(int nrRow, String name, int price) {
		assert nrRow >= 0 && name != null && price >= 1;
		assert isWellFormed();
		this.getMenus().get(nrRow).setName(name);
		this.getMenus().get(nrRow).setPrice(price);
		assert (getMenus().get(nrRow).getName().equals(name)) && (getMenus().get(nrRow).getPrice() == price);
		assert isWellFormed();
	}

	/**
	 * Adauga o comanda noua
	 * 
	 * @param key   este pozitia la care se adauga comanda in HashMap
	 * @param value este continutul comenzii
	 * 
	 * @pre key!=null && value!=null && this.getSizeOrder()>=0
	 * @post getSizeOrder()==getSizeOrder()@pre+1
	 * @post contains(key, value)==true
	 */
	public void createNewOrder(Order key, List<MenuItem> value) {
		assert key != null && value != null && this.getSizeOrder() >= 0;
		assert isWellFormed();
		int sizePre = this.getSizeOrder();
		orders.put(key, value);
		for (MenuItem m : value) {
			if (m instanceof CompositeProduct) {
				this.setChanged();
				this.notifyObservers(new CompositeProduct(m.getName()));
			}
			System.out.println("ORDER ADDED");
		}
		int sizePost = this.getSizeOrder();
		assert sizePost == sizePre + 1;
		assert this.contains(key, value) == true;
		assert isWellFormed();
	}

	/**
	 * Genereaza o factura in format .txt dupa o comanda
	 * 
	 * @param order este comanda pe baza careia se genereaza factura
	 * @pre order!=null
	 * @post totalAmount>0
	 * @post writer!=null
	 */
	public void generateBill(Order order) {
		assert order != null;
		assert isWellFormed();
		Writer writer = null;
		FileWriter fileWriter = new FileWriter();
		Integer totalAmount = 0;
		String text = ("\t\t\t\tBILL\n\n\t\t\tGordon Ramsay's Restaurant\t\t\t" + "\n\t\t\tLondon, Main Street 10\n\n");
		try {
			writer = fileWriter.createFile("bill" + order.getOrderID() + ".txt");
			writer.write(text + "\n");
			writer.write("Menu\t\tQuantity     Price\n\n");
			List<MenuItem> variableValue = this.orders.get(order);
			if (variableValue != null) {

				for (MenuItem df : variableValue) {
					writer.write(df.getName());
					if (df.getName().length() > 7)
						writer.write("\t   ");
					else
						writer.write("\t\t   ");
					writer.write("1  \t\t");
					writer.write(df.getPrice() + "\n");
					totalAmount += df.getPrice();
				}
			}
			writer.write("\n\n");
			writer.write("\t\t\t\t");
			writer.write("Number of table: " + order.getTable());
			writer.write("\n\n");
			writer.write("\t\t\t\t");
			writer.write("Total amount: " + totalAmount + " RON");
		} catch (IOException ex) {
			System.out.println("IO EXCEPTION - OPEN!");
		} finally {
			fileWriter.closeFile(writer);
		}
		assert totalAmount > 0;
		assert writer != null;
		assert isWellFormed();
	}

	protected boolean isWellFormed() {
		if (this.orders.size() < 0)
			return false;
		if (this.menus.size() < 0)
			return false;
		for (MenuItem menu : this.menus) {
			if (menu == null)
				return false;
		}
		Set<Order> keys = this.orders.keySet();
		for (Order order : keys) {
			if (order == null)
				return false;
		}
		Collection<List<MenuItem>> menus = this.orders.values();
		for (List<MenuItem> m : menus) {
			if (m == null)
				return false;
		}
		return true;
	}
}
