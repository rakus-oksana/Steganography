package Images;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String filename = "C:/Users/oksana/Desktop/oop/lab04/src/main/resources/img/koz.jpg";
        String gray = "C:/Users/oksana/Desktop/oop/lab04/src/main/resources/img/gray_koz.jpg";
        MyImage img = new MyImage(filename);
        img.toGray().save(gray);
    }
}
