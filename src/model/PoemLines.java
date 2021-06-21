package model;

public class PoemLines {

    private String line;

    public PoemLines(String line) {
        this.line = line;
    }

    public String getLine() {
        return line;
    }

    @Override
    public String toString() {
        return "PoemLines{" +
                "line='" + line + '\'' +
                '}';
    }
}
