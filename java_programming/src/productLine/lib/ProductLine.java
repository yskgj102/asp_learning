package line.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class _ProductLine {
	private String name;
	private List<_Product> productList = new ArrayList<>();
	private BigDecimal pitchTime = null;
	private _ProductLine calculatedProductLine;

	public _ProductLine(String name) {
		this.name = name;
	}

	public _ProductLine(String name, BigDecimal pitchTime) {
		this.name = name;
		this.pitchTime = pitchTime;
	}

	public void setPitchTime(BigDecimal pitchTime) {
		this.pitchTime = pitchTime;
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

	public BigDecimal getPitchTime() {
		return pitchTime;
	}

	public BigDecimal getTotalProductTime() {
		BigDecimal totalProductTime = new BigDecimal("0");

		for (int i = 0; i < productList.size(); i++) {
			totalProductTime = totalProductTime.add(productList.get(i).getTime());
		}
		return totalProductTime;
	}

	public BigDecimal getEmptyTime() {
		if (pitchTime != null) {
			return BigDecimal.valueOf(productList.size() + 1).multiply(pitchTime).subtract(getTotalProductTime());
		} else {
			return null;
		}
	}

	public _ProductLine getCalculatedProductLine() {
		calculatedProductLine=new _ProductLine("calculated"+name,pitchTime);


		return calculatedProductLine;

	}
}
