// arquivo: src/apl2/DLinkedList.java

/*
 Felipe Haddad - 10437372
 Beatriz Nobrega - 10435789
 Carolina Lee - 10440304
 Ana Julia Matilha - 10436655
 */


package apl2;

// -- A classe DLinkedList (que pertence ao pacote apl2) deve implementar uma
// lista duplamente encadeada. Os nós dessa lista são do tipo [da classe] Node.
// -- A classe deve possuir dois nós especiais, head e tail, que são
// referências para o primeiro e último nó da lista, respectivamente.
// -- A classe deve possuir um contador de quantos nós existem na lista.
// -- A classe deve sobrescrever (override) o método public String toString()
// {...}, retornando uma string com o conteúdo da lista.
// -- A classe deve implementar as operações a seguir, respeitando o
// comportamento descrito em cada operação.

public class DLinkedList {
	
	private Node head;
	private Node tail;
	private int count;

// OPERAÇÃO:		Metodo construtor
// COMPORTAMENTO:	Cria uma lista vazia.
	public DLinkedList () {
		this.head = null;
		this.tail = null;
		count = 0;
	}

// OPERAÇÃO: 		Metodo construtor
// COMPORTAMENTO: 	Cria uma lista com parâmetros
	public DLinkedList(Node head, Node tail, int count) {
		this.head = head;
		this.tail = tail;
		count = 0;
	}


// OPERAÇÃO:		insert(<dados da pessoa>)
// COMPORTAMENTO:	Aloca um Node que contém os <dados da pessoa> e insere o
//					novo nó no início da lista.
	public void insert(int id, String nome, double nota) {
		Node node = new Node(id, nome, nota, this.head, null);
		Node aux = node.getProximo();
		if (isEmpty()) {
			tail = node;
		} else {
			aux.setAnterior(node);
		}
		this.head = node;
		count++;
	}

// OPERAÇÃO:		append(<dados da pessoa>)
// COMPORTAMENTO:	Aloca um Node que contém os <dados da pessoa> e insere o
//					novo nó no final da lista.
	public void append(int id, String nome, double nota) {
		Node node = new Node (id, nome, nota, null, null);

		if (isEmpty()) {
			this.head = node;
		} else {
			node.setAnterior(tail);
			tail.setProximo(node);
		}
		this.tail = node;
		count++;
	}


// OPERAÇÃO: 		removeHead()
// COMPORTAMENTO:	Remove o nó do início da lista e retorna a referência do
//					nó removido.
//					Ou retorna null caso a lista esteja vazia.
	public Node removeHead() {
		if(this.head == null)
		{
			return null;
		}
		else if(this.head == this.tail){
			Node removedNode = this.head;
			this.head = null;
			this.tail = null;
			count--;
			return removedNode;
		}
		Node removedNode = this.head;
		this.head = head.getProximo();
		this.head.setAnterior(null);
		count--;
		return removedNode;
	}


// OPERAÇÃO:		removeTail()
// COMPORTAMENTO:	Remove o nó do final da lista e retorna a referência do
//					nó removido.
//					Ou retorna null caso a lista esteja vazia.
	public Node removeTail() {
		if(this.head == null){
			return null;
		}
		else if (this.head == this.tail){
			Node removedTail = this.tail;
			this.head = null;
			this.tail = null;
			count--;
			return removedTail;
		}

		Node removedTail = this.tail;
		this.tail = tail.getAnterior();
		this.tail.setProximo(null);
		count--;


		return removedTail;
	}


// OPERAÇÃO:		removeNode(<ID da pessoa>)
// COMPORTAMENTO:	Remove o nó que contém o <ID da pessoa> da lista e retorna
//					a referência do nó removido.
//					Ou retorna null caso não exista um nó com <ID da pessoa>.
	public Node removeNode(String idString) {
		int id = Integer.parseInt(idString);
		Node toRemove = head;
		Node previous = null;

		if (isEmpty()) {
			return null;
		}
		while (toRemove != null && toRemove.getId() != id) {
			previous = toRemove;
			toRemove = toRemove.getProximo();
		}

		if (toRemove == null) {
			return null;
		}

		if (toRemove == head) {
			return removeHead();
		}

		if (toRemove == tail) {
			return removeTail();
		}

		previous.setProximo(toRemove.getProximo());
		toRemove.getProximo().setAnterior(previous);
		count--;

		toRemove.setProximo(null);
		return toRemove;
	}


// OPERAÇÃO:		getHead()
// COMPORTAMENTO:	Retorna uma referência para o nó do início da lista.
//					Ou retorna null caso a lista esteja vazia.
	public Node getHead() {
		return head;
	}


// OPERAÇÃO:		getTail()
// COMPORTAMENTO:	Retorna uma referência para o nó do final da lista.
//					Ou retorna null caso a lista esteja vazia.
	public Node getTail() {
		return tail;
	}


// OPERAÇÃO:		getNode(<ID da pessoa>)
// COMPORTAMENTO:	Retorna uma referência para o nó que contém o <ID da pessoa>
//					da lista.
//					Ou retorna null caso não exista um nó com <ID da pessoa>.
	public Node getNode(String id) {
		int idProcurado = Integer.parseInt(id);
		Node aux = head;

		while (aux != null) {
			if (aux.getId() == idProcurado) {
				return aux;
			}
			aux = aux.getProximo();
		}
		return null;
	}

// OPERAÇÃO:		count()
// COMPORTAMENTO:	Retorna a quantidade de nós da lista.
	public int count() {
		return count;
	}

// OPERAÇÃO:		isEmpty()
// COMPORTAMENTO:	Retorna true se a lista estiver vazia ou false, caso contrário.
	public boolean isEmpty() {return head == null;}


// OPERAÇÃO:		clear()
// COMPORTAMENTO:	Esvazia a lista, liberando a memória de todos os nós da lista.
	public void clear() {
		while (!isEmpty()) {
			removeHead();
		}
	}

// OPERAÇÃO:		toString()
// COMPORTAMENTO:	Retorna uma string com o conteúdo da lista (caso queira, use o
//					exemplo do metodo toString() da classe LinkedListOriginal).
	@Override
	public String toString() {
		StringBuilder stringb = new StringBuilder();
		stringb.append("(").append(count).append(") \n");

		if (head == null) {
			stringb.append("null");
		} else {
			Node atualNode = head;
			while (atualNode != null) {
				Node ant = atualNode.getAnterior();
				Node prox = atualNode.getProximo();
				String idAnt = (ant == null) ? "null" : "23.S1-" + ant.getId();
				String idProx = (prox == null) ? "null" : "23.S1-" + prox.getId();
				stringb.append(idAnt).append(" <- (23.S1-")
					.append(atualNode.getId()).append(";")
					.append(atualNode.getNome()).append(";")
					.append(atualNode.getNota()).append(") -> ")
					.append(idProx).append("\n");
				atualNode = atualNode.getProximo();
			}
		}
		return stringb.toString();
	}

}
