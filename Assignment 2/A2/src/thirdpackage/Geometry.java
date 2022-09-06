package thirdpackage;
import firstpackage.MathematicalCalculations;

public class Geometry
{
    public static double volumeOfCylinder(int radius, int height)
    {
        double value = 0;
        value = (MathematicalCalculations.pi * radius * radius * height);
        return value;
    }
}
