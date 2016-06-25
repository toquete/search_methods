package search;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Node implements Comparable<Node>{
	private Boolean visited;
	private String nodeName;
	private Integer x;
	private Integer y;
	private Double targetDistance;
	private Node sons[] = null;
	
	public Node(){
		
	}
	
	public Node(String nodeName, Integer sonsNum, Integer x, Integer y){
		this.visited = false;
		this.nodeName = nodeName;
		this.x = x;
		this.y = y;
		this.sons = new Node[sonsNum];
	}

	public Boolean getVisited() {
		return visited;
	}


	public void setVisited(Boolean visited) {
		this.visited = visited;
	}


	public String getNodeName() {
		return nodeName;
	}


	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Double getTargetDistance() {
		return targetDistance;
	}

	public void setTargetDistance(Double targetDistance) {
		this.targetDistance = targetDistance;
	}

	public Node[] getSons() {
		return sons;
	}

	public void setSon(int position, Node node) {
		this.sons[position] = node;
	}
	
	public Double getDistance(Integer x1, Integer x2, Integer y1, Integer y2){
		return Math.sqrt(((Math.pow((x1 - x2), 2)) + (Math.pow((y1 - y2), 2))));
	}
	
	public List<Node> getSortedList(Node target) {
		List <Node> list = new LinkedList<Node>();
		
		for (Node node : this.sons) {
			node.setTargetDistance(getDistance(target.x, node.x, target.y, node.y));
			list.add(node);
		}
		
		Collections.sort(list);
		return list;
	}
	
	public Node getUnvisitedNode(Node node){
		Node retNode = null;
		for (Node aux : node.sons) {
			if (!aux.getVisited()){
				aux.setVisited(true);
				retNode = aux;
				break;
			}
		}
		return retNode;
	}
	
	public Node getHillClimbingNode(Node node){
		Node retNode = null;
		double min = 1000;
		
		for (Node aux : node.sons) {
			double distance = getDistance(node.x, aux.x, node.y, aux.y); 
			
			if ((distance < min) && (!aux.getVisited())){
				min = distance;
				retNode = aux;
			}
		}
		
		if(retNode == null)
		  retNode = node.getSons()[0];
		
		retNode.setVisited(true);
		return retNode;
	}
	
	public Node getBestFirstNode(Node target) {
		Node retNode = null;
		List<Node> list = new LinkedList<Node>();
		
		list = getSortedList(target);
		
		for (Node node : list) {
			if (!node.getVisited()) {
				retNode = node;
				break;
			}
		}
		
		return retNode;
	}
	
	public Node getASearchNode(Node node) {
		Node retNode = new Node();
		Double function = 1000.0, sum;
		
		for (Node aux : sons) {
			if (!aux.getVisited()) {
				sum = getDistance(node.x, aux.x, node.y, node.y) + aux.getTargetDistance();
				
				if (sum < function) {
					function = sum;
					retNode = aux;
				}
			}
		}
		
		return retNode;
	}

	@Override
	public int compareTo(Node other) {
		if (this.targetDistance < other.targetDistance)
			return -1;
		if (this.targetDistance > other.targetDistance)
			return 1;
		return 0;
	}
}
