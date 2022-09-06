package firstpackage;
import secondpackage.SbiInterest;
import java.util.ArrayList;
import java.util.List;

public class MathematicalCalculations
{
    public static double pi = 3.141592;
    public int square;
    public int cube;
    public int powerValue;
    public double root;
    public double temp;
    public double nearestInt;
    public int smallest;
    public int biggest;
    public int addValue;
    public int subtractValue;
    public float divisionValue;
    public List<Integer> even = new ArrayList<>();
    public List<Integer> odd = new ArrayList<>();
    public boolean evenCheck;
    public boolean oddCheck;
    public boolean palindrome;
    public boolean cal;
    public boolean prime;
    public float sInterest;
    public double cInterest;

    public int squareOfANumber(int value)
    {
        square = 0;
        for(int i = 0; i < 2; i++)
        {
            square += value;
        }
        return square;
    }

    public int cubeOfANumber(int value)
    {
        cube = 0;
        for(int i = 0; i < 3; i++)
        {
            cube += value;
        }
        return cube;
    }

    public int powerOfANumber(int value, int power)
    {
        powerValue = 0;
        for(int i = 0; i < power; i++)
        {
            powerValue += value;
        }
        return powerValue;
    }

    public double rootOfANumber(double value)
    {
        root = value / 2;
        do
        {
            temp = root;
            root = (temp + (value/temp)) / 2;
        }
        while ((temp - root) != 0);
        return root;
    }

    public double floorDivision(int value1, int value2)
    {
        nearestInt = Math.floorDiv(value1, value2);
        return nearestInt;
    }

    public int minimumNumber(int value1, int value2, int value3)
    {
        if (value1 <= value2 && value1 <= value3)
        {
            smallest = value1;
        }
        else if (value2 <= value3 && value2 <= value1)
        {
            smallest = value2;
        }
        else
        {
            smallest = value3;
        }
        return smallest;
    }

    public int maximumNumber(int value1, int value2, int value3)
    {
        if (value1 >= value2 && value1 >= value3)
        {
            biggest = value1;
        }
        else if (value2 >= value3 && value2 >= value1)
        {
            biggest = value2;
        }
        else
        {
            biggest = value3;
        }
        return biggest;
    }

    public int addition(int value1, int value2, int value3)
    {
        addValue = value1 + value2 + value3;
        return addValue;
    }

    public int subtraction(int value1, int value2, int value3)
    {
        subtractValue = value1 - value2 - value3;
        return subtractValue;
    }

    public float division(int value1, int value2)
    {
        divisionValue = value1 / value2;
        return divisionValue;
    }

    public List<Integer> evenNumbers(int number)
    {
        for(int i = 1; i <= number; i++)
        {
            if(i % 2 == 0)
            {
                even.add(i);
            }
        }
        return even;
    }

    public List<Integer> oddNumbers(int number)
    {
        for(int i = 1; i <= number; i++)
        {
            if(i % 2 != 0)
            {
                odd.add(i);
            }
        }
        return odd;
    }

    public boolean checkForEven(int number)
    {
        evenCheck = false;
        if(number / 2 == 0)
        {
            evenCheck = true;
        }
        return evenCheck;
    }

    public boolean checkForOdd(int number)
    {
        oddCheck = false;
        if(number / 2 != 0)
        {
            oddCheck = true;
        }
        return oddCheck;
    }

    public boolean isPalindrome(int num)
    {
        palindrome = false;
        int i, sum = 0, temp;
        temp = num;
        while(num >0)
        {
            i = num % 10;
            sum = (sum * 10 ) + i;
            num = num/10;
        }
        if(temp == sum)
        {
            palindrome = true;
        }
        return palindrome;
    }

    public boolean isTwentyOne(int p, int q)
    {
        cal = false;
        if(p == 21 || q == 21 || (p+q) == 21 || Math.abs(p-q) == 21)
        {
            cal = true;
        }
        return cal;
    }

    public boolean isPrime(int value)
    {
        prime = false;
        for(int i = 2; i <= value/2; ++i)
        {
            if(value % i == 0)
            {
                prime = true;
                break;
            }
        }
        return !prime;
    }

    public float simpleInterest(float principal, float rate, int time)
    {
        sInterest = (principal * rate * time) / 100;
        return sInterest;
    }

    public double compoundInterest(float principal, int time, float rate, int noOfTimes)
    {
        double temp = principal * Math.pow(1 + (rate/noOfTimes), noOfTimes * time);
        cInterest = temp - principal;
        return cInterest;
    }

    public static void multiplication(int a, int b, int c, int d, int e, int f, int g, int h, int i, int j)
    {
        int answer = a*b*c*d*e*f*g*h*i*j;
        System.out.println(answer);
    }

    public static void main(String[] args)
    {
        multiplication(1, 6, 82, 73, 2, 9, 62, 72, 2, 18);
        SbiInterest.fdInterest(143);
    }
}
