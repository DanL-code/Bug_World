package MyBugWorld;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LadyBird extends MyBug {
	
	private int id;

	public LadyBird(int id, float x, float y, float dx, float dy) {
		super(x, y, dx, dy);
		// TODO Auto-generated constructor stub
		this.setImage(new Image(getClass().getResourceAsStream("LadyBird.gif")));
		this.id = id;
	}
	
	@Override
	public String getText() {
		return "Lady Bird No. " + id + " says: Whoa! I got caught~";
	}


}
