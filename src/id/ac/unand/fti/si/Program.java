package id.ac.unand.fti.si;

import java.sql.*;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		
		Scanner scn = new Scanner(System.in);
		Laporan laporan = new Laporan();

		try {

			static String DB_URL = "jdbc:mysql://localhost:3306/tb_bpl";
    			static String USERNAME = "root";
    			static String PASSWORD = "";
			
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

			if (connection != null) {
				System.out.println("OK");
			} else {
				System.out.println("NOT OK");
			}

			int cek = 0;

			do {
				System.out.println("\n||Laporan");
				System.out.println("\nSilakan Pilih Jenis Laporan");
				System.out.println("1. Laporan Penjualan");
				System.out.println("2. Laporan Keuntungan");

				System.out.print("\nPilihan Anda ? ");
				String pilih = scn.next();

				switch (pilih) {

				case "1":
					
					int cek1 = 0;
					
					do {
					System.out.println("\n||Laporan Penjualan");
					System.out.println("\nSilakan Pilih Jenis Pengelompokan Penjualan");
					System.out.println("1. Perhari");
					System.out.println("2. perbulan");
					
					System.out.print("\nPilihan Anda ? ");
					String pilih1 = scn.next();
					
					switch(pilih1) {
					
					case "1":
						cek1 = 1;
						laporan.laporanHari();
						break;
						
					case "2":
						cek1 = 1;
						laporan.laporan();
						break;
						
					default:
						cek1 = 0;
						System.out.print("\nPilihan Anda Salah, Silakan Pilih Lagi\n");
						break;
					
					}
					}while(cek1 == 0);
					
					System.out.print("\n\nKeluar Dari Aplikasi? (y/t) : ");
					String tanya = scn.next();
					
					switch(tanya) {
					
					case "y":
						cek = 1;	
						break;
						
					case "t":
						cek = 0;	
						break;
						
					}

					break;
				case "2":
					int cek2 = 0;
					
					do {
					System.out.println("\n||Laporan Keuntungan");
					System.out.println("\nSilakan Pilih Jenis Pengelompokan Keuntungan");
					System.out.println("1. Perhari");
					System.out.println("2. perbulan");
					
					System.out.print("\nPilihan Anda ? ");
					String pilih1 = scn.next();
					
					switch(pilih1) {
					
					case "1":
						cek2 = 1;
						laporan.keuntunganHari();
						break;
						
					case "2":
						cek2 = 1;
						laporan.keuntungan();
						break;
						
					default:
						cek2 = 0;
						System.out.print("\nPilihan Anda Salah, Silakan Pilih Lagi\n");
						break;
					
					}
					}while(cek2 == 0);

					
					
					System.out.print("\n\nKeluar Dari Aplikasi? (y/t) : ");
					String tanya1 = scn.next();
					
					switch(tanya1) {
					
					case "y":
						cek = 1;	
						break;
						
					case "t":
						cek = 0;	
						break;
						
					}
					
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
