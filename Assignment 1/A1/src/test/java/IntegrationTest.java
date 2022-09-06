import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.*;

class IntegrationTest
{
    @Test
    /*
     * Integration test 1:
     * When init pass successfully
     */
    public void initPassTest()
    {
        String[] arguments = {"init", "T:\\GRM VISHNU\\5308\\Assignment 1\\book.csv"};

        Assertions.assertDoesNotThrow(new Executable()
        {
            @Override
            public void execute() throws Throwable
            {
                DbWrapper.main(arguments);
            }
        });
    }


    @Test
    /*
     * Integration test 2:
     * When init command word given wrong
     */
    public void initWordFailtest()
    {
        String[] arguments = {"class", "T:\\GRM VISHNU\\5308\\Assignment 1\\book.csv"};
        Exception exception = assertThrows(Exception.class, () ->
                DbWrapper.main(arguments));

        String expectedMessage = "Wrong command. Please enter the right one.";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }


    @Test
    /*
     * Integration test 3:
     * When file type given wrong with init command
     */
    public void pathFailTest()
    {
        String[] arguments = {"init", "T:\\GRM VISHNU\\5308\\Assignment 1\\book.pdf"};
        Exception exception = assertThrows(Exception.class, () ->
                DbWrapper.main(arguments));

        String expectedMessage = "File type given is not .csv";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }


    @Test
    /*
     * Integration test 4:
     * When query command is passed and everything is successful
     */
    public void queryPassTest()
    {
        String[] arguments = {"query", "Assignment1", "Select * from book"};

        Assertions.assertDoesNotThrow(new Executable()
        {
            @Override
            public void execute() throws Throwable
            {
                DbWrapper.main(arguments);
            }
        });
    }


    @Test
    /*
     * Integration test 5:
     * When query word is given wrong
     */
    public void queryFailtest()
    {
        String[] arguments = {"class", "Assignment1", "Select * from book;"};
        Exception exception = assertThrows(Exception.class, () ->
                DbWrapper.main(arguments));

        String expectedMessage = "Wrong command. Please enter the right one.";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }


    @Test
    /*
     * Integration test 6:
     * When query word is given wrong
     */
    public void test()
    {
        String[] arguments = {"query", "Assignment", "Select * from book;"};
        Exception exception = assertThrows(Exception.class, () ->
                DbWrapper.main(arguments));

        String expectedMessage = "Database name is not equal to the database we inserted values into.";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }
}
