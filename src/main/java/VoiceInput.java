import javax.speech.AudioException;
import javax.speech.Central;
import javax.speech.recognition.*;
import java.io.FileReader;
import java.util.Locale;

public class VoiceInput extends ResultAdapter {

    private Recognizer rec;
    private String strExp;

    public VoiceInput() {
        strExp = "";
        try {
            RecognizerModeDesc desc = new RecognizerModeDesc(Locale.US, Boolean.TRUE);
            rec = Central.createRecognizer(desc);
            rec.allocate();
            FileReader reader = new FileReader("src/main/resources/expression2.gram");
            RuleGrammar gram = rec.loadJSGF(reader);
            gram.setEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resultAccepted(ResultEvent e) {
        Result r = (Result) (e.getSource());
        ResultToken[] tokens = r.getBestTokens();

        FinalRuleResult result = (FinalRuleResult) e.getSource();
        String[] tags = result.getTags();
        System.out.println(tags[0]);

        if (!tags[0].equals("stop")) {
            strExp = strExp + tokens[0].getSpokenText();
        } else {
            try {
                rec.deallocate();
            } catch (Exception f) {
                f.printStackTrace();
            }
        }
        int tempResult = 0;
        if (tags[0].equals("sign")) {
            System.out.print(tokens[0].getSpokenText());
        } else {
            for (ResultToken token : tokens) {
                tempResult = tempResult + Integer.parseInt(token.getSpokenText());
            }
            System.out.print(tempResult);
        }
    }

    public void listen() {
        try {
            rec.addResultListener(new VoiceInput());
            rec.commitChanges();
            // Request focus and start listening
            rec.requestFocus();
            rec.resume();
        } catch (GrammarException | AudioException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        VoiceInput vc = new VoiceInput();
        vc.listen();
    }

}