package services;

import enums.COMMAND;

public interface EngineService {

	/* Observators */

	public int getHeight();

	public int getWidth();

	// pre: i \in { 1, 2 }
	public CharacterService getChar(int i);

	// pre: i \in { 1, 2 }
	public PlayerService getPlayer(int i);

	public boolean isGameOver();

	/* Invariants */
	// \inv: isGameOver() == getChar(1).isDead() || getChar(2).isDead()

	/* Constructors */
	// \pre: h > 0
	// \pre: s > 0
	// \pre: w > s
	// \pre: p1 != p2
	// \post: getHeight() == h
	// \post: getWidth() == w
	// \post: getPlayer(1) == p1
	// \post: getPlayer(2) == p2
	// \post: getChar(1).getPositionX() == w/2 - s/2
	// \post: getChar(2).getPositionX() == w/2 + s/2
	// \post: getChar(1).getPositionY() == 0
	// \post: getChar(2).getPositionY() == 0
	// \post: getChar(1).faceRight()
	// \post: !(getChar(2).faceRight())
	public void init(int h, int w, int s, PlayerService p1, PlayerService p2);

	/* Operators */

	// \pre: !isGameOver()
	// \post: getChar(1) = getChar(1)@pre.step(C1)
	// \post: getChar(2) = getChar(2)@pre.step(C2)
	// \post: if getChar(1).getPositionX() < getChar(1).getPositionX() 
	// && getChar(1).faceRight() then getChar(1).switchFace()
	// \post: if getChar(1).getPositionX() < getChar(2).getPositionX() 
	// && getChar(1).faceRight() then !getChar(1).faceRight() && getChar(2).faceRight()
	public void step(COMMAND c1, COMMAND c2);




}

