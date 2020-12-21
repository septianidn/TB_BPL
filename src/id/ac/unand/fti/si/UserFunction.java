package id.ac.unand.fti.si;

import java.sql.*;
import java.util.ArrayList;
import java.util.TreeMap;

public class UserFunction {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/TB_BPL?serverTimezone=Asia/Jakarta";
	static final String USERNAME = "root";
	static final String PASSWORD = "";
	
	static Connection conn;
	static Statement stmt;
	static PreparedStatement statement;
	dataUser datauser;
	User user;
	Login logIn;
	SignUp signup;

	public UserFunction(){
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		} 
		catch (SQLException | ClassNotFoundException e) {
			System.out.println("Tidak terhubung");
		}
	}
	
	
	public Integer login(dataUser datauser) {
		Integer login = 0;
	
		try {	
			String query = "SELECT * FROM user WHERE username=? AND password=?";
			statement = conn.prepareStatement(query);
			statement.setString(1, datauser.username);
			statement.setString(2, datauser.password);
			ResultSet rs = statement.executeQuery();
			
			if(rs.next()) {	
				try {
					String sql = "UPDATE user SET login_terakhir=? WHERE username=?";
					statement = conn.prepareStatement(sql);
					statement.setString(1, datauser.date);
					statement.setString(2, datauser.username);
					login = statement.executeUpdate();
					
					if(login == 1) {
						dataUser.user = datauser.username;
						dataUser.pass = datauser.password;
					}	
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
				
			} 
			else {
				if(Login.ulang>0) {
					System.out.println("Username dan password yang anda masukkan salah");
				} 
				else if(Login.ulang <= 0) {
					System.out.println("Password anda telah direset");
					
					user = new User();
					logIn = new Login();
					
					try {	
						String reset = "UPDATE user SET password=? WHERE username =?";
						statement = conn.prepareStatement(reset);
						statement.setString(1, logIn.randomString());
						statement.setString(2, datauser.username);
						statement.executeUpdate();	
					} 
					catch (SQLException e) {
						System.out.println("Terjadi kesalahan");
					}	
				}
			}			
		} 
		catch (SQLException e) {
			System.out.println("Terjadi kesalahan");
		}				
		return login;
	}
	
	
	public Integer register(dataUser datauser, String confirm) {
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
	
	
	public Integer updateData(String passwordLama, String passwordBaru) {
		
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
	

	public Integer deleteData() {	
		Integer delete = 0;		
		try {			
			String query = "DELETE FROM user WHERE username=?";
			statement = conn.prepareStatement(query);
			statement.setString(1, dataUser.user);
			delete = statement.executeUpdate();	
		}
		catch (SQLException e) {
			System.out.println("Terjadi kesalahan");
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
