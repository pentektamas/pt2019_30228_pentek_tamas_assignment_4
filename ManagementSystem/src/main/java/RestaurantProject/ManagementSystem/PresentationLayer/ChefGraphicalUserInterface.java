package RestaurantProject.ManagementSystem.PresentationLayer;

import javax.swing.*;

public class ChefGraphicalUserInterface extends JPanel{

	private JLabel select=new JLabel("Select an operation: ");
	
	public ChefGraphicalUserInterface() {
		JPanel p=new JPanel();
		p.add(select);
		this.add(p);
	//	this.setSize(new Dimension(300,200));
		this.setVisible(true);
	}
}
