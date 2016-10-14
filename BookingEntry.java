package magicianagentproject;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author adityakela
 */
public class BookingEntry {

    private String Customername;
    private String Holiday;
    private String Magician;

    /**
     * @return the Customername
     */
    public String getCustomername() {
        return Customername;
    }

    /**
     * @return the Holiday
     */
    public String getHoliday() {
        return Holiday;
    }

    /**
     * @return the Magician
     */
    public String getMagician() {
        return Magician;
    }

    public BookingEntry(String givenCustomername, String givenHoliday, String givenMagician) {
        this.Customername = givenCustomername;
        this.Holiday = givenHoliday;
        this.Magician = givenMagician;

    }
}
