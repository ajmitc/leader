package leader.util;

import java.util.Random;
import java.util.Date;

public class Util
{
	private static Random GEN = new Random( new Date().getTime() );
	
	public static int randInt( int min, int max )
	{
		return min + GEN.nextInt( max - min ); 
	}
	
	public static int randInt( int max )
	{
		return randInt( 0, max );
	}
	
	public static double rand()
	{
		return GEN.nextDouble();
	}
	
	public static double getDistance( int x0, int y0, int x1, int y1 )
	{
		return Math.sqrt( (x1 - x0) * (x1 - x0) + (y1 - y0) * (y1 - y0) );
	}
	
	private Util(){}
}