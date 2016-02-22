package com.philhudson;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class RouteCostFinderTests
{
	
	@Before
	public void initialise()
	{
		try
		{
			RouteCostFinder.loadInputDataFiles();
		} catch (RouteCostFinderException e)
		{
			System.out.println("An error occurred loading the input data from the resources folder, and the program is terminating:");
			e.printStackTrace();
			System.exit(0);
		}
	}	
	
	@Test
	public void allTests()
	{
		RouteCostFinder rcf = null;
		ArrayList<RouteDetails> routeDetailsList = null;
		int foundFlight;
		
		// Scenario 1: 1 adult, 30 days to the departure date, flying AMS -> FRA
		
		rcf = new RouteCostFinder("AMS", "FRA", 31, 1, 0, 0);
		routeDetailsList = rcf.findFlights();
		rcf.outputRoutesToConsole(routeDetailsList);
		assertEquals("Number of routes must be 3", 3, routeDetailsList.size());
		foundFlight = 0;
		
		for(RouteDetails routeDetail : routeDetailsList)
		{
			if(routeDetail.getAirline().equals("TK2372")) assertEquals("Flight " + ++ foundFlight + ": TK2372 cost must be 157.60", "157.60", routeDetail.getFormattedCalculatedCost());
			if(routeDetail.getAirline().equals("TK2659")) assertEquals("Flight " + ++ foundFlight + ": TK2659 cost must be 198.40", "198.40", routeDetail.getFormattedCalculatedCost());
			if(routeDetail.getAirline().equals("LH5909")) assertEquals("Flight " + ++ foundFlight + ": LH5909 cost must be 90.40", "90.40", routeDetail.getFormattedCalculatedCost());
		}
		assertEquals("Found correct 3 flights", 3, foundFlight);
		
		// Scenario 2: 2 adults, 1 child, 1 infant, 15 days to the departure date, flying LHR -> IST
		
		rcf = new RouteCostFinder("LHR", "IST", 15, 2, 1, 1);
		routeDetailsList = rcf.findFlights();
		rcf.outputRoutesToConsole(routeDetailsList);
		assertEquals("Number of routes must be 2", 2, routeDetailsList.size());
		foundFlight = 0;
		
		for(RouteDetails routeDetail : routeDetailsList)
		{
			if(routeDetail.getAirline().equals("TK8891")) assertEquals("Flight " + ++ foundFlight + ": TK8891 cost must be 806.00", "806.00", routeDetail.getFormattedCalculatedCost());
			if(routeDetail.getAirline().equals("LH1085")) assertEquals("Flight " + ++ foundFlight + ": LH1085 cost must be 481.19", "481.19", routeDetail.getFormattedCalculatedCost());
		}
		assertEquals("Found correct 2 flights", 2, foundFlight);
		
		// Scenario 3: 1 adult, 2 children, 2 days to the departure date, flying BCN -> MAD
		
		rcf = new RouteCostFinder("BCN", "MAD", 2, 1, 2, 0);
		routeDetailsList = rcf.findFlights();
		rcf.outputRoutesToConsole(routeDetailsList);
		assertEquals("Number of routes must be 2", 2, routeDetailsList.size());
		foundFlight = 0;
		
		for(RouteDetails routeDetail : routeDetailsList)
		{
			if(routeDetail.getAirline().equals("IB2171")) assertEquals("Flight " + ++ foundFlight + ": IB2171 cost must be 909.09", "909.09", routeDetail.getFormattedCalculatedCost());
			if(routeDetail.getAirline().equals("LH5496")) assertEquals("Flight " + ++ foundFlight + ": LH5496 cost must be 1,028.43", "1,028.43", routeDetail.getFormattedCalculatedCost());
		}
		assertEquals("Found correct 2 flights", 2, foundFlight);
		
		// Scenario 4: CDG -> FRA
		
		rcf = new RouteCostFinder("CDG", "FRA", 0, 0, 0, 0);
		routeDetailsList = rcf.findFlights();
		rcf.outputRoutesToConsole(routeDetailsList);
		assertEquals("No routes found", true, routeDetailsList.isEmpty());
				
		
	}
}
