package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_FinishLine extends SuperObject{

public OBJ_FinishLine () {
		
		name = "finish_line";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/finish_line.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}
}

