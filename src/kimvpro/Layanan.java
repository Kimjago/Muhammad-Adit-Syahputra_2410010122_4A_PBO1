package kimvpro;

import java.text.NumberFormat;
import java.util.Locale;

public abstract class Layanan {

    // ATRIBUT (ENCAPSULATION: private)
    private String kodeTransaksi;
    private String namaPelanggan;
    protected double harga; // protected supaya bisa diisi langsung oleh subclass (inheritance)

    // CONSTRUCTOR
    public Layanan(String kodeTransaksi, String namaPelanggan) {
        this.kodeTransaksi = kodeTransaksi;
        this.namaPelanggan = namaPelanggan;
        this.harga = 0;
    }

    // ACCESSOR (getter)
    public String getKodeTransaksi() {
        return kodeTransaksi;
    }

    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    public double getHarga() {
        return harga;
    }

    // MUTATOR (setter)
    public void setNamaPelanggan(String namaPelanggan) {
        if (namaPelanggan != null && !namaPelanggan.trim().isEmpty()) {
            this.namaPelanggan = namaPelanggan;
        }
    }

    // POLYMORPHISM
    public abstract double hitungTotalHarga();
    public abstract String cetakDetail();

    protected String formatRupiah(double nilai) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));
        return formatter.format(nilai);
    }
}
