package productLine.lib;

import java.io.IOException;

public interface Strategy {
	abstract ProductLine getCalculateProductdLine(ProductLine productLine)
			throws ClassNotFoundException, IOException, Exception;
}