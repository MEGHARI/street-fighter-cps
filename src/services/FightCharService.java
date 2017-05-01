package services;

import data.Tech;
import enums.COMMAND;
import enums.NAME;

public interface FightCharService extends /* refine */ CharacterService {

	@Override
	public RectangleHitboxService getCharBox();
	
	public boolean notManipulable();

	public boolean isBlocking();

	public boolean isBlockstunned();

	public boolean isHitstunned();

	public boolean isTeching();

	// pre: isTeching()
	public Tech getTech();

	// pre: isTeching()
	public int getTechFrame();

	// pre: isTeching()
	public boolean isTechHasAlreadyHit();

	// Invariants
	// notManipulable(C) =(min) isTeching(C) || isBlockstunned(C) || isHitStunned(C)

	// Constructors
	
	// pre: l > 0
	// pre: s > 0
	// post: getName() == name
	// post: getLife() == l
	// post: getSpeed() == s
	// post: faceRight() == f
	// post: getEngine() == e
	// post: getCharBox() ==rh 
	// \post: notManipulable(C) = false ^ isBlocking() = false
	public void init(NAME name,int l, int s, boolean f,RectangleHitboxService rh, EngineService e);

	// Operators

	// pre: !notManipulable()
	public void startTech(Tech tech);
	// pre: !notManipulable()
	// \post : getPositionX()@pre=getPositionX()
	// \post : getPositionY()@pre=getPositionY()
	public void jump();
	// pre: !notManipulable()
	// \post : getPositionX()@pre=getPositionX()
	// \post : getPositionY()@pre=getPositionY()
	public void crouch();
	// \pre : startBloking() requires !isBloking()
	// \post : isBloking = false;

	// pre: !notManipulable() && !notBlocking()
	// post : isBlocking()=false
	public void startBlock();


	// \post (notManipulable()@pre || isBlocking()@pre) => getPositionX() = getPositionX()@pre
	// \post:  \exists i:int if ( !(notManipulable()@pre || isBlocking()@pre) && getEngine()@pre.getChar(i) != self &&
	//	getCharBox().collidesWith(getEngine()@pre.getChar(i).getCharBox()) )
	//	then getPositionX() == getPositionX()@pre

	// \post: if ( !(notManipulable()@pre || isBlocking()@pre) && getPositionX()@pre <= getSpeed()) && 
	//(\forall i: int { getEngine()@pre.getChar(i) == self)
	//	|| !getCharBox().collidesWith(getEngine()@pre.getChar(i).getCharBox())))
	//        then   getPositionX() == 0

	// \post: if ( !(notManipulable()@pre || isBlocking()@pre) && getPositionX()@pre > getSpeed()) && 
	//(\forall i: int { getEngine()@pre.getChar(i) == self)
	//	|| !getCharBox().collidesWith(getEngine()@pre.getChar(i).getCharBox())))
	//        then  getPositionX() == getPositionX()@pre -getSpeed()@pre

	// \post: faceRight() == faceRight()@pre && getLife() == getLife()@pre

	// \post: getPositionY() == getPositionY()@pre
	@Override
	public void moveLeft();
	// \post:(notManipulable()@pre || isBlocking()@pre) => getPositionX()@pre = getPositionX()

	// \post:  \exists i:int if ( !(notManipulable()@pre || isBlocking()@pre) && getEngine()@pre.getChar(i) != self &&
	//	getCharBox().collidesWith(getEngine()@pre.getChar(i).getCharBox()) )
	//	then getPositionX() == getPositionX()@pre

	// \post: if (!(notManipulable()@pre || isBlocking()@pre) && (getPositionX()@pre <= getEngine()@pre.getWidth()-getSpeed()@pre) && 
	//(\forall i: int { getEngine()@pre.getChar(i) == self)
	//	|| !getCharBox().collidesWith(getEngine()@pre.getChar(i).getCharBox())))
	//        then   getPositionX() == getPositionX()@pre+getSpeed()@pre

	// \post: if ( !(notManipulable()@pre || isBlocking()@pre) &&(getPositionX()@pre > getEngine()@pre.getWidth()-getSpeed()@pre) && 
	// (\forall i: int { getEngine()@pre.getChar(i) == self)
	//	|| !getCharBox().collidesWith(getEngine()@pre.getChar(i).getCharBox())))
	//        then   getPositionX() == getEngine()@pre.getWidth() - getChar().getWith()

	// \post: faceRight() == faceRight()@pre && getLife() == getLife()@pre

	// \post: getPositionY() == getPositionY()@pre
	@Override
	public void moveRight();

	// post:(notManipulable()@pre || isBlocking()@pre) => faceRight() = faceRight()@pre
	// post:!(notManipulable()@pre || isBlocking()@pre) => faceRight() != faceRight()@pre
	// \post: getPositionX() == getPositionX()@pre
	// \post: getPositionY() == getPositionY()@pre
	@Override
	public void switchSide();
	// \post : c == JUMP => (step(c) == jump())
	// \post : c == CROUCH => (step(c) == crouch())

	@Override
	public void step(COMMAND c);
	
	// _post : getBlokstunned = b
	public void setBlokstunned(boolean b);
	// post : getHitstunned() = h
	public void setHitstunned(boolean h);
	
	// pre : damage > 0
	// post : getLife()@pre > dammage => getLife() = getLife()@pre -damage
	// post : getLife()@pre < damage =>getLife() =0
	public void updateLife(int dammage);
	
	

	//@Override
	public FightCharService clone();

}