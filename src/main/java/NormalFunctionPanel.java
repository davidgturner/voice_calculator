import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * This class creates a panel of buttons that will be used for the main
 * four functions of the Calculator. +, -, * and /.  In addition to that
 * will be a CE button that clears the equation, a C button which clears
 * the whole screen, and a DEL button which deletes the last character on
 * the screen.  Also included is the functions of SIN, COS, and TAN.  The
 * digits are also included.
 */
public class NormalFunctionPanel extends JPanel {
    
    // Create buttons 0-9
    JButton b0 = new JButton("0");
    JButton b1 = new JButton("1");
    JButton b2 = new JButton("2");
    JButton b3 = new JButton("3");
    JButton b4 = new JButton("4");
    JButton b5 = new JButton("5");
    JButton b6 = new JButton("6");
    JButton b7 = new JButton("7");
    JButton b8 = new JButton("8");
    JButton b9 = new JButton("9");
    
    // Create buttons C, CE and DEL
    JButton bC = new JButton("C");
    JButton bCE = new JButton("CE");
    JButton bDEL = new JButton("DEL");

    // Create buttons for SIN, COS and TAN
    JButton bSIN = new JButton("SIN");
    JButton bCOS = new JButton("COS");
    JButton bTAN = new JButton("TAN");

    // Create buttons +, -, *, /, (-), ., =
    JButton bPlus = new JButton("+");
    JButton bMinus = new JButton("-");
    JButton bMultiply = new JButton("*");
    JButton bDivide = new JButton("/");
    JButton bPlusMinus = new JButton("(-)");
    JButton bDecimal = new JButton(".");
    JButton bEqual = new JButton("=");
    

    /**
     * Constructor
     */
    NormalFunctionPanel(){
	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	add(constructTopPanel());
	add(constructBottomPanel());
	setButtonSettings();

	setOpaque(false);
	//	this.addKeyListener(this);
    }

    JPanel constructTopPanel() {
	JPanel topPanel = new JPanel();
	topPanel.setOpaque(false);
	topPanel.setLayout(new GridLayout(1,3,7,5));

       	// Add C, CE and DEL buttons to the Upper Panel
	topPanel.add(bDEL);
	topPanel.add(bCE);
	topPanel.add(bC);

	return topPanel;
    }
    
    JPanel constructBottomPanel() {
	JPanel bottomPanel = new JPanel();
	bottomPanel.setOpaque(false);
	bottomPanel.setLayout(new GridLayout(4,5,7,5));

	// Add all the buttons to the lower Panel
	bottomPanel.add(b7);
	bottomPanel.add(b8);
	bottomPanel.add(b9);
	bottomPanel.add(bDivide);
	bottomPanel.add(bSIN);
	bottomPanel.add(b4);
	bottomPanel.add(b5);
	bottomPanel.add(b6);
	bottomPanel.add(bMultiply);
	bottomPanel.add(bCOS);
	bottomPanel.add(b1);
	bottomPanel.add(b2);
	bottomPanel.add(b3);
	bottomPanel.add(bMinus);
	bottomPanel.add(bTAN);
	bottomPanel.add(b0);
	bottomPanel.add(bPlusMinus);
	bottomPanel.add(bDecimal);
	bottomPanel.add(bPlus);
	bottomPanel.add(bEqual);
	
	return bottomPanel;
    }
    
    /**
     * Enables (or disables) the DEL button
     *
     * @param b true to enable the button, otherwise false
     */
    public void setDELEnabled(boolean b){
	bDEL.setEnabled(b);
    }

    /**
     * Determines whether this DEL button is enabled. If enabled, 
     * the button can respond to user input and generate events. The 
     * DEL button may be enabled or disabled by calling its
     * setEnabled method.
     *
     * @returns TRUE if the component is enabled; FALSE otherwise.
     */
    boolean isDELEnabled(){
	return bDEL.isEnabled();
    }

    /**
     * Sets the settings of the buttons to predefined values
     *
     */
    
    void setButtonSettings() {
	//  Setting up the DEL, CE, and C
	bC.setBorder(BorderFactory.createRaisedBevelBorder());
	bC.setForeground(Color.red);
	bC.setFont(new java.awt.Font("Dialog", 0, 11));
	bCE.setBorder(BorderFactory.createRaisedBevelBorder());
	bCE.setPreferredSize(new Dimension(60, 26));
	bCE.setForeground(Color.red);
	bCE.setFont(new java.awt.Font("Dialog", 0, 11));
	bDEL.setBorder(BorderFactory.createRaisedBevelBorder());
	bDEL.setPreferredSize(new Dimension(60, 26));
	bDEL.setEnabled(false);
	bDEL.setForeground(Color.red);
	bDEL.setFont(new java.awt.Font("Dialog", 0, 11));

	//  Setting up the digits and 4 functions
	b9.setBorder(BorderFactory.createRaisedBevelBorder());
	b9.setPreferredSize(new Dimension(33, 26));
	b9.setForeground(Color.blue);
	b9.setFont(new java.awt.Font("Dialog", 0, 11));
	bMultiply.setBorder(BorderFactory.createRaisedBevelBorder());
	bMultiply.setPreferredSize(new Dimension(33, 26));
	bMultiply.setForeground(Color.red);
	bMultiply.setFont(new java.awt.Font("Dialog", 0, 11));
	bDivide.setBorder(BorderFactory.createRaisedBevelBorder());
	bDivide.setPreferredSize(new Dimension(33, 26));
	bDivide.setForeground(Color.red);
	bDivide.setFont(new java.awt.Font("Dialog", 0, 11));
	b8.setBorder(BorderFactory.createRaisedBevelBorder());
	b8.setPreferredSize(new Dimension(33, 26));
	b8.setForeground(Color.blue);
	b8.setFont(new java.awt.Font("Dialog", 0, 11));
	b7.setBorder(BorderFactory.createRaisedBevelBorder());
	b7.setPreferredSize(new Dimension(33, 26));
	b7.setForeground(Color.blue);
	b7.setFont(new java.awt.Font("Dialog", 0, 11));
	b6.setBorder(BorderFactory.createRaisedBevelBorder());
	b6.setPreferredSize(new Dimension(33, 26));
	b6.setForeground(Color.blue);
	b6.setFont(new java.awt.Font("Dialog", 0, 11));
	b5.setBorder(BorderFactory.createRaisedBevelBorder());
	b5.setPreferredSize(new Dimension(33, 26));
	b5.setForeground(Color.blue);
	b5.setFont(new java.awt.Font("Dialog", 0, 11));
	b4.setBorder(BorderFactory.createRaisedBevelBorder());
	b4.setPreferredSize(new Dimension(33, 26));
	b4.setForeground(Color.blue);
	b4.setFont(new java.awt.Font("Dialog", 0, 11));
	b3.setBorder(BorderFactory.createRaisedBevelBorder());
	b3.setPreferredSize(new Dimension(33, 26));
	b3.setForeground(Color.blue);
	b3.setFont(new java.awt.Font("Dialog", 0, 11));
	b2.setBorder(BorderFactory.createRaisedBevelBorder());
	b2.setPreferredSize(new Dimension(33, 26));
	b2.setForeground(Color.blue);
	b2.setFont(new java.awt.Font("Dialog", 0, 11));
	b1.setBorder(BorderFactory.createRaisedBevelBorder());
	b1.setPreferredSize(new Dimension(33, 26));
	b1.setForeground(Color.blue);
	b1.setFont(new java.awt.Font("Dialog", 0, 11));
	bTAN.setBorder(BorderFactory.createRaisedBevelBorder());
	bTAN.setPreferredSize(new Dimension(33, 26));
	bTAN.setForeground(Color.blue);
	bTAN.setFont(new java.awt.Font("Dialog", 0, 11));
	b0.setBorder(BorderFactory.createRaisedBevelBorder());
	b0.setPreferredSize(new Dimension(33, 26));
	b0.setForeground(Color.blue);
	b0.setFont(new java.awt.Font("Dialog", 0, 11));
	bMinus.setBorder(BorderFactory.createRaisedBevelBorder());
	bMinus.setPreferredSize(new Dimension(33, 26));
	bMinus.setForeground(Color.red);
	bMinus.setFont(new java.awt.Font("Dialog", 0, 11));
	bPlusMinus.setBorder(BorderFactory.createRaisedBevelBorder());
	bPlusMinus.setPreferredSize(new Dimension(33, 26));
	bPlusMinus.setForeground(Color.blue);
	bPlusMinus.setFont(new java.awt.Font("Dialog", 0, 11));
	bPlus.setBorder(BorderFactory.createRaisedBevelBorder());
	bPlus.setPreferredSize(new Dimension(33, 26));
	bPlus.setForeground(Color.red);
	bPlus.setFont(new java.awt.Font("Dialog", 0, 11));
	bDecimal.setBorder(BorderFactory.createRaisedBevelBorder());
	bDecimal.setPreferredSize(new Dimension(33, 26));
	bDecimal.setForeground(Color.blue);
	bDecimal.setFont(new java.awt.Font("Dialog", 0, 11));
	bSIN.setBorder(BorderFactory.createRaisedBevelBorder());
	bSIN.setPreferredSize(new Dimension(33, 26));
	bSIN.setForeground(Color.blue);
	bSIN.setFont(new java.awt.Font("Dialog", 0, 11));
	bEqual.setBorder(BorderFactory.createRaisedBevelBorder());
	bEqual.setPreferredSize(new Dimension(33, 26));
	bEqual.setForeground(Color.red);
	bEqual.setFont(new java.awt.Font("Dialog", 0, 11));
	bCOS.setBorder(BorderFactory.createRaisedBevelBorder());
	bCOS.setPreferredSize(new Dimension(33, 26));
	bCOS.setForeground(Color.blue);
	bCOS.setFont(new java.awt.Font("Dialog", 0, 11));
    }

    public void keyReleased(KeyEvent evt){
	char keyChar = evt.getKeyChar();
	// The Key released will have that button raised
	switch (keyChar) {
	case '1': b1.setBorder(BorderFactory.createRaisedBevelBorder()); break;
	case '2': b2.setBorder(BorderFactory.createRaisedBevelBorder()); break;
	case '3': b3.setBorder(BorderFactory.createRaisedBevelBorder()); break;
	case '4': b4.setBorder(BorderFactory.createRaisedBevelBorder()); break;
	case '5': b5.setBorder(BorderFactory.createRaisedBevelBorder()); break;
	case '6': b6.setBorder(BorderFactory.createRaisedBevelBorder()); break;
	case '7': b7.setBorder(BorderFactory.createRaisedBevelBorder()); break;
	case '8': b8.setBorder(BorderFactory.createRaisedBevelBorder()); break;
	case '9': b9.setBorder(BorderFactory.createRaisedBevelBorder()); break;
	case '0': b0.setBorder(BorderFactory.createRaisedBevelBorder()); break;
	case '*': bMultiply.setBorder(BorderFactory.createRaisedBevelBorder()); break;
	case '/': bDivide.setBorder(BorderFactory.createRaisedBevelBorder()); break;
	case '+': bPlus.setBorder(BorderFactory.createRaisedBevelBorder()); break;
	case '-': bMinus.setBorder(BorderFactory.createRaisedBevelBorder()); break;
	case '.': bDecimal.setBorder(BorderFactory.createRaisedBevelBorder()); break;
	case '\n': bEqual.setBorder(BorderFactory.createRaisedBevelBorder()); break;
	}
    }

    public void keyTyped(KeyEvent evt){
	char keyChar = evt.getKeyChar();
	switch (keyChar) {  // Lower the button that has been typed
	case '1': b1.setBorder(BorderFactory.createLoweredBevelBorder()); break;
	case '2': b2.setBorder(BorderFactory.createLoweredBevelBorder()); break;
	case '3': b3.setBorder(BorderFactory.createLoweredBevelBorder()); break;
	case '4': b4.setBorder(BorderFactory.createLoweredBevelBorder()); break;
	case '5': b5.setBorder(BorderFactory.createLoweredBevelBorder()); break;
	case '6': b6.setBorder(BorderFactory.createLoweredBevelBorder()); break;
	case '7': b7.setBorder(BorderFactory.createLoweredBevelBorder()); break;
	case '8': b8.setBorder(BorderFactory.createLoweredBevelBorder()); break;
	case '9': b9.setBorder(BorderFactory.createLoweredBevelBorder()); break;
	case '0': b0.setBorder(BorderFactory.createLoweredBevelBorder()); break;
	case '*': bMultiply.setBorder(BorderFactory.createLoweredBevelBorder()); break;
	case '/': bDivide.setBorder(BorderFactory.createLoweredBevelBorder()); break;
	case '+': bPlus.setBorder(BorderFactory.createLoweredBevelBorder()); break;
	case '-': bMinus.setBorder(BorderFactory.createLoweredBevelBorder()); break;
	case '.': bDecimal.setBorder(BorderFactory.createLoweredBevelBorder()); break;
	case '\n': bEqual.setBorder(BorderFactory.createLoweredBevelBorder()); break;
	}
	// Lower the DEL key if it is pressed and enabled
	switch (evt.getKeyCode()){ 
	case KeyEvent.VK_BACK_SPACE :  if (bDEL.isEnabled()){
	    bDEL.setBorder(BorderFactory.createLoweredBevelBorder());} break;
	}
    }
}

