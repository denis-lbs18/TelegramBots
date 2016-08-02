package br.com.denisluna.utils;

public class Usuario {
	protected int id;
	protected String nome;
	protected String sobrenome;
	protected String username;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the sobrenome
	 */
	public String getSobrenome() {
		return sobrenome;
	}
	/**
	 * @param sobrenome the sobrenome to set
	 */
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsuarioNomeCompleto(){
		return this.nome + " " + this.sobrenome;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return "Usuário [id=" +this.getId()+ " firstName=" + this.getNome() + ", lastName=" + this.getSobrenome()
				+ ", username=" + this.getUsername() + "]";
	}	
}
