package RestaurantProject.ManagementSystem.BusinessLayer;

import java.util.List;

public interface RestaurantProcessing {

	public void createNewMenu(MenuItem m);

	public void deleteMenuItem(MenuItem m);

	public void editMenuItem(int nrRow);

	public void createNewOrder(Order key, List<MenuItem> value);

	public void generateBill(Order order);

	public void computePrice(Order o);
}
