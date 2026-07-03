package kimvpro;

public class TopUpVP extends Layanan {

    // ATRIBUT khusus TopUpVP (ENCAPSULATION: private)
    private int jumlahVP;
    private static final double HARGA_PER_VP = 110.0;

    // CONSTRUCTOR
    public TopUpVP(String kodeTransaksi, String namaPelanggan, int jumlahVP) {
        super(kodeTransaksi, namaPelanggan); 
        this.jumlahVP = jumlahVP;
    }

    // ACCESSOR
    public int getJumlahVP() {
        return jumlahVP;
    }

    // MUTATOR
    public void setJumlahVP(int jumlahVP) {
        if (jumlahVP > 0) {
            this.jumlahVP = jumlahVP;
        }
    }

    // POLYMORPHISM
    @Override
    public double hitungTotalHarga() {
        double subtotal = jumlahVP * HARGA_PER_VP;
        double diskon = 0;

        if (jumlahVP >= 5000) {
            diskon = 0.10; 
        } else if (jumlahVP >= 2000) {
            diskon = 0.05; 
        } else if (jumlahVP >= 1000) {
            diskon = 0.02; 
        }

        harga = subtotal - (subtotal * diskon);
        return harga;
    }

    // POLYMORPHISM
    @Override
    public String cetakDetail() {
        return "[TOP UP VP VALORANT]\n" +
                "Kode Transaksi : " + getKodeTransaksi() + "\n" +
                "Pelanggan      : " + getNamaPelanggan() + "\n" +
                "Jumlah VP      : " + jumlahVP + " VP\n" +
                "Total Bayar    : " + formatRupiah(hitungTotalHarga());
    }
}
