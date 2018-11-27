package br.arcadia.controller;

import java.util.Optional;
import br.arcadia.dao.UsuarioDao;
import br.arcadia.entidade.Usuario;
import br.arcadia.main.Principal;
import br.arcadia.ui.AlteraUsuario;
import br.arcadia.ui.InserirUsuario;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ControllerFXMLListar {
	@FXML
	private TableView<Usuario> tabela;

	@FXML
	private TableColumn<Usuario, Integer> clmId;

	@FXML
	private TableColumn<Usuario, String> clmNome;

	@FXML
	private TableColumn<Usuario, String> clmEmail;

	@FXML
	private TableColumn<Usuario, Integer> clmFone;

	@FXML
	private Button btDeletar;

	@FXML
	private Button btAtualizar;

	@FXML
	private Button btAlterar;

	@FXML
	private Button btInserir;

	@FXML
	private TextField tfDesc;

	@FXML
	private Button btPesquisar;

	@FXML
	private Button btSair;

	private Usuario selecionada;

	@FXML
	void Inserir(ActionEvent event) {
		InserirUsuario p = new InserirUsuario();

		try {
			p.start(new Stage());

		} catch (Exception ex) {
			ex.toString();
		}
		fechar();
	}

	@FXML
	void altera(ActionEvent event) {
		AlteraUsuario j = new AlteraUsuario(selecionada);
		try {
			j.start(new Stage());

		} catch (Exception ex) {
			ex.toString();
		}
	}

	@FXML
	void atualiza(ActionEvent event) {
		tabela.setItems(atualizaTabela());
	}

	@FXML
	void excluir(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("SAIR");
		String s = "Você tem certeza que deseja excluir o Contato?";
		alert.setContentText(s);

		Optional<ButtonType> result = alert.showAndWait();

		if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
			if (selecionada != null) {
				UsuarioDao usuarioDao = new UsuarioDao();
				usuarioDao.excluiUsuarios(selecionada);
				tabela.setItems(atualizaTabela());

				Alert b = new Alert(AlertType.INFORMATION);
				b.setHeaderText("Contato excluido com sucesso!");
				b.show();

			} else {
				Alert a = new Alert(AlertType.WARNING);
				a.setHeaderText("Selecione um usuario");
				a.show();
			}
		}
	}

	@FXML
	void pesquisar(ActionEvent event) {
		tabela.setItems(atualizaTabelaPesquisa());

	}

	@FXML
	void sair(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("SAIR");
		String s = "Você tem certeza que deseja sair do sistema?";
		alert.setContentText(s);

		Optional<ButtonType> result = alert.showAndWait();

		if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
			System.exit(0);
		}

	}

	public void initialize() {
		initTable();
	}

	public void initTable() {
		clmId.setCellValueFactory(new PropertyValueFactory("Codigo"));
		clmNome.setCellValueFactory(new PropertyValueFactory("Nome"));
		clmEmail.setCellValueFactory(new PropertyValueFactory("Email"));
		clmFone.setCellValueFactory(new PropertyValueFactory("Fone"));
		tabela.setItems(atualizaTabela());

		tabela.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Usuario>() {
			@Override
			public void changed(ObservableValue<? extends Usuario> observable, Usuario oldValue, Usuario newValue) {
				selecionada = (Usuario) newValue;
			}
		});
	}

	public ObservableList<Usuario> atualizaTabela() {
		UsuarioDao usuarioDao = new UsuarioDao();
		return FXCollections.observableArrayList(usuarioDao.listarTodosUsuarios());
	}

	public void fechar() {
		Principal.getStage().close();
	}

	public ObservableList<Usuario> atualizaTabelaPesquisa() {
		UsuarioDao usuarioDao = new UsuarioDao();
		return FXCollections.observableArrayList(usuarioDao.pesquisarUsuarios(tfDesc.getText()));
	}

}
