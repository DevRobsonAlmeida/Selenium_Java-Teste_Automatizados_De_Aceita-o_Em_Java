/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alura.leilao.leiloes;

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
public class LeiloesPage {

    private static final String URL_CADASTRO_LEILAO = "http://localhost:8081/leiloes/new";
    private static final String URL_LEILOES = "http://localhost:8081/leiloes";

    private WebDriver browser;
    private WebElement wait;

    public LeiloesPage(WebDriver browser) {
        this.browser = browser;
    }

    public void fechar() {
        this.browser.quit();
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

    public void getWaitTimeInId(String id) {
        wait = (new WebDriverWait(browser, 30)).until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));

    }
    public void getWaitTimeInClass(String classe) {
        wait = (new WebDriverWait(browser, 30)).until(ExpectedConditions.visibilityOfElementLocated(By.className(classe)));

    }

    void getWaitLoadPage() {
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
