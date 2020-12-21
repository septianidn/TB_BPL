package Program;
import java.sql.*;
import java.util.Scanner;

public class Laporan {

	static String DB_URL = "jdbc:mysql://localhost:3306/tb_bpl";
  	static String USERNAME = "root";
  	static String PASSWORD = "";
  
	Scanner scn = new Scanner(System.in);

	public void laporanHari() {

		try {
			int hitung = 0;
			int penjualan = 0;
			int bln, tgl;
			int cek = 0;

			do {
				System.out.println("\n||Laporan Penjualan Perhari");
				System.out.print("\nSilakan Inputkan Bulannya (1-12) : ");
				bln = scn.nextInt();
				System.out.print("Silakan Inputkan Tanggalnya (1-31) : ");
				tgl = scn.nextInt();

				if ((bln < 1 || bln > 12) || (tgl < 1 || tgl > 31)) {

					System.out.print("Ada yang Salah Dengan Tanggalnya, Ulangi!!\n");
					cek = 0;

				} else {

					cek = 1;

				}

			} while (cek == 0);

			Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			Statement statement = conn.createStatement();

			String sql = "SELECT detail_transaksi.id, transaksi.tanggal, detail_transaksi.sku, detail_transaksi.noresi, transaksi.username, detail_transaksi.jumlah, detail_transaksi.harga\r\n"
					+ "	FROM detail_transaksi,transaksi\r\n"
					+ "	WHERE transaksi.noresi = detail_transaksi.noresi AND tanggal = '2020-" + bln + "-" + tgl
					+ "' ORDER BY id ASC";

			ResultSet result = statement.executeQuery(sql);

			System.out.println("\n||Laporan Penjualan Pada Tanggal " + tgl + "/" + bln + "/2020");
			System.out.println("\nid\ttanggal\t\tsku\tnoresi\tusername\tjumlah\tharga");

			while (result.next()) {

				System.out.print("\n" + result.getInt("id") + "\t");
				System.out.print(result.getDate("tanggal") + "\t");
				System.out.print(result.getString("sku") + "\t");
				System.out.print(result.getString("noresi") + "\t");
				System.out.print(result.getString("username") + "\t\t");
				System.out.print(result.getInt("jumlah") + "\t");
				System.out.print("Rp." + result.getInt("harga") + "\t");

				hitung = hitung + 1;
				penjualan = penjualan + result.getInt("harga");
			}

			System.out.println("\n\nBanyak Tranksaksi Penjualan yang Terjadi Pada Tanggal " + tgl + "/" + bln
					+ "/2020 Adalah " + hitung + " Tranksaksi");
			System.out.println("Total Penjualan Pada Tanggal " + tgl + "/" + bln + "/2020 Adalah : Rp." + penjualan);

		} catch (SQLException e) {

			System.out.println("\nAda yang Salah Pada Inputan barusan!!");
		}

	}

	
	public void laporan() {
		try {

			int hitung = 0;
			int penjualan = 0;
			String bln;
			int cek1 = 0;
			String bulan = "";

			do {
				System.out.println("\n||Laporan Penjualan Perbulan");
				System.out.print("\nSilakan Inputkan Bulannya (1-12) : ");
				bln = scn.next();

				switch (bln) {

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

			Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			Statement statement = conn.createStatement();

			String sql = "SELECT detail_transaksi.id, transaksi.tanggal, detail_transaksi.sku, detail_transaksi.noresi, transaksi.username, detail_transaksi.jumlah, detail_transaksi.harga\r\n"
					+ "	FROM detail_transaksi,transaksi\r\n"
					+ "	WHERE transaksi.noresi = detail_transaksi.noresi AND MONTH(transaksi.tanggal) = '" + bln +"' ORDER BY tanggal ASC";

			ResultSet result = statement.executeQuery(sql);

			System.out.println("\n||Laporan Penjualan Bulan " + bulan);
			System.out.println("\nid\ttanggal\t\tsku\tnoresi\tusername\tjumlah\tharga");

			while (result.next()) {

				System.out.print("\n" + result.getInt("id") + "\t");
				System.out.print(result.getDate("tanggal") + "\t");
				System.out.print(result.getString("sku") + "\t");
				System.out.print(result.getString("noresi") + "\t");
				System.out.print(result.getString("username") + "\t\t");
				System.out.print(result.getInt("jumlah") + "\t");
				System.out.print("Rp." + result.getInt("harga") + "\t");

				hitung = hitung + 1;
				penjualan = penjualan + result.getInt("harga");
			}

			System.out.println(
					"\n\nBanyak Tranksaksi Penjualan yang Terjadi Pada Bulan ini Adalah " + hitung + " Tranksaksi");
			System.out.println("Total Penjualan Pada Bulan ini Adalah : Rp." + penjualan);

		} catch (SQLException e) {

			System.out.println("\nAda yang Salah Pada Inputan barusan!!" + e.getMessage());
		}
	}

	public void keuntunganHari() {

		try {
			
		int hitung =0;	
		int laba =0;
		int bln, tgl,keuntungan;
		int cek = 0;

		do {
			System.out.println("\n||Laporan Keuntungan Perhari");

			System.out.print("\nSilakan Inputkan Bulannya (1-12) : ");
			bln = scn.nextInt();
			System.out.print("Silakan Inputkan Tanggalnya (1-31) : ");
			tgl = scn.nextInt();

			if ((bln < 1 || bln > 12) || (tgl < 1 || tgl > 31)) {

				System.out.print("Ada yang Salah Dengan Tanggalnya, Ulangi!!\n");
				cek = 0;

			} else {

				cek = 1;

			}

		} while (cek == 0);

		Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		Statement statement = conn.createStatement();
		
		String sql = "SELECT detail_transaksi.id, transaksi.tanggal, detail_transaksi.noresi, detail_transaksi.sku, barang.nama, detail_transaksi.jumlah, barang.harga_beli, barang.harga_jual\r\n"
				+ "	FROM detail_transaksi,transaksi,barang\r\n"
				+ "	WHERE transaksi.noresi = detail_transaksi.noresi AND detail_transaksi.sku = barang.sku AND tanggal = '2020-" + bln + "-" + tgl+"' ORDER BY id ASC";
		
		ResultSet result = statement.executeQuery(sql);
		
		System.out.println("\n||Laporan Keuntungan Pada Tanggal " + tgl + "/" + bln + "/2020");
		System.out.println("\nid\ttanggal\t\tnoresi\tsku\tnamabarang\tjumlah\thargabeli\thargajual\tkeuntungan");

		while (result.next()) {

			System.out.print("\n" + result.getInt("id") + "\t");
			System.out.print(result.getDate("tanggal") + "\t");
			System.out.print(result.getString("noresi") + "\t");
			System.out.print(result.getString("sku") + "\t");
			
			if(result.getString("nama").length()<6) {
				System.out.print(result.getString("nama") + "\t\t");
			}
			else {
				System.out.print(result.getString("nama") + "\t");
			}
			System.out.print(result.getInt("jumlah") + "\t");
			System.out.print("Rp." + result.getInt("harga_beli") + "\t\t");
			System.out.print("Rp." + result.getInt("harga_jual") + "\t\t");

			keuntungan = (result.getInt("jumlah")) * (result.getInt("harga_jual") - result.getInt("harga_beli"));

			System.out.print("Rp." + keuntungan);

			hitung = hitung + 1;
			laba = laba + keuntungan;
		}
		
		System.out.println("\n\nBanyak Tranksaksi Penjualan yang Terjadi Pada Tanggal " + tgl + "/" + bln
				+ "/2020 Adalah " + hitung + " Tranksaksi");
		System.out.println("Total Keuntungan Pada Tanggal "+tgl+"/"+bln+"/2020 Adalah : Rp." + laba);

		
		} catch (SQLException e) {
		
			System.out.println("\nAda yang Salah Pada Inputan barusan!!");
		}

	}

	
	public void keuntungan() {

		try {

			int hitung =0;
			int keuntungan;
			int laba = 0;
			int cek1 = 0;
			String bulan = "";
			String bln;

			do {
				System.out.println("\n||Laporan Keuntungan Perbulan");
				System.out.print("\nSilakan Inputkan Bulannya (1-12) : ");
				bln = scn.next();

				switch (bln) {

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

			Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			Statement statement = conn.createStatement();

			String sql = "SELECT detail_transaksi.id, transaksi.tanggal, detail_transaksi.noresi, detail_transaksi.sku, barang.nama, detail_transaksi.jumlah, barang.harga_beli, barang.harga_jual\r\n"
					+ "	FROM detail_transaksi,transaksi,barang\r\n"
					+ "	WHERE transaksi.noresi = detail_transaksi.noresi AND detail_transaksi.sku = barang.sku AND MONTH(transaksi.tanggal) = "
					+ bln + " ORDER BY tanggal ASC";

			ResultSet result = statement.executeQuery(sql);

			System.out.println("\n||Laporan Keuntungan Bulan " + bulan);
			System.out.println("\nid\ttanggal\t\tnoresi\tsku\tnamabarang\tjumlah\thargabeli\thargajual\tkeuntungan");

			while (result.next()) {

				System.out.print("\n" + result.getInt("id") + "\t");
				System.out.print(result.getDate("tanggal") + "\t");
				System.out.print(result.getString("noresi") + "\t");
				System.out.print(result.getString("sku") + "\t");
				if(result.getString("nama").length()<6) {
					System.out.print(result.getString("nama") + "\t\t");
				}
				else {
					System.out.print(result.getString("nama") + "\t");
				}
				System.out.print(result.getInt("jumlah") + "\t");
				System.out.print("Rp." + result.getInt("harga_beli") + "\t\t");
				System.out.print("Rp." + result.getInt("harga_jual") + "\t\t");

				keuntungan = (result.getInt("jumlah")) * (result.getInt("harga_jual") - result.getInt("harga_beli"));
				hitung = hitung + 1;
				
				System.out.print("Rp." + keuntungan);

				laba = laba + keuntungan;
			}

			System.out.println(
					"\n\nBanyak Tranksaksi Penjualan yang Terjadi Pada Bulan ini Adalah " + hitung + " Tranksaksi");
			System.out.println("Total Keuntungan Pada Bulan ini Adalah : Rp." + laba);

		} catch (SQLException e) {

			System.out.println("\nAda yang Salah Pada Inputan barusan!!"+ e.getMessage());
		}

	}

}

