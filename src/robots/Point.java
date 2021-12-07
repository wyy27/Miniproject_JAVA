package robots;

import java.util.Random;

public class Point {
	private static Random random = new Random(0);
	private double x,y;
	
	public Point() {
		x=random.nextDouble();
		y=random.nextDouble();
	}
	
	public Point(double x, double y) {
		this.x=x;
		this.y=y;
	}
	
	public double get_x() { return x; }
	
	public double get_y() { return y; }
	
	public double distance(Point p) {
		double d = Math.hypot(p.get_x()-x, p.get_y()-y);
		return d;
	}
	
	public Point move(Point cible, double d) {
		Point result;
		double d_this_cible = this.distance(cible);
		
		if (d_this_cible==0 || d_this_cible<=d) {
			result = cible;
		} else {
			x += ( d / d_this_cible )*(cible.x - this.x);
			y += ( d / d_this_cible )*(cible.y - this.y);
			result = this;
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		Point a = new Point(0.3,0.4);
		Point b = new Point(0.3,0.7);
		Point res = a.move(b, 0.08);
		
		System.out.println("La distance entre deux points: "+a.distance(b));
		System.out.println("Le point suivante: ( "+ res.get_x() +" , "+ res.get_y()+" )");
	}

}
