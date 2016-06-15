package client.grafica.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUIConnessione extends Application{
		Stage theWindow;
		Scene theScene;
		
		public void start(Stage primaryStage) {

			theWindow = primaryStage;
			
			try {
				
				
				
				
	//			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gui/Connessione.fxml"));
	//			Parent root = FXMLLoader.load(getClass().getResource("Connessione.fxml"));
				Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("client/grafica/gui/fxml/login.fxml"));
				theScene = new Scene(root);
				
				//scene.getStylesheets().add(getClass().getResource("font.css").toExternalForm());
				//scene.getStylesheets().add("http://fonts.googleapis.com/css?family=IM+Fell+English+SC");
				
				/*SimpleDoubleProperty stageWidthProperty = new SimpleDoubleProperty(primaryStage.getWidth());
				stageWidthProperty.addListener(new ChangeListener<Number>() {
				  @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldStageWidth, Number newStageWidth) {
				    primaryStage.setWidth(newStageWidth.doubleValue());
				    System.out.println("Stage width = " + newStageWidth);
				  }
				});
				
				SimpleDoubleProperty stageHeightProperty = new SimpleDoubleProperty(primaryStage.getWidth());
				stageHeightProperty.bind(stageWidthProperty.divide(2));
				
				
				
				scene.widthProperty().addListener(new ChangeListener<Number>() {
				      @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
				        System.out.println("Scene width = " + newSceneWidth);
				      }
				    });*/
				
				theWindow.setScene(theScene);
				theWindow.show();
				
				/*ResizeListener listener = new ResizeListener();
				  theScene.setOnMouseMoved(listener);
				  theScene.setOnMousePressed(listener);
				  theScene.setOnMouseDragged(listener);
				*/
				

			} catch (Exception e) {

				e.printStackTrace();
			}

		}

		/**
		 * @return the theScene
		 */
		public Scene getTheScene() {
			return theScene;
		}

		/**
		 * @param theWindow the theWindow to set
		 */
		public void setTheWindow(Stage theWindow) {
			this.theWindow = theWindow;
		}

		/**
		 * @return the theWindow
		 */
		public Stage getTheWindow() {
			return theWindow;
		}

		
		
		public static void main(String[] args) {

			launch(args); 
		
}}
