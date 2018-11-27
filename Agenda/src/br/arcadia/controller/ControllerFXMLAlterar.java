package br.arcadia.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import br.arcadia.dao.UsuarioDao;
import br.arcadia.entidade.Usuario;
import br.arcadia.ui.AlteraUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;

public class ControllerFXMLAlterar implements Initializable {

	@FXML
	private Text id;

	@FXML
	private TextField tfNome;

	@FXML
	private Button btAlterar;

	@FXML
	private Button btCancelar;

	@FXML
	private TextField tfEmail;

	@FXML
	private TextField tfFone;

	@FXML
	private TextField tfId;

	private static Usuario user;

	public void initialize(URL url, ResourceBundle rb) {
		initPerson();
	}

	public void initPerson() {
		tfId.setText(Integer.toString(user.getCodigo()));
		tfNome.setText(user.getNome());
		tfEmail.setText(user.getEmail());
		tfFone.setText(Integer.toString(user.getFone()));

	}

	public static Usuario getC() {
		return user;
	}

	public static void setC(Usuario user1) {
		ControllerFXMLAlterar.user = user1;
	}

	@FXML
	void altera(ActionEvent event) {
		if (!tfNome.getText().equals("") && !tfEmail.getText().equals("") && !tfFone.getText().equals("")
				&& !tfId.getText().equals("")) {
			try {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("SAIR");
				String s = "Você tem certeza que deseja Alterar este contato?";
				alert.setContentText(s);

				Optional<ButtonType> result = alert.showAndWait();

				if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
					UsuarioDao userDao = new UsuarioDao();
					int Codigo = Integer.parseInt(tfId.getText());
					int Fone = Integer.parseInt(tfFone.getText());
					String Nome = tfNome.getText(), Email = tfEmail.getText();
					Usuario umUsuario = new Usuario(Codigo, Nome, Email, Fone);
					userDao.alterarUsuario(umUsuario);

					Alert a = new Alert(AlertType.INFORMATION);
					a.setHeaderText("Contato alterado com sucesso!");
					a.show();

					fechar();
				}
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

	@FXML
	void cancela(ActionEvent event) {
		fechar();

	}

	public void fechar() {
		AlteraUsuario.getStage().close();
	}

}
