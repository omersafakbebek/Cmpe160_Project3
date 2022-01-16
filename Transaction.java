package elements;
/**
 * This class is used to create transaction objects which occurs in the market. 
 */
public class Transaction {
	/**
	 * Selling Order which has the seller part information of the transaction.
	 */
	private SellingOrder sellingOrder;
	/**
	 * Buying Order which has the buyer part information of the transaction.
	 */
	private BuyingOrder buyingOrder;	
	/**
	 * Constructor for transaction objects.
	 * @param sellingOrder Selling Order which has the seller part information of the transaction.
	 * @param buyingOrder Buying Order which has the buyer part information of the transaction.
	 */
	public Transaction(SellingOrder sellingOrder,BuyingOrder buyingOrder) {
		this.buyingOrder=buyingOrder;
		this.sellingOrder=sellingOrder;	
		
	}
}
