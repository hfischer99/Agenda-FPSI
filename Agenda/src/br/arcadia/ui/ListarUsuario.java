package br.arcadia.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ListarUsuario extends Application {

	private static Stage stage;

	@Override
	public void start(Stage stage) {
		try {
			Pane root = FXMLLoader.load(getClass().getResource("../ui/FXMLListar.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Listar");
			stage.show();
			setStage(stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		ListarUsuario.stage = stage;
	}

	public static void main(String[] args) {
		launch(args);
		// System.out.println(new CNXJDBC().connectar());
	}

}