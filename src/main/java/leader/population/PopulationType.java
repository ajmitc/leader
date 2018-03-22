package leader.population;

public enum PopulationType
{
    // Statistics
    MALE(       "Male" ),
    FEMALE(     "Female" ),
	
    CHILD(      "Child" ),
    ADOLESCENT( "Adolescent" ),
    ADULT(      "Adult" ),
    ELDERLY(    "Elderly" ),
    
    // Person attributes  (bitoffset, bitlen)
    GENDER(     "Gender", 0, 1 ),  // 0: female, 1: male
    AGE(        "Age",    2, 3 );  // Decade of life: 0: child, 1: adolescent, 2-5: adult, 6-7: elderly
	
	private String _name;
	private int _bitOffset;
	private int _numBits;
	
	PopulationType( String n )
	{
		this( n, -1, -1 );
	}
	
	PopulationType( String n, int bitoffset, int numbits )
	{
		_name = n;
		_bitOffset = bitoffset;
		_numBits = numbits;
	}
	
	public String getName(){ return _name; }
	public int getBitOffset(){ return _bitOffset; }
	public int getNumBits(){ return _numBits; }
	
	public String toString(){ return _name; }
}
