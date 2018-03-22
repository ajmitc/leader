

class Population( object ):
    # Statistics
    MALE   = "Male"
    FEMALE = "Female"
    CHILD  = "Child"
    ADOLESCENT = "Adolescent"
    ADULT = "Adult"
    ELDERLY = "Elderly"
    
    # Person attributes
    GENDER = "Gender"
    AGE = "Age"
    
    # Bit unpacking parameters (byte, bitoffset, numbits)
    GENDER_DECODE = (0, 0, 1)  # 0: female, 1: male
    AGE_DECODE    = (0, 2, 3)  # Decade of life: 0: child, 1: adolescent, 2-5: adult, 6-7: elderly
    
    def __init__( self ):
        pass
        
    def generate_stats( population ):
        stats = {
            self.MALE: 0,
            self.FEMALE: 0,
            self.CHILD: 0,
            self.ADOLESCENT: 0,
            self.ADULT: 0,
            self.ELDERLY: 0,
        }
        for person in population:
            attrs = self.decode( person )
            stats[ self.MALE       ] += 1 if attrs[ self.GENDER ] == self.MALE else 0
            stats[ self.FEMALE     ] += 1 if attrs[ self.GENDER ] == self.FEMALE else 0
            stats[ self.CHILD      ] += 1 if attrs[ self.AGE    ] == self.CHILD else 0
            stats[ self.ADOLESCENT ] += 1 if attrs[ self.AGE    ] == self.ADOLESCENT else 0
            stats[ self.ADULT      ] += 1 if attrs[ self.AGE    ] == self.ADULT else 0
            stats[ self.ELDERLY    ] += 1 if attrs[ self.AGE    ] == self.ELDERLY else 0
    generate_stats = staticmethod( generate_stats )
            
            
    def decode( person ):
        ubytes = struct.unpack( "<BBBB", person )
        attrs = {
            self.GENDER: self.MALE if self.unpack( ubytes, GENDER_DECODE ) > 0 else self.FEMALE,
            self.AGE: self.unpack_age( ubytes, AGE_DECODE ),
        }
        return attrs
    decode = staticmethod( decode )
    
    
    def encode( person, attr, value ):
        params = None
        if attr == self.GENDER:
            params = self.GENDER_DECODE
        elif attr == self.AGE:
            params = self.AGE_DECODE
        if params is None:
            print "Unable to set %s value: cannot find decode parameters" % attr
            return person
        return self.pack( value, person, params )
    encode = staticmethod( encode )
    
        
    def unpack( ubytes, decode_param ):
        mask = 0
        for i in xrange( decode_param[ 2 ] ):
            mask |= (1 << i)
        mask <<= decode_param[ 1 ]
        v = (ubytes[ decode_param[ 0 ] ] & mask) >> (decode_param[ 2 ] - 1)
        return v
    unpack = staticmethod( unpack )
    
        
    def unpack_age( ubytes, decode_param ):
        v = self.unpack( ubytes, decode_param )
        if v == 0:
            return self.CHILD
        elif v == 1:
            return self.ADOLESCENT
        elif 2 >= v >= 5:
            return self.ADULT
        return self.ELDERLY
    unpack_age = staticmethod( unpack_age )
    
        
    def pack( value, person, decode_param ):
        ubytes = struct.unpack( "<BBBB", person )
        mask = 0
        for i in xrange( decode_param[ 2 ] ):
            mask |= (1 << i)
        mast <<= decode_param[ 1 ]
        ubytes[ decode_param[ 0 ] ] |= mask
        person = struct.pack( "<BBBB", *ubytes )
        return person
    pack = staticmethod( pack )
    
    
    def advance_age( person ):
        """ Advance this person's age.  If the person died, return False.  Return True otherwise. """
        age = self.unpack_age( person )
        if age == self.ELDERLY:
            return person  # Elderly people stay elderly!
        return encode( person, AGE, age + 1 )
    