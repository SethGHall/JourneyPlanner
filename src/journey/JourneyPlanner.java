package journey;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;


/**
 *
 * @author Seth
 */
public class JourneyPlanner {
    
    private final Map<String, Set<BusTrip>> locationMap;
    
    
    public JourneyPlanner()
    {
        locationMap = new LinkedHashMap<>();  
    }
    
    public boolean add(BusTrip c)
    {
        if(!locationMap.containsKey(c.getDepartLocation()))
        {
            Set<BusTrip> set = new HashSet<>();
            set.add(c);
            locationMap.put(c.getDepartLocation(),set);
            return true;
        }
        else
        {
            Set<BusTrip> set = locationMap.get(c.getDepartLocation());
            return set.add(c);
        }
    }

    public List<BusJourney> getPossibleJourneys(String origin, LocalTime originTime, String destinatinon, LocalTime destinationTime)
    {        
        BusJourney current = new BusJourney();
        List<BusJourney> list = new ArrayList<>();
       
        findPaths(origin, originTime, destinatinon, destinationTime, current, list);
        return list;
    }
    
    private void findPaths(String departLocation, LocalTime departTime, String destination, LocalTime destinationTime, BusJourney current, List<BusJourney> list)
    {
        if(departTime.compareTo(destinationTime) <= 0)
        {
            if(destination.equals(current.getDestination()))
            {
                list.add(current.cloneJourney());
            }
            else
            {
                 if(locationMap.containsKey(departLocation))
                 {
                    Set<BusTrip> departures = locationMap.get(departLocation);
                    for(BusTrip bus:departures)
                    {
                        if(bus.getDepartTime().compareTo(departTime) >= 0)
                        {
                            if(current.addBus(bus))
                            {   findPaths(current.getDestination(), current.getDestinationTime(), destination, destinationTime, current, list);
                                current.removeLastTrip();
                            }
                        }
                    }
                 }
            }
        }
    }  
}
