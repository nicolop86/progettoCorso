package resources;

import org.joda.time.DateTime;
import org.joda.time.Days;

public class DemoJoda {

	public static void main(String[] args) {
		
		DateTime temp = new DateTime();
		temp.withDate(2100, 12, 31);
		DateTime start = new DateTime().dayOfMonth().roundFloorCopy();
		DateTime expiration = start.plusDays(15);
		System.out.println(start);
		System.out.println(expiration);
		System.out.println(Days.daysBetween(start, expiration).getDays());
		System.out.println(new DateTime().toDate());
		System.out.println("---------");
		
		if (temp.isAfter(expiration)) {
			System.out.println("OK");
		} else {
			System.out.println("NO BUONO");
		}
	}

}
