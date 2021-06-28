import javax.swing.*;
import java.awt.*;

/**
 * The TI Screen that will be used in conjunction with the Calculator class.
 * Default settings are that it shows 20 characters per line and 8 lines
 * total.  The screen is not scrollable.
 */
public class TIScreenPanel extends JPanel {
    /** The maximum number of characters per line */
    int maximum;

    /** The maximum number of lines in the TextArea */
    int maxlines;
    
    /** The text area that the results and equations will show */
    JTextArea screenArea = new JTextArea();
    
    /** The current character placement of the TextString */
    int charPlace = 0;
    
    /** The current line of the TextString */
    int numLines = 2;
    
    /** The old line placement before the equation */
    int oldNumLines = 1;
    
    /** The number of lines the Equation takes up */
    int lineEquation = 1;

    /** This is the String that holds what is shown on the screen */
    String text;
    
    /** 
     * This is the previous TextString before the addition of the 
     * last equation.  Used for CE Button 
     */
    String oldText;

    /** This is the equation string */
    String equation;

    /**
     * Constructor
     *
     * @param maximum The maximum number of characters per line
     * @param maxlines The maximum number of lines in the TextArea
     * @param initString
     */
    TIScreenPanel(int maximum, int maxlines, String initString) {
	this.maximum = maximum;
	this.maxlines = maxlines;
	setString(initString);
	this.add(screenArea);
	screenArea.setPreferredSize(new Dimension(184, 175));
	screenArea.setMinimumSize(new Dimension(184, 175));
	screenArea.setBorder(BorderFactory.createLoweredBevelBorder());
	screenArea.setText(text);
	screenArea.setFont(new java.awt.Font("Monospaced", 0, 15));
	screenArea.setEditable(false);
    }
    
    /**
     * Sets the screen to be the given String
     *
     * @param text The String that will be shown on the TI Screen
     */
    public void setScreen(String text){
	screenArea.setText(text);
    }
    
    /**
     * Returns the value of the screen in a form of a String
     *
     * @returns A string of the screen
     */
    public String getScreen()
		{
	return screenArea.getText();
    }
    
	 /**
     * Returns the length of characters of the Equation
     *
     * @returns The length of characters of equation variable
     */
    public int getEquationLength() {
	return equation.length();
    }

    /**
     * Returns the Equation String
     * @returns Equation String
     */
    public String getEquation(){
	return equation;
    }
    
    /** 
     * Sets the Equation to the input String
     *
     * @param input Input String to set to Equation
     */
    public void setEquation(String input){
	equation = input;
    }

    /**
     * Appends the input String onto the Current Text Area.
     *
     * @param input Input string to be appended to the TextArea.
     */
    public void appendString(String input) {
	//  If the current textArea + input makes it pass the end of the line
	if ((charPlace + input.length()) >= maximum){
	    // Add input up to end of line and then go to next line and do it again
	    do {
		
		//  If # of lines = maxlines, remove line at top + add input to end of line
		if (numLines == maxlines) {
		    text = text.substring(text.indexOf("\n")+1,text.length()-1) +
			input.substring(0,maximum-charPlace) + "\n" +"_";
		}
		
		//  Else just add the input to end of line + increment numLines
		else{
		    text = text.substring(0,text.length()-1) +
			input.substring(0,maximum-charPlace) + "\n" +"_";
		    numLines = numLines + 1;
		}
		// Add the input to the equation
		equation = equation + input.substring(0,maximum-charPlace) + "\n";
		
		/*while (numLines > maxlines) {
		  text = text.substring(text.indexOf("\n"));
		  numLines = numLines -1;
		  }*/
		lineEquation++;  // Add another line to equation
      		// remove first line of input
		input = input.substring(maximum - charPlace);
		// reset the placement back to 0
		charPlace = 0; 
	    }
	    // until input is less than maximum
	    while (input.length() > maximum);
	}
	equation = equation + input;
	text = text.substring(0,text.length()-1) + input +"_";
	charPlace = charPlace + input.length();
	
	// Checks to see if the oldText and equation is over maxlines lines
	while (((lineEquation + oldNumLines) > maxlines) && (oldNumLines != 0)){
	    if (oldNumLines > 1) {
		oldText = oldText.substring(oldText.indexOf("\n"));
		oldNumLines = oldNumLines - 1;
	    }
	    else {
		oldText = "";
		oldNumLines = 0;
	    }
	}
	setScreen(text);
    }

    /**
     * Clears the screen and sets the screen to the given String
     *
     * @param input Input String to be set in the TextArea. 
     */
    public void setString(String input) {
	text = input;
	String tempString = text;
	numLines = 1;
	while (tempString.indexOf("\n") != -1) {
	    // Calculates amount of lines there are in input
	    numLines++;  
	    tempString = tempString.substring(tempString.indexOf("\n") + 1);
	}
	while (numLines > (maxlines - 1)) {
	    text = text.substring(text.indexOf("\n") + 1);
	    numLines--;
	}
	setScreen(text);
	//lineEquation is resetted back to 1 line
	lineEquation = 1; 

	// Starting from very left of screen, so placement is 0
	charPlace = 0;

	// Resets equation back to empty
	equation = "";
	
	// Sets the oldText to be the same as the current String
	oldText = text;
    }

    /**
     * Deletes the last character on the TextArea
     *
     */
    public void delLastChar(){
	// if there is still text on the last line, just delete last character
	//if the placement of the last line is not on the far left
	if (charPlace > 0){
	    // delete last char
	    text = text.substring(0,text.length()-2) + "_";

	    // decrement the placement of place in line
	    charPlace--;

	    // delete last character in equation
	    equation = equation.substring(0,equation.length()-1);
	}
	// else if only the cursor is the beginning of the last line
	else{
	    // if the number of lines for equation <= maxlines, just delete last 3 characters
	    // delete last 2 char, the char and "\n"
	    equation = equation.substring(0,equation.length()-2);

	    // the current line of equation is decremented by 1
	    lineEquation--;

	    // if the equation does not take up the whole screen
	    if (lineEquation <= maxlines-1) {
		// remove last line of text and decrement placement by 1
		numLines = numLines -1;
		
		//delete last char and "\n"
		text = text.substring(0,text.length()-3) + "_";
	    }
	    // else, onscreen display is equal to last maxlines lines of equation
	    else {
		int tempPlace = lineEquation;

		//Assign text to equation because it takes up whole screen
		text = equation + "_"; 

		while (tempPlace > maxlines) {
		    // Remove first line of the text
		    text = text.substring(text.indexOf("\n")+1); 
		    
		    // Number of lines is decremented by 1
		    tempPlace--; 
		}
	    }
	    charPlace = 19;
	}
	setScreen(text);   //Show the text on the Calculator
    }
    
    /**
     * Right shifts the Answer so it is on the right side of the screen and 
     * appends onto the TI Screen
     *
     * @param input Input Answer String to append
     */
    public void appendAnswer(String input){
	while (input.length() < maximum){ // aligns the answer to the right
	    input = " " + input;
	}
	
        // go to the next line of the text
	text = text.substring(0,text.length()-1) + "\n_";
	
	charPlace = 0;  //Start the next line at very left of last line
	
	// sets TextArea to current screen with answer
	setString(text.substring(0, text.length() - 1) + input + "\n_");

	oldText = getScreen();

	// resets the equation
	equation = "";
	
	// resets the number of lines of equation
	lineEquation = 1;
    }
}
