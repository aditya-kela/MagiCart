/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicianagentproject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Magician {

    private static Connection connection = null; // manages connection
    private static Statement statement = null; // query statement
    private static ResultSet resultSet = null; // manages results

    public static ArrayList<String> getallmagicians() {
        ArrayList<String> MagList = new ArrayList<String>();
        try {
            // query database
            connection = DBConnection.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(
                    "SELECT Magicianname FROM Magician");

            while (resultSet.next()) {
                MagList.add(resultSet.getString("Magicianname"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return MagList;
    }

}
