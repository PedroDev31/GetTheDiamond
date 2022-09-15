package objeto;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Stairs extends SuperObjeto{
	GamePanel gp;
	public OBJ_Stairs(GamePanel gp){
		this.gp = gp;
		nome = "Stair";
		try {
			imagem = ImageIO.read(getClass().getResourceAsStream("/objetos/stairs.png"));
			uFerramenta.scaleImage(imagem , gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
