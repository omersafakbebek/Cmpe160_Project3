package elements;
/**
 * This class is used to create BuyingOrder object and extends Order class. Also, implements Comparable interface to compare its objects.
 */
public class BuyingOrder extends Order implements Comparable<BuyingOrder> {
	/**
	 * This is a constructor to create BuyingOrder objects.
	 * @param traderID ID of the trader which gives the order.
	 * @param amount The amount of PQoins which the trader wants to buy.
	 * @param price The price of PQoins which the trader wants to buy.
	 */
	public BuyingOrder(int traderID, double amount, double price) {
		super(traderID, amount, price);
		// TODO Auto-generated constructor stub
	}

	@Override
	/**
	 * This method compares the BuyingOrder objects according to their price's, amount's and traderID's.
	 * Firstly checks prices, after that amounts and finally traderIDs.
	 */
	public int compareTo(BuyingOrder o) {
		// TODO Auto-generated method stub
		if(price==o.price) {
			if (amount==o.amount) {
				if(traderID==o.traderID) {
					return 0;
				}else {
					return traderID-o.traderID;
				}
			}else if(amount>o.amount){
				return -1;				
			}else {
				return 1;
			}
		}else if(price>o.price) {
			return -1;
		}else {
			return 1;
		}		
	}
	

}
