package RestaurantProject.ManagementSystem.BusinessLayer;

public interface MenuItem {

	public int computePrice();

	public String getName();

	public int getPrice();

	public void add(MenuItem menuItem);

	public void delete(MenuItem menuItem);

	public void setName(String text);

	public void setPrice(int price);
}
