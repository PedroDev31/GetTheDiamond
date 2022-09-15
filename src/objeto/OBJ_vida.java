package objeto;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_vida extends SuperObjeto {

		GamePanel gp;
		public OBJ_vida(GamePanel gp){
			this.gp = gp;
			nome = "vida";
			try {
				imagem  = ImageIO.read(getClass().getResourceAsStream("/objetos/heart_blank.png"));		
				imagem1 = ImageIO.read(getClass().getResourceAsStream("/objetos/heart_full.png"));
				imagem2 = ImageIO.read(getClass().getResourceAsStream("/objetos/heart_half.png"));
				imagem  = uFerramenta.scaleImage(imagem , gp.tileSize, gp.tileSize);
				imagem1 = uFerramenta.scaleImage(imagem1 , gp.tileSize, gp.tileSize);
				imagem2 = uFerramenta.scaleImage(imagem2 , gp.tileSize, gp.tileSize);
			}catch(IOException e) {
				e.printStackTrace();
			}
		}

}
