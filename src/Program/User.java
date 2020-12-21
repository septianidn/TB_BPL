package Program;
import java.util.Scanner;
import java.util.TreeMap;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class User implements kelola {
	Scanner scann = new Scanner(System.in);
	Date date = new Date();
	dataUser datauser;
	UserFunction userFunction;
	Login login = new Login();
	
	public User() throws SQLException {
		try {
			userFunction = new UserFunction();
		} 
		catch (NullPointerException e) {
			System.out.println("Masukkan data terlebih dahulu");
		}
	}

	// Register data
	@Override
	public void tambahData() throws Exception{

	}

	@Override
	public void editData() throws Exception{
		System.out.println("\n\n>>UBAH PASSWORD>>");
		
		System.out.print("Password lama : ");
		String passwordLama = scann.next();

		System.out.print("Password baru :");
		String passwordBaru = scann.next();
		
		if(userFunction.updateData(passwordLama, passwordBaru) == 1) {
			System.out.println("Password berhasil diperbarui");
			Main.mainMenu();
		} 
		else {
			System.out.println("Gagal memperbarui password");
			editData();
		}
	}

	
	@Override
	public void hapusData() throws Exception{
		System.out.println("\n\n>>HAPUS DATA USER<<");

		System.out.print("Apakah anda yakin untuk menghapus akun anda ?..(y/t)  ");
		String lanjut = scann.next();

		if (lanjut.equalsIgnoreCase("y")) {
			if(userFunction.deleteData() == 1) {
				System.out.println("Akun berhasil di hapus\n\n");
				Program.main(null);
			}
		} 
		else{
			Main.mainMenu();
		}
	}


	@Override
	public void cariData(){
		System.out.println("\n\n>>CARI DATA USER<<");

		System.out.print("Masukkan username : ");
		String search = scann.next();

		ArrayList <dataUser> searchList = userFunction.search(search);
		
		System.out.print("Username");
        System.out.print("\t");
        System.out.print("Login terakhir");
        System.out.print("\t\t");
        System.out.println("Email");
		
		for(dataUser datauseer : searchList){
            System.out.print(datauseer.username);
            System.out.print("\t\t");
            System.out.print(datauseer.date);
            System.out.print("\t\t");
            System.out.println(datauseer.email);
        }	
		Main.tunggu();
	}


	@Override
	public void lihatData(){
		System.out.println("\n\n>>DAFTAR USER>>");

		TreeMap <String, dataUser> userList = userFunction.lihat();
		
		System.out.print("Username");
        System.out.print("\t");
        System.out.print("Login terakhir");
        System.out.print("\t\t");
        System.out.println("Email");
        
        for(Map.Entry list : userList.entrySet()){
            dataUser listUser = (dataUser) list.getValue();
            
            System.out.print(list.getKey());
            System.out.print("\t\t");
            System.out.print(listUser.date);
            System.out.print("\t\t");
            System.out.println(listUser.email);
        }		
		Main.tunggu();
	}	
}

