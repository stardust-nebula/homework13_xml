import domParser.DomParser;
import domParser.ITags;
import model.Author;
import model.PoemLines;
import model.Sonnet;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Программа проверяет естьли xm файл в заданной директории. Если программа находит один xml файл в директории, тогда
 * этот файл обрабатывается.
 * Из файла берутся lastName, firstName, title, которые используются в названии ноыого txt файла.
 * Также из xmk файла берутся строки поэмы, которые заносятся в txt файл.
 */


public class Runner implements ITags {

    public static void main(String[] args) {

        String title = null;
        Node authorNode = null;
        Node poemLinesNode = null;
        String pathToDirectory;
        String pathToXMLFileToParse = null;
        String authorFirstName = null;
        String authorLastName = null;

        DomParser domParser = new DomParser();

        Sonnet sonnet = new Sonnet();
        Document doc;

        Scanner sc = new Scanner(System.in);
        System.out.println("Введите путь к директории, где находится xml файл для парсинга.");
        pathToDirectory = sc.next();
        List<String> listOfDirectories = FilesValidator.getListOfDirPath(pathToDirectory);

        if (!FilesValidator.isThereFileToParse(pathToDirectory)) {
            for (int i = 0; i < listOfDirectories.size(); i++) {
                if (!FilesValidator.isThereFileToParse(listOfDirectories.get(i))) {
                    continue;
                }
                pathToXMLFileToParse = FilesValidator.getXmlFilePath(listOfDirectories.get(i));
                break;
            }
            if (pathToXMLFileToParse == null) {
                return;
            }

        } else {
            pathToXMLFileToParse = FilesValidator.getXmlFilePath(pathToDirectory);
        }

        System.out.println("Найден файл. Путь: " + pathToXMLFileToParse);
        File file = new File(pathToXMLFileToParse);

        try {
            doc = domParser.buildDocument(file);
        } catch (Exception e) {
            System.out.println("Open parsing error " + e.toString());
            return;
        }

        Node rootNode = doc.getFirstChild();
        NodeList rootChildren = rootNode.getChildNodes();

        for (int i = 0; i < rootChildren.getLength(); i++) {

            if (rootChildren.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            switch (rootChildren.item(i).getNodeName()) {
                case TAG_AUTHOR: {
                    authorNode = rootChildren.item(i);
                    break;
                }
                case TAG_TITLE: {
                    title = rootChildren.item(i).getTextContent();
                    break;
                }
                case TAG_LINES: {
                    poemLinesNode = rootChildren.item(i);
                    break;
                }
            }
        }

        if (authorNode == null) {
            System.out.println("<author> tag is empty");
            return;
        }

        if ((title.isEmpty())) {
            System.out.println("Title is empty");
            return;
        }

        if (poemLinesNode == null) {
            System.out.println("<lines> tag is empty");
            return;
        }

        List<Author> authorInfo = domParser.parseAuthorChildrenTags(authorNode);
        List<PoemLines> poemLines = domParser.parsePoemLinesChildrenTags(poemLinesNode);

        sonnet.setAuthor(authorInfo);
        sonnet.setTitle(title);
        sonnet.setPoemLines(poemLines);

        for (int i = 0; i < sonnet.getAuthor().size(); i++) {
            authorFirstName = sonnet.getAuthor().get(i).getFirstname();
            authorLastName = sonnet.getAuthor().get(i).getLastName();
        }

        String buildFileName = authorFirstName + "_" + authorLastName + "_" + sonnet.getTitle();
        String newFilePath = buildFileName + ".txt";

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(newFilePath))) {

            List<String> lines = new ArrayList<>();
            sonnet.getPoemLines().stream().forEach(e -> lines.add(e.getLine()));

            for (int i = 0; i < lines.size(); i++) {
                bufferedWriter.write(lines.get(i) + '\n');
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
