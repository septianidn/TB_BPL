package Program;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeMap;

public class KelolaTransaksi {
	

	static  String DB_URL = "jdbc:mysql://localhost:3306/tb_bpl";
	static String USERNAME = "root";
	static String PASSWORD = "";
	
	static Connection conn;
	static Statement stmt;
	static PreparedStatement statement;
	Transaksi transaksiData;
	KelolaBarang kelolabarang = new KelolaBarang();
	Date date = new Date();
	Scanner scn = new Scanner(System.in);

	public KelolaTransaksi(){
		
		try {
			
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		} catch (SQLException  e) {
			System.out.println("Koneksi tidak tersambung");
		}
		
	}
	
	
	//	Input data penjualan
	public void tambah() throws Exception {
		
		
		Integer tambah = 0; 
		Integer i;
		Integer total = 0;
		
		
		
		String ada;
		TreeMap<String, Integer> jual = new TreeMap<>();
		
		
		  ArrayList<Barang> listBarang =  kelolabarang.getAll();
	        

		System.out.println("\n\n--PENJUALAN--");
		
		String tanggal = String.format("%tF", date);
		
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
		
			
			
			
			
		
		
		
		
		
		try {			
			
			//	menyimpan data stock awal			
			String savedata = "SELECT * FROM barang";
			stmt = conn.createStatement();
			ResultSet rssave = stmt.executeQuery(savedata);
			
			while(rssave.next()) {
				Barang barangData = new Barang(
						rssave.getString("nama"),
						rssave.getInt("stock")
				);
			}
			
			//	cek nomor resi terakhir
			String ambilresi = "SELECT noresi FROM transaksi WHERE noresi IN (SELECT MAX(noresi) FROM transaksi)";
			stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(ambilresi);
			
			
			if(result.next()) {
				String dapatresi = result.getString("noresi");
				String noresi = dapatresi.substring(1);
				i = Integer.parseInt(noresi);
				i++;
			} else {
				i=1;
			}
			
			String noresifix = "T" + i.toString();
			
			
			String query = "INSERT INTO transaksi VALUES(?,?,?)";
			statement = conn.prepareStatement(query);
			statement.setString(1, noresifix);
			statement.setString(2, tanggal);
			statement.setString(3, dataUser.user); 
			statement.executeUpdate();
			
			for(Map.Entry list : jual.entrySet()) {
				
				//	ambil sku barang, stock, harga
				String ambilsku = "SELECT sku, stock, harga_jual FROM barang WHERE nama=?";
				statement = conn.prepareStatement(ambilsku);
				statement.setString(1, (String) list.getKey());
				ResultSet rs = statement.executeQuery();
				
				if(rs.next()) {
				String sql = "INSERT INTO detail_transaksi(sku, noresi, jumlah, harga) VALUES(?,?,?,?)";
				statement = conn.prepareStatement(sql);
				statement.setString(1, rs.getString("sku"));
				statement.setString(2, noresifix);
				statement.setInt(3, (Integer) list.getValue());
				statement.setInt(4, rs.getInt("harga_jual")*(Integer) list.getValue());
				tambah = statement.executeUpdate();
				
				total += (rs.getInt("harga_jual")*(Integer) list.getValue());
				
				//	mengurangi jumlah stock
				String stock = "UPDATE barang SET stock=? WHERE sku=?";
				statement = conn.prepareStatement(stock);
				statement.setInt(1, rs.getInt("stock")-(Integer) list.getValue());
				statement.setString(2, rs.getString("sku"));
				statement.executeUpdate();
				}
				
			}
			if (tambah==1) {
				jual.clear();
				System.out.println("Transaksi berhasil ditambahkan");
				Main.mainMenu();
			}
			
			System.out.println("Total belanja : " + total);
			
		} catch (SQLException e) {
			System.out.println("Terjadi kesalahan"+ e.getMessage());
		}
		
		}
		
		
		
	}
	
	
	//	Lihat data barang
	public void lihat(){
		
		Stack<Transaksi> listTransaksi = new Stack<>();
		
		try {
			
			String query = "SELECT t.noresi, t.username, t.tanggal, "
					+ "b.nama, dt.jumlah, dt.harga FROM transaksi "
					+ "AS t INNER JOIN detail_transaksi AS dt ON "
					+ "t.noresi=dt.noresi INNER JOIN barang AS b ON dt.sku=b.sku";
			stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(query);
			
			while(result.next()) {
				transaksiData = new Transaksi(
						result.getString("noresi"),
						result.getString("username"), 
						result.getString("tanggal"),
						result.getString("nama"),
						result.getInt("jumlah"),
						result.getInt("harga")
				);
				listTransaksi.add((Transaksi) transaksiData);
			}
			
		} catch (SQLException e) {
			System.out.println("Terjadi kesalahan");
		}
		System.out.println("\n\n--DAFTAR TRANSAKSI--");
		
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
        
        for(Transaksi list : listTransaksi) {
        	
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
	
	
	//	Hapus data transaksi
	public void hapus() throws Exception {
		
		System.out.println("\n\n--HAPUS DATA TRANSAKSI--");
		
		lihat();
		System.out.print("Noresi : ");
		String noresi = scn.next().toUpperCase();
		
		Integer hapus = 0;
		
		try {
			
			String query = "DELETE FROM detail_transaksi WHERE noresi=?";
			statement = conn.prepareStatement(query);
			statement.setString(1, noresi);
			statement.executeUpdate();
			
			String sql = "DELETE FROM transaksi WHERE noresi=?";
			statement = conn.prepareStatement(sql);
			statement.setString(1, noresi);
			hapus = statement.executeUpdate();
			
			if (hapus==1) {
				System.out.println("Transaksi berhasil dihapus");
				Main.mainMenu();
			}
			else {
			System.out.println("Noresi tidak ditemukan");
			Main.mainMenu();
			}
			
		} catch (SQLException e) {
			System.out.println("Terjadi kesalahan");
		}
		
		
	}
	
	
	//	Cari data transaksi
	public void cari(){
		
		System.out.println("\n\n--PENCARIAN DATA TRANSAKSI--");
		System.out.print("Username : ");
		String username = scn.next();
		
		ArrayList<Transaksi> cari = new ArrayList<>();
		
		try {
			
			String query = "SELECT t.noresi, t.username, t.tanggal, b.nama, "
					+ "dt.jumlah, dt.harga FROM transaksi AS t INNER JOIN detail_transaksi "
					+ "AS dt ON t.noresi=dt.noresi INNER JOIN barang AS b "
					+ "ON dt.sku=b.sku WHERE t.username LIKE ?";
			statement = conn.prepareStatement(query);
			statement.setString(1, "%" + username + "%");
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				transaksiData = new Transaksi(
						result.getString("noresi"),
						result.getString("username"), 
						result.getString("tanggal"),
						result.getString("nama"),
						result.getInt("jumlah"),
						result.getInt("harga")
				);
				cari.add(transaksiData);
			}
			
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
	        
	        for(Transaksi list : cari) {
	        	
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
			
		} catch (SQLException e) {
			System.out.println("Terjadi kesalahan");
		}
		
		
		
	}

}
