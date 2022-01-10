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

}
