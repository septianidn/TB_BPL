package Program;
import java.util.ArrayList;
import java.util.Scanner;

public class BarangFunction implements kelola {
	
	static Scanner scanner = new Scanner(System.in);
	static KelolaBarang kelolaBarang = new KelolaBarang();
	
	

	@Override
	public void tambahData() throws Exception {
		
	       System.out.println(" >> Tambah Data Barang");
	        
	        
	        
	        if(kelolaBarang.tambahData()>0) {
	        	System.out.println("Berhasil input data");
	        	Main.menuBarang();
	        }
	        else {
	        	System.out.println("Tidak berhasil menghapus data");
	        }
		
	}

	@Override
	public void editData() throws Exception {
		
	       System.out.println(" >> Edit Data Barang");
	        
	        lihatData();
	        
	        if(kelolaBarang.edit()>0) {
	        	System.out.println("Berhasil edit data");
	        	Main.menuBarang();
	        }
	}

	@Override
	public void hapusData() throws Exception {
		
	    System.out.println(" >> Hapus Data Barang");
        
        lihatData();
        
      if(kelolaBarang.hapus()>0) {
        	System.out.println("Berhasil hapus data");
        	Main.menuBarang();
        }
	}

	@Override
	public void cariData() throws Exception {
		
	      System.out.println(" >> Cari Data Barang");
	        ArrayList<Barang> listBarang =  kelolaBarang.cari();
	        
	        for( Barang barang : listBarang) {
	        	System.out.println("SKU : " + barang.getSKU());
	        	System.out.println("Nama Barang : " + barang.getNamaBarang());
	        	System.out.println("Stok : " + barang.getStok());
	        	System.out.println("Harga Beli : " + barang.getHargaBeli());
	        	System.out.println("Harga Jual : " + barang.getHargaJual());
	        	
	        	
	        } 
	        Main.menuBarang();
	}

	@Override
	public void lihatData() throws Exception {
		
		
		   System.out.println(" >> Tampilkan data Barang");
	        
	        ArrayList<Barang> listBarang =  kelolaBarang.getAll();
	        
	       
	        
	        for( Barang barang : listBarang) {
	        	System.out.println("\n===============================");
	        	System.out.println("SKU : " + barang.getSKU());
	        	System.out.println("Nama Barang : " + barang.getNamaBarang());
	        	System.out.println("Stok : " + barang.getStok());
	        	System.out.println("Harga Jual : " + barang.getHargaJual());
	        	System.out.println("Harga Beli : " + barang.getHargaBeli());
	        	System.out.println("===============================");
	        	
	        }

		
		
	}

}
