package objeto;

import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Salto extends SuperObjeto {
	GamePanel gp;
public OBJ_Salto(GamePanel gp){
		this.gp = gp;
		nome = "Salto";
		try {
			imagem = ImageIO.read(getClass().getResourceAsStream("/objetos/saltoDeCria.png"));
			uFerramenta.scaleImage(imagem , gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}
}
