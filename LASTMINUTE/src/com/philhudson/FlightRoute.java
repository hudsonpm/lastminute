package com.philhudson;

public class FlightRoute implements Comparable<FlightRoute>
{
	
	private String origin;
	private String destination;
	
	public FlightRoute()
	{
		origin = null;
		destination = null;
	}

	public String getOrigin()
	{
		return origin;
	}

	public void setOrigin(String origin)
	{
		this.origin = origin;
	}

	public String getDestination()
	{
		return destination;
	}

	public void setDestination(String destination)
	{
		this.destination = destination;
	}

	@Override
	public int compareTo(FlightRoute anotherRoute)
	{
		return (origin + destination).compareTo(anotherRoute.getOrigin() + anotherRoute.getDestination());
	}
	
}
