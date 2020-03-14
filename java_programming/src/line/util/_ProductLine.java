package line.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class _ProductLine {
	private String name;
	private List<_Product> productList = new ArrayList<>();

	public _ProductLine(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void addProduct(String name, BigDecimal time) {
		_Product product = new _Product(name, time);
		this.productList.add(product);
	}

	public void addProducts(String name, BigDecimal time, int quantity) {
		_Product product = new _Product(name, time);
		for (int i = 0; i < quantity; i++) {
			this.productList.add(product);
		}
	}
}
