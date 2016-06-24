package search;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Graph {
	private Node nodeA, nodeB, nodeC, nodeD, nodeE, nodeF, 
	             nodeG, nodeH, nodeI, nodeK, nodeL, nodeM, 
	             nodeN, nodeP, nodeQ, nodeR, nodeS, nodeT, 
	             nodeU, nodeV, nodeW;

	public void criaGrafo(){
		nodeA = new Node("A", 2, 0, 15);
		nodeB = new Node("B", 4, 2, 13);
		nodeC = new Node("C", 3, 2, 9);
		nodeD = new Node("D", 2, 0, 7);
		nodeE = new Node("E", 3, 2, 6);
		nodeF = new Node("F", 3, 10, 0);
		nodeG = new Node("G", 3, 2, 3);
		nodeH = new Node("H", 2, 0, 1);
		nodeI = new Node("I", 4, 2, 17);
		nodeK = new Node("K", 3, 2, 0);
		nodeL = new Node("L", 2, 7, 17);
		nodeM = new Node("M", 2, 18, 0);
		nodeN = new Node("N", 2, 11, 11);
		nodeP = new Node("P", 3, 11, 8);
		nodeQ = new Node("Q", 2, 9, 8);
		nodeR = new Node("R", 2, 9, 10);
		nodeS = new Node("S", 2, 7, 10);
		nodeT = new Node("T", 4, 7, 6);
		nodeU = new Node("U", 2, 14, 8);
		nodeV = new Node("V", 2, 14, 6);
		nodeW = new Node("W", 2, 7, 3);
		
		// NodeI
		nodeI.setSon(0, nodeA);
		nodeI.setSon(1, nodeB);
		nodeI.setSon(2, nodeN);
		nodeI.setSon(3, nodeL);
		
		// NodeA
		nodeA.setSon(0, nodeI);
		nodeA.setSon(1, nodeB);
		
		// NodeB
		nodeB.setSon(0, nodeI);
		nodeB.setSon(1, nodeA);
		nodeB.setSon(2, nodeC);
		nodeB.setSon(3, nodeT);
		
		// NodeN
		nodeN.setSon(0, nodeI);
		nodeN.setSon(1, nodeP);
		
		// NodeL
		nodeL.setSon(0, nodeI);
		nodeL.setSon(1, nodeM);
		
		// NodeC
		nodeC.setSon(0, nodeB);
		nodeC.setSon(1, nodeD);
		nodeC.setSon(2, nodeE);
		
		// NodeT
		nodeT.setSon(0, nodeB);
		nodeT.setSon(1, nodeS);
		nodeT.setSon(2, nodeV);
		nodeT.setSon(3, nodeW);
		
		// NodeD
		nodeD.setSon(0, nodeC);
		nodeD.setSon(1, nodeE);
		
		// NodeE
		nodeE.setSon(0, nodeD);
		nodeE.setSon(1, nodeC);
		nodeE.setSon(2, nodeG);
		
		// NodeG
		nodeG.setSon(0, nodeE);
		nodeG.setSon(1, nodeH);
		nodeG.setSon(2, nodeK);
		
		// NodeK
		nodeK.setSon(0, nodeG);
		nodeK.setSon(1, nodeH);
		nodeK.setSon(2, nodeF);
		
		// NodeH
		nodeH.setSon(0, nodeG);
		nodeH.setSon(1, nodeK);
		
		// NodeF
		nodeF.setSon(0, nodeK);
		nodeF.setSon(1, nodeW);
		nodeF.setSon(2, nodeM);
		
		// NodeP
		nodeP.setSon(0, nodeN);
		nodeP.setSon(1, nodeQ);
		nodeP.setSon(2, nodeU);
		
		// NodeM
		nodeM.setSon(0, nodeL);
		nodeM.setSon(1, nodeF);
		
		// NodeQ
		nodeQ.setSon(0, nodeP);
		nodeQ.setSon(1, nodeR);
		
		// NodeR
		nodeR.setSon(0, nodeQ);
		nodeR.setSon(1, nodeS);
		
		// NodeS
		nodeS.setSon(0, nodeR);
		nodeS.setSon(1, nodeT);
		
		// NodeU
		nodeU.setSon(0, nodeP);
		nodeU.setSon(1, nodeV);
		
		// NodeV
		nodeV.setSon(0, nodeU);
		nodeV.setSon(1, nodeT);
		
		// NodeW
		nodeW.setSon(0, nodeT);
		nodeW.setSon(1, nodeF);
	}
	
	public void resetNodes(){
		Queue<Node> queue = new LinkedList<Node>();
		Node node;
		
		nodeI.setVisited(false);
		queue.offer(nodeI);
		
		while(!queue.isEmpty()){
			node = queue.poll();
			for (Node aux : node.getSons()) {
				if (!node.getNodeName().equals(aux.getNodeName())){
					aux.setVisited(false);
					queue.offer(node);
				}
			}
		}
	}
	
	public String buscaProfundidade(String graphName){
		Stack<Node> stack = new Stack<Node>();
		Node node, nodeAdj;
		boolean found = false;
		
		String path = nodeI.getNodeName() + " > ";
		nodeI.setVisited(true);
		
		stack.push(nodeI);
		
		while(!stack.isEmpty()){
			if (!found){
				node = (Node)stack.peek();
				
				nodeAdj = node.getUnvisitedNode(node); 
				if(nodeAdj != null){
					found = nodeAdj.getNodeName().equals(graphName);
					path += nodeAdj.getNodeName() + " > ";
					stack.push(nodeAdj);
				}
				else
					stack.pop();
			}
			else
				stack.pop();
		}
		
		return path;
	}
	
	public String buscaLargura(String graphName) {
		Queue<Node> queue = new LinkedList<Node>();
		Node node, nodeAdj;
		boolean found = false;
		String path = "";
	
		nodeI.setVisited(true);
		
		queue.offer(nodeI);
		
		while (!queue.isEmpty()) {
			if(!found){
				node = (Node)queue.poll();
				
				if (path.isEmpty())
					path = node.getNodeName() + " > ";
				else
					path += node.getNodeName() + " > ";
				
				found = node.getNodeName().equals(graphName);
				
				if(!found){
					for (int i = 0; i < node.getSons().length; i++) {
						nodeAdj = node.getUnvisitedNode(node);
						if (nodeAdj != null)
							queue.offer(nodeAdj);
					}
				}
			}
			else
				queue.poll();
		}
		
		return path;
	}
	
	public String buscaHillClimbing(String graphName){
		Stack<Node> stack = new Stack<Node>();
		Node node, nodeAdj;
		boolean found = false;
		
		String path = nodeI.getNodeName() + " > ";
		nodeI.setVisited(true);
		
		stack.push(nodeI);
		
		while(!stack.isEmpty()){
			if (!found){
				node = (Node)stack.peek();
				
				nodeAdj = node.getHillClimbingNode(node);
				if(nodeAdj != null){
					found = nodeAdj.getNodeName().equals(graphName);
					path += nodeAdj.getNodeName() + " > ";
					stack.push(nodeAdj);
				}
				else
					stack.pop();
			}
			else
				stack.pop();
		}
		
		return path;
	}
	
	public String buscaBestFirst(String graphName){
		Queue<Node> queue = new LinkedList<Node>();
		Node node, aux, target = null;
		boolean found = false;
		String path = "";
		
		Class<?> c = this.getClass();
		Field[] fields = c.getDeclaredFields();
		
		for (Field field : fields) {
			if (field.getName().equals("node" + graphName)){
				try {
					target = (Node)field.get(this);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		nodeI.setVisited(true);
		
		queue.offer(nodeI);
		
		while (!queue.isEmpty()) {
			if(!found){
				node = (Node)queue.poll();
				
				if (path.isEmpty())
					path = node.getNodeName() + " > ";
				else
					path += node.getNodeName() + " > ";
				
				found = node.getNodeName().equals(graphName);
				
				if(!found){
					aux = node.getBestFirstNode(target);
					aux.setVisited(true);
					queue.offer(aux);
				}
			}
			else
				queue.poll();
		}
		
		return path;
	}
	
	public String buscaCustoUniforme(String graphName){
		List<Node> list = new ArrayList<Node>();
		List<UniformCost> listCost = new ArrayList<UniformCost>();
		boolean found = false;
		String path = "";
		
		UniformCost cost = new UniformCost();
		cost.setNode(nodeI);
		cost.setNextNodeDistance(0.0);
		listCost.add(cost);
		
		while (!listCost.isEmpty()) {
			if(!found){
				while(listCost.get(0).getNode().getVisited())
					listCost.remove(0);
				
				UniformCost uc = listCost.remove(0);
				uc.getNode().setVisited(true);
				
				if (path.isEmpty())
					path = uc.getNode().getNodeName() + " > ";
				else
					path += uc.getNode().getNodeName() + " > ";
				
				found = uc.getNode().getNodeName().equals(graphName);
				
				if(!found){
					List<UniformCost> aux = new ArrayList<UniformCost>();
					aux = uc.getSonsList(uc.getNode());
					
					for (UniformCost uniformCost : aux) {
						uniformCost.setNextNodeDistance(uniformCost.getNextNodeDistance() + uc.getNextNodeDistance());
					}
					
					listCost.addAll(aux);
					Collections.sort(listCost);
				}
			}
			else if (!listCost.isEmpty())
				listCost.remove(0);
		}
		
		return path;
	}	
}
