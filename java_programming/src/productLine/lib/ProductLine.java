
package productLine.lib;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import productLine.util.Calculator;

public class ProductLine {
	private AbstractLineStrategy lineStrategy = null;
	private BigDecimal pitchTime = null;
	private List<_Product> productList = new ArrayList<>();
	private List<Integer> emptyList = new ArrayList<>();/* M_n*/

	public ProductLine(AbstractLineStrategy strategy) {
		this.lineStrategy = strategy;
	}

	public ProductLine(AbstractLineStrategy strategy, double pitchTime) {
		this.lineStrategy = strategy;
		this.pitchTime = BigDecimal.valueOf(pitchTime);
	}

	public ProductLine(AbstractLineStrategy strategy, BigDecimal pitchTime) {
		this.lineStrategy = strategy;
		this.pitchTime = pitchTime;
	}

	public AbstractLineStrategy getLineStrategy() {
		return lineStrategy;
	}

	public ProductLine getCalculatedProductLine() throws Exception {
		return lineStrategy.getCalculateProductdLine(this);
	}

	public String getLineName() {
		return lineStrategy.getLineName();
	}

	public BigDecimal getPitchTime() {
		return pitchTime;
	}//

	public void setPitchTime(BigDecimal pitchTime) {//
		this.pitchTime = pitchTime;//
	}

	public List<Integer> getEmptyList() {
		return emptyList;
	}

	public int getEmptyPitchNumber() {
		if (emptyList.size() >= 1) {
			return emptyList.get(emptyList.size() - 1);
		}
		return 0;
	}

	public void addProduct(_Product _product) throws Exception {
		productList.add(_product);
		emptyList.add(0);
	}

	public void addProducts(_Product _product, int quantity) throws Exception {
		for (int i = 0; i < quantity; i++) {
			addProduct(_product);
		}
	}

	public void addProducts(String name, double time, int quantity) throws Exception {
		_Product _product = new _Product(name, time);
		addProducts(_product, quantity);
	}

	public void addProductList(List<_Product> productList) {
		this.productList.addAll(productList);
	}

	public void addEmptyProduct(Integer ii) {
		int previous_empty = emptyList.get(emptyList.size() - 1);
		for (int i = ii - emptyList.size() - 1; i < emptyList.size(); i++) {
			this.emptyList.add(i, previous_empty + 1);
		}
	}

	public void addEmptyList(List<Integer> emptyList) {
		this.emptyList.addAll(emptyList);
	}

	public List<_Product> getProductList() {
		return productList;
	}

	public int getTotalProductionQuantity() {
		return Calculator.getTotalProductionQuantity(productList);
	}

	public BigDecimal getTotalProductionTime() {
		return Calculator.getTotalProductionTime(productList);
	}

	public BigDecimal getEmptyTime() {
		return Calculator.getEmptyTime(this);
	}

	public BigDecimal getSafedPitchTime() throws ClassNotFoundException, IOException {
		return Calculator.getSafedPitchTime(productList);
	}

	public BigDecimal getIdlePitchTime() {
		return Calculator.getIdlePitchTime(productList);
	}

	public BigDecimal getMinimumPitchTime() throws ClassNotFoundException, IOException {
		return Calculator.getMinimumPitchTime(productList);
	}

	public BigDecimal getAveragePitchTime() {
		return Calculator.getAveragePitchTime(productList);
	}

	public int getQuantityOfSameProduct(_Product product) {
		int quantity = 0;
		for (int i = 0; i < productList.size(); i++) {
			if (productList.get(i).equals(product)) {
				quantity++;
			}
		}
		return quantity;
	}

	public void output() throws ClassNotFoundException, IOException {
		int productNum = getProductList().size();
		System.out.println("LineName:" + getLineName());
		System.out.println("PitchTime:" + getPitchTime());
		System.out.println("TotalProductionTime" + getTotalProductionTime());
		System.out.println("TotalProductionQuantity" + getTotalProductionQuantity());
		System.out.println("SafedPitchTime" + getSafedPitchTime());
		System.out.println("AveragePitchTime" + getAveragePitchTime());
		System.out.println("IdlePitchTime" + getIdlePitchTime());
		System.out.println("EmptyPitchNumber:" + getEmptyPitchNumber());

		System.out.println("EmptyList:" + getEmptyList());
		System.out.println("EmptyTime:" + getEmptyTime());
		System.out.println("i name time");
		for (int i_production = 0; i_production < productNum; i_production++) {
			_Product p = getProductList().get(i_production);
			Integer emptyNumber = 0;
			if (i_production != 0) {
				emptyNumber = getEmptyList().get(i_production) - getEmptyList().get(i_production - 1);
				for (int i = 0; i < emptyNumber; i++) {
					System.out.println("i name time");
				}
			}
			System.out.println((i_production + 1) + " " + p.getName() + " " + p.getTime());
		}
	}
}
