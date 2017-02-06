package com.example.ddao_sizebook;

import org.w3c.dom.Comment;

import java.util.Date;

import static com.example.ddao_sizebook.db.ShopperInformation.ShopperEntry.Chest;
import static com.example.ddao_sizebook.db.ShopperInformation.ShopperEntry.Hip;
import static com.example.ddao_sizebook.db.ShopperInformation.ShopperEntry.Inseam;
import static com.example.ddao_sizebook.db.ShopperInformation.ShopperEntry.Name;
import static com.example.ddao_sizebook.db.ShopperInformation.ShopperEntry.Waist;

/**
 * Created by ddao on 2/5/17.
 */

public class Shopper {

    private String Name;
    private String Date;
    private int Neck;
    private int Bust;
    private int Chest;
    private int Waist;
    private int Hip;
    private int Inseam;
    private String Comment;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getNeck() {
        return Neck;
    }

    public void setNeck(int neck) {
        Neck = neck;
    }

    public int getBust() {
        return Bust;
    }

    public void setBust(int bust) {
        Bust = bust;
    }

    public int getChest() {
        return Chest;
    }

    public void setChest(int chest) {
        Chest = chest;
    }

    public int getWaist() {
        return Waist;
    }

    public void setWaist(int waist) {
        Waist = waist;
    }

    public int getHip() {
        return Hip;
    }

    public void setHip(int hip) {
        Hip = hip;
    }

    public int getInseam() {
        return Inseam;
    }

    public void setInseam(int inseam) {
        Inseam = inseam;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }
}
