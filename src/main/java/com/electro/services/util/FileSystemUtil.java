package com.electro.services.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.electro.services.enums.FileType;
import jakarta.servlet.http.Part;

public class FileSystemUtil {

    private static final String PROFILEPIC_DIRECTORY = System.getProperty("user.home") + "/electroPics/profilePic";
    private static final String PRODUCTPIC_DIRECTORY = System.getProperty("user.home") + "/electroPics/productPic";

    /**
     * Store a file obtained from a Part object in the specified directory.
     *
     * @param filePart The Part object representing the file to be stored.
     * @param fileName The name of the file.
     * @param fileType The type of file.
     * @return The absolute path of the saved file.
     * @throws IOException If an I/O error occurs.
     */
    public static String storeFileFromPart(Part filePart, String fileName, FileType fileType) throws IOException {

        // Determine the base directory based on the file type
        String baseDirectory;
        switch (fileType) {
            case PROFILE_PIC:
                baseDirectory = PROFILEPIC_DIRECTORY;
                break;
            case PRODUCT_PIC:
                baseDirectory = PRODUCTPIC_DIRECTORY;
                break;
            default:
                throw new IllegalArgumentException("Unsupported file type");
        }

        // Create directory if it doesn't exist
        Path directoryPath = Paths.get(baseDirectory);
        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }

        // Derive file extension from Part object
        String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));

        // Create the file path
        Path filePath = directoryPath.resolve(fileName + fileExtension);

        // Write the file from Part to the file system
        try {
            filePart.write(filePath.toString());
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
            throw e;
        }

        // Return the absolute path of the saved file
        return filePath.toAbsolutePath().toString();
    }

    /**
     * Retrieve the byte array from the specified file path.
     *
     * @param filePath The absolute path of the file.
     * @return The byte array of the file.
     * @throws IOException If an I/O error occurs.
     */
    public static byte[] getBytesFromFile(String filePath) {

        Path path = Path.of(filePath);
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            System.out.println("Error getting bytes from file" + e.getMessage());
            return null;
        }
    }
}
