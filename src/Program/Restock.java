package Program;
import java.sql.SQLException;
import java.util.Scanner;

public class Restock {
	Scanner scn = new Scanner(System.in);
	DataRestock dataRestock;
	KelolaBarang barang = new KelolaBarang();
	
	public Restock() {
		try {
			dataRestock = new DataRestock();
		} catch (NullPointerException e) {
			System.out.println("Masukkan data terlebih dahulu");
		}
	}
	
	
	//	Restok
	public void restock() throws Exception {
		
		barang.getAll();
		System.out.println("\n\n--RESTOCK BARANG--");
		
		System.out.print("Masukkan SKU : ");
		String sku = scn.next().toUpperCase();
		
		System.out.print("Stock baru : ");
		Integer stock = scn.nextInt();
		
		if(dataRestock.restock(sku, stock) == 1) {
			System.out.println("Restock success");
			Main.menuBarang();
		}
		
	}

}
