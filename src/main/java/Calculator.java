import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This is a Calculator that can take Voice, keyboard, mouse as input for calculator.
 * Answers are returned to the TIScreen as well as spoken out to the user.
 * <p>
 * The calculator have 3 options which are:
 * <ul>
 * <li> Standard four function + sin, cos, and tan
 * <li> Scientific
 * <li> Graphing
 * </ul>
 */
public class Calculator extends JFrame implements KeyListener {

    /**
     * Background image for the Calculator
     */
    private Image image;

    /**
     * The TI Screen Panel of the Calculator
     */
    private TIScreenPanel tiScreen;

    // Voice object for speech synthesis
    private VoiceOutput vo;

    /**
     * The Normal Functions Buttons Panel of the Calculator
     */
    private NormalFunctionPanel normalFunction;

    /**
     * The TI MenuBar of the Calculator
     */
    private TIMenuBar tiMenuBar;

    /**
     * The main panel of the Calculator
     */
    private JPanel panel;

    /**
     * Initializes the Screen to 0, aligned to the right and with a line break
     */
    private String initString = "                   0\n_";

    /**
     * Equation that will be processed when ENTER or '=' button is pressed
     */
    private String equation = "";

    //the answer to the equation is stored here
    private String answer = "";

    /**
     * Previous Answer from a previous valid Equation
     */
    private String previousAnswer = "";

    /**
     * Constructor
     *
     * @param maximum  The Maximum number of characters per line of the screen
     * @param maxlines The Maximum number of lines in the TextArea
     */
    public Calculator(int maximum, int maxlines) {
        createCalculator(maximum, maxlines);
    }

    /**
     * Constructor - maximum number of characters is defaulted at 8 and the maximum
     * number of lines is defaulted at 20.
     */

    public String getEquation() {
        return equation;
    }

    public TIScreenPanel getScreen() {
        return tiScreen;
    }

    public String getAnswer() {
        return answer;
    }

    public Calculator() {
        createCalculator(20, 8);
    }

    /**
     * The method called by the constructor that creates the Calculator
     *
     * @param maximum  The Maximum number of characters per line of the screen
     * @param maxlines The Maximum number of lines in the TextArea
     */
    private void createCalculator(int maximum, int maxlines) {
        //Toolkit toolkit = Toolkit.getDefaultToolkit();
        //image = toolkit.getImage("background.jpg");
        //image = getImage(getDocumentBase(), "background.jpg");


        final ImageIcon m_image = new ImageIcon("background.jpg");
        final int winc = m_image.getIconWidth();
        final int hinc = m_image.getIconHeight();
        JLabel backlabel = new JLabel("");
        if (m_image.getIconWidth() > 0 && m_image.getIconHeight() > 0) {
            backlabel = new JLabel() {
                public void paintComponent(Graphics g) {
                    int w = getParent().getWidth();
                    int h = getParent().getHeight();
                    for (int i = 0; i < h + hinc; i = i + hinc) {
                        for (int j = 0; j < w + winc; j = j + winc) {
                            m_image.paintIcon(this, g, j, i);
                        }
                    }
                }

                public Dimension getPreferredSize() {
                    return new Dimension(super.getSize());
                }

                public Dimension getMinimumSize() {
                    return getPreferredSize();
                }
            };
        }
        getLayeredPane().add(backlabel, new Integer(Integer.MIN_VALUE));
        backlabel.setBounds(0, 0, 5000, 5000);
        setSize(260, 235);
        setVisible(true);
        Color grey = new Color(210, 200, 190);

        panel = new JPanel();//"background.jpg");
        panel.setOpaque(false);
        //panel.setBackground(SystemColor.text);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Adding the TI Screen and the Normal Funtions Panels to the main panel
        tiScreen = new TIScreenPanel(maximum, maxlines, initString);
        tiScreen.setOpaque(false);
        panel.add(tiScreen);
        normalFunction = new NormalFunctionPanel();
        normalFunction.setOpaque(false);
        panel.add(normalFunction);

        // Add the mouse listeners for each button on the Calculator
        addMouseListeners();

        // Adding the TI MenuBar onto the Frame
        tiMenuBar = new TIMenuBar();
        setJMenuBar(tiMenuBar);

        // Setting up the frame settings
        getContentPane().add(panel, BorderLayout.NORTH);
        ((JPanel) getContentPane()).setOpaque(false);
        setDefaultCloseOperation(3);
        setTitle("XTreme Voice Calculator");
        setVisible(true);
        pack();
        setResizable(true);
        addKeyListener(this); // Add key listener to the Calculator
    }

    /**
     * Checks to see if equation is empty, if so then equation will
     * append the previous answer to the beginning of the equation and
     * will be used when computed.
     *
     * @param operation Appends the previous Answer to this equation if there
     *                  is a previous answer in memory.
     */
    void checkPreviousAnswer(String operation) {
        if ((tiScreen.getEquationLength() == 0) & (previousAnswer.length() > 0)) {
            appendString("Ans" + operation);
        } else {
            appendString(operation);
        }
    }

    /**
     * Calls the TIScreen.appendString() method to append the input string and enable the
     * DEL button
     *
     * @param input String that will be appended to the end of the Screen
     */
    void appendString(String input) {
        normalFunction.setDELEnabled(true);
        tiScreen.appendString(input);
    }

    /**
     * Calls the TIScreen.setString() method to set the input string in the TextArea
     * and disables the DEL button.  Also the equation is reset to empty.
     *
     * @param input String that will replace the current screen and will be shown.
     */
    void setString(String input) {
        tiScreen.setString(input);

        // Disables DEL button because there is no equation
        normalFunction.setDELEnabled(false);
    }

    /**
     * Calls the TIScreen.delLastChar() method to delete the last character of the
     * equation in the TextArea and disables the DEL button if the length
     * of equation String is equal to 0.
     */
    void delLastChar() {
        tiScreen.delLastChar();
        // if equation is an empty string
        if (tiScreen.getEquationLength() == 0) {
            // disable the DEL button
            normalFunction.setDELEnabled(false);
        }
    }

    /**
     * Method is called when Enter button or "=" button is pressed.  The answer of the
     * given equation is added to the TextArea.
     */
    void answer() throws Exception {
        // Checks to see if ANS is in front of the equation
        if ((tiScreen.getEquationLength() > 4) &&
                (((tiScreen.getEquation()).substring(0, 3)).compareTo("Ans") == 0)) {

            tiScreen.setEquation(previousAnswer + (tiScreen.getEquation()).substring(3));
        }
        // Removes the line breaks in the equation string
        while ((tiScreen.getEquation()).indexOf("\n") != -1) {
            String equation = tiScreen.getEquation();

            // Finds the nearest line break and removes it
            equation = equation.substring(0, equation.indexOf("\n") - 1) + equation.indexOf("\n" + 1);
            tiScreen.setEquation(equation);
        }

        //Allows the VoiceConvert to accept input into the textarea


        // Computes the answer of equation
        String output = Compute.convert(tiScreen.getEquation());
        previousAnswer = Compute.computeAnswer(output);

        //allocates a VoiceOutput object to say the answer
        try {
            vo = new VoiceOutput();
            vo.speak(previousAnswer);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }


        // Add the answer to the TI Screen
        tiScreen.appendAnswer(previousAnswer);

        // Disables bDEL because no equation
        normalFunction.setDELEnabled(false);
    }

    /**
     * This method has been turned off because the button classes will have
     * this method specified.
     */
    public void keyTyped(KeyEvent evt) {
    }

    /**
     * This method has been turned off because the button classes will have
     * this method specified.
     */
    public void keyReleased(KeyEvent evt) {
    }

    public void keyPressed(KeyEvent evt) {
        char keyChar = evt.getKeyChar();

        try {
            // Adds the character of the key that was pressed
            switch (keyChar) {
                case '1':
                    appendString("1");
                    break;
                case '2':
                    appendString("2");
                    break;
                case '3':
                    appendString("3");
                    break;
                case '4':
                    appendString("4");
                    break;
                case '5':
                    appendString("5");
                    break;
                case '6':
                    appendString("6");
                    break;
                case '7':
                    appendString("7");
                    break;
                case '8':
                    appendString("8");
                    break;
                case '9':
                    appendString("9");
                    break;
                case '0':
                    appendString("0");
                    break;
                case '*':
                    checkPreviousAnswer("*");
                    break;
                case '/':
                    checkPreviousAnswer("/");
                    break;
                case '+':
                    checkPreviousAnswer("+");
                    break;
                case '-':
                    checkPreviousAnswer("-");
                    break;
                case '.':
                    appendString(".");
                    break;
                case '\n':
                    answer();
                    break;
            }

        } catch (Exception e) {
        }

        // If the DEL button is pressed
        if (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            if (normalFunction.isDELEnabled()) {
                delLastChar();  // delete last character of equation
            }
        }

        //if the DEL key is pressed, raise the button
        if (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            if (normalFunction.isDELEnabled()) {
                normalFunction.bDEL.setBorder(BorderFactory.createRaisedBevelBorder());
            }
        }
    }

    /**
     * Adds mouselisteners for all buttons in the Calculator
     */
    void addMouseListeners() {
        // Add Mouselistener for C
        normalFunction.bC.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                tiScreen.oldText = "_";
                setString(tiScreen.oldText);
                tiScreen.oldNumLines = 1;
                tiScreen.numLines = 1;
                normalFunction.setDELEnabled(false);
            }
        });

        // Add Mouselistener for CE
        normalFunction.bCE.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                if (tiScreen.lineEquation >= tiScreen.maxlines) {
                    setString("_");
                } else {
                    setString(tiScreen.oldText);
                }
                tiScreen.numLines = tiScreen.oldNumLines;
                normalFunction.setDELEnabled(false);
            }
        });

        // Add Mouselistener for DEL
        normalFunction.bDEL.addMouseListener(new MouseAdapter() {
            // If there is a character in the equation, it will delete the last char
            public void mousePressed(MouseEvent me) {
                if (normalFunction.isDELEnabled()) {
                    delLastChar();
                }
            }
        });

        //  Setting the MouseListeners for each button
        normalFunction.b9.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                appendString("9");
            }
        });

        normalFunction.b8.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                appendString("8");
            }
        });

        normalFunction.b7.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                appendString("7");
            }
        });

        normalFunction.b6.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                appendString("6");
            }
        });

        normalFunction.b5.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                appendString("5");
            }
        });

        normalFunction.b4.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                appendString("4");
            }
        });

        normalFunction.b3.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                appendString("3");
            }
        });

        normalFunction.b2.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                appendString("2");
            }
        });

        normalFunction.b1.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                appendString("1");
            }
        });

        normalFunction.b0.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                appendString("0");
            }
        });


        normalFunction.bDecimal.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                appendString(".");
            }
        });


        normalFunction.bPlus.addMouseListener(new MouseAdapter() {
            // if there is nothing in the equation, it adds to the previous answer
            public void mousePressed(MouseEvent me) {
                checkPreviousAnswer("+");
            }
        });

        normalFunction.bMinus.addMouseListener(new MouseAdapter() {
            // if there is nothing in the equation, it subtracts the previous answer
            public void mousePressed(MouseEvent me) {
                checkPreviousAnswer("-");
            }
        });

        normalFunction.bDivide.addMouseListener(new MouseAdapter() {
            // if there is nothing in the equation, it divides the previous answer
            public void mousePressed(MouseEvent me) {
                checkPreviousAnswer("/");
            }
        });

        normalFunction.bMultiply.addMouseListener(new MouseAdapter() {
            // if there is nothing in the equation, it multiplies the previous answer
            public void mousePressed(MouseEvent me) {
                checkPreviousAnswer("*");
            }
        });

        normalFunction.bPlusMinus.addMouseListener(new MouseAdapter() {
            //  Adds a negative sign to equation
            public void mousePressed(MouseEvent me) {
                appendString("¬");
            }
        });

        normalFunction.bSIN.addMouseListener(new MouseAdapter() {
            // Adds a "sin(" to the equation
            public void mousePressed(MouseEvent me) {
                appendString("sin(");
            }
        });

        normalFunction.bCOS.addMouseListener(new MouseAdapter() {
            // Adds a "cos(" to the equation
            public void mousePressed(MouseEvent me) {
                appendString("cos(");
            }
        });

        normalFunction.bTAN.addMouseListener(new MouseAdapter() {
            // Adds a "tan(" to the equation
            public void mousePressed(MouseEvent me) {
                appendString("tan(");
            }
        });

        normalFunction.bEqual.addMouseListener(new MouseAdapter() {
            // calls the answerTextString method to calculate the answer of equation
            public void mousePressed(MouseEvent me) {
                try {
                    answer();
                } catch (Exception e) {
                }
            }
        });
    }

    /**
     * Main method to create a Calculator object
     */
    public static void main(String a[]) {
        Calculator c1 = new Calculator();

/***
 //allocates a recognizer and gets out string to be given to the calcualtor to compute
 //as characters are spoken they are appended to the TextArea
 VoiceConvert2 vc = new VoiceConvert2();
 vc.tostring();
 String equ = vc.getStr();


 //validates the string to test if it is in the form "digit operator digit"
 boolean isValid = vc.validate();
 if(isValid == true)
 {
 String answer="";
 //compute the answer and store it in answer
 try
 {
 answer = Compute.computeAnswer(equ);
 }
 catch (Exception et)
 {
 System.out.println(et.getMessage());
 }

 // after answer is obtained it gives it to the synthesizr and speaks answer
 VoiceOutput vo = new VoiceOutput();
 vo.speak(equ + " = " + answer);
 }
 ***/
    }
}
