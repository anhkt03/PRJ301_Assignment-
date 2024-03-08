/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.Lecturer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import static util.DateTimeHelper.convertUtilDateToSqlDate;

/**
 *
 * @author kieuthanhtheanh
 */
public class DateTimeHelper {

    public static String[] splitString(String input) {
        // Sử dụng phương thức .split() để tách chuỗi thành mảng
        String[] result = input.split(" To ");

        // Nếu mảng có đúng 2 phần, trả về mảng này
        if (result.length == 2) {
            return result;
        } else {
            // Nếu không, trả về null
            return null;
        }
    }

    public static String changeDateFormat(String inputDate) {
        try {
            // Định dạng đầu vào
            DateFormat inputFormat = new SimpleDateFormat("yyyy/dd/MM");

            // Định dạng đầu ra
            DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

            // Chuyển đổi chuỗi thành đối tượng Date
            Date date = inputFormat.parse(inputDate);

            // Chuyển đổi lại thành chuỗi mới với định dạng mong muốn
            String formattedDate = outputFormat.format(date);

            return formattedDate;
        } catch (ParseException e) {
            // Xử lý nếu có lỗi chuyển đổi
            e.printStackTrace();
            return "Invalid date format";
        }
    }

    public static Date addDaysToDate(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.getTime();
    }

    public static ArrayList<java.sql.Date> getDatesBetween(java.sql.Date fromDate, java.sql.Date toDate) {
        if (fromDate == null || toDate == null) {
            throw new IllegalArgumentException("Both fromDate and toDate cannot be null.");
        }
        if (fromDate.after(toDate)) {
            throw new IllegalArgumentException("fromDate cannot be after toDate.");
        }

        Date from = new Date(fromDate.getTime());
        Date to = new Date(toDate.getTime());

        Date temp = from;

        ArrayList<java.sql.Date> dates = new ArrayList<>();
        while (!temp.after(to)) {
            dates.add(convertUtilDateToSqlDate(temp));
            temp = addDaysToDate(temp, 1);
        }
        return dates;
    }
    
    public static java.sql.Date convertStringToSqlDate(String dateString) {
        try {
            // Định dạng của chuỗi đầu vào
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            // Chuyển đổi chuỗi thành java.util.Date
            java.util.Date utilDate = sdf.parse(dateString);

            // Lấy timestamp từ java.util.Date
            long timeInMillis = utilDate.getTime();

            // Khởi tạo java.sql.Date từ timestamp
            return new java.sql.Date(timeInMillis);
        } catch (ParseException e) {
            // Xử lý nếu có lỗi chuyển đổi
//            e.printStackTrace();
            return null;
        }
    }

}
