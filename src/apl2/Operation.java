//*************************** ATENÇÃO! *****************************
// As assinaturas dos métodos desta classe não devem ser alteradas!
//*************************** ATENÇÃO! *****************************
// arquivo: src/apl2/Operation.java

/*
 Felipe Haddad - 10437372
 Beatriz Nobrega - 10435789
 Carolina Lee - 10440304
 Ana Julia Matilha - 10436655
 */

package apl2;

public class Operation {

	/**
	 * <p>Recebe como parâmetro uma lista encadeada do tipo {@code LinkedListOriginal}, sendo que os nós da lista estão
	 * populados com o conteúdo da base de dados original (conteúdo do arquivo dados.txt).</p>
	 * <p>A operação {@code map()} deve mapear os dados originais para uma lista encadeada do tipo {@code DLinkedList} e
	 * retornar a referência da {@code DLinkedList} que possui os dados mapeados para a nova estrutura usada pelo sistema de notas.</p>
	 *
	 * @param original Base de dados original carregada em uma {@code LinkedListOriginal}.
	 * @return Uma nova {@code DLinkedList} que contém o mapeamento da coleção de dados {@code original} para a nova estrutura usada pelo sistema de notas.
	 */
	public static DLinkedList map(final LinkedListOriginal original) {
		DLinkedList novaLista = new DLinkedList();
		NodeOriginal atualOriginal = original.getHead(); // Primeiro nó da lista original
		while (atualOriginal != null) {
			// Extrai os dados do nó original
			//String id = atualOriginal.getId();
			int idInt = atualOriginal.getId(); // pega o ID (int)

			//String sb = new String(data);
			String id = String.valueOf(idInt);
			id = ("23.S1-" + id);
			String nome = atualOriginal.getNome();
			int inteiro = atualOriginal.getInteiro();
			int decimo = atualOriginal.getDecimo();
			double nota;

			if (inteiro < 0 || decimo < 0) {
				nota = 99.9;
			} else {
				nota = Double.parseDouble(inteiro + "." + decimo);
			}

			// Cria um novo nó para a lista duplamente encadeada (NodeDuplo)
			Node novoNode = new Node();
			novoNode.setId(id);
			novoNode.setNome(nome);
			novoNode.setNota(nota);
			// ... Mapeie os outros campos conforme necessário para o seu sistema de notas

			// Adiciona o novo nó à lista duplamente encadeada
			novaLista.append(id, nome, nota);

			// Move para o próximo nó na lista original
			atualOriginal = atualOriginal.getNext();
		}
		return novaLista; // Retorna a nova lista construída
	}

	/**
	 * <p>Recebe como parâmetro uma lista duplamente encadeada do tipo {@code DLinkedList}, sendo que os nós da lista estão
	 * populados com o resultado da operação {@code map()}.</p>
	 * <p>A operação {@code filterRemoveNonGraded()} deve filtrar os nós que não possuem notas válidas (caso de "ausência de nota")
	 * e retornar uma nova lista do tipo {@code DLinkedList} contendo apenas os nós com notas válidas.</p>
	 *
	 * @param data Base de dados mapeada para o formato {@code DLinkedList} (via operação {@code map()}).
	 * @return Uma nova {@code DLinkedList} que contém a coleção de dados ({@code data}) filtrada com nós que possuem apenas pessoas com notas válidas.
	 */
	public static DLinkedList filterRemoveNonGraded(final DLinkedList data) {
		DLinkedList ListaFiltrada = new DLinkedList(); // Cria uma nova lista para armazenar os nós válidos
		Node atualNode = data.getHead(); // Pega o primeiro nó da lista original 'data'
		// Percorre a lista original enquanto houver nós
		while (atualNode != null) {
			// Verifica se a nota do nó é válida (ou seja, diferente de -1)
			if (atualNode.getNota() != 99.9) {
				// Se a nota for válida, cria um novo nó e adiciona à lista filtrada
				ListaFiltrada.append(atualNode.getId(), atualNode.getNome(), atualNode.getNota());
			}
			// Move para o próximo nó na lista original
			atualNode = atualNode.getProximo();
		}
		// Retorna a lista filtrada com os nós que possuem notas válidas
		return ListaFiltrada;
	}


	/**
	 * <p>Recebe como parâmetro uma lista duplamente encadeada do tipo {@code DLinkedList}, sendo que os nós da lista estão
	 * populados com o resultado da operação {@code map()}.</p>
	 * <p>A operação {@code filterRemoveGraded()} deve filtrar os nós que possuem notas válidas e retornar uma nova lista do
	 * tipo {@code DLinkedList} contendo apenas os nós com notas inválidas (caso de "ausência de nota").</p>
	 *
	 * @param data Base de dados mapeada para o formato {@code DLinkedList} (via operação {@code map()}).
	 * @return Uma nova {@code DLinkedList} que contém a coleção de dados ({@code data}) filtrada com nós que possuem apenas pessoas com notas inválidas.
	 */
	public static DLinkedList filterRemoveGraded(final DLinkedList data) {
		DLinkedList listaFiltrada = new DLinkedList();
		Node atualNode = data.getHead();
		while(atualNode != null){
			if(atualNode.getNota() == 99.9){
				listaFiltrada.append(atualNode.getId(), atualNode.getNome(), atualNode.getNota());
			}
			atualNode = atualNode.getProximo();
		}
		return listaFiltrada;
	}

	/**
	 * <p>Recebe como parâmetro uma lista duplamente encadeada do tipo {@code DLinkedList}, sendo que os nós da lista estão
	 * populados com o resultado da operação {@code filterRemoveNonGraded()}, e a média de notas válidas, calculadas com a
	 * operação {@code reduce()}.</p>
	 * <p>A operação {@code filterRemoveBelowAverage()} deve filtrar os nós que possuem notas abaixo da média e retornar uma
	 * nova lista do tipo {@code DLinkedList} contendo apenas os nós com notas acima da média.
	 *
	 * @param data Base de dados filtrada com a operação {@code filterRemoveNonGraded()}.
	 * @param average Média de notas válidas calculada com a operação {@code reduce()}.
	 * @return Uma nova {@code DLinkedList} que contém a coleção de dados ({@code data}) filtrada somente com pessoas com notas maiores do que {@code average}.
	 */
	public static DLinkedList filterRemoveBelowAverage(final DLinkedList data, float average) {
		DLinkedList listaacimaMedia = new DLinkedList();
		Node atualNode = data.getHead();

		while(atualNode!= null){
			if(atualNode.getNota() > average){
				listaacimaMedia.append(atualNode.getId(), atualNode.getNome(), atualNode.getNota());
			}
			atualNode = atualNode.getProximo();
		}
		return listaacimaMedia;

	}

	/**
	 * <p>Recebe como parâmetro uma lista duplamente encadeada do tipo {@code DLinkedList}, sendo que os nós da lista estão
	 * populados com o resultado da operação {@code filterRemoveNonGraded()}.</p>
	 * <p>A operação {@code reduce()} deve calcular a média das notas contidas na coleção de dados passada como parâmetro e
	 * retornar a média calculada.
	 *
	 * @param data Base de dados filtrada com a operação {@code filterRemoveNonGraded()}.
	 * @return Média das notas ({@code float}) contidas na coleção de dados ({@code data}).
	 */
	public static float reduce(final DLinkedList data) {
		float soma = 0;
		float quant = 0;
		Node atualNode = data.getHead();
		while(atualNode!= null ){
			soma += atualNode.getNota();
			quant++;
			atualNode = atualNode.getProximo();
		}
		float average = soma/quant;
		return average;

	}

	/**
	 * <p>Recebe como parâmetro uma lista duplamente encadeada do tipo {@code DLinkedList}, sendo que os nós da lista estão
	 * populados com o resultado da operação {@code map()}.</p>
	 * <p>A operação {@code mapToString()} deve mapear todos os nós da coleção de dados passada como parâmetro para uma única
	 * {@code String}, sendo que cada dado de uma pessoa é separado por ponto-e-vírgula (;) e cada pessoa é separada por uma
	 * quebra de linha.</p>
	 *
	 * @param data Base de dados mapeada para o formato {@code DLinkedList} (via operação {@code map()}).
	 * @return {@code String} com a coleção de dados separada por ponto-e-vírgula (dados de cada pessoa) e quebras de linha (cada pessoa).
	 */
	public static String mapToString(final DLinkedList data) {
		StringBuilder stringb = new StringBuilder();
		Node atualNode = data.getHead();

		while(atualNode != null){
			stringb.append(atualNode.getId());
			stringb.append(";");
			stringb.append(atualNode.getNome());
			stringb.append(";");
			stringb.append(atualNode.getNota());
			stringb.append("\n");
			atualNode = atualNode.getProximo();
		}
		return stringb.toString();

	}

}
