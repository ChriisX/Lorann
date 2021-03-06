package model.element.mobile.auto;

import java.util.Random;

import model.ILevel;
import model.Sprite;

/**
 * The FourthMonster class
 * @author Christophe CHICHMANIAN
 *
 */
public class FourthMonster extends Monster {
	
	/** Sprite of the monster */
	private static Sprite sprite = new Sprite('P', "monster_4");

	/**
	 * @see Monster constructor
	 * @param level
	 * 		the level
	 * @param x
	 * 		X coordinate
	 * @param y
	 * 		Y coordinate
	 */
	public FourthMonster(ILevel level, int x, int y) {
		super(sprite, level, x, y);
	}

	@Override
	public void doNothing() {
		// TODO Auto-generated method stub
	}

	@Override
	public void move() {

		Random random = new Random();
		
		boolean hasMoved = false;
		
		while(!hasMoved) {
			switch(random.nextInt(4) + 1) {
				case 1:
					hasMoved = moveUp();
					break;
				case 2:
					hasMoved = moveDown();
					break;
				case 3:
					hasMoved = moveLeft();
					break;
				case 4:
					hasMoved = moveRight();
					break;
			}
		}	
	}
	

}
