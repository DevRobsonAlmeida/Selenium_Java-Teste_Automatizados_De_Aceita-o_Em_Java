/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alura.leilao.login;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author 99030499
 */
public class LoginTest {

    private static final String URL_LOGIN = "http://localhost:8080/login";
    
    private WebDriver browser;
    private WebElement wait;
    

    @BeforeAll
    public static void beforeAll() {
        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
    }

    @BeforeEach
    public void beforeEach() {
        this.browser = new FirefoxDriver();
        browser.navigate().to(URL_LOGIN);
    }

    @AfterEach
    public void afterEach() {
        browser.quit();
    }

    @Test
    public void efetuarLoginCorreto() {
        browser.findElement(By.id("username")).sendKeys("fulano");
        browser.findElement(By.id("password")).sendKeys("pass");
        browser.findElement(By.id("login-form")).submit();

        wait = (new WebDriverWait(browser, 100)).until(ExpectedConditions.elementToBeClickable(By.id("usuario-logado")));

        String user = browser.findElement(By.id("usuario-logado")).getText();
        Assertions.assertFalse(browser.getCurrentUrl().equals(URL_LOGIN));
        Assertions.assertEquals("fulano", user);

    }

    @Test
    public void efetuarLoginIncorreto() throws InterruptedException {
        browser.findElement(By.id("username")).sendKeys("beltrano");
        browser.findElement(By.id("password")).sendKeys("ssap");
        browser.findElement(By.id("login-form")).submit();

        wait = (new WebDriverWait(browser, 100)).until(ExpectedConditions.elementToBeClickable(By.id("login-incorreto")));

        Assertions.assertTrue(browser.getCurrentUrl().equals(URL_LOGIN + "?error"));
        boolean erro = browser.getPageSource().contains("Usuário e senha inválidos");
        Assertions.assertTrue(erro);

        Assertions.assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("usuario-logado")).getText());

        browser.quit();
    }
    
    @Test
    public void bloqueioAcessoSemLogin(){
        this.browser.navigate().to("http://localhost:8080/leiloes/2");
        
        Assertions.assertTrue(browser.getCurrentUrl().equals("http://localhost:8080/login"));
        Assertions.assertFalse(browser.getPageSource().contains("Dados do Leilão"));
    }

}
