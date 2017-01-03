package Question7_6;

// Graph point
public class GraphPoint {
	public double x;
	public double y;

	public GraphPoint(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public String toString(){
		return "(" + x + ", " + y + ")";
	}
}