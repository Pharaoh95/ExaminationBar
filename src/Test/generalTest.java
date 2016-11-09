package Test;

import java.util.Calendar;
import java.util.Date;

public class generalTest {

	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
	}
}