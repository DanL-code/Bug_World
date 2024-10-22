package MyBugWorld;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Plant extends ImageView {

	public Plant() {
		super();
		// TODO Auto-generated constructor stub
		randomPlants();
	}

	public Plant(Image arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
		randomPlants();
	}

	public Plant(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
		randomPlants();
	}

	private void randomPlants() {

		Random random = new Random();
		int randomX = random.nextInt(690);
		int randomY = random.nextInt(400);

		this.setImage(new Image(getClass().getResourceAsStream("Grass.png")));
		this.setFitWidth(35);
		this.setFitHeight(35);
		this.setX(randomX);
		this.setY(randomY);

	}

}
