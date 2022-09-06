import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArgumentsCheckerTest
{
    @Test
    /*
     * isValid method test 1:
     * Test passing successfully, init argument passed
     */
    public void initPassTest()
    {
        String[] demo = {"init"};
        assertTrue(ArgumentsChecker.isValid(demo));
    }

    @Test
    /*
     * isValid method test 2:
     * Test passing successfully, query argument passed
     */
    public void queryPasstest()
    {
        String[] demo = {"query"};
        assertTrue(ArgumentsChecker.isValid(demo));
    }


    @Test
    /*
     * isValid method test 3:
     * Test failed, no init or no query argument passed
     */
    public void Failtest()
    {
        String[] demo = {"class", "query", "init"};
        assertFalse(ArgumentsChecker.isValid(demo));
    }
}
