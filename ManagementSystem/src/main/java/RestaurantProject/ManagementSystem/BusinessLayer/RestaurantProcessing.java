package RestaurantProject.ManagementSystem.BusinessLayer;

import java.util.List;
import java.util.Map;

public interface RestaurantProcessing {

	/**
	 * Metoda returneza lungimea listei cu meniuri
	 * 
	 * @return lungimea listei
	 * @pre menus!=null
	 * @post @nochange
	 */
	public int getSizeMenu();

	/**
	 * Metoda returneza lungimea listei cu comenzi
	 * 
	 * @return lungimea listei
	 * @pre orders!=null
	 * @post @nochange
	 */
	public int getSizeOrder();

	/**
	 * Metoda returneza true daca m este in lista si false daca nu
	 * 
	 * @param m este un meniu
	 * @return true daca lista contine m, false altfel
	 * @pre true
	 * @post @nochange
	 */
	public boolean contains(MenuItem m);

	/**
	 * Metoda returneza true daca perechea(key,value) este in lista si false daca nu
	 * 
	 * @param key   este cheia
	 * @param value este valoarea care va forma perechea(cheie,valoeare)
	 * @return true daca lista contine perechea(key,value), false altfel
	 * @pre true
	 * @post @nochange
	 */
	public boolean contains(Order key, List<MenuItem> value);

	/**
	 * Metoda returneaza comenzile restaurantului
	 * 
	 * @return comenzile restaurantului
	 * @pre orders!=null
	 * @post @nochange
	 */
	public Map<Order, List<MenuItem>> getOrders();

	/**
	 * Metoda returneaza meniul restaurantului
	 * 
	 * @return meniul restaurantului
	 * @pre menus!=null
	 * @post @nochange
	 */
	public List<MenuItem> getMenus();

	/**
	 * Metoda seteaza menus ca si meniul restaurantului
	 * 
	 * @param menus este meniul care va fi meniul restaurantului
	 * @pre menus!=null
	 * @post getSizeMenu()>0
	 */
	public void setMenus(List<MenuItem> menus);

	/**
	 * Metoda salveaza intr-o lista numele fiecarui meniu
	 * 
	 * @return lista cu numele fiecarui meniu
	 * @pre names.size()==0
	 * @post names.size()>0 && names!=null
	 */
	public List<String> getNames();

	/**
	 * Metoda salveaza intr-o lista numele fiecarui meniu
	 * 
	 * @return lista cu numele fiecarui meniu
	 * @pre prices.size()==0
	 * @post getSize()>0 && prices!=null
	 */
	public List<Integer> getPrices();

	/**
	 * Metoda returneaza ID de comanda pentru fiecare comanda facuta
	 * 
	 * @return o lista cu ID de comenzi
	 * @pre keys.size()==0
	 * @post keys.size() < 0 && keys != null
	 */
	public List<String> getOrderIDs();

	/**
	 * Adauga un nou meniu in lista de meniu a restaurantului
	 * 
	 * @param m este noul meniu
	 * @pre m!=null
	 * @post getSizeMenu() == getSizeMenu()@pre+1
	 */
	public void createNewMenu(MenuItem m);

	/**
	 * Sterge un meniu din lista de meniu a restaurantului
	 * 
	 * @param m este meniul pe care vrem sa-l stergem
	 * @pre getSize()>0 && contains(m)==true
	 * @post getSize()== getSize()@pre-1
	 * @post contains(m)==false
	 */
	public void deleteMenuItem(MenuItem m);

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
	public void editMenuItem(int nrRow, String name, int price);

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
	public void createNewOrder(Order key, List<MenuItem> value);

	/**
	 * Genereaza o factura in format .txt dupa o comanda
	 * 
	 * @param order este comanda pe baza careia se genereaza factura
	 * @pre order!=null
	 * @post totalAmount>0
	 * @post writer!=null
	 */
	public void generateBill(Order order);
}
