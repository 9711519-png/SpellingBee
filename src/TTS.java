public class TTS {

  private String lastWord; // stores last spoken word

  public TTS() {
    // nothing needed to set up
  }

  public void speak(String word) {
    lastWord = word;

    try {
      // Windows PowerShell text-to-speech command
      String command =
        "powershell -Command \"Add-Type -AssemblyName System.Speech; " +
        "(New-Object System.Speech.Synthesis.SpeechSynthesizer).Speak('" + word + "');\"";

      Runtime.getRuntime().exec(command);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void repeat() {
    if (lastWord != null) {
      speak(lastWord);
    }
  }
}
