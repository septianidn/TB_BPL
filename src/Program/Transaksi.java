package Program;
public class Transaksi {

	String noresi;
	String sku;
	String username;
	String date; 
	String namaBarang; 
	Integer jumlah;
	Integer total;
	static Integer stock;
	
	public Transaksi() {
		
	}
	
	public Transaksi(String noresi, String username, String date, String nama, Integer jumlah, Integer total) {
		this.noresi=noresi;
		this.username=username;
		this.date=date;
		this.namaBarang=nama;
		this.jumlah=jumlah;
		this.total=total;
	}
	
	public Transaksi(String noresi, String nama, Integer jumlah) {
		this.noresi=noresi;
		this.namaBarang=nama;
		this.jumlah=jumlah;
	}
	
}