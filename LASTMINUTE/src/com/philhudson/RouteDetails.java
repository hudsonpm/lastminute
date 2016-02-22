package com.philhudson;

import java.text.DecimalFormat;
import java.util.Map;

public class RouteDetails
{

	private String airline;
	private double basePrice;
	private int adults;
	private int children;
	private int infants;
	private int daysBeforeDeparture;
	private double calculatedCost;
	
	public RouteDetails()
	{
		airline = null;
		basePrice = 0.0D;
		calculatedCost = 0.0D;
	}

	public String getAirline()
	{
		return airline;
	}
	
	public String getIATACode()
	{
		return airline.length() == 6 ? airline.substring(0, 2) : null;
	}
	
	public void setAirline(String airline)
	{
		this.airline = airline;
	}

	public double getBasePrice()
	{
		return basePrice;
	}

	public void setBasePrice(double basePrice)
	{
		this.basePrice = basePrice;
	}

	public void setPassengers(int _adults, int _children, int _infants)
	{
		this.adults = _adults;
		this.children = _children;
		this.infants = _infants;
	}

	public void setDaysBeforeDeparture(int _daysBeforeDeparture)
	{
		this.daysBeforeDeparture = _daysBeforeDeparture;
	}

	public void setCalculatedCost(Map<String, Double> infantPrices)
	{
		double percentageOfBasePrice;
		
		if(daysBeforeDeparture > 30)
		{
			percentageOfBasePrice = 0.8D;
		} else if(daysBeforeDeparture <= 30 && daysBeforeDeparture >= 16)
		{
			percentageOfBasePrice = 1.0D;
		} else if(daysBeforeDeparture <= 15 && daysBeforeDeparture >= 3)
		{
			percentageOfBasePrice = 1.2D;
		} else
			percentageOfBasePrice = 1.5D;
		
		double adultAndChildCost = percentageOfBasePrice * basePrice * ((double) adults + 0.67 * (double) children );
		double infantCost = infantPrices.containsKey(getIATACode()) ? ((double) infants * infantPrices.get(getIATACode())) : 0.0D ; 
				
		calculatedCost = adultAndChildCost + infantCost;
	}
	
	public double getCalculatedCost()
	{
		return calculatedCost;
	}

	public String getFormattedCalculatedCost()
	{
		DecimalFormat myFormatter = new DecimalFormat("#,###.00");
		return myFormatter.format(calculatedCost);
	}
}