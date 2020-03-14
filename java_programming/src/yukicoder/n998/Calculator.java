package yukicoder.n998;

import java.util.ArrayList;

public class Calculator {
	Calculator() {

	}

	public static boolean judgeContinuousNumber(InputNumbers in) {
		boolean isContinuous=false;

		ArrayList<Integer> inList=new ArrayList<>();
		inList.add(in.getA());
		inList.add(in.getB());
		inList.add(in.getC());
		inList.add(in.getD());
		inList.sort(null);

		isContinuous=inList.get(1)-inList.get(0)==1&&inList.get(2)-inList.get(1)==1&&inList.get(3)-inList.get(2)==1;

		return isContinuous;

	}

	public static void main(String[] args) {
		InputNumbers in=new InputNumbers(1,2,4,3);

		System.out.println(Calculator.judgeContinuousNumber(in));
	}
}
