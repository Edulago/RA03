public class Functions {
    public static class HashFunctions {
        // Função hash usando operação XOR
        public static int hashXOR(int chave, int arraySize) {
            return chave ^ arraySize;
        }

        // Função hash usando operação de multiplicação
        public static int hashMultiplicacao(int chave, int arraySize) {
            double A = 0.6180339887; // Fator de escala (constante mágica)
            double valorHash = chave * A;
            valorHash -= Math.floor(valorHash);
            return (int) (arraySize * valorHash);
        }
    


        // Função hash usando soma dos valores ASCII
        public static int hashSomaValoresASCII(String chave, int arraySize) {
            int soma = 0;
            for (char c : chave.toCharArray()) {
                soma += (int) c;
            }
            return soma % arraySize;
        }
    }
}

