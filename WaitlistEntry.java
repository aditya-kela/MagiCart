/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicianagentproject;

/**
 *
 * @author adityakela
 */
public class WaitlistEntry {

    private String Customername;
    private String Holiday;

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

    public WaitlistEntry(String givenCustomername, String givenHoliday) {
        this.Customername = givenCustomername;
        this.Holiday = givenHoliday;

    }

}
