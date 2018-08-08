package id.co.asyst.prasetya.fragmentpras.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    public static String formatDate(String currentFormat, String resultFormat, String dateString) {
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(currentFormat, Locale.getDefault());

        try {
            date = simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat simpleDateFormatResult = new SimpleDateFormat(resultFormat, Locale.getDefault());
        return simpleDateFormatResult.format(date);
    }

    public static Date saveDate(String currentFormat, String dateString) {
        Date date = null;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(currentFormat, Locale.getDefault());
        try {
            date = simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;

    }

}
