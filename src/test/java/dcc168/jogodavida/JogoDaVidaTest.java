package dcc168.jogodavida;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JogoDaVidaTest {
    private JogoDaVida jogo;

    @BeforeEach
    void setUp() {
        jogo = new JogoDaVida();
    }

    @Test
    void testCelulaVivaMorrePorSolidão_0Vizinhos() {
        int[][] estadoInicial = new int[6][6];
        estadoInicial[2][2] = 1; // Célula viva isolada
        jogo.setTabuleiro(estadoInicial);
        jogo.proximaGeracao();
        assertEquals(0, jogo.getCelula(2, 2)); // Deve morrer
    }

    @Test
    void testCelulaVivaMorrePorSolidão_1Vizinho() {
        int[][] estadoInicial = new int[6][6];
        estadoInicial[2][2] = 1;
        estadoInicial[2][3] = 1; // Apenas 1 vizinho vivo
        jogo.setTabuleiro(estadoInicial);
        jogo.proximaGeracao();
        assertEquals(0, jogo.getCelula(2, 2));
    }

    @Test
    void testCelulaVivaSobrevive_2Vizinhos() {
        int[][] estadoInicial = new int[6][6];
        estadoInicial[2][2] = 1;
        estadoInicial[2][3] = 1;
        estadoInicial[3][2] = 1; // 2 vizinhos vivos
        jogo.setTabuleiro(estadoInicial);
        jogo.proximaGeracao();
        assertEquals(1, jogo.getCelula(2, 2)); // Sobrevive
    }

    @Test
    void testCelulaVivaSobrevive_3Vizinhos() {
        int[][] estadoInicial = new int[6][6];
        estadoInicial[2][2] = 1;
        estadoInicial[2][3] = 1;
        estadoInicial[3][2] = 1;
        estadoInicial[3][3] = 1; // 3 vizinhos vivos
        jogo.setTabuleiro(estadoInicial);
        jogo.proximaGeracao();
        assertEquals(1, jogo.getCelula(2, 2));
    }

    @Test
    void testCelulaVivaMorrePorSuperpopulação() {
        int[][] estadoInicial = new int[6][6];
        estadoInicial[2][2] = 1;
        estadoInicial[2][3] = 1;
        estadoInicial[3][2] = 1;
        estadoInicial[3][3] = 1;
        estadoInicial[1][2] = 1; // 4 vizinhos vivos
        jogo.setTabuleiro(estadoInicial);
        jogo.proximaGeracao();
        assertEquals(0, jogo.getCelula(2, 2)); // Morre por superpopulação
    }

    @Test
    void testCelulaMortaRevivePorReproducao() {
        int[][] estadoInicial = new int[6][6];
        estadoInicial[2][3] = 1;
        estadoInicial[3][2] = 1;
        estadoInicial[3][3] = 1; // 3 vizinhos vivos
        jogo.setTabuleiro(estadoInicial);
        jogo.proximaGeracao();
        assertEquals(1, jogo.getCelula(2, 2)); // Deve reviver
    }

    @Test
    void testCelulaMortaPermaneceMorta_MenosDe3Vizinhos() {
        int[][] estadoInicial = new int[6][6];
        estadoInicial[2][3] = 1;
        estadoInicial[3][2] = 1; // Apenas 2 vizinhos vivos
        jogo.setTabuleiro(estadoInicial);
        jogo.proximaGeracao();
        assertEquals(0, jogo.getCelula(2, 2));
    }

    @Test
    void testCelulaMortaPermaneceMorta_MaisDe3Vizinhos() {
        int[][] estadoInicial = new int[6][6];
        estadoInicial[2][3] = 1;
        estadoInicial[3][2] = 1;
        estadoInicial[3][3] = 1;
        estadoInicial[1][2] = 1; // 4 vizinhos vivos
        jogo.setTabuleiro(estadoInicial);
        jogo.proximaGeracao();
        assertEquals(0, jogo.getCelula(2, 2));
    }
}
