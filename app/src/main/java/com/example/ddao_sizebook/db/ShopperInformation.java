package com.example.ddao_sizebook.db;

import android.provider.BaseColumns;

/**
 * Created by ddao on 2/5/17.
 */

public class ShopperInformation {

    public static final String DB_NAME = "com.example.ddao_sizebook.shopper_db";
    public static final int DB_VERSION = 1;

    public class ShopperEntry implements BaseColumns {
        public static final String TABLE = "Shoppers";

        public static final String Name = "Name";
        public static final String Date = "Date";
        public static final String Neck = "Neck";
        public static final String Bust = "Bust";
        public static final String Chest = "Chest";
        public static final String Waist = "Waist";
        public static final String Hip = "Hip";
        public static final String Inseam = "Inseam";
        public static final String Comment = "Comment";
    }
}
