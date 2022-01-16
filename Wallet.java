package elements;

import executable.Main;
/**
 * This class is a template for the wallets of the traders.
 *
 *
 */
public class Wallet {
	/**
	 * The amount of dollars in the wallet.
	 */
	private double dollars;
	/**
	 * The amount of PQoins in the wallet.
	 */
	private double coins;
	/**
	 * The amount of blocked dollars in the wallet.
	 */
	private double blockedDollars;
	/**
	 * The amount of blocked PQoins in the wallet.
	 */
	private double blockedCoins;
	/**
	 * Constructor for wallet objects.
	 * @param dollars Initial amount of dollars in the wallet.
	 * @param coins Initial amount of PQoins in the wallet.
	 */
	public Wallet(double dollars, double coins) {
		this.dollars=dollars;
		this.coins=coins;
	}
	/**
	 * This method is used to add dollars to the wallet if the trader wants to deposit money.
	 * @param dollars The amount of dollars which wanted to be deposit.
	 */
	public void deposit(double dollars) {
		this.dollars+=dollars;
	}
	/**
	 * This method is used to remove dollars from the wallet if the trader wants to withdraw money. If the trader has not enough money , number of invalid queries increases by 1.
	 * @param dollars The amount of dollars which wanted to be withdraw.
	 */
	public void withdraw(double dollars) {
		if (this.dollars>=dollars) {
			this.dollars-=dollars;			
		}
		else {			
			Main.numberOfInvalidQueries++;
		}
		
	}
	/**
	 * Getter method for the amount of the usable dollars in the wallet.
	 * @return the amount of the usable dollars in the wallet
	 */
	public double getDollars() {
		return dollars;
	}
	/**
	 * Getter method for the amount of the blocked PQoins in the wallet.
	 * @return the amount of the blocked PQoins in the wallet
	 */
	public double getBlockedCoins() {
		return blockedCoins;		
	}
	/**
	 * Getter method for the amount of the blocked dollars in the wallet.
	 * @return the amount of the blocked dollars in the wallet
	 */
	public double getBlockedDollars() {
		return blockedDollars;
	}
	/**
	 * This method calculate the total amount of the dollars in the wallet.
	 * @return total amount of the dollars in the wallet
	 */
	public double calculateTotalDollars() {
		return dollars+blockedDollars;
	}
	/**
	 * This method calculate the total amount of the PQoins in the wallet.
	 * @return total amount of the PQoins in the wallet
	 */
	public double calculateTotalCoins() {
		return coins+blockedCoins;
	}
	/**
	 * Getter method for the amount of the usable PQoins in the wallet.
	 * @return the amount of the usable PQoins in the wallet
	 */
	public double getCoins() {
		return coins;
	}
	/**
	 * This method adds the given amount of PQoins to the usable amount of PQoins in the wallet when the query wants to give reward.
	 * @param amount
	 */
	public void reward(double amount) {
		coins+=amount;
	}
	/**
	 * This method adds the given amount of the PQoins to the amount of the blocked PQoins in the wallet.
	 * @param amount the amount of the PQoins wanted to be added.
	 */
	public void addBlockedCoins(double amount) {
		this.blockedCoins+= amount;
	}
	/**
	 * This method removes the given amount of the PQoins from the amount of the blocked PQoins in the wallet.
	 * @param amount the amount of the PQoins wanted to be removed.
	 */
	public void delBlockedCoins(double amount) {
		this.blockedCoins-= amount;
	}
	/**
	 * This method adds the given amount of the dollars to the amount of the blocked dollars in the wallet.
	 * @param amount the amount of the dollars wanted to be added.
	 */
	public void addBlockedDollars(double amount) {
		this.blockedDollars+= amount;
	}
	/**
	 * This method removes the given amount of the dollars from the amount of the blocked dollars in the wallet.
	 * @param amount the amount of the dollars wanted to be removed.
	 */
	public void delBlockedDollars(double amount) {
		this.blockedDollars-= amount;
	}
	/**
	 * This method removes the given amount of the dollars from the amount of the usable dollars in the wallet.
	 * @param amount the amount of the dollars wanted to be removed.
	 */
	public void removeDollars(double amount) {
		this.dollars-=amount;
	}
	/**
	 * This method adds the given amount of the dollars to the amount of the usable dollars in the wallet.
	 * @param amount the amount of the dollars wanted to be added.
	 */
	public void addDollars(double amount) {
		this.dollars+=amount;
	}
	/**
	 * This method removes the given amount of the PQoins from the amount of the usable PQoins in the wallet.
	 * @param amount the amount of the PQoins wanted to be removed.
	 */
	public void removeCoins(double amount) {
		this.coins-=amount;
	}
	/**
	 * This method adds the given amount of the PQoins to the amount of the usable PQoins in the wallet.
	 * @param amount the amount of the PQoins wanted to be added.
	 */
	public void addCoins(double amount) {
		this.coins+=amount;
	}
}
