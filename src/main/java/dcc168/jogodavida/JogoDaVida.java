package dcc168.jogodavida;
import java.util.Random;
import java.util.Scanner;


public class JogoDaVida {
    private static final int TAMANHO = 6;
    private int[][] tabuleiro;

    public JogoDaVida() {
        tabuleiro = new int[TAMANHO][TAMANHO];
        inicializarTabuleiro();
    }

    public void setTabuleiro(int[][] tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public int getCelula(int x, int y) {
        return tabuleiro[x][y];
    }

    private void inicializarTabuleiro() {
        Random random = new Random();
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                tabuleiro[i][j] = random.nextInt(2);
            }
        }
    }

    public void mostrarTabuleiro() {
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void proximaGeracao() {
        int[][] novoTabuleiro = new int[TAMANHO][TAMANHO];

        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                int vizinhosVivos = contarVizinhosVivos(i, j);

                if (tabuleiro[i][j] == 1) {
                    if (vizinhosVivos < 2 || vizinhosVivos > 3) {
                        novoTabuleiro[i][j] = 0;
                    } else {
                        novoTabuleiro[i][j] = 1;
                    }
                } else {
                    if (vizinhosVivos == 3) {
                        novoTabuleiro[i][j] = 1;
                    } else {
                        novoTabuleiro[i][j] = 0;
                    }
                }
            }
        }

        tabuleiro = novoTabuleiro;
    }

    private int contarVizinhosVivos(int x, int y) {
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

    public static void main(String[] args) {
        JogoDaVida jogo = new JogoDaVida();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Geração Atual:");
            jogo.mostrarTabuleiro();
            System.out.println("Digite ENTER para a próxima geração ou 'sair' para finalizar.");
            String entrada = scanner.nextLine();

            if (entrada.equalsIgnoreCase("sair")) {
                break;
            }
            jogo.proximaGeracao();
        }

        scanner.close();
    }
}
