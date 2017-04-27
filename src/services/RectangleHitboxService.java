package services;

public interface RectangleHitboxService extends /*refine*/ HitboxService{
 
	// Observators
	
    public int getWidth();
    
    public int getHeight();
    
    
    // Invariants

	// \inv: belongsTo(x,y) == getPositionX() <= x <= getPositionX()+getWidth() && getPositionY() <= y <= getPositionY()+getHeight() 
    
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
    // \post: getHeight() == h
    // \post: getWidth() == w
    public void resize(int w,int h);
    
    @Override
	public RectangleHitboxService clone();
}
