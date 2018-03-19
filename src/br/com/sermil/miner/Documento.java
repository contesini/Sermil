package br.com.sermil.miner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

public class Documento {

	public static List<String> linhaRA = new ArrayList();


	public static List<String> codProfissao = new ArrayList();
	public static List<String> anotacoes = new ArrayList();
	
		
	public Documento(String linhaRa, String codProfissao, String anotacoes) {
		this.linhaRA.add(linhaRa);
		this.codProfissao.add(codProfissao);
		this.anotacoes.add(anotacoes);
	}

	public static String getCodProfissao(int posicao) {
		return codProfissao.get(posicao);
	}

	public void setCodProfissao(int count, String codProfissao) {
		count = 0;
		this.codProfissao.add(count, codProfissao);
		count++;
	}

	public static String getAnotacoes(int posicao) {
		return anotacoes.get(posicao);
	}

	public void setAnotacoes(int count, String anotacoes) {
		count = 0;
		this.anotacoes.add(count, anotacoes);
		count++;
	}

	public Documento() {

	}

	public static String getLinhaRA(int posicao) {
		return linhaRA.get(posicao);

	}

	public static int getLinhaRALength() {
		return linhaRA.size();
	}

	public void setLinhaRA(int count, String linhaRA) {
		count = 0;
		this.linhaRA.add(count, linhaRA);
		count++;
	}

	public void chooser() {
		Documento documento = new Documento();
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(null);
		try {
			FileReader arq = new FileReader(chooser.getSelectedFile());
			BufferedReader lerArq = new BufferedReader(arq);
			String numeroRa;
			while ((numeroRa = lerArq.readLine()) != null) {
				int count = 0;
				documento.setLinhaRA(count, numeroRa);
				count++;

			}

			arq.close();
			lerArq.close();

		} catch (IOException localIOException) {
		}

	}

	public void limparArray() {
		for (int i = 0; i < linhaRA.size(); i++) {
			linhaRA.remove(i);
		}
	}

	public void chooserOcupacao() {
		Documento documento = new Documento();
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(null);
		try {
			FileReader arq = new FileReader(chooser.getSelectedFile());
			BufferedReader lerArq = new BufferedReader(arq);
			String[] texto1;
			String texto2;
			while ((texto2 = lerArq.readLine()) != null) { // texto1[4]
															// ocupacao,
															// texto1[0] ra e
															// texto1[6]
															// anotacoes //

				int count = 0;
				
				

				texto1 = texto2.split(",");
				documento.setCodProfissao(count, texto1[5]);
				documento.setLinhaRA(count, texto1[0]);
				documento.setAnotacoes(count, texto1[7]);

				count++;

			}

			arq.close();
			lerArq.close();

		} catch (IOException localIOException) {
		}

	}

}
