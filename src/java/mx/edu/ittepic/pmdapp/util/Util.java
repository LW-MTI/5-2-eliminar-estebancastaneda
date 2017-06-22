/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.ittepic.pmdapp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Esteban
 */
public class Util {

    public static Date strToDate(String date, String format) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.parse(date);
    }

    public static String dateToStr(Date fecha, String formato) {

        //DateFormat fecha = new SimpleDateFormat(format).format(date);
        //Formats a Date into a date/time string.
        //return fecha.format(date);
        return new SimpleDateFormat(formato).format(fecha);
    }

    // convert from UTF-8 -> internal Java String format
    public static String convertFromUTF8(String s) {
        String out = null;
        try {
            out = new String(s.getBytes("ISO-8859-1"), "UTF-8");
        } catch (java.io.UnsupportedEncodingException e) {
            return null;
        }
        return out;
    }

    // convert from internal Java String format -> UTF-8
    public static String convertToUTF8(String s) {
        String out = null;
        try {
            out = new String(s.getBytes("UTF-8"), "ISO-8859-1");
        } catch (java.io.UnsupportedEncodingException e) {
            return null;
        }
        return out;
    }

//    public static void main(String[] args) {
//        String xmlstring = "Здравей' хора";
//        String utf8string = StringHelper.convertToUTF8(xmlstring);
//        for (int i = 0; c < utf8string.length(); ++i) {
//            System.out.printf("%x ", (int) utf8string.charAt(c));
//        }
//    }
}
