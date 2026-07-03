# KimVPro - Aplikasi Top Up VP & Sewa Akun Valorant

Proyek ini adalah aplikasi sederhana berbasis console untuk layanan **Top Up VP (Valorant Points)** dan **Sewa/Pinjam Akun Valorant**, dibuat menggunakan Java sebagai UAS mata kuliah Pemrograman Berbasis Objek.

## Deskripsi

Aplikasi ini memungkinkan pengguna untuk melakukan transaksi top up VP maupun menyewa akun Valorant berdasarkan rank tertentu, lengkap dengan perhitungan diskon, harga sewa per jam, pengecekan stok akun, serta pencatatan riwayat seluruh transaksi.

Aplikasi ini mengimplementasikan berbagai konsep penting dalam pemrograman berorientasi objek (OOP) seperti Class, Object, Atribut, Constructor, Mutator, Accessor, Encapsulation, Inheritance, Polymorphism, Seleksi, Perulangan, IO Sederhana, Array, dan Error Handling.

## Penjelasan Kode

Berikut adalah bagian kode yang relevan dengan konsep OOP yang diterapkan:

1. **Class** adalah template atau blueprint dari object. Pada kode ini, `Layanan`, `TopUpVP`, `SewaAkun`, `StokTidakCukupException`, dan `Main` adalah contoh dari class.

```java
public abstract class Layanan {
    ...
}

public class TopUpVP extends Layanan {
    ...
}

public class SewaAkun extends Layanan {
    ...
}
```

2. **Object** adalah instance dari class. Pada kode ini, `new TopUpVP(kode, nama, jumlahVP)` dan `new SewaAkun(kode, nama, daftarRank[indexRank], durasi, hargaSewaPerJam[indexRank])` adalah contoh pembuatan object.

```java
TopUpVP topUp = new TopUpVP(kode, nama, jumlahVP);
SewaAkun sewa = new SewaAkun(kode, nama, daftarRank[indexRank], durasi, hargaSewaPerJam[indexRank]);
```

3. **Atribut** adalah variabel yang ada dalam class. Pada kode ini, `kodeTransaksi`, `namaPelanggan`, `harga` (di `Layanan`), serta `jumlahVP` (di `TopUpVP`) dan `rankAkun`, `durasiJam`, `hargaPerJam` (di `SewaAkun`) adalah contoh atribut.

```java
private String kodeTransaksi;
private String namaPelanggan;
protected double harga;
```

4. **Constructor** adalah method yang pertama kali dijalankan saat pembuatan object. Pada kode ini, constructor ada di dalam class `Layanan`, `TopUpVP`, dan `SewaAkun`.

```java
public Layanan(String kodeTransaksi, String namaPelanggan) {
    this.kodeTransaksi = kodeTransaksi;
    this.namaPelanggan = namaPelanggan;
    this.harga = 0;
}

public TopUpVP(String kodeTransaksi, String namaPelanggan, int jumlahVP) {
    super(kodeTransaksi, namaPelanggan);
    this.jumlahVP = jumlahVP;
}
```

5. **Mutator** atau setter digunakan untuk mengubah nilai dari suatu atribut. Pada kode ini, `setNamaPelanggan`, `setJumlahVP`, dan `setDurasiJam` adalah contoh method mutator.

```java
public void setNamaPelanggan(String namaPelanggan) {
    if (namaPelanggan != null && !namaPelanggan.trim().isEmpty()) {
        this.namaPelanggan = namaPelanggan;
    }
}

public void setDurasiJam(int durasiJam) {
    if (durasiJam > 0) {
        this.durasiJam = durasiJam;
    }
}
```

6. **Accessor** atau getter digunakan untuk mengambil nilai dari suatu atribut. Pada kode ini, `getKodeTransaksi`, `getNamaPelanggan`, `getHarga`, `getJumlahVP`, `getRankAkun`, dan `getDurasiJam` adalah contoh method accessor.

```java
public String getKodeTransaksi() {
    return kodeTransaksi;
}

public double getHarga() {
    return harga;
}
```

7. **Encapsulation** adalah konsep menyembunyikan data dengan membuat atribut menjadi private dan hanya bisa diakses melalui method. Pada kode ini, atribut `kodeTransaksi`, `namaPelanggan`, `jumlahVP`, `rankAkun`, `durasiJam`, dan `hargaPerJam` dienkapsulasi dan hanya bisa diakses melalui method getter dan setter.

```java
private String kodeTransaksi;
private int jumlahVP;
private String rankAkun;
```

8. **Inheritance** adalah konsep di mana sebuah class bisa mewarisi property dan method dari class lain. Pada kode ini, `TopUpVP` dan `SewaAkun` mewarisi `Layanan` dengan sintaks `extends`.

```java
public class TopUpVP extends Layanan {
    ...
}

public class SewaAkun extends Layanan {
    ...
}
```

9. **Polymorphism** adalah konsep di mana method dengan nama yang sama dapat berperilaku berbeda tergantung pada tipe objek yang memanggilnya. Pada kode ini, method abstrak `hitungTotalHarga()` dan `cetakDetail()` di class `Layanan` di-override secara berbeda oleh `TopUpVP` dan `SewaAkun`.

```java
// Di Layanan (abstract)
public abstract double hitungTotalHarga();
public abstract String cetakDetail();

// Di TopUpVP
@Override
public double hitungTotalHarga() {
    double subtotal = jumlahVP * HARGA_PER_VP;
    ...
}

// Di SewaAkun
@Override
public double hitungTotalHarga() {
    double subtotal = durasiJam * hargaPerJam;
    ...
}
```

10. **Seleksi** adalah statement kontrol yang digunakan untuk membuat keputusan berdasarkan kondisi. Pada kode ini, digunakan seleksi `if-else` untuk menentukan diskon top up VP dan potongan harga sewa akun, serta `switch` pada menu utama di `Main`.

```java
// Diskon Top Up VP
if (jumlahVP >= 5000) {
    diskon = 0.10;
} else if (jumlahVP >= 2000) {
    diskon = 0.05;
} else if (jumlahVP >= 1000) {
    diskon = 0.02;
}

// Menu utama
switch (pilihan) {
    case 1:
        prosesTopUpVP();
        break;
    case 2:
        prosesSewaAkun();
        break;
    ...
}
```

11. **Perulangan** adalah statement kontrol yang digunakan untuk menjalankan blok kode berulang kali. Pada kode ini, digunakan loop `do-while` untuk menampilkan menu secara terus-menerus, serta loop `for` untuk mencari index rank, menampilkan daftar transaksi, dan menampilkan stok akun.

```java
do {
    tampilkanMenu();
    pilihan = bacaPilihanMenu();
    ...
} while (pilihan != 0);

for (int i = 0; i < daftarRank.length; i++) {
    if (daftarRank[i].equalsIgnoreCase(rank)) {
        return i;
    }
}
```

12. **Input Output Sederhana** digunakan untuk menerima input dari user dan menampilkan output ke user. Pada kode ini, digunakan class `Scanner` untuk menerima input dan method `System.out.println` untuk menampilkan output.

```java
Scanner scanner = new Scanner(System.in);
System.out.print("Masukkan nama pelanggan : ");
String nama = scanner.nextLine();

System.out.println(topUp.cetakDetail());
```

13. **Array** adalah struktur data yang digunakan untuk menyimpan beberapa nilai dalam satu variabel. Pada kode ini, `daftarRank`, `stokAkun`, `hargaSewaPerJam`, dan `daftarTransaksi` adalah contoh penggunaan array.

```java
static String[] daftarRank      = {"Iron", "Bronze", "Silver", "Gold", "Platinum", "Diamond", "Ascendant", "Immortal", "Radiant"};
static int[] stokAkun           = {5, 5, 4, 3, 3, 2, 2, 1, 1};
static double[] hargaSewaPerJam = {3000, 4000, 5000, 7000, 10000, 15000, 20000, 30000, 50000};
static Layanan[] daftarTransaksi = new Layanan[100];
```

14. **Error Handling** digunakan untuk menangani error yang mungkin terjadi saat runtime. Pada kode ini, digunakan `try-catch` untuk menangani `NumberFormatException` dan `IllegalArgumentException`, serta dibuat custom exception `StokTidakCukupException` untuk menangani kasus stok akun habis.

```java
public class StokTidakCukupException extends Exception {
    public StokTidakCukupException(String pesan) {
        super(pesan);
    }
}
```

```java
try {
    ...
    if (stokAkun[indexRank] <= 0) {
        throw new StokTidakCukupException("Maaf, stok akun rank " + rankDipilih + " sedang habis!");
    }
    ...
} catch (NumberFormatException e) {
    System.out.println("Durasi sewa harus berupa angka! Transaksi dibatalkan.");
} catch (StokTidakCukupException | IllegalArgumentException e) {
    System.out.println(e.getMessage() + " Transaksi dibatalkan.");
}
```

## Fitur Aplikasi

1. **Top Up VP Valorant** — pembelian VP dengan diskon otomatis berdasarkan jumlah pembelian.
2. **Sewa/Pinjam Akun Valorant** — penyewaan akun berdasarkan rank dengan potongan harga untuk durasi sewa tertentu.
3. **Lihat Semua Transaksi** — menampilkan riwayat seluruh transaksi beserta total pendapatan.
4. **Lihat Stok Akun** — menampilkan daftar rank, stok, dan harga sewa per jam yang tersedia.

## Struktur File

```
kimvpro/
├── Layanan.java                    (abstract class)
├── TopUpVP.java                    (extends Layanan)
├── SewaAkun.java                   (extends Layanan)
├── StokTidakCukupException.java    (custom exception)
└── Main.java                       (entry point aplikasi)
```

## Tabel Self-Assessment Penilaian


| No | Materi | Bukti di Kode | Nilai Maks | Nilai Diri |
|---|---|---|---:|---:|
| 1 | Class | Layanan, TopUpVP, SewaAkun, Main, StokTidakCukupException | 5 | 5 |
| 2 | Object | `new TopUpVP(...)`, `new SewaAkun(...)` di `Main.java` | 5 | 5 |
| 3 | Atribut | `kodeTransaksi`, `namaPelanggan`, `jumlahVP`, `rankAkun`, dst. | 5 | 5 |
| 4 | Constructor | Constructor di `Layanan`, `TopUpVP`, `SewaAkun` (`super(...)`) | 5 | 5 |
| 5 | Mutator | `setNamaPelanggan()`, `setJumlahVP()`, `setDurasiJam()` | 5 | 5 |
| 6 | Accessor | `getKodeTransaksi()`, `getHarga()`, `getJumlahVP()`, dst. | 5 | 5 |
| 7 | Encapsulation | Semua atribut `private` atau `protected`, akses lewat getter/setter | 5 | 5 |
| 8 | Inheritance | `TopUpVP extends Layanan`, `SewaAkun extends Layanan` | 5 | 5 |
| 9 | Polymorphism | Override `hitungTotalHarga()` dan `cetakDetail()`, dipanggil lewat `Layanan[]` | 10 | 10 |
| 10 | Seleksi | `if-else` diskon, `switch-case` menu, validasi input | 5 | 5 |
| 11 | Perulangan | `do-while` menu utama, `for` cari rank dan cetak transaksi | 5 | 5 |
| 12 | IO Sederhana | `Scanner` input dan `System.out` output di `Main.java` | 10 | 10 |
| 13 | Array | `daftarRank`, `stokAkun`, `hargaSewaPerJam`, `daftarTransaksi` | 15 | 15 |
| 14 | Error Handling | `try-catch`, `NumberFormatException`, custom `StokTidakCukupException` | 15 | 15 |


## Identitas Pembuat

- Nama: `Muhammad Adit Syahputra`
- NPM: `2410010122`
- Kelas: `4A Reg TI Banjarbaru`
- Mata Kuliah: Pemrograman Berbasis Objek 1

## Video Penjelasan

- YouTube: `https://youtu.be/p4D204oc5NA?si=2rLru72boN6W4TOd`