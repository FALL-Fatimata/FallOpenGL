package Jeu;



public class Tools  {

	
	protected double x;
	protected double y;
	protected int speed;
	
	 // The VBO of this object
    
	public Tools(double x, double y, double dx,double dy,int speed) {
		super();
		this.x = x;
		this.y = y;
//		this.z = z;
		this.dx = dx;
//		this.dz = dz;
		this.dy=dy;
		this.speed=speed;
	}
	

//	protected double z;
	protected double dx;
	protected double dy;
//	protected double dz;
	
	public double getX() {
		
		return this.x;
	}

	
	
	public double getY() {
		
		return this.y;
	}

	
//	public double getZ() {
//		
//		return this.z;
//	}

	
	public double getDx() {
		
		return this.dx;
	}

	
//	public double getDz() {
//		
//		return this.dz;
//	}

	
	public void setX(double x) {
		
		this.x=x;
	}

	
	public void setY(double y) {
		
		this.y=y;
	}

	
//	public void setZ(double z) {
//		
//		this.z=z;
//	}

	
	public void setDx(double dx) {
		
		this.dx=dx;
	}

	
//	public void setDz(double dz) {
//		
//		this.dz=dz;
//	}

	


	
	public double getDy() {
		
		return this.dy;
	}

	
	public void setDy(double dy) {
		
		this.dy=dy;
	}
	

	public void move(){
		x+=dx;
		y+=dy;
//		z+=dz;
	}
	
	public void reInitialize() {
		
		this.setX(0);
		this.setY(0);
//		this.setZ(0);
		this.setDy(Math.signum(Math.random()-0.5)*(Math.random()*0.5+2*speed));
		this.setDx(Math.signum(Math.random()-0.5)*(Math.random()*0.5+2*speed));
//		this.setDz(Math.random()*0.01);
	}

}
