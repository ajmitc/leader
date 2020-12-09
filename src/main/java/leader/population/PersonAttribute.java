package leader.population;

public enum PersonAttribute {
    // Statistics
    DEAD("Dead", 0),
    LIVING("Living", 1),

    FEMALE("Female", 0),
    MALE("Male", 1),

    CHILD("Child", 0),
    ADOLESCENT("Adolescent", 1),
    ADULT("Adult", 2), // 2-5
    ELDERLY("Elderly", 6);

    private String name;
    private int value;

    PersonAttribute(String n, int value) {
        this.name = n;
        this.value = value;
    }

    public static PersonAttribute toPersonAttribute(PersonStruct personStruct, int value){
        switch (personStruct){
            case ALIVE:
                return value == DEAD.getValue()? DEAD: LIVING;
            case GENDER:
                return value == FEMALE.getValue()? FEMALE: MALE;
            case AGE:
                return value == CHILD.getValue()? CHILD:
                        value == ADOLESCENT.getValue()? ADOLESCENT:
                                value == ELDERLY.getValue()? ELDERLY:
                                        ADULT;
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        return name;
    }
}
