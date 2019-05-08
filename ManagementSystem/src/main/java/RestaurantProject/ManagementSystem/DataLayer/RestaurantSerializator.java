package RestaurantProject.ManagementSystem.DataLayer;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import RestaurantProject.ManagementSystem.BusinessLayer.MenuItem;

public class RestaurantSerializator {

	private String filename = "restaurantMenus.ser";

	public void serialization(List<MenuItem> menus) {

		try {
			FileOutputStream file = new FileOutputStream(this.filename);
			ObjectOutputStream outputObject = new ObjectOutputStream(file);

			for (MenuItem m : menus) {
				outputObject.writeObject(m);
			}

			outputObject.close();
			file.close();

		} catch (IOException e) {
			System.out.println("InputOutput exception - serialization");
		}
	}

	public List<MenuItem> deserialization() {
		MenuItem menu = null;
		boolean endOfFile = false;
		List<MenuItem> menus = new ArrayList<MenuItem>();
		try {

			FileInputStream file = new FileInputStream(this.filename);
			ObjectInputStream inputObject = new ObjectInputStream(file);
			while (!endOfFile) {
				menu = (MenuItem) inputObject.readObject();
				if (menu != null)
					menus.add(menu);
				else
					endOfFile = true;
			}
			inputObject.close();
			file.close();
		} catch (EOFException e) {
		} catch (IOException e) {
			System.out.println("InputOutput exception - deserialization");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException  - deserialization");
		}
		return menus;
	}
}
