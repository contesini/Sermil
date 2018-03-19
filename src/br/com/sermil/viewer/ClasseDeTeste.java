package br.com.sermil.viewer;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.com.sermil.miner.Documento;

public class ClasseDeTeste {

	public static void main(String[] args) {

		WebDriver driver = new FirefoxDriver();
		driver.get("https://www.sermilweb.eb.mil.br");
		WebElement cpf = driver.findElement(By.id("j_username"));
		cpf.sendKeys("");

		WebElement senha = driver.findElement(By.name("j_password"));
		senha.sendKeys("");

		WebElement btlogin = driver.findElement(By.tagName("button"));
		btlogin.submit();
		String anotacao = "FUNFA PORRA";
		
		driver.get("https://www.sermilweb.eb.mil.br/cidadao/anotacoes!input.action?cidadao.ra=");
		WebElement anotacoes = driver.findElement(By.xpath("html/body/div[1]/div[3]/form/div[1]/div[2]/textarea"));
		anotacoes.sendKeys("\n" + anotacao);
		driver.findElement(By.xpath("html/body/div[1]/div[3]/form/div[2]/button[1]")).submit();
		
	}
}
