package dcc168.jogodavida;

import java.util.Random;
import java.util.Scanner;


public class JogoDaVida {
    private int TAMANHO = 6;
    private int[][] tabuleiro;

    public int[][] getTabuleiro() {
        return tabuleiro;
    }

    public int getTAMANHO() {
        return TAMANHO;
    }

    public void setTAMANHO(int TAMANHO) {
        this.TAMANHO = TAMANHO;
    }

    public JogoDaVida() {
        this.setTabuleiro(new int[TAMANHO][TAMANHO]);
        this.inicializarTabuleiro();
    }

    public void Run() {
        try (Scanner scanner = new Scanner(System.in)) {

            while (true) {
                System.out.println("Geração Atual:");
                this.mostrarTabuleiro();
                System.out.println("Digite ENTER para a próxima geração ou 'sair' para finalizar.");
                String entrada = scanner.nextLine();

                if (entrada.equalsIgnoreCase("sair"))
                    break;
                else if (entrada.equalsIgnoreCase(""))
                    this.proximaGeracao();
                else {
                    throw new RuntimeException("Entrada inválida.");
                }
            }
        }
    }

    public void setTabuleiro(int[][] tabuleiro) {
        this.tabuleiro = tabuleiro;
        this.setTAMANHO(tabuleiro.length);
    }

    public int getCelula(int x, int y) {
        return tabuleiro[x][y];
    }

    public void setCelula(int x, int y, int valor) {
        tabuleiro[x][y] = valor;
    }

    public void inicializarTabuleiro() {
        Random random = new Random();
        for (int lin = 0; lin < TAMANHO; lin++) {
            for (int col = 0; col < TAMANHO; col++) {
                tabuleiro[lin][col] = random.nextInt(2);
            }
        }
    }

    public void mostrarTabuleiro() {
        for (int lin = 0; lin < TAMANHO; lin++) {
            for (int col = 0; col < TAMANHO; col++) {
                System.out.print(tabuleiro[lin][col] + " ");
            }
            System.out.println();
        }
    }

    public void proximaGeracao() {
        int[][] novoTabuleiro = new int[TAMANHO][TAMANHO];

        for (int lin = 0; lin < TAMANHO; lin++) {
            for (int col = 0; col < TAMANHO; col++) {
                int vizinhosVivos = contarVizinhosVivos(lin, col);

                if (tabuleiro[lin][col] == 1) {
                    if (vizinhosVivos < 2 || vizinhosVivos > 3) {
                        novoTabuleiro[lin][col] = 0;
                    } else {
                        novoTabuleiro[lin][col] = 1;
                    }
                } else {
                    if (vizinhosVivos == 3) {
                        novoTabuleiro[lin][col] = 1;
                    }
                }
            }
        }

        tabuleiro = novoTabuleiro;
    }

    public int contarVizinhosVivos(int x, int y) {
        int contagem = 0;
        int[] direcoes = {-1, 0, 1};

        for (int dx : direcoes) {
            for (int dy : direcoes) {
                if (dx == 0 && dy == 0) continue;
                int nx = x + dx;
                int ny = y + dy;

                if (nx >= 0 && nx < TAMANHO && ny >= 0 && ny < TAMANHO) {
                    contagem += tabuleiro[nx][ny];
                }
            }
        }

        return contagem;
    }
}
