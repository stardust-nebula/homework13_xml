import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilesValidator {

    public static boolean isContentExistInDirectory(String path) {
        boolean isFilesExistInDirectory = false;
        File dir = new File(path);
        List<File> listOfFiles = Arrays.stream(dir.listFiles()).toList();

        if (listOfFiles.isEmpty()) {
            isFilesExistInDirectory = false;
        } else {
            isFilesExistInDirectory = true;
        }
        return isFilesExistInDirectory;
    }

    public static boolean isDirectoryExistInSetDirectory(String path) {
        boolean isDirectoryExistInside = false;
        File dir = new File(path);
        List<File> listOfPath = Arrays.stream(dir.listFiles()).toList();

        if (isContentExistInDirectory(path)) {
            for (File pathOfContent : listOfPath) {
                if (pathOfContent.isDirectory()) {
                    isDirectoryExistInside = true;
                } else {
                    isDirectoryExistInside = false;
                }
            }
        }
        return isDirectoryExistInside;
    }

    public static List<String> getListOfDirPath(String path) {
        File dir = new File(path);
        List<File> listOfPath = Arrays.stream(dir.listFiles()).toList();
        List<String> listOfDirectories = new ArrayList<>();

        if (isContentExistInDirectory(path)) {
            for (File pathOfContent : listOfPath) {
                if (pathOfContent.isDirectory()) {
                    listOfDirectories.add(pathOfContent.getAbsolutePath());
                }
            }
        }
        return listOfDirectories;
    }

    public static boolean isFilesExistInSetDirectory(String path) {
        boolean isFilesExistInSetDirectory = false;
        File dir = new File(path);

        List<File> listOfPath = Arrays.stream(dir.listFiles()).toList();

        if (isContentExistInDirectory(path)) {
            for (File pathOfContent : listOfPath) {
                if (pathOfContent.isFile()) {
                    isFilesExistInSetDirectory = true;
                } else {
                    isFilesExistInSetDirectory = false;
                }
            }
        }
        return isFilesExistInSetDirectory;
    }

    public static String getExtension(String path) {
        String extension = "";
        File filePath = new File(path);

        int i = filePath.getName().lastIndexOf('.');
        if (i >= 0) {
            extension = filePath.getName().substring(i + 1);
        }
        return extension;
    }

    public static int countNumberOfXmlFiles(String path) {
        int countXmlFiles = 0;
        String xmlExtensionName = "xml";
        String tempExtensionName = "";

        File dir = new File(path);
        List<File> listOfPath = new ArrayList<>();
        for (File pathToFiles : dir.listFiles()) {
            listOfPath.add(pathToFiles);
        }

        if (isFilesExistInSetDirectory(path)) {
            for (File pathOfContent : listOfPath) {
                tempExtensionName = getExtension(pathOfContent.toString());
                if (xmlExtensionName.equals(tempExtensionName)) {
                    countXmlFiles = countXmlFiles + 1;
                }
            }
        }
        return countXmlFiles;
    }

    public static boolean isOnlyOneXmlFileExist(String path) {
        boolean isOnlyOneXmlFileExist = false;
        int countXmlFiles = countNumberOfXmlFiles(path);

        if (countXmlFiles == 1) {
            isOnlyOneXmlFileExist = true;
        } else {
            isOnlyOneXmlFileExist = false;
        }
        return isOnlyOneXmlFileExist;
    }

    public static boolean isThereFileToParse(String path) {
        boolean isThereWhatToParse = false;
        if (isOnlyOneXmlFileExist(path)) {
            isThereWhatToParse = true;
        } else {
            isThereWhatToParse = false;
        }

        return isThereWhatToParse;
    }

    public static String getXmlFilePath(String path) {
        String xmlExtensionName = "xml";
        String tempExtensionName = "";

        File dir = new File(path);
        List<File> listOfPath = Arrays.stream(dir.listFiles()).toList();

        for (File pathOfContent : listOfPath) {
            tempExtensionName = getExtension(pathOfContent.toString());
            if (xmlExtensionName.equals(tempExtensionName)) {
                return pathOfContent.getAbsolutePath();
            }
        }
        return null;
    }
}
