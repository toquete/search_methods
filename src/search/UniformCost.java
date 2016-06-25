package search;

import java.util.LinkedList;
import java.util.List;

public class UniformCost implements Comparable<UniformCost>{
	private Node node;
	private Double nextNodeDistance;
	
	@Override
	public int compareTo(UniformCost other) {
		if (this.nextNodeDistance < other.nextNodeDistance)
			return -1;
		if (this.nextNodeDistance > other.nextNodeDistance)
			return 1;
		return 0;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public Double getNextNodeDistance() {
		return nextNodeDistance;
	}

	public void setNextNodeDistance(Double nextNodeDistance) {
		this.nextNodeDistance = nextNodeDistance;
	}
	
	private Double getDistance(Integer x1, Integer x2, Integer y1, Integer y2){
		return Math.sqrt(((Math.pow((x1 - x2), 2)) + (Math.pow((y1 - y2), 2))));
	}
	
	public List<UniformCost> getSonsList(Node target) {
		List <UniformCost> list = new LinkedList<UniformCost>();
		
		for (Node n : node.getSons()) {
			UniformCost uc = new UniformCost();
			
			uc.setNode(n);
			uc.setNextNodeDistance(getDistance(target.getX(), n.getX(), target.getY(), n.getY()));
			list.add(uc);
		}
		
		return list;
	}
}
