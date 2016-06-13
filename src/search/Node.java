package search;

public class Node {
	private Boolean visited;
	private String nodeName;
	private Integer x;
	private Integer y;
	private Node sons[] = null;
	
	
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

	public Node[] getSons() {
		return sons;
	}

	public void setSon(int position, Node node) {
		this.sons[position] = node;
	}
	
	private Double getDistance(Integer x1, Integer x2, Integer y1, Integer y2){
		return Math.sqrt(((Math.pow((x1 - x2), 2)) + (Math.pow((y1 - y2), 2))));
	}
	
	public Node getUnvisitedNode(Node node){
		Node retNode = null;
		for (int i = 0; i < node.getSons().length; i++) {
			if (!node.getSons()[i].getVisited()){
				node.getSons()[i].setVisited(true);
				retNode = node.getSons()[i];
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
}
