package br.com.sermil.miner;

public class RegistroDeAlistamento {



	private String ra;



	private String nome;



	private String nomeDaMae;



	private String dataDeNascimento;



	private String ocupacao;



	private String arrecadacao;



	private String anotacao;



	private String dataAlistamento;



	public RegistroDeAlistamento() {
		
	}



	public RegistroDeAlistamento(String ra, String nome, String situacao, String nomeDaMae, String dataDeNascimento,
			String ocupacao, String arrecadacao, String anotacao) {
		this.ra = ra;
		this.nome = nome;
		this.nomeDaMae = nomeDaMae;
		this.dataDeNascimento = dataDeNascimento;
		this.ocupacao = ocupacao;
		this.arrecadacao = arrecadacao;
		this.anotacao = anotacao;
	}



	public String getRa() {

			return ra; // retorna o ra do array na posição i

	}



	public void setRa(String ra) {

		this.ra = ra; // adciona o ra no array começa na posição 0

	}



	public String getNome() {

			return nome;
		
	}



	public void setNome(String nome) {

			this.nome = nome;
		
	}



	public String getNomeDaMae() {
		return nomeDaMae;
	}



	public void setNomeDaMae(String nomeDaMae) {

			this.nomeDaMae = nomeDaMae;
		
	}



	public String getDataDeNascimento() {

		return dataDeNascimento;

	}



	public void setDataDeNascimento(String dataDeNascimento) {

			this.dataDeNascimento = dataDeNascimento;
		
	}



	public String getOcupacao() {

			return ocupacao;
		
	}



	public void setOcupacao(String ocupacao) {

			this.ocupacao = ocupacao;
		
	}



	public String getArrecadacao() {

			return arrecadacao;
		
	}



	public void setArrecadacao(String arrecadacao) {

			this.arrecadacao = arrecadacao;
		
	}



	public String getAnotacao() {

			return anotacao;
		
	}



	public void setAnotacao(String anotacao) {

			this.anotacao = anotacao;
		
	}



	public String getDataAlistamento() {
		return dataAlistamento;
	}



	public void setDataAlistamento(String dataAlistamento) {
		this.dataAlistamento = dataAlistamento;
	}



}
