package MyBugWorld;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MyBugWorldMain extends Application {
	int width = 880, height = 650;
	float x = 100, y = 100, dx = -1.5f, dy = -1.5f;
	private Timeline timeline;
	private Label topLabel = new Label();
	private Pane centerPane = new Pane();
	private Slider slider = new Slider();
	int id = 1;
	int maxID = 15;
	private boolean isEat = false;

	private ArrayList<MyBug> bugs = new ArrayList<MyBug>();
	private ArrayList<MyBug> removedBugs = new ArrayList<MyBug>();
	private ArrayList<Plant> plants = new ArrayList<Plant>();
	private ArrayList<ImageView> flowers = new ArrayList<ImageView>();

	@Override
	public void start(Stage primaryStage) throws Exception {

		Image flower1_img = new Image(getClass().getResourceAsStream("Flower1.gif"));
		Image flower2_img = new Image(getClass().getResourceAsStream("Flower2.gif"));		

		// crate bottom flowers
		for (int i = 0; i < 1000; i += 200) {
			ImageView flower1 = new ImageView(flower1_img);
			flower1.setX(i);
			flower1.setFitWidth(100);
			flower1.setFitHeight(100);
			flower1.setY(height - 200);

			ImageView flower2 = new ImageView(flower2_img);
			flower2.setX(i + 100);
			flower2.setY(height - 200);
			flower2.setFitWidth(100);
			flower2.setFitHeight(100);

			flowers.add(flower1);
			flowers.add(flower2);
		}


		final BorderPane root = new BorderPane();

		// top pane
		StackPane topPane = new StackPane();
		root.setTop(topPane);
		topPane.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
		topPane.getChildren().add(topLabel); // add a label to the top pane
		topLabel.setText("Welcome to My Wonderful Garden!");//label set text
		Image topImg = new Image(getClass().getResourceAsStream("FlowerGarden.png"));
		ImageView topPic = new ImageView(topImg);
		topLabel.setGraphic(topPic);
		topLabel.setTextFill(Color.WHITE);
		topLabel.setFont(new Font("Arial", 24));
		topPic.setFitWidth(50);
		topPic.setFitHeight(50);

		// bottom pane
		StackPane bottomPane = new StackPane();
		root.setBottom(bottomPane);
		bottomPane.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
		bottomPane.setMinHeight(50);
		bottomPane.setPadding(new Insets(5, 100, 5, 100));
		// create a slider on bottom pane
		slider.setMin(0);
		slider.setMax(10);
		slider.setValue(1);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(1);
		slider.setMinorTickCount(5);
		slider.setBlockIncrement(1);
		bottomPane.getChildren().add(slider);

		// central pane
		root.setCenter(centerPane);
		Random random = new Random();

		// create plants randomly
		int plantNum = random.nextInt(10) + 16;
//		System.out.println(plantNum);
		for (int i = 0; i < plantNum; i++) {
			Plant plant = new Plant();
			plants.add(plant);
		}
		centerPane.getChildren().addAll(flowers);
		centerPane.getChildren().addAll(plants);

		// create bugs randomly on central pane
		int bugNum = random.nextInt(5) + 3;

		for (int i = 0; i < bugNum; i++) {
			randomBugs();
		}
		

		// left pane
		Pane leftP = new Pane();
		root.setLeft(leftP);
		VBox vB = new VBox();
		vB.setPadding(new Insets(10, 10, 10, 10));

		// add buttons on left pane
		// start button
		Button btnS = new Button();
		btnS.setText("Start");
		btnS.setMinWidth(50);
		vB.getChildren().add(btnS);
		btnS.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (removedBugs.size() > 0) {
					bugs.addAll(removedBugs);
					removedBugs.clear();
				}

				ImageView topI = new ImageView(topImg);
				topLabel.setGraphic(topI);
				topI.setFitWidth(50);
				topI.setFitHeight(50);
				topLabel.setText("Welcome to My Wonderful Garden!️");
				timeline.play();
			}

		});

		vB.setSpacing(10);
		// pause button
		Button btnP = new Button();
		btnP.setText("Pause");
		btnP.setMinWidth(50);
		vB.getChildren().add(btnP);
		btnP.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				timeline.pause();
			}
		});

		vB.setSpacing(10);
		// add bugs button
		Button btnAdd = new Button();
		btnAdd.setText("+Bug");
		btnAdd.setMinWidth(50);
		vB.getChildren().add(btnAdd);
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				randomBugs();
			}
		});

		vB.setSpacing(10);
		// eat button
		Button btnEat = new Button();
		btnEat.setText("Eat");
		btnEat.setMinWidth(50);
		vB.getChildren().add(btnEat);
		btnEat.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				isEat = true;
			}

		});

		vB.setSpacing(10);
		// stop button
		Button btnStop = new Button();
		btnStop.setText("Stop");
		btnStop.setMinWidth(50);
		vB.getChildren().add(btnStop);
		btnStop.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				timeline.stop();
			}

		});

		leftP.getChildren().add(vB);

		final Scene scene = new Scene(root, width, height);

		KeyFrame frame = new KeyFrame(Duration.millis(16), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {

				for (MyBug b : bugs) {

					if (b.getX() < 0 || b.getX() + b.getFitWidth() > centerPane.getWidth()) {

						b.setDx(-b.getDx());

						b.setScaleX(-b.getScaleX());

					}

					if (b.getY() < 0 || b.getY() + b.getFitHeight() > centerPane.getHeight()) {

						b.setDy(-b.getDy());
					}

					b.setX(b.getX() + b.getDx() * slider.getValue());

					b.setY(b.getY() + b.getDy() * slider.getValue());


					if (isEat) {
						for (Plant p : plants) {
							if (isOverLap(b, p)) {
								centerPane.getChildren().remove(p);
							}
						}
					}
				}
			}
		});

		timeline = new Timeline();
		timeline.setCycleCount(javafx.animation.Animation.INDEFINITE);
		timeline.getKeyFrames().add(frame);

		primaryStage.setTitle("🦋❤️Bug World❤️🐝️");
		primaryStage.setScene(scene);
		primaryStage.show();

		// resize the window but keep flowers on bottom(source:
		// https://itecnote.com/tecnote/how-to-make-javafx-scene-scene-resize-while-maintaining-an-aspect-ratio/)
		primaryStage.heightProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue observable, Number oldValue, Number newValue) {

				for (ImageView iv : flowers) {
					iv.setY(newValue.intValue() - 228);
				}
			}
		});
	}

	public void randomBugs() {
		if (id > maxID) {
			topLabel.setText("Too Crowded.");
			return;
		}

		Random random = new Random();
		int bugType = random.nextInt(3);
		int x = random.nextInt(480);
		int y = random.nextInt(350);
		float dx = random.nextFloat() * 2 - 1;
		float dy = random.nextFloat() * 2 - 1;

		MyBug bg;

		if (bugType == 0) {
			bg = new Bee(id, x, y, dx, dy);
			if (dx > 0)
				bg.setScaleX(-bg.getScaleX());
			bg.setFitWidth(35);
			bg.setFitHeight(35);

		} else if (bugType == 1) {
			bg = new Butterfly(id, x, y, dx, dy);
			if (dx < 0)
				bg.setScaleX(-bg.getScaleX());
			bg.setFitWidth(50);
			bg.setFitHeight(50);
		} else {
			bg = new LadyBird(id, x, y, dx, 0);
			if (dx > 0)
				bg.setScaleX(-bg.getScaleX());
			bg.setFitWidth(40);
			bg.setFitHeight(40);
		}
		bugs.add(bg);//add random bugs to bugs array list

		id++;

		centerPane.getChildren().add(bg);
		// click on a bug and the label on the top pane will demonstrate the icon and
		// the text of the bug
		bg.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {

				System.out.println("Clicked on " + event.getSource());

				if (event.getSource() instanceof MyBug && bugs.contains((MyBug) event.getSource())) {
					MyBug mb = (MyBug) event.getSource();
					bugs.remove(mb);
					removedBugs.add(mb);

					topLabel.setText(mb.getText());
					ImageView niv = new ImageView(mb.getImage());
					niv.setFitWidth(50);
					niv.setFitHeight(50);
					topLabel.setGraphic(niv);
				}
			}
		});
	}

	// check if overlapping (source from:
	// https://www.baeldung.com/java-check-if-two-rectangles-overlap)
	public boolean isOverLap(ImageView imgview1, ImageView imgview2) {

		double x1 = imgview1.getX();
		double y1 = imgview1.getY();
		double width1 = imgview1.getFitWidth();
		double height1 = imgview1.getFitHeight();

		double x2 = imgview2.getX();
		double y2 = imgview2.getY();
		double width2 = imgview2.getFitWidth();
		double height2 = imgview2.getFitHeight();

		boolean isOverlap = (x1 < x2 + width2 - 20 && x1 + width1 + 20 > x2 && y1 < y2 + height2 - 20
				&& y1 + height1 + 20 > y2);

		if (isOverlap) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		launch();
	}

}
