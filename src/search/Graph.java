package search;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graph {
	private Node nodeA, nodeB, nodeC, nodeD, nodeE, nodeF, 
	             nodeG, nodeH, nodeI, nodeK, nodeL, nodeM, 
	             nodeN, nodeP, nodeQ, nodeR, nodeS, nodeT, 
	             nodeU, nodeV, nodeW;

	public void criaGrafo(){
		nodeA = new Node("A", 2);
		nodeB = new Node("B", 4);
		nodeC = new Node("C", 3);
		nodeD = new Node("D", 2);
		nodeE = new Node("E", 3);
		nodeF = new Node("F", 3);
		nodeG = new Node("G", 3);
		nodeH = new Node("H", 2);
		nodeI = new Node("I", 4);
		nodeK = new Node("K", 3);
		nodeL = new Node("L", 2);
		nodeM = new Node("M", 2);
		nodeN = new Node("N", 2);
		nodeP = new Node("P", 3);
		nodeQ = new Node("Q", 2);
		nodeR = new Node("R", 2);
		nodeS = new Node("S", 2);
		nodeT = new Node("T", 4);
		nodeU = new Node("U", 2);
		nodeV = new Node("V", 2);
		nodeW = new Node("W", 2);
		
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
		Node node, nodeAdj;
		
		nodeI.setVisited(false);
		queue.offer(nodeI);
		
		while(!queue.isEmpty()){
			node = queue.poll();
			for (int i = 0; i < node.getSons().length; i++) {
				if (!node.getNodeName().equals(node.getSons()[i].getNodeName())){
					node.getSons()[i].setVisited(false);
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
	
	public String buscaLargura(String graphName){
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
}