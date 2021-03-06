package model;

import java.awt.Point;
import java.util.Observable;

public interface ILevel {
	
	int getWidth();
	int getHeight();
	IElement getOnTheLevelXY(int x, int y);
	void setOnTheLevelXY(int x, int y, IElement element);
	Observable getObservable();
	void setElementHasChanged();
	Point getCharacterPosition();
	IMobile[] getPurses();
	IMobile[] getMonsters();
	IMobile getEnergyBall();
	IMobile getDoor();
	IMobile getSpell();

}
