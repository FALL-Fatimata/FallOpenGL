package New;

import java.awt.Rectangle;



public abstract class Objects implements ObjectInterface {

	protected double x,y,width,height;
	protected Rectangle rect= new Rectangle();
	
	public Objects(double x, double y,double width,double height) {
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
	}


	@Override
	public void setX(double x) {
		this.x=x;
	}

	@Override
	public void setY(double y) {
		this.y=y;
	}

	@Override
	public void setWidth(double width) {
		this.width=width;
	}

	@Override
	public void setHeight(double height) {
		this.height=height;

	}

	@Override
	public double getX() {
		
		return x;
	}

	@Override
	public double getY() {
	
		return y;
	}

	@Override
	public double getHeight() {
		
		return height;
	}

	@Override
	public double getWidth() {
		
		return width;
	}

	@Override
	public boolean intersects(ObjectInterface other) {
		rect.setBounds((int)x,(int) y, (int)width,(int) height);
		return rect.intersects(other.getX(),other.getY(),other.getWidth(),other.getHeight());
	}

}
