package RestaurantProject.ManagementSystem.BusinessLayer;

import java.util.ArrayList;
import java.util.List;

public class CompositeProduct implements MenuItem {

	private String name;
	private int price;
	private List<MenuItem> menuItems = new ArrayList<MenuItem>();

	public CompositeProduct(String name) {
		this.name = name;
	}

	public int computePrice() {
		int rez = 0;
		for (MenuItem m : menuItems) {
			rez = rez + m.getPrice();
		}
		return rez;
	}

	public String getName() {
		return this.name;
	}

	public int getPrice() {
		this.price=this.computePrice();
		return this.price;
	}

	public void add(MenuItem menuItem) {
		menuItems.add(menuItem);
	}

	public void delete(MenuItem menuItem) {
		menuItems.remove(menuItem);
	}

	public void setName(String text) {
		this.name=text;
	}

	public void setPrice(int price) {
		this.price=price;
	}

}
