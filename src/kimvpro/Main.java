package kimvpro;

import java.util.Scanner;

public class Main {

    
    static String[] daftarRank        = {"Iron", "Bronze", "Silver", "Gold", "Platinum", "Diamond", "Ascendant", "Immortal", "Radiant"};
    static int[] stokAkun             = {5, 5, 4, 3, 3, 2, 2, 1, 1};
    static double[] hargaSewaPerJam   = {3000, 4000, 5000, 7000, 10000, 15000, 20000, 30000, 50000};

    
    static Layanan[] daftarTransaksi = new Layanan[100];
    static int jumlahTransaksi = 0;

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=======================================");
        System.out.println("   KimVPro - Top Up VP & Sewa Akun    ");
        System.out.println("            Valorant Service           ");
        System.out.println("=======================================");

        int pilihan;
        do {
            tampilkanMenu();
            pilihan = bacaPilihanMenu();

            switch (pilihan) {
                case 1:
                    prosesTopUpVP();
                    break;
                case 2:
                    prosesSewaAkun();
                    break;
                case 3:
                    tampilkanSemuaTransaksi();
                    break;
                case 4:
                    tampilkanStok();
                    break;
                case 0:
                    System.out.println("Terima kasih sudah menggunakan KimVPro!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid, coba lagi.");
            }
            System.out.println();
        } while (pilihan != 0);

        scanner.close();
    }

   
    static void tampilkanMenu() {
        System.out.println("---------------- MENU ----------------");
        System.out.println("1. Top Up VP Valorant");
        System.out.println("2. Sewa / Pinjam Akun Valorant");
        System.out.println("3. Lihat Semua Transaksi");
        System.out.println("4. Lihat Stok Akun");
        System.out.println("0. Keluar");
        System.out.print("Pilih menu: ");
    }

    // ERROR HANDLING
    static int bacaPilihanMenu() {
        int pilihan = -1;
        try {
            pilihan = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Input harus berupa angka!");
        }
        return pilihan;
    }

    // FITUR 1: TOP UP VP
    static void prosesTopUpVP() {
        try {
            System.out.print("Masukkan nama pelanggan : ");
            String nama = scanner.nextLine();

            System.out.print("Masukkan jumlah VP yang ingin dibeli : ");
            int jumlahVP = Integer.parseInt(scanner.nextLine().trim());

            // ERROR HANDLING
            if (jumlahVP <= 0) {
                throw new IllegalArgumentException("Jumlah VP harus lebih dari 0!");
            }

            String kode = "TVP-" + String.format("%03d", jumlahTransaksi + 1);

            // OBJECT
            TopUpVP topUp = new TopUpVP(kode, nama, jumlahVP);

            simpanTransaksi(topUp);

            System.out.println("\n--- NOTA PEMBAYARAN ---");
            System.out.println(topUp.cetakDetail());

        } catch (NumberFormatException e) {
            System.out.println("Jumlah VP harus berupa angka! Transaksi dibatalkan.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + " Transaksi dibatalkan.");
        }
    }

    // FITUR 2: SEWA AKUN
    static void prosesSewaAkun() {
        try {
            System.out.print("Masukkan nama pelanggan : ");
            String nama = scanner.nextLine();

            tampilkanStok();
            System.out.print("Pilih rank akun (ketik nama rank) : ");
            String rankDipilih = scanner.nextLine().trim();

            int indexRank = cariIndexRank(rankDipilih);
            if (indexRank == -1) {
                throw new IllegalArgumentException("Rank tidak ditemukan!");
            }

            // ERROR HANDLING
            if (stokAkun[indexRank] <= 0) {
                throw new StokTidakCukupException("Maaf, stok akun rank " + rankDipilih + " sedang habis!");
            }

            System.out.print("Masukkan durasi sewa (jam) : ");
            int durasi = Integer.parseInt(scanner.nextLine().trim());
            if (durasi <= 0) {
                throw new IllegalArgumentException("Durasi sewa harus lebih dari 0 jam!");
            }

            String kode = "SWA-" + String.format("%03d", jumlahTransaksi + 1);

            // OBJECT
            SewaAkun sewa = new SewaAkun(kode, nama, daftarRank[indexRank], durasi, hargaSewaPerJam[indexRank]);

            simpanTransaksi(sewa);
            stokAkun[indexRank]--; // stok berkurang setelah akun disewa

            System.out.println("\n--- NOTA PEMBAYARAN ---");
            System.out.println(sewa.cetakDetail());

        } catch (NumberFormatException e) {
            System.out.println("Durasi sewa harus berupa angka! Transaksi dibatalkan.");
        } catch (StokTidakCukupException | IllegalArgumentException e) {
            System.out.println(e.getMessage() + " Transaksi dibatalkan.");
        }
    }

    // PERULANGAN
    static int cariIndexRank(String rank) {
        for (int i = 0; i < daftarRank.length; i++) {
            if (daftarRank[i].equalsIgnoreCase(rank)) {
                return i;
            }
        }
        return -1;
    }

    static void simpanTransaksi(Layanan layanan) {
        if (jumlahTransaksi < daftarTransaksi.length) {
            daftarTransaksi[jumlahTransaksi] = layanan;
            jumlahTransaksi++;
        } else {
            System.out.println("Kapasitas penyimpanan transaksi penuh!");
        }
    }

    // FITUR 3: LIHAT SEMUA TRANSAKSI
    static void tampilkanSemuaTransaksi() {
        if (jumlahTransaksi == 0) {
            System.out.println("Belum ada transaksi.");
            return;
        }
        System.out.println("\n===== DAFTAR SEMUA TRANSAKSI =====");
        double totalPendapatan = 0;

        // PERULANGAN + POLYMORPHISM:
        for (int i = 0; i < jumlahTransaksi; i++) {
            System.out.println("---------------------------------------");
            System.out.println(daftarTransaksi[i].cetakDetail());
            totalPendapatan += daftarTransaksi[i].getHarga();
        }
        System.out.println("---------------------------------------");
        System.out.println("Total pendapatan KimVPro: Rp" + totalPendapatan);
    }

    // FITUR 4: LIHAT STOK AKUN
    static void tampilkanStok() {
        System.out.println("\n--- STOK AKUN VALORANT TERSEDIA ---");
        for (int i = 0; i < daftarRank.length; i++) {
            System.out.println((i + 1) + ". " + daftarRank[i] +
                    " - Stok: " + stokAkun[i] +
                    " - Harga/jam: Rp" + hargaSewaPerJam[i]);
        }
    }
}
