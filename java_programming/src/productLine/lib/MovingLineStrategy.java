package productLine.lib;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import productLine.util.Calculator;

public class MovingLineStrategy extends AbstractLineStrategy {
	private static final int HUGE_NUMBER = 100000;
	private static final BigDecimal TINY_VALUE = BigDecimal.valueOf(0.00000000000000000001);

	@Override
	protected void setLineName() {
		super.lineName = "Moving Line";
	}

	@Override
	public ProductLine getCalculateProductdLine(ProductLine productLine) throws Exception {
		ProductLine calculatedProductLine = null;
		/*Pass ALGORITHM1 if fixing the pitch time*/
		if (productLine.getPitchTime() != null) {
			if (productLine.getPitchTime().compareTo(productLine.getMinimumPitchTime()) == -1) {
				throw new Exception(
						"" + "" + productLine.getPitchTime() + "eGÝ" + productLine.getMinimumPitchTime() + "" + "");
			}
			calculatedProductLine = executeALGORITHM1(productLine);
		}
		/*Pass ALGORITHM2 if not fixing the pitch time*/
		else {
			calculatedProductLine = executeALGORITHM2(productLine);
			;
		}
		if (calculatedProductLine != null
				&& calculatedProductLine.getProductList().size() == productLine.getProductList().size()) {
			System.out.println("Success" + productLine.getLineName());
		} else {
			System.out.println("Fail" + productLine.getLineName());
		}
		return calculatedProductLine;
	}

	private static ProductLine executeALGORITHM1(ProductLine productLine) throws ClassNotFoundException, IOException {
		ProductLine calculatedProductLine = null;
		if (productLine.getPitchTime() != null) {
			//List<_Product> tmpProductList = (ArrayList<_Product>) ((ArrayList<_Product>) productLine.getProductList()).clone();
			List<_Product> tmpProductList = deepcopy(productLine.getProductList());
			ArrayList<_Product> calculatedProductList = new ArrayList<>();
			ArrayList<Integer> calculatedEmptyList = new ArrayList<>();
			calculatedProductLine = new ProductLine(productLine.getLineStrategy(), productLine.getPitchTime());
			int roopCounter = 0;
			boolean emp_add_flg = false;
			for (;;) {
				roopCounter++;
				BigDecimal sufficiencyRate = BigDecimal.valueOf(HUGE_NUMBER);
				int i_rem = HUGE_NUMBER;
				int emp_num_rem = 0;
				for (int i = 0; i < tmpProductList.size(); i++) { // qÿ4ãa`ELïÓ×Ôó5kâï3£ÞmÚH
					calculatedProductList.add(tmpProductList.get(i));
					int emp_num = 0;
					if (calculatedEmptyList.size() >= 1) {
						if (emp_add_flg) {
							emp_num = calculatedEmptyList.get(calculatedEmptyList.size() - 1) + 1;
							emp_add_flg = false;
						} else {
							emp_num = calculatedEmptyList.get(calculatedEmptyList.size() - 1);
						}
					}
					calculatedEmptyList.add(emp_num);
					if (Calculator.isAvailableProductList(calculatedProductList, calculatedEmptyList,
							productLine.getPitchTime())) {
						if (sufficiencyRate.compareTo(Calculator.getSufficiencyRateOfX(calculatedProductList,
								productLine, tmpProductList.get(i))) >= 0) {
							sufficiencyRate = Calculator.getSufficiencyRateOfX(calculatedProductList, productLine,
									tmpProductList.get(i));
							i_rem = i;
							emp_num_rem = emp_num;
						}
					}
					calculatedProductList.remove(calculatedProductList.size() - 1);
					calculatedEmptyList.remove(calculatedEmptyList.size() - 1);
				} // for(tmpProductList)
				if (i_rem != HUGE_NUMBER) {
					calculatedProductList.add(tmpProductList.get(i_rem));
					calculatedEmptyList.add(emp_num_rem);
					tmpProductList.remove(i_rem);
				} else {
					emp_add_flg = true;
				}
				if (tmpProductList.size() == 0 | roopCounter >= HUGE_NUMBER) {
					break;
				}
			} // for(;;)
			calculatedProductLine.addProductList(calculatedProductList);
			calculatedProductLine.addEmptyList(calculatedEmptyList);
		}
		return calculatedProductLine;
	}

	private static ProductLine executeALGORITHM2(ProductLine productLine) throws ClassNotFoundException, IOException {
		ProductLine calculatedProductLine = null;
		int roop_counter = 0;
		BigDecimal dt = BigDecimal.valueOf(0);
		for (;;) {
			System.out.println("roopcounter=" + roop_counter);
			calculatedProductLine = new ProductLine(productLine.getLineStrategy(),
					productLine.getIdlePitchTime().add(dt));
			calculatedProductLine.addProductList(productLine.getProductList());
			calculatedProductLine.addEmptyList(productLine.getEmptyList());
			calculatedProductLine = executeALGORITHM1(calculatedProductLine);
			dt = dt.add(TINY_VALUE);
			roop_counter++;
			if (calculatedProductLine.getEmptyPitchNumber() == 0 || roop_counter >= HUGE_NUMBER) {
				break;
			}
		}
		return calculatedProductLine;
	}

	@SuppressWarnings("unchecked")
	public static <T> T deepcopy(T obj) throws IOException, ClassNotFoundException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		new ObjectOutputStream(baos).writeObject(obj);
		return (T) new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray())).readObject();
	}
}