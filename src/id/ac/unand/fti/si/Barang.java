package id.ac.unand.fti.si;

public class Barang {

	private String SKU;
	private String namaBarang;
	private Integer stok;
	private Integer hargaBeli;
	private Integer hargaJual;
	
	
	
	
	
	public Barang(String sKU, String namaBarang, Integer stok, Integer hargaBeli, Integer hargaJual) {
		super();
		SKU = sKU;
		this.namaBarang = namaBarang;
		this.stok = stok;
		this.hargaBeli = hargaBeli;
		this.hargaJual = hargaJual;
	}

	Barang(){
		
	}
	
	public String getSKU() {
		return SKU;
	}
	public void setSKU(String sKU) {
		SKU = sKU;
	}
	public String getNamaBarang() {
		return namaBarang;
	}
	public void setNamaBarang(String namaBarang) {
		this.namaBarang = namaBarang;
	}
	public int getStok() {
		return stok;
	}
	public void setStok(int stok) {
		this.stok = stok;
	}
	public int getHargaBeli() {
		return hargaBeli;
	}
	public void setHargaBeli(int hargaBeli) {
		this.hargaBeli = hargaBeli;
	}
	public int getHargaJual() {
		return hargaJual;
	}
	public void setHargaJual(int hargaJual) {
		this.hargaJual = hargaJual;
	}
	
	
}
