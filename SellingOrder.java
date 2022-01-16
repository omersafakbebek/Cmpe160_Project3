package elements;
/**
 * This class is used to create SellingOrder objects and extends Order class. Also, implements Comparable interface to compare its objects.
 */
public class SellingOrder extends Order implements Comparable<SellingOrder>{
	/**
	 * This is a constructor to create SellingOrder objects.
	 * @param traderID ID of the trader which gives the order.
	 * @param amount The amount of PQoins which the trader wants to sell.
	 * @param price The price of PQoins which the trader wants to sell.
	 */
	public SellingOrder(int traderID, double amount, double price) {
		super(traderID, amount, price);
		// TODO Auto-generated constructor stub
	}

	@Override
	/**
	 * This method compares the SellingOrder objects according to their price's, amount's and traderID's.
	 * Firstly checks prices, after that amounts and finally traderIDs.
	 */
	public int compareTo(SellingOrder o) {
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
			return 1;
		}else {
			return -1;
		}		
	}

}
