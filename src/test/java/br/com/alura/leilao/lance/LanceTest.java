/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alura.leilao.lance;

import br.com.alura.leilao.login.LoginPage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author 99030499
 */
public class LanceTest {
    
    private LancePage page;
    
    @BeforeEach
    public void beforeEach() throws InterruptedException{
     LoginPage loginPage = new LoginPage();
     Thread.sleep(1000);
     loginPage.preencherFormularioDeUsuario("fulano", "pass");
     loginPage.efetuarLogin();
     Thread.sleep(1000);
     this.page = new LancePage(loginPage.getStatusBrowser());
    }
    
    
    @AfterEach
    public void afterEach(){
        this.page.fechar();
    }
    
    @Test
    public void darLance() throws InterruptedException{
        String nome = "fulano";
        String data = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String valor = "R$ 600,00";
        
        
        Thread.sleep(1000);
        page.preencherCampo("600");
        Thread.sleep(1000);
        Assertions.assertTrue(page.isLanceCadastro(data, nome, valor));
    }
    
    
    @Test
    public void setValorVazio() throws InterruptedException{
        Thread.sleep(1000);
        page.preencherCampo("");
        Thread.sleep(1000);
        Assertions.assertFalse(page.isPaginaAtual());
        Assertions.assertTrue(page.isTituloLeilaoVisivel("não deve ser nulo"));
        Assertions.assertTrue(page.isTituloLeilaoVisivel("Dados do Leilão"));
        
        
    }
    
    @Test
    public void lanceComValorInferior() throws InterruptedException {
        Thread.sleep(1000);
        page.preencherCampo("600");
        Thread.sleep(1000);
        page.preencherCampo("200");
        
        Thread.sleep(1000);
        Assertions.assertTrue(page.isTituloLeilaoVisivel("Lance invalido!"));
    }
    
}
