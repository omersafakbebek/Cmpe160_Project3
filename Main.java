package executable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import elements.BuyingOrder;
import elements.Market;
import elements.SellingOrder;
import elements.Trader;
/**
 * This class provides us to run the program and has the main method.	
 * Also has two static fields called numberOfInvalidQueries and myRandom
 *
 */
public class Main{
	/**
	 * Number of invalid queries given by the input file.
	 */
	public static int numberOfInvalidQueries=0;
	/**
	 * This random object provides us to generate random numbers.
	 */
	public static Random myRandom=new Random();		
	/**
	 * This method is run first when the program starts and takes an input file and according to the inputs calls related parts from other classes and prints out the outputs to the output file.
	 * @param args the paths of input and output files.
	 * @throws FileNotFoundException if the files given by the arguments can not be found.
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File(args[0]));
		PrintStream out = new PrintStream(new File(args[1]));		
		long a=in.nextLong();
		int b=in.nextInt(); int c = in.nextInt(); int d=in.nextInt();
		myRandom.setSeed(a);
		
		Market market= new Market(b);
		for (int i=0;i<c;i++) {
			double dollars=in.nextDouble(); double coins=in.nextDouble();
			Trader newTrader=new Trader(dollars,coins);
			market.traders.add(newTrader);
		}
		for (int i=0;i<d;i++) {			
			int query=in.nextInt();			
			// Buying Order
			if (query==10) {
				int traderID=in.nextInt(); double price=in.nextDouble(); double amount=in.nextDouble();
				BuyingOrder newBuyingOrder=new BuyingOrder(traderID,amount,price);
				market.giveBuyOrder(newBuyingOrder);
			}
			//current selling price
			if (query==11) {
				int traderID=in.nextInt(); double amount=in.nextDouble();				
				if (market.getSellingOrders().size()!=0) {
					BuyingOrder newBuyingOrder=new BuyingOrder(traderID,amount,market.getSellingOrders().peek().getPrice());
					market.giveBuyOrder(newBuyingOrder);
				}else {
					numberOfInvalidQueries++;
				}
			}
			if (query==20) {
				int traderID=in.nextInt(); double price=in.nextDouble() ;   double amount=in.nextDouble();
				SellingOrder newSellingOrder=new SellingOrder(traderID,amount,price);
				market.giveSellOrder(newSellingOrder);
			}
			if (query==21) {
				int traderID=in.nextInt();   double amount=in.nextDouble();
				if (market.getBuyingOrders().size()!=0) {
				SellingOrder newSellingOrder=new SellingOrder(traderID,amount,market.getBuyingOrders().peek().getPrice());
				market.giveSellOrder(newSellingOrder);
				}else {
					numberOfInvalidQueries++;
				}
			}
			if (query==3) {
				int traderID=in.nextInt(); double amount=in.nextDouble();
				market.traders.get(traderID).getWallet().deposit(amount);
			}
			if (query==4) {
				int traderID=in.nextInt(); double amount=in.nextDouble();
				market.traders.get(traderID).getWallet().withdraw(amount);
			}
			if (query==5) {
				Trader trader=market.traders.get(in.nextInt());
				out.print("Trader "+trader.getId()+": "); out.printf("%.5f",trader.getWallet().getDollars()+trader.getWallet().getBlockedDollars()); out.print("$ ");out.printf("%.5f",trader.getWallet().getCoins()+trader.getWallet().getBlockedCoins());out.println("PQ");
			}
			if(query==777) {
				for(int j=0;j<c;j++) {
					market.traders.get(j).getWallet().reward(myRandom.nextDouble()*10);
				}
			}if(query==666) {
				market.makeOpenMarketOperation(in.nextDouble(),market.traders);
			}
			if(query==500) {
				out.print("Current market size: ");out.printf("%.5f",market.calculateDollarsInBuying());out.print(" ");out.printf("%.5f", market.calculateCoinsInSelling());out.println();
			}
			if(query==501) {
				out.println("Number of successful transactions: "+market.getTransactions().size());
			}
			if(query==502) {
				out.println("Number of invalid queries: "+numberOfInvalidQueries);
			}
			if(query==505) {
				double currentBuying;
				double currentSelling;
				double avg;
				if (market.getSellingOrders().size()!=0) {					
					 currentSelling=market.getSellingOrders().peek().getPrice();
				}else {
					 currentSelling=0;
				}
				if(market.getBuyingOrders().size()!=0) {					
					currentBuying=market.getBuyingOrders().peek().getPrice();
				}else {
					currentBuying=0;
				}
				if(market.getBuyingOrders().size()!=0 && market.getSellingOrders().size()!=0 ) {
					avg=(currentBuying+currentSelling)/2;
				}
				else if(market.getBuyingOrders().size()!=0) {
					avg=currentBuying;
				}
				else if(market.getSellingOrders().size()!=0){
					avg=currentSelling;
				}else {
					avg=0;
				}			
				
				out.print("Current prices: "); out.printf("%.5f",currentBuying);out.print(" ");out.printf("%.5f",currentSelling);out.print(" ");out.printf("%.5f", avg);out.println();
			}
			if(query==555) {				
				for (int j=0;j<market.traders.size();j++) {
					out.print("Trader "+j+": ");out.printf("%.5f",market.traders.get(j).getWallet().calculateTotalDollars());out.print("$ ");out.printf("%.5f",market.traders.get(j).getWallet().calculateTotalCoins());out.println("PQ");
				}
				
			}market.checkTransactions(market.traders);
		}
	}
}

