package line.util;

import java.math.BigDecimal;

public class Test {

	public static void main(String[] args) {
		//_ProductLine movingLine = new _ProductLine("movingLine", new BigDecimal("5.6"));
		_ProductLine movingLine = new _ProductLine("movingLine");
		movingLine.addProducts("A", new BigDecimal("4.2"), 25);
		movingLine.addProducts("B", new BigDecimal("5"), 15);
		movingLine.addProducts("C", new BigDecimal("10"), 10);

		output(movingLine);
	}

	public static void output(_ProductLine line) {
		System.out.println("Line Name:" + line.getName());
		System.out.println("Pitch Time:" + line.getPitchTime());
		System.out.println("Enpty Time:" + line.getEmptyTime());
	}
}
