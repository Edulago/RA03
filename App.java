import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo ao teste da tabela hash!");

        int[] tamanhos = {10, 100, 1000, 10000, 100000}; // Tamanhos desejados

        for (int i = 0; i < tamanhos.length; i++) {
            int tamanhoEscolhido = tamanhos[i];
            System.out.println("Tabela hash criada com tamanho " + tamanhoEscolhido + ".");
            
            int tamanhoDados = 20000 * (i + 1); // Tamanho dos dados (20k, 100k, 500k, 1M, 5M)
            int contagemBuscas = 5; // Número padrão de buscas
            int escolhaFuncaoHash = 0;
            
            while (escolhaFuncaoHash < 1 || escolhaFuncaoHash > 3) {
                System.out.println("\nEscolha uma função de hash:");
                System.out.println("1. XOR");
                System.out.println("2. Multiplicação");
                System.out.println("3. Soma dos Valores ASCII");
                escolhaFuncaoHash = scanner.nextInt();
            }
            
            testarTamanhoTabela(tamanhoEscolhido, tamanhoDados, contagemBuscas, escolhaFuncaoHash);
        }
        
        System.out.println("Programa encerrado.");
    }
    
    public static void testarTamanhoTabela(int tamanhoTabela, int tamanhoDados, int contagemBuscas, int escolhaFuncaoHash) {
        HashTable hashTable = new HashTable(tamanhoTabela, 0.7);
    
        Registro[] dados = new Registro[tamanhoDados];
    
        // Gerar dados aleatórios
        Random random = new Random();
        for (int i = 0; i < tamanhoDados; i++) {
            int codigoRegistro = 100000000 + random.nextInt(900000000); // Gera números de 100.000.000 a 999.999.999
            dados[i] = new Registro(codigoRegistro);
        }
    
        long inicioInsercao = System.nanoTime();
        for (Registro dado : dados) {
            int chave = dado.getCodigo();
            int valorHash = 0;
    
            // Escolher a função de hash com base na escolha do usuário
            switch (escolhaFuncaoHash) {
                case 1:
                    valorHash = Functions.HashFunctions.hashXOR(chave, tamanhoTabela);
                    break;
                case 2:
                    valorHash = Functions.HashFunctions.hashMultiplicacao(chave, tamanhoTabela);
                    break;
                case 3:
                    valorHash = Functions.HashFunctions.hashSomaValoresASCII(String.valueOf(chave), tamanhoTabela);
                    break;
                default:
                    System.out.println("Função de hash inválida.");
                    return;
            }
    
            hashTable.insere_hashing(valorHash);
        }
        long fimInsercao = System.nanoTime();
        long tempoInsercao = fimInsercao - inicioInsercao;
        int contagemColisoes = hashTable.getContagemColisoes();
    
        System.out.println("Tempo de Inserção (ns): " + tempoInsercao);
        System.out.println("Contagem de Colisões: " + contagemColisoes);
    
        long tempoBuscaTotal = 0;
        int contagemComparacoesTotal = 0;
    
        // Realizar buscas em registros aleatórios
        for (int i = 0; i < contagemBuscas; i++) {
            int chaveBusca = dados[random.nextInt(tamanhoDados)].getCodigo();
            int valorHashBusca = 0;
    
            // Escolher a função de hash para a busca com base na escolha do usuário
            switch (escolhaFuncaoHash) {
                case 1:
                    valorHashBusca = Functions.HashFunctions.hashXOR(chaveBusca, tamanhoTabela);
                    break;
                case 2:
                    valorHashBusca = Functions.HashFunctions.hashMultiplicacao(chaveBusca, tamanhoTabela);
                    break;
                case 3:
                    valorHashBusca = Functions.HashFunctions.hashSomaValoresASCII(String.valueOf(chaveBusca), tamanhoTabela);
                    break;
                default:
                    System.out.println("Função de hash inválida.");
                    return;
            }
    
            long inicioBusca = System.nanoTime();
            int resultado = hashTable.busca_hashing(valorHashBusca);
            long fimBusca = System.nanoTime();
            long tempoBusca = fimBusca - inicioBusca;
            int contagemComparacoes = hashTable.getContagemComparacoes();
    
            tempoBuscaTotal += tempoBusca;
            contagemComparacoesTotal += contagemComparacoes;
        }
    
        System.out.println("Tempo médio de Busca (ns): " + (tempoBuscaTotal / contagemBuscas));
        System.out.println("Contagem média de Comparações: " + (contagemComparacoesTotal / contagemBuscas));
    }
    
}