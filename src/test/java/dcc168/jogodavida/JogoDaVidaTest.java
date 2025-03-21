package dcc168.jogodavida;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class JogoDaVidaTest {
    private JogoDaVida jogo;

    @BeforeEach
    void setUp() {
        jogo = new JogoDaVida();
        jogo.setTabuleiro(new int[6][6]);
    }

    @Test
    void testConstructor(){
        var newJogo = new JogoDaVida();
        assertEquals(6, newJogo.getTAMANHO());
        assertEquals(6, newJogo.getTabuleiro().length);
        for (int lin = 0; lin < newJogo.getTAMANHO(); lin++) {
            for (int col = 0; col < newJogo.getTAMANHO(); col++) {
                int valor = newJogo.getCelula(lin, col);
                Assertions.assertTrue(valor == 0 || valor == 1, "Célula deve ser 0 ou 1");
            }
        }
    }

    @Test
    void testCelulaViva_0VizinhosVivos_Morre() {
        jogo.setCelula(0, 0, 1);
        jogo.proximaGeracao();
        assertEquals(0, jogo.getCelula(0, 0));
    }

    @Test
    void testCelulaViva_1VizinhoVivo_Morre() {
        jogo.setCelula(0, 0, 1);
        jogo.setCelula(0, 1, 1);
        jogo.proximaGeracao();
        assertEquals(0, jogo.getCelula(0, 0));
    }

    @Test
    void testCelulaViva_2VizinhosVivos_Sobrevive() {
        jogo.setCelula(0, 0, 1);
        jogo.setCelula(0, 1, 1);
        jogo.setCelula(1, 0, 1);
        jogo.proximaGeracao();
        assertEquals(1, jogo.getCelula(0, 0));
    }

    @Test
    void testCelulaViva_3VizinhosVivos_Sobrevive() {
        jogo.setCelula(0, 0, 1);
        jogo.setCelula(0, 1, 1);
        jogo.setCelula(1, 0, 1);
        jogo.setCelula(1, 1, 1);
        jogo.proximaGeracao();
        assertEquals(1, jogo.getCelula(0, 0));
    }

    @Test
    void testCelulaViva_4VizinhosVivos_Morre() {
        jogo.setCelula(0, 0, 1);
        jogo.setCelula(0, 1, 1);
        jogo.setCelula(0, 2, 1);
        jogo.setCelula(1, 0, 1);
        jogo.setCelula(1, 1, 1);
        jogo.proximaGeracao();
        assertEquals(0, jogo.getCelula(1, 1));
    }


    @Test
    void testCelulaMorta_3VizinhosVivos_Revive() {
        jogo.setCelula(0, 0, 1);
        jogo.setCelula(0, 1, 1);
        jogo.setCelula(1, 0, 1);
        jogo.proximaGeracao();
        assertEquals(1, jogo.getCelula(1, 1));
    }

    @Test
    void testCelulaMorta_2VizinhosVivos_Morre() {
        jogo.setCelula(0, 0, 1);
        jogo.setCelula(0, 1, 1);
        jogo.proximaGeracao();
        assertEquals(0, jogo.getCelula(1, 0));
    }

    @Test
    void testCelulaMorta_4VizinhosVivos_Morre() {
        jogo.setCelula(0, 0, 1);
        jogo.setCelula(0, 1, 1);
        jogo.setCelula(0, 2, 1);
        jogo.setCelula(1, 0, 1);
        jogo.setCelula(1, 1, 1);
        jogo.proximaGeracao();
        assertEquals(0, jogo.getCelula(1, 1));
    }

    @Test
    void testContarVizinhosVivosBordas(){
        jogo.setCelula(0, 0, 1);
        jogo.setCelula(0, 1, 1);
        jogo.setCelula(1, 0, 1);
        jogo.setCelula(1, 1, 1);
        assertEquals(3, jogo.contarVizinhosVivos(0, 0));
        assertEquals(3, jogo.contarVizinhosVivos(0, 1));
        assertEquals(3, jogo.contarVizinhosVivos(1, 0));
        assertEquals(3, jogo.contarVizinhosVivos(1, 1));
    }

    @Test
    void testBordaInferiorDireita() {
        jogo.setCelula(5, 5, 1); // Última célula do tabuleiro
        jogo.setCelula(4, 5, 1);
        jogo.setCelula(5, 4, 1);
        assertEquals(2, jogo.contarVizinhosVivos(5, 5));
    }

    @Test
    void testGetterSetterTabuleiro() {
        jogo.inicializarTabuleiro();
        assertEquals(6, jogo.getTAMANHO());

        int[][] tabuleiro = new int[3][3];
        jogo.setTabuleiro(tabuleiro);
        assertEquals(tabuleiro, jogo.getTabuleiro());
        assertEquals(3, jogo.getTAMANHO());
    }

    @Test
    void testMostrarTabuleiro() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        jogo.setCelula(0, 0, 1);
        jogo.mostrarTabuleiro();
        String expected = "1 0 0 0 0 0 \n" + // Formato esperado
                "0 0 0 0 0 0 \n".repeat(5);
        assertEquals(expected, outputStream.toString());
    }

    @Test
    void testCommandoSair() {
        var systemInBackup = System.in;
        var in = new ByteArrayInputStream("sair".getBytes());
        System.setIn(in);
        try {
            jogo.Run();
        } catch (RuntimeException e) {
            fail();
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
            fail();
        } catch (RuntimeException e) {
            assertEquals("Entrada inválida.", e.getMessage());
        }
        System.setIn(systemInBackup);
    }

    @Test
    void testCommandoEnter() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        var systemInBackup = System.in;
        var in = new ByteArrayInputStream("\nsair".getBytes());
        System.setIn(in);
        try {
            jogo.Run();
            var output = outputStream.toString();
            var expected = "Geração Atual:\n" +
                    "0 0 0 0 0 0 \n" .repeat(6)+
                    "Digite ENTER para a próxima geração ou 'sair' para finalizar.\n" +
                    "Geração Atual:\n" +
                    "0 0 0 0 0 0 \n" .repeat(6)+
                    "Digite ENTER para a próxima geração ou 'sair' para finalizar.";
            assertTrue(output.contains(expected));
        } catch (RuntimeException e) {
            fail();
        }
        System.setIn(systemInBackup);
    }

    @Test
    void testInicializacaoTabuleiro() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        jogo.inicializarTabuleiro();
        jogo.mostrarTabuleiro();
        String expected ="0 0 0 0 0 0 \n".repeat(6);
        assertNotEquals(expected, outputStream.toString());
    }

    @Test
    void testRun(){
        var systemInBackup = System.in;
        var in = new ByteArrayInputStream("\nsair".getBytes());
        System.setIn(in);
        try {
            var oldTabuleiro = jogo.getTabuleiro();
            jogo.Run();
            assertNotEquals(oldTabuleiro, jogo.getTabuleiro());
        } catch (RuntimeException e) {
            fail();
        }
        System.setIn(systemInBackup);
    }

}
