import java.io.*;
import java.sql.*;
import java.util.*;

public class DbWrapper {
    static List<List<String>> outerList;
    static List<String> header;
    static List<String> headerInCsv = new ArrayList<>();
    static List<String> rowsInCsv;
    static String tableName;
    static int columnCount;
    static String fileType;

    public static List<List<String>> readingFile(String filename) throws IllegalArgumentException, EmptyFileCustomException, Exception
    {
        if (filename != null && filename.length() != 0) {
            outerList = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = "";
            while ((line = br.readLine()) != null) {
                List<String> values = Arrays.asList(line.split(","));
                outerList.add(values);
            }
            if (outerList.size() == 0) {
                throw new EmptyFileCustomException("This is an empty file");
            }
            return outerList;
        } else {
            throw new Exception();
        }
    }


    public static List<String> getHeader(List<List<String>> outerList) throws EmptyFileCustomException {
        header = new ArrayList<>();
        if (outerList.size() != 0) {
            header = outerList.get(0);
            return header;
        } else {
            throw new EmptyFileCustomException("This is an empty file");
        }
    }


    public static boolean dbConnection()
    {
        try {
            Connection connect;
            Class.forName(DatabaseConfig.jdbcDriver);
            connect = DriverManager.getConnection(DatabaseConfig.url, DatabaseConfig.username, DatabaseConfig.password);
            return connect.isValid(1000);
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
            return false;
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("No JDBC driver");
            return false;
        } catch (SQLException e)
        {
            System.out.println("Error: url, user, password");
            return false;
        }
    }


    public static int databaseCreation()
    {
        String dbName = "Assignment1";
        String databaseCheck = "DROP DATABASE IF EXISTS " + dbName + ";";
        String query = "CREATE DATABASE IF NOT EXISTS " + dbName + ";";
        int ans;

        try {
            Connection connect;
            Class.forName(DatabaseConfig.jdbcDriver);
            connect = DriverManager.getConnection(DatabaseConfig.url, DatabaseConfig.username, DatabaseConfig.password);
            Statement stat = connect.createStatement();
            stat.execute(databaseCheck);
            ans = stat.executeUpdate(query);
            stat.close();
            connect.close();
            return ans;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return 0;
        } catch (ClassNotFoundException e) {
            System.out.println("No JDBC driver");
            return 0;
        } catch (SQLException e) {
            System.out.println("Error: url, user, password");
            return 0;
        }
    }


    public static String getTableName(String filename) throws Exception
    {
        if (filename != null && filename.length() != 0) {
            tableName = filename.substring(filename.lastIndexOf("\\") + 1);
            fileType = tableName.substring(tableName.length() - 4, tableName.length());
            if (fileType.equals(".csv")) {
                tableName = tableName.substring(0, tableName.length() - 4);
                if (tableName.length() != 0) {
                    return tableName;
                } else {
                    throw new Exception("Table name is empty");
                }
            } else {
                throw new Exception("File type given is not .csv");
            }
        } else {
            throw new Exception();
        }
    }


    public static String tableQueryGenerator(List<String> header, String tableName) throws Exception {
        if (tableName != null && tableName.length() != 0 && header.size() != 0) {
            Boolean moreThanOneWord = false;
            for (String word : header) {
                if (word.contains(" ")) moreThanOneWord = true;
            }
            if (!moreThanOneWord) {
                String temp_statement_1 = "CREATE TABLE IF NOT EXISTS " + tableName + "(";
                String temp_statement_2 = "";

                for (String column : header) {
                    temp_statement_2 += column + " VARCHAR(512), ";
                }

                String temp_statement_3 = temp_statement_1 + temp_statement_2;
                String temp_statement_4 = temp_statement_3 + "ID INT NOT NULL AUTO_INCREMENT, PRIMARY KEY(ID)";
                String finalStatement = temp_statement_4 + ");";
                return finalStatement;
            } else {
                throw new Exception("The header in the csv file contains columns with more than one word");
            }
        } else if (tableName == null) {
            throw new NullPointerException();
        } else if (tableName.length() == 0) {
            throw new Exception("Table name is empty");
        } else if (header.size() == 0) {
            throw new EmptyFileCustomException("Header size is zero");
        } else {
            throw new Exception("Table Query didn't get generated");
        }
    }


    public static int tableCreation(String finalStatement) {
        String databaseUse = "USE Assignment1";
        String tableDeletion = "DROP TABLE IF EXISTS " + tableName;
        try {
            Class.forName(DatabaseConfig.jdbcDriver);
            Connection connect = DriverManager.getConnection(DatabaseConfig.url, DatabaseConfig.username, DatabaseConfig.password);
            Statement statement = connect.createStatement();
            statement.execute(databaseUse);
            statement.execute(tableDeletion);
            statement.executeUpdate(finalStatement);

            statement.close();
            connect.close();
            return 1;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    public static String insertQueryGenerator(List<List<String>> outerList, String tableName) throws Exception {
        String finalQuery = "";
        if (tableName != null && tableName.length() != 0 && outerList.size() != 0) {
            if (outerList.size() != 1) {
                String start_1 = "INSERT INTO " + tableName + " (";
                String columnNames = "";
                for (String column : header) {
                    columnNames += column + ", ";
                }
                columnNames = columnNames.substring(0, columnNames.length() - 2);
                String start_2 = start_1 + columnNames + ") VALUES (";
                String start_4 = "";
                for (int i = 1; i < outerList.size(); i++) {
                    String start_3 = "";
                    for (String rowValue : outerList.get(i)) {
                        start_3 += "'" + rowValue + "',";
                    }
                    start_3 = start_3.substring(0, start_3.length() - 1);
                    start_4 += start_3 + "), (";
                }
                start_4 = start_4.substring(0, start_4.length() - 3);
                finalQuery = start_2 + start_4 + ";";

                return finalQuery;
            } else {
                String start_1 = "INSERT INTO " + tableName + " (";
                String columnNames = "";
                for (String column : header) {
                    columnNames += column + ", ";
                }
                columnNames = columnNames.substring(0, columnNames.length() - 2);
                finalQuery = start_1 + columnNames + ")";

                return finalQuery;
            }
        } else if (tableName == null) {
            throw new NullPointerException();
        } else if (tableName.length() == 0) {
            throw new Exception("Table name is empty");
        } else {
            throw new Exception();
        }
    }


    public static boolean insertingValues(String insertingQuery) {
        String databaseUse = "USE Assignment1";
        try {
            Class.forName(DatabaseConfig.jdbcDriver);
            Connection connect = DriverManager.getConnection(DatabaseConfig.url, DatabaseConfig.username, DatabaseConfig.password);
            Statement statement1 = connect.createStatement();
            statement1.execute(databaseUse);
            statement1.execute(insertingQuery);
            statement1.close();
            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }


    public static boolean query(String DbName, String query) throws Exception
    {
        if(DbName.equalsIgnoreCase("Assignment1")) {
            String useDatabase = "USE " + DbName + ";";
            try {
                Class.forName(DatabaseConfig.jdbcDriver);
                Connection connect = DriverManager.getConnection(DatabaseConfig.url, DatabaseConfig.username, DatabaseConfig.password);
                Statement querystat = connect.createStatement();
                querystat.execute(useDatabase);

                ResultSet result;
                result = querystat.executeQuery(query);

                ResultSetMetaData rsmd = result.getMetaData();
                columnCount = rsmd.getColumnCount();

                rowsInCsv = new ArrayList<>(columnCount);
                while (result.next()) {
                    int i = 1;
                    while (i <= columnCount) {
                        rowsInCsv.add(result.getString(i));
                        i++;
                    }

                }
                for (int i = 1; i <= columnCount; i++) {
                    String a = result.getMetaData().getColumnLabel(i);
                    headerInCsv.add(a);
                }

                querystat.close();
                connect.close();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        else
        {
            throw new Exception("Database name is not equal to the database we inserted values into.");
        }
    }


    public static boolean printToCSV(List<String> headerInCsv, List<String> rowsInCsv, int columnCount) throws IOException {
        try {
            if (headerInCsv.size() != 0 && rowsInCsv.size() != 0 && columnCount != 0) {
                FileWriter csvWriter = new FileWriter("results.csv");
                StringBuilder stringBuilder = new StringBuilder();
                for (String columnName : headerInCsv) {
                    stringBuilder.append(columnName);
                    stringBuilder.append(",");
                }
                stringBuilder.append("\n");

                int count = 1;
                for (String rowData : rowsInCsv) {
                    if (count % columnCount == 0) {
                        stringBuilder.append(rowData);
                        stringBuilder.append(", ");
                        stringBuilder.append("\n");
                        count++;
                    } else {
                        stringBuilder.append(rowData);
                        stringBuilder.append(", ");
                        count++;
                    }
                }

                csvWriter.append(stringBuilder.toString());
                csvWriter.flush();
                csvWriter.close();
                return true;
            } else {
                throw new IOException();
            }
        } catch (IOException e) {
            throw new IOException("File writing failed");
        }
    }

    public static void main(String[] args) throws Exception
    {
        String path = "";
        String database = "";
        String que = "";

        List<List<String>> outerListMain;
        List<String> headerMain;
        Boolean dbConnectionMain;
        int dbCreationMain;
        String tableNameMain;
        String tableQueryMain;
        int tableCreatedMain;
        String insertQueryMain;
        Boolean queryOutput;

        if(ArgumentsChecker.isValid(args))
        {
            if (args[0].equalsIgnoreCase("init"))
            {
                path = args[1];

                outerListMain = readingFile(path);
                if (outerListMain != null) {
                    headerMain = getHeader(outerListMain);
                    if (headerMain.size() != 0) {
                        dbConnectionMain = dbConnection();
                        if (dbConnectionMain) {
                            dbCreationMain = databaseCreation();
                            if (dbCreationMain == 1) {
                                tableNameMain = getTableName(path);
                                if (!tableNameMain.equalsIgnoreCase("Table name not generated")) {
                                    tableQueryMain = tableQueryGenerator(headerMain, tableNameMain);
                                    if (!tableQueryMain.equalsIgnoreCase("Table creation query not generated")) {
                                        tableCreatedMain = tableCreation(tableQueryMain);
                                        if (tableCreatedMain == 1) {
                                            insertQueryMain = insertQueryGenerator(outerListMain, tableNameMain);
                                            if (!insertQueryMain.equalsIgnoreCase("Inserting query not generated")) {
                                                insertingValues(insertQueryMain);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else if (args[0].equalsIgnoreCase("query"))
            {
                database = args[1];
                que = args[2];

                queryOutput = query(database, que);
                if (queryOutput) {
                    printToCSV(headerInCsv, rowsInCsv, columnCount);
                }
            }
        }
        else
        {
            throw new Exception("Wrong command. Please enter the right one.");
        }
    }
}
