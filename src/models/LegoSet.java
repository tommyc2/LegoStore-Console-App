package models;

import utils.MinimumAgeUtility;
import utils.ThemeUtility;
import utils.Utilities;

import java.util.ArrayList;

import static java.lang.Double.MAX_VALUE;


public class LegoSet {

    private String name = "";
    private int code = 10000;
    private double cost = MAX_VALUE;
    private int pieceCount = 1;
    private boolean inStock = true;
    private String theme = "Classic";
    private int minimumAge = 4;
    private ArrayList<InstructionBooklet> instructionBooklets;

    public LegoSet(String name, int code, double cost, int pieceCount, String theme, int minimumAge) {

        if (Utilities.validStringlength(name, 35)) {
            this.name = name;
        } else {
            this.name = Utilities.truncateString(name, 35);
        }
        setCode(code);
        setCost(cost);
        setPieceCount(pieceCount);
        setTheme(theme);
        setMinimumAge(minimumAge);

        this.instructionBooklets = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (Utilities.validStringlength(name, 35)) {
            this.name = name;
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        if (Utilities.validRange(code, 10000, 99999)) {
            this.code = code;
        }
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        if (cost > 0) {
            this.cost = cost;
        }
    }

    public int getPieceCount() {
        return pieceCount;
    }

    public void setPieceCount(int pieceCount) {
        if (Utilities.validRange(pieceCount, 1, 2000)) {
            this.pieceCount = pieceCount;
        }
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        if (ThemeUtility.isValidTheme(theme)) {
            this.theme = theme;
        }
    }

    public int getMinimumAge() {
        return minimumAge;
    }

    public void setMinimumAge(int minimumAge) {
        if (MinimumAgeUtility.isValidAge(minimumAge)) {
            this.minimumAge = minimumAge;
        }
    }

    public ArrayList<InstructionBooklet> getInstructionBooklets() {
        return instructionBooklets;
    }

    public void setInstructionBooklets(ArrayList<InstructionBooklet> instructionBooklets) {
        this.instructionBooklets = instructionBooklets;
    }

    public int numberOfInstructionBooklets() {
        return instructionBooklets.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LegoSet legoSet = (LegoSet) o;
        return code == legoSet.code && Double.compare(legoSet.cost, cost) == 0 && pieceCount == legoSet.pieceCount && inStock == legoSet.inStock && minimumAge == legoSet.minimumAge && name.equals(legoSet.name) && theme.equals(legoSet.theme) && instructionBooklets.equals(legoSet.instructionBooklets);
    }

    public boolean addInstructionBooklet(InstructionBooklet instructionBooklet) {
        return instructionBooklets.add(instructionBooklet);
    }

    public InstructionBooklet findInstructionBooklet(int bookletNum) {
        if (isValidIndex(bookletNum)) {
            return instructionBooklets.get(bookletNum);
        }
        return null;
    }

    public boolean isValidIndex(int index) {
        return (index >= 0) && (index < instructionBooklets.size());
    }

    public InstructionBooklet deleteInstructionBooklet(int indexToDelete) {
        if (isValidIndex(indexToDelete)) {
            return instructionBooklets.remove(indexToDelete);
        }
        return null;
    }

    @Override
    public String toString() {
        String toString; // string declaration, then assigning a value to it.
        toString = "LegoSet:" +
                Colours.RED+" | Name = " +Colours.RESET+ name +
                Colours.RED+" | Code = " +Colours.RESET+ code +
                Colours.RED+" | Cost = " + Colours.RESET+"€" + cost +
                Colours.RED+" | " +Colours.RESET+ pieceCount + " pieces" +
                Colours.RED+" | Theme = " +Colours.RESET+ theme +
                Colours.RED+" | Minimum Age for use = " +Colours.RESET+ minimumAge + "+";

        if (this.inStock) {
            toString += Colours.RED+" | Availability = " +Colours.RESET+ inStock + "(in stock)" + "\n";
        } else {
            toString += Colours.RED+" | Availability = " +Colours.RESET+ inStock + "(not available)" + "\n";
        }

        toString += Colours.ORANGE+"--- Instruction Booklets ---" +Colours.RESET+ "\n";

        int total = instructionBooklets.size();
        if (total != 0) {
            for (int i = 0; i < total; i++) {
                toString += "[" + i + "] " + Colours.CYAN+ instructionBooklets.get(i) +Colours.RESET+"\n";
            }
        } else {
            toString += "No instruction booklets" + "\n";
        }
        return toString;
    }

    public String listInstructionBooklets() {
        if (!instructionBooklets.isEmpty()) {
            String listOfBooklets = "";
            for (int i = 0; i < instructionBooklets.size(); i++) {
                listOfBooklets += (i) + ": " + instructionBooklets.get(i) + "\n";
            }
            return listOfBooklets;
        } else {
            return "No Instruction Booklets available!";
        }
    }

    public boolean updateInstructionBooklet(int indexOfBooklet, String newFileName, int numOfPages) {
        InstructionBooklet locatedBooklet = findInstructionBooklet(indexOfBooklet);

        if (locatedBooklet != null) {
            locatedBooklet.setFileName(newFileName);
            locatedBooklet.setNumberOfPages(numOfPages);
            return true;
        }
        return false;
    }
}
