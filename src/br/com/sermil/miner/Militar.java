package br.com.sermil.miner;

public class Militar {



	private String cpf;



	private String senha;



	private String cookie;



	public Militar(String cpf, String senha, String cookie) {
		this.cpf = cpf;
		this.senha = senha;
		this.cookie = cookie;
		
	}



	public String getCpf() {	

			return cpf;
		
	}



	public void setCpf(String cpf) {

			this.cpf = cpf;
		
	}



	public String getSenha() {		

			return senha;
		
	}



	public void setSenha(String senha) {

			this.senha = senha;
		
	}



	public String getCookie() {

			return cookie;
		
	}



	public void setCookie(String cookie) {

			this.cookie = cookie;
		
	}

	
	
}

