package model;

import java.util.List;
import java.util.Map;

public class Sonnet {

    private List<Author> author;
    private String title;
    private List<PoemLines> poemLines;

    public List<Author> getAuthor() {
        return author;
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public List<PoemLines> getPoemLines() {
        return poemLines;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setPoemLines(List<PoemLines> poemLines) {
        this.poemLines = poemLines;
    }
}
