package productLine.test;

import productLine.lib.AbstractLineStrategy;
import productLine.lib.MovingLineStrategy;
import productLine.lib.ProductLine;

public class Main_test {
	public static void main(String[] args) {
		final double MIN_PITCH_TIME = 0;
		try {
			AbstractLineStrategy strategy = null;
			ProductLine productLine = null;
			double pitchTime = MIN_PITCH_TIME;
			strategy = new MovingLineStrategy();
			/*Set pitch time here!*/
			//pitchTime = 1275.26694045174;
			if (pitchTime > MIN_PITCH_TIME) {
				productLine = new ProductLine(strategy, pitchTime);
			} else {
				productLine = new ProductLine(strategy);
			}
			productLine.addProducts("A", 1, 9);
			productLine.addProducts("B", 2, 5);
			productLine.addProducts("C", 3, 10);
			productLine.addProducts("D", 4, 4);
			productLine.addProducts("E", 5, 30);
			productLine.addProducts("F", 6, 70);
			productLine.addProducts("G", 7, 5);
			productLine.addProducts("H", 8, 10);
			productLine.addProducts("I", 9, 2);
			productLine.getCalculatedProductLine().output();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
