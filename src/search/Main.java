package search;

import java.util.Scanner;

public class Main {

	private static Scanner in;

	public static void main(String[] args) {
		Graph graph = new Graph();
		in = new Scanner(System.in);
		String path;
		
		graph.criaGrafo();
		
		System.out.println("Digite o grafo a ser procurado:");
		String input = in.nextLine().toUpperCase();
		
		path = graph.buscaProfundidade(input);
		System.out.println("Caminho percorrido - profundidade: " + path);
		
		graph.criaGrafo();
		
		path = graph.buscaLargura(input);
		System.out.println("Caminho percorrido - largura: " + path);
		
		graph.criaGrafo();
		
		path = graph.buscaHillClimbing(input);
		System.out.println("Caminho percorrido - hill climbing: " + path);
		
		graph.criaGrafo();
		
		path = graph.buscaBestFirst(input);
		System.out.println("Caminho percorrido - best first: " + path);
		
		graph.criaGrafo();
		
		path = graph.buscaCustoUniforme(input);
		System.out.println("Caminho percorrido - custo uniforme: " + path);
	}
}

