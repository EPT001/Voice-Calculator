import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Eldhos Thomas
 */
public class VoiceCalculator {
    public static void main(String[] args) throws Exception {
        // Configuration object
        Configuration configuration = new Configuration();

        // Set the path to the acoustic model
        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        
        // Set the path to the dictionary
        configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");

        // Set the language model
        configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

        // Create a live speech recognizer
        LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);
        
        System.out.println("Voice Calculator is ready. Start speaking...");

        // Start recognition
        recognizer.startRecognition(true);
        
        System.out.println("Voice Calculator started. Speak a mathematical expression.");

        while (true) {
            // Get the next spoken phrase
            SpeechResult result = recognizer.getResult();

            if (result != null) {
                String spokenText = result.getHypothesis();

                // Process the spoken text
                spokenText = spokenText.toLowerCase().trim();

                if (spokenText.equals("exit") || spokenText.contains("exit")) {
                    System.out.println("Exiting...");
                    break;
                }

                try {
                    // Evaluate the spoken text as a mathematical expression
                    double answer = evaluateExpression(spokenText);
                    System.out.println("Result: " + answer);
                    } catch (Exception e) {
                    System.out.println("Invalid input. Please try again.");
                }
            }
        }

        // Stop recognition
        recognizer.stopRecognition();
        System.out.println("Voice Calculator stopped.");
    }
    private static double evaluateExpression(String expression) throws ScriptException {
        // Implement your own logic to evaluate the mathematical expression
        // Here, we are using a dummy implementation
        // You can use libraries like JEP, Apache Commons Math, or write your own parser
        // to evaluate the expression
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        return (double) engine.eval(expression);
    }
}   
