package br.arcadia.entidade;

public class Usuario {
	protected int Codigo;
	protected String Nome;
	protected String Email;
	protected int Fone;

	public Usuario(int Codigo, String Nome, String Email, int Fone) {
		super();
		this.Codigo = Codigo;
		this.Nome = Nome;
		this.Email = Email;
		this.Fone = Fone;
	}

	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public int getCodigo() {
		return Codigo;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public int getFone() {
		return Fone;
	}

	public void setFone(int fone) {
		Fone = fone;
	}

	public void setCodigo(int codigo) {
		Codigo = codigo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Codigo;
		result = prime * result + ((Email == null) ? 0 : Email.hashCode());
		result = prime * result + Fone;
		result = prime * result + ((Nome == null) ? 0 : Nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (Codigo != other.Codigo)
			return false;
		if (Email == null) {
			if (other.Email != null)
				return false;
		} else if (!Email.equals(other.Email))
			return false;
		if (Fone != other.Fone)
			return false;
		if (Nome == null) {
			if (other.Nome != null)
				return false;
		} else if (!Nome.equals(other.Nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [Codigo=" + Codigo + ", Nome=" + Nome + ", Email=" + Email + ", Fone=" + Fone + "]";
	}

}
