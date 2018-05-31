package model.element.mobile;

import java.awt.Point;

import model.ILevel;
import model.IMobile;
import model.Permeability;
import model.Sprite;
import model.element.Element;
import model.element.mobile.auto.Spell;
import model.element.mobile.collectible.Purse;
import model.element.motionless.EnergyBall;
import model.element.motionless.MoneyBag;
import model.element.motionless.MotionlessElementFactory;
import model.element.motionless.OpenedDoor;

public abstract class Mobile extends Element implements IMobile {
	
	private Point position;
	private boolean alive = true;
	private ILevel level;
	
	private int lastX = 0;
	private int lastY = 0;

	public Mobile(Sprite sprite, Permeability permeability, ILevel level) {
		this(sprite, permeability, level, 0, 0);
	}

	public Mobile(Sprite sprite, Permeability permeability, ILevel level, int x, int y) {
		super(sprite, permeability);
		this.level = level;
		this.position = new Point();
		this.getPosition().x = x;
		this.getPosition().y = y;
	}

	@Override
	public void moveUp() {
		this.setY(this.getY() - 1);
		this.lastY = -1;
		this.lastX = 0;
	}

	@Override
	public void moveLeft() {
		this.setX(this.getX() - 1);
		this.lastX = -1;
		this.lastY = 0;
	}

	@Override
	public void moveDown() {
		this.setY(this.getY() + 1);
		this.lastY = 1;
		this.lastX = 0;
	}

	@Override
	public void moveRight() {
		this.setX(this.getX() + 1);
		this.lastX = 1;
		this.lastY = 0;
	}
	
	public void setHasMoved() {
		this.level.setElementHasChanged();
	}

	@Override
	public int getX() {
		return this.getPosition().x;
	}
	
	public void setX(int x) {
		if (!this.isOnWall(x, this.getY())) {
			this.getPosition().x = x;
        }
		
		if(this.isOnKey(x, this.getY())) {
			
		}
		else if(this.isOnDoor(x, this.getY())) {
			
		}
		else if(this.isHit(x, this.getY())) {
			
		}
		else if(this.isOnPurse(x, this.getY())) {
			this.getLevel().setOnTheLevelXY(x, this.getY(), MotionlessElementFactory.createFloor());
		}
	}

	@Override
	public int getY() {
		return this.getPosition().y;
	}
	
	public void setY(int y) {
		if (!this.isOnWall(this.getX(), y)) {
			this.getPosition().y = y;
        }
		
		if(this.isOnKey(this.getX(), y)) {
			
		}
		else if(this.isOnDoor(this.getX(), y)) {
			
		}
		else if(this.isHit(this.getX(), y)) {
			
		}
		else if(this.isOnPurse(this.getX(), y)) {
			((Purse)this.getLevel().getOnTheLevelXY(this.getX(), y)).die();
		}
	}
	
	public void initX(int x) {
		this.getPosition().x = x;
	}
	
	public void initY(int y) {
		this.getPosition().y = y;
	}

	@Override
	public boolean isAlive() {
		return this.alive;
	}

	public boolean isOnWall(int newX, int newY) {
		return (this.getLevel().getOnTheLevelXY(newX, newY).getPermeability() == Permeability.BLOCKING);
	}
	
	@Override
	public boolean isHit(int newX, int newY) {
		return (this.getLevel().getOnTheLevelXY(newX, newY).getPermeability() == Permeability.MONSTER);
	}

	@Override
	public boolean isOnDoor(int newX, int newY) {
		return (this.getLevel().getOnTheLevelXY(newX, newY) instanceof OpenedDoor);
	}

	@Override
	public boolean isOnKey(int newX, int newY) {
		return (this.getLevel().getOnTheLevelXY(newX, newY) instanceof EnergyBall);
	}
	
	@Override
	public boolean isOnPurse(int newX, int newY) {
		return (this.getLevel().getOnTheLevelXY(newX, newY) instanceof MoneyBag);
	}

	@Override
	public Point getPosition() {
		return this.position;
	}

	@Override
	public void shoot() {
		int x = this.getX() - lastX;
		int y = this.getY() - lastY;
		this.level.setSpellOnTheLevelXY(x, y, new Spell(this.getLevel(), x, y));
	}
	
	protected void die() {
		this.alive = false;
		this.setHasMoved();
	}
	
	protected ILevel getLevel() {
		return this.level;
	}

}