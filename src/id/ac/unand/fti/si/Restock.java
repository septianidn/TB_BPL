package id.ac.unand.fti.si;

import java.util.Scanner;

public class Restock {
	Scanner scn = new Scanner(System.in);
	DataRestock dataRestock;
	
	public Restock() {
		try {
			dataRestock = new DataRestock();
		} catch (NullPointerException e) {
			System.out.println("Masukkan data terlebih dahulu");
		}
	}
	
	
	//	Restok
	public void restock() {
		
		System.out.println("\n\n--RESTOCK BARANG--");
		
		System.out.print("SKU : ");
		String sku = scn.next().toUpperCase();
		
		System.out.print("Stock baru : ");
		Integer stock = scn.nextInt();
		
		if(dataRestock.restock(sku, stock) == 1) {
			System.out.println("Restock success");
			Main.mainMenu();
		}
		
	}

}
