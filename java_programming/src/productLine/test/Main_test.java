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
			productLine.addProducts("A", 1, 1);
			productLine.addProducts("B", 15, 5);
			productLine.addProducts("C", 10, 1);
//			productLine.addProducts("D", 120, 40);
//			productLine.addProducts("E", 158, 30);
//			productLine.addProducts("F", 167, 5);
//			productLine.addProducts("G", 197, 50);
//			productLine.addProducts("H", 1999, 100);
//			productLine.addProducts("I", 2000, 200);
			productLine.getCalculatedProductLine().output();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
