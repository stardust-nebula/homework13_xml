package model;

public class Author {

    private String lastName;
    private String firstname;

    public Author(String lastName, String firstname) {
        this.lastName = lastName;
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstname() {
        return firstname;
    }

}
