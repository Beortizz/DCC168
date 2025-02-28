package dcc168.jogodavida;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

public class JogoDaVidaTest {
    private JogoDaVida jogo;

    @BeforeEach
    void setUp() {
        jogo = new JogoDaVida();
    }

    @Test
    void testCelulaViva_0VizinhosVivos_Morre() {
        jogo.setCelula(0, 0, 1);
        jogo.proximaGeracao();
        Assertions.assertEquals(0, jogo.getCelula(0, 0));
    }

    @Test
    void testCelulaViva_1VizinhoVivo_Morre() {
        jogo.setCelula(0, 0, 1);
        jogo.setCelula(0, 1, 1);
        jogo.proximaGeracao();
        Assertions.assertEquals(0, jogo.getCelula(0, 0));
    }

    @Test
    void testCelulaViva_2VizinhosVivos_Sobrevive() {
        jogo.setCelula(0, 0, 1);
        jogo.setCelula(0, 1, 1);
        jogo.setCelula(1, 0, 1);
        jogo.proximaGeracao();
        Assertions.assertEquals(1, jogo.getCelula(0, 0));
    }

    @Test
    void testCelulaViva_3VizinhosVivos_Sobrevive() {
        jogo.setCelula(0, 0, 1);
        jogo.setCelula(0, 1, 1);
        jogo.setCelula(1, 0, 1);
        jogo.setCelula(1, 1, 1);
        jogo.proximaGeracao();
        Assertions.assertEquals(1, jogo.getCelula(0, 0));
    }

    @Test
    void testCelulaViva_4VizinhosVivos_Morre() {
        jogo.setCelula(0, 0, 1);
        jogo.setCelula(0, 1, 1);
        jogo.setCelula(0, 2, 1);
        jogo.setCelula(1, 0, 1);
        jogo.setCelula(1, 1, 1);
        jogo.proximaGeracao();
        Assertions.assertEquals(0, jogo.getCelula(1, 1));
    }


    @Test
    void testCelulaMorta_3VizinhosVivos_Revive() {
        jogo.setCelula(0, 0, 1);
        jogo.setCelula(0, 1, 1);
        jogo.setCelula(1, 0, 1);
        jogo.proximaGeracao();
        Assertions.assertEquals(1, jogo.getCelula(1, 1));
    }

    @Test
    void testCelulaMorta_2VizinhosVivos_Morre() {
        jogo.setCelula(0, 0, 1);
        jogo.setCelula(0, 1, 1);
        jogo.proximaGeracao();
        Assertions.assertEquals(0, jogo.getCelula(1, 0));
    }

    @Test
    void testCelulaMorta_4VizinhosVivos_Morre() {
        jogo.setCelula(0, 0, 1);
        jogo.setCelula(0, 1, 1);
        jogo.setCelula(0, 2, 1);
        jogo.setCelula(1, 0, 1);
        jogo.setCelula(1, 1, 1);
        jogo.proximaGeracao();
        Assertions.assertEquals(0, jogo.getCelula(1, 1));
    }

    @Test
    void testCommandoSair() {
        var systemInBackup = System.in;
        var in = new ByteArrayInputStream("sair".getBytes());
        System.setIn(in);
        try {
            jogo.Run();
        } catch (RuntimeException e) {
            Assertions.fail();
        }
        System.setIn(systemInBackup);
    }

    @Test
    void testCommandoInvalido() {
        var systemInBackup = System.in;
        var in = new ByteArrayInputStream("comando".getBytes());
        System.setIn(in);
        try {
            jogo.Run();
            Assertions.fail();
        } catch (RuntimeException e) {
            Assertions.assertEquals("Entrada inválida.", e.getMessage());
        }
        System.setIn(systemInBackup);
    }

    @Test
    void testCommandoEnter() {
        var systemInBackup = System.in;
        var in = new ByteArrayInputStream("\nsair".getBytes());
        System.setIn(in);
        try {
            jogo.Run();
        } catch (RuntimeException e) {
            Assertions.fail();
        }
        System.setIn(systemInBackup);
    }

}
