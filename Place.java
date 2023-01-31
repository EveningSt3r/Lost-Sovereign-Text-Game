package Chimera.tech;

import java.io.*;
import java.util.*;

// You could point a gun to my head and ask me what the place and places classes did and I would tell you my last words

public class Place {
    public final int placeID;
    private final String desc;
    private final Map<String, Integer> exits;


    public Place(int placeID, String desc, Map<String, Integer> exits) {
        this.placeID = placeID;
        this.desc = desc;
        if(exits != null) {
            this.exits = new LinkedHashMap<String, Integer>(exits);
        } else {
            this.exits = new LinkedHashMap<String, Integer>();
        }
        this.exits.put("Q", 0);
    }

    public int getPlaceID() {
        return placeID;
    }

    public String getDesc() {
        return desc;
    }

    public String exitsIntoString() {
        String exitList = "";
        for (String exit : exits.keySet()) {
            exitList = exitList + exit + ", ";

        }
        String exitFinal = exitList.substring(0, exitList.length()-2);
        return exitFinal;
    }

    public Map<String, Integer> getExits() {
        return new LinkedHashMap<String, Integer>(exits);
    }
    protected void addExit(String direction, int location) {
        exits.put(direction, location);
    }

    // accessible within class or subclasses of package - make sure this doesn't get misused by other parts
}
