package elements;
/**
 * This is an abstract class and has 2 child classes called BuyingOrder and SellingOrder.
 *  
 *
 */
public abstract class Order{
	/**
	 * The amount of the PQoins of the order.
	 */
	double amount;
	/**
	 * The price of the PQoins of the order.
	 */
	double price;
	/**
	 * The ID of the trader which gives the order.
	 */
	int traderID;	
	/**
	 * The constructor for the Order objects.
	 * @param traderID The ID of the trader which gives the order.
	 * @param amount The amount of the PQoins of the order
	 * @param price The price of the PQoins of the order.
	 */
	public Order(int traderID, double amount, double price) {
		this.traderID=traderID;
		this.amount=amount;
		this.price=price;
	}
	/**
	 * Getter method for the amount of the Order. 
	 * @return the amount of the Order.
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * Getter method for the price of the Order. 
	 * @return the price of the Order.
	 */
	public double getPrice() {
		return price;
	}	
}

