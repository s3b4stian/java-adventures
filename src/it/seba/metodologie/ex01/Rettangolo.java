package it.seba.metodologie.ex01;

/**
 * Rectangle
 * 
 * @author Sebastian Rapetti
 */
public class Rettangolo {

	/**
	 * x coordinate of the north west point
	 */
	private int xNw;

	/**
	 * y coordinate of the north west point
	 */
	private int yNw;

	/**
	 * Rectangle height
	 */
	private int height;

	/**
	 * Rectangle length
	 */
	private int length;

	/**
	 * Class constructor
	 *
	 * @param x coordinate of the north west point
	 * @param y coordinate of the north west point
	 * @param h height
	 * @param l length
	 */
	public Rettangolo(int x, int y, int l, int h) {
		xNw = x;
		yNw = y;
		height = h;
		length = l;
	}

	/**
	 * Translate the rectangle to new point
	 *
	 * @param x new coordinate of the north west point
	 * @param y new coordinate of the worth west point
	 */
	public void trasla(int x, int y) {
		xNw = x;
		yNw = y;
	}

	@Override
	public String toString() {
		// calculate south east point
		int xSe = xNw + length;
		int ySe = yNw + height;

		return "(" + xNw + ", " + yNw + ")->(" + xSe + ", " + ySe + ")";
	}
}
