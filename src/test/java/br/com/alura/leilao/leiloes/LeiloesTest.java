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
    public void afterEach() {
        this.page.fechar();
    }

    @Test
    public void preencherNovoLeilaoCorretamente() throws InterruptedException {

        LoginPage login = new LoginPage();
        Thread.sleep(1000);
        login.preencherFormularioDeUsuario("fulano", "pass");
        this.page = login.efetuarLogin();
        
        Thread.sleep(1000);
        CadastroLeilaoPage cadastro = page.carregarFormulario();
        Assertions.assertTrue(page.isPageNewLeilao());
        String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String nome = "Leilao do dia" + hoje;
        String valor = "500.00";

        Thread.sleep(1000);
        this.page = cadastro.preencherNewLeilao(nome, valor, hoje);
        Thread.sleep(1000);
        Assertions.assertTrue(page.isLeilaoCadastrado(nome, valor, hoje));
    }

}
