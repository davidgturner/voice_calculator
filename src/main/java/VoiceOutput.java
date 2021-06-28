import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Voice;
import java.util.Locale;

public class VoiceOutput {

    private Voice[] voices;    //voices that user has to pick from
    private Synthesizer synthesizer;    //synthesizer to create speech output
    private String tone;        //tone of voice
    private String name;
    private String userspeech;    //what ever the user says
    private String answer;        //answer to the calculation of user speech

    public VoiceOutput() {
        try {
            // Set property as Kevin Dictionary
            System.setProperty(
                    "freetts.voices",
                    "com.sun.speech.freetts.en.us"
                            + ".cmu_us_kal.KevinVoiceDirectory");

            // Register Engine
            Central.registerEngineCentral(
                    "com.sun.speech.freetts"
                            + ".jsapi.FreeTTSEngineCentral");

            // Create a Synthesizer
            synthesizer
                    = Central.createSynthesizer(
                    new SynthesizerModeDesc(Locale.US));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void speak(String name) {
        try {
            // Get it ready to speak
            synthesizer = Central.createSynthesizer(new SynthesizerModeDesc(Locale.ENGLISH));
            synthesizer.allocate();
            synthesizer.resume();
            synthesizer.speakPlainText(name, null);
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (synthesizer != null)
                synthesizer.deallocate();
        } catch (EngineException e) {
            e.printStackTrace();
        }
    }

    public void finalize() {
        close();
    }

    public static void main(String[] args) {
        VoiceOutput vo = new VoiceOutput();
        String d = "Lets talk";
        vo.speak(d);
        String bye = "Bye for now.";
        vo.speak(bye);
    }
}
