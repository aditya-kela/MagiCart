package magicianagentproject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author adityakela
 */
public class holiday {

    private static Connection connection = null; // manages connection
    private static Statement statement = null; // query statement
    private static ResultSet resultSet = null; // manages results

    public static ArrayList<String> getallholidays() {
        ArrayList<String> HolidayList = new ArrayList<String>();
        try {
            // query database
            connection = DBConnection.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(
                    "SELECT Holidayname FROM Holiday");

            while (resultSet.next()) {
                HolidayList.add(resultSet.getString("Holidayname"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return HolidayList;

    }
}
