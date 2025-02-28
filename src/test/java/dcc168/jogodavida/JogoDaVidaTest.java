package dcc168.jogodavida;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void testCelulaMorta_5VizinhosVivos_Morre() {
        jogo.setCelula(0, 0, 1);
        jogo.setCelula(0, 1, 1);
        jogo.setCelula(0, 2, 1);
        jogo.setCelula(1, 0, 1);
        jogo.setCelula(1, 1, 1);
        jogo.setCelula(1, 2, 1);
        jogo.proximaGeracao();
        Assertions.assertEquals(0, jogo.getCelula(1, 1));
    }

    @Test
    void testCelulaMorta_6VizinhosVivos_Morre() {
        jogo.setCelula(0, 0, 1);
        jogo.setCelula(0, 1, 1);
        jogo.setCelula(0, 2, 1);
        jogo.setCelula(1, 0, 1);
        jogo.setCelula(1, 1, 1);
        jogo.setCelula(1, 2, 1);
        jogo.setCelula(2, 1, 1);
        jogo.proximaGeracao();
        Assertions.assertEquals(0, jogo.getCelula(1, 1));
    }

    @Test
    void testCelulaMorta_7VizinhosVivos_Morre() {
        jogo.setCelula(0, 0, 1);
        jogo.setCelula(0, 1, 1);
        jogo.setCelula(0, 2, 1);
        jogo.setCelula(1, 0, 1);
        jogo.setCelula(1, 1, 1);
        jogo.setCelula(1, 2, 1);
        jogo.setCelula(2, 1, 1);
        jogo.setCelula(2, 2, 1);
        jogo.proximaGeracao();
        Assertions.assertEquals(0, jogo.getCelula(1, 1));
    }

    @Test
    void testCelulaMorta_8VizinhosVivos_Morre() {
        jogo.setCelula(0, 0, 1);
        jogo.setCelula(0, 1, 1);
        jogo.setCelula(0, 2, 1);
        jogo.setCelula(1, 0, 1);
        jogo.setCelula(1, 1, 1);
        jogo.setCelula(1, 2, 1);
        jogo.setCelula(2, 1, 1);
        jogo.setCelula(2, 2, 1);
        jogo.setCelula(2, 0, 1);
        jogo.proximaGeracao();
        Assertions.assertEquals(0, jogo.getCelula(1, 1));
    }
}
