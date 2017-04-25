package services;

public interface RectangleHitboxService extends /*refine*/ HitboxService{
 
	// Observators
	
    public int getWidth();
    
    public int getHeight();
    
    
    // Invariants
  
    
    
    // 
    
    // Constructors
    
    // \pre: w > 0
    // \pre: h > 0
    // \post: getPositionX() == x
    // \post: getPositionY() == y
    // \post: getWidth() == w
    // \post: getHeight() == h
    public void init(int x, int y, int w, int h);
    
    // \pre: w > 0
    // \pre: h > 0
    // \post: getHeight(w,h) == w
    // \post: getWidth(w,h) == w
    public void resize(int w,int h);
    
    
}
