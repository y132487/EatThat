import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class MainImage {
	public static BufferedImage Load(String  filepath) {   		
		try { return ImageIO.read(new File(filepath)); } 
		catch (IOException e) { return  null; }
	}
}
