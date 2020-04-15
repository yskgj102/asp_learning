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
			double pitchTime = MIN_PITCH_TIME; // Ç¾³é9±~
			strategy = new MovingLineStrategy(); // âõyñâ7
			//	pitchTime = 123.49306433273644388398;
			// +ÿ÷É(ýªX
			if (pitchTime > MIN_PITCH_TIME) {
				productLine = new ProductLine(strategy, pitchTime);
			} else {
				productLine = new ProductLine(strategy);
			} // »z#×õy
			productLine.addProducts("A", 1, 100);
			productLine.addProducts("B", 18, 50);
			productLine.addProducts("C", 20, 100);
			productLine.addProducts("D", 120, 400);
			productLine.addProducts("E", 158, 30);
			productLine.addProducts("F", 167, 50);
			productLine.addProducts("G", 197, 50);
			productLine.addProducts("H", 1999, 10);
			productLine.addProducts("I", 2000, 2); // f1ÀuÆ

			productLine.getCalculatedProductLine().output();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
