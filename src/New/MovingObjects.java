package New;



public abstract class MovingObjects extends Objects implements MovingObjectInterface {

	protected double dx, dy;
	
	public MovingObjects(double x, double y, double width,
			double height) {
		super(x, y, width, height);
        this.dx=0;
        this.dy=0;}
	
	
	public double getDX(){
		
		return dx;
	}
	public double getDY(){
		
		return dy;
	}
	public void setDX(double dx){
		this.dx=dx;
	}
	public void setDY (double dy){
		
		this.dy=dy;
	}

    @Override
	
	public void update(int delta){
		
		this.x += 1000*delta*dx;
		this.y += 1000*delta*dy;
	}

}
