package quartettgame;

public enum AttributeLevel{
    NON_EXISTENT(1),
    BABY(2),
    LOW(3),
    BELOW_AVARAGE(4),
    AVARAGE(5),
    ABOVE_AVARAGE(5),
    HIGH(6),
    SUPER_HIGH(7),
    INSANE(8),
    GOD_LIKE(9);

    int value;
    AttributeLevel(int value) {
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}


