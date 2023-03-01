package controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.LegoSet;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

//TODO When you have the code written in this class, write a JavaDoc comment for class and also
//     for any public methods in this class.

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
            return legoSets.remove(indexToDelete);
        }
        return null;
    }

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

    public int numberOfLegoSets() {
        return legoSets.size();
    }

    public int numberOfLegoSetsInStock() {
        int numInStock = 0;
        for(LegoSet legoSet : legoSets) {
            if (legoSet.isInStock()) {
                numInStock += 1;
            }
        }
        return numInStock;
    }

    public int numberOfLegoSetsOutOfStock() {
        int numOutOfStock = 0;
        for(int i = 0; i < legoSets.size(); i++) {
            if (!legoSets.get(i).isInStock()) {
                numOutOfStock += 1;
            }
        }
        return numOutOfStock;
    }

    public int numberOfLegoSetsByTheme(String themeName) {
        if (legoSets.isEmpty()) {
            return 0;
        }
        else {
            int numOfSetsThatMatchTheme = 0;
            for(LegoSet legoSet : legoSets) {
                if (legoSet.getTheme().equalsIgnoreCase(themeName)) {
                    numOfSetsThatMatchTheme += 1;
                }
            }
            if (numOfSetsThatMatchTheme == 0){
                return 0;
            }
            else{
                return numOfSetsThatMatchTheme;
            }
        }
    }

    public int numberOfLegoSetsForAgeRatingAndAbove(int minAge) {
        int numOfSetsForRatingPlus = 0;
        for (LegoSet legoSet : legoSets) {
            if (legoSet.getMinimumAge() >= minAge) {
                numOfSetsForRatingPlus++;
            }
        }
        return numOfSetsForRatingPlus;
    }

    public int totalNumberOfInstructionBooklets() {
        int total = 0;
        for(LegoSet legoset : legoSets) {
            total += legoset.numberOfInstructionBooklets();
        }
        return total;
    }

    public String listAllLegoSets() {
        String listOfALlLegoSets = "";
        if (!legoSets.isEmpty()) {
            for (int i = 0; i < legoSets.size(); i++) {
                listOfALlLegoSets += "[" + i + "]: " + legoSets.get(i) + "\n";
            }
            return listOfALlLegoSets;
        }
        else {
            return "Sorry, No Lego Sets in the list";
        }
    }

    public String listLegoSetsInStock() {
        String listOfSetsInStock = "";
        for(int i = 0; i < legoSets.size(); i++) {
            if (legoSets.get(i).isInStock()) {
                listOfSetsInStock += "[" + i + "]: " + legoSets.get(i) + "\n";
            }
        }
        if (listOfSetsInStock.equals("")) {
            return "No Lego Sets in stock";
        }
        else {
            return listOfSetsInStock;
        }
    }

    public String listLegoSetsOutOfStock() {
        String listOfSetsOutOfStock = "";
        for(int i = 0; i < legoSets.size(); i++) {
            if (!legoSets.get(i).isInStock()) {
                listOfSetsOutOfStock += "[" + i + "]: " + legoSets.get(i) + "\n";
            }
        }
        if (!listOfSetsOutOfStock.equals("")) { // If there are LegoSets in the out of stock string, then return it.
            return listOfSetsOutOfStock;
        }
        else {
            return "No lego sets out of stock"; // If there is an empty list of out
            // of stock items, then return no sets out of stock
        }
    }

    public String listLegoSetsBySpecificTheme(String theme) {
        if (legoSets.isEmpty()) {
            return "no lego sets stored";
        }
        else {
            String listOfSetsMatchingTheme = "";
            for(int i = 0; i < legoSets.size(); i++) {
                if(legoSets.get(i).getTheme().toLowerCase().contains(theme.toLowerCase())) {
                    listOfSetsMatchingTheme += "[" + i + "]: " + legoSets.get(i).toString() + "\n";
                }
            }
            if (listOfSetsMatchingTheme.equals("")) {
                return "no lego sets with theme";
            }
            else {
                return listOfSetsMatchingTheme;
            }
        }
    }

    public String listLegoSetsForAgeRatingAndAbove(int minAge) {
        if (legoSets.isEmpty()) {
            return "No Lego sets";
        }
        else {
            String listOfSetsEqualOrAbove = "";
            for(int i = 0; i < legoSets.size(); i++) {
                if (legoSets.get(i).getMinimumAge() >= minAge) {
                    listOfSetsEqualOrAbove += "[" + i + "]: " + legoSets.get(i).toString() + "\n";
                }

            }
            if (listOfSetsEqualOrAbove.equals("")) {
                return "no lego sets available for age";
            }
            else {
                return listOfSetsEqualOrAbove;
            }
        }
    }

    public String listAllInstructionBooklets() {
        if (legoSets.isEmpty()) {
            return "no lego sets stored";
        }
        else  {
            String listOfBooklets = "";
            for(LegoSet legoSet : legoSets) {
                listOfBooklets += "LegoSet Name: " + legoSet.getName() + ", " + legoSet.getCode() + "\n" +
                legoSet.listInstructionBooklets() + "\n";
            }

            if (listOfBooklets.equals("")) {
                return "no instruction booklets";
            }
            else {
                return listOfBooklets;
            }
        }

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

    public String listStockStatusBySpecificTheme(String theme) {

        if (legoSets.isEmpty()) {
            return "no lego sets stored";
        }
        else {
            int totalInStock = 0;
            int totalOutStock = 0;
            String listInStock = "";
            String listOutStock = "";
            for(LegoSet legoSet : legoSets) {
                if (legoSet.getTheme().equalsIgnoreCase(theme)) {
                // error here
                    if (legoSet.isInStock()){
                        listInStock += legoSet.getName() +"\n";
                        totalInStock++;
                    }
                    else {
                            listOutStock += legoSet.getName() +"\n";
                            totalOutStock++;
                    }
                }
            }
            if ((totalInStock != 0) || (totalOutStock != 0)) {
                String InStockList = "Number of lego sets in stock: " + totalInStock + "\n" + listInStock;
                String OutStockList = "Number of lego sets out of stock: " + totalOutStock + "\n" + listOutStock;
                String fullList = InStockList + OutStockList;
                return fullList;
            }
            else {
                return "no lego sets with theme";
            }
        }
    }

    public LegoSet findLegoSet(int indexOfSet) {
        if (isValidIndex(indexOfSet)) {
            return legoSets.get(indexOfSet);
        }
        return null;
    }
    public LegoSet findLegoSetByCode(int code) {
        LegoSet foundLegoSet = null;
        for (int i = 0; i < legoSets.size(); i++) {
            if (legoSets.get(i).getCode() == code) {
                foundLegoSet = legoSets.get(i);
            }
        }
        if (foundLegoSet == null) {
            return null;
        }
        else {
            return foundLegoSet;
        }
    }
    public String searchLegoSetsByName(String name) {
        if (legoSets.isEmpty()) {
            return "no lego sets stored";
        }
        else {
            String foundLegoSet = "";
            for(int i = 0; i < legoSets.size(); i++){
                if(legoSets.get(i).getName().toLowerCase().contains(name)){
                    foundLegoSet += "[" + i + "]: " + legoSets.get(i).toString();
                }

            }
            if (foundLegoSet.equals("")){
                return "no lego sets found";
            }
            else {
                return foundLegoSet;
            }

        }
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

    public String searchInstructionBookletsByFileName(String fileName) {
        if (legoSets.isEmpty()) {
            return "no lego sets stored";
        }
        else {
            String bookletThatContainsFileName = "";
            for(LegoSet legoSet : legoSets) {
                if (legoSet.listInstructionBooklets().toLowerCase().contains(fileName)) {
                    bookletThatContainsFileName += legoSet.getInstructionBooklets().contains(fileName);

                }
            }
            return bookletThatContainsFileName;
        }

    }

    public boolean isValidIndex(int index) {
        return (index >= 0) && (index < legoSets.size());
    }

    public void load() throws Exception {

        Class<?>[] classes = new Class[] {LegoSet.class };
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("legoSets.xml"));
        legoSets = (ArrayList<LegoSet>) is.readObject();
        is.close();
    }

    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("legoSets.xml"));
        out.writeObject(legoSets);
        out.close();
    }
}

