package resources;

import org.joda.time.DateTime;
import org.joda.time.Days;

public class DemoJoda {

	public static void main(String[] args) {
		DateTime start = new DateTime().dayOfMonth().roundFloorCopy();
		DateTime expiration = start.plusDays(15);
		System.out.println(start);
		System.out.println(expiration);
		System.out.println(Days.daysBetween(start, expiration).getDays());
	}

}
