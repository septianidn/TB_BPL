package id.ac.unand.fti.si;

import java.sql.*;
import java.util.Scanner;

public class Laporan {

	static String DB_URL = "jdbc:mysql://localhost:3306/tb_bpl";
  static String USERNAME = "root";
  static String PASSWORD = "";
  
	Scanner scn = new Scanner(System.in);

	public void laporan() {
		try {
			
			int penjualan=0;
			String bln;
			int cek1 = 0;
			String bulan = "";

			do {
				System.out.println("\n||Laporan Penjualan Perbulan");
				System.out.print("\nSilakan Inputkan Bulannya (1-12) : ");
				bln = scn.next();

				switch(bln) {
				
				case "1":
					cek1 = 1;
					bulan = "Januari";
					break;
				
				case "2":
					cek1 = 1;
					bulan = "Februari";
					break;
				
				case "3":
					cek1 = 1;
					bulan = "Maret";
					break;
					
				case "4":
					cek1 = 1;
					bulan = "April";
					break;
					
				case "5":
					cek1 = 1;
					bulan = "Mei";
					break;
					
				case "6":
					cek1 = 1;
					bulan = "Juni";
					break;
					
				case "7":
					cek1 = 1;
					bulan = "Juli";
					break;
					
				case "8":
					cek1 = 1;
					bulan = "Agustus";
					break;
					
				case "9":
					cek1 = 1;
					bulan = "September";
					break;
					
				case "10":
					cek1 = 1;
					bulan = "Oktober";
					break;
					
				case "11":
					cek1 = 1;
					bulan = "November";
					break;
					
				case "12":
					cek1 = 1;
					bulan = "Desember";
					break;
				
				default:
					cek1 = 0;
					System.out.print("\n---Input Salah!! Ulangi---\n");
					break;
				}

			} while (cek1 == 0);

			System.out.println("\n||Laporan Penjualan Bulan " + bulan);

			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			Statement statement = conn.createStatement();

			String sql = "SELECT detail_transaksi.id, transaksi.tanggal, detail_transaksi.sku, detail_transaksi.noresi, transaksi.username, detail_transaksi.jumlah, detail_transaksi.harga\r\n"
					+ "	FROM detail_transaksi,transaksi\r\n"
					+ "	WHERE transaksi.noresi = detail_transaksi.noresi AND EXTRACT (MONTH FROM tanggal) = " + bln;

			ResultSet result = statement.executeQuery(sql);

			System.out.println("\nid\ttanggal\t\tsku\tnoresi\tusername\tjumlah\tharga");

			while (result.next()) {

				System.out.print("\n" + result.getInt("id") + "\t");
				System.out.print(result.getDate("tanggal") + "\t");
				System.out.print(result.getString("sku") + "\t");
				System.out.print(result.getString("noresi") + "\t");
				System.out.print(result.getString("username") + "\t\t");
				System.out.print(result.getInt("jumlah") + "\t");
				System.out.print("Rp." + result.getInt("harga") + "\t");

				penjualan = penjualan + result.getInt("harga");
			}
			
			System.out.println("\n\nTotal Penjualan Pada Bulan ini Adalah : Rp." +penjualan);

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void keuntungan() {

		try {

			int keuntungan;
			int laba=0;
			int cek1 = 0;
			String bulan = "";
			String bln;

			do {
				System.out.println("\n||Laporan Keuntungan Perbulan");
				System.out.print("\nSilakan Inputkan Bulannya (1-12) : ");
				bln = scn.next();

				switch(bln) {
				
				case "1":
					cek1 = 1;
					bulan = "Januari";
					break;
				
				case "2":
					cek1 = 1;
					bulan = "Februari";
					break;
				
				case "3":
					cek1 = 1;
					bulan = "Maret";
					break;
					
				case "4":
					cek1 = 1;
					bulan = "April";
					break;
					
				case "5":
					cek1 = 1;
					bulan = "Mei";
					break;
					
				case "6":
					cek1 = 1;
					bulan = "Juni";
					break;
					
				case "7":
					cek1 = 1;
					bulan = "Juli";
					break;
					
				case "8":
					cek1 = 1;
					bulan = "Agustus";
					break;
					
				case "9":
					cek1 = 1;
					bulan = "September";
					break;
					
				case "10":
					cek1 = 1;
					bulan = "Oktober";
					break;
					
				case "11":
					cek1 = 1;
					bulan = "November";
					break;
					
				case "12":
					cek1 = 1;
					bulan = "Desember";
					break;
				
				default:
					cek1 = 0;
					System.out.print("\n---Input Salah!! Ulangi---\n");
					break;
				}
			} while (cek1 == 0);

			System.out.println("\n||Laporan Keuntungan Bulan " + bulan);

			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			Statement statement = conn.createStatement();

			String sql = "SELECT detail_transaksi.id, transaksi.tanggal, detail_transaksi.noresi, detail_transaksi.sku, barang.nama, detail_transaksi.jumlah, barang.harga_beli, barang.harga_jual\r\n" + 
					"	FROM detail_transaksi,transaksi,barang\r\n" + 
					"	WHERE transaksi.noresi = detail_transaksi.noresi AND detail_transaksi.sku = barang.sku AND EXTRACT (MONTH FROM tanggal) = "+bln;

			ResultSet result = statement.executeQuery(sql);

			System.out.println("\nid\ttanggal\t\tnoresi\tsku\tnamabarang\tjumlah\thargabeli\thargajual\tkeuntungan");

			while (result.next()) {

				System.out.print("\n" + result.getInt("id") + "\t");
				System.out.print(result.getDate("tanggal") + "\t");
				System.out.print(result.getString("noresi") + "\t");
				System.out.print(result.getString("sku") + "\t");
				System.out.print(result.getString("nama") + "\t\t");
				System.out.print(result.getInt("jumlah") + "\t");
				System.out.print("Rp." + result.getInt("harga_beli") + "\t\t");
				System.out.print("Rp." + result.getInt("harga_jual") + "\t\t");
				
				keuntungan = (result.getInt("jumlah"))*(result.getInt("harga_jual") - result.getInt("harga_beli"));
				
				System.out.print("Rp." +keuntungan);
				
				laba = laba + keuntungan;
			}
			
			System.out.println("\n\nTotal Keuntungan Pada Bulan ini Adalah : Rp." +laba);

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}

