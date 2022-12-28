package journey;


import java.util.Comparator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Seth
 */
 public class JourneyComparator implements Comparator<BusJourney>
{

    @Override
    public int compare(BusJourney o1, BusJourney o2) {
        return o1.getDestinationTime().compareTo(o2.getDestinationTime());
    }

}