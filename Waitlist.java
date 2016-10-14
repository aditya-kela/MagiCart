package magicianagentproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author adityakela
 */
public class Waitlist {

    private static Connection connection = null; // manages connection
    private static Statement statement = null; // query statement
    private static PreparedStatement insertNewEntry = null;
    private static PreparedStatement getwaitlist = null;
    private static ResultSet resultSet = null; // manages results
    

    public static void addtowaitlist(String customername, String holiday) {
        try {
            java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
            // create insert that add a new entry into database booking
            connection = DBConnection.getConnection();
            insertNewEntry = connection.prepareStatement(
                    " INSERT INTO WAITLIST" + "(Customername, Requestingholiday, Time)"
                    + " VALUES (?,?,?)");

            insertNewEntry.setString(1, customername);
            insertNewEntry.setString(2, holiday);
            insertNewEntry.setTimestamp(3, currentTimestamp);
            insertNewEntry.executeUpdate();
        }// end try
        catch (SQLException ex) {
            ex.printStackTrace();
        }// end catch

    }

    public static ArrayList<WaitlistEntry> getwaitlist() {
        ArrayList<WaitlistEntry> WaitlistStatusList = new ArrayList<>();
        try {
            connection = DBConnection.getConnection();
            getwaitlist = connection.prepareStatement(
                    " SELECT Customername, Requestingholiday "
                    + " FROM Waitlist ");
            resultSet = getwaitlist.executeQuery();

            while (resultSet.next()) {
                WaitlistEntry WE = new WaitlistEntry(resultSet.getString("Customername"), resultSet.getString("Requestingholiday"));
                WaitlistStatusList.add(WE);
                System.out.println(WE.getCustomername());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return WaitlistStatusList;
    }
}
