import controllers.LegoSetAPI;
import models.Colours;
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
                \033[0;33m
                -------------------------------------------------------------------
                 |                            LEGO SET App                        |
                 ------------------------------------------------------------------\033[0;36m
                  Lego Set MENU:                                                 \033[0;33m
                 |   1) Add a lego set                                            |
                 |   2) List all lego sets                                        |
                 |   3) Update a lego set                                         | 
                 |   4) Delete a lego set                                         | 
                 |   5) Set stock status of Lego set                              |
                 |   6) Find a specific lego set (by code)                        |
                 |   7) Search for all lego sets (by name)                        |
                 ------------------------------------------------------------------\033[0;36m
                    Instruction Booklet MENU:                                    \033[0;33m
                 |   8) Add a booklet to a lego set                               |
                 |   9) List all booklets                                         |
                 |   10) Update a booklet on a lego set                           |
                 |   11) Delete a booklet on a lego set                           |
                 |   12) Search for all booklets (by file name)                   |
                 ------------------------------------------------------------------\033[0;36m
                    REPORT MENU:                                                 \033[0;33m
                 |   13) Print Overall stock report                               |  
                 |   14) Print all lego sets by chosen theme                      |
                 |   15) Print all lego sets at or above a minimum age            |                               
                 ------------------------------------------------------------------\033[0;36m
                    SETTINGS MENU:                                               \033[0;33m
                 |   16) Save                                                     |  
                 |   17) Load                                                     |
                 |   0) Exit app                                                  |
                 ------------------------------------------------------------------
                 ==>>  \033[0m""");

        return option;
    }


    //------------------------------------
    // Private methods for CRUD on LegoSet
    //------------------------------------

    private void addLegoSet() {
        String name = ScannerInput.readNextLine(Colours.GREEN + "Enter the lego set name: " + Colours.RESET);
        int code = ScannerInput.readNextInt(Colours.GREEN + "Enter the lego set code: " + Colours.RESET);
        double cost = ScannerInput.readNextDouble(Colours.GREEN + "Enter the lego set cost: " + Colours.RESET);
        int pieceCount = ScannerInput.readNextInt(Colours.GREEN + "Enter the lego set piece count: " + Colours.RESET);
        String theme = ScannerInput.readNextLine(Colours.GREEN + "Enter the lego set theme: " + Colours.RESET);
        int minimumAge = ScannerInput.readNextInt(Colours.GREEN + "Enter minimum age for set: " + Colours.RESET);

        boolean isAdded = legoSetAPI.addLegoSet(new LegoSet(name, code, cost, pieceCount, theme, minimumAge));

        if (isAdded) {
            System.out.println("Lego Set added successfully");
        } else {
            System.out.println("Your attempt was unsuccessful");
        }
    }

    private void printAllLegoSets() {
        System.out.println(Colours.GREEN + "List of lego sets:" + Colours.RESET);
        System.out.println(legoSetAPI.listAllLegoSets());
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
        int option = ScannerInput.readNextInt("Type '1' to set lego set in stock, Type '2' to set lego set out of stock: ");

        if (option == 1) {
            setLegoSetInStock();
        } else if (option == 2) {
            setLegoSetOutOfStock();
        } else {
            System.out.println("Please type a valid number (1 or 2)");
            setStockStatusForLegoSets();
        }
    }

    private void setLegoSetInStock() {
        // Set Lego Set to IN STOCK //
        int indexToSetInStock = ScannerInput.readNextInt("Enter the index of the lego set: ");
        boolean isDone = legoSetAPI.setLegoSetInStock(indexToSetInStock);

        if (isDone) {
            System.out.println("Lego set is now in stock!");
        } else {
            System.out.println("Lego set is already in stock!");
        }

    }

    private void setLegoSetOutOfStock() {
        // Set Lego Set to OUT OF STOCK //
        int indexToSetOutOfStock = ScannerInput.readNextInt("Enter the index of the lego set: ");
        boolean isDone = legoSetAPI.setLegoSetOutOfStock(indexToSetOutOfStock);

        if (isDone) {
            System.out.println("Lego set is now out of stock!");
        } else {
            System.out.println("Lego set is already out of stock!");
        }
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

        if (legoSetAPI.isValidIndex(indexForLegoSet)) {

            String fileName = ScannerInput.readNextLine("Enter the booklet filename here, then press enter: ");
            int numberOfPages = ScannerInput.readNextInt("Enter the number of pages in the booklet here, then press enter: ");
            InstructionBooklet bookletToAdd = new InstructionBooklet(numberOfPages, fileName);

            boolean bookletAdded = legoSetAPI.findLegoSet(indexForLegoSet).addInstructionBooklet(bookletToAdd);

            if (bookletAdded) {
                System.out.println("Instruction booklet successfully added to lego set!");
            } else {
                System.out.println("Sorry, Instruction booklet was not added to the lego set");
            }
        }
    }

    private void printAllInstructionBooklets() {
        System.out.println("List of all instruction booklets in all lego sets: ");
        System.out.println(legoSetAPI.listAllInstructionBooklets());
    }

    private void updateBookletInLegoSet() {
        printAllInstructionBooklets();

        //Lego set index
        int indexOfLegoSet = ScannerInput.readNextInt("Enter the index of the booklet's lego set: ");
        // Booklet index
        int indexOfBooklet = ScannerInput.readNextInt("Enter the index of the booklet: ");

        try {
            if (legoSetAPI.findLegoSet(indexOfLegoSet).isValidIndex(indexOfBooklet)) {

                String newFileName = ScannerInput.readNextLine("Enter the new file name of the booklet: ");
                int newNumOfPages = ScannerInput.readNextInt("Enter the new number of pages of the booklet: ");

                boolean updatedLegoSetBooklet = legoSetAPI.findLegoSet(indexOfLegoSet).updateInstructionBooklet(indexOfBooklet, newFileName, newNumOfPages);

                if (updatedLegoSetBooklet) {
                    System.out.println("Updated booklet successfully!");
                } else {
                    System.out.println("Booklet update was not successful");
                }

            } else {
                System.out.println("Not a valid index of a lego set booklet");
            }
        } catch (Exception error) {
            System.out.println(Colours.RED + "\n" + "Error occurred - this might be a non-existent lego set: " + Colours.RESET
                    + "\n" + Colours.ORANGE + error + Colours.RESET);
        }

    }


    private void deleteBookletFromLegoSet() {
        printAllLegoSets();

        if (legoSetAPI.totalNumberOfInstructionBooklets() > 0) {
            int indexOfLegoSetToDelete = ScannerInput.readNextInt("Enter index of booklet's lego set: ");
            int indexOfBookletToDelete = ScannerInput.readNextInt("Enter index of booklet to delete: ");

            InstructionBooklet bookletToDelete = legoSetAPI.findLegoSet(indexOfLegoSetToDelete).deleteInstructionBooklet(indexOfBookletToDelete);

            if (bookletToDelete == null) {
                System.out.println("This lego set was not deleted: " + bookletToDelete.getFileName());
            } else {
                System.out.println("This lego set was deleted: " + bookletToDelete.getFileName());
            }
        } else {
            System.out.println("There are no lego set booklets stored");
        }
    }

    //-----------------------------------------------------------------
    //  Private methods for Search facility (for LegoSets and Booklets)
    //-----------------------------------------------------------------
    private void searchBookletsByFileName() {
        String fileName = ScannerInput.readNextLine("Enter the filename of the booklet to find: ");
        System.out.println(legoSetAPI.searchInstructionBookletsByFileName(fileName));
    }

    private void findLegoSetByCode() {
        int code = ScannerInput.readNextInt("Enter the lego set code: ");
        System.out.println(legoSetAPI.findLegoSetByCode(code));
    }

    private void searchLegoSetsByName() {
        String name = ScannerInput.readNextLine("Enter the lego set name: ");
        System.out.println(legoSetAPI.searchLegoSetsByName(name));
    }

    //-----------------------------
    //  Private methods for Reports
    // ----------------------------

    private void printStockReport() {
        System.out.println("--- Overall Stock Report Year Ending 2022 ---");
        printInStockLegoSets();
        printOutOfStockLegoSets();
    }

    private void printInStockLegoSets() {
        System.out.println("\n" + "--- List of in stock lego sets ---");
        System.out.println(legoSetAPI.numberOfLegoSetsInStock());
        System.out.println(legoSetAPI.listLegoSetsInStock());
    }

    private void printOutOfStockLegoSets() {
        System.out.println("\n" + "--- List of out of stock lego sets ---");
        System.out.println(legoSetAPI.numberOfLegoSetsOutOfStock());
        System.out.println(legoSetAPI.listLegoSetsOutOfStock());
    }

    //---------------------------------
    //  Private methods for Persistence
    // --------------------------------

    private void save() {
        try {
            legoSetAPI.save();
        } catch (Exception e) {
            System.err.println("Error occurred: " + e);
        }
    }

    private void load() {
        try {
            legoSetAPI.load();
        } catch (Exception e) {
            System.err.println("Error occurred: " + e);
        }
    }


}