package com.aditya.mlabs.tester;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Scanner;
import java.util.TreeMap;

public class Budget {

	
	static int budgetShopping(int n, int[] bundleQuantities, int[] bundleCosts) {
		//50 dollars
		int maxNtBookQtyPurchased = 0;
		int currentBalanceInHand = n;
		
		int smallest = bundleCosts[0];
        int largetst = bundleCosts[0];
         
          for(int i=1; i< bundleCosts.length; i++)
          {
	          if(bundleCosts[i] > largetst)
	                  largetst = bundleCosts[i];
	          else if (bundleCosts[i] < smallest)
	                  smallest = bundleCosts[i];
          }
  		Map<Integer, Integer> bundleQtyToPrice = new TreeMap<Integer, Integer>();
		//Map<Integer, Integer> bundleQtyToPrice = new TreeMap<Integer, Integer>(new MyComparator());
		for(int i =0; i<bundleQuantities.length; i++){
			bundleQtyToPrice.put(bundleQuantities[i], bundleCosts[i]);
		}
		NavigableMap<Integer, Integer> nmap = ((TreeMap<Integer, Integer>) bundleQtyToPrice).descendingMap();
		
		while(currentBalanceInHand >= smallest) {
			for(Integer bundleQty : nmap.keySet()){
				if(currentBalanceInHand >= nmap.get(bundleQty)){
					maxNtBookQtyPurchased = maxNtBookQtyPurchased + bundleQty;
					currentBalanceInHand = currentBalanceInHand - nmap.get(bundleQty);
					break;
				}
			}
		}
		
		return maxNtBookQtyPurchased;

    }
	
	public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        final String fileName = System.getenv("OUTPUT_PATH");
        BufferedWriter bw = null;
        if (fileName != null) {
            bw = new BufferedWriter(new FileWriter(fileName));
        }
        else {
            bw = new BufferedWriter(new OutputStreamWriter(System.out));
        }

        int res;
        int n;
        n = Integer.parseInt(in.nextLine().trim());

        int bundleQuantities_size = 0;
        bundleQuantities_size = Integer.parseInt(in.nextLine().trim());

        int[] bundleQuantities = new int[bundleQuantities_size];
        for(int i = 0; i < bundleQuantities_size; i++) {
            int bundleQuantities_item;
            bundleQuantities_item = Integer.parseInt(in.nextLine().trim());
            bundleQuantities[i] = bundleQuantities_item;
        }

        int bundleCosts_size = 0;
        bundleCosts_size = Integer.parseInt(in.nextLine().trim());

        int[] bundleCosts = new int[bundleCosts_size];
        for(int i = 0; i < bundleCosts_size; i++) {
            int bundleCosts_item;
            bundleCosts_item = Integer.parseInt(in.nextLine().trim());
            bundleCosts[i] = bundleCosts_item;
        }

        res = budgetShopping(n, bundleQuantities, bundleCosts);
        bw.write(String.valueOf(res));
        bw.newLine();

        bw.close();
    }
}

/*
class MyComparator implements Comparator<Integer> {

	  @Override
	  public int compare(Integer first, Integer second) {

	    return second.compareTo(first);
	  }
	}
*/