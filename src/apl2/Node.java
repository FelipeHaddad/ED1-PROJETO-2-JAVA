// arquivo: src/apl2/Node.java

/*
 Felipe Haddad - 10437372
 Beatriz Nobrega - 10435789
 Carolina Lee - 10440304
 Ana Julia Matilha - 10436655
 */

package apl2;

// -- A classe Node (que pertence ao pacote apl2) deve conter os atributos que
// representam a nova versão dos dados de uma pessoa, conforme descrito no
// enunciado da atividade Apl2.
// -- A classe deve conter os construtores apropriados, assim como os métodos
// getters e setters.
// -- A classe também representa um nó que é usado na implementação da lista
// duplamente encadeada (classe DLinkedList).
// -- A classe deve sobrescrever (override) o metodo public String toString()
// {...}, retornando uma string com os valores dos atributos da classe.

public class Node {
    private String ID;
    private String nomePessoa;
    private double notaPessoa;

    private Node proximo;
    private Node anterior;

    public Node() {
        this.ID = "";
        this.nomePessoa = "";
        this.notaPessoa = 99.9;

        this.proximo = null;
        this.anterior = null;
    }

    public Node (String id, String nome, double nota, Node prox, Node ant) {
        this.ID = id;
        this.nomePessoa = nome;
        this.notaPessoa = nota;
        this.proximo = prox;
        this.anterior = ant;
    }
    // Getters
    public String getId () {
        return this.ID;
    }

    public String getNome () {
        return this.nomePessoa;
    }

    public double getNota () {
        return this.notaPessoa;
    }

    public Node getProximo () {
        return this.proximo;
    }

    public Node getAnterior () {
        return this.anterior;
    }

    // Setters
    public void setId (String id) {
        this.ID = id;
    }

    public void setNome (String nome) {
        this.nomePessoa = nome;
    }

    public void setNota (double nota) {
        this.notaPessoa = nota;
    }

    public void setProximo (Node prox) {
        this.proximo = prox;
    }

    public void setAnterior (Node ant) {
        this.anterior = ant;
    }

    public String toString() {
        if (anterior == null && proximo == null) {
            return "null" + " <- (23.S1-" + this.ID + ";" + this.nomePessoa + ";" + notaPessoa + ") " +
                    "-> " + "23.S1-" + "null";
        } else if (anterior == null) {
            return "null" + " <- (23.S1-" + this.ID + ";" + this.nomePessoa + ";" + notaPessoa +
                    ") " +
                    "-> " + "23.S1-" + proximo.getId();
        } else if (proximo == null) {
            return "23.S1-" + anterior.getId() + " <- (23.S1-" + this.ID + ";" + this.nomePessoa + ";" + notaPessoa + ") " +
                    "-> " + "null";
        }
        return "23.S1-" + anterior.getId() + " <- (23.S1-" + this.ID + ";" + this.nomePessoa + ";" + notaPessoa + ") " +
                "-> " + "23.S1-" + proximo.getId();
    }

}
