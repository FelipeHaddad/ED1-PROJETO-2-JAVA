//*************************** ATENÇÃO! ****************************
// O metodo main() deve ser alterado somente nos comentários .
// Todas as outras instruções devem permanecer intactas e o código
// deve funcionar conforme descrito no enunciado da atividade.
//*************************** ATENÇÃO! ****************************
// arquivo: src/MainApl2.java

/*
 Felipe Haddad - 10437372
 Beatriz Nobrega - 10435789
 Carolina Lee - 10440304
 Ana Julia Matilha - 10436655
 */
// Slides do prof.Dr.Ivan

import apl2.DLinkedList;
import apl2.LinkedListOriginal;
import apl2.Node;
import apl2.Operation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MainApl2 {

	public static void main(String[] args) throws FileNotFoundException {
		LinkedListOriginal list = new LinkedListOriginal();
		DLinkedList novaLista = new DLinkedList();
		boolean loop = true;
		int opcao = 0;

		// Leitura do arquivo dados.txt
		File arquivo = new File("dados.txt");
		Scanner scanner = new Scanner(arquivo);

		// Começa a ler o arquivo enquanto não chegar no fimw
		while (scanner.hasNextLine()) {
			String linha = scanner.nextLine();
			//System.out.println(linha);

			// Divide os dados da linha pelo delimitador "#"
			String[] partes = linha.split("#");

			// Parse manual dos campos
			int id = Integer.parseInt(partes[0]);
			String nome = partes[1];
			int parteInteira = Integer.parseInt(partes[2]);
			int parteDecimal = Integer.parseInt(partes[3]);
			double nota;

			if (parteInteira < 0 || parteDecimal < 0) {
				nota = 99.9;
			} else {
				nota = Double.parseDouble(parteInteira + "." + parteDecimal);
			}
			list.append(id, nome, parteInteira, parteDecimal);
			novaLista.append(String.valueOf(id), nome, nota);
		}

		System.out.println(">>>>>>>>>> Dados originais (sistema legado) >>>>>>>>>>");
		System.out.println(list);
		System.out.println("<<<<<<<<<< Dados originais (sistema legado) <<<<<<<<<<\n");

		DLinkedList fixedList = Operation.map(list);
		System.out.println(">>>>>>>>>> Dados convertidos para a nova representação dos dados >>>>>>>>>>");
		System.out.println(fixedList);
		System.out.println("<<<<<<<<<< Dados convertidos para a nova representação dos dados <<<<<<<<<<\n");

		DLinkedList filteredGradedList = Operation.filterRemoveNonGraded(fixedList);
		System.out.println(">>>>>>>>>> Lista filtrada (somente notas válidas) >>>>>>>>>>");
		System.out.println(filteredGradedList);
		System.out.println("<<<<<<<<<< Lista filtrada (somente notas válidas) <<<<<<<<<<\n");

		DLinkedList filteredNonGradedList = Operation.filterRemoveGraded(fixedList);
		System.out.println(">>>>>>>>>> Lista filtrada (somente 'ausência de nota') >>>>>>>>>>");
		System.out.println(filteredNonGradedList);
		System.out.println("<<<<<<<<<< Lista filtrada (somente 'ausência de nota') <<<<<<<<<<\n");

		float average = Operation.reduce(filteredGradedList);
		System.out.println(">>>>>>>>>> Média das notas válidas >>>>>>>>>>");
		System.out.println(average);
		System.out.println("<<<<<<<<<< Média das notas válidas <<<<<<<<<<\n");

		DLinkedList aboveAverageList = Operation.filterRemoveBelowAverage(filteredGradedList, average);
		System.out.println(">>>>>>>>>> Lista com notas acima da média >>>>>>>>>>");
		System.out.println(aboveAverageList);
		System.out.println("<<<<<<<<<< Lista com notas acima da média <<<<<<<<<<\n");

		String contents = Operation.mapToString(fixedList);
		System.out.println(">>>>>>>>>> Lista mapeada para uma única string >>>>>>>>>>");
		System.out.println(contents);
		System.out.println("<<<<<<<<<< Lista mapeada para uma única string <<<<<<<<<<\n");


		Node aux = novaLista.getHead(); // ou o jeito que pega o primeiro nó
		String conteudo = "";
		while (aux != null) {
			// Aqui pegue o ID completo — tipo String "23.S1-111"
			String idCompleto = (aux.getId()); // tem que ser uma String com o valor
			// completo!
			String nome = aux.getNome();
			double nota = aux.getNota();

			conteudo = conteudo + idCompleto
					 + (";")
					 + (nome)
					 + (";")
					 + (nota)
					 + ("\n");
			aux = aux.getProximo();
		}

		// Depois salva no arquivo
		try (FileWriter fw = new FileWriter("dados.csv")) {
			fw.write(conteudo);
		} catch (IOException e) {
			e.printStackTrace();
		}


		Node test1 = fixedList.getNode("23.S1-999");
		System.out.println(">>>>>>>>>> test1 >>>>>>>>>>\n" + test1 + "\n<<<<<<<<<< test1 <<<<<<<<<<\n");

		Node test2 = fixedList.removeNode("23.S1-999");
		System.out.println(">>>>>>>>>> test2 >>>>>>>>>>\n" + test2 + "\n<<<<<<<<<< test2 <<<<<<<<<<\n");

		Node test3 = fixedList.getNode("23.S1-999");
		System.out.println(">>>>>>>>>> test3 >>>>>>>>>>\n" + test3 + "\n<<<<<<<<<< test3 <<<<<<<<<<\n");

		aboveAverageList.clear();
		System.out.println(">>>>>>>>>> aboveAverageList.clear() >>>>>>>>>>\n" + aboveAverageList + "\n<<<<<<<<<< aboveAverageList.clear() <<<<<<<<<<\n");

		DLinkedList testList = new DLinkedList();
		testList.insert("ABC", "John Doe", 4.7f);
		testList.append("XYZ", "Jane Doe", 9.9f);
		testList.insert("321", "Test", 2.3f);
		testList.append("Nothing", "Yada yada yada", 99.9f);
		System.out.println(">>>>>>>>>> testList >>>>>>>>>>\n" + testList  + "\n<<<<<<<<<< testList <<<<<<<<<<\n");

		System.out.println("testList.getHead(): " + testList.getHead());
		System.out.println("testList.getTail(): " + testList.getTail());
		System.out.println("testList.removeHead(): " + testList.removeHead());
		System.out.println("testList.removeTail(): " + testList.removeTail() + '\n');

		System.out.println(">>>>>>>>>> testList >>>>>>>>>>\n" + testList  + "\n<<<<<<<<<< testList <<<<<<<<<<\n");
		System.out.println("testList.getHead(): " + testList.getHead());
		System.out.println("testList.getTail(): " + testList.getTail());
		System.out.println("testList.removeNode(\"ABC\"): " + testList.removeNode("ABC") + '\n');
		System.out.println(">>>>>>>>>> testList >>>>>>>>>>\n" + testList  + "\n<<<<<<<<<< testList <<<<<<<<<<\n");
		System.out.println("testList.getHead(): " + testList.getHead());
		System.out.println("testList.getTail(): " + testList.getTail() + '\n');
		testList.insert("qwerty", "QWERTY", 1.2f);
		testList.append("WASD", "wasd", 3.4f);
		testList.insert("ijkl", "IJKL", 5.6f);
		testList.append("1234", "Um Dois Tres Quatro", 7.8f);
		System.out.println(">>>>>>>>>> testList >>>>>>>>>>\n" + testList  + "\n<<<<<<<<<< testList <<<<<<<<<<\n");
		testList.clear();
		System.out.println(">>>>>>>>>> testList.clear() >>>>>>>>>>\n" + testList  + "\n<<<<<<<<<< testList.clear() <<<<<<<<<<\n");

		Scanner scanner2 = new Scanner(System.in);
		while (loop) {
			System.out.println("\nSistema Conversor de Notas\n" +
					"1) Dados originais: lê arquivo dados.txt e apresenta todos os dados do Sistema de Notas Legado;\n" +
					"2) Dados convertidos: gera arquivo dados.csv e apresenta todos os dados do Sistema de Notas Atualizado;\n" +
					"3) Lista notas filtradas válidas: apresenta os dados somente das notas válidas filtradas;\n" +
					"4) Lista notas filtradas inválidas: apresenta os dados somente das notas filtradas pela ausência de notas”;\n" +
					"5) Média de notas válidas: apresenta a média das notas válidas filtradas;\n" +
					"6) Notas acima da média: apresenta os dados para as notas acima da média;\n" +
					"7) Lista mapeada para uma única string: apresenta a String contendo os dados mapeados;\n" +
					"8) Finaliza sistema.\n" +
					"Insira sua opção: ");
					opcao = scanner2.nextInt();
					DLinkedList listaFiltradaNotasValidas = Operation.filterRemoveNonGraded(fixedList);
					average = Operation.reduce(listaFiltradaNotasValidas);

			if (opcao == 1) {
						System.out.println("Dados Originais : \n");
						System.out.println(list);
					}
					else if (opcao == 2) {
						arquivo = new File("dados.txt");
						Scanner scanner3 = new Scanner(arquivo);

						// Começa a ler o arquivo enquanto não chegar no fimw
						while (scanner3.hasNextLine()) {
							String linha = scanner3.nextLine();
							//System.out.println(linha);

							// Divide os dados da linha pelo delimitador "#"
							String[] partes = linha.split("#");

							// Parse manual dos campos
							int id = Integer.parseInt(partes[0]);
							String nome = partes[1];
							int parteInteira = Integer.parseInt(partes[2]);
							int parteDecimal = Integer.parseInt(partes[3]);
							double nota;

							if (parteInteira < 0 || parteDecimal < 0) {
								nota = 99.9;
							} else {
								nota = Double.parseDouble(parteInteira + "." + parteDecimal);
							}
							list.append(id, nome, parteInteira, parteDecimal);
							novaLista.append(String.valueOf(id), nome, nota);
						}
						System.out.println("Dados Convertidos: \n" + fixedList);
					}
					else if (opcao == 3) {
						System.out.println("Lista notas filtradas válidas: \n" + listaFiltradaNotasValidas);
					}
					else if (opcao == 4) {
						DLinkedList listaFiltradaNotasInvalidas = Operation.filterRemoveGraded(fixedList);
						System.out.println("Lista notas filtradas inválidas: \n" + listaFiltradaNotasInvalidas);
					}
					else if (opcao == 5) {
						System.out.println("Media das notas validas: \n" + average);
					}
					else if (opcao == 6) {
						DLinkedList listaNotasAcimaMedia = Operation.filterRemoveBelowAverage(filteredGradedList, average);
						System.out.println("Lista notas acima da média: \n" + listaNotasAcimaMedia);
					}
					else if (opcao == 7) {
						String listaUnicaString = Operation.mapToString(fixedList);
						System.out.println("Lista mapeada para uma unica string: \n" + listaUnicaString);
					}
					else if (opcao == 8) {
						System.out.println("\nSistema Finalizado");
						loop = false;
					}
					else {
						System.out.println("\nDigite uma opcao valida!\n");
			}
		}
		scanner.close();
	}

}
