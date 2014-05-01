package Jeu;


public class Balle extends Tools {


    
	public Balle(double x, double y, double dx,double dy,int radius,int speed) {
		super(x, y, dx,dy,speed);
		this.radius=radius;
		
	  
		// TODO Auto-generated constructor stub
	}
	
	public Balle (double x, double y, double z,int speed, int radius){
		super(x,y,1+(Math.random()-0.5)*speed, 1+(Math.random()-0.5)*speed ,speed);
		System.out.println("La balle est en possition: ("+x+","+y+","+z+") \n Sa vitesse en x est :"+dx);		
		this.radius=radius;
	}

	
	private int radius;
	
	public boolean staysInTheField(Raquette r) {
		
		System.out.println("La position de la balle est : "+y+"celle de la raquette est :"+r.getY()+"Donc la balle reste dans le terrain,"+(Math.abs(r.getY()-this.getY())<r.getWidth()/2));
		
		return (Math.abs(r.getY()-this.getY())<r.getWidth()/2);
	}

	
	public int getRadius() {
		
		return radius;
	}
	

	public boolean isAtLimitsX(TableDePingPong d) {
		

		return (Math.abs(this.getX())>Math.abs(d.getX()/2-this.getRadius()));
	}

	
	public boolean isAtLimitsY(TableDePingPong d) {
		
		return (Math.abs(this.getY())>Math.abs(d.getY()/2-this.getRadius()));
	}

	
//	public boolean isAtLimitsZ(TableDePingPong d) {
//		
//		return (Math.abs(this.getZ())>Math.abs(d.getZ()-this.getRadius()));
//	}

	public String toString(){
		return x+" , "+y;
		
	}
	
}
