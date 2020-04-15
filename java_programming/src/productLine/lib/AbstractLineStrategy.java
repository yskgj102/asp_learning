package productLine.lib;

public abstract class AbstractLineStrategy implements Strategy {
	protected String lineName;

	public AbstractLineStrategy() {
		setLineName();
	}

	protected abstract void setLineName();

	public String getLineName() {
		return lineName;
	}

	public AbstractLineStrategy getLineStrategy() {
		return this;
	}
}