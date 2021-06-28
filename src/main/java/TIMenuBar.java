import javax.swing.*;

/**
 * Creates the JMenu portion of the Calculator.
 * The Menu options include:
 * <ul>
 * <li> File 
 *   <ul> 
 *   <li> Exit
 *   </ul>
 * <li> View
 *   <ul>
 *   <li> Standard
 *   <li> Scientific
 *   <li> Graphing
 *   </ul>
 * <li> Settings
 *   <ul>
 *   <li> Pitch
 *     <ul>
 *     <li> Low
 *     <li> Medium
 *     <li> High
 *   </ul>
 * </ul>
 * <p>
 * File contains an Exit option.  View contains a
 * Standard, Scientific and Graphing options.  Settings has a Pitch Setting.
 *
 */
public class TIMenuBar extends JMenuBar {

    // Exit option to exit the program
    JMenuItem exit = new JMenuItem();

    // Changes the Calculator to only show a Standard Calculator
    JRadioButtonMenuItem standard;
    
    // Changes the Calculator to only show a Scientific Calculator
    JRadioButtonMenuItem scientific;	
    
    // Changes the Calculator to have full functionality including Graph
    JRadioButtonMenuItem graphing;

    // Changes the Voice Synthesis's Pitch to High
    JRadioButtonMenuItem pitchHigh;

    // Changes the Voice Synthesis's Pitch to Medium
    JRadioButtonMenuItem pitchMedium;

    // Changes the Voice Synthesis's Pitch to Low
    JRadioButtonMenuItem pitchLow;
    
    /**
     * Constructor
     *
     */
    TIMenuBar(){
	this.setFont(new java.awt.Font("Dialog", 0, 11));
	this.add(constructFileMenu());
	this.add(constructViewMenu());
	this.add(constructSettingsMenu());
    }

    /**
     * Constructs the File Menu with the Exit option
     *
     */
    JMenu constructFileMenu(){
	JMenu fileMenu = new JMenu();
    	fileMenu.setFont(new java.awt.Font("Dialog", 0, 11));
	fileMenu.setText("File");

	// Settings for the Exit Menu Item
	exit.setFont(new java.awt.Font("Dialog", 0, 11));
	exit.setText("Exit");

	// Adding the Exit Item onto the File Menu
	fileMenu.add(exit);

	return fileMenu;
    }
    
    /**
     * Constructs the View Menu with Standard, Scientific and Graphing as the 
     * options.
     *
     */
    JMenu constructViewMenu(){
	JMenu viewMenu = new JMenu();
	viewMenu.setFont(new java.awt.Font("Dialog", 0, 11));
	viewMenu.setText("View");
	
	// Settings for the Menu Items
	standard = new JRadioButtonMenuItem("Standard");
	standard.setFont(new java.awt.Font("Dialog", 0, 11));
	standard.setSelected(true);

	scientific = new JRadioButtonMenuItem("Scientific");
	scientific.setFont(new java.awt.Font("Dialog", 0, 11));

	graphing = new JRadioButtonMenuItem("Graphing");
	graphing.setFont(new java.awt.Font("Dialog", 0, 11));

	// Add the 3 type of calculators into a button group
	ButtonGroup bg = new ButtonGroup();
	bg.add(standard);
	bg.add(scientific);
	bg.add(graphing);

	// Adding Standard, Scientific and Graphing Items onto the View Menu
	viewMenu.add(standard);
	viewMenu.add(scientific);
	viewMenu.add(graphing);

	return viewMenu;
    }
    
    /**
     * Constructs the Settings Menu with Pitch as an option
     *
     */
    JMenu constructSettingsMenu(){
	JMenu settingMenu = new JMenu();
	settingMenu.setFont(new java.awt.Font("Dialog", 0, 11));
	settingMenu.setText("Settings");

	// Settings for the Pitch Menu Item
	JMenu pitch = new JMenu();
	pitch.setFont(new java.awt.Font("Dialog", 0, 11));
	pitch.setText("Pitch");
	
	// Settings for the different pitches
	pitchLow = new JRadioButtonMenuItem("Low");
	pitchLow.setFont(new java.awt.Font("Dialog", 0, 11));

	pitchMedium = new JRadioButtonMenuItem("Medium");
	pitchMedium.setFont(new java.awt.Font("Dialog", 0, 11));
	pitchMedium.setSelected(true);

	pitchHigh = new JRadioButtonMenuItem("High");
	pitchHigh.setFont(new java.awt.Font("Dialog", 0, 11));

	// Adding all 3 pitch settings to a button group
	ButtonGroup bg = new ButtonGroup();
	bg.add(pitchHigh);
	bg.add(pitchMedium);	
	bg.add(pitchLow);
	
	// Adding the pitch settings to the pitch menu
	pitch.add(pitchLow);
	pitch.add(pitchMedium);
	pitch.add(pitchHigh);

	// Adding the Pitch Option Menu Item onto the Settings Menu
	settingMenu.add(pitch);
	
	return settingMenu;
    }
}
