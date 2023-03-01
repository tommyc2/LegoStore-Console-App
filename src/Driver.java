import controllers.LegoSetAPI;
import models.InstructionBooklet;
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

    //----------------------------------------------------------------------------
    // Private methods for displaying the menu and processing the selected options
    //----------------------------------------------------------------------------

    private void runMenu() {
        int option = displayMenu();

        while (option != 0) {

            switch (option) {
                case 1 -> addLegoSet();
                case 2 -> printAllLegoSets();
                case 3 -> updateLegoSet();
                case 4 -> deleteLegoSet();
                case 5 -> setStockStatusForLegoSets();
                case 6 -> findLegoSetByCode();
                case 7 -> searchLegoSetsByName();
                case 8 -> addBookletToLegoSet();
                case 9 -> printAllInstructionBooklets();
                case 10 -> updateBookletInLegoSet();
                case 11 -> deleteBookletFromLegoSet();
                case 12 -> searchBookletsByFileName();
                case 13 -> printStockReport();
                case 14 -> printLegoSetsBySelectedTheme();
                case 15 -> printLegoSetsByMinAge();
                case 16 -> save();
                case 17 -> load();
                default -> System.out.println("Invalid option entered: " + option); // option 0 for exiting app
            }

            ScannerInput.readNextLine("\nPress enter key to continue...");

            option = displayMenu();
        }
        System.out.println("Exiting...bye");
        System.exit(0);
    }

    private int displayMenu() {
        int option = ScannerInput.readNextInt("""
                -------------------------------------------------------------------
                 |                            LEGO SET App                        |
                 ------------------------------------------------------------------
                 | Lego Set MENU:                                                 |
                 |   1) Add a lego set                                            |
                 |   2) List all lego sets                                        |
                 |   3) Update a lego set                                         | 
                 |   4) Delete a lego set                                         | 
                 |   5) Set stock status of Lego set                              |
                 |   6) Find a specific lego set (by code)                        |
                 |   7) Search for all lego sets (by name)                        |
                 ------------------------------------------------------------------
                 |   Instruction Booklet MENU:                                    |
                 |   8) Add a booklet to a lego set                               |
                 |   9) List all booklets                                         |
                 |   10) Update a booklet on a lego set                           |
                 |   11) Delete a booklet on a lego set                           |
                 |   12) Search for all booklets (by file name)                   |
                 ------------------------------------------------------------------
                 |   REPORT MENU:                                                 |  
                 |   13) Print Overall stock report                               |  
                 |   14) Print all lego sets by chosen theme                      |
                 |   15) Print all lego sets at or above a minimum age            |                               
                 ------------------------------------------------------------------
                 |   SETTINGS MENU:                                               |  
                 |   16) Save lego set                                            |  
                 |   17) Load lego set                                            |
                 |   0) Exit app                                                  |
                 ------------------------------------------------------------------
                 ==>>  """);

        return option;
    }


    //------------------------------------
    // Private methods for CRUD on LegoSet
    //------------------------------------

    private void addLegoSet() {
        String name = ScannerInput.readNextLine("Enter the lego set name: ");
        int code = ScannerInput.readNextInt("Enter the lego set code: ");
        double cost = ScannerInput.readNextDouble("Enter the lego set cost: ");
        int pieceCount = ScannerInput.readNextInt("Enter the lego set piece count: ");
        String theme = ScannerInput.readNextLine("Enter the lego set theme: ");
        int minimumAge = ScannerInput.readNextInt("Enter minimum age for set: ");

        boolean isAdded = legoSetAPI.addLegoSet(new LegoSet(name, code, cost, pieceCount, theme, minimumAge));

        if (isAdded) {
            System.out.println("Lego Set added successfully");
        } else {
            System.out.println("Your attempt was unsuccessful");
        }
    }

    private void printAllLegoSets() {
        System.out.println("List of lego sets:");
        System.out.println(legoSetAPI.listAllLegoSets());
    }

    private void printInStockLegoSets() {

    }

    private void printOutOfStockLegoSets() {

    }

    private void updateLegoSet() {
        printAllLegoSets();

        if (legoSetAPI.numberOfLegoSets() > 0) {
            int indexToUpdate = ScannerInput.readNextInt("Enter the index of the lego set: ");

            if (legoSetAPI.isValidIndex(indexToUpdate)) {

                String name = ScannerInput.readNextLine("Enter the lego set name: ");
                int code = ScannerInput.readNextInt("Enter the lego set code: ");
                double cost = ScannerInput.readNextDouble("Enter the lego set cost: ");
                int pieceCount = ScannerInput.readNextInt("Enter the lego set piece count: ");
                String theme = ScannerInput.readNextLine("Enter the lego set theme: ");
                int minimumAge = ScannerInput.readNextInt("Enter minimum age for set: ");

                boolean updatedLegoSet = legoSetAPI.updateLegoSet(indexToUpdate, name, code, cost, pieceCount, theme, minimumAge);

                if (updatedLegoSet) {
                    System.out.println("Updated lego set successfully!");
                } else {
                    System.out.println("Lego set was not successful");
                }

            } else {
                System.out.println("Not a valid index");
            }
        }
    }

    private void deleteLegoSet() {
        printAllLegoSets();

        if (legoSetAPI.numberOfLegoSets() > 0) {
            int indexToDelete = ScannerInput.readNextInt("Enter index of lego set to delete here, then press enter: ");

            LegoSet setToDelete = legoSetAPI.deleteLegoSet(indexToDelete);

            if (setToDelete == null) {
                System.out.println("This lego set was not deleted: " + setToDelete.getName());
            } else {
                System.out.println("This lego set was deleted: " + setToDelete.getName());
            }
        } else {
            System.out.println("There are no lego sets stored");
        }
    }

    private void setStockStatusForLegoSets() {

    }

    private void printLegoSetsBySelectedTheme() {
        String selectedTheme = ScannerInput.readNextLine("Enter the theme here, then press enter: ");
        System.out.println(legoSetAPI.listLegoSetsBySpecificTheme(selectedTheme));
    }

    private void printLegoSetsByMinAge() {
        int selectedMinAge = ScannerInput.readNextInt("Enter minimum age: ");
        System.out.println(legoSetAPI.listLegoSetsForAgeRatingAndAbove(selectedMinAge));
    }

    //--------------------------------------------------
    //  Private methods for CRUD on Instruction Booklets
    //--------------------------------------------------

    private void addBookletToLegoSet() {
    printAllLegoSets();
    int indexForLegoSet = ScannerInput.readNextInt("Enter the lego set to add the booklet to: ");

    if (legoSetAPI.isValidIndex(indexForLegoSet)){

        String fileName = ScannerInput.readNextLine("Enter the booklet filename here, then press enter: ");
        int numOfPages = ScannerInput.readNextInt("Enter the number of pages in the booklet here, then press enter: ");

        InstructionBooklet bookletToAdd = new InstructionBooklet(numOfPages,fileName);

        boolean bookletAdded = legoSetAPI.findLegoSet(indexForLegoSet).addInstructionBooklet(bookletToAdd);

        if (bookletAdded){
            System.out.println("Instruction booklet successfully added!");
        }
        else{
            System.out.println("Sorry, Instruction booklet was not added to lego set");
        }
    }
    }

    private void printAllInstructionBooklets() {

    }

    private void updateBookletInLegoSet() {

    }

    private void deleteBookletFromLegoSet() {

    }

    //-----------------------------------------------------------------
    //  Private methods for Search facility (for LegoSets and Booklets)
    //-----------------------------------------------------------------
    private void searchBookletsByFileName() {

    }

    private void findLegoSetByCode() {

    }

    private void searchLegoSetsByName() {

    }

    //-----------------------------
    //  Private methods for Reports
    // ----------------------------

    private void printStockReport() {

    }

    //---------------------------------
    //  Private methods for Persistence
    // --------------------------------

    private void save() {

    }

    private void load(){

    }


}