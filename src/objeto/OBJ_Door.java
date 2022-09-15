package objeto;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Door extends SuperObjeto{
	GamePanel gp;
	public OBJ_Door(GamePanel gp){
		this.gp = gp;
		nome = "Door";
		try {
			imagem = ImageIO.read(getClass().getResourceAsStream("/objetos/door.png"));
			uFerramenta.scaleImage(imagem , gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}
}
