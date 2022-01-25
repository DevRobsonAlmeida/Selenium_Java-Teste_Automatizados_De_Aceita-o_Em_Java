/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alura.leilao.leiloes;

import br.com.alura.leilao.PageObject;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author 99030499
 */
public class LeiloesPage extends PageObject{

    private static final String URL_CADASTRO_LEILAO = "http://localhost:8081/leiloes/new";
    private static final String URL_LEILOES = "http://localhost:8081/leiloes";
    
    private WebElement wait;

    public LeiloesPage(WebDriver browser) {
        super(browser);
    }

    public boolean contemTextPage(String descricao) {
        return browser.getPageSource().contains(descricao);
    }

    boolean isPageNewLeilao() {
        System.out.println(browser.getCurrentUrl());
        return browser.getCurrentUrl().equals(URL_CADASTRO_LEILAO);
    }

    public CadastroLeilaoPage carregarFormulario() {
        browser.navigate().to(URL_CADASTRO_LEILAO);
        return new CadastroLeilaoPage(browser);
    }

    boolean isLeilaoCadastrado(String nome, String valor, String hoje) {
        WebElement linhaDatabela = this.browser.findElement(By.cssSelector("#tabela-leiloes tbody tr:last-child"));
        WebElement colunaNome = linhaDatabela.findElement(By.cssSelector("td:nth-child(1)"));
        WebElement colunaDataAbertura = linhaDatabela.findElement(By.cssSelector("td:nth-child(2)"));
        WebElement colunaValorInicial = linhaDatabela.findElement(By.cssSelector("td:nth-child(3)"));

        return colunaNome.getText().equals(nome)
                && colunaDataAbertura.getText().equals(hoje) 
                && colunaValorInicial.getText().equals(valor);
    }

    boolean isPaginaAtual() {
        return browser.getCurrentUrl().contentEquals(URL_LEILOES);
    }

}
