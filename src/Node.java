import java.util.HashSet;
import java.util.Set;

public class Node implements Comparable<Node>
{
	
	Set<Node> neighbourNodes;
	Coordinate coordinate;
	double distance;
	double distanceToThisNode;
	double estimatedDistanceToGoal;
	double totalEstimatedDistance;
	
	public Node(Coordinate coordinate)
	{
		neighbourNodes = new HashSet<>();
		this.coordinate = coordinate;
		this.distance = Double.MAX_VALUE;
		this.distanceToThisNode = Double.MAX_VALUE;
		estimatedDistanceToGoal = Double.MAX_VALUE;
		totalEstimatedDistance = Double.MAX_VALUE;
	}
	
	public void AddNeighbours(Node node)
	{
		neighbourNodes.add(node);
	}

	public double Distance(Coordinate coordinate)
	{
		return Math.hypot(this.coordinate.x - coordinate.x, this.coordinate.y - coordinate.y);
	}
	
	public int compareTo(Node node) {
		if(this.totalEstimatedDistance > node.totalEstimatedDistance) return 1;
		else if (this.totalEstimatedDistance < node.totalEstimatedDistance) return -1;
		return 0;
	}
}
