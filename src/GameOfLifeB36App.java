import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author Sayf Elhawary
 */
public class GameOfLifeB36App extends Application {

	// grid dimensions
	private static final int GRIDROWS = 25, GRIDCOLS = 25;

	private Controller controller_;

	// GUI elements
	private Timer timer_; // animation timer
	private Button startbtn_, stopbtn_; // control buttons
	private Canvas canvas_; // drawing canvas

	public void start ( Stage stage ) throws Exception {
		stage.setTitle("Game of Life: B36");

		controller_ = new Controller(new LifeCellB36(GRIDROWS,GRIDCOLS));
		controller_.initSim();

		BorderPane root = new BorderPane();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		// keep the window from being resized by the user
		stage.setResizable(false);

		MenuBar menubar = new MenuBar();
		Menu file = new Menu("File");
		MenuItem quititem = new MenuItem("quit");
		quititem.setOnAction(e -> Platform.exit());
		file.getItems().add(quititem);
		menubar.getMenus().addAll(file);
		root.setTop(menubar);

		canvas_ = new Canvas(500,500);
		root.setCenter(canvas_);
		BorderPane.setMargin(canvas_,new Insets(5,5,5,5));

		VBox btnpane = new VBox();
		btnpane.setSpacing(5);
		startbtn_ = new Button("start");
		stopbtn_ = new Button("stop");
		stopbtn_.setDisable(true);
		Button resetbtn = new Button("reset");
		btnpane.getChildren().addAll(startbtn_,stopbtn_,new Separator(),resetbtn);
		root.setLeft(btnpane);
		BorderPane.setMargin(btnpane,new Insets(5,5,5,5));

		startbtn_.setOnAction(e -> {
			stopbtn_.setDisable(false);
			startbtn_.setDisable(true);
			startTimer();
		});
		stopbtn_.setOnAction(e -> {
			startbtn_.setDisable(false);
			stopbtn_.setDisable(true);
			stopTimer();
		});
		resetbtn.setOnAction(e -> {
			boolean running = timer_ != null;
			stopTimer();
			controller_.initSim();
			draw(canvas_);
			if ( running ) {
				startTimer();
			}
		});

		draw(canvas_);

		// terminate when window is closed - needed because timer may still be
		// running
		stage.setOnCloseRequest(e -> {
			Platform.exit();
			System.exit(0);
		});

		stage.show();
	}

	/**
	 * Start the animation timer.
	 */
	private void startTimer () {
		if ( timer_ != null ) {
			timer_.cancel();
		}

		TimerTask task = new TimerTask() {
			public void run () {
				doNextGeneration();
			}
		};
		timer_ = new Timer();
		timer_.scheduleAtFixedRate(task,1000,100);
	}

	/**
	 * Stop the animation timer.
	 */
	private void stopTimer () {
		if ( timer_ != null ) {
			timer_.cancel();
			timer_ = null;
		}
	}

	/**
	 * Do one step of the simulation.
	 */
	private void doNextGeneration () {
		controller_.doSimStep();
		safedraw(canvas_);

		if ( controller_.isSimOver() ) {
			stopTimer();
			startbtn_.setDisable(false);
			stopbtn_.setDisable(true);
		}
	}

	/**
	 * Draw the current state using the JavaFX Application Thread.
	 * 
	 * @param canvas
	 *          canvas to draw on
	 */
	private void safedraw ( Canvas canvas ) {
		Platform.runLater( () -> draw(canvas));
	}

	/**
	 * Draw the current state.
	 * 
	 * @param canvas
	 *          canvas to draw on
	 */
	private void draw ( Canvas canvas ) {
		double w = canvas.getWidth() / GRIDCOLS, h = canvas.getHeight() / GRIDROWS;
		GraphicsContext g = canvas.getGraphicsContext2D();

		g.setStroke(Color.GRAY);
		for ( int row = 0 ; row < GRIDROWS ; row++ ) {
			for ( int col = 0 ; col < GRIDCOLS ; col++ ) {
				// (x,y) is the upper left corner of the current square
				double x = col * w, y = row * h;
				g.setFill(Color.valueOf(controller_.getStringColor(row,col)));
				g.fillRect(x,y,w,h);
				g.strokeRect(x,y,w,h);
			}
		}
	}

	public static void main ( String[] args ) {
		launch(args);
	}
}
