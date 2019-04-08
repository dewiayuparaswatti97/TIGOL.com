package com.example.tigol;

import java.util.Date;

public class Match {

    public String club1, club2;
    public String imgClub1, imgClub2;
    public Date matchDate;
    public String stadium;

    public Match(String club1, String club2, String imgClub1, String imgClub2) {
        this.club1 = club1;
        this.club2 = club2;
        this.imgClub1 = imgClub1;
        this.imgClub2 = imgClub2;
    }

    public String getClub1() {
        return club1;
    }

    public void setClub1(String club1) {
        this.club1 = club1;
    }

    public String getClub2() {
        return club2;
    }

    public void setClub2(String club2) {
        this.club2 = club2;
    }

    public String getImgClub1() {
        return imgClub1;
    }

    public void setImgClub1(String imgClub1) {
        this.imgClub1 = imgClub1;
    }

    public String getImgClub2() {
        return imgClub2;
    }

    public void setImgClub2(String imgClub2) {
        this.imgClub2 = imgClub2;
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }
}


