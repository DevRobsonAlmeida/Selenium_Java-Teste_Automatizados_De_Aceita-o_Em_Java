/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alura.leilao.lance;

import br.com.alura.leilao.PageObject;
import java.time.LocalDate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author 99030499
 */
public class LancePage extends PageObject {

    private static final String URL_LANCES = "http://localhost:8081/leiloes/2";

    public LancePage(WebDriver browser) {
        super(browser);
        this.browser.navigate().to(URL_LANCES);
    }

    public boolean isPaginaAtual() {
        return browser.getCurrentUrl().contains(URL_LANCES);
    }

    public boolean isTituloLeilaoVisivel(String descricao) {
        return browser.getPageSource().contains(descricao);
    }
    
    public void preencherCampo(String valor){
        browser.findElement(By.id("valor")).sendKeys(valor);
        browser.findElement(By.id("btnDarLance")).submit();
    }
    
    public boolean isLanceCadastro(String data, String nome, String valor){
        WebElement linhaDaTable =  browser.findElement(By.cssSelector("#LancesDados tbody tr:last-child"));
        WebElement colunaData   =  linhaDaTable.findElement(By.cssSelector("td:nth-child(1)"));
        WebElement colunaNome   =  linhaDaTable.findElement(By.cssSelector("td:nth-child(2)"));
        WebElement colunaValor  =  linhaDaTable.findElement(By.cssSelector("td:nth-child(3)"));
        
        return colunaData.getText().equals(data) 
                && colunaNome.getText().equals(nome)
                && colunaValor.getText().equals(valor);
    }

}
