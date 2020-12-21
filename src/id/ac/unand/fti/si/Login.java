package id.ac.unand.fti.si;

import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Login {
	Scanner scann = new Scanner(System.in);
	Date date = new Date();
	dataUser datauser;
	UserFunction userFunction;
	Random random = new Random();
	static Integer ulang = 2;
	private String username1 = null, username2 = null, username3 = null;
	
	public Login() {
		try {
			userFunction = new UserFunction();
		} 
		catch (NullPointerException e) {
			System.out.println("Masukkan data terlebih dahulu");
		}
	}
	
	public String randomString() {
		
		char[] karakter = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder builder = new StringBuilder();
		String hasilRandom = null;
		
		for(Integer i=0; i<15; i++) {
			Character chara = karakter[random.nextInt(karakter.length)];
			builder.append(chara);
		}
		
		hasilRandom = builder.toString();
		builder.delete(0, 15);
		
		return hasilRandom;
		
	}
	
    public void login() {
    	
    	System.out.println("\n\n>>LOGIN>>");
    	
    	System.out.print("Username : ");
		String username = scann.next();
		
		System.out.print("Password : ");
		String password = scann.next();

		String dt = String.format("%tF", date);

		datauser = new dataUser(username, dt, password);
		
		//	cek username yang diinputkan	
		if(ulang==2) {
			username1 = username;
		} 
		else if(ulang==1) {
			username2 = username;
			if(!username2.equals(username1)) {
				username1=username;
				ulang = 2;
				username2=null;
			}
		} 
		else if(ulang==0) {
			username3 = username;
			if(!username3.equals(username2)) {
				ulang = 2;
				username1=username;
				username2=null;
				username3=null;
			}
		}
		
		if(userFunction.login(datauser) == 1) {
			System.out.println("Login berhasil");
			Menu.menuDashboard();
		} 
		else {
			while(ulang>0) {
				ulang--;
				login();
			}			
		}		
	}	
}


