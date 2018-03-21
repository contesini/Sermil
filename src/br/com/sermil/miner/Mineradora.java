package br.com.sermil.miner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Mineradora {


	private WebDriver driver;


	private WebElement cpf;


	private WebElement senha;


	private WebElement btlogin;


	private RegistroDeAlistamento registro;


	private FirefoxProfile suporte;


	private String proxy;



	public Mineradora() {
		//this.driver = new FirefoxDriver(getProxy());
		FirefoxBinary binary = new FirefoxBinary(new File("/opt/firefox/firefox"));
		FirefoxProfile profile = new FirefoxProfile();
		this.driver = new FirefoxDriver(binary, profile);

		this.registro = new RegistroDeAlistamento();
		this.proxy = "https://";
	}



	public boolean login(Militar militar) {
		System.out.println("\t\t\t\tFazendo login");
		this.driver.get("https://www.sermilweb.eb.mil.br");

		this.cpf = this.driver.findElement(By.id("username"));
		this.cpf.sendKeys(new CharSequence[] { militar.getCpf() });

		this.senha = this.driver.findElement(By.name("password"));
		this.senha.sendKeys(new CharSequence[] { militar.getSenha() });

		this.btlogin = this.driver.findElement(By.tagName("button"));
		this.btlogin.submit();
		try {
			WebElement sair = this.driver.findElement(By.xpath(".//*[@id='erroModal']/div/div/div[3]/button"));
			return false;
		} catch (Exception localException) {
		}
		return true;
	}



	public void minerar(String ra) {

		this.registro.setRa(ra);
		this.driver.get("https://www.sermilweb.eb.mil.br/cidadao/recuperar.action?cidadao.ra=" + ra);

		WebElement alistamento = this.driver.findElement(By.id("alistamento"));
		WebElement botaoEditar = this.driver.findElement(By.xpath(".//*[@id='alistamento']/div/div[1]/a"));
		WebDriverWait wait = new WebDriverWait(this.driver, 45L);
		wait.until(ExpectedConditions.elementToBeClickable(botaoEditar));

		String[] ali = new String[27];
		ali = alistamento.getText().split("\n");

		String nome = ali[2].substring(4);
		this.registro.setNome(nome);

		String nomeDaMae = ali[3].substring(11);
		this.registro.setNomeDaMae(nomeDaMae);

		String dataDeNascimento = ali[4].substring(19);
		String data = dataDeNascimento;
		this.registro.setDataDeNascimento(dataDeNascimento);

		String ocupacao = "sem ocupacao";
		boolean condicao = true;
		for (int i = 0; i < ali.length; i++) {
			if (ali[i].length() > 8) {
				ocupacao = ali[i].substring(0, 8);
				if (ocupacao.contains("Ocupação")) {
					ocupacao = ali[i].substring(17);
					condicao = false;
					i = ali.length;
				} 
				
			}
		}
		if (condicao) {
			ocupacao = "sem ocupacao";
		}
		System.out.println(ocupacao);
		this.registro.setOcupacao(ocupacao);

		System.out.println(ra + "\n" + nome + "\n" + nomeDaMae + "\n" + data + "\n" + this.registro.getOcupacao() + "\n");
		
	}



	
	public void getDataAlistamento() {
		this.driver.get("https://www.sermilweb.eb.mil.br/cidadao/recuperar.action?cidadao.ra=" + this.registro.getRa());

		((JavascriptExecutor) this.driver).executeScript("showInfo('eventos');", new Object[0]);
		sleep(1000);

		WebElement dataAlistamento = this.driver.findElement(By.xpath(".//*[@id='bc']/div[3]/div[2]/div[2]/div[3]/ul/li[2]"));
		String data = dataAlistamento.getText().substring(13);
		this.registro.setDataAlistamento(data);
	}



	public void minerarRa(String ra) {
		this.registro.setRa(ra);
		this.driver.get("https://www.sermilweb.eb.mil.br/cidadao/recuperar.action?cidadao.ra=" + ra);
		try {
			WebElement alistamento = this.driver.findElement(By.id("alistamento"));

			WebElement botaoEditar = this.driver.findElement(By.xpath(".//*[@id='alistamento']/div/div[1]/a"));

			WebDriverWait wait = new WebDriverWait(this.driver, 45L);
			wait.until(ExpectedConditions.elementToBeClickable(botaoEditar));
		} catch (Exception localException) {
		}
	}



	public void anotacoes() {
		this.driver.get(
				"https://www.sermilweb.eb.mil.br/cidadao/anotacoes!input.action?cidadao.ra=" + this.registro.getRa());
		WebElement anotacoes = this.driver.findElement(By.id("anotacoes"));
		String anota = "";
		try {
			anota = anotacoes.getText().substring(33);
			this.registro.setAnotacao(anota.substring(1, anota.length() - 43));
		} catch (Exception e) {
			anota = "sem anotacao";
			this.registro.setAnotacao(anota);
		}
	}



	public void salvarCsv() throws IOException {
		String numeroCSM = this.registro.getRa().substring(0, 2);
		String numeroJunta = this.registro.getRa().substring(2, 5);
		if (this.registro.getOcupacao().equals("sem ocupacao")) {
			FileWriter myWriter = new FileWriter("CidadoSemOcupacao.csv", true);
			myWriter.append(this.registro.getRa()+ ";" + numeroCSM +";"+ numeroJunta+";"+ this.registro.getDataAlistamento()+ ";" + this.registro.getNome() + ";" 
			+ this.registro.getDataDeNascimento() + ";" + this.registro.getNomeDaMae() + ";"
					+ " " +  ";"
					+ this.registro.getAnotacao() + "\n");
			myWriter.flush();
			myWriter.close();
		} else {
			FileWriter myWriter = new FileWriter("FAMs.csv", true);
			myWriter.append(this.registro.getRa()+ ";" + numeroCSM +";"+ numeroJunta+";"+ this.registro.getDataAlistamento()+ ";" + this.registro.getNome() + ";" 
			+ this.registro.getDataDeNascimento() + ";" + this.registro.getNomeDaMae() + ";"
			+ this.registro.getOcupacao() + ";"
					+ this.registro.getAnotacao() + "\n");
			
			myWriter.flush();
			myWriter.close();
		}
	}



	public void logout() {
		this.driver.get("https://www.sermilweb.eb.mil.br/portal/j_spring_security_logout");
		this.driver.close();
	}



	private void sleep(int mil) {
		try {
			Thread.sleep(mil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public void erroRA() throws IOException {
		FileWriter myWriter = new FileWriter("FAMs.csv", true);
		myWriter.append(this.registro.getRa() + ";" + "NAO ENCONTRADO" + ";" + "NAO ENCONTRADO" + ";" + "NAO ENCONTRADO"
				+ ";" + "NAO ENCONTRADO" + ";" + "NAO ENCONTRADO" + "\n");
		myWriter.flush();
		myWriter.close();
	}



	public void gerarRelatorio() throws IOException {
		if (this.registro.getOcupacao().equals("sem ocupacao")) {
			String[] anotacoes = this.registro.getAnotacao().split("ocupacao como:  '");
			String ocupacaoAnatocao = anotacoes[1].substring(0, anotacoes[1].length() - 3);

			this.registro.setOcupacao(ocupacaoAnatocao);
		} else if (this.registro.getOcupacao().isEmpty()) {
			this.registro.setOcupacao("SEM OCUPACAO NO SERMIL");
		}
		FileWriter myWriter = new FileWriter("Relatorio.csv", true);
		myWriter.append(this.registro.getRa() + ";" + this.registro.getNome() + ";" + this.registro.getNomeDaMae() + ";"
				+ this.registro.getDataDeNascimento() + ";" + this.registro.getOcupacao() + "\n");
		myWriter.flush();
		myWriter.close();
	}



	public FirefoxProfile getProxy() {
		FirefoxProfile suporte = new FirefoxProfile();

		suporte.setPreference("network.proxy.type", 0);
		suporte.setPreference("network.proxy.http", "proxy.lages.sc.gov.br");
		suporte.setPreference("network.proxy.http_port", 3129);
		suporte.setPreference("network.proxy.ssl", "proxy.lages.sc.gov.br");
		suporte.setPreference("network.proxy.ssl_port", 3129);
		suporte.setPreference("network.proxy.ftp", "proxy.lages.sc.gov.br");
		suporte.setPreference("network.proxy.ftp_port", 3129);
		suporte.setPreference("network.proxy.socks", "proxy.lages.sc.gov.br");
		suporte.setPreference("network.proxy.socks_port", 3129);

		return suporte;
	}



}
