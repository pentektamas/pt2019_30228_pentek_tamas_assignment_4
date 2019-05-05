package RestaurantProject.ManagementSystem.Main;

import java.time.LocalDate;

import RestaurantProject.ManagementSystem.BusinessLayer.BaseProduct;
import RestaurantProject.ManagementSystem.BusinessLayer.CompositeProduct;
import RestaurantProject.ManagementSystem.BusinessLayer.MenuItem;
import RestaurantProject.ManagementSystem.PresentationLayer.MainWindow;

public class MainClass {

	public static void main(String[] args) {
		MainWindow mw = new MainWindow();
		mw.setVisible(true);

		MenuItem m1 = new BaseProduct("Snitel de pui", 9);
		MenuItem m2 = new BaseProduct("Snitel de porc", 12);
		MenuItem m3 = new BaseProduct("Cartofi", 2);
		MenuItem m4 = new BaseProduct("Orez", 3);

		MenuItem m5 = new CompositeProduct("Meniu 1");
		MenuItem m6 = new CompositeProduct("Meniu 2");

		m5.add(m1);
		m5.add(m4);

		m6.add(m2);
		m6.add(m3);

		System.out.println(m5.getName() + "  " + m5.getPrice());
		System.out.println(m6.getName() + "  " + m6.getPrice());
		System.out.println(LocalDate.now().toString());
	}

}
