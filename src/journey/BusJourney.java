package journey;


import java.time.LocalTime;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Seth
 */
public class BusJourney {
    
    private LinkedList<BusTrip> tripList;
    
    
    public BusJourney()
    {
        tripList = new LinkedList<>();
    }
    
    
    public BusJourney(List<BusTrip> list)
    {   this();
        for(BusTrip c:list)
            this.addBus(c);
    }
    
    public final boolean addBus(BusTrip newBus)
    {
       if(tripList.isEmpty() || newBus.getDepartLocation().equals(getDestination()))
       {    
            if(!containsLocation(newBus.getArrivalLocation()))
            {   tripList.add(newBus);
                return true;
            }
       }
       return false;
    }
    public boolean containsLocation(String location)
    {
        for(BusTrip trip:tripList)
        {
            if(trip.getArrivalLocation().equals(location) || trip.getDepartLocation().equals(location))
                return true;
        }
        return false;
    }
    public String getDestination()
    {
        if(!tripList.isEmpty())
        {
            return tripList.getLast().getArrivalLocation();
        }
        else return null;
    }
    public LocalTime getDestinationTime()
    {
        if(!tripList.isEmpty())
        {
            return tripList.getLast().getArrivalTime();
        }
        else return null;
    }
    public int getNumberOfBusTrips()
    {
        return tripList.size();
    }
    public String getOrigin()
    {
        if(!tripList.isEmpty())
        {
            return tripList.getFirst().getDepartLocation();
        }
        else return null;
    }
    public LocalTime getOriginTime()
    {
        if(!tripList.isEmpty())
        {
            return tripList.getFirst().getDepartTime();
        }
        else return null;
    }
    
    public boolean removeLastTrip()
    {
        if(!tripList.isEmpty())
        {
            tripList.removeLast();
            return true;
        }
        return false;
    }
    
    public float getTotalJourneyCost()
    {
        float total = 0;
        for(BusTrip c:tripList)
            total+=c.getCost();
        return total;
    }
    public BusJourney cloneJourney()
    {
        return new BusJourney(tripList);
    }
    @Override
    public String toString()
    {   String journey = "TOTAL COST: $"+getTotalJourneyCost()+"!!!\n";
        for(int i=0;i<tripList.size();i++)
        {   BusTrip trip = tripList.get(i);
            journey += trip.toString()+"\n";
        }
        return journey;
    }
}
