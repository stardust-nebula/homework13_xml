package domParser;

import model.Author;
import model.PoemLines;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DomParser implements ITags {

    public static Document buildDocument(File file) throws Exception {
        DocumentBuilderFactory docBuildFactory = DocumentBuilderFactory.newInstance();
        return docBuildFactory.newDocumentBuilder().parse(file);
    }

    public static List<Author> parseAuthorChildrenTags(Node authorNode) {
        List<Author> authorList = new ArrayList<>();
        NodeList authorChildren = authorNode.getChildNodes();

        String lastName = "";
        String firstName = "";

        for (int i = 0; i < authorChildren.getLength() - 1; i++) {

            if (authorChildren.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            switch (authorChildren.item(i).getNodeName()) {
                case ITags.TAG_LAST_NAME: {
                    lastName = authorChildren.item(i).getTextContent();
                    break;
                }
                case ITags.TAG_FIRST_NAME: {
                    firstName = authorChildren.item(i).getTextContent();
                    break;
                }
            }
        }
        Author author = new Author(lastName, firstName);
        authorList.add(author);

        return authorList;
    }

    public static List<PoemLines> parsePoemLinesChildrenTags(Node poemLinesNode) {
        List<PoemLines> poemLinesList = new ArrayList<>();
        NodeList poemLinesChildren = poemLinesNode.getChildNodes();
        String line = "";

        for (int i = 0; i < poemLinesChildren.getLength(); i++) {

            if (poemLinesChildren.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            line = poemLinesChildren.item(i).getTextContent();

            PoemLines poemLine = new PoemLines(line);
            poemLinesList.add(poemLine);
        }
        return poemLinesList;
    }
}
