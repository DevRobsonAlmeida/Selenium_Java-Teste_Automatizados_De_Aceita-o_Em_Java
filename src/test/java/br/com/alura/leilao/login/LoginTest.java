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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author 99030499
 */
public class LoginTest {
       
    private LoginPage page;

    @BeforeEach
    public void beforeAll() {
       this.page = new LoginPage();
    }
    
    @AfterEach
    public void afterEach(){
        this.page.fechar();
    }

    @Test
    public void efetuarLoginCorreto() {
        page.preencherFormularioDeUsuario("fulano", "pass");
        page.efetuarLogin();

        page.getWaitTimeInId("usuario-logado");
        
        Assertions.assertFalse(page.isPageLogin());
        Assertions.assertEquals("fulano", page.getUsuarioLogado());
    }

    @Test
    public void efetuarLoginIncorreto() throws InterruptedException {
        page.preencherFormularioDeUsuario("invalido", "12346");
        page.efetuarLogin();
        
        page.getWaitTimeInId("login-incorreto");

        Assertions.assertTrue(page.isPageLoginErro());
        Assertions.assertTrue(page.contemNaPagina("Usuário e senha inválidos"));
        Assertions.assertNull(page.getUsuarioLogado());

        page.fechar();
    }
      
    @Test
    public void bloqueioAcessoSemLogin(){
        page.setPageDeLance(2);
        
        Assertions.assertTrue(page.isPageLogin());
        Assertions.assertFalse(page.contemNaPagina("Dados do Leilão"));
    }

}
