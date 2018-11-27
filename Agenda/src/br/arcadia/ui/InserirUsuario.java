package br.arcadia.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class InserirUsuario extends Application {
	private static Stage stage;

	@Override
	public void start(Stage stage) {
		try {
			Pane root = FXMLLoader.load(getClass().getResource("../ui/FXMLInserirUsuario.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Cadastrar");
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
		InserirUsuario.stage = stage;
	}

	public static void main(String[] args) {
		launch(args);
		// System.out.println(new CNXJDBC().connectar());
	}

}
