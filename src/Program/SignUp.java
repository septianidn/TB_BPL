package Program;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class SignUp extends User{
	Scanner scann = new Scanner(System.in);
	Date date = new Date();
	UserFunction userFunction;	
	Login login = new Login();
	dataUser datauser;
		
		public SignUp() throws SQLException {
			try {
				userFunction = new UserFunction();
			} 
			catch (NullPointerException e) {
				System.out.println("Masukkan data terlebih dahulu");
				}
			
		}

		// Register data
		public void tambahData() throws Exception{
			System.out.println("\n\n>>SIGN UP>>");

			System.out.print("Username : ");
			String username = scann.next();

			String dt = String.format("%tF", date);

			System.out.print("Email : ");
			String email = scann.next();

			System.out.print("Password : ");
			String password = scann.next();
			
			System.out.print("Konfirmasi password : ");
			String confirm = scann.next();
			
			datauser = new dataUser(username, dt, email, password);
			
			if(userFunction.register(datauser,confirm) == 1) {
				System.out.println("Akun berhasil dibuat");
				login.login();
			}
		}		
	}