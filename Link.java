class Link {
    public int iData; // Item de dado
    public Link next; // Próximo nó na lista

    public Link(int id) {
        iData = id; // Inicializa os dados
        next = null; // Inicializa o próximo nó como nulo
    }

    public void displayLink() {
        System.out.print("[" + iData + "] ");
    }
}