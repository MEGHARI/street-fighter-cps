package services;

import data.Tech;

public interface FightCharService extends /* refine */ CharacterService {
	
	
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
	// isOutOfControl(C) =(min) isTeching(C) || isBlockstunned(C) || isHitStunned(C)


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
	
	// pre: !notManipulable()
	// post : isBlocking()=false
	public void startBlock();
}
