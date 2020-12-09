package leader.population;

import java.util.*;
import java.util.logging.Logger;

import leader.util.Util;

public class Population {
    private static Logger logger = Logger.getLogger(Population.class.getName());
    private static final int PERSON_BYTE_SIZE = 2;

    private List<byte[]> persons;

    public Population() {
        persons = new ArrayList<>();
    }

    public int size() {
        return persons.size();
    }

    public void clear() {
        persons.clear();
    }

    public byte[] addRandomPerson() {
        return addRandomPerson(Util.randInt(PersonAttribute.ELDERLY.getValue() + 1));
    }

    public byte[] addRandomPerson(int age) {
        byte[] person = new byte[PERSON_BYTE_SIZE];
        pack(PersonAttribute.LIVING.getValue(), person, PersonStruct.ALIVE);
        pack(Util.rand() < 0.5 ? PersonAttribute.FEMALE.getValue(): PersonAttribute.MALE.getValue(), person, PersonStruct.GENDER);
        pack(age, person, PersonStruct.AGE);
        logger.info("Created Person " + explodePerson(person));
        persons.add(person);
        return person;
    }

    public List<byte[]> addRandomPeople(int count) {
        List<byte[]> people = new ArrayList<>();
        for (int i = 0; i < count; ++i) {
            people.add(addRandomPerson());
        }
        return people;
    }

    public List<byte[]> addRandomPeople(int count, int age) {
        List<byte[]> people = new ArrayList<>();
        for (int i = 0; i < count; ++i) {
            people.add(addRandomPerson(age));
        }
        return people;
    }

    public PopulationStatistics generateStats() {
        PopulationStatistics stats = new PopulationStatistics();
        for (byte[] person : persons) {
            Map<PersonStruct, PersonAttribute> attrs = decode(person);
            stats.inc(PersonAttribute.MALE, (attrs.get(PersonStruct.GENDER) == PersonAttribute.MALE ? 1.0 : 0.0));
            stats.inc(PersonAttribute.FEMALE, (attrs.get(PersonStruct.GENDER) == PersonAttribute.FEMALE ? 1.0 : 0.0));
            stats.inc(PersonAttribute.CHILD, (attrs.get(PersonStruct.AGE) == PersonAttribute.CHILD ? 1.0 : 0.0));
            stats.inc(PersonAttribute.ADOLESCENT, (attrs.get(PersonStruct.AGE) == PersonAttribute.ADOLESCENT ? 1.0 : 0.0));
            stats.inc(PersonAttribute.ADULT, (attrs.get(PersonStruct.AGE) == PersonAttribute.ADULT ? 1.0 : 0.0));
            stats.inc(PersonAttribute.ELDERLY, (attrs.get(PersonStruct.AGE) == PersonAttribute.ELDERLY ? 1.0 : 0.0));
        }
        return stats;
    }


    public static Map<PersonStruct, PersonAttribute> decode(byte[] person) {
        Map<PersonStruct, PersonAttribute> attrs = new HashMap<>();
        attrs.put(PersonStruct.ALIVE,  unpack(person, PersonStruct.ALIVE));
        attrs.put(PersonStruct.GENDER, unpack(person, PersonStruct.GENDER));
        attrs.put(PersonStruct.AGE,    unpack(person, PersonStruct.AGE));
        return attrs;
    }


    public static PersonAttribute unpack(byte[] person, PersonStruct type) {
//        int mask = 0;
//        for (int i = 0; i < type.getNumBits(); ++i) {
//            mask |= (1 << i);
//        }
//        mask <<= type.getBitOffset();
//        int v = (person & mask) >> (type.getBitOffset() - 1);
//        return PersonAttribute.toPersonAttribute(type, v);


        int bitOffset = type.getBitOffset();
        int numBits = type.getNumBits();

        int posByte = bitOffset >> 3;
        int posBit = bitOffset & 7;


        long value = 0;
        int modifyBits;
        int valByte;
        int leftShift;
        modifyBits = 8 - posBit;
        if (numBits < modifyBits) modifyBits = numBits;
        leftShift = (8 - posBit - modifyBits);
        while (true) {
            valByte = person[posByte] & 0xff;
            if (modifyBits == 8)
                value += valByte;
            else
                value += (valByte & ((1 << modifyBits) - 1) << leftShift) >> leftShift;
            numBits -= modifyBits;
            if (numBits == 0)
                break;
            posByte++;
            modifyBits = 8;
            if (numBits < modifyBits)
            {
                modifyBits = numBits;
                leftShift = (8 - modifyBits);
            }
            value <<= modifyBits;
        }
        return PersonAttribute.toPersonAttribute(type, (int) value);
    }


    public static void pack(int value, byte[] person, PersonStruct type) {
//        int mask = 0;
//        for (int i = 0; i < type.getNumBits(); ++i) {
//            mask |= (1 << i);
//        }
//        mask <<= type.getBitOffset();
//        person |= mask;
//        return person;
        int numBits = type.getNumBits();
        int bitOffset = type.getBitOffset();

        final long valLong = (value & ((1L << numBits) - 1L));
        int posByte = bitOffset >> 3;
        int posBit = bitOffset & 7;
        int valByte;
        int modifyBits;

        long lValue;
        int leftShift;
        modifyBits = 8 - posBit;
        if (numBits < modifyBits)
            modifyBits = numBits;
        leftShift = (8 - posBit - modifyBits);
        while (true){
            valByte = person[posByte];
            if (modifyBits == 8) {
                lValue = valLong << (32 - numBits) >> (24);
                person[posByte] = (byte) lValue;
            }
            else {
                lValue = valLong << (32 - numBits) >> (32 - modifyBits) << leftShift;
                person[posByte] = (byte) ((valByte & ~(((1 << modifyBits) - 1) << leftShift)) | lValue);
            }
            numBits -= modifyBits;
            if (numBits == 0) break;
            posByte++;
            modifyBits = 8;
            if (numBits < modifyBits) {
                modifyBits = numBits;
                leftShift = (8 - modifyBits);
            }
        }
    }

    // Advance this person's age.  If the person died, return False.  Return True otherwise.
    public static void advanceAge(byte[] person) {
        PersonAttribute age = unpack(person, PersonStruct.AGE);
        if (age != PersonAttribute.ELDERLY)
            pack(age.getValue() + 1, person, PersonStruct.AGE);
    }

    public void buryTheDead(){
        Iterator<byte[]> iterator = persons.iterator();
        while (iterator.hasNext()){
            byte[] person = iterator.next();
            if (unpack(person, PersonStruct.ALIVE) == PersonAttribute.DEAD) {
                iterator.remove();
            }
        }
    }

    public List<byte[]> getPersons() {
        return persons;
    }

    public String explodePerson(byte[] person){
        StringBuilder sb = new StringBuilder();
        for (byte b: person) {
            byte mask = (byte) (1 << (Byte.SIZE - 1));
            for (int i = 0; i < Byte.SIZE; ++i) {
                sb.append((b & mask) > 0 ? "1" : "0");
                mask >>= 1;
            }
        }
        return sb.toString();
    }
}