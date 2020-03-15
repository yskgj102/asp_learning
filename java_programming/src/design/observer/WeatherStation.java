package design.observer;

public class WeatherStation {
	public static void main(String[] args) {
		WeatherData weatherData=new WeatherData();
		CurrentConditionsDisplay currentdisplay=new CurrentConditionsDisplay(weatherData);
//		StatisticsDisplay cstatisticstdisplay=new StatisticsDisplay(weatherData);
		weatherData.setMesurements(27, 65, 30.4f);
		weatherData.setMesurements(31, 61, 30.4f);

	}

}
