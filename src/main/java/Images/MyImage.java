package Images;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyImage {
    private String path;
    private BufferedImage img;
    private int w;
    private int h;

    private MyImage(BufferedImage img) {
        this.img = img;
    }

    public MyImage(String path) throws IOException {
        this.path = path;
        img = ImageIO.read(new File(path));
        w = img.getWidth();
        h = img.getHeight();
    }

    public int[][][] getRawPixels() throws IOException {
        int[][][] pixels = new int[w][h][3];

        for (int i=0; i<w; i++) {
            for (int j=0; j<h; j++) {
                int pixel = img.getRGB(i, j);
                pixels[i][j][0] = (pixel >> 16) & 0xff;
                pixels[i][j][1] = (pixel >> 8) & 0xff;
                pixels[i][j][2] = (pixel) & 0xff;
            }
        }
        return pixels;
    }

    public void save(String path) throws IOException {
        ImageIO.write(img, "jpg", new File(path));
    }

    public MyImage toGray() {
        BufferedImage grayImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        for (int i=0; i<w; i++) {
            for (int j=0; j<h; j++) {
                int pixel = img.getRGB(i, j);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                int avg = (int) (0.2126*red + 0.7152*green + 0.0722*blue);
                int p = (avg<<24) | (avg<<16) | (avg<<8) | avg;
                grayImg.setRGB(i, j, p);
            }
        }
        return new MyImage(grayImg);
    }

    public void rewrite(BufferedImage img) throws IOException {
        this.img = img;
        ImageIO.write(img, "jpg", new File(path));
    }
}
