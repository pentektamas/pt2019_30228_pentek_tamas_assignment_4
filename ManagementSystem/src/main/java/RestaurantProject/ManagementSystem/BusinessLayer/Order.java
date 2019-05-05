package RestaurantProject.ManagementSystem.BusinessLayer;

import java.time.LocalDate;
import java.util.Objects;

public class Order {

	private int orderID;
	private static int temp=100;
	private String date;
	private int table;

	public Order() {
		orderID=temp++;
		date = LocalDate.now().toString();
	}

	public int hashCode() {
		return Objects.hash(this.orderID, this.date, this.table);
	}

	public boolean equals(Object object) {
		if (object == this)
			return true;
		if (!(object instanceof Order))
			return false;
		Order order = (Order) object;
		boolean rez = (this.orderID == order.orderID) && (this.table == order.table) && this.date.equals(order.date);
		return rez;
	}

	public int getOrderID() {
		return orderID;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getTable() {
		return table;
	}

	public void setTable(int table) {
		this.table = table;
	}
}
