package kimvpro;

public class SewaAkun extends Layanan {

    // ATRIBUT khusus SewaAkun (ENCAPSULATION: private)
    private String rankAkun;
    private int durasiJam;
    private double hargaPerJam;

    // CONSTRUCTOR
    public SewaAkun(String kodeTransaksi, String namaPelanggan, String rankAkun, int durasiJam, double hargaPerJam) {
        super(kodeTransaksi, namaPelanggan);
        this.rankAkun = rankAkun;
        this.durasiJam = durasiJam;
        this.hargaPerJam = hargaPerJam;
    }

    // ACCESSOR
    public String getRankAkun() {
        return rankAkun;
    }

    public int getDurasiJam() {
        return durasiJam;
    }

    // MUTATOR 
    public void setDurasiJam(int durasiJam) {
        if (durasiJam > 0) {
            this.durasiJam = durasiJam;
        }
    }

    // POLYMORPHISM
    @Override
    public double hitungTotalHarga() {
        double subtotal = durasiJam * hargaPerJam;

        // SELEKSI
        if (durasiJam > 10) {
            subtotal -= subtotal * 0.15;
        } else if (durasiJam > 5) {
            subtotal -= subtotal * 0.08;
        }

        harga = subtotal;
        return harga;
    }

    // POLYMORPHISM
    @Override
    public String cetakDetail() {
        return "[SEWA / PINJAM AKUN VALORANT]\n" +
                "Kode Transaksi : " + getKodeTransaksi() + "\n" +
                "Pelanggan      : " + getNamaPelanggan() + "\n" +
                "Rank Akun      : " + rankAkun + "\n" +
                "Durasi Sewa    : " + durasiJam + " jam\n" +
                "Total Bayar    : " + formatRupiah(hitungTotalHarga());
    }
}
