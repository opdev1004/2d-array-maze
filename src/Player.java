
public class Player
{
	// Direction for facing
	final int TOP = 0;
	final int RIGHT = 1;
	final int BOTTOM = 2;
	final int LEFT = 3;
	
	int facing;
	int x;
	int y;
	int walkingCount;
	
	public Player(int facing) throws IncorrectDirectionException
	{
		x = 0;
		y = 0;
		
		if(facing < 0 || facing > 3) throw new IncorrectDirectionException(String.format("Direction should be defined between 0 - 3 but it was %d", facing));
		this.facing = facing;
		
		walkingCount = 0;
	}
	
	public Player(int x, int y, int facing) throws IncorrectCoordinateException, IncorrectDirectionException
	{
		if(x < 0 || y < 0) throw new IncorrectCoordinateException(String.format("Incorrect Coordinate. Coordinate cannot be negative. (%d, %d)", x, y));
		this.x = x;
		this.y = y;
		
		if(facing < 0 || facing > 3) throw new IncorrectDirectionException(String.format("Direction should be defined between 0 - 3 but it was %d", facing));
		this.facing = facing;
		
		walkingCount = 0;
	}
	
	public void TurnRight()
	{
		++facing;
		if(facing > 3) facing = TOP;
	}
	
	public void TurnLeft()
	{
		--facing;
		if(facing < 0) facing = LEFT;
		
	}
	
	public void increaseWalkingCount()
	{
		++walkingCount;
	}
	
	public void Forward()
	{
		if(facing == TOP) --y;
		else if(facing == RIGHT) ++x;
		else if(facing == BOTTOM) ++y;
		else if (facing == LEFT) --x;
		
		increaseWalkingCount();
	}
	
	public void PrintPlayer()
	{
		System.out.println(ToString());
	}
	
	public String ToString()
	{
		String direction = "";
		
		if(facing == TOP) direction = "TOP";
		else if(facing == RIGHT) direction = "RIGHT";
		else if(facing == BOTTOM) direction = "BOTTOM";
		else if(facing == LEFT) direction = "LEFT";
		
		return String.format("Player is at (%d, %d). %d steps. Facing %s", x, y, walkingCount, direction);
	}
}
