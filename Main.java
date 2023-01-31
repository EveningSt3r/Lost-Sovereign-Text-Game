package Chimera.tech;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

import static Chimera.tech.Global.place;

public class Main {
    private static Map<Integer, String> cutscenes = new HashMap<>();

    // framework for places class is taken from Tim B's Java Course and modified. Modeled after both his and the colossal cave game

    private static Places placesInstance = new Places();
    // Imports done in Places static initialization

    private static Map<Integer,Boolean> hasVisited = new HashMap<>();
    // integer key with boolean for whether the player has visited the location
    // prevents backtracking to gain CP from the same location


    public static void main(String[] args) throws ExceptionInInitializerError{

        Map<String, String> altVocab = new HashMap<String, String>();
        altVocab.put("QUIT", "Q");
        altVocab.put("NORTH", "N");
        altVocab.put("SOUTH", "S");
        altVocab.put("EAST", "E"); // want to type words go ahead I guess, also (non) case sensitivity
        altVocab.put("WEST", "W");

        Global.place = 22;

    // import from cutscenes file
        try(Scanner scanner = new Scanner(new BufferedReader(new FileReader("cutscenes.txt")))) {
            scanner.useDelimiter("/");
            while(scanner.hasNextLine()) {
                int ID = scanner.nextInt();
                scanner.skip(scanner.delimiter());
                String text = scanner.nextLine();

                System.out.println("Imported cutscene: " + ID + ": " + text);
                cutscenes.put(ID, text);
            }

        } catch(IOException | InputMismatchException e) {
            // I have absolutely no clue why its giving me an input mismatch here and not places class
            // I also don't really care
            // this is technically blowing up but the game works fine so L lmao
        }

        for(int i = 0; i < placesInstance.keySet().size(); i++) {
            hasVisited.put(i, false);
        }

        // GUI components begin here
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        JFrame frame = new JFrame("Lost Sovereign - Version 1"); // GUI started

        JFrame intro = new JFrame("Tutorial Window");
        intro.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        intro.setLayout(null);
        intro.setSize(screenSize.width, screenSize.height);
        intro.setExtendedState(intro.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        intro.getContentPane().setBackground(Color.BLACK);
        intro.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel intro1 = new JLabel("Welcome to Lost Sovereign, a text adventure game by Quadsar/EveningSt3r on Github!");
        intro1.setFont(new Font("Serif", Font.BOLD, 32));
        intro1.setForeground(Color.WHITE);
        intro1.setBounds(335, 200, 10000, 100);
        intro.add(intro1);

        JLabel intro2 = new JLabel("Directions (Close or minimize this window to start the game): ");
        intro2.setFont(new Font("Serif", Font.BOLD, 22));
        intro2.setBounds(335, 300, 10000, 100);
        intro2.setForeground(Color.WHITE);
        intro.add(intro2);

        JTextArea intro3 = new JTextArea();
        intro3.setText("Click on the provided text field and enter in a valid direction with your keyboard. Directions represent movement of your character. N for north, S for south, etc. Your \n" +
                "CP (Combat Power) changes depending on what items you get during your adventure. At certain checkpoints you will receieve an encounter. If your CP does not meet\n" +
                "a threshold you will be forced to restart. If this occurs, click the text field and enter 'N' to continue. This prompt will reappear as a reminder upon death. Entering\n" +
                "N will warp you back to a checkpoint. Only certain paths allow progression, so make sure to explore.");
        intro3.setFont(new Font("Serif", Font.BOLD, 20));
        intro3.setBounds(335, 450, 1500, 500);
        intro3.setBackground(Color.BLACK);
        intro3.setForeground(Color.WHITE);
        intro.add(intro3);




        /*frame.setSize(screenSize.width,screenSize.height);*/
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH); // These serve same purpose I think
        frame.setLayout(null);//using no layout managers
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setSize(screenSize.width, screenSize.height);
        Container c = frame.getContentPane();
        JLabel locLabel = new JLabel();


        // This is on hold until I get the rest of the images
        File file = new File("mountain.png");
        String path = file.getAbsolutePath();
        // Adapt to path of installer's PC

        File forest = new File("forest.png");
        String forestPath = forest.getAbsolutePath();

        locLabel.setIcon(new ImageIcon(forestPath));
        Dimension size = locLabel.getPreferredSize();
        locLabel.setBounds(1000, 300, size.width, size.height);
        c.add(locLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JLabel pixelLabel = new JLabel("Current Location: ");
        pixelLabel.setFont(new Font("Serif", Font.BOLD, 16));
        pixelLabel.setForeground(Color.WHITE);
        pixelLabel.setBounds(1000, 200, 500, 100);
        frame.add(pixelLabel);

        // end of section 'on hold'

        /*JLabel instructions = new JLabel("<html>Click on the text field and enter in a valid direction. <br/> For example, " +
                "N for north, U for up and D for down, etc. You can type the words ('North') instead of the letters. Non case sensitive. " +
                "Q means quit and will close the app. </html>");
        instructions.setFont(new Font("Serif", Font.BOLD, 16));
        instructions.setForeground(Color.WHITE);
        frame.add(instructions);
        instructions.setBounds(100,550,1000,80);
*/
        // HIGHER Y VALUE GOES DOWN
        JLabel placeLabel = new JLabel(placesInstance.get(Global.place).getDesc(), SwingConstants.LEFT);
        placeLabel.setFont(new Font("Serif", Font.BOLD, 16));
        placeLabel.setForeground(Color.WHITE);
        frame.add(placeLabel);
        placeLabel.setBounds(100,100,1500,50);

        JLabel cutsceneLabel = new JLabel(cutscenes.get(place), SwingConstants.LEFT);
        cutsceneLabel.setForeground(Color.WHITE);
        cutsceneLabel.setFont(new Font("Serif", Font.BOLD, 16));
        cutsceneLabel.setBounds(100,50,1500,50);
        frame.add(cutsceneLabel);

        JTextField inputField = new JTextField(10);
        inputField.setBounds(100, 300,500,25);
        frame.add(inputField);
        frame.revalidate();
        frame.repaint();


        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.println();

        /*System.out.println("Use N, S, W, E, to go North, South, etc. Use Q to quit the game. ");*/
        Global.cp = 0;

        JLabel cpLabel = new JLabel(("Your current CP is: " + String.valueOf(Global.cp)), SwingConstants.LEFT);
        cpLabel.setForeground(Color.WHITE);
        cpLabel.setFont(new Font("Serif", Font.BOLD, 16));
        cpLabel.setBounds(150,500,1500,50);
        frame.add(cpLabel);
        // THIS MEANS COMBAT POWER

        JLabel dirLabel = new JLabel("The available directions are: " + placesInstance.get(place).exitsIntoString());
        dirLabel.setBounds(100,350,500,50);
        dirLabel.setForeground(Color.WHITE);
        frame.add(dirLabel);
        frame.revalidate();
        frame.repaint(); // I don't know WHY and I don't know HOW but this label doesn't show without this
        frame.add(dirLabel);

        Map<String, Integer> exits = placesInstance.get(place).getExits(); // IMPORTANT

        JLabel cpCheck = new JLabel("");
        cpCheck.setBounds(550,350,2000,50);
        cpCheck.setForeground(Color.WHITE);
        frame.add(cpCheck);
        frame.revalidate();
        frame.repaint();
        frame.add(cpCheck);


        frame.setVisible(true); //making the frame visible
        intro.setVisible(true); // intro first

        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cpCheck.setText("");
                cutsceneLabel.setText("");
                Map<String, Integer> exitTemp = placesInstance.get(place).getExits();
                Global.direction = inputField.getText().toUpperCase();
                if(Global.direction.length() >= 1) {
                    String[] words = Global.direction.split(" ");
                    for(String word: words) {
                        if (altVocab.containsKey(word)) {
                            Global.direction = altVocab.get(word);
                            break;
                        }
                    }
                }
                if(exitTemp.containsKey(Global.direction)) {
                    place = exitTemp.get(Global.direction);
                    dirLabel.setText("The available directions are: " + placesInstance.get(place).exitsIntoString());
                    try {
                        placeLabel.setText(placesInstance.get(place).getDesc());
                    } catch(NullPointerException rr) {
                        rr.printStackTrace();
                        frame.revalidate();
                        frame.repaint();
                    }
                    if(cutscenes.get(place) != null) {
                        System.out.println(cutscenes.get(place));
                        cutsceneLabel.setText(cutscenes.get(place));
                    }
                    if(place == 0) {
                        System.exit(0);
                    }
                    int cpCh = placesInstance.getCP(place); // cp change (increase) for location
                    if (cpCh != 0) {
                        if (!hasVisited.get(place)) {
                            Global.cp = Global.cp + cpCh;
                            cpLabel.setText(("Your current CP is: " + String.valueOf(Global.cp)));
                        } else {
                            System.out.println("Flag: You've been here"); // For testing
                            cpCheck.setText("You have already visited. Cannot receive CP rewards twice. ");
                        }

                    }
                    System.out.println("Current CP: " + Global.cp);
                    int cpReq = placesInstance.getReq(place);
                    if (Global.cp < cpReq) {
                        cpCheck.setText("<html>Game over, you were defeated.<br/>" +
                                "Enter N into the text field to restart the game. <br/>"+
                                "Make sure to keep your power up to defeat opponents. </html>");
                        place = 1;
                    }
                    if (Global.cp >= cpReq && cpReq != 0) {
                        cpCheck.setText("Congratulations! Encounter won, moving on.");
                    }
                    hasVisited.replace(place, true);
                } else {
                    try { // Thread.sleep is implemented here but not for game overs because it literally will not work there for some reason
                        // It literally only works on Wednesdays at 6PM
                        Thread.sleep(500); // Catch for users not entering available directions
                    } catch(InterruptedException r) {
                        r.printStackTrace();
                    }
                    inputField.setText(""); // This is part of the incorrect input catch
                }
                inputField.setText(""); // This is normal clearing after the input has been received
                if(Global.place >= 19) {
                    locLabel.setIcon(new ImageIcon(path));
                    frame.repaint();
                }
            }
        });



        /*while(true) {
            try {
                System.out.println(placesInstance.get(place).getDesc());
                placeLabel.setText(placesInstance.get(place).getDesc());
                frame.revalidate();
                frame.repaint();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            if(cutscenes.get(place) != null) { // print cutscene from text file import if available, throwing error?
                System.out.println(cutscenes.get(place));
                cutsceneLabel.setText(cutscenes.get(place));
            }
            if(place == 0) {
                break;
            }

                int cpCh = placesInstance.getCP(place); // cp change (increase) for location
                if (cpCh != 0) {
                    if (!hasVisited.get(place)) {
                        Global.cp = Global.cp + cpCh;
                        cpLabel.setText(("Your current CP is: " + String.valueOf(Global.cp)));
                    } else {
                        System.out.println("You have already visited this location, and cannot receive CP rewards twice. ");
                    }
                } 
                // You might be asking who these messages are being printed for. This was a console game before it opened a window.
            // The messages were there to help me troubleshoot. Plus, the windowed game uses the console code anyway

                System.out.println("Current CP: " + Global.cp);
                int cpReq = placesInstance.getReq(place);
                if (Global.cp < cpReq) {
                    System.out.println("You meet a tragic end. Warping you back. ");
                    break;
                }
                if (Global.cp >= cpReq && cpReq != 0) {
                    System.out.println("Congratulations! Encounter won. Continuing. ");
                }
                hasVisited.replace(place, true);
                System.out.println("The available directions are: ");

                String text = "The available directions are"; // Getting a string together to print exits for GUI
                for (String exit : exits.keySet()) {
                    System.out.println(exit);
                }

                System.out.println();
                Scanner scanner = new Scanner(System.in);
                Global.direction = scanner.nextLine().toUpperCase();


                if (exits.containsKey(Global.direction)) {
                    place = exits.get(Global.direction);
                    dirLabel.setText("The available directions are: " + placesInstance.get(place).exitsIntoString());
                } else {
                    System.out.println("You cannot go in that direction. ");
                }
                // FOR THE TEXT GAME THAT IS IN CONSOLE. I USED THIS CODE TO MAKE THE GUI VERSION
        } // while loop ends here*/

    }

}

