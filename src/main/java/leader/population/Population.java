package leader.population;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import leader.util.Util;

public class Population
{   
    private List<Integer> _persons;
    
    public Population()
    {
        _persons = new ArrayList<>();
    }
    
    public int size()
    {
        return _persons.size();
    }
    
    public void clear()
    {
        _persons.clear();
    }
    
    public Integer addRandomPerson()
    {
        Integer person = new Integer( 0 );
        pack( Util.nextDouble() < 0.5? 0: 1, person, PopulationType.GENDER );
        pack( Util.nextInt( 8 ), person, PopulationType.AGE );
        _persons.add( person );
        return person;
    }
    
    public List<Integer> addRandomPeople( int count )
    {
        List<Integer> people = new ArrayList<>();
        for( int i = 0; i < count; ++i )
        {
            people.add( addRandomPerson() );
        }
        return people;
    }
        
    public PopulationStatistics generateStats()
    {
        PopulationStatistics stats = new PopulationStatistics();
        for( Integer person: _persons )
        {
            Map<PopulationType, Integer> attrs = decode( person );
            stats.inc( PopulationType.MALE,       (attrs.get( PopulationType.GENDER ) == PopulationType.MALE?       1: 0) );
            stats.inc( PopulationType.FEMALE,     (attrs.get( PopulationType.GENDER ) == PopulationType.FEMALE?     1: 0) );
            stats.inc( PopulationType.CHILD,      (attrs.get( PopulationType.AGE    ) == PopulationType.CHILD?      1: 0) );
            stats.inc( PopulationType.ADOLESCENT, (attrs.get( PopulationType.AGE    ) == PopulationType.ADOLESCENT? 1: 0) );
            stats.inc( PopulationType.ADULT,      (attrs.get( PopulationType.AGE    ) == PopulationType.ADULT?      1: 0) );
            stats.inc( PopulationType.ELDERLY,    (attrs.get( PopulationType.AGE    ) == PopulationType.ELDERLY?    1: 0) );
        }
    }
            
            
    public static Map<PopulationType, PopulationType> decode( Integer person )
    {   
        Map<PopulationType, PopulationType> attrs = new HashMap<>();
        attrs.put( PopulationType.GENDER, unpack( person, PopulationType.GENDER ) > 0? PopulationType.MALE: PopulationType.FEMALE );
        attrs.put( PopulationType.AGE,    unpackAge( person ) );
        return attrs
    }
    
        
    public static int unpack( Integer person, PopulationType type )
    {
        int mask = 0;
        for( int i = 0; i < type.getNumBits(); ++i )
        {
            mask |= (1 << i);
        }
        mask <<= type.getBitOffset();
        int v = (person & mask) >> (type.getBitOffset() - 1)
        return v;
    }
    
        
    public static PopulationType unpackAge( Integer person )
    {
        int v = unpack( person, PopulationType.AGE );
        if( v == 0 )
            return PopulationType.CHILD;
        else if( v == 1 )
            return PopulationType.ADOLESCENT;
        else if( v >= 2 && v <= 5 )
            return PopulationType.ADULT;
        return PopulationType.ELDERLY;
    }
    
        
    public static Integer pack( int value, Integer person, PopulationType type )
    {
        int mask = 0;
        for( int i = 0; i < type.getNumBits(); ++i )
        {
            mask |= (1 << i);
        }
        mask <<= type.getBitOffset();
        person |= mask;
        return person;
    }
    
    // Advance this person's age.  If the person died, return False.  Return True otherwise.
    public static Integer advanceAge( Integer person )
    {
        int age = unpack( person, PopulationType.AGE );
        if( age == 7 )
            return person;  // Elderly people stay elderly!
        return pack( age + 1, person, PopulationType.AGE );
    }
}