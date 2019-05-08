package RestaurantProject.ManagementSystem.BusinessLayer;

public class BaseProduct implements MenuItem {

	private String name;
	private int price;

	public BaseProduct(String name, int price) {
		this.name = name;
		this.price = price;
	}

	public int computePrice() {
		return this.price;
	}

	public String getName() {
		return this.name;
	}

	public int getPrice() {
		return this.price;
	}

	public void add(MenuItem menuItem) {
	}

	public void delete(MenuItem menuItem) {
	}

	public void setName(String text) {
		this.name = text;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
