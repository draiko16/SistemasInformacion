package application;

import application.view.ResizeHelper;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	private double xOffset = 0;
	private double yOffset = 0;

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader fxloader = new FXMLLoader(Main.class.getResource("view/main.fxml"));
			AnchorPane root = fxloader.load();

			primaryStage.initStyle(StageStyle.UNDECORATED);
			//Make the user able to move the scene while clicking on it
			root.setOnMousePressed(new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent event) {
	                xOffset = event.getSceneX();
	                yOffset = event.getSceneY();
	            }
	        });
	        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent event) {
	                primaryStage.setX(event.getScreenX() - xOffset);
	                primaryStage.setY(event.getScreenY() - yOffset);
	            }
	        });

			Scene scene = new Scene(root);

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setMinHeight(root.getHeight());
			primaryStage.setMinWidth(root.getWidth());
			primaryStage.show();
			//ResizeHelper.addResizeListener(primaryStage);

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
