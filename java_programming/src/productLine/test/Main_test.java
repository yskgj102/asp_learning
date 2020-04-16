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
//			strategy = new MovingLineStrategyLogicTest();
			strategy = new MovingLineStrategy();
			/*Set pitch time here!*/
			pitchTime = 5.6;
			if (pitchTime > MIN_PITCH_TIME) {
				productLine = new ProductLine(strategy, pitchTime);
			} else {
				productLine = new ProductLine(strategy);
			}
//			productLine.addProducts("A", 1, 1);
//			productLine.addProducts("B", 10, 5);
//			
			productLine.addProducts("A", 4.2, 5);
			productLine.addProducts("B", 5, 3);
			
			productLine.addProducts("C", 10, 2);
//			productLine.addProducts("C", 3, 10);
//			productLine.addProducts("D", 4, 4);

			productLine.getCalculatedProductLine().output();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
