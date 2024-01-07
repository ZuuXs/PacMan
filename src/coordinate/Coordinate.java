package coordinate;

public class Coordinate {
    private int y;
    private int x;
    
    public Coordinate(int x, int y) {
        this.y = y;
        this.x = x;
    }

	public int getY() {
		return y;
	}

    public int getX() {
        return x;
    }
    
    public Coordinate boardToPosXY() {
        return new Coordinate(this.x * 20, this.y * 20);
    }

    public Coordinate posXYToBoard() {
        return new Coordinate(this.x / 20, this.y / 20);
    }

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}    
}
