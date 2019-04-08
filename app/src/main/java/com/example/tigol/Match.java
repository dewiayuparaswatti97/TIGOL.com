package com.example.tigol;

public class Match {
private String homeTeam, awayTeam, harga,jam,nama,tanggal;

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public Match(String homeTeam, String awayTeam, String harga, String jam, String nama, String tanggal) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.harga = harga;
        this.jam = jam;
        this.nama = nama;
        this.tanggal = tanggal;
    }
}
