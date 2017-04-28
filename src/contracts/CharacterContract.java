package contracts;

import decorators.CharacterDecorator;
import enums.COMMAND;
import enums.NAME;
import errors.InvariantError;
import errors.PostconditionError;
import errors.PreconditionError;
import services.CharacterService;
import services.EngineService;
import services.HitboxService;

public class CharacterContract extends CharacterDecorator {
	
	public CharacterContract(CharacterService delegate) {
		super(delegate);
	}
	
	public void checkInvariant() {
		// \inv: getPositionX() >= 0 && getPositionX() <= getEngine().getWidth()
		if (getEngine() != null && !(getPositionX() >= 0 && getPositionX() <= getEngine().getWidth()))
		    throw new InvariantError("Position (x) incohérente du personnage ");
		// \inv: getPositionY() >= 0 && getPositionY() <= getEngine().getHeight()
		if (getEngine() != null && !(getPositionY() >= 0 && getPositionY() <= getEngine().getHeight()))
		    throw new InvariantError("Position (y) incoherente du personage");
		// \inv: isDead() == !(getLife() > 0)
		if (!(isDead() == !(getLife() > 0)))
		    throw new InvariantError("Incohérence au niveau de la vie du personage");
		// \inv : getPositionX() = getCharBox().getPositionX()
		if(!(getPositionX() == getCharBox().getPositionX()))
			throw new InvariantError("Incohérence de positionX entre le charactere et sa hitbox");
		// \inv : getPositionY() = getCharBox().getPositionY()
		if(!(getPositionY() == getCharBox().getPositionY()))
			throw new InvariantError("Incohérence de positionY entre le charactere et sa hitbox");
	}
		/*Observators*/
	
	@Override
    public int getPositionX() {
		// run
		return super.getPositionX();
    }

    @Override
    public int getPositionY() {
		// run
		return super.getPositionY();
    }
    @Override
    public NAME getName() {
    	return super.getName();
    }

    @Override
    public EngineService getEngine() {
		// run
		return super.getEngine();
    }

    @Override
    public HitboxService getCharBox() {
		// run
		return super.getCharBox();
    }

    @Override
    public int getLife() {
		// run
		return super.getLife();
    }

    @Override
    public int getSpeed() {
		// run
		return super.getSpeed();
    }

    @Override
    public boolean faceRight() {
		// run
		return super.faceRight();
    }

    @Override
    public boolean isDead() {
		// run
		return super.isDead();
    }
    
    /*Initialisations*/
    
    @Override
    public void init(NAME name,int l, int s, boolean f, EngineService e) {
	    
    	// preconditions
		// pre: l > 0
		if (!(l > 0))
		    throw new PreconditionError("Vie négative ou nulle");
	    	// pre: s > 0
		if (!(s > 0))
		    throw new PreconditionError("Vitesse négative ou nulle");
		
		// run
		super.init(name,l, s, f, e);
		
		// postInvariants
		checkInvariant();
		
		// postConditions
		// \post : getName() = name
		if (!(getName() == name))
		    throw new PostconditionError("initialisation du nom incorrect");
	    // post: getLife() == l
		if (!(getLife() == l))
		    throw new PostconditionError("initialisation de la vie incorrect");
	    // post: getSpeed() == s
		if (!(getSpeed() == s))
		    throw new PostconditionError("initialisation de la vitesse incorrect");
	    // post: faceRight() == f
		if (!(faceRight() == f))
		    throw new PostconditionError("initialisation de l'orientation incorrect");
	    // post: getEngine() == e
		if (!(getEngine() == e))
		    throw new PostconditionError("initialisation du moteur de jeu incorrect");
	    // post: \exists h: HitboxService { getCharBox() == h }
		if(getCharBox() == null)
			throw new PostconditionError("initialisation de la hitbox  incorrect");
			
		
    }
    			/*Operators	*/
    @Override
    public void setPositions(int x, int y) {
    	
    	//preInvariants
    	checkInvariant();
    	
    	//run
    	super.setPositions(x, y);
    	
    	//postInvariants
    	checkInvariant();
    	
    	//postConditions
    	// \post : getPositionX()==x
    		if(getPositionX() != x)
    			throw new PostconditionError("la position 'x' est incohérente");
    	// \post : getPositionY()==y
    		if(getPositionY() != y)
    			throw new PostconditionError("la position 'y' est incohérente");
    }
    @Override
    public void initFace(boolean face) {
    	//preInvariants
    	checkInvariant();
    	
    	// run
    	super.initFace(face);
    	
    	// postInvariant
    	checkInvariant();
    	
    	// capture
    	int positionXPre=getPositionX();
    	int positionYPre = getPositionY();
    	
    	// postConditions
    	// \post faceRight() == face
    	if(faceRight()!=face)
    		throw new PostconditionError("initialisation incorrect de la face du joueur");
    	// \post: getPositionX() == getPositionX()@pre
    	if(getPositionX() != positionXPre)
			throw new PostconditionError("la position 'x' est incohérente");
    	// \post: getPositionY() == getPositionY()@pre
    	if(getPositionY() != positionYPre)
			throw new PostconditionError("la position 'y' est incohérente");
    }
    
    @Override
    public void moveLeft() {
		// preInvariant
		checkInvariant();
		// captures
		int positionXPre = getPositionX();
		int positionYPre = getPositionY();
		int speedPre = getSpeed();
		int lifePre = getLife();
		boolean faceRightPre = faceRight();
		EngineService enginePre = getEngine();
		// run
		super.moveLeft();
		// postInvariants
		checkInvariant();
		// postConditions
		
		// \post:  \exists i:int if ( getEngine()@pre.getChar(i) != self &&
		//	getCharBox().collidesWith(getEngine()@pre.getChar(i).getCharBox()) )
		//	then getPositionX() == getPositionX()@pre
			
		for (int i = 0; i < 2; i++) {
			 if (enginePre.getChar(i)!=this && getCharBox().collidesWith(enginePre.getChar(i).getCharBox())) {
				 if(getPositionX() != positionXPre)
					 throw new PostconditionError("erreur de position de x");
			 }
		    

		}
		// \post: if ( getPositionX()@pre <= getSpeed()@pre) && 
		//(\exists i: int { getEngine()@pre.getChar(i) == self)
		//	|| !getCharBox().collidesWith(getEngine()@pre.getChar(i).getCharBox())))
		//        then   getPositionX() == 0
		for (int i = 0; i < 2; i++) {
		    if((positionXPre <= speedPre) && (enginePre.getChar(i) == this) 
		    		|| (!getCharBox().collidesWith(enginePre.getChar(i).getCharBox())) ) {
		    	if(getPositionX() != 0) {
		    		throw new PostconditionError("erreur de positions");
		    	}
		    }
		}
		// \post: if ( getPositionX()@pre > getSpeed()@pre) && 
		//(\exists i: int { getEngine()@pre.getChar(i) == self)
		//	|| !getCharBox().collidesWith(getEngine()@pre.getChar(i).getCharBox())))
		//        then   getPositionX() == getPositionX()@pre -getSpeed()@pre
		
	    for (int i = 0; i < 2; i++) {
	    	    if((positionXPre>speedPre) && (enginePre.getChar(i) == this) 
	    	    		||(!getCharBox().collidesWith(enginePre.getChar(i).getCharBox())) )
	    	    	if(getPositionX() != positionXPre - speedPre)
	    	    		throw new PostconditionError("erreur de position");
	    }
	    // \post: faceRight() == faceRight()@pre && getLife() == getLife()@pre
	    if (!(faceRight() == faceRightPre && getLife() == lifePre))
	    	throw new PostconditionError("L'orientation ou la vie erronée");
	    // \post: getPositionY() == getPositionY()@pre
	    if (!(getPositionY() == positionYPre))
	    	throw new PostconditionError("La position Y erronée");
    }
    
    @Override
    public void moveRight() {
		// preInvariant
		checkInvariant();
		// captures
		int positionXPre = getPositionX();
		int positionYPre = getPositionY();
		int speedPre = getSpeed();
		int lifePre = getLife();
		boolean faceRightPre = faceRight();
		EngineService enginePre = getEngine();
		// run
		super.moveRight();
		// postInvariants
		checkInvariant();
		// postConditions
		
		// \post:  \exists i:int if ( getEngine()@pre.getChar(i) != self &&
		//	getCharBox().collidesWith(getEngine()@pre.getChar(i).getCharBox()) )
		//	then getPositionX() == getPositionX()@pre
			
		for (int i = 0; i < 2; i++) {
			if (enginePre.getChar(i)!=this && getCharBox().collidesWith(enginePre.getChar(i).getCharBox())) {
				 if(getPositionX() != positionXPre)
					 throw new PostconditionError("erreur de position de x");
			 }
		}
		// \post: if ( (getPositionX()@pre <= getEngine()@pre.getWith()-getSpeed()@pre) && 
		//(\exists i: int { getEngine()@pre.getChar(i) == self)
		//	|| !getCharBox().collidesWith(getEngine()@pre.getChar(i).getCharBox())))
		//        then   getPositionX() == getPositionX()@pre+getSpeed()@pre
		for (int i = 0; i < 2; i++) {
		    if((positionXPre<=enginePre.getWidth()-speedPre) && (enginePre.getChar(i)== this)
		    		||(!getCharBox().collidesWith(enginePre.getChar(i).getCharBox()))) {
		    	if(getPositionX() != positionXPre-speedPre)
		    		throw new PostconditionError("erreur de positionnement (moveRight)");
		    }
		}
		// \post: if ( (getPositionX()@pre > getEngine()@pre.getWith()-getSpeed()@pre) && 
		//(\exists i: int { getEngine()@pre.getChar(i) == self)
		//	|| !getCharBox().collidesWith(getEngine()@pre.getChar(i).getCharBox())))
		//        then   getPositionX() == getEngine()@pre.getWith()
	    for (int i = 0; i < 2; i++) {
	    	    if((positionXPre > enginePre.getWidth()-speedPre) 
	    	    		&& (enginePre.getChar(i) == this) 
	    	    		|| (!getCharBox().collidesWith(enginePre.getChar(i).getCharBox()))) {
	    	    	if(getPositionX() != enginePre.getWidth())
	    	    		throw new PostconditionError("erreur de positionement (moveRight)");
	    	    }
	    }
	    // \post: faceRight() == faceRight()@pre && getLife() == getLife()@pre
	    if (!(faceRight() == faceRightPre && getLife() == lifePre))
	    	throw new PostconditionError("L'orientation ou la vie erronée");
	    // \post: getPositionY() == getPositionY()@pre
	    if (!(getPositionY() == positionYPre))
	    	throw new PostconditionError("La position Y erronée");
    }
    
    
    
    @Override
    public void switchSide() {
		
		// preInvariants
		checkInvariant();
		
		// captures
		boolean faceRightPre = faceRight();
		int positionXPre = getPositionX();
		int positionYPre = getPositionY();
		
		// run
		super.switchSide();
		
		// postInvariants
		checkInvariant();
		
		// postConditions
		// post: faceRight() != faceRight()@pre
		if (!(faceRight() != faceRightPre))
		    throw new PostconditionError("L'orientation n'a pas changé");
		// \post: getPositionX() == getPositionX()@pre
		if (!(getPositionX() == positionXPre))
		    throw new PostconditionError("La position X a changé");
		// \post: getPositionY() == getPositionY()@pre
		if (!(getPositionY() == positionYPre))
		    throw new PostconditionError("La position Y a changé");
    }
    
    @Override
    public void step(COMMAND c) {
    	
    	// preconditions
		// pre: !isDead()
		if (isDead())
		    throw new PreconditionError("Le personnage est KO");
		// \pre : c ∈ {RIGHT, LEFT, NEUTRAL }
		if(!(c==COMMAND.RIGHT || c == COMMAND.LEFT || c==COMMAND.NEUTRAL))
			throw new PreconditionError("COMMANDE INVALIDE");
		
		// preInvariants
		checkInvariant();
		
		// run
		super.step(c);
		
		// postInvariants
		checkInvariant();
		
		// postConditions
		// \post : c == LEFT => (step(c) == moveLeft(c))
		// \post : c == RIGHT => (step(c) == moveRIGHT(c))
		// \post : c==NEUTRAL => (step(c) == step(c)@pre)
    }
    
    @Override
    public CharacterContract clone(){
    	return new CharacterContract(getDelegate().clone());
    }


}
