/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alura.leilao.leiloes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author 99030499
 */
class CadastroLeilaoPage {
    
    private static final String URL_CADASTRO_LEILAO = "http://localhost:8081/leiloes/new";
    
    private WebDriver browser;

    public CadastroLeilaoPage(WebDriver browser) {
        this.browser = browser;
    }
    
    void fechar(){
        this.browser.quit();
    }

    public LeiloesPage preencherNewLeilao(String nome, String valor, String hoje) {
        browser.findElement(By.id("nome")).sendKeys(nome);
        browser.findElement(By.id("valorInicial")).sendKeys(valor);
        browser.findElement(By.id("dataAbertura")).sendKeys(hoje);
        browser.findElement(By.id("button-submit")).submit();
        
        return new LeiloesPage(browser);
    }

    boolean isPaginaAtual() {
        return browser.getCurrentUrl().equals(URL_CADASTRO_LEILAO);
    }

    boolean isMensagemDeValidacao() {
        String pageSource = browser.getPageSource();
        return pageSource.contains("não deve estar em branco") 
                && pageSource.contains("minimo 3 caracteres")
                && pageSource.contains("deve ser um valor maior de 0.1")
                && pageSource.contains("deve ser uma data no formato dd/MM/yyyy");
    }

}
