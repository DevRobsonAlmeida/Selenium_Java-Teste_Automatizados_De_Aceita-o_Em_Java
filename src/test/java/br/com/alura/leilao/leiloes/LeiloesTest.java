/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alura.leilao.leiloes;


import br.com.alura.leilao.login.LoginPage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author 99030499
 */
public class LeiloesTest {
    
    private LeiloesPage page;
    
    @AfterEach
    public void afterEach(){
        this.page.fechar();
    }
    
    @Test
    public void preencherNovoLeilaoCorretamente(){

        LoginPage login = new LoginPage();
//        Thread.sleep(500);
        login.preencherFormularioDeUsuario("fulano", "pass");
        this.page = new LeiloesPage(login.efetuarLogin());
        CadastroLeilaoPage cadastro = new CadastroLeilaoPage(page.carregarFormulario());
        String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String nome = "Leilao do dia" + hoje;
        String valor = "500.00";
        

//        this.page.getWaitTimeInId(nome);
        this.page = new LeiloesPage(cadastro.preencherNewLeilao(nome, valor, hoje));
//        Assertions.assertTrue(page.isLeilaoCadastrado(nome, valor, hoje));
    }
}
