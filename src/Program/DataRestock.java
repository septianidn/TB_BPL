package Program;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataRestock {
	static final String DB_URL = "jdbc:mysql://localhost:3306/tb_bpl";
	static final String USERNAME = "root";
	static final String PASSWORD = "";
	
	static Connection conn;
	static Statement stmt;
	static PreparedStatement statement;
	
	public DataRestock(){
		
		try {
			
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			System.out.println("Koneksi tidak tersambung");
		}
		
	}
	
	
	public Integer restock(String sku, Integer restock) throws Exception {
		
		Integer tambah = 0;
		Restock stock = new Restock();
			
		try {
			
			//	Cek SKU barang
			String cek = "SELECT * FROM barang WHERE sku=?";
			statement = conn.prepareStatement(cek);
			statement.setString(1, sku);
			ResultSet result = statement.executeQuery();
			
			if(!result.next()) {
				
				System.out.println("SKU barang tidak ditemukan");
				stock.restock();
				
			} else {
				
				String query = "UPDATE barang SET stock=? WHERE sku=?";
				statement = conn.prepareStatement(query);
				statement.setInt(1, result.getInt("stock") + restock);
				statement.setString(2, sku);
				tambah = statement.executeUpdate();
				
			}
			
		} catch (SQLException e) {
			System.out.println("Terjadi kesalahan");
		}
		
		return tambah;
	}

}
