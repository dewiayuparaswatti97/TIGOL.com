package com.example.tigol;

public class Match {
    private int hargaVIP, hargaOutdoor, hargaReguler, homeTeam, awayTeam, stadium;
    private String key, jam, title, tanggal;

    public int getStadium() {
        return stadium;
    }
    public String getKey() {
        return key;
    }

    public int getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(int homeTeam) {
        this.homeTeam = homeTeam;
    }

    public int getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(int awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getHargaOutdoor() {
        return hargaOutdoor;
    }
    public int getHargaReguler() {
        return hargaReguler;
    }
    public int getHargaVIP() {
        return hargaVIP;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public Match(String key, int homeTeam, int awayTeam, int stadium, int hargaVIP, int hargaReguler, int hargaOutdoor, String jam, String title, String tanggal) {
        this.key = key;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.stadium = stadium;
        this.hargaVIP = hargaVIP;
        this.hargaReguler = hargaReguler;
        this.hargaOutdoor = hargaOutdoor;
        this.hargaVIP = hargaVIP;
        this.jam = jam;
        this.title = title;
        this.tanggal = tanggal;
    }
}
