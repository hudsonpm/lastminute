package com.philhudson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Map;

public class DataLoader
{
	public DataLoader()
	{
	}
	
	public Map<FlightRoute, ArrayList<RouteDetails>> loadFlightData(String fileName) throws IOException
	{
		Map<FlightRoute, ArrayList<RouteDetails>> flightMap = new TreeMap<FlightRoute, ArrayList<RouteDetails>>();
		
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		
		File file = new File(fileName);
		
		try
		{
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			String line = null;
			
			boolean headerline = true;
			
			while((line = bufferedReader.readLine()) != null)
			{
				if(headerline)
				{
					headerline = false;
					continue;
				}
				
				String[] flightLine = line.split(",");
				if(flightLine.length != 4) continue;
				
				FlightRoute flightRoute = new FlightRoute();
				RouteDetails routeDetails = new RouteDetails();
				
				flightRoute.setOrigin(flightLine[0]);
				flightRoute.setDestination(flightLine[1]);
				routeDetails.setAirline(flightLine[2]);
				routeDetails.setBasePrice(new Double(flightLine[3]));
				
				if(flightMap.containsKey(flightRoute))
				{
					ArrayList<RouteDetails> routeList = flightMap.get(flightRoute);
					routeList.add(routeDetails);
				} else
				{
					ArrayList<RouteDetails> routeList = new ArrayList<RouteDetails>();
					routeList.add(routeDetails);
					flightMap.put(flightRoute, routeList);
				}
				
			}
			
			return flightMap;
		}
		finally
		{
			try
			{
				if(fileReader != null) fileReader.close();
				if(bufferedReader != null) bufferedReader.close();
			} catch(IOException e) 
			{
				throw new IOException(e);
			}
			
		}
	}
	
	public Map<String, Double> loadInfantPrices(String fileName) throws IOException
	{
		Map<String, Double> infantPriceMap = new HashMap<String, Double>();
		
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		
		File file = new File(fileName);
		
		try
		{
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			String line = null;
			
			boolean headerline = true;
			
			while((line = bufferedReader.readLine()) != null)
			{
				if(headerline)
				{
					headerline = false;
					continue;
				}
				
				String[] infantPriceLine = line.split(",");
				if(infantPriceLine.length != 2) continue;
				
				infantPriceMap.put(infantPriceLine[0],Double.parseDouble(infantPriceLine[1]));
			}
			
			return infantPriceMap;
		}
		finally
		{
			try
			{
				if(fileReader != null) fileReader.close();
				if(bufferedReader != null) bufferedReader.close();
			} catch(IOException e) 
			{
				throw new IOException(e);
			}
			
		}
	}
}
