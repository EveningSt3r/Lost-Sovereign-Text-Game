package Chimera.tech;

public class Global {
    public static int place = 1;
    public static String direction;
    public static int cp;
}
/* Look I KNOW this is bad practice but man this is like the only way this garbage runs */
/* Global variables are constant regardless of class instance, eliminating a headache where certain parts of the GUI
 were not able to be accessed within the giant block that is the actionListener. */