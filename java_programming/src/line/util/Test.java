package line.util;

import java.math.BigDecimal;

public class Test {

	public static void main(String[] args) {
		_ProductLine movingLine=new _ProductLine("movingLine");
		movingLine.addProducts("A", new BigDecimal("4.2"),25);
		movingLine.addProducts("B", new BigDecimal("5"),15);
		movingLine.addProducts("C", new BigDecimal("10"),10);

	}

	public static void output() {
		
	}
}
