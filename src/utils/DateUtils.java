package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class DateUtils {

    public static boolean isDateAvailable(LocalDate startDate, LocalDate endDate, List<LocalDate> dates) {
        for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
            if (!dates.contains(date)) {
                return false;
            }
        }
        return true;
    }


}