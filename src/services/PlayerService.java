package services;


public interface PlayerService {
	
	public CharacterService getCharacter();
    
    public int getNum();
    
    
    
    // Invariants
    
    // Constructors
    
    // pre: n == 1 || n ==2
    public void init(CharacterService c, int n);
    
    
    public void setCharacter(CharacterService ch);
    
    //public void sendCommand();
    
}
