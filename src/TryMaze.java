
public class TryMaze {

	public static void main(String[] args)
	{
		try
		{
			Coordinate goal = new Coordinate(4, 4);
			
			int[][] map = new int[][] {
				{0, 0, 0, 0, 0},
				{0, 1, 1, 0, 1},
				{0, 1, 0, 0, 0},
				{0, 1, 0, 1, 0},
				{0, 1, 0, 1, 0},
				};
			Maze maze = new Maze(map, goal);
			maze.RightHandRule();
			
			map = new int[][] {
				{0, 0, 0, 0, 0},
				{1, 1, 1, 1, 0},
				{0, 0, 0, 0, 0},
				{0, 1, 1, 1, 1},
				{0, 0, 0, 0, 0},
				};
				
			maze = new Maze(map, goal);
			maze.RightHandRule();
			
			map = new int[][] {
				{0, 1, 0, 0, 0},
				{0, 1, 0, 1, 0},
				{0, 1, 0, 1, 0},
				{0, 1, 0, 1, 0},
				{0, 0, 0, 1, 0},
				};
				
			maze = new Maze(map, goal);
			maze.RightHandRule();
			
			map = new int[][] {
				{0, 1, 0, 0, 0},
				{0, 1, 0, 1, 0},
				{0, 0, 0, 1, 1},
				{0, 1, 0, 0, 0},
				{0, 1, 0, 1, 0},
				};
				
			maze = new Maze(map, goal);
			maze.RightHandRule();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
