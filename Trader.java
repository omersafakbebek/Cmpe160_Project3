package elements;
/**
 * This class is a template for Trader objects.
 * 
 *
 */
public class Trader {
	/**
	 * ID of the trader.
	 */
	private int id;
	/**
	 * Wallet of the trader.
	 */
	private Wallet wallet;
	/**
	 * Number of traders in the market.
	 */
	public static int numberOfUsers = 0;
	/**
	 * This constructor creates Treader objects.
	 * @param dollars The initial amount of money in the trader's wallet.
	 * @param coins The initial amount of PQoins in the trader's wallet.
	 */
	public Trader(double dollars, double coins) {
		id=numberOfUsers;
		numberOfUsers++;
		wallet=new Wallet(dollars,coins);
	}
	/**
	 * This method checks if the trader has enough amount of PQoins to give sell order.
	 * @param amount The amount of PQoins which trader wants to sell.
	 * @param price The price of PQoins which trader wants to sell.
	 * @param market The market in which the trader gives the order.
	 * @return 1 if trader has enough amount of PQoins. Otherwise -1.
	 */
	public int sell(double amount, double price,Market market) {
		if(wallet.getCoins()>=amount) {
			return 1;
		}
		else {
			return -1;
		}
	}
	/**
	 * This method checks if the trader has enough amount of dollars to give buy order.
	 * @param amount The amount of PQoins which trader wants to buy.
	 * @param price The price of PQoins which trader wants to buy.
	 * @param market The market in which the trader gives the order.
	 * @return 1 if trader has enough amount of money. Otherwise -1.
	 */
	public int buy(double amount, double price, Market	market) {
		if(wallet.getDollars()>=price*amount) {
			return 1;
		}
		else {
			return -1;
		}
	}
	/**
	 * Getter method for wallet of the trader.
	 * @return wallet of the trader
	 */
	public Wallet getWallet() {
		return wallet;
	}
	/**
	 * Getter method for ID of the trader.
	 * @return ID of the trader.
	 */
	public int getId() {
		return id;
	}
	
}
