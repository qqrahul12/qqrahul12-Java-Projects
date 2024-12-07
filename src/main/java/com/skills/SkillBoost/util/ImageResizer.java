package com.game.SkillBoost.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageResizer {

    private static final Logger logger = LoggerFactory.getLogger(ImageResizer.class);

    public static void resizeImage(File inputFile, File outputFile, int width, int height) throws IOException {
        BufferedImage originalImage = ImageIO.read(inputFile);
        Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        BufferedImage bufferedResizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedResizedImage.createGraphics();
        g2d.drawImage(resizedImage, 0, 0, null);
        g2d.dispose();

        ImageIO.write(bufferedResizedImage, "png", outputFile);
    }

    public static void main(String[] args) {
        try {
            File inputFile = new File("path/to/original/image.png");
            File outputFile = new File("path/to/resized/image.png");
            int width = 200;
            int height = 200;
            resizeImage(inputFile, outputFile, width, height);
            System.out.println("Image resized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
