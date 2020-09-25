import java.util.HashMap;
import java.util.Map;

public class Graph
{
	Map<Coordinate, Node> nodes;
	
	public Graph()
	{
		nodes = new HashMap<>();
	}
	
	public void AddNode(Node node)
	{
		nodes.put(node.coordinate, node);
	}
}
