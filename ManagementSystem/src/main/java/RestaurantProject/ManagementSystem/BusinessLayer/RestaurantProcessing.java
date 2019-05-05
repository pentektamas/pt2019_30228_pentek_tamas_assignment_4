package RestaurantProject.ManagementSystem.BusinessLayer;

import java.util.ArrayList;

public interface RestaurantProcessing {

	public void createNewMenu(MenuItem m);

	public void deleteMenuItem(MenuItem m);

	public void editMenuItem(int nrRow);

	public void createNewOrder(Order key, ArrayList<MenuItem> value);

	public void generateBill();

	public void computePrice(Order o);
}
