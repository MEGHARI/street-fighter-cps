package services;

import enums.COMMAND;
import enums.NAME;

public interface CharacterService {
	
	public int getPositionX();
	
	public int getPositionY();
	
	public NAME getName();
	
	public EngineService getEngine();
	
	public HitboxService getCharBox();
	
	public int getLife();
	
	public int getSpeed();
	
	public boolean faceRight();
	
	public boolean isDead();

	/* Invariants */
	
	// \inv: getPositionX() >= 0 && getPositionX() <= getEngine().getWidth()
	// \inv: getPositionY() >= 0 && getPositionY() <= getEngine().getHeight()
	// \inv: isDead() == !(getLife() > 0)

			/* Initialisations */
	
	// pre: l > 0
	// pre: s > 0
	// post: getName() == name
	// post: getLife() == l
	// post: getSpeed() == s
	// post: faceRight() == f
	// post: getEngine() == e
	// post: \exists h: HitboxService { getCharBox() == h }
	public void init(NAME name,int l, int s, boolean f, EngineService e);
	
		/* Operators */
	
	// \post:  \exists i:int if ( getEngine()@pre.getChar(i) != self &&
	//	getCharBox().collidesWith(getEngine()@pre.getChar(i).getCharBox()) )
	//	then getPositionX() == getPositionX()@pre
	
	// \post: if ( getPositionX()@pre <= getSpeed()@pre) && 
	//(\exists i: int { getEngine()@pre.getChar(i) == self)
	//	|| !getCharBox().collidesWith(getEngine()@pre.getChar(i).getCharBox())))
	//        then   getPositionX() == 0
	
	// \post: if ( getPositionX()@pre > getSpeed()@pre) && 
	//(\exists i: int { getEngine()@pre.getChar(i) == self)
	//	|| !getCharBox().collidesWith(getEngine()@pre.getChar(i).getCharBox())))
	//        then  getPositionX() == getPositionX()@pre -getSpeed()@pre
	
	// \post: faceRight() == faceRight()@pre && getLife() == getLife()@pre
	
	// \post: getPositionY() == getPositionY()@pre
	
	// \post : getPositionX()==x
	// \post : getPositionY()==y
	public void setPositions(int x,int y);
	
	// \post faceRight() == face
	public void initFace(boolean face);
	
	public void moveLeft();
	// \post:  \exists i:int if ( getEngine()@pre.getChar(i) != self &&
	//	getCharBox().collidesWith(getEngine()@pre.getChar(i).getCharBox()) )
	//	then getPositionX() == getPositionX()@pre
	
	// \post: if ( (getPositionX()@pre <= getEngine()@pre.getWith()-getSpeed()@pre) && 
	//(\exists i: int { getEngine()@pre.getChar(i) == self)
	//	|| !getCharBox().collidesWith(getEngine()@pre.getChar(i).getCharBox())))
	//        then   getPositionX() == getPositionX()@pre+getSpeed()@pre
	
	// \post: if ( (getPositionX()@pre > getEngine()@pre.getWith()-getSpeed()@pre) && 
	// (\exists i: int { getEngine()@pre.getChar(i) == self)
	//	|| !getCharBox().collidesWith(getEngine()@pre.getChar(i).getCharBox())))
	//        then   getPositionX() == getEngine()@pre.getWith()
	
	// \post: faceRight() == faceRight()@pre && getLife() == getLife()@pre
	
	// \post: getPositionY() == getPositionY()@pre
	public void moveRight();
	
	
	// \post: faceRight() != faceRight()@pre
	// \post: getPositionX() == getPositionX()@pre
	// \post: getPositionY() == getPositionY()@pre
	public void switchSide();
	
	// \pre : !isDead()
	// \post : c == LEFT => (step(c) == moveLeft(c))
	// \post : c == RIGHT => (step(c) == moveRIGHT(c))
	// \post : c==NEUTRAL => (step(c) == step(c)@pre)
	public void step(COMMAND c);
	
}
