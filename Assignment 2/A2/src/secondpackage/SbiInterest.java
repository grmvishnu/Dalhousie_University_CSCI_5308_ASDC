package secondpackage;
import thirdpackage.Geometry;

public class SbiInterest
{
    public static void fdInterest(int days)
    {
        int deposit = 100000;
        double amount;

        if(days >= 7 && days <= 45)
        {
            amount = deposit * 2.90;
        }
        else if(days >= 46 && days <= 179)
        {
            amount = deposit * 3.90;
        }
        else if(days >= 180 && days <= 210)
        {
            amount = deposit * 4.40;
        }
        else if(days >= 211 && days <= 365)
        {
            amount = deposit * 4.80;
        }
        else if(days >= 366 && days <= 420)
        {
            amount = deposit * 5.10;
        }
        else if(days >= 421 && days <= 500)
        {
            amount = deposit * 5.60;
        }
        else if(days >= 501 && days <= 630)
        {
            amount = deposit * 6.20;
        }
        else if(days >= 631 && days <= 770)
        {
            amount = deposit * 7.50;
        }
        else
        {
            amount = deposit * 8.30;
        }

        System.out.println("Amount of interest is: " + amount);
        System.out.println("Area of the cone of radius 4 is: " + Geometry.volumeOfCylinder(4, 6));
    }
}
