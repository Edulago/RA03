public class HashTable {
        private Link[] tabela; // Use Link em vez de int[] para armazenar a lista encadeada
        private int arraySize;
    private static final int VAZIO = -1;
    private int contagemColisoes;
    private int contagemComparacoes;
    private double loadFactor;
    private int funcaoHashEscolhida;

    public HashTable(int size, double loadFactor) {
        arraySize = size;
        this.loadFactor = loadFactor;
        tabela = new Link[arraySize]; // Use Link em vez de int[] para criar a tabela
    }

    public void displayTable() {
        System.out.println("Tabela: ");
        for (int j = 0; j < arraySize; j++) {
            System.out.print("Bucket " + j + ": ");
            Link current = tabela[j];
            while (current != null) {
                System.out.print(current.iData + " ");
                current = current.next;
            }
            if (tabela[j] == null) {
                System.out.print("**");
            }
            System.out.println();
        }
    }
    

    public int hashFuncResto(int chave) {
        return chave % arraySize;
    }

    public void insere_hashing(int chave) {
        int i = hashFuncResto(chave);
        Link newLink = new Link(chave);
        newLink.next = tabela[i];
        tabela[i] = newLink;
        contagemColisoes++;
    
        // Verifica o fator de carga e faz rehash se necessário
        if ((double) contagemColisoes / arraySize > loadFactor) {
            rehash();
        }
    }
    
    public int busca_hashing(int chave) {
        int i = hashFuncResto(chave);
        Link current = tabela[i];
        
        while (current != null) {
            contagemComparacoes++;
            if (current.iData == chave) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }
    private int[] insertInArray(int[] arr, int value) {
        int[] newArr = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        newArr[arr.length] = value;
        return newArr;
    }

    private void rehash() {
        int newSize = arraySize * 2; // Dobra o tamanho da tabela
        Link[] newTable = new Link[newSize];
    
        for (int i = 0; i < arraySize; i++) {
            Link current = tabela[i];
            while (current != null) {
                int newIndex = hashFuncResto(current.iData) % newSize;
                Link newLink = new Link(current.iData);
                newLink.next = newTable[newIndex];
                newTable[newIndex] = newLink;
                current = current.next;
            }
        }
    
        tabela = newTable;
        arraySize = newSize;
    }
    

    public int getContagemColisoes() {
        return contagemColisoes;
    }

    public int getContagemComparacoes() {
        return contagemComparacoes;
    }

}