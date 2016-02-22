package com.philhudson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class RouteCostFinder
{

	private static Map<FlightRoute, ArrayList<RouteDetails>> flightMap;
	private static Map<String, Double> infantPrices;
	
	private String originalAirport;
	private String destinationAirport;
	private int daysBeforeDeparture;
	private int adults;
	private int children;
	private int infants;
	
	public RouteCostFinder(String _originalAirport, String _destinationAirport, int _daysBeforeDeparture, int _adults, int _children, int _infants)
	{
		this.originalAirport = _originalAirport;
		this.destinationAirport = _destinationAirport;
		this.daysBeforeDeparture = _daysBeforeDeparture;
		this.adults = _adults;
		this.children = _children;
		this.infants = _infants;
	}
	
	public static void loadInputDataFiles() throws RouteCostFinderException
	{
		DataLoader dl = new DataLoader();
		
		flightMap = new TreeMap<FlightRoute, ArrayList<RouteDetails>>();
		infantPrices = new HashMap<String, Double>();
		
		try
		{
			flightMap = dl.loadFlightData("resources/flightdata.csv");
			infantPrices = dl.loadInfantPrices("resources/infantprices.csv");
		} catch (IOException e)
		{
			throw new RouteCostFinderException("An IOException occurred whilst attempting to load the data from the CSV file:", e);
		}
	}
	
	public ArrayList<RouteDetails> findFlights()
	{
		
		ArrayList<RouteDetails> outputRouteList = new ArrayList<RouteDetails>();
		
		FlightRoute inputFlightRoute = new FlightRoute();
		
		inputFlightRoute.setOrigin(originalAirport);
		inputFlightRoute.setDestination(destinationAirport);
		
		if(!flightMap.containsKey(inputFlightRoute))
		{
			outputRouteList.clear();
			return outputRouteList;
		}
		
		outputRouteList = flightMap.get(inputFlightRoute);
		
		for(RouteDetails outputRoute : outputRouteList)
		{
			outputRoute.setPassengers(adults, children, infants);
			outputRoute.setDaysBeforeDeparture(daysBeforeDeparture);
			outputRoute.setCalculatedCost(infantPrices);
		}
		
		return outputRouteList;
	}
	
	public void outputRoutesToConsole(ArrayList<RouteDetails> routeDetailsList)
	{
		System.out.println("--------------------------------------------------");
		System.out.println("Origin : " + originalAirport + "; Destination : " + destinationAirport);
		
		if(routeDetailsList.isEmpty())
		{
			System.out.println("No routes found\n");
			return;
		}
		
		int routeNumber = 0;
		
		for(RouteDetails routeDetail : routeDetailsList)
		{
			System.out.println(++routeNumber + ": " + routeDetail.getAirline() + "\t" + routeDetail.getFormattedCalculatedCost() + " €");
		}
		
		System.out.print("\n");
	}
	
}
