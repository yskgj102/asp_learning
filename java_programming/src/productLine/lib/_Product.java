package productLine.lib;

import java.io.Serializable;
import java.math.BigDecimal;

public class _Product implements Cloneable, Serializable {
	private String name;
	private BigDecimal time;

	public _Product(String name, BigDecimal time) {
		this.name = name;
		this.time = time;
	}

	public _Product(String name, double time) {
		this.name = name;
		this.time = BigDecimal.valueOf(time);
	}

	public String getName() {
		return this.name;
	}

	public BigDecimal getTime() {
		return this.time;
	}

	public boolean equals(_Product product) {
		boolean eq_flg = false;
		eq_flg = this.name.equals(product.getName()) && this.time.compareTo(product.getTime()) == 0;
		return eq_flg;
	}
}