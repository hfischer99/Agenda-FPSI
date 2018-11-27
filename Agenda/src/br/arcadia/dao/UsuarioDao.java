package br.arcadia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.arcadia.entidade.Usuario;
import br.arcadia.jdbc.CNXJDBC;

public class UsuarioDao {
	private final String SQL_INSERE_USUARIO = "INSERT INTO \"PUBLIC\". \"USUARIOS\"(\"NOME\",\"EMAIL\",\"FONE\") VALUES (?,?,?);";
	private final String SQL_ALTERA_USUARIO = "UPDATE USUARIOS SET NOME=?, EMAIL=?, FONE=? WHERE ID=?";
	private final String SQL_EXCLUI_USUARIO = "DELETE FROM USUARIOS WHERE ID=?";
	private final String SQL_SELECIONA_USUARIO = "SELECT * FROM USUARIOS";
	private final String SQL_PESQUISA_USUARIO = "SELECT * FROM USUARIOS WHERE NOME LIKE ?";

	// SELECT * FROM USUARIOS where Nome like '%a%';

	private PreparedStatement pst = null;

	public boolean inserirUsuario(Usuario umusuario) {

		boolean ret = false;
		Connection conn = CNXJDBC.connectar();
		try {
			pst = conn.prepareStatement(SQL_INSERE_USUARIO);
			pst.setString(1, umusuario.getNome());
			pst.setString(2, umusuario.getEmail());
			pst.setInt(3, umusuario.getFone());
			ret = pst.execute();
			pst.close();
			CNXJDBC.fecharCNX();
		} catch (SQLException e) {
			System.out.println("Erro ao executar statement" + e.toString());
		}
		return ret;
	}

	public ArrayList<Usuario> listarTodosUsuarios() {
		ArrayList<Usuario> listaDeUsuarios = new ArrayList<Usuario>();
		Connection conn = CNXJDBC.connectar();
		Usuario umUsuario;
		try {
			pst = conn.prepareStatement(SQL_SELECIONA_USUARIO);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				umUsuario = new Usuario();
				umUsuario.setCodigo(rs.getInt("ID"));
				umUsuario.setNome(rs.getString("NOME"));
				umUsuario.setEmail(rs.getString("EMAIL"));
				umUsuario.setFone(rs.getInt("FONE"));
				listaDeUsuarios.add(umUsuario);
			}
			rs.close();
			pst.close();
			CNXJDBC.fecharCNX();
		} catch (SQLException e) {
			System.out.println("Erro ao executar Statement" + e.toString());
		}
		return listaDeUsuarios;
	}

	public boolean alterarUsuario(Usuario umUsuario) {
		boolean ret = false;
		Connection conn = CNXJDBC.connectar();
		try {
			pst = conn.prepareStatement(SQL_ALTERA_USUARIO);
			pst.setString(1, umUsuario.getNome());
			pst.setString(2, umUsuario.getEmail());
			pst.setInt(3, umUsuario.getFone());
			pst.setInt(4, umUsuario.getCodigo());
			ret = pst.execute();
			pst.close();
			CNXJDBC.fecharCNX();

		} catch (SQLException e) {
			System.out.println("Erro ao executar Statement" + e.toString());
		}
		return ret;
	}

	public boolean excluiUsuarios(Usuario umUsuario) {
		boolean ret = false;
		Connection conn = CNXJDBC.connectar();
		try {
			pst = conn.prepareStatement(SQL_EXCLUI_USUARIO);
			pst.setInt(1, umUsuario.getCodigo());
			ret = pst.execute();
			pst.close();
			CNXJDBC.fecharCNX();

		} catch (SQLException e) {
			System.out.println("Erro ao executar statement" + e.toString());
		}
		return ret;
	}

	public ArrayList<Usuario> pesquisarUsuarios(String desc) {
		ArrayList<Usuario> pesquisaDeUsuarios = new ArrayList<Usuario>();
		Connection conn = CNXJDBC.connectar();
		Usuario umUsuario;
		String parametro = "%" + desc + "%";

		try {
			pst = conn.prepareStatement(SQL_PESQUISA_USUARIO);
			pst.setString(1, parametro);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				umUsuario = new Usuario();
				umUsuario.setCodigo(rs.getInt("ID"));
				umUsuario.setNome(rs.getString("NOME"));
				umUsuario.setEmail(rs.getString("EMAIL"));
				umUsuario.setFone(rs.getInt("FONE"));
				pesquisaDeUsuarios.add(umUsuario);
			}
			rs.close();
			pst.close();
			CNXJDBC.fecharCNX();

		} catch (SQLException e) {
			System.out.println("Erro ao executar statement" + e.toString());
		}
		return pesquisaDeUsuarios;
	}

}
