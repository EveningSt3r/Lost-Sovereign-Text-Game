package Chimera.tech;

import java.io.*;
import java.util.*;

public class Places implements Map<Integer, Place> {
    private static Map<Integer, Place> places = new LinkedHashMap<>();
    private static Map<Integer, Integer> combatIndex = new LinkedHashMap<>();
    private static Map<Integer, Integer> requirementIndex = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException{
        System.out.println("Main is running, static finished. ");
        try (DataOutputStream placeFile = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("places.dat")))) {
            for(Place place: places.values()) {
                placeFile.writeInt(place.getPlaceID());
                placeFile.writeUTF(place.getDesc());
                System.out.println("Writing place: " + place.getPlaceID() + ": " + place.getDesc());
                System.out.println("WRITING " + (place.getExits().size() - 1) + " exits.");
                placeFile.writeInt(place.getExits().size()-1);
                for(String direction : place.getExits().keySet()) {
                    if(!direction.equalsIgnoreCase("Q")) {
                        System.out.println(direction + "," + place.getExits().get(direction));
                        placeFile.writeUTF(direction);
                        placeFile.writeInt(place.getExits().get(direction));
                    }
                }

            }
        } // idk what this is anymore
        // This actually doesn't do anything I think since its not writing the whole file
    }
    static {

        try(Scanner scanner = new Scanner(new BufferedReader(new FileReader("places.txt")))) {
            scanner.useDelimiter("/");
            while(scanner.hasNextLine()) {
                int loc = scanner.nextInt();
                scanner.skip(scanner.delimiter());
                String description = scanner.nextLine();
                System.out.println("Imported loc: " + loc + ": " + description);
                Map<String, Integer> tempExit = new HashMap<>();
                places.put(loc, new Place(loc, description, tempExit));
            }

        } catch(IOException e) {
            e.printStackTrace(); // these might be the same 
        } catch (InputMismatchException e) {
            e.printStackTrace(); // there is no reason this should be giving me an exception only on thursdays
        }


        try (BufferedReader dirFile = new BufferedReader(new FileReader("directions.txt"))) {
            String input;
            while((input = dirFile.readLine()) != null) {
                String[] data = input.split(",");
                int loc = Integer.parseInt(data[0]);
                String direction = data[1];
                int destination = Integer.parseInt(data[2]);
                int cpChange = Integer.parseInt(data[3]);
                int cpReq = Integer.parseInt(data[4]);
                try {
                    combatIndex.put(loc, cpChange);
                } catch (NullPointerException er) {
                    System.out.println("Attempt to add null. " + loc + cpChange);
                }
                try {
                    requirementIndex.put(loc, cpReq);
                } catch(NullPointerException er) {
                    System.out.println("Attempt to add null. " + loc + cpReq);
                }
                System.out.println(loc + ": " + direction + ": " + destination);
                Place location = places.get(loc);
                try {
                    location.addExit(direction, destination);
                } catch(NullPointerException e) {
                    System.out.println("Attempt to add null. " + direction + destination);



                }




            }
        } catch (IOException | NumberFormatException e) {
           // why is this blowing up
        }
    }
    /*static {
        try(DataInputStream placeFile = new DataInputStream(new BufferedInputStream(new FileInputStream("places.dat")))) {
            boolean eof = false;
            while(!eof) {
                try {
                    Map<String, Integer> exits = new LinkedHashMap<>();
                    int locID = placeFile.readInt();
                    String description = placeFile.readUTF();
                    int numExits = placeFile.readInt();
                    System.out.println("Read location " + locID + " : " + description);
                    System.out.println("Found " + numExits + " exits");
                    for(int i=0; i<numExits; i++) {
                        String direction = placeFile.readUTF();
                        int destination = placeFile.readInt();
                        exits.put(direction, destination);
                        System.out.println("\t\t" + direction + "," + destination);
                    }
                    places.put(locID, new Place(locID, description, exits));

                } catch(EOFException e) {
                    eof = true;
                }

            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
*/

// you'll use this when you're done adding exits and locations

    // 7/8/2022 no i won't

    public int getReq(Object key) {
        return requirementIndex.get(key);
    }
    public int getCP(Object key) {
        return combatIndex.get(key);
    }

    @Override
    public int size() {
        return places.size();
    }

    @Override
    public boolean isEmpty() {
        return places.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return places.containsKey(key);

    }

    @Override
    public boolean containsValue(Object value) {
        return places.containsKey(value);
    }

    @Override
    public Place get(Object key) {
        return places.get(key);
    }

    @Override
    public Place put(Integer key, Place value) {
        return places.put(key, value);
    }

    @Override
    public Place remove(Object key) {
        return places.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Place> m) {

    }

    @Override
    public void clear() {
        places.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return places.keySet();
    }

    @Override
    public Collection<Place> values() {
        return places.values();
    }

    @Override
    public Set<Entry<Integer, Place>> entrySet() {
        return places.entrySet();
    }
}
