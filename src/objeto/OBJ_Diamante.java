package objeto;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Diamante extends SuperObjeto{
	GamePanel gp;
	public OBJ_Diamante(GamePanel gp){
		this.gp = gp;
		nome = "Diamante";
		try {
			imagem = ImageIO.read(getClass().getResourceAsStream("/objetos/diamante.png"));
			uFerramenta.scaleImage(imagem , gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
