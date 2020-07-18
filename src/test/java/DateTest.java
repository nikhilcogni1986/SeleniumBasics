import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTest {
    public static void main(String[] args) {
        Date d1 = new Date();
        System.out.println(d1.toString());

        //format the date
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String formattedDate = sdf.format(d1);
        System.out.println(formattedDate);

        //how to get date and time using Calendar class
        Calendar cal = Calendar.getInstance();
        Date currentDateTime = cal.getTime();
        System.out.println(currentDateTime);

        //how to set future date and time
        Calendar cal1 = Calendar.getInstance();
        cal1.add(Calendar.DAY_OF_YEAR, 10);
        Date futureDateTime = cal1.getTime();
        System.out.println(futureDateTime);
    }
}
