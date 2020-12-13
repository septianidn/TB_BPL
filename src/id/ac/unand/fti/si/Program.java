package id.ac.unand.fti.si;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {
	
	static Scanner scanner;
	static KelolaBarang kelolaBarang;

	public static void main(String[] args) throws SQLException {
		
		kelolaBarang = new KelolaBarang();
		 scanner = new Scanner(System.in);
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

	

}
