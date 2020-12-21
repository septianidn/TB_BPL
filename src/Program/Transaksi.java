package Program;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeMap;

public class Transaksi {
	
	Date date = new Date();
	Scanner scn = new Scanner(System.in);
	TransaksiData transaksiData;
	TransaksiFunction transaksiFunction;
	KelolaBarang kelolabarang;
	
	public Transaksi() {
		try {
			transaksiFunction = new TransaksiFunction();
		} catch (NullPointerException e) {
			System.out.println("Masukkan data terlebih dahulu");
		}
	}
	

	
	public void tambahData() throws Exception {
		
		String ada;
		TreeMap<String, Integer> jual = new TreeMap<>();
		
		kelolabarang = new KelolaBarang();
		  ArrayList<Barang> listBarang =  kelolabarang.getAll();
	        
	       
	        
	        for( Barang barang : listBarang) {
	        	System.out.println("\n===============================");
	        	System.out.println("SKU : " + barang.getSKU());
	        	System.out.println("Nama Barang : " + barang.getNamaBarang());
	        	System.out.println("Stok : " + barang.getStok());
	        	System.out.println("Harga Jual : " + barang.getHargaJual());
	        	System.out.println("Harga Beli : " + barang.getHargaBeli());
	        	System.out.println("===============================");
	        	
	        }

		System.out.println("\n\n--PENJUALAN--");
		
		String dt = String.format("%tF", date);
		
		do {
			
			System.out.print("Nama Barang : ");
			String nama = scn.next();
			String namafix = nama.substring(0, 1).toUpperCase() + nama.substring(1);
			
			System.out.print("Jumlah : ");
			Integer jumlah = scn.nextInt();
			
			jual.put(namafix, jumlah);
			
			System.out.print("Ada lagi??..(y/t)");
			ada = scn.next();
			
		} while(ada.equalsIgnoreCase("y"));
		
		if(ada.equalsIgnoreCase("t")) {
		
			if(transaksiFunction.tambah(dt, jual) == 1) {
				jual.clear();
				System.out.println("Transaksi berhasil ditambahkan");
				Main.mainMenu();
			}
			
			Main.mainMenu();
			
		}
		
	}



	
	public void hapusData() throws Exception {
		
		System.out.println("\n\n--HAPUS DATA TRANSAKSI--");
		
		System.out.print("Noresi : ");
		String noresi = scn.next().toUpperCase();
		
		if(transaksiFunction.hapus(noresi) == 1) {
			System.out.println("Transaksi berhasil dihapus");
			Main.mainMenu();
		} else {
			System.out.println("Noresi tidak ditemukan");
			Main.mainMenu();
		}
		
	}

	
	public void cariData() {
		
		System.out.println("\n\n--PENCARIAN DATA TRANSAKSI--");
		
		System.out.print("Username : ");
		String username = scn.next();
		
		ArrayList<TransaksiData> listcari = transaksiFunction.cari(username);
		
		System.out.print("Noresi");
        System.out.print("\t");
        System.out.print("Username");
        System.out.print("\t");
        System.out.print("Tanggal");
        System.out.print("\t\t");
        System.out.print("Nama Barang");
        System.out.print("\t");
        System.out.print("Jumlah");
        System.out.print("\t\t");
        System.out.println("Total");
        
        for(TransaksiData list : listcari) {
        	
        	System.out.print(list.noresi);
            System.out.print("\t");
            System.out.print(list.username);
            System.out.print("\t\t");
            System.out.print(list.date);
            System.out.print("\t");
            System.out.print(list.namaBarang);
            System.out.print("\t\t");
            System.out.print(list.jumlah);
            System.out.print("\t\t");
            System.out.println(list.total);
        	
        }
        
        Main.tunggu();
		
	}

	
	public void lihatData() {
		
		System.out.println("\n\n--DAFTAR TRANSAKSI--");
		
		Stack<TransaksiData> listTransaksi = transaksiFunction.lihat();
		
		System.out.print("Noresi");
        System.out.print("\t");
        System.out.print("Username");
        System.out.print("\t");
        System.out.print("Tanggal");
        System.out.print("\t\t");
        System.out.print("Nama Barang");
        System.out.print("\t");
        System.out.print("Jumlah");
        System.out.print("\t\t");
        System.out.println("Total");
        
        for(TransaksiData list : listTransaksi) {
        	
        	System.out.print(list.noresi);
            System.out.print("\t");
            System.out.print(list.username);
            System.out.print("\t\t");
            System.out.print(list.date);
            System.out.print("\t");
            System.out.print(list.namaBarang);
            System.out.print("\t\t");
            System.out.print(list.jumlah);
            System.out.print("\t\t");
            System.out.println(list.total);
        	
        }
		
	}

}
