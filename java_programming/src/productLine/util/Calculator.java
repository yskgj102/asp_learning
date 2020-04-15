package productLine.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import productLine.lib.ProductLine;
import productLine.lib._Product;

public class Calculator {
	private final static int SCALE_INT = 20;

	public static void sortProductListByName(List<_Product> productList) {
		Collections.sort(productList, new Comparator<_Product>() {
			public int compare(_Product p1, _Product p2) {
				return p1.getName().compareTo(p2.getName());
			}
		});
	}

	public static void sortProductListByTimeDesc(List<_Product> productList) {
		Collections.sort(productList, new Comparator<_Product>() {
			public int compare(_Product p1, _Product p2) {
				return -p1.getTime().compareTo(p2.getTime());
			}
		});
	}

	/**	 * É³#Ó	 * @param productList	 * @return	 * @throws IOException	 * @throws ClassNotFoundException	 */
	public static BigDecimal getSafedPitchTime(List<_Product> productList) throws ClassNotFoundException, IOException {
		List<_Product> tmpProductList = deepcopy(productList);
		/* ÀFF*/ Calculator.sortProductListByTimeDesc(tmpProductList);
		BigDecimal max_pitch_time = BigDecimal.valueOf(0);
		BigDecimal total_product_time_i = BigDecimal.valueOf(0);
		for (int i = 0; i < tmpProductList.size(); i++) {
			total_product_time_i = total_product_time_i.add(tmpProductList.get(i).getTime());
			BigDecimal tmp_pitch_time_i = total_product_time_i.divide(BigDecimal.valueOf((i + 1) + 1), SCALE_INT,
					RoundingMode.HALF_UP);
			if (max_pitch_time.compareTo(tmp_pitch_time_i) == -1) {
				max_pitch_time = tmp_pitch_time_i;
			}
		}
		return max_pitch_time;
	}

	public static BigDecimal getMinimumPitchTime(List<_Product> productList)
			throws ClassNotFoundException, IOException {
		BigDecimal min_pitch_time = BigDecimal.valueOf(0);
		List<_Product> tmpProductList = deepcopy(productList);
		/* ÖZJÚ*/ Calculator.sortProductListByTimeDesc(tmpProductList);
		min_pitch_time = tmpProductList.get(0).getTime().divide(BigDecimal.valueOf(2), SCALE_INT, RoundingMode.HALF_UP);
		return min_pitch_time;
	}

	public static int getTotalProductionQuantity(List<_Product> productList) {
		return productList.size();
	}

	public static BigDecimal getTotalProductionTime(List<_Product> productList) {
		BigDecimal total_production_time = new BigDecimal("0");
		for (int i = 0; i < getTotalProductionQuantity(productList); i++) {
			total_production_time = total_production_time.add(productList.get(i).getTime());
		}
		return total_production_time;
	}

	public static BigDecimal getEmptyTime(ProductLine line) {
		BigDecimal pitchTime = line.getPitchTime();
		List<_Product> productList = line.getProductList();
		List<Integer> emptyList = line.getEmptyList();
		if (pitchTime != null && emptyList.size() != 0) {
			/* (N+M_n+1)T-S_N*/ return pitchTime
					.multiply(BigDecimal.valueOf(productList.size() + 1 + emptyList.get(emptyList.size() - 1)))
					.subtract(getTotalProductionTime(productList));
		}
		return null;
	}

	public static BigDecimal getIdlePitchTime(List<_Product> productList) {
		return getTotalProductionTime(productList).divide(BigDecimal.valueOf(productList.size() + 1), SCALE_INT,
				RoundingMode.HALF_UP);
	}

	public static BigDecimal getAveragePitchTime(List<_Product> productList) {
		if (productList.size() != 0) {
			return getTotalProductionTime(productList).divide(BigDecimal.valueOf(productList.size()), SCALE_INT,
					RoundingMode.HALF_UP);
		} else {
			return null;
		}
	}

	/**	 * J»Ãí	 * SN<=(N+MN+1)T¢.ÆÊ/ïå	 * @param line	 * @return AµÐÝtrue	 */
	public static boolean isAvailableProductLine(ProductLine line) {
		return isAvailableProductList(line.getProductList(), line.getEmptyList(), line.getPitchTime());
	}

	/**	 *	 * Sn<=(n+Mn+1)T  &ølq}	 * @param productList	 * @param emptyList	 * @param pitchTime	 * @return	 */
	public static boolean isAvailableProductList(List<_Product> productList, List<Integer> emptyList,
			BigDecimal pitchTime) {
		if (productList.size() == 0 || emptyList.size() == 0) {
			System.out.println("WÓaqfÆÕ");
			return true;
		}
		BigDecimal leftSideEq = getTotalProductionTime(productList);
		BigDecimal rightSideEq = BigDecimal.valueOf(getTotalProductionQuantity(productList)
				+ emptyList.get(getTotalProductionQuantity(productList) - 1) + 1).multiply(pitchTime);
		/* sb<Mê»-1*/ int compare_int = leftSideEq.compareTo(rightSideEq);
		return compare_int <= 0;
	}

	/**	 * ý©XbE	 * @param calculationProductList	 * @param productLine	 * @param productName	 * @return	 */
	public static BigDecimal getSufficiencyRateOfX(List<_Product> calculationProductList, ProductLine productLine,
			_Product product) {
		if (calculationProductList.size() == 1) {
			return BigDecimal.valueOf(0);
		}
		int theNumberOfProduct = calculationProductList.size();
		int theNumberOfProductFromCalculationgProductList = getQuantityOfSameProduct(calculationProductList, product);
		return BigDecimal.valueOf(theNumberOfProductFromCalculationgProductList - 1).divide(
				getRateOfTheProduct(productLine, product).multiply(BigDecimal.valueOf(theNumberOfProduct - 1)),
				SCALE_INT, RoundingMode.HALF_UP);
	}

	/**	 *	 */
	public static BigDecimal getRateOfTheProduct(ProductLine productLine, _Product product) {
		int theNumberOfProduct = productLine.getQuantityOfSameProduct(product);
		int theTptalProductNumber = productLine.getTotalProductionQuantity();
		return BigDecimal.valueOf(theNumberOfProduct).divide(BigDecimal.valueOf(theTptalProductNumber), SCALE_INT,
				RoundingMode.HALF_UP);
	}

	public static int getQuantityOfSameProduct(List<_Product> productList, _Product product) {
		int quantity = 0;
		for (int i = 0; i < productList.size(); i++) {
			if (productList.get(i).equals(product)) {
				quantity++;
			}
		}
		return quantity;
	}

	@SuppressWarnings("unchecked")
	public static <T> T deepcopy(T obj) throws IOException, ClassNotFoundException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		new ObjectOutputStream(baos).writeObject(obj);
		return (T) new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray())).readObject();
	}
}