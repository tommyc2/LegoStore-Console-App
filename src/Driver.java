import controllers.LegoSetAPI;
import utils.ScannerInput;

public class Driver {

    private LegoSetAPI legoSetAPI = new LegoSetAPI();

    public static void main(String[] args) {
        new Driver();
    }

    public Driver() {
    this.runMenu();
    }

    //TODO Refer to the tutors instructions for building this class.  You are free to deviate in any way
    //     from the Driver menu that is in the tutors instructions, once you have these included:
    //       - CRUD on LegoSet
    //       - CRUD on Instruction Booklets
    //       - Search facility (for LegoSets and Booklets)
    //       - Reports
    //       - Persistence
    // Note:  This is the ONLY class that can talk to the user i.e. have System.out.print and Scanner reads in it.

    //----------------------------------------------------------------------------
    // Private methods for displaying the menu and processing the selected options
    //----------------------------------------------------------------------------

    private void runMenu() {
        int option = displayMenu();

        while (option != 0) {

            switch (option) {

                default -> System.out.println("Invalid option entered: " + option);
            }

            ScannerInput.readNextLine("\nPress enter key to continue...");

            option = displayMenu();
        }
        System.out.println("Exiting...bye");
        System.exit(0);
    }

    private int displayMenu(){
            int option = ScannerInput.readNextInt("""
             ------------------------------------------------------------------
                |                            LEGO SET App                           |
                ------------------------------------------------------------------
                | Lego Set Menu:                                        
                |   1) Add a lego set                                            |
                |   2) List the Products                                         |
                |   3) Update a product                                          | 
                |   4) Delete a product                                          | 
                ------------------------------------------------------------------
                |   5) List the current products                                 
                |   6) Display average product unit cost                         |
                |   7) Display cheapest product                                  |
                |   8) List products that are more expensive than a given price  |
                ------------------------------------------------------------------
                |   9)  Save products to products.xml                            |  
                |   10) Load products from products.xml                          |  
                |   0)  Exit                                                     |  
                ------------------------------------------------------------------
                ==>>  """);

            return option;
        }


    //------------------------------------
    // Private methods for CRUD on LegoSet
    //------------------------------------


    //--------------------------------------------------
    //  Private methods for CRUD on Instruction Booklets
    //--------------------------------------------------


    //-----------------------------------------------------------------
    //  Private methods for Search facility (for LegoSets and Booklets)
    //-----------------------------------------------------------------


    //-----------------------------
    //  Private methods for Reports
    // ----------------------------


    //---------------------------------
    //  Private methods for Persistence
    // --------------------------------

}