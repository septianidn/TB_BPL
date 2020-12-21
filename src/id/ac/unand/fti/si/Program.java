package id.ac.unand.fti.si;


import java.sql.*;

import java.sql.SQLException;
import java.util.ArrayList;

import java.util.Scanner;

public class Program {
	
	static Scanner scanner;
	static KelolaBarang kelolaBarang;

	public static void main(String[] args) throws SQLException {
		
		kelolaBarang = new KelolaBarang();
		 scanner = new Scanner(System.in);
		Laporan laporan = new Laporan();
	        Integer option = 0;
	       
	            
	            do {
	                System.out.println(">>> MENU Mahasiswa");
	                System.out.println(" 1. Lihat Data Barang");
	                System.out.println(" 2. Tambah Data Barang");
	                System.out.println(" 3. Hapus Data Barang");
	                System.out.println(" 4. Edit Data Barang");
	                System.out.println(" 5. Cari Data Barang");
	                System.out.println(" 0. Keluar");
	                System.out.print("\nPilihan Anda (1/2/3/4/5/0)? ");
	                option = Integer.parseInt(scanner.nextLine());
	                
	                switch (option) {
	                    case 1:
	                    lihatKoleksi();
	                    tunggu();
	                    break;
	                    case 2:
	                    tambahKoleksi();
	                    tunggu();
	                    break;
	                    case 3:
	                    hapusKoleksi();
	                    tunggu();
	                    break;
	                    case 4:
	                    editKoleksi();
	                    tunggu();
	                    break;
	                    case 5:
	                    cariKoleksi();
	                    tunggu();
	                    break;
	                    case 0:
	                     break;
	                    default:
	                    System.out.println("Input tidak valid");
	                }
	                
	                
	            } while (option != 0);
	            


	        
	    }
	    
	    private static void cariKoleksi() throws SQLException {
	      System.out.println(" >> Cari Data Barang");
	        ArrayList<Barang> listBarang =  kelolaBarang.cari();
	        
	        for( Barang barang : listBarang) {
	        	System.out.println("SKU : " + barang.getSKU());
	        	System.out.println("Nama Barang : " + barang.getNamaBarang());
	        	System.out.println("Stok : " + barang.getStok());
	        	System.out.println("Harga Beli : " + barang.getHargaBeli());
	        	System.out.println("Harga Jual : " + barang.getHargaJual());
	        	
	        	
	        } 
	        
	    }
	    
	    private static void editKoleksi() throws SQLException {
	        System.out.println(" >> Edit Data Barang");
	        
	        lihatKoleksi();
	        
	        if(kelolaBarang.edit()>0) {
	        	System.out.println("Berhasil edit data");
	        }
	    
	    }
	    
	    private static void hapusKoleksi() throws SQLException{
	        System.out.println(" >> Hapus Data Barang");
	        
	        lihatKoleksi();
	        
	      if(kelolaBarang.hapus()>0) {
	        	System.out.println("Berhasil hapus data");
	        }
	      
	    }
	    
	    private static void tambahKoleksi() {
	        System.out.println(" >> Tambah Data Barang");
	        
	        
	        
	        if(kelolaBarang.tambahData()>0) {
	        	System.out.println("Berhasil input data");
	        }
	        else {
	        	System.out.println("Tidak berhasil menghapus data");
	        }
	        
	    }
	    
	    private static void lihatKoleksi() throws SQLException {
	        System.out.println(" >> Tampilkan data Barang");
	        
	        ArrayList<Barang> listBarang =  kelolaBarang.getAll();
	        
	       
	        
	        for( Barang barang : listBarang) {
	        	System.out.println("SKU : " + barang.getSKU());
	        	System.out.println("Nama Barang : " + barang.getNamaBarang());
	        	System.out.println("Stok : " + barang.getStok());
	        	System.out.println("Harga Jual : " + barang.getHargaJual());
	        	System.out.println("Harga Beli : " + barang.getHargaBeli());
	        	
	        } 
	    
	    }
	    
	    private static void tunggu(){
	        System.out.print("\n\nTekan Enter untuk melanjutkan");
	        scanner.nextLine();
	    }
  
  
  
  public static void laporan(){
  
  		try {

			static String DB_URL = "jdbc:mysql://localhost:3306/tb_bpl";
    			static String USERNAME = "root";
    			static String PASSWORD = "";
			
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

			if (connection != null) {
				System.out.println("OK");
			} else {
				System.out.println("NOT OK");
			}

			int cek = 0;

			do {
				System.out.println("\n||Laporan");
				System.out.println("\nSilakan Pilih Jenis Laporan");
				System.out.println("1. Laporan Penjualan");
				System.out.println("2. Laporan Keuntungan");

				System.out.print("\nPilihan Anda ? ");
				String pilih = scn.next();

				switch (pilih) {

				case "1":
					
					int cek1 = 0;
					
					do {
					System.out.println("\n||Laporan Penjualan");
					System.out.println("\nSilakan Pilih Jenis Pengelompokan Penjualan");
					System.out.println("1. Perhari");
					System.out.println("2. perbulan");
					
					System.out.print("\nPilihan Anda ? ");
					String pilih1 = scn.next();
					
					switch(pilih1) {
					
					case "1":
						cek1 = 1;
						laporan.laporanHari();
						break;
						
					case "2":
						cek1 = 1;
						laporan.laporan();
						break;
						
					default:
						cek1 = 0;
						System.out.print("\nPilihan Anda Salah, Silakan Pilih Lagi\n");
						break;
					
					}
					}while(cek1 == 0);
					
					System.out.print("\n\nKeluar Dari Aplikasi? (y/t) : ");
					String tanya = scn.next();
					
					switch(tanya) {
					
					case "y":
						cek = 1;	
						break;
						
					case "t":
						cek = 0;	
						break;
						
					}

					break;
				case "2":
					int cek2 = 0;
					
					do {
					System.out.println("\n||Laporan Keuntungan");
					System.out.println("\nSilakan Pilih Jenis Pengelompokan Keuntungan");
					System.out.println("1. Perhari");
					System.out.println("2. perbulan");
					
					System.out.print("\nPilihan Anda ? ");
					String pilih1 = scn.next();
					
					switch(pilih1) {
					
					case "1":
						cek2 = 1;
						laporan.keuntunganHari();
						break;
						
					case "2":
						cek2 = 1;
						laporan.keuntungan();
						break;
						
					default:
						cek2 = 0;
						System.out.print("\nPilihan Anda Salah, Silakan Pilih Lagi\n");
						break;
					
					}
					}while(cek2 == 0);

					
					
					System.out.print("\n\nKeluar Dari Aplikasi? (y/t) : ");
					String tanya1 = scn.next();
					
					switch(tanya1) {
					
					case "y":
						cek = 1;	
						break;
						
					case "t":
						cek = 0;	
						break;
						
					}
					
					break;
				default:
					cek = 0;
					System.out.print("\nPilihan Anda Salah, Silakan Pilih Lagi\n");
					break;

				}
			} while (cek == 0);
			
			System.out.println("\nBye");

			
		} catch (SQLException e) {

			e.printStackTrace();
		}
  
  
  
  
  }



	

}
