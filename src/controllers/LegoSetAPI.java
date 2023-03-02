package controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.InstructionBooklet;
import models.LegoSet;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * LegoSetAPI manages ArrayList of LegoSets
 *
 * @author Tommy Condon, 20101841, W2
 */

public class LegoSetAPI {
    private ArrayList<LegoSet> legoSets;

    public LegoSetAPI() {
        legoSets = new ArrayList<LegoSet>();
    }


    /**
     * This method adds a lego set object to an arraylist of lego sets
     *
     * @param legoSet Accepts a legoset object as a parameter
     * @return returns true if legoset has been added.
     */
    public boolean addLegoSet(LegoSet legoSet) {
        return legoSets.add(legoSet);
    }

    /**
     * This method update a current lego set in the arraylist of legosets
     *
     * @param indexToUpdate     takes in legoset object index
     * @param updatedName       updated name passed in
     * @param updatedCode       updated code passed in
     * @param updatedCost       updated cost passed in
     * @param updatedPieceCount updated piece count passed in
     * @param updatedTheme      updated theme passed in
     * @param updatedMinimumAge updated minimum age passed in
     * @return returns true if updated successfully, false otherwise
     */
    public boolean updateLegoSet(int indexToUpdate, String updatedName, int updatedCode, double updatedCost, int updatedPieceCount, String updatedTheme, int updatedMinimumAge) {
        LegoSet locatedLegoSet = findLegoSet(indexToUpdate);

        if (locatedLegoSet != null) {
            locatedLegoSet.setName(updatedName);
            locatedLegoSet.setCode(updatedCode);
            locatedLegoSet.setCost(updatedCost);
            locatedLegoSet.setPieceCount(updatedPieceCount);
            locatedLegoSet.setTheme(updatedTheme);
            locatedLegoSet.setMinimumAge(updatedMinimumAge);
            return true;
        }
        return false;
    }

    /**
     * This method deletes a legoset in the arraylist
     *
     * @param indexToDelete index of legoset to delete is passed in
     * @return returns the legoset to be deleted if true, false if the legoset is null
     */
    public LegoSet deleteLegoSet(int indexToDelete) {
        if (isValidIndex(indexToDelete)) {
            return legoSets.remove(indexToDelete);
        }
        return null;
    }

    /**
     * This method sets the legoset stock status to in stock
     *
     * @param indexToUpdate index of legoset passed in
     * @return sets stock status if its a valid index passed. Returns false if legoset is already in stock, returns true otherwise.
     */
    public boolean setLegoSetInStock(int indexToUpdate) {
        if (isValidIndex(indexToUpdate)) {
            LegoSet foundLegoSet = legoSets.get(indexToUpdate);

            if (foundLegoSet.isInStock()) {
                return false;
            } else {
                foundLegoSet.setInStock(true);
                return true;
            }
        }
        return false;
    }

    /**
     * This method sets stock status of legoset to out of stock
     *
     * @param indexOfLegoSet index of legoset passed in
     * @return Checks if it's a valid index passed in. Then, returns true if legoset is in stock, returns false if legoset isnt in stock
     */

    public boolean setLegoSetOutOfStock(int indexOfLegoSet) {
        if (isValidIndex(indexOfLegoSet)) {
            LegoSet locatedLegoSet = findLegoSet(indexOfLegoSet);
            if (locatedLegoSet.isInStock()) {
                locatedLegoSet.setInStock(false);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * This method returns the size of the legoset array list
     *
     * @return returns the size of the legoset array list
     */
    public int numberOfLegoSets() {
        return legoSets.size();
    }

    /**
     * This method returns the number of lego sets in stock
     *
     * @return returns the number in stock
     */
    public int numberOfLegoSetsInStock() {
        int numInStock = 0;
        for (LegoSet legoSet : legoSets) {
            if (legoSet.isInStock()) {
                numInStock += 1;
            }
        }
        return numInStock;
    }

    /**
     * This method returns the number of lego sets out of stock
     *
     * @return returns the number out of stock
     */
    public int numberOfLegoSetsOutOfStock() {
        int numOutOfStock = 0;
        for (int i = 0; i < legoSets.size(); i++) {
            if (!legoSets.get(i).isInStock()) {
                numOutOfStock += 1;
            }
        }
        return numOutOfStock;
    }

    /**
     * This method returns the number of lego sets by theme as specified
     *
     * @param themeName takes in theme name
     * @return returns the number of sets by theme
     */
    public int numberOfLegoSetsByTheme(String themeName) {
        if (legoSets.isEmpty()) {
            return 0;
        } else {
            int numOfSetsThatMatchTheme = 0;
            for (LegoSet legoSet : legoSets) {
                if (legoSet.getTheme().equalsIgnoreCase(themeName)) {
                    numOfSetsThatMatchTheme += 1;
                }
            }
            if (numOfSetsThatMatchTheme == 0) {
                return 0;
            } else {
                return numOfSetsThatMatchTheme;
            }
        }
    }

    /**
     * This method returns the number of lego sets for an age rating passed in as param or above
     *
     * @param minAge minimum age passed in as integer
     * @return returns the number of sets for the minimum age or above
     */
    public int numberOfLegoSetsForAgeRatingAndAbove(int minAge) {
        int numOfSetsForRatingPlus = 0;
        for (LegoSet legoSet : legoSets) {
            if (legoSet.getMinimumAge() >= minAge) {
                numOfSetsForRatingPlus++;
            }
        }
        return numOfSetsForRatingPlus;
    }

    /**
     * This method returns the total number of instruction booklets across all legoset objects in the legoset array list
     *
     * @return returns the total number of instruction booklets
     */
    public int totalNumberOfInstructionBooklets() {
        int total = 0;
        for (LegoSet legoset : legoSets) {
            total += legoset.numberOfInstructionBooklets();
        }
        return total;
    }

    /**
     * This method returns the list of all legosets in the LegoSets array list
     *
     * @return returns the above
     */
    public String listAllLegoSets() {
        String listOfALlLegoSets = "";
        if (!legoSets.isEmpty()) {
            for (int i = 0; i < legoSets.size(); i++) {
                listOfALlLegoSets += "[" + i + "]: " + legoSets.get(i) + "\n";
            }
            return listOfALlLegoSets;
        } else {
            return "Sorry, No Lego Sets in the list";
        }
    }

    /**
     * this method creates a list of lego sets in stock
     *
     * @return returns the list that are in stock
     */
    public String listLegoSetsInStock() {
        String listOfSetsInStock = "";
        for (int i = 0; i < legoSets.size(); i++) {
            if (legoSets.get(i).isInStock()) {
                listOfSetsInStock += "[" + i + "]: " + legoSets.get(i) + "\n";
            }
        }
        if (listOfSetsInStock.equals("")) {
            return "No Lego Sets in stock";
        } else {
            return listOfSetsInStock;
        }
    }

    /**
     * This method creates a string of lego sets out of stock
     *
     * @return returns the list of sets out of stock
     */
    public String listLegoSetsOutOfStock() {
        String listOfSetsOutOfStock = "";
        for (int i = 0; i < legoSets.size(); i++) {
            if (!legoSets.get(i).isInStock()) {
                listOfSetsOutOfStock += "[" + i + "]: " + legoSets.get(i) + "\n";
            }
        }
        if (!listOfSetsOutOfStock.equals("")) { // If there are LegoSets in the out of stock string, then return it.
            return listOfSetsOutOfStock;
        } else {
            return "No lego sets out of stock"; // If there is an empty list of out
            // of stock items, then return no sets out of stock
        }
    }

    /**
     * This method creates a string list of all lego sets that match a specific theme
     *
     * @param theme theme name passed in
     * @return returns the list of lego sets by that theme
     */
    public String listLegoSetsBySpecificTheme(String theme) {
        if (legoSets.isEmpty()) {
            return "no lego sets stored";
        } else {
            String listOfSetsMatchingTheme = "";
            for (int i = 0; i < legoSets.size(); i++) {
                if (legoSets.get(i).getTheme().toLowerCase().contains(theme.toLowerCase())) {
                    listOfSetsMatchingTheme += "[" + i + "]: " + legoSets.get(i).toString() + "\n";
                }
            }
            if (listOfSetsMatchingTheme.equals("")) {
                return "no lego sets with theme";
            } else {
                return listOfSetsMatchingTheme;
            }
        }
    }

    /**
     * This method creates a list of lego sets for an age rating or above
     *
     * @param minAge age rating passed in
     * @return returns the list of lego sets for that age rating and/or above
     */
    public String listLegoSetsForAgeRatingAndAbove(int minAge) {
        if (legoSets.isEmpty()) {
            return "No Lego sets";
        } else {
            String listOfSetsEqualOrAbove = "";
            for (int i = 0; i < legoSets.size(); i++) {
                if (legoSets.get(i).getMinimumAge() >= minAge) {
                    listOfSetsEqualOrAbove += "[" + i + "]: " + legoSets.get(i).toString() + "\n";
                }

            }
            if (listOfSetsEqualOrAbove.equals("")) {
                return "no lego sets available for age";
            } else {
                return listOfSetsEqualOrAbove;
            }
        }
    }

    /**
     * This method creates a list of instruction booklets across each legoset object
     *
     * @return returns the list of instruction booklets as a string
     */
    public String listAllInstructionBooklets() {
        if (legoSets.isEmpty()) {
            return "no lego sets stored";
        } else {
            String listOfBooklets = "";
            for (LegoSet legoSet : legoSets) {
                listOfBooklets += "LegoSet Name: " + legoSet.getName() + ", " + legoSet.getCode() + "\n" +
                        legoSet.listInstructionBooklets() + "\n";
            }

            if (listOfBooklets.equals("")) {
                return "no instruction booklets";
            } else {
                return listOfBooklets;
            }
        }

    }

    /**
     * This method creates a list of legosets. Each legoset must match the theme name passed in as a parameter.
     *
     * @param theme theme name passed in
     * @return returns a list of legosets in stock and out of stock that match the specific theme
     */
    public String listStockStatusBySpecificTheme(String theme) {

        if (legoSets.isEmpty()) {
            return "no lego sets stored";
        } else {
            int totalInStock = 0;
            int totalOutStock = 0;
            String listInStock = "";
            String listOutStock = "";
            for (LegoSet legoSet : legoSets) {
                if (legoSet.getTheme().equalsIgnoreCase(theme)) {
                    if (legoSet.isInStock()) {
                        listInStock += legoSet.getName() + "\n";
                        totalInStock++;
                    } else {
                        listOutStock += legoSet.getName() + "\n";
                        totalOutStock++;
                    }
                }
            }
            if ((totalInStock != 0) || (totalOutStock != 0)) {
                String InStockList = "Number of lego sets in stock: " + totalInStock + "\n" + listInStock;
                String OutStockList = "Number of lego sets out of stock: " + totalOutStock + "\n" + listOutStock;
                String fullList = InStockList + OutStockList;
                return fullList;
            } else {
                return "no lego sets with theme";
            }
        }
    }

    /**
     * This method locates a legoset object
     *
     * @param indexOfSet index of the lego set passed in
     * @return returns the legoset object at that index
     */
    public LegoSet findLegoSet(int indexOfSet) {
        if (isValidIndex(indexOfSet)) {
            return legoSets.get(indexOfSet);
        }
        return null;
    }

    /**
     * This method finds a lego set by its code
     *
     * @param code legoset code passed in
     * @return returns the legoset object with that code
     */
    public LegoSet findLegoSetByCode(int code) {
        LegoSet foundLegoSet = null;
        for (int i = 0; i < legoSets.size(); i++) {
            if (legoSets.get(i).getCode() == code) {
                foundLegoSet = legoSets.get(i);
            }
        }
        if (foundLegoSet == null) {
            return null;
        } else {
            return foundLegoSet;
        }
    }

    /**
     * This method searches for a lego set by a name keyword
     *
     * @param name set name passed in
     * @return returns the lego sets in a string with that name
     */
    public String searchLegoSetsByName(String name) {
        if (legoSets.isEmpty()) {
            return "no lego sets stored";
        } else {
            String foundLegoSet = "";
            for (int i = 0; i < legoSets.size(); i++) {
                if (legoSets.get(i).getName().toLowerCase().contains(name)) {
                    foundLegoSet += "[" + i + "]: " + legoSets.get(i).toString();
                }

            }
            if (foundLegoSet.equals("")) {
                return "no lego sets found";
            } else {
                return foundLegoSet;
            }

        }
    }

    /**
     * This method searches for instruction booklets across the legoset arraylist
     *
     * @param fileName booklet filename passed in as a parameter
     * @return returns the list of booklets with that filename
     */

    public String searchInstructionBookletsByFileName(String fileName) {
        if (legoSets.isEmpty()) {
            return "no lego sets stored";
        } else {
            String bookletThatContainsFileName = "";
            for (LegoSet legoSet : legoSets) {
                for (InstructionBooklet instructionBooklet : legoSet.getInstructionBooklets()) {
                    if (instructionBooklet.getFileName().toLowerCase().contains(fileName.toLowerCase())) {
                        bookletThatContainsFileName += instructionBooklet.getFileName() + " in " + legoSet.getName() + " (" + legoSet.getCode() + ")";
                    }
                }
            }
            if (bookletThatContainsFileName.equalsIgnoreCase("")) {
                return "no instruction booklets found";
            }
            return bookletThatContainsFileName;
        }

    }

    /**
     * This method validates whether the index passed in is a valid legoset object index in the arraylist of legosets
     *
     * @param index index passed in
     * @return returns true if the index is valid, false otherwise
     */
    public boolean isValidIndex(int index) {
        return (index >= 0) && (index < legoSets.size());
    }

    /**
     * This method loads saved Lego sets and booklets stored in legoSets.xml
     *
     * @throws Exception loads saved legosets provided there isnt an error
     */
    public void load() throws Exception {
        Class<?>[] classes = new Class[]{LegoSet.class, InstructionBooklet.class};
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("legoSets.xml"));
        legoSets = (ArrayList<LegoSet>) is.readObject();
        is.close();
    }

    /**
     * This method saves added legosets
     *
     * @throws Exception saves legosets in XML file
     */
    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("legoSets.xml"));
        out.writeObject(legoSets);
        out.close();
    }
}

