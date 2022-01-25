/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alura.leilao;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author 99030499
 */
public class PageObject {

    protected WebDriver browser;

    public PageObject(WebDriver browser) {
        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");

        if (browser == null) {
            this.browser = new FirefoxDriver();
        } else {
            this.browser = browser;
        }
          
//        this.browser.manage().timeouts()
                  //Guarda o atributos aguarda
//                .implicitlyWait(5, TimeUnit.SECONDS)
                  //Guarda a pagina carregar
//                .pageLoadTimeout(10, TimeUnit.SECONDS);
//        
    }
    
    public void fechar(){
        this.browser.quit();
    }
}
