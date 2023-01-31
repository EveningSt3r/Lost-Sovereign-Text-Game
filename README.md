# Lost-Sovereign-Text-Game

**AS OF ANY TIME PERIOD BEFORE THIS BLURB IS REMOVED - THIS REPOSITORY IS ARCHIVAL AND FOR VIEWING OF CODE ONLY - JPACKAGE SOLUTIONS RELEASED ON GAME COMPLETION 
1/31 WIP - VERY SOON**


*** IN BETA/ EARLY STAGE OF DEVELOPMENT, EXPECT SHAKY FEATURES AND QOL***
- Very unclear whether or not I will continue this, it's comprehensive as is but lacking

Table of Contents:
- About the Project
- Running the Project
- Usage/Controls
- For the Future
- Boring legal stuff
------------------------------------------------------------------------------------------------------------------------------------------
**ABOUT THE PROJECT**

The Quadsar ("EveningSt3r" on github) Adventure Game was initially an experiment with HashMap and
later, the GUI aspects of Java (Swing). I have used concepts and ideas from both the classic
"Colossal Cave" game, and Tim B's Java adventure game from his Java course. While this project was initially
very similar to these two, the former(s) rely on HashMap and the command line, while this has expanded into the
GUI and I/O from text files.

Yes. I am aware this was achieveable in a much easier fashion through a game engine. Keep in mind that this was
initially a console project. Creating the GUI from scratch was a learning experience and I'd say it turned out
alright.

This story features an amnesiac waking up in a world to which he is unfamiliar, armed with only his wits and 
the weapons he can salvage from battles past, in a world where powerful beings known as "Sovereigns" rule over
different aspects of the world. 

This game is not finished. The framework is complete but the story is not. If you are reading this before 
playing, you will see that the game initially prompts you to three locations. The current locations finished
as of 9/2022 are:
Forest of Dissent (starting area)
Luster, the Green Summit
Cave of the Red Augur

This game may be updated in the future, or it will remain in the beta-like state it is. 

Built with Java using Swing

----------------------------------------------------------------------------------------------------------------------------------------
**RUNNING THE PROGRAM**

You have likely recieved this project in a zip or folder. The multitude of .dll files in the folder are required
for the project to run. Please do not move any files outside of their folders. Likely a way to make this folder
more organized will be workedon in the future. 

You can click on the ".exe" file, provided all the correct text files (places, directions, cutscenes)

The "cutscenes" text file was a product of me not knowing how to correctly format text in GUI and may be improved
later.

As long as all the correct files needed to run (text files, images, and .dll), along with the java installation
provided by Jpackage are present, no errors should occur.

-----------------------------------------------------------------------------------------------------------------------------------------
**USAGE/CONTROLS**

The game tries to be as self sufficient as possible. A directions page is included, but
the text box takes inputs of letters (N for north, S for south) of the cardinal directions, and U for up and D for
down. The available directions the player can take are displayed below the input box. There are graphics to the
right of the text box with a visual of the player's location (@Bagged_Sprout on Twitter). More graphics may be
available in the future.

Upon a game over, the player only needs enter "N" into the input box to be taken back to either a checkpoint
(dev pending) or the game's beginning. 
The game uses a "CP" (Combat Power) system to gauge combat effectiveness
(may be removed in the future for other methods) the player must use the correct paths through navigatable areas
in order to receive the best gear to clear encounters. Certain locations will have an "encounter" with a CP checkpoint
that will kill the player if not met. 

*** The game opens in a windowed environment. Previously this was a console project, but as it stands it only works on 
Windows environments. The game tries its best to frame itself to your screen size, but in the event that some
parts are cut, the full directions are here. ***

-----------------------------------------------------------------------------------------------------------------------------------------

**FOR THE FUTURE**

(Plans that may never happen)
- Finish story and remaining areas
- Add a better way to handle CP checks or a better combat system
- Fix/add checkpoints (most likely to actually be implemented)
- Actual graphics, unlikely, but possible
- Sound

-----------------------------------------------------------------------------------------------------------------------------------------

**BORING LEGAL STUFF**

The Oracle Java 14 JDK (Java development Kit) was obtained under the Oracle Network Java SE License permitting
personal use at no-cost.
https://www.oracle.com/downloads/licenses/javase-license1.html

In the future, this project may move to 17, in which this license will apply, the Oracle No-Fee Terms and Conditions
License. 
https://www.oracle.com/downloads/licenses/no-fee-license.html

Both permit personal use (I am not making money from this but feel free to donate to me in a way that is not
associated with this project) at no cost. 

Images/Graphics from @Bagged_Sprout on Twitter
Text files written by me

Find EveningSt3r on Github or Quadsar#8918 on Discord for help or concerns

