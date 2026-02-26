package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public final class ResourceLoader {
    private ResourceLoader(){
    }

    public static BufferedImage loadImage(Class<?> context, String path) throws IOException{
        try (InputStream is = context.getResourceAsStream(path)){
            if (is == null){
                throw new IllegalStateException("Recurso não encontrado no classpath: " + path);
            }
            BufferedImage img = ImageIO.read(is);
            if (img == null){
                throw new IOException("Não foi possível ler a imagem: " + path);
            }
            return img;
        }
    }
}
