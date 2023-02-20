package models;

import utils.Utilities;

import java.util.Objects;

public class InstructionBooklet {

    private int numberOfPages = 1;
    private String fileName = "";

    public InstructionBooklet(int numberOfPages, String filename) {
        setNumberOfPages(numberOfPages);

        if (Utilities.validStringlength(filename, 20)) {
            this.fileName = filename;
        } else {
            this.fileName = Utilities.truncateString(filename, 20);
        }

    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        if (Utilities.validRange(numberOfPages, 1, 80)) {
            this.numberOfPages = numberOfPages;
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String filename) {
        if (Utilities.validStringlength(filename, 20)) {
            this.fileName = filename;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstructionBooklet that = (InstructionBooklet) o;
        return numberOfPages == that.numberOfPages && fileName.equals(that.fileName);
    }


    @Override
    public String toString() {
        String str = "";

        str += "--- Instruction Booklet ---" + "\n";
        str += "Filename: ";

        if (this.fileName.endsWith(".pdf")) {
            str += this.fileName;
        }
        if (!this.fileName.endsWith(".pdf")) {
            str += this.fileName + ".pdf";
        }

        str += "\n" + "Number of Pages: ";

        if (this.numberOfPages != 1) {
            str += "(" + numberOfPages + " pages" + ")";
        } else {
            str += "(" + numberOfPages + " page" + ")";
        }

        return str;


    }
}