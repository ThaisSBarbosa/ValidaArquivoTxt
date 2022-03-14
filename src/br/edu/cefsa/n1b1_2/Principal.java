package br.edu.cefsa.n1b1_2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Principal {

	public static void main(String[] args) throws Exception {
		List<String> linhas = leArquivoTexto();
		List<String> linhasValidadas = analisaLinhas(linhas);
		escreveArquivoSaida(linhasValidadas);
	}

	public static List<String> leArquivoTexto() throws Exception {
		Scanner scanner = new Scanner(new File("prog.txt"));
		List<String> linhas = new ArrayList<String>();

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			linhas.add(line);
		}
		return linhas;
	}

	public static List<String> analisaLinhas(List<String> linhas) throws Exception {

		for (String linha : linhas) {
			Stack<String> pilha = new Stack<String>();

			for (int i = 0; i < linha.length(); i++) {
				var letra = String.valueOf(linha.charAt(i));

				if (!pilha.empty()) {
					if (pilha.peek() == "<" && letra == ">") {
						pilha.pop();
					}
					if (pilha.peek() == "[" && letra == "]") {
						pilha.pop();
					}
					if (pilha.peek() == "{" && letra == "}") {
						pilha.pop();
					}
					if (pilha.peek() == "(" && letra == ")") {
						pilha.pop();
					}
				} else {
					pilha.push(letra);
				}

			}

			if (pilha.empty()) {
				linha = linha + " - OK";
			} else {
				linha = linha + " - Inválido";
			}

			System.out.println(linha);
		}

		return linhas;
	}

	public static void escreveArquivoSaida(List<String> linhas) throws Exception {

		FileOutputStream arquivo;
		arquivo = new FileOutputStream("prog-check.txt");
		ObjectOutputStream gravador = new ObjectOutputStream(arquivo);
		gravador.writeObject(linhas);

		gravador.close();
		arquivo.close();
	}
}
