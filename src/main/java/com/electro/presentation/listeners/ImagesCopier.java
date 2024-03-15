package com.electro.presentation.listeners;


import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.io.IOException;
import java.nio.file.*;
import java.util.Properties;

@WebListener
public class ImagesCopier implements ServletContextListener {


    private Path ImagesPath;
    private Path serverImagesPath;
    public void contextInitialized(ServletContextEvent sce) {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream("/config.properties"));
            ImagesPath = Paths.get(properties.getProperty("images.path"));
            serverImagesPath = Paths.get(sce.getServletContext().getRealPath(properties.getProperty("server.images.path")));
            if (!Files.exists(ImagesPath)) {
                System.err.println("Images directory does not exist: " + ImagesPath);
                return;
            }
            copyImages(ImagesPath, serverImagesPath);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        copyImages(serverImagesPath, ImagesPath);
    }


    private void copyImages(Path sourcePath, Path destinationPath){
        try {
            Files.walk(sourcePath).filter(Files::isRegularFile).forEach(source -> {
                try {
                    Path destination = destinationPath.resolve(sourcePath.relativize(source));
                    Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    System.err.println("Error copying images: " + e.getMessage());
                }
            });
            System.out.println("Images are copied from " + sourcePath + " to " + destinationPath +" successfully");
        } catch (IOException e) {
            System.err.println("Error copying images: " + e.getMessage());
        }
    }
}
