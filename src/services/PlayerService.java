package services;


public interface PlayerService {
	
	public CharacterService getCharacter();
    
    public int getNum();
    // Invariants
    
    // Constructors
    
    // pre: n == 1 || n ==2
    // post:getNum() == n.
    public void init(int n);
    
    
    public void setCharacter(CharacterService ch);
    
    
}
