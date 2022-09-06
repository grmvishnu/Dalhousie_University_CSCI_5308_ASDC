import org.junit.jupiter.api.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DbWrapperTest
{
    @Test
    /*
     * readingFile method test 1:
     * Test about the normal basic case with all values in it
     */
    public void readingFileBasicTest() throws Exception
    {
        List<List<String>> outerList = new ArrayList<>();
        List<String> innerList_1 = new ArrayList<>();
        innerList_1.add("ISBN");
        innerList_1.add("Title");
        innerList_1.add("Author");
        innerList_1.add("Publisher");
        outerList.add(innerList_1);

        List<String> innerList_2 = new ArrayList<>();
        innerList_2.add("1");
        innerList_2.add("ABC");
        innerList_2.add("James");
        innerList_2.add("RGK Solutions");
        outerList.add(innerList_2);

        List<String> innerList_3 = new ArrayList<>();
        innerList_3.add("2");
        innerList_3.add("B");
        innerList_3.add("James");
        innerList_3.add("GRM Publishers");
        outerList.add(innerList_3);

        List<String> innerList_4 = new ArrayList<>();
        innerList_4.add("3");
        innerList_4.add("C");
        innerList_4.add("Rishi");
        innerList_4.add("GRM Publishers");
        outerList.add(innerList_4);

        List<String> innerList_5 = new ArrayList<>();
        innerList_5.add("4");
        innerList_5.add("D");
        innerList_5.add("Rivi");
        innerList_5.add("Pragma Publications");
        outerList.add(innerList_5);

        List<String> innerList_6 = new ArrayList<>();
        innerList_6.add("7");
        innerList_6.add("S");
        innerList_6.add("Sanjuna");
        innerList_6.add("Tarun");
        outerList.add(innerList_6);

        List<String> innerList_7 = new ArrayList<>();
        innerList_7.add("9");
        innerList_7.add("ABC");
        innerList_7.add("Sandeep");
        innerList_7.add("Mutyala");
        outerList.add(innerList_7);

        List<String> innerList_8 = new ArrayList<>();
        innerList_8.add("24");
        innerList_8.add("PALLU");
        innerList_8.add("Anudeep");
        innerList_8.add("Hate");
        outerList.add(innerList_8);

        List<List<String>> answer = DbWrapper.readingFile("T:\\GRM VISHNU\\5308\\Assignment 1\\book.csv");
        Assertions.assertEquals(answer, outerList);
    }


    @Test
    /*
     * readingFile method test 2:
     * Test with the file being present and no data in it
     */
    public void NoDataInFileTest()
    {
        Exception exception = assertThrows(EmptyFileCustomException.class, () ->
                DbWrapper.readingFile("T:\\GRM VISHNU\\5308\\Assignment 1\\empty.csv"));

        String expectedMessage = "This is an empty file";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    /*
     * reading File method test 3:
     * Test when the file is not present in the given path
     */
    public void FileNotFoundTest()
    {
        Exception exception = assertThrows(FileNotFoundException.class, () ->
                DbWrapper.readingFile("T:\\GRM VISHNU\\5308\\Assignment 1\\Input.csv"));

        String expectedMessage = "The system cannot find the file specified";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    /*
     * readingFile method test 4:
     * Test when the file has only header and no values
     */
    public void onlyHeaderNoValuesTest() throws Exception
    {
        List<List<String>> outerList = new ArrayList<>();
        List<String> innerList_1 = new ArrayList<>();
        innerList_1.add("ISBN");
        innerList_1.add("Title");
        innerList_1.add("Author");
        innerList_1.add("Publisher");
        outerList.add(innerList_1);

        List<List<String>> answer = DbWrapper.readingFile("T:\\GRM VISHNU\\5308\\Assignment 1\\onlyheader.csv");
        Assertions.assertEquals(answer, outerList);
    }


    @Test
    /*
     * readingFile method test 5:
     * Test when the file name passed is an empty string
     */
    public void emptyStringPassedTest()
    {
        Exception exception = assertThrows(Exception.class, () -> DbWrapper.readingFile(""));

        String expectedMessage = null;
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    /*
     * readingFile method test 6:
     * Test when the file name passed is a null value
     */
    public void nullValuePassedTest()
    {
        Exception exception = assertThrows(Exception.class, () -> DbWrapper.readingFile(null));

        String expectedMessage = null;
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }


    @Test
    /*
     * getHeader method test 1:
     * Test when there is a header in the file to get the header
     */
    public void gettingHeaderTest() throws SQLException, ClassNotFoundException, EmptyFileCustomException
    {
        List<List<String>> outerList = new ArrayList<>();
        List<String> innerList_1 = new ArrayList<>();
        innerList_1.add("ISBN");
        innerList_1.add("Title");
        innerList_1.add("Author");
        innerList_1.add("Publisher");
        outerList.add(innerList_1);

        List<String> innerList_2 = new ArrayList<>();
        innerList_2.add("1");
        innerList_2.add("ABC");
        innerList_2.add("James");
        innerList_2.add("RGK Solutions");
        outerList.add(innerList_2);

        List<String> innerList_3 = new ArrayList<>();
        innerList_3.add("2");
        innerList_3.add("A");
        innerList_3.add("James");
        innerList_3.add("GRM Publishers");
        outerList.add(innerList_3);

        List<String> innerList_4 = new ArrayList<>();
        innerList_4.add("3");
        innerList_4.add("C");
        innerList_4.add("Rishih");
        innerList_4.add("GRM Publishers");
        outerList.add(innerList_4);

        List<String> innerList_5 = new ArrayList<>();
        innerList_5.add("4");
        innerList_5.add("D");
        innerList_5.add("Rivi");
        innerList_5.add("Pragma Publications");
        outerList.add(innerList_5);

        List<String> value = new ArrayList<>();
        value.add("ISBN");
        value.add("Title");
        value.add("Author");
        value.add("Publisher");

        List<String> answer = DbWrapper.getHeader(outerList);
        Assertions.assertEquals(answer, value);
    }


    @Test
    /*
     * getHeader method test 2:
     * Test when there is no header in the file i.e., empty file
     */
    public void noHeaderInFileTest() throws EmptyFileCustomException
    {
        List<List<String>> outerList = new ArrayList<>();
        List<String> innerList_1 = new ArrayList<>();

        Exception exception = assertThrows(EmptyFileCustomException.class, () ->
                DbWrapper.getHeader(outerList));

        String expectedMessage = "This is an empty file";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    /*
     * getTableName method test 1:
     * Test when we get the table name successfully
     */
    public void tableNameReceivedTest() throws Exception
    {
        String expected = "demo";
        String actual = DbWrapper.getTableName("C:\\jnshb\\hbd\\jsh vhsd\\demo.csv");
        Assertions.assertEquals(expected, actual);
    }


    @Test
    /*
     * getTableName method test 2:
     * Test when we get exceptions due to other reasons
     */
    public void tableNameNotReceivedTest()
    {
        Exception exception = assertThrows(Exception.class, () -> DbWrapper.getTableName("C:\\bkk\\hgvdcj\\.csv"));

        String expectedMessage = "Table name is empty";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    /*
     * getTableName method test 3:
     * Test when the file name passed is an empty string
     */
    public void emptyStringFileNameTest()
    {
        Exception exception = assertThrows(Exception.class, () -> DbWrapper.getTableName(""));

        String expectedMessage = null;
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    /*
     * getTableName method test 4:
     * Test when the file name passed is a null value
     */
    public void nullValueFileNameTest()
    {
        Exception exception = assertThrows(Exception.class, () -> DbWrapper.getTableName(null));

        String expectedMessage = null;
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }


    @Test
    /*
     * getTableName method test 5:
     * Test when the file name passed is a null value
     */
    public void wrongFileTypeTest()
    {
        Exception exception = assertThrows(Exception.class, () ->
                DbWrapper.getTableName("C:\\jnshb\\hbd\\jsh vhsd\\demo.pdf"));

        String expectedMessage = "File type given is not .csv";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }


    @Test
    /*
     * tableQueryGenerator method test 1:
     * Test when it successfully generates a query for table insertion with all the required values
     */
    public void tableQueryGeneratedTest() throws Exception
    {
        List<String> headerList = new ArrayList<>();
        headerList.add("Vichhuu");
        headerList.add("Ammuuu");
        headerList.add("Dimpu");
        headerList.add("Rivi");
        String table = "demo";

        String expected = "CREATE TABLE IF NOT EXISTS demo(Vichhuu VARCHAR(512), Ammuuu VARCHAR(512), " +
                "Dimpu VARCHAR(512), Rivi VARCHAR(512), ID INT NOT NULL AUTO_INCREMENT, PRIMARY KEY(ID));";
        String actual = DbWrapper.tableQueryGenerator(headerList, table);
        Assertions.assertEquals(expected, actual);
    }


    @Test
    /*
     * tableQueryGenerator method test 2:
     * Test when it doesn't generate a query for table insertion with all the required values
     */
    public void tableQueryNotGeneratedTest() throws Exception
    {
        List<List<String>> demoOne = DbWrapper.readingFile("T:\\GRM VISHNU\\5308\\Assignment 1\\onlyheader.csv");
        List<String> headerList = DbWrapper.getHeader(demoOne);
        String table = "onlyheader";

        Exception exception = assertThrows(Exception.class, () -> DbWrapper.tableQueryGenerator(headerList, table));

        String expectedMessage = "The header in the csv file contains columns with more than one word";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }


    @Test
    /*
     * tableQueryGenerator method test 3:
     * Test when the header file passed is empty
     */
    public void headerEmptyFileTest() throws Exception
    {
        List<String> headerList = new ArrayList<>();
        String table = "demo";

        Exception exception = assertThrows(EmptyFileCustomException.class, () -> DbWrapper.tableQueryGenerator(headerList, table));

        String expectedMessage = "Header size is zero";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }


    @Test
    /*
     * tableQueryGenerator method test 4:
     * Test when the table name passed is an empty string
     */
    public void emptyTableNameTest() throws Exception
    {
        List<String> headerList = new ArrayList<>();
        headerList.add("Vichhuu");
        headerList.add("Ammuuu");
        headerList.add("Dimpu");
        headerList.add("Rivi");
        String table = "";

        Exception exception = assertThrows(Exception.class, () -> DbWrapper.tableQueryGenerator(headerList, table));

        String expectedMessage = "Table name is empty";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }


    @Test
    /*
     * tableQueryGenerator method test 5:
     * Test when the table name passed is a null value
     */
    public void nullTableNameTest()
    {
        List<String> headerList = new ArrayList<>();
        headerList.add("Vichhuu");
        headerList.add("Ammuuu");
        headerList.add("Dimpu");
        headerList.add("Rivi");
        String table = null;

        Exception exception = assertThrows(NullPointerException.class, () -> DbWrapper.tableQueryGenerator(headerList, table));

        String expectedMessage = null;
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }


    @Test
    /*
     * insertQueryGenerator method test 1:
     * This method also includes testing getHeader method
     * Test when the inserting values query is generated successfully
     */
    public void insertQueryGeneratedTest() throws Exception
    {
        List<List<String>> outer = new ArrayList<>();
        List<String> innerList_1 = new ArrayList<>();
        innerList_1.add("ISBN");
        innerList_1.add("Title");
        innerList_1.add("Author");
        innerList_1.add("Publisher");
        outer.add(innerList_1);

        List<String> innerList_2 = new ArrayList<>();
        innerList_2.add("1");
        innerList_2.add("ABC");
        innerList_2.add("James");
        innerList_2.add("RGK Solutions");
        outer.add(innerList_2);

        List<String> innerList_3 = new ArrayList<>();
        innerList_3.add("2");
        innerList_3.add("A");
        innerList_3.add("James");
        innerList_3.add("GRM Publishers");
        outer.add(innerList_3);

        List<String> innerList_4 = new ArrayList<>();
        innerList_4.add("3");
        innerList_4.add("C");
        innerList_4.add("Rishih");
        innerList_4.add("GRM Publishers");
        outer.add(innerList_4);

        List<String> innerList_5 = new ArrayList<>();
        innerList_5.add("4");
        innerList_5.add("D");
        innerList_5.add("Rivi");
        innerList_5.add("Pragma Publications");
        outer.add(innerList_5);

        List<String> header = DbWrapper.getHeader(outer);
        String table = "demo";

        String expected = "INSERT INTO demo (ISBN, Title, Author, Publisher) VALUES " +
                "('1','ABC','James','RGK Solutions'), ('2','A','James','GRM Publishers'), " +
                "('3','C','Rishih','GRM Publishers'), ('4','D','Rivi','Pragma Publications');";
        String actual = DbWrapper.insertQueryGenerator(outer, table);
        Assertions.assertEquals(expected, actual);
    }


    @Test
    /*
     * insertQueryGenerator method test 2:
     * This method also includes testing getHeader method
     * Test when the inserting values have no values in the file and only header
     */
    public void onlyHeaderNoValuesQueryTest() throws Exception
    {
        List<List<String>> outer = new ArrayList<>();
        List<String> innerList_1 = new ArrayList<>();
        innerList_1.add("ISBN");
        innerList_1.add("Title");
        innerList_1.add("Author");
        innerList_1.add("Publisher");
        outer.add(innerList_1);

        List<String> header = DbWrapper.getHeader(outer);
        String table = "demo";

        String expected = "INSERT INTO demo (ISBN, Title, Author, Publisher)";
        String actual = DbWrapper.insertQueryGenerator(outer, table);
        Assertions.assertEquals(expected, actual);

    }


    @Test
    /*
     * insertQueryGenerator method test 3:
     * This method also includes testing getHeader method
     * Test when outer list passed is empty
     */
    public void listEmptyTest()
    {
        List<List<String>> outer = new ArrayList<>();
        String table = "demo";

        Exception exception1 = assertThrows(EmptyFileCustomException.class, () ->
                DbWrapper.getHeader(outer));
        String expectedMessage1 = "This is an empty file";
        String actualMessage1 = exception1.getMessage();
        if(!expectedMessage1.equalsIgnoreCase(actualMessage1))
        {
            Exception exception2 = assertThrows(EmptyFileCustomException.class, () ->
                    DbWrapper.insertQueryGenerator(outer, table));

            String expectedMessage2 = "This is an empty file";
            String actualMessage2 = exception2.getMessage();
            Assertions.assertEquals(expectedMessage2, actualMessage2);
        }
        else
        {
            Assertions.assertEquals(expectedMessage1, actualMessage1);
        }
    }


    @Test
    /*
     * insertQueryGenerator method test 4:
     * This method also includes testing getHeader method
     * Test when the table name passed is an empty string
     */
    public void insertQueryEmptyStringTest() throws Exception
    {
        List<List<String>> outer = new ArrayList<>();
        List<String> innerList_1 = new ArrayList<>();
        innerList_1.add("ISBN");
        innerList_1.add("Title");
        innerList_1.add("Author");
        innerList_1.add("Publisher");
        outer.add(innerList_1);

        List<String> innerList_2 = new ArrayList<>();
        innerList_2.add("1");
        innerList_2.add("ABC");
        innerList_2.add("James");
        innerList_2.add("RGK Solutions");
        outer.add(innerList_2);

        List<String> innerList_3 = new ArrayList<>();
        innerList_3.add("2");
        innerList_3.add("A");
        innerList_3.add("James");
        innerList_3.add("GRM Publishers");
        outer.add(innerList_3);

        List<String> innerList_4 = new ArrayList<>();
        innerList_4.add("3");
        innerList_4.add("C");
        innerList_4.add("Rishih");
        innerList_4.add("GRM Publishers");
        outer.add(innerList_4);

        List<String> innerList_5 = new ArrayList<>();
        innerList_5.add("4");
        innerList_5.add("D");
        innerList_5.add("Rivi");
        innerList_5.add("Pragma Publications");
        outer.add(innerList_5);

        List<String> header = DbWrapper.getHeader(outer);
        String table = "";

        Exception exception = assertThrows(Exception.class, () -> DbWrapper.insertQueryGenerator(outer, table));

        String expectedMessage = "Table name is empty";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }


    @Test
    /*
     * insertQueryGenerator method test 5:
     * This method also includes testing getHeader method
     * Test when the table name passed is a null value
     */
    public void insertQueryNullStringTest() throws Exception
    {
        List<List<String>> outer = new ArrayList<>();
        List<String> innerList_1 = new ArrayList<>();
        innerList_1.add("ISBN");
        innerList_1.add("Title");
        innerList_1.add("Author");
        innerList_1.add("Publisher");
        outer.add(innerList_1);

        List<String> innerList_2 = new ArrayList<>();
        innerList_2.add("1");
        innerList_2.add("ABC");
        innerList_2.add("James");
        innerList_2.add("RGK Solutions");
        outer.add(innerList_2);

        List<String> innerList_3 = new ArrayList<>();
        innerList_3.add("2");
        innerList_3.add("A");
        innerList_3.add("James");
        innerList_3.add("GRM Publishers");
        outer.add(innerList_3);

        List<String> innerList_4 = new ArrayList<>();
        innerList_4.add("3");
        innerList_4.add("C");
        innerList_4.add("Rishih");
        innerList_4.add("GRM Publishers");
        outer.add(innerList_4);

        List<String> innerList_5 = new ArrayList<>();
        innerList_5.add("4");
        innerList_5.add("D");
        innerList_5.add("Rivi");
        innerList_5.add("Pragma Publications");
        outer.add(innerList_5);

        List<String> header = DbWrapper.getHeader(outer);
        String table = null;

        Exception exception = assertThrows(NullPointerException.class, () -> DbWrapper.insertQueryGenerator(outer, table));

        String expectedMessage = null;
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }


    @Test
    /*
     * printToCSV method test 1:
     * Test passed successfully
     */
    public void writingToFilePassedTest() throws IOException
    {
        List<String> header = new ArrayList<>();
        List<String> rows = new ArrayList<>();
        int columns = 4;

        header.add("ISBN");
        header.add("Title");
        header.add("Author");
        header.add("Publisher");

        rows.add("1");
        rows.add("ABC");
        rows.add("James");
        rows.add("RGK Solutions");

        rows.add("2");
        rows.add("A");
        rows.add("James");
        rows.add("GRM Publishers");

        Boolean value = DbWrapper.printToCSV(header, rows, columns);
        assertTrue(value);
    }


    @Test
    /*
     * printToCSV method test 2:
     * Test when the header list passed is empty
     */
    public void emptyHeaderListTest()
    {
        List<String> header = new ArrayList<>();
        List<String> rows = new ArrayList<>();
        int columns = 3;

        rows.add("1");
        rows.add("ABC");
        rows.add("James");
        rows.add("RGK Solutions");

        rows.add("2");
        rows.add("A");
        rows.add("James");
        rows.add("GRM Publishers");

        Exception exception = assertThrows(IOException.class, () -> DbWrapper.printToCSV(header, rows, columns));

        String expectedMessage = "File writing failed";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }


    @Test
    /*
     * printToCSV method test 3:
     * Test when the row list passed is empty
     */
    public void emptyRowsListTest()
    {
        List<String> header = new ArrayList<>();
        List<String> rows = new ArrayList<>();
        int columns = 3;

        header.add("ISBN");
        header.add("Title");
        header.add("Author");
        header.add("Publisher");

        Exception exception = assertThrows(IOException.class, () -> DbWrapper.printToCSV(header, rows, columns));

        String expectedMessage = "File writing failed";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }


    @Test
    /*
     * printToCSV method test 4:
     * Test when the column count passed is zero
     */
    public void zeroColumnTest()
    {
        List<String> header = new ArrayList<>();
        List<String> rows = new ArrayList<>();
        int columns = 0;

        header.add("ISBN");
        header.add("Title");
        header.add("Author");
        header.add("Publisher");

        rows.add("1");
        rows.add("ABC");
        rows.add("James");
        rows.add("RGK Solutions");

        rows.add("2");
        rows.add("A");
        rows.add("James");
        rows.add("GRM Publishers");

        Exception exception = assertThrows(IOException.class, () -> DbWrapper.printToCSV(header, rows, columns));

        String expectedMessage = "File writing failed";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }


    @Test
    /*
     * printToCSV method test 5:
     * Test when results.csv file is already present in the respective folder
     */
    public void fileAlreadyPresentTest() throws IOException
    {
        List<String> header = new ArrayList<>();
        List<String> rows = new ArrayList<>();
        int columns = 4;

        header.add("ISBN");
        header.add("Title");
        header.add("Author");
        header.add("Publisher");

        rows.add("1");
        rows.add("ABC");
        rows.add("James");
        rows.add("RGK Solutions");

        rows.add("2");
        rows.add("A");
        rows.add("James");
        rows.add("GRM Publishers");

        Boolean value = DbWrapper.printToCSV(header, rows, columns);
        assertTrue(value);
    }
}
