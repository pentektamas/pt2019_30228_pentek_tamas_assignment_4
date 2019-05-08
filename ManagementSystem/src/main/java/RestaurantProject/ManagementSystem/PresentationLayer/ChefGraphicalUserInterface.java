package RestaurantProject.ManagementSystem.PresentationLayer;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import RestaurantProject.ManagementSystem.BusinessLayer.CompositeProduct;

@SuppressWarnings("deprecation")
public class ChefGraphicalUserInterface extends JPanel implements Observer {

	private List<String> foods;
	private JLabel list = new JLabel("List of foods is: ");
	private JList foodList;
	JPanel p = new JPanel();
	private static int TEMP = 1;
	private int index;

	public ChefGraphicalUserInterface() {

		foods = new ArrayList<String>();
		foodList = new JList(foods.toArray());
		JPanel p = new JPanel();
		BorderLayout layout = new BorderLayout();
		p.setLayout(layout);
		p.add(list, BorderLayout.PAGE_START);
		p.add(foodList, BorderLayout.CENTER);
		this.add(p);
		this.setVisible(true);
	}

	public void update(Observable o, Object arg) {
		if (arg instanceof CompositeProduct) {
			this.index = this.TEMP++;
			String element = this.index + ". " + ((CompositeProduct) arg).getName();// + products;
			foods.add(element);
			Collections.sort(foods);
			foodList.setListData(foods.toArray());
			updateUI();
		}
	}
}
