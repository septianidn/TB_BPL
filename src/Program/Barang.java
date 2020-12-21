package Program;

import java.util.TreeMap;

public class Barang {

	private String SKU;
	private String namaBarang;
	private Integer stok;
	private Integer hargaBeli;
	private Integer hargaJual;
	TreeMap<String, Integer> stockBarang = new TreeMap<>();
	
	
	
	
public Barang() {
		
	}
	
	public Barang(String sku, String nama, Integer stock, Integer harga_beli, Integer harga_jual) {
		this.SKU=sku;
		this.namaBarang=nama;
		this.stok=stock;
		this.hargaBeli=harga_beli;
		this.hargaJual=harga_jual;
	}
	
	public Barang(String sku, String nama, Integer harga_jual) {
		this.SKU=sku;
		this.namaBarang=nama;
		this.hargaJual=harga_jual;
	}
	
	public Barang(String nama, Integer stock, Integer harga_beli, Integer harga_jual) {
		this.namaBarang=nama;
		this.stok=stock;
		this.hargaBeli=harga_beli;
		this.hargaJual=harga_jual;
	}
	
	public Barang(String nama, Integer jumlah) {
		this.stockBarang.put(nama, jumlah);
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
