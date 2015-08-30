package maps;

import java.awt.Point;
import java.util.ArrayList;
/**
 * Forest with variable size - for use with GameMap
 */
import java.util.List;

public class ForrestBasic extends OccupiedArea implements IEnterable, IExitable {
	
	private String messageOnEnter;
	private String messageOnExit;

	public ForrestBasic(String name, String messageOnNear, String messageOnEnter, String messageOnExit, Point forrestStartPoint, int width, int height) {
		super(	"basic forrest", 
				"Oh.. I recognize that smell. A forrest is near!", 
				createOccupiedPoints(forrestStartPoint, width, height)
				);
		
		this.messageOnEnter = messageOnEnter;
		this.messageOnExit = messageOnExit;
		
	}
	
	// this is where the "structure" of the forest is created
	private static List<Point> createOccupiedPoints(Point forrestStartingPoint, int width, int height){
		// forest begins here
		int minX = forrestStartingPoint.x;
		int minY = forrestStartingPoint.y;
		
		// forest ends here
		int maxX = minX + width;
		int maxY = minY + height;
		
		ArrayList<Point> occupiedPoints = new ArrayList<Point>();
		for(int xPos = minX; xPos <= maxX; xPos++){
			for(int yPos = minY; yPos <= maxY; yPos++){
				occupiedPoints.add(new Point(xPos, yPos));
			}
		}
		return occupiedPoints;
	}

	@Override
	public void onEnter() {
		System.out.println(messageOnEnter);
	}
	
	@Override
	public void onExit() {
		System.out.println(messageOnExit);	
	}

}
