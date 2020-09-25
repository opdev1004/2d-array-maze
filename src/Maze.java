import java.util.PriorityQueue;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

public class Maze
{
	// Direction for facing
	final int TOP = 0;
	final int RIGHT = 1;
	final int BOTTOM = 2;
	final int LEFT = 3;
	
	int[][] maze;
	Player player;
	Coordinate goal;
	
	public Maze(int[][] maze, Coordinate goal) throws IncorrectDirectionException, IncorrectCoordinateException
	{
		this.maze = maze;
		player = new Player(BOTTOM);
		
		if(goal.x < 0 || goal.y < 0)
		{
			throw new IncorrectCoordinateException(String.format("Incorrect Coordinate. Coordinate cannot be negative. (%d, %d)", goal.x, goal.y));
		}
		else if(goal.x > maze[0].length - 1 || goal.y > maze.length - 1)
		{
			throw new IncorrectCoordinateException(String.format("Incorrect Coordinate. Coordinate cannot be bigger than maze. (%d, %d)", goal.x, goal.y));
		}
		
		this.goal = goal;
	}
	
	public Maze(int[][] maze, Coordinate start, Coordinate goal) throws IncorrectCoordinateException, IncorrectDirectionException
	{
		this.maze = maze;
		player = new Player(BOTTOM);
		
		if(start.x < 0 || start.y < 0)
		{
			throw new IncorrectCoordinateException(String.format("Incorrect Coordinate. Coordinate cannot be negative. (%d, %d)", start.x, start.y));
		}
		else if(start.x > maze[0].length - 1 || start.y > maze.length - 1)
		{
			throw new IncorrectCoordinateException(String.format("Incorrect Coordinate. Coordinate cannot be bigger than maze. (%d, %d)", start.x, start.y));
		}
		
		player.x = start.x;
		player.y = start.y;
		
		if(goal.x < 0 || goal.y < 0)
		{
			throw new IncorrectCoordinateException(String.format("Incorrect Coordinate. Coordinate cannot be negative. (%d, %d)", goal.x, goal.y));
		}
		else if(goal.x > maze[0].length - 1 || goal.y > maze.length - 1)
		{
			throw new IncorrectCoordinateException(String.format("Incorrect Coordinate. Coordinate cannot be bigger than maze. (%d, %d)", goal.x, goal.y));
		}
		
		this.goal = goal;
	}
	
	public void LeftHandRule() throws IncorrectCoordinateException, InterruptedException
	{
		PrintMaze();
		player.PrintPlayer();
		
		while(player.x != goal.x || player.y != goal.y)
		{
			//TimeUnit.SECONDS.sleep(1);
			
			if(FrontWall() && LeftWall())
			{
				player.TurnRight();
			}
			else if(!LeftWall())
			{
				player.TurnLeft();
				player.Forward();
			}
			else
			{
				player.Forward();
			}
			
			PrintMaze();
			player.PrintPlayer();
		}
		
		PrintWinningMessage();
	}
	
	public void RightHandRule() throws IncorrectCoordinateException, InterruptedException
	{
		PrintMaze();
		player.PrintPlayer();
		
		while(player.x != goal.x || player.y != goal.y)
		{
			//TimeUnit.SECONDS.sleep(1);
			
			if(FrontWall() && RightWall())
			{
				player.TurnLeft();
			}
			else if(!RightWall())
			{
				player.TurnRight();
				player.Forward();
			}
			else
			{
				player.Forward();
			}
			
			PrintMaze();
			player.PrintPlayer();
		}
		
		PrintWinningMessage();
	}
	
	public boolean FrontWall()
	{
		if(player.facing == TOP)
		{
			Coordinate wall = new Coordinate(player.x, player.y - 1);
			
			if(wall.y < 0) return true;
			if(maze[wall.y][wall.x] == 1) return true;
			
		}
		else if(player.facing == RIGHT)
		{
			Coordinate wall = new Coordinate(player.x + 1, player.y);
			
			if(wall.x >= maze[0].length) return true;
			if(maze[wall.y][wall.x] == 1) return true;
		}
		else if(player.facing == BOTTOM)
		{
			Coordinate wall = new Coordinate(player.x, player.y + 1);
			
			if(wall.y >= maze.length) return true;
			if(maze[wall.y][wall.x] == 1) return true;
		}
		else if(player.facing == LEFT)
		{
			Coordinate wall = new Coordinate(player.x - 1, player.y);
			
			if(wall.x < 0) return true;
			if(maze[wall.y][wall.x] == 1) return true;
		}
		
		return false;
	}
	
	public boolean LeftWall()
	{	
		if(player.facing == TOP)
		{
			Coordinate wall = new Coordinate(player.x - 1, player.y);
			
			if(wall.x < 0) return true;
			if(maze[wall.y][wall.x] == 1) return true;
			
		}
		else if(player.facing == RIGHT)
		{
			Coordinate wall = new Coordinate(player.x, player.y - 1);
			
			if(wall.y < 0) return true;
			if(maze[wall.y][wall.x] == 1) return true;
		}
		else if(player.facing == BOTTOM)
		{
			Coordinate wall = new Coordinate(player.x + 1, player.y);
			
			if(wall.x >= maze[0].length) return true;
			if(maze[wall.y][wall.x] == 1) return true;
		}
		else if(player.facing == LEFT)
		{
			Coordinate wall = new Coordinate(player.x, player.y + 1);
			
			if(wall.y >= maze.length) return true;
			if(maze[wall.y][wall.x] == 1) return true;
		}
		
		return false;
	}

	public boolean RightWall()
	{	
		if(player.facing == TOP)
		{
			Coordinate wall = new Coordinate(player.x + 1, player.y);
			
			if(wall.x >= maze[0].length) return true;
			if(maze[wall.y][wall.x] == 1) return true;
			
		}
		else if(player.facing == RIGHT)
		{
			Coordinate wall = new Coordinate(player.x, player.y + 1);

			if(wall.y >= maze.length) return true;
			if(maze[wall.y][wall.x] == 1) return true;
		}
		else if(player.facing == BOTTOM)
		{
			Coordinate wall = new Coordinate(player.x - 1, player.y);
			
			if(wall.x < 0) return true;
			if(maze[wall.y][wall.x] == 1) return true;
		}
		else if(player.facing == LEFT)
		{
			Coordinate wall = new Coordinate(player.x, player.y - 1);
			
			if(wall.y < 0) return true;
			if(maze[wall.y][wall.x] == 1) return true;
		}
		
		return false;
	}
	
	public void AStarShortestPath()
	{
		Graph graph = CreateGraph();
		
		PriorityQueue<Node> waitPool = new PriorityQueue<>();
		Stack<Node> path = new Stack<>();
		
		Coordinate startCoordinate = new Coordinate(0, 0);
		Node node = graph.nodes.get(startCoordinate);
		Node endNode = graph.nodes.get(goal);
		node.distanceToThisNode = 0;
		node.totalEstimatedDistance = node.distanceToThisNode + node.estimatedDistanceToGoal;
		path.push(node);
		
		while(node != endNode)
		{
			if(node.neighbourNodes.isEmpty())
			{
				
			}
			
			for(Node neighbourNode : node.neighbourNodes)
			{
				neighbourNode.distanceToThisNode = node.distanceToThisNode + 1;
				neighbourNode.totalEstimatedDistance = neighbourNode.distanceToThisNode + neighbourNode.estimatedDistanceToGoal;
				waitPool.add(neighbourNode);
			}
			
			node = waitPool.poll();
			path.push(node);
		}
	}
	
	public Graph CreateGraph()
	{
		Graph graph = new Graph();
		
		for(int i = 0; i < maze.length;i++)
		{
			for(int j = 0; j < maze[i].length; j++)
			{
				if(maze[i][j] == 0)
				{
					Coordinate coordinate = new Coordinate(i, j);
					Node node = new Node(coordinate);
					node.distance = 1;
					node.estimatedDistanceToGoal = node.Distance(goal);
					
					if(j > 0)
					{
						Coordinate neighbourCoordinate = new Coordinate(i, j - 1);
						Node neighbourNode = graph.nodes.getOrDefault(neighbourCoordinate, null);
						if(neighbourNode != null)
						{
							neighbourNode.AddNeighbours(node);
							graph.nodes.put(neighbourCoordinate, neighbourNode);
						}
					}
					
					if(i > 0)
					{
						Coordinate neighbourCoordinate = new Coordinate(i - 1, j);
						Node neighbourNode = graph.nodes.getOrDefault(neighbourCoordinate, null);
						if(neighbourNode != null)
						{
							neighbourNode.AddNeighbours(node);
							graph.nodes.put(neighbourCoordinate, neighbourNode);
						}
					}
					
					graph.AddNode(node);
				}
			}
			
		}
		
		return graph;
	}
	
	public void PrintWinningMessage()
	{
		System.out.println("***************************");
		System.out.println("Player reached to the goal!");
		System.out.println("***************************");
	}
	
	public void PrintMaze() throws IncorrectCoordinateException
	{
		System.out.println(ToString());
	}
	
	public String ToString() throws IncorrectCoordinateException
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("====================== \n");
		int[][] mazeReplica = new int[maze.length][maze[0].length];
		
		for(int i = 0; i < maze.length;i++)
		{
			for(int j = 0; j < maze[i].length; j++)
			{
				mazeReplica[i][j] = maze[i][j];
			}
		}
		
		mazeReplica[player.y][player.x] = 2;
				
		for(int i = 0; i < mazeReplica.length;i++)
		{
			for(int j = 0; j < mazeReplica[i].length; j++)
			{
				stringBuilder.append(mazeReplica[i][j]);
			}
			stringBuilder.append("\n");
		}
		stringBuilder.append(String.format("The goal is (%d, %d).", goal.x, goal.y));
		return stringBuilder.toString();
	}
	
}
