package Image;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ImageLoader {
    private static final Map<String, Image> imageCache = new HashMap<>();

    public static Image loadImage(String path) {
        if(imageCache.containsKey(path)) {
            return imageCache.get(path);
        }

        try {
            Image image = ImageIO.read(ImageLoader.class.getResourceAsStream(path));
            imageCache.put(path, image);
            return image;
        } catch (IOException e) {
            System.err.println("无法加载图片: " + path);
            return null;
        }
    }
}
