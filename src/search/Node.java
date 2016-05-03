package search;

public class Node {
	private Boolean visited;
	private String nodeName;
	private Node sons[] = null;
	
	
	public Node(String nodeName, Integer sonsNum){
		this.visited = false;
		this.nodeName = nodeName;
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


	public Node[] getSons() {
		return sons;
	}


	public void setSon(int position, Node node) {
		this.sons[position] = node;
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
}
