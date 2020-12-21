package Program;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	static Date date = new Date();
	static Scanner scanner = new Scanner(System.in);
	
	public static Integer landingPage() {
		
		Integer pilihan = 0;
		
		System.out.println("     Toko Selamat Pagi");
		System.out.println("============================");
		System.out.println(date+"\n");
		System.out.println("1. Login");
		System.out.println("2. Register");
		System.out.print("Tentukan pilihanmu : ");
		pilihan = scanner.nextInt();
		
		return pilihan;
	}
	
	
	public static void mainMenu() throws Exception {
		
		Transaksi transaksi = new Transaksi();
		
		System.out.println("\n\n--DASHBOARD--");
		System.out.println("1. Pengaturan");
		System.out.println("2. Data Master Transaksi");
		System.out.println("3. Data Master Barang");
		System.out.println("4. Penjualan");
		System.out.println("5. Laporan");
		System.out.println("6. Logout");
		System.out.print("Tentukan pilihanmu : ");
		
		try {
			
			Integer pilihan = scanner.nextInt();
			
			switch(pilihan) {
				case 1:
					pengaturanAkun();
					break;
					
				case 2:
					menuTransaksi();
					break;
					
				case 3:
					menuBarang();
					break;
					
				case 4: 
					transaksi.tambahData();
					break;
					
				case 5:
					menuLaporan();
					break;
					
				case 6:
					Login.ulang=2;
					System.out.println("\n");
					Program.main(null);
					break;
					
				default:
					System.out.println("Pilihan tidak tersedia");
					mainMenu();
					break;
			}
			
		} catch (InputMismatchException e) {
			System.out.println("Masukkan input dengan benar");
		
		}
		
	}
	
		
	public static void pengaturanAkun() throws Exception {
		
		User user = new User();
		
		System.out.println("\n\n--PENGATURAN AKUN--");
		System.out.println("1. Ubah Password");
		System.out.println("2. Hapus Akun");
		System.out.println("3. Lihat Daftar User");
		System.out.println("4. Cari User");
		System.out.println("0. Menu Utama");
		System.out.print("Tentukan pilihanmu : ");
		
		try {
			Integer pilihan = scanner.nextInt();
			
			switch(pilihan) {
			case 1:
				user.editData();
				break;
				
			case 2:
				user.hapusData();
				break;
				
			case 3:
				user.lihatData();
				break;
				
			case 4:
				user.cariData();
				break;
			case 0:
				mainMenu();
				break;
				
			default:
				System.out.println("Pilihan tidak tersedia");
				mainMenu();
				break;
			}
			
		} catch(InputMismatchException e) {
			System.out.println("Masukkan input dengan benar");
		}
		
		
	}
	
	
	public static void tunggu() {
		
		InputStreamReader streamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(streamReader);
        
        try {
        	System.out.print("\nKlik enter untuk melanjutkan");
        	String lanjut = (bufferedReader.readLine());
    		mainMenu();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void menuBarang() throws Exception {
		
		BarangFunction barang = new BarangFunction();
		Restock stock = new Restock();
		
		barang.lihatData();
		
		System.out.println("\n\n--DATA MASTER BARANG--");
		System.out.println("1. Tambah data barang");
		System.out.println("2. Ubah data barang");
		System.out.println("3. Hapus data barang");
		System.out.println("4. Cari data barang");
		System.out.println("5. Restock barang");
		System.out.println("0. Menu Utama");
		System.out.print("Tentukan pilihanmu : ");
		
		try {
			
			Integer pilihan = scanner.nextInt();
			switch(pilihan) {
			
			case 1:
				barang.tambahData();
				break;
				
			case 2:
				barang.editData();
				break;
				
			case 3:
				barang.hapusData();
				break;
				
			case 4: 
				barang.cariData();
				break;
				
			case 5:
				stock.restock();
				break;
			case 0:
				mainMenu();
				break;
				
			default:
				System.out.println("Pilihan tidak tersedia");
				mainMenu();
				break;
			
			}
			
		} catch (InputMismatchException e) {
			System.out.println("Masukkan input dengan benar");
		}
		
	}
	
	
	public static void menuTransaksi() throws Exception {
		
		Transaksi transaksi = new Transaksi();
		transaksi.lihatData();
		
		System.out.println("\n\n--DATA MASTER TRANSAKSI--");
		System.out.println("1. Hapus data transaksi");
		System.out.println("2. Cari data transaksi");
		System.out.println("0. Menu Utama");
		System.out.print("Tentukan pilihanmu : ");
		
		try {
			
			Integer pilihan = scanner.nextInt();
			switch(pilihan) {
			
		
			case 1:
				transaksi.hapusData();
				break;
				
			case 2:
				transaksi.cariData();
				break;
			case 0:
				menuBarang();
				break;
				
			default :

				break;
			
			}
			
		} catch (InputMismatchException e) {
			System.out.println("Masukkan input dengan benar");
		}
		
	}
	
	public static void menuLaporan() throws Exception {
		
		 Laporan laporan = new Laporan();
  		try {

			String DB_URL = "jdbc:mysql://localhost:3306/tb_bpl";
    			String USERNAME = "root";
    			String PASSWORD = "";
			
			Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

//			if (connection != null) {
//				System.out.println("OK");
//			} else {
//				System.out.println("NOT OK");
//			}

			int cek = 0;

			do {
				System.out.println("\n||Laporan");
				System.out.println("\nSilakan Pilih Jenis Laporan");
				System.out.println("1. Laporan Penjualan");
				System.out.println("2. Laporan Keuntungan");
				System.out.println("0. Menu Utama");
				

				System.out.print("\nPilihan Anda ? ");
				String pilih = scanner.next();

				switch (pilih) {

				case "1":
					
					int cek1 = 0;
					
					do {
					System.out.println("\n||Laporan Penjualan");
					System.out.println("\nSilakan Pilih Jenis Pengelompokan Penjualan");
					System.out.println("1. Perhari");
					System.out.println("2. perbulan");
					System.out.println("0. Menu Laporan");
					
					System.out.print("\nPilihan Anda ? ");
					String pilih1 = scanner.next();
					
					switch(pilih1) {
					
					case "1":
						cek1 = 1;
						laporan.laporanHari();
						break;
						
					case "2":
						cek1 = 1;
						laporan.laporan();
						break;
					case "0":
						menuLaporan();
						break;
						
					default:
						cek1 = 0;
						System.out.print("\nPilihan Anda Salah, Silakan Pilih Lagi\n");
						break;
					
					}
					}while(cek1 == 0);
					
				
					menuLaporan();
					break;
					
				case "2":
					int cek2 = 0;
					
					do {
					System.out.println("\n||Laporan Keuntungan");
					System.out.println("\nSilakan Pilih Jenis Pengelompokan Keuntungan");
					System.out.println("1. Perhari");
					System.out.println("2. perbulan");
					System.out.println("0. Menu Laporan");
					
					System.out.print("\nPilihan Anda ? ");
					String pilih1 = scanner.next();
					
					switch(pilih1) {
					
					case "1":
						cek2 = 1;
						laporan.keuntunganHari();
						break;
						
					case "2":
						cek2 = 1;
						laporan.keuntungan();
						break;
					case "0":
						menuLaporan();
						break;
						
					default:
						cek2 = 0;
						System.out.print("\nPilihan Anda Salah, Silakan Pilih Lagi\n");
						break;
					
					}
					}while(cek2 == 0);

					
					
					menuLaporan();
					
					break;
					
				case "0":
					mainMenu();
					break;
					
				default:
					cek = 0;
					System.out.print("\nPilihan Anda Salah, Silakan Pilih Lagi\n");
					break;

				}
			} while (cek == 0);
			
			System.out.println("\nBye");

			
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
