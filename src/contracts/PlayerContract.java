package contracts;

import decorators.PlayerDecorator;
import errors.PostconditionError;
import errors.PreconditionError;
import services.CharacterService;
import services.PlayerService;

public class PlayerContract extends PlayerDecorator {
	
	public PlayerContract(PlayerService delegate) {
		super(delegate);
	}
	
	public void checkInvariant() {
		// nothing
	}
	@Override
	public CharacterService getCharacter() {
		return super.getCharacter();
	}

	@Override
	public int getNum() {
		return super.getNum();
	}

	@Override
	public void init(int n) {
		// precondition
		// pre : n== 1 || n==2
		if(!(n==1 || n==2))
			throw new PreconditionError("numero non existant");
		// run
		super.init(n);
		
		// postInvariant
		checkInvariant();
		
		// postcondition
		// post : getNum() = n
		if(!(getNum() == n))
			throw new PostconditionError("erreur d'initialisation ");

	}

	@Override
	public void setCharacter(CharacterService ch) {
		// run
		super.setCharacter(ch);
		// postcondition
		// post : getCharacter() == ch
		if(!(getCharacter()==ch)) 
			throw new PostconditionError("character du joueur non initialisé");
	}

}
