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

/**
 * Class to contain information to pass to ShopperAdapter class, in order to be displayed on the list View
 * Only contain getters and setters, its purpose is just to pass information to the Adapter
 * Every member variable is String since we have to deal with no information sometimes,
 * in which some variable might be empty because users didn't input them
 */
public class Shopper {

    private String Name;
    private String Date;
    private String Neck;
    private String Bust;
    private String Chest;
    private String Waist;
    private String Hip;
    private String Inseam;
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

    public String getNeck() {
        return Neck;
    }

    public void setNeck(String neck) {
        Neck = neck;
    }

    public String getBust() {
        return Bust;
    }

    public void setBust(String bust) {
        Bust = bust;
    }

    public String getChest() {
        return Chest;
    }

    public void setChest(String chest) {
        Chest = chest;
    }

    public String getWaist() {
        return Waist;
    }

    public void setWaist(String waist) {
        Waist = waist;
    }

    public String getHip() {
        return Hip;
    }

    public void setHip(String hip) {
        Hip = hip;
    }

    public String getInseam() {
        return Inseam;
    }

    public void setInseam(String inseam) {
        Inseam = inseam;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }
}
