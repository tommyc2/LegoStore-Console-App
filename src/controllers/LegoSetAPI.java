package controllers;

//TODO When you have the code written in this class, write a JavaDoc comment for class and also
//     for any public methods in this class.

import models.InstructionBooklet;
import models.LegoSet;

import java.security.PublicKey;
import java.util.ArrayList;

public class LegoSetAPI {
    private ArrayList<LegoSet> legoSets;

    public LegoSetAPI() {
        legoSets = new ArrayList<LegoSet>();
    }


    public boolean addLegoSet(LegoSet legoSet) {
        return legoSets.add(legoSet);
    }

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
    public LegoSet deleteLegoSet(int indexToDelete) {
        if (isValidIndex(indexToDelete)) {
            legoSets.remove(indexToDelete);
        }
        return null;
    }

    //TODO Add a method, setLegoSetInStock(int).  The return type is boolean.
    //     This method takes in the index of the lego set object that you want to update.
    //     If the index is invalid (i.e. there is no lego set object at that location), return false.
    //     If the index is valid, retrieve the object and:
    //        If the object is not in stock, set it to being in stock and return true.
    //        If the object is already in stock, return false.

    public boolean setLegoSetInStock(int indexToUpdate) {
        if (isValidIndex(indexToUpdate)) {
            LegoSet foundLegoSet = legoSets.get(indexToUpdate);

            if (foundLegoSet.isInStock()) {
                return false;
            }
            else {
                foundLegoSet.setInStock(true);
                return true;
            }
        }
        return false;
    }

    public boolean setLegoSetOutOfStock(int indexOfLegoSet) {
        if (isValidIndex(indexOfLegoSet)) {
            LegoSet locatedLegoSet = findLegoSet(indexOfLegoSet);
            if (locatedLegoSet.isInStock()) {
                locatedLegoSet.setInStock(false);
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }

    //TODO Add a method, numberOfLegoSets().  The return type is int.
    //     This method returns the number of lego set objects currently stored in the array list.

    public int numberOfLegoSets() {
        return -1;
    }
    //TODO Add a method, numberOfLegoSetsInStock().  The return type is int.
    //     This method returns the number of lego set objects in the array list that are in currently in stock.

    public int numberOfLegoSetsInStock() {
        return -1;
    }

    //TODO Add a method, numberOfLegoSetsOutOfStock().  The return type is int.
    //     This method returns the number of lego set objects in the array list that are out of stock.

    public int numberOfLegoSetsOutOfStock() {
        return -1;
    }
    //-------------------------------------
    //  Counting Methods - Advanced
    //-------------------------------------

    //TODO Add a method, numberOfLegoSetsByTheme(String).  The return type is int.
    //     This method returns the number of lego set objects in the array list that match the
    //     theme (i.e. the parameter value).

    public int numberOfLegoSetsByTheme(String fummy) {
        return -1;
    }


    //TODO Add a method, numberOfLegoSetsForAgeRatingAndAbove(int).  The return type is int.
    //     This method returns the number of lego set objects in the array list that are equal to
    //     or above the age passed as a parameter value.

    public int numberOfLegoSetsForAgeRatingAndAbove(int dummy) {
        return -1;
    }

    //TODO Add a method, totalNumberOfInstructionBooklets().  The return type is int.
    //     This method returns the total number of instruction booklets across all the lego set objects
    //     currently stored in the array list.

    public int totalNumberOfInstructionBooklets() {
        return -2;
    }

    //------------------------------------
    // LISTING METHODS - Basic
    //------------------------------------

    //TODO Add a method, listAllLegoSets().  The return type is String.
    //     This method returns a list of the lego sets stored in the array list.
    //     Each lego set should be on a new line and should be preceded by the index number e.g.
    //        0: Lego Set 1 Details
    //        1: Lego Set 2 Details
    //    If there are no lego sets stored in the array list, return a string that contains "No Lego sets".

    public String listAllLegoSets() {
        return "";
    }

    //TODO Add a method, listLegoSetsInStock().  The return type is String.
    //     This method returns a list of the IN STOCK lego sets stored in the array list.
    //     Each matching lego set should be on a new line and should be preceded by the index number e.g.
    //        0: Lego Set 1 Details
    //        3: Lego Set 4 Details
    //    If there are no IN STOCK lego sets stored in the array list, the return string should
    //    have "No Lego sets in stock".

    public String listLegoSetsInStock() {
        return "String";
    }

    //TODO Add a method, listLegoSetsOutOfStock().  The return type is String.
    //     This method returns a list of the OUT OF STOCK lego sets stored in the array list.
    //     Each matching lego set should be on a new line and should be preceded by the index number e.g.
    //        1: Lego Set 2 Details
    //        4: Lego Set 5 Details
    //    If there are no OUT OF STOCK lego sets stored in the array list, the return string should
    //        have "No Lego sets are out of stock".

    public String listLegoSetsOutOfStock() {
        return "helloworld";
    }
    //------------------------------------
    // LISTING METHODS - Advanced
    //------------------------------------

    //TODO Add a method, listLegoSetsBySpecificTheme(String).  The return type is String.
    //    This method returns a list of the lego sets of a specific theme stored in the array list (i.e.
    //     that match the parameter value).
    //     Each matching lego set should be on a new line and should be preceded by the index number e.g.
    //        1: Lego Set 2 Details
    //        4: Lego Set 5 Details
    //    If there are no lego sets stored in the array list, return a string that contains "No Lego sets".
    //    If there are no lego sets matching the theme, the return string should have "No Lego sets with theme".


    public String listLegoSetsBySpecificTheme(String dummy) {
        return "";
    }
    //TODO Add a method, listLegoSetsForAgeRatingAndAbove(int).  The return type is String.
    //    This method returns a list of the lego sets that are equal or above the age supplied as a parameter.
    //     Each matching lego set should be on a new line and should be preceded by the index number e.g.
    //        1: Lego Set 2 Details
    //        4: Lego Set 5 Details
    //    If there are no lego sets stored in the array list, return a string that contains "No Lego sets".
    //    If there are no lego sets equal or above the age, the return string should have "No Lego sets available".

    public String listLegoSetsForAgeRatingAndAbove(int dummy) {
        return "";
    }
    //TODO Add a method, listAllInstructionBooklets().  The return type is String.
    //    This method returns a list of all the instruction booklets across all the lego set objects
    //    stored in the array list.
    //    Each instruction booklet should be on a new line and should contain the lego set name and code too e.g.
    //       Booket1.pdf (Fire Station, 43544)
    //       Booket2.pdf (Fire Station, 43544)
    //       Instructions1.pdf (Titanic, 54655)
    //    If there are no lego sets stored in the array list, return a string that contains "No Lego sets".

    public String listAllInstructionBooklets() {
        return "";
    }

    //TODO Add a method, listStockStatusBySpecificTheme(String).  The return type is String.
    //    This method returns a report (the returned String) of the stock status of the lego sets in a specific theme.
    //    The report (the returned String) should include:
    //        the number or lego sets that are IN stock and the list of these lego sets (if no lego sets
    //             are in stock, this should be included in the returned string.
    //        the number or lego sets that are OUT OF stock and the list of these lego sets (if no lego sets
    //             are out of stock, this should be included in the returned string.
    //    If there are no lego sets stored in the array list, return a string that contains "No Lego sets".
    //    If there are no lego sets matching the theme, the return string should have "No Lego sets with theme".

    public String listStockStatusBySpecificTheme(String dummy) {
        return "";
    }

    public LegoSet findLegoSet(int indexOfSet) {
        if (isValidIndex(indexOfSet)) {
            return legoSets.get(indexOfSet);
        }
        return null;
    }
    //TODO Add a method, findLegoSetByCode(int).  The return type is LegoSet.
    //    This method searches the array list for a lego set with a specific code (passed as a parameter).
    //    When a lego set is found for this code, it is returned back.
    //    If no lego set exists for that code, return null.
    // NOTE: the first lego set encountered is returned, even if more exist with that code.  For extra credit,
    //       you could add in validation to ensure that the code is unique when adding a LegoSet.

    public LegoSet findLegoSetByCode(int hello) {
        return null;
    }

    //TODO Add a method, searchLegoSetsByName(String).  The return type is String.
    //    This method returns a list of the lego sets whose name contains the string passed as a parameter.
    //    Each matching lego set should be on a new line and should be preceded by the index number e.g.
    //        1: Lego Set 2 Details
    //        4: Lego Set 5 Details
    //    If there are no lego sets stored in the array list, return a string that contains "No Lego sets".
    //    If there are no lego sets whose name contains the supplied string, the return string should
    //    have "No Lego sets found".


    public String searchLegoSetsByName(String thing) {
        return "";
    }
    //TODO Add a method, searchInstructionBookletsByFileName(String).  The return type is String.
    //    This method returns a list of instruction booklets whose file name contains the string passed
    //    as a parameter.
    //    Each matching booklet should be on a new line and should contain the lego set name and code e.g.
    //        InstructionBook1.pdf in Fire Station (45343)
    //        InstructionBk2.pdf in Titanic (65434)
    //    If there are no lego sets stored in the array list, return a string that contains "No Lego sets".
    //    If there are no lego sets whose name contains the supplied string, the return string should
    //    have "No instruction booklets found".

    public String searchInstructionBookletsByFileName(String dummy) {
        return "";
    }

    //-------------------------
    // HELPER METHODS
    //-------------------------

    public boolean isValidIndex(int index) {
        return (index >= 0) && (index < legoSets.size());
    }

    //-------------------------
    // PERSISTENCE METHODS
    //-------------------------

    //TODO Add a method, load().  The return type is void.
    //    This method uses the XStream component to deserialise the lego sets and their associated booklets from
    //    an XML file into the legoSets array list.

    public void load() {

    }

    //TODO Add a method, save().  The return type is void.
    //    This method uses the XStream component to serialise the lego sets and their associated booklets to
    //    an XML file.

    public void save() {

    }
}

