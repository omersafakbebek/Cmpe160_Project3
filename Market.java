package elements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

import executable.Main;
/**
 * This class is a template for market objects.
 * Class has  5 fields.
 * This has also a constructor to create market objects and some methods to do market operations. 
 */
public class Market {
	/**
	 * This priority queue includes selling orders given by traders.
	 */
	private PriorityQueue<SellingOrder> sellingOrders;
	/**
	 * This priority queue includes buying orders given by traders.
	 */
	private PriorityQueue<BuyingOrder> buyingOrders;
	/**
	 * When there is a successful transaction, a transaction object is created and this object is added to this Array List.
	 */
	private ArrayList<Transaction> transactions;
	/**
	 * This array list contains traders in the market.
	 */
	public  ArrayList<Trader> traders;
	/**
	 * The amount of commission taken by the market from the transaction per thousand dollars.
	 */
	private int fee;
	/**
	 * This constructor creates market objects.
	 * @param fee The amount of commission taken by the market from the transaction per thousand dollars.
	 */
	public Market(int fee) {
		this.fee=fee;
		sellingOrders=new PriorityQueue<>();
		buyingOrders=new PriorityQueue<>();
		transactions=new ArrayList<>();	
		traders=new ArrayList<>();
	}
	
	/**
	 * This method first checks if the order given by the system or not.If the order not given by the system, checks if the trader provides the liabilities by using sell method in the trader class.
	 * Adds the order to selling orders priority queue and adds the amount of ordered coins to blocked coins of trader's wallet.
	 * Increases the number of invalid queries if order does not fit to the liabilities.
	 * @param order the selling order given by the query.
	 */
	public void giveSellOrder(SellingOrder order) {
		if (order.traderID==0) {
			sellingOrders.add(order);
		}else {
		if(traders.get(order.traderID).sell(order.amount, order.price, this)==1) {
			sellingOrders.add(order);
			traders.get(order.traderID).getWallet().addBlockedCoins(order.amount);
			traders.get(order.traderID).getWallet().removeCoins(order.amount);
		}
		else {
			Main.numberOfInvalidQueries+=1;
		}
		}
	}
	/**
	 * This method first checks if the order given by the system or not.If the order not given by the system, checks if the trader provides the liabilities by using buy method in the trader class.
	 * Adds the order to selling orders priority queue and adds the amount of ordered coins to blocked coins of trader's wallet.
	 * Increases the number of invalid queries if order does not fit to the liabilities.
	 * @param order the buying order given by the query.
	 */
	public void giveBuyOrder(BuyingOrder order) {
		if (order.traderID==0) {
			buyingOrders.add(order);
		}else {
		
		if(traders.get(order.traderID).buy(order.amount, order.price, this)==1) {
			buyingOrders.add(order);
			traders.get(order.traderID).getWallet().addBlockedDollars(order.price*order.amount);
			traders.get(order.traderID).getWallet().removeDollars(order.price*order.amount);
		}
		else {
			Main.numberOfInvalidQueries+=1;
		}
		}
	}
	/**
	 * This method gives buy and sell orders to change the current price to the given price.
	 * @param price the price wanted to be set as current price by the system.
	 * @param traders the list of the traders in the market.
	 */
	public void makeOpenMarketOperation(double price,ArrayList<Trader> traders) {
		while(buyingOrders.size()>0 && buyingOrders.peek().price>=price) {
			SellingOrder newsell=new SellingOrder(0, buyingOrders.peek().amount, buyingOrders.peek().price);
			giveSellOrder(newsell);
			checkTransactions(traders);
		}
		while(sellingOrders.size()>0 && sellingOrders.peek().price<=price) {
			BuyingOrder newbuy=new BuyingOrder(0, sellingOrders.peek().amount, sellingOrders.peek().price);
			giveBuyOrder(newbuy);
			checkTransactions(traders);
		}
	}
	/**
	 * Checks if the prices at the priority queues are overlapping. If any overlap is detected, does the necessary transaction operations until there is no overlapping. Changes the wallets of	the traders and creates a transaction object and adds it to the transaction list. Also creates new orders if the amounts of the PQoins of the selling order and buying order are not same.
	 * @param traders the list of the traders in the market.
	 */
	public void checkTransactions(ArrayList<Trader> traders) {
		boolean check=true;
		while(check&& buyingOrders.size()!=0 && sellingOrders.size()!=0) {			
			check=false;
			BuyingOrder buyorder = buyingOrders.peek();
			SellingOrder sellorder=sellingOrders.peek();
			if(buyorder.price>=sellorder.price) {			
				check=true;				
				double tAmount;
				double tPrice = sellorder.price;
				buyingOrders.remove(buyorder);
				sellingOrders.remove(sellorder);				
				if (sellorder.amount>buyorder.amount) {
					tAmount=buyorder.amount;					
					sellingOrders.add(new SellingOrder(sellorder.traderID, sellorder.amount-tAmount, tPrice));					
				}else if(sellorder.amount<buyorder.amount) {
					tAmount=sellorder.amount;
					buyingOrders.add(new BuyingOrder(buyorder.traderID,buyorder.amount-tAmount,buyorder.price));
				}else {
					tAmount=sellorder.amount;
				}
				transactions.add(new Transaction(new SellingOrder(sellorder.traderID, tAmount, tPrice), new BuyingOrder(buyorder.traderID, tAmount, tPrice)));
				if(buyorder.traderID==0) {
					traders.get(sellorder.traderID).getWallet().delBlockedCoins(tAmount);
					traders.get(sellorder.traderID).getWallet().addDollars(tPrice*tAmount*(1-(double)fee/1000));
				}else if(sellorder.traderID==0) {
					traders.get(buyorder.traderID).getWallet().delBlockedDollars(buyorder.price*tAmount);
					traders.get(buyorder.traderID).getWallet().addDollars((buyorder.price-tPrice)*tAmount);
					traders.get(buyorder.traderID).getWallet().addCoins(tAmount);
				}else {
					traders.get(buyorder.traderID).getWallet().delBlockedDollars(buyorder.price*tAmount);
					traders.get(buyorder.traderID).getWallet().addDollars((buyorder.price-tPrice)*tAmount);
					traders.get(buyorder.traderID).getWallet().addCoins(tAmount);
					traders.get(sellorder.traderID).getWallet().delBlockedCoins(tAmount);
					traders.get(sellorder.traderID).getWallet().addDollars(tPrice*tAmount*(1-(double)fee/1000));					
				}				
			}
		}		
	}
	/**
	 * This method calculates total amount of the dollars used to give buying orders in the market.
	 * @return total dollars used to give buying orders in the market.
	 */
	public double calculateDollarsInBuying() {
		double total=0;
		Iterator<BuyingOrder> itr=buyingOrders.iterator();
		while(itr.hasNext()) {
			BuyingOrder temp=itr.next();
			total+=temp.price*temp.amount;
		}
		return total;
	}
	/**
	 * This method calculates total amount of the PQoins used to give selling orders in the market.
	 * @return total PQoins used to give selling orders in the market.
	 */
	public double calculateCoinsInSelling() {
		double total=0;
		Iterator<SellingOrder> itr=sellingOrders.iterator();
		while(itr.hasNext()) {
			total+=itr.next().amount;
		}
		return total;
	}
	/**
	 * Getter method for buyingOrders priority queue.
	 * @return priority queue of buying orders in the market.
	 */
	public PriorityQueue<BuyingOrder> getBuyingOrders() {
		return buyingOrders;
	}
	/**
	 * Getter method for sellingOrders priority queue.
	 * @return priority queue of selling orders in the market.
	 */
	public PriorityQueue<SellingOrder> getSellingOrders() {
		return sellingOrders;
	}
	/**
	 * Getter method for transactions array list.
	 * @return list of the transaction done in the market.
	 */
	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

}
