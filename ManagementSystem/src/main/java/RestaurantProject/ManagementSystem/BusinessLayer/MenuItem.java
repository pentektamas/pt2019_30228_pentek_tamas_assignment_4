package RestaurantProject.ManagementSystem.BusinessLayer;

import java.io.Serializable;

public interface MenuItem extends Serializable {

	public int computePrice();

	public String getName();

	public int getPrice();

	public void add(MenuItem menuItem);

	public void delete(MenuItem menuItem);

	public void setName(String text);

	public void setPrice(int price);
}
