package pacote_one;

import java.util.Scanner;

public class caixaeletronico {

	// Cédulas no caixa
	private static int[] notas = { 100, 50, 50, 20, 20, 20, 20, 20, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 };

	// Saldo do cliente
	private static int currentBalance = 300;

	/*
	 * Método principal: main() Propósito: startar aplicação :)
	 */
	public static void main(String[] args) {

		boolean hasMoney = false;

		int saqueValue = 1;

		WriteInTheScreen("Inicio: " + saqueValue);

		while (saqueValue != 0) {

			saqueValue = RequestMoney();

			hasMoney = QueryBalance(saqueValue);

			// Trecho que usei para testar a função removeNotes
			WriteInTheScreen("A maior é: " + String.valueOf(findBiggerNote(0)));

			WriteInTheScreen("A menor é: " + String.valueOf(findSmallerNote(0)));

			if (hasMoney == false) {

				WriteInTheScreen("Saldo insuficiente!");

			} else {

				WriteInTheScreen(
						"Dinheiro liberado, pegue e vá embora: " + valueValidation(String.valueOf(saqueValue)));

				WriteInTheScreen("Saldo atualizado:" + String.valueOf(currentBalance));

			}

		}

		// Limpando o console do Eclipse na gambeta
		for (int i = 0; i <= 100; i++) {
			System.out.println();
		}

		WriteInTheScreen("Operação cancelada - precisando estamos sempre as ordens!");

	}

	private static String valueValidation(String valueOf) {

		String valueVerified, saque = "";
		int saqueValue = 0;
		int valueComparated = 0;

		for (int i = 0; i < notas.length; i++) {

			valueComparated = saqueValue + notas[i];

			// Se exisitr uma nota cujo valor é igual ao solicitado tudo já
			// termina nesse if
			if (Integer.valueOf(valueOf) == notas[i]) {

				valueVerified = String.valueOf(notas[i]);
				saque = valueVerified;
				removeNotes(notas[i]);
				break;

				// Totaliza e separa notas a serem entregues - priorizando
				// entrega das maiores paras a menores gradativamente
			} else if (Integer.valueOf(valueOf) > notas[i] && valueComparated <= Integer.valueOf(valueOf)) {

				valueVerified = String.valueOf(notas[i]);

				if (saque.indexOf(valueVerified) == -1 || saqueValue < Integer.valueOf(valueOf)) {

					saque = saque + String.valueOf(notas[i]) + "; ";
					saqueValue = saqueValue + notas[i];
					removeNotes(notas[i]);

				}

				if (String.valueOf(saqueValue).equals(valueOf))
					break;

			}

		}

		currentBalance = currentBalance - saqueValue;

		return saque;
	}

	/* Lista notas disponíveis no caixa eletrônico */
	private static void listNotes() {

		WriteInTheScreen("Notas: ");

		for (int i = 0; i < notas.length; i++) {

			WriteInTheScreen(String.valueOf(notas[i]));

		}

	}

	/* Remove notas do caixa eletrônico */
	private static void removeNotes(int valueNote) {

		for (int i = 0; i < notas.length; i++) {

			if (notas[i] == valueNote) {
				notas[i] = 0;
				break;
			}

		}
	}

	/* Liberar montante em dinheiro solicitado */
	private static int liberateRequestMoney(int valueRequestMoney) {

		int totalNotes = 0;
		int valueNote = 0;

		// FInd money solicity
		for (int i = 0; i < notas.length; i++) {

			if (notas[i] == valueNote)
				valueNote = notas[i];

			if (valueNote == valueRequestMoney) {
				break;
			} else {
				valueNote = valueNote + valueRequestMoney;
			}

		}

		return valueRequestMoney;

	}

	public static String notesSeparete(int desiredValue) {

		int valueSepareted = 0;

		while (valueSepareted < desiredValue) {

			valueSepareted = valueSepareted + findSmallerNote(desiredValue);

		}

		return String.valueOf(valueSepareted);

	}

	/* Encontra a maior nota disponível */
	private static int findSmallerNote(int valueRef) {

		int smallerNoteOne = valueRef;
		int smallerNoteTwo, smaller = 0;

		// Find money solicity
		for (int i = 0; i < notas.length; i++) {

			if (valueRef == 0)
				smallerNoteOne = notas[i];

			for (int y = 0; y < notas.length; y++) {

				smallerNoteTwo = notas[y];

				if (smallerNoteOne != smallerNoteTwo) {

					smaller = Math.min(smallerNoteOne, smallerNoteTwo);
					break;

				}
			}
		}

		return smaller;

	}

	/* Encontra a maior nota disponível */
	private static int findBiggerNote(int valueRef) {

		int biggerNoteOne = valueRef;
		int biggerNoteTwo, big = 0;

		// Find money solicity
		for (int i = 0; i < notas.length; i++) {

			if (valueRef == 0)
				biggerNoteOne = notas[i];

			for (int y = 0; y < notas.length; y++) {

				biggerNoteTwo = notas[y];

				if (biggerNoteOne != biggerNoteTwo) {

					big = Math.max(biggerNoteOne, biggerNoteTwo);
					break;

				}
			}
		}

		return big;
	}

	/* Solicita escolha do valor saque ou desistir da operação */
	private static int RequestMoney() {

		String op;

		Scanner readData = new Scanner(System.in);
		WriteInTheScreen("Informe o valor da retirada ou 'S' para sair");

		op = readData.next(); // .nextInt();

		if (op.toUpperCase().equals("S"))
			return 0;
		return Integer.valueOf(op);

	}

	/* Imprime uma informação na tela */
	public static void WriteInTheScreen(String result) {

		System.out.println(result);

	}

	/* Informe se o cliente possui saldo na conta */
	private static boolean QueryBalance(int saqueValue) {

		if (saqueValue <= currentBalance)
			return true;
		return false;

	}

}
