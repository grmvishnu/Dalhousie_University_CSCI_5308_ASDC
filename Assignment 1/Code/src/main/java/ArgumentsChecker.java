public class ArgumentsChecker
{
    public static boolean isValid(String[] args)
    {
        if(args[0].equalsIgnoreCase("init") || args[0].equalsIgnoreCase("query"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
