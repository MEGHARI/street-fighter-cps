package services;

public interface HitboxService {

	public int getPositionX();
	
	public int getPositionY();
	
	public boolean belongsTo(int x,  int y);
	
	public boolean collidesWith(HitboxService h);

	public boolean equalsTo(HitboxService h);
	
			/*Invariants*/
	
	// \inv: collidesWith(h) == \exists x, y: int x int { belongsTo(x, y) && h.belongsTo(x, y) }
    // \inv: equalsTo(h) == \forall x: int x int { belongsTo(x, y) == h.belongsTo(x, y) }
	
			/*initialisation*/
	
	// \post : getPositionX() == x
	// \post : getPositionY() == y
	public void init(int x,int y) ;
	
			/*Operators*/
	
	// \post: getPositionX() == x
    // \post: getPositionY() == y
    // \post: \forall u, v: int x int { belongsTo(u, v) == belongsTo(u-(x-getPositionX()@pre), v-(y-getPositionY()@pre))@pre }
	public void moveTo(int x, int y);
	
	
	
}
