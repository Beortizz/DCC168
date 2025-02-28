package dcc168.jogodavida;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JogoDaVidaVizinhosTest {
    private JogoDaVida jogo;

    @BeforeEach
    void setUp() {
        jogo = new JogoDaVida();
    }

    @Test
    void testCelulaViva() {
        jogo.setCelula(0, 0, 1);
        Assertions.assertEquals(1, jogo.getCelula(0, 0));
    }

    @Test
    void testCelulaMorta() {
        jogo.setCelula(0, 0, 0);
        Assertions.assertEquals(0, jogo.getCelula(0, 0));
    }

    @Test
    void testCelula_0VizinhosVivos() {
        jogo.setCelula(0, 0, 1);
        Assertions.assertEquals(0, jogo.contarVizinhosVivos(0, 0));
    }

    @Test
    void testCelula_1VizinhoVivo() {
        jogo.setCelula(0, 1, 1);
        Assertions.assertEquals(1, jogo.contarVizinhosVivos(0, 0));
    }

    @Test
    void testCelula_2VizinhosVivos() {
        jogo.setCelula(0, 1, 1);
        jogo.setCelula(1, 0, 1);
        Assertions.assertEquals(2, jogo.contarVizinhosVivos(0, 0));
    }

    @Test
    void testCelula_3VizinhosVivos() {
        jogo.setCelula(0, 0, 1);
        jogo.setCelula(0, 1, 1);
        jogo.setCelula(1, 0, 1);
        jogo.setCelula(1, 1, 1);
        Assertions.assertEquals(3, jogo.contarVizinhosVivos(0, 0));
    }

    @Test
    void testCelula_4VizinhosVivos() {
        jogo.setCelula(0, 0, 1);
        jogo.setCelula(0, 1, 1);
        jogo.setCelula(1, 0, 1);
        jogo.setCelula(1, 1, 1);
        jogo.setCelula(1, 2, 1);
        Assertions.assertEquals(4, jogo.contarVizinhosVivos(1, 1));
    }

    @Test
    void testCelula_5VizinhosVivos() {
        jogo.setCelula(0, 0, 1);
        jogo.setCelula(0, 1, 1);
        jogo.setCelula(1, 0, 1);
        jogo.setCelula(1, 1, 1);
        jogo.setCelula(1, 2, 1);
        jogo.setCelula(2, 1, 1);
        Assertions.assertEquals(5, jogo.contarVizinhosVivos(1, 1));
    }

    @Test
    void testCelula_6VizinhosVivos() {
        jogo.setCelula(0, 0, 1);
        jogo.setCelula(0, 1, 1);
        jogo.setCelula(1, 0, 1);
        jogo.setCelula(1, 1, 1);
        jogo.setCelula(1, 2, 1);
        jogo.setCelula(2, 1, 1);
        jogo.setCelula(2, 2, 1);
        Assertions.assertEquals(6, jogo.contarVizinhosVivos(1, 1));
    }

    @Test
    void testCelula_7VizinhosVivos() {
        jogo.setCelula(0, 0, 1);
        jogo.setCelula(0, 1, 1);
        jogo.setCelula(1, 0, 1);
        jogo.setCelula(1, 1, 1);
        jogo.setCelula(1, 2, 1);
        jogo.setCelula(2, 1, 1);
        jogo.setCelula(2, 2, 1);
        jogo.setCelula(2, 0, 1);
        Assertions.assertEquals(7, jogo.contarVizinhosVivos(1, 1));
    }

    @Test
    void testCelula_8VizinhosVivos() {
        jogo.setCelula(0, 0, 1);
        jogo.setCelula(0, 1, 1);
        jogo.setCelula(1, 0, 1);
        jogo.setCelula(1, 1, 1);
        jogo.setCelula(1, 2, 1);
        jogo.setCelula(2, 1, 1);
        jogo.setCelula(2, 2, 1);
        jogo.setCelula(2, 0, 1);
        jogo.setCelula(0, 2, 1);
        Assertions.assertEquals(8, jogo.contarVizinhosVivos(1, 1));
    }
}
