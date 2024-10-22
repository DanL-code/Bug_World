package MyBugWorld;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MyBug extends ImageView{
	
	float dx = -1.5f, dy = -1.5f;

	public MyBug(float x, float y, float dx, float dy) {
		super();

		setX(x);
		setY(y);
		this.dx = dx;
		this.dy = dy;

	}

	public float getDx() {
		return dx;
	}

	public void setDx(float dx) {
		this.dx = dx;
	}

	public float getDy() {
		return dy;
	}

	public void setDy(float dy) {
		this.dy = dy;
	}

	public String getText() {
		return "";
	}
	

}
