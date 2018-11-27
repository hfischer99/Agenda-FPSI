package br.arcadia.ui;

import br.arcadia.controller.ControllerFXMLAlterar;
import br.arcadia.entidade.Usuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AlteraUsuario extends Application {

	public AlteraUsuario(Usuario user1) {
		// TODO Auto-generated constructor stub
		ControllerFXMLAlterar.setC(user1);
	}

	private static Stage stage;

	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = FXMLLoader.load(getClass().getResource("../ui/FXMLAlteraUsuario.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Altera");
			primaryStage.show();
			setStage(primaryStage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		AlteraUsuario.stage = stage;
	}

	public static void main(String[] args) {
		launch(args);
		// System.out.println(new CNXJDBC().connectar());
	}
}
