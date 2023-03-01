import controllers.LegoSetAPI;
import models.LegoSet;
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
             ---------------------------------------------------------------------
                |                            LEGO SET App                        |
                ------------------------------------------------------------------
                | Lego Set MENU:                                                 |
                |   1) Add a lego set                                            |
                |   2) List all lego sets                                        |
                |   3) Update a lego set                                         | 
                |   4) Delete a lego set                                         | 
                |   5) Set stock status of Lego set                              |
                |   6) Find a specific lego set (by code)                        |
                |   7) Find a specific lego set (by name)                        |
                ------------------------------------------------------------------
                |   Instruction Booklet MENU:                                   |
                |   8) Add a booklet to a lego set                               |
                |   9) List all booklets                                         |
                |   10) Update a booklet on a lego set                           |
                |   11) Delete a booklet on a lego set                           |
                |   12) Search for all booklets (by file name)                   |
                ------------------------------------------------------------------
                |   REPORT MENU:                                                 |  
                |   13) Print Overall stock report                               |  
                |   14) Print all lego sets by chosen theme                      |
                |   15) Print all lego sets at or above a minimum age            |                               |  
                ------------------------------------------------------------------
                ==>>  """);

            return option;
        }


    //------------------------------------
    // Private methods for CRUD on LegoSet
    //------------------------------------

    private void addLegoSet(){
        String name = ScannerInput.readNextLine("Enter the lego set name: ");
        int code = ScannerInput.readNextInt("Enter the lego set code: ");
        double cost = ScannerInput.readNextDouble("Enter the lego set cost: ");
        int pieceCount = ScannerInput.readNextInt("Enter the lego set piece count: ");
        String theme = ScannerInput.readNextLine("Enter the lego set theme: ");
        int minimumAge = ScannerInput.readNextInt("Enter minimum age for set: ");

        boolean isAdded = legoSetAPI.addLegoSet(new LegoSet(name,code,cost,pieceCount,theme,minimumAge));

        if (isAdded){
            System.out.println("Lego Set added successfully");
        }
        else{
            System.out.println("Your attempt was unsuccessful");
        }
    }

    private void printAllLegoSets(){
        System.out.println("List of lego sets:");
        System.out.println(legoSetAPI.listAllLegoSets());
    }

    private void printInStockLegoSets(){

    }

    private void printOutOfStockLegoSets(){

    }

    private void updateLegoSet(){
    printAllLegoSets();

    if (legoSetAPI.numberOfLegoSets() > 0){
        int indexToUpdate = ScannerInput.readNextInt("Enter the index of the lego set: ");

        if (legoSetAPI.isValidIndex(indexToUpdate)){

            String name = ScannerInput.readNextLine("Enter the lego set name: ");
            int code = ScannerInput.readNextInt("Enter the lego set code: ");
            double cost = ScannerInput.readNextDouble("Enter the lego set cost: ");
            int pieceCount = ScannerInput.readNextInt("Enter the lego set piece count: ");
            String theme = ScannerInput.readNextLine("Enter the lego set theme: ");
            int minimumAge = ScannerInput.readNextInt("Enter minimum age for set: ");

            boolean updatedLegoSet = legoSetAPI.updateLegoSet(indexToUpdate,name,code,cost,pieceCount,theme,minimumAge);

            if (updatedLegoSet){
                System.out.print("Updated lego set successfully!");
            }
            else{
                System.out.println("Lego set was not successful");
            }

        }
        else{
            System.out.println("Not a valid index");
        }
    }
    }

    private void deleteLegoSet(){
    printAllLegoSets();

    if (legoSetAPI.numberOfLegoSets() > 0) {
        int indexToDelete = ScannerInput.readNextInt("Enter index of lego set to delete here, then press enter: ");

       LegoSet setToDelete = legoSetAPI.deleteLegoSet(indexToDelete);

       if (setToDelete == null){
           System.out.println("This lego set was not deleted: " + setToDelete.getName());
    }
       else{
           System.out.println("This lego set was deleted: " + setToDelete.getName());
       }
    }
    else{
        System.out.println("There are no lego sets stored");
    }
    }

    private void setStockStatusForLegoSets(){

    }

    private void printLegoSetsBySelectedTheme(){
    String selectedTheme = ScannerInput.readNextLine("Enter the theme here, then press enter: ");
    System.out.println(legoSetAPI.listLegoSetsBySpecificTheme(selectedTheme));
    }

    private void printLegoSetsByMinAge(){

    }

    //--------------------------------------------------
    //  Private methods for CRUD on Instruction Booklets
    //--------------------------------------------------

    private void addBookletToLegoSet(){

    }

    private void printAllInstructionBooklets(){

    }

    private void updateBookletInLegoSet(){

    }

    private void deleteBookletFromLegoSet(){

    }

    //-----------------------------------------------------------------
    //  Private methods for Search facility (for LegoSets and Booklets)
    //-----------------------------------------------------------------
    private void searchBookletsByFileName(){

    }
    private void findLegoSetByCode(){

    }
    private void searchLegoSetsByName(){

    }

    //-----------------------------
    //  Private methods for Reports
    // ----------------------------

    private void printStockReport(){

    }

    //---------------------------------
    //  Private methods for Persistence
    // --------------------------------

    private void save(){

    }


}