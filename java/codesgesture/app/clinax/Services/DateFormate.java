package codesgesture.app.clinax.Services;

import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateFormate {

    public static String getYear(String raw) {
        String inputPattern = "dd-MMM-yyyy";
        String outputPattern = "yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        Date date = null;
        String str = null;
        try {
            date = inputFormat.parse(raw);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
       return  str;
    }

    public static String getMonth(String raw) {
        String inputPattern = "dd-MMM-yyyy";
        String outputPattern = "MM";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        Date date = null;
        String str = null;
        try {
            date = inputFormat.parse(raw);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  str;
    }

    public static String getDay(String raw) {
        String inputPattern = "dd-MMM-yyyy";
        String outputPattern = "dd";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        Date date = null;
        String str = null;
        try {
            date = inputFormat.parse(raw);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  str;
    }

    public static String getCurrentTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getTimeFromAndroid() {
        Date dt = new Date();
        int hours = dt.getHours();
        int min = dt.getMinutes();
        String state = "";

        if(hours>=1 || hours<=12){
            state="Good Morning";
           // Toast.makeText(this, "Good Morning", Toast.LENGTH_SHORT).show();
        }else if(hours>=12 || hours<=16){
            state="Good Afternoon";
          //  Toast.makeText(this, "Good Afternoon", Toast.LENGTH_SHORT).show();
        }else if(hours>=16 || hours<=21){
            state="Good Evening";
           // Toast.makeText(this, "Good Evening", Toast.LENGTH_SHORT).show();
        }else if(hours>=21 || hours<=24){
            state="Good Night";
           // Toast.makeText(this, "Good Night", Toast.LENGTH_SHORT).show();
        }

        return state;
    }

    public static int getNumberOfDaysInMonth(int month)
    {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        Calendar mycal = new GregorianCalendar(year, month, 1);
        int daysInMonth = mycal.getActualMaximum(Calendar.DATE);

        return daysInMonth;
    }

    public static String  getInMonth(String month)
    {
        String n="0";

        if (month.equals("January")){
            n="01";
        }else if (month.equals("February")){
            n="02";
        }else if (month.equals("March")){
            n="03";
        }else if (month.equals("April")){
            n="04";
        }else if (month.equals("May")){
            n="05";
        }else if (month.equals("June")){
            n="06";
        }else if (month.equals("July")){
            n="07";
        }else if (month.equals("August")){
            n="08";
        }else if (month.equals("September")){
            n="09";
        }else if (month.equals("October")){
            n="10";
        }else if (month.equals("November")){
            n="11";
        }else if (month.equals("December")){
            n="12";
        }

        return n;

    }

    public static String  getInMonthWord(String month)
    {
        String n="0";

        if (month.equals("01")){
            n="January";
        }else if (month.equals("02")){
            n="February";
        }else if (month.equals("03")){
            n="March";
        }else if (month.equals("04")){
            n="April";
        }else if (month.equals("05")){
            n="May";
        }else if (month.equals("006")){
            n="June";
        }else if (month.equals("07")){
            n="July";
        }else if (month.equals("008")){
            n="August";
        }else if (month.equals("09")){
            n="September";
        }else if (month.equals("10")){
            n="October";
        }else if (month.equals("11")){
            n="November";
        }else if (month.equals("12")){
            n="December";
        }

        return n;

    }

    public static int getMonthDays(int month) {
        int daysInMonth ;
        int year = Calendar.getInstance().get(Calendar.YEAR);;
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            daysInMonth = 30;
        }
        else {
            if (month == 2) {
                daysInMonth = (year % 4 == 0) ? 29 : 28;
            } else {
                daysInMonth = 31;
            }
        }
        return daysInMonth;
    }

    public static String year(){

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("yyyy");
        String format = simpleDateFormat.format(date);

        return  format;
    }

    public static String getDateformate(String raw) {
        String inputPattern = "yyyy-MM-dd'T'HH:mm:ss";
        String outputPattern = "dd-MMM-yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        Date date = null;
        String str = null;
        try {
            date = inputFormat.parse(raw);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  str;
    }


    public static String getDateformte(String raw) {
        String inputPattern = "yyyy-MM-dd";
        String outputPattern = "dd-MMM-yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        Date date = null;
        String str = null;
        try {
            date = inputFormat.parse(raw);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  str;
    }

}
