package Program;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

public class UserFunction {
	static  String DB_URL = "jdbc:mysql://localhost:3306/tb_bpl";
	static String USERNAME = "root";
	static  String PASSWORD = "";
	
	static Connection conn;
	static Statement stmt;
	static PreparedStatement statement;
	dataUser datauser;
	User user;
	Login logIn;
	SignUp signup;
	String username;
	String password;
	Random random = new Random();
	Scanner scann = new Scanner(System.in);
	Date date = new Date();
	
	public UserFunction() throws SQLException{
		try {
			
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			}
		catch (SQLException  e) {
			System.out.println("Tidak terhubung"+ e.getMessage());
			e.printStackTrace();
		}
		finally {
			
		}
	}
	
	
	public Integer login() throws Exception {
		Integer login = 0;
		Integer coba = 0;
		
		do {
    	System.out.println("\n\n>>LOGIN>>");
    	
    	System.out.print("Username : ");
		this.username = scann.next();
		
		System.out.print("Password : ");
		this.password = scann.next();

		String tanggal = String.format("%tF", date);

		
	
		try {	
			String query = "SELECT * FROM user WHERE username=? AND password=?";
			statement = conn.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet rs = statement.executeQuery();
			
			if(rs.next()) {	
				try {
					String sql = "UPDATE user SET login_terakhir=? WHERE username=?";
					statement = conn.prepareStatement(sql);
					statement.setString(1, tanggal);
					statement.setString(2, username);
					login = statement.executeUpdate();
					
					if(login == 1) {
						dataUser.user = username;
						dataUser.pass = password;
						return login;
					}	
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
				
			} 
			else {
				coba++;
				System.out.println("Username dan password yang anda masukkan salah");
				if(coba==3) {
					resetPassword();
				} 
			
			}		
		
		} 
		catch (SQLException e) {
			System.out.println("Terjadi kesalahan"+ e.getMessage());
		}	
		
		} while (coba>=0 && coba<=2);
		return login;
	}
	
	
	public void resetPassword() throws Exception {
		try {
			String query = "SELECT * FROM user WHERE username=?";
			statement = conn.prepareStatement(query);
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			
		if(rs.next()) {
		String resetpass = "abcdefghijklmnopqrstuvwxyz1234567890";
		String randompass = "";
		int length = 8;
		Random random = new Random();
		char [] pass = new char [length];
		
		for (int a=0 ; a<length ; a++  ) {
			pass[a] = resetpass.charAt(random.nextInt(resetpass.length()));
		}
		
		for (int a=0 ; a<pass.length ; a++) {
			randompass += pass[a];
		}
		System.out.println("--------------------------------------");
		System.out.println("Password Anda telah di reset! ");
		System.out.println("--------------------------------------");
		
		
		
		String reset = "UPDATE user SET password=? WHERE username =?";
		statement = conn.prepareStatement(reset);
		statement.setString(1, randompass);
		statement.setString(2, this.username);
		statement.executeUpdate();
		
		
		}
	else {
	System.out.println("Username tidak ditemukan");		
		}
		Login.login();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
	
	public Integer register(dataUser datauser, String confirm) throws Exception {
		user = new User();
		logIn = new Login();
		signup = new SignUp();
		Integer register = 0;
		
		// pengecekan validasi email
		if (datauser.email.contains("@")) {
			
			//	pengecekan password
			if(datauser.password.equals(confirm)) {
				try {
					// pengecekan username sudah tersedia atau belum
					String cek = "SELECT username FROM user WHERE username = ?";
					statement = conn.prepareStatement(cek);
					statement.setString(1, datauser.username);
					ResultSet resultCek = statement.executeQuery();
							
					if (resultCek.next()) {
						System.out.println("Username sudah terdaftar");
						signup.tambahData();
					} 
					else{					
						try {
							String query = "INSERT INTO user VALUES (?,?,?,?)";
							statement = conn.prepareStatement(query);
							statement.setString(1, datauser.username);
							statement.setString(2, datauser.date);
							statement.setString(3, datauser.email);
							statement.setString(4, datauser.password);
							register = statement.executeUpdate();								
						} 
						catch (SQLException e) {							
							System.out.println("Terjadi kesalahan");					
						}
					}
				} 
				catch (SQLException e) {					
					System.out.println("Terjadi kesalahan");					
				}				
			} 
			else {				
				System.out.println("Password yang anda masukkan salah");
				signup.tambahData();			
			}			
		}
		else{					
			System.out.println("Masukkan email dengan benar");
			signup.tambahData();			
		}			
		return register;		
	}
	
	
	public Integer updateData(String passwordLama, String passwordBaru) throws Exception {
		
		Integer update = 0;
		User user = new User();
		
		if (passwordLama.equals(dataUser.pass)) {
			try {				
				String query = "UPDATE user SET password=? WHERE username=?";
				statement = conn.prepareStatement(query);
				statement.setString(1, passwordBaru);
				statement.setString(2, dataUser.user);
				update = statement.executeUpdate();
				
				if(update == 1) {
					dataUser.pass = passwordBaru;
				}
			} 
			catch (SQLException e) {
				System.out.println("Terjadi kesalahan");
			}
		}
		else{			
			System.out.println("Password yang anda masukkan salah");
			user.editData();		
		}	
		return update;		
	}
	

	public Integer deleteData() throws SQLException{	
		Integer delete = 0;		
		try {			
			String query = "DELETE FROM user WHERE username=?";
			statement = conn.prepareStatement(query);
			statement.setString(1, dataUser.user);
			delete = statement.executeUpdate();	
		}
		catch (SQLException e) {
			System.out.println("Terjadi kesalahan" +e.getMessage());
		}		
		return delete;		
	}
	

	public ArrayList <dataUser> search(String search){
		ArrayList<dataUser> searchList = new ArrayList<>();		
		try {			
			String query = "SELECT * FROM user WHERE username LIKE ?";
			statement = conn.prepareStatement(query);
			statement.setString(1, "%" + search + "%");
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				datauser = new dataUser(
						rs.getString("username"),
						rs.getString("login_terakhir"), 
						rs.getString("email"),
						rs.getString("password")
				);
				searchList.add(datauser);
			}		
		} 
		catch (SQLException e) {
			System.out.println("Terjadi kesalahan");
		}		
		return searchList;		
	}
	

	public TreeMap <String, dataUser> lihat(){		
		TreeMap <String, dataUser> userList = new TreeMap<>();		
		try {			
			String query = "SELECT * FROM user";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()){
                datauser = new dataUser(
                		rs.getString("username"),
                		rs.getString("login_terakhir"), 
                		rs.getString("email"),
                		rs.getString("password")
                );
                userList.put(rs.getString("username"), datauser);
			}			
		} 
		catch (SQLException e) {
			System.out.println("Terjadi kesalahan");
		}		
		return userList;		
	}	
}
