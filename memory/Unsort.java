/*
* Assignment	memory
* Program		Unsort
* date			Nov 11, 2018
* Author		Jorge Jimenez
*/
package memory;

import java.util.Comparator;
import java.util.Random;

public class Unsort implements Comparator
{

	@Override
	public int compare(Object o1, Object o2)
	{
		Random rand = new Random();
				
		return (rand.nextInt(5) + 1) * (rand.nextInt(5) - 2);
	}

}
