/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Calendar;

/**
 *
 * @author kieuthanhtheanh
 */
public class DateTimeHelper {
    public static java.sql.Date convertUtilDateToSqlDate(java.util.Date utilDate) {
        if (utilDate != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(utilDate);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            return new java.sql.Date(calendar.getTimeInMillis());
        } else {
            return null;
        }
    }
    
    public static int getCurrentWeek() {
        // Sử dụng LocalDate để lấy thông tin về ngày và tuần hiện tại
        LocalDate currentDate = LocalDate.now();
        WeekFields weekFields = WeekFields.ISO;

        // Lấy số tuần của ngày hiện tại trong năm
        int currentWeek = currentDate.get(weekFields.weekOfWeekBasedYear());

        return currentWeek;
    }
    
    public static LocalDate[] getWeekDaysForWeekNumber(int getCurrentWeek) {
        // Lấy ngày hiện tại
        LocalDate currentDate = LocalDate.now();

        // Lấy ngày đầu tiên của năm
        LocalDate firstDayOfYear = LocalDate.of(currentDate.getYear(), 1, 1);

        // Lấy ngày đầu tiên của tuần trong năm
        LocalDate firstDayOfRequestedWeek = firstDayOfYear
                .with(DayOfWeek.MONDAY)
                .plusWeeks(getCurrentWeek - 1); // Trừ 1 vì tuần đầu tiên đã được tính từ đầu năm

        // Tạo mảng chứa các ngày trong tuần
        LocalDate[] weekDays = new LocalDate[7];
        for (int i = 0; i < 7; i++) {
            weekDays[i] = firstDayOfRequestedWeek.plusDays(i);
        }

        return weekDays;
    }
    
}
