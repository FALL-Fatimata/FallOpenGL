package New;


public interface ObjectInterface {

	
	
	public double getX();
	public double getY();
	public double getHeight();
	public double getWidth();
	
	public void setX(double x);
	public void setY(double y);
	public void setWidth(double width);
	public void setHeight(double height);
	
	
	public void draw();
	public void update(int delta);
	
	public boolean intersects(ObjectInterface other);
	
	
	
}
