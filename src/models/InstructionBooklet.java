package models;

public class InstructionBooklet {

    //TODO The number of pages (int) is between 1 and 80 (both inclusive).  Default is 1.
    private int numberOfPages;
    private String fileName;

    public InstructionBooklet(int numberOfPages, String filename) {
        this.numberOfPages = numberOfPages;
        this.fileName = filename;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String filename) {
        this.fileName = filename;
    }

    //TODO The file name (String) of the booklet in the system is entered by the user.
    //     Default value is "".
    //     When creating the booklet, truncate the name to 20 characters.
    //     When updating an existing booklet, only update the name if it is 20 characters or less.


    //TODO Add the constructor, InstructionBooklet(int, String), that adheres to the above validation rules


    //TODO Add a getter and setter for each field, that adheres to the above validation rules


    //TODO Add a generated equals method.


    //TODO The toString should return the string in this format:
    //      legobooklet1.pdf (5 pages)  OR
    //      legobooklet2.pdf (1 page)   OR
    //      legobooklet3.pdf (0 pages)
    //  NOTE: .pdf is added to the actual file name if the user hasn't added it themselves.
    //  NOTE: "pages" is added to the number of pages when it is not equal 1, "page" otherwise.

}