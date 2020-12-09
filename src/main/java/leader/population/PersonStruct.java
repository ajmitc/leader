package leader.population;

public enum PersonStruct {
    // Person attributes  (bitoffset, bitlen)
    ALIVE("Alive", 0, 1),  // 0: dead, 1: alive
    GENDER("Gender", 1, 1),  // 0: female, 1: male
    AGE("Age", 2, 3);  // Decade of life: 0: child, 1: adolescent, 2-5: adult, 6-7: elderly

    private String name;
    private int bitOffset;
    private int numBits;

    PersonStruct(String n, int bitoffset, int numbits) {
        this.name = n;
        this.bitOffset = bitoffset;
        this.numBits = numbits;
    }

    public String getName() {
        return name;
    }

    public int getBitOffset() {
        return bitOffset;
    }

    public int getNumBits() {
        return numBits;
    }

    public String toString() {
        return name;
    }
}
