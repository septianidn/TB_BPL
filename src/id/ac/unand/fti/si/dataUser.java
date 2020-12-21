package id.ac.unand.fti.si;

public class dataUser {
	String username;
	String password;
	String email;
	String date;
	static String user;
	static String pass;
	
	public dataUser() {
		
	}
	
	public dataUser(String username, String date, String password) {
		this.username = username;
		this.date = date;
		this.password = password;
	}
	
	public dataUser(String username, String date, String email, String password) {
		this.username = username;
		this.date = date;
		this.email = email;
		this.password = password;
	}
}
