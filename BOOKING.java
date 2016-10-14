/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicianagentproject;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public class BOOKING {

    private static Connection connection = null; // manages connection
    private static Statement statement = null; // query statement
    private static PreparedStatement insertNewEntry = null;
    private static PreparedStatement getfreemagicians = null;
    private static PreparedStatement getmagicianstatus = null;
    private static PreparedStatement getholidaystatus = null;
    private static ResultSet resultSet = null; // manages results

    public static void addbooking(String magicianname, String customername, String holiday) {
        try {
            // create insert that add a new entry into database booking
            java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
            connection = DBConnection.getConnection();
            insertNewEntry = connection.prepareStatement(
                    "INSERT INTO BOOKING" + "(Magicianname, Customername, Holiday, Time)"
                    + "VALUES (?,?,?,?)");

            insertNewEntry.setString(1, magicianname);
            insertNewEntry.setString(2, customername);
            insertNewEntry.setString(3, holiday);
            insertNewEntry.setTimestamp(4, currentTimestamp);
            insertNewEntry.executeUpdate();
        }// end try
        catch (SQLException ex) {
            ex.printStackTrace();
        }// end catch

    }

    public static String getfreemagicians(String holiday) {
        try {
            connection = DBConnection.getConnection();
            getfreemagicians = connection.prepareStatement(
                    " SELECT Magicianname FROM Magician "
                    + " WHERE Magicianname not in ("
                    + " SELECT Magicianname FROM Booking "
                    + " WHERE Holiday = ?)");
            getfreemagicians.setString(1, holiday);
            resultSet = getfreemagicians.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1);
            } else {
                return null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static ArrayList<BookingEntry> getmagicianStatus(String magicianname) {
        ArrayList<BookingEntry> MagicianStatusList = new ArrayList<BookingEntry>();
        try {
            // query database
            connection = DBConnection.getConnection();
            getmagicianstatus = connection.prepareStatement(
                    " SELECT Customername, Holiday "
                    + " FROM BOOKING "
                    + " WHERE Magicianname = ? ");

            getmagicianstatus.setString(1, magicianname);
            resultSet = getmagicianstatus.executeQuery();

            while (resultSet.next()) {
                BookingEntry BE = new BookingEntry(resultSet.getString("Customername"), resultSet.getString("Holiday"), magicianname);
                MagicianStatusList.add(BE);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return MagicianStatusList;
    }

    public static ArrayList<BookingEntry> getholidayStatus(String holidayname) {
        ArrayList<BookingEntry> HolidayStatusList = new ArrayList<BookingEntry>();
        try {
            // query database
            connection = DBConnection.getConnection();
            getmagicianstatus = connection.prepareStatement(
                    "SELECT Magicianname, Customername, Time "
                    + " FROM BOOKING "
                    + " WHERE Holiday = ? ");
            getmagicianstatus.setString(1, holidayname);
            resultSet = getmagicianstatus.executeQuery();

            while (resultSet.next()) {
                BookingEntry BE = new BookingEntry(resultSet.getString("Customername"), holidayname, resultSet.getString("Magicianname"));
                HolidayStatusList.add(BE);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return HolidayStatusList;
    }
}
