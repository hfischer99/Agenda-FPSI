package br.arcadia.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import br.arcadia.ui.InserirUsuario;
import br.arcadia.ui.ListarUsuario;
import br.arcadia.dao.UsuarioDao;
import br.arcadia.entidade.Usuario;

public class ControllerFXMLInserirUsuario {

	@FXML
	private Text id;

	@FXML
	private TextField tfNome;

	@FXML
	private Button btInserir;

	@FXML
	private TextField tfEmail;

	@FXML
	private TextField tfFone;

	@FXML
	private Button btVoltar;

	@FXML
	void Voltar(ActionEvent event) {
		ListarUsuario p = new ListarUsuario();
		try {
			p.start(new Stage());
		} catch (Exception ex) {
			ex.toString();
		}
		fechar();
	}

	@FXML
	void inserir(ActionEvent event) {
		if (!tfNome.getText().equals("") && !tfEmail.getText().equals("") && !tfFone.getText().equals("")) {
			try {
				Usuario umUser = new Usuario();
				umUser.setNome(tfNome.getText());
				umUser.setEmail(tfEmail.getText());
				umUser.setFone(Integer.parseInt(tfFone.getText()));
				new UsuarioDao().inserirUsuario(umUser);

				Alert a = new Alert(AlertType.INFORMATION);
				a.setHeaderText("Contato cadastrado com sucesso!");
				a.show();
			} catch (Exception ex) {
				Alert a = new Alert(AlertType.INFORMATION);
				a.setHeaderText("Insira os Valores Corretamente!");
				a.show();
			}
		} else {
			Alert a = new Alert(AlertType.WARNING);
			a.setHeaderText("Favor preencher todos os campos!");
			a.show();
		}
	}

	public void fechar() {
		InserirUsuario.getStage().close();
	}

}
