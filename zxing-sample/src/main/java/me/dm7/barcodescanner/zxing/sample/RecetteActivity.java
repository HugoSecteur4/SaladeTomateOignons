package me.dm7.barcodescanner.zxing.sample;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Locale;

public class RecetteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recette);
        TextToSpeech mTts;
        mTts=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
            }
        });
        mTts.setLanguage(Locale.UK);
        String myText1 = "Did you sleep well?";
        String myText2 = "I hope so, because it's time to wake up.";
        mTts.speak(myText1, TextToSpeech.QUEUE_FLUSH, null);

    }
}
