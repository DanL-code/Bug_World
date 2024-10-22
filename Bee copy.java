package MyBugWorld;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bee extends MyBug {
	
	private int id;

	public Bee(int id, float x, float y, float dx, float dy) {
		super(x, y, dx, dy);
		// TODO Auto-generated constructor stub
		this.setImage(new Image(getClass().getResourceAsStream("Bee.png")));
		this.id = id;
	}
	
	@Override
	public String getText() {
		return "Bee No. " + id + " says: Oops~ I'm frozen!";
	}
	
}
