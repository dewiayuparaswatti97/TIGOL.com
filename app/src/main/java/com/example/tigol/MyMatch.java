package com.example.tigol;

public class MyMatch {
    private int hargaVIP, hargaOutdoor, hargaReguler, homeTeam, awayTeam, stadium, seat, total;
    private String key, jam, title, tanggal, namaUser, userId, metode;
    private int verified;

    public int getSeat() {
        return seat;
    }

    public int getTotal() {
        return total;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public String getUserId() {
        return userId;
    }

    public String getMetode() {
        return metode;
    }

    public boolean isVerified() {
        return verified == 2;
    }

    public boolean isRejected() {
        return verified == 0;
    }

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

    public MyMatch(String key, String namaUser, String userId, String metode, int seat, int total, int status,
                   int homeTeam, int awayTeam, int stadium, int hargaVIP, int hargaReguler, int hargaOutdoor,
                   String jam, String title, String tanggal) {
        this.key = key;

        this.namaUser = namaUser;
        this.userId = userId;
        this.metode = metode;
        this.seat = seat;
        this.total = total;
        this.verified = status;

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
