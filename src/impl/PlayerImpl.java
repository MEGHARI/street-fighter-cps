package impl;

import services.CharacterService;
import services.PlayerService;

public class PlayerImpl implements PlayerService {
	
	private CharacterService ch;
	private int num;
	
	@Override
	public CharacterService getCharacter() {
		return ch;
	}
	@Override
	public int getNum() {
		return num;
	}
	
	@Override
	public void init(int n) {
		this.num =n;
	}
	@Override
	public void setCharacter(CharacterService ch) {
		this.ch=ch;
	}
	
	
	
}