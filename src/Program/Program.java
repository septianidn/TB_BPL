package Program;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {
	

	

	public static void main(String[] args) throws Exception  {

		Login login = new Login();
		SignUp signup = new SignUp();

		try {
			switch (Main.landingPage()) {
			case 1:
				login.login();
				break;
	
			case 2:
				signup.tambahData();
				break;
		
			default:
				System.out.println("Pilihan tidak tersedia");
				break;
			}
		} catch (InputMismatchException e) {
			System.out.println("Masukkan input dengan benar");
		}


	}

}
