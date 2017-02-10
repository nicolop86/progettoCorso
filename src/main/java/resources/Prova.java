package resources;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Interval;

public class Prova {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Car car1 = new Car(1, "W", 4, 2);
		Projector pj1 = new Projector(2, 256, "Toshiba");
		Laptop pc1 = new Laptop(3, 4, 8, "Sony");
		Car car2 = new Car(1, "R2", 4, 2);
		
		System.out.println(car1.equals(pc1));		
		System.out.println(car1.equals(pj1));
		System.out.println(car1.equals(car2));
		System.out.println("======");
		DateTime ds1 = new DateTime(new Date());
		DateTime df1 = new DateTime(new Date(117, 1, 22));
		DateTime ds2 = new DateTime(new Date(117, 1, 15));
		DateTime df2 = new DateTime(new Date(117, 1, 22));
		Interval int1 = new Interval(ds1, df1);
		Interval int2 = new Interval(ds2, df2);
		System.out.println((int1.overlaps(int2)));
	}

}
