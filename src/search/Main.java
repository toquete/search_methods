package search;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Graph graph = new Graph();
		Scanner in = new Scanner(System.in);
		
		graph.criaGrafo();
		
		System.out.println("Digite o grafo a ser procurado:");
		String input = in.nextLine();
		
		String pathDeep = graph.buscaProfundidade(input);
		System.out.println("Caminho percorrido - profundidade: " + pathDeep);
		
		graph.criaGrafo();
		
		String pathBreadth = graph.buscaLargura(input);
		System.out.println("Caminho percorrido - largura: " + pathBreadth);
		
		graph.criaGrafo();
		
		String pathHill = graph.buscaHillClimbing(input);
		System.out.println("Caminho percorrido - hill climbing: " + pathHill);
	}
}

