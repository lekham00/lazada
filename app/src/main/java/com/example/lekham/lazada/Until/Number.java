package com.example.lekham.lazada.Until;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by Le Kham on 6/19/2017.
 */

public class Number {
    public static String formatPrice(int price) {
        NumberFormat numberFormat = new DecimalFormat("###,###");
        return numberFormat.format(price);
    }
}
