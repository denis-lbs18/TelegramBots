package br.com.denisluna.telegrambots.types;

public class Usuario {
	private long id;
	private boolean isBot;
	private String nome;
	private String sobrenome;
	private String username;

	public Usuario(long id, boolean isBot, String nome, String sobrenome, String username) {
		this.id = id;
		this.isBot = isBot;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.username = username;
	}

	public Usuario(long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isBot() {
		return isBot;
	}

	public void setBot(boolean isBot) {
		this.isBot = isBot;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public String getUsuarioNomeCompleto() {
		return this.getNome() + " " + this.getSobrenome();
	}

	@Override
	public String toString() {
		String retorno = String.format("\nChat_Id: %1$s \nUsername: %2$s \nNome: %3$s", this.getId(),
				this.getUsername(), this.getUsuarioNomeCompleto());
		return retorno;
	}
}
