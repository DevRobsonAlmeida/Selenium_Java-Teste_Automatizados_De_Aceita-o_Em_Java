/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alura.leilao.login;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
class LoginPage {

    private static final String URL_LOGIN = "http://localhost:8080/login";

    private WebDriver browser;
    private WebElement wait;

    public LoginPage() {
        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
        this.browser = new FirefoxDriver();
        browser.navigate().to(URL_LOGIN);
    }

    public void fechar() {
        this.browser.quit();
    }

    public void preencherFormularioDeUsuario(String username, String password) {
        browser.findElement(By.id("username")).sendKeys(username);
        browser.findElement(By.id("password")).sendKeys(password);
    }

    public void efetuarLogin() {
        browser.findElement(By.id("login-form")).submit();
    }

    public void getWaitTimeInId(String id) {
        wait = (new WebDriverWait(browser, 100)).until(ExpectedConditions.elementToBeClickable(By.id(id)));
    }

    public String getUsuarioLogado() {
        try {
            return browser.findElement(By.id("usuario-logado")).getText();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public boolean isPageLogin() {
        return browser.getCurrentUrl().equals(URL_LOGIN);
    }

    boolean isPageLoginErro() {
        return browser.getCurrentUrl().equals(URL_LOGIN + "?error");
    }

    boolean contemNaPagina(String descricao) {
        return browser.getPageSource().contains(descricao);
    }

    void setPageDeLance(int i) {
        this.browser.navigate().to("http://localhost:8080/leiloes/" + i);
    }

}
