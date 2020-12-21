package Program;
import java.sql.SQLException;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Login {
	
	
	dataUser datauser;
	static UserFunction userFunction;
	

	
	public Login() throws SQLException {
		try {
			userFunction = new UserFunction();
		} 
		catch (NullPointerException e) {
			System.out.println("Masukkan data terlebih dahulu");
		}
	}
	

	
    public static void login() throws Exception {
    	

		
		if(userFunction.login() == 1) {
			System.out.println("Login berhasil");
			Main.mainMenu();
		} 
			
	}	
}


