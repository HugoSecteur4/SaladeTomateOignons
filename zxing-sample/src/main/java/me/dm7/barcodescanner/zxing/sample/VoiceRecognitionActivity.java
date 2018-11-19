package me.dm7.barcodescanner.zxing.sample;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;

public class VoiceRecognitionActivity extends AppCompatActivity implements
        RecognitionListener {

    private static final int REQUEST_RECORD_PERMISSION = 100;
    private TextView titreEtape;
    private TextView returnedText;
    private ImageView imageEtape;
    private Recette recette;
    private ProgressBar progressBar;
    private SpeechRecognizer speech = null;
    private Intent recognizerIntent;
    private String LOG_TAG = "VoiceRecognitionActivity";
    private TextToSpeech mTts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        this.recette = (Recette) intent.getSerializableExtra("recette");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recette_maker);
        returnedText = (TextView) findViewById(R.id.textView1);
        titreEtape = (TextView) findViewById(R.id.numero_etape);
        imageEtape = (ImageView) findViewById(R.id.image_etape);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);


        progressBar.setVisibility(View.INVISIBLE);

        if (this.recette.getIndexCourant() < this.recette.getEtapes().size()-1) {
            //recette.nextEtape();
            //returnedText.setText("Etape "+Integer.toString(this.recette.getIndexCourant()+1)+"/"+Integer.toString(this.recette.getEtapes().size())+":\n"+this.recette.getEtapeCourante().getDescriptif());
            mTts=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status == TextToSpeech.SUCCESS) {
                        int ttsLang = mTts.setLanguage(Locale.FRANCE);

                        if (ttsLang == TextToSpeech.LANG_MISSING_DATA
                                || ttsLang == TextToSpeech.LANG_NOT_SUPPORTED) {
                            Log.println(Log.ASSERT,"TTS", "The Language is not supported!");
                        } else {

                                                  //Log.println(Log.ASSERT,"TTS", "Language Supported." + new String(" " + ttsLang));
                                                  Log.println(Log.ASSERT, "taglog", new String("" + (mTts.isLanguageAvailable(Locale.US))) + " " + Locale.getAvailableLocales().length);
                                                  String myText1 = recette.getEtapeCourante().getDescriptif();
                                                  String myText2 = "I hope so, because it's time to wake up.";
                                                  mTts.speak("Vous pouvez dire étape suivante ou cliquer sur le bouton étape suivante pour passer à la prochaine étape.", TextToSpeech.QUEUE_FLUSH, null);

                                                  mTts.playSilentUtterance(600, TextToSpeech.QUEUE_ADD, null);
                                                  mTts.speak("Pour commencer, ", TextToSpeech.QUEUE_ADD, null);
                                                  mTts.speak(myText1, TextToSpeech.QUEUE_ADD, null);
                                                  while(mTts.isSpeaking()) {

                                                  }
                                                  speech = SpeechRecognizer.createSpeechRecognizer(VoiceRecognitionActivity.this);
                                                  Log.i(LOG_TAG, "isRecognitionAvailable: " + SpeechRecognizer.isRecognitionAvailable(VoiceRecognitionActivity.this));
                                                  speech.setRecognitionListener(VoiceRecognitionActivity.this);

                                                  recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                                                  recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE,
                                                          "en");
                                                  recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                                                          RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                                                  recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);

                                                  progressBar.setIndeterminate(true);
                                                  ActivityCompat.requestPermissions
                                                          (VoiceRecognitionActivity.this,
                                                                  new String[]{Manifest.permission.RECORD_AUDIO},
                                                                  REQUEST_RECORD_PERMISSION);




                        }
                        Log.println(Log.ASSERT,"TTS", "Initialization success.");
                    } else {
                        Toast.makeText(getApplicationContext(), "TTS Initialization failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


                    //progressBar.setVisibility(View.VISIBLE);
        titreEtape.setText("Etape "+Integer.toString(this.recette.getIndexCourant()+1)+"/"+Integer.toString(this.recette.getEtapes().size() )+ ":");
        returnedText.setText(this.recette.getEtapeCourante().getDescriptif());
        imageEtape.setImageResource(this.recette.getEtapeCourante().getURLphoto());

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_RECORD_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    speech.startListening(recognizerIntent);
                } else {

                }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (speech != null) {
            speech.destroy();
            Log.i(LOG_TAG, "destroy");
        }
    }


    @Override
    public void onBeginningOfSpeech() {
        Log.i(LOG_TAG, "onBeginningOfSpeech");
        progressBar.setIndeterminate(false);
        progressBar.setMax(10);
    }

    @Override
    public void onBufferReceived(byte[] buffer) {
        Log.i(LOG_TAG, "onBufferReceived: " + buffer);
    }

    @Override
    public void onEndOfSpeech() {
        Log.i(LOG_TAG, "onEndOfSpeech");
        //progressBar.setVisibility(View.VISIBLE);
        progressBar.setIndeterminate(true);
        ActivityCompat.requestPermissions
                (VoiceRecognitionActivity.this,
                        new String[]{Manifest.permission.RECORD_AUDIO},
                        REQUEST_RECORD_PERMISSION);
        //progressBar.setIndeterminate(true);
        //toggleButton.setChecked(false);
    }

    @Override
    public void onError(int errorCode) {
        //progressBar.setVisibility(View.VISIBLE);
        progressBar.setIndeterminate(true);
        ActivityCompat.requestPermissions
                (VoiceRecognitionActivity.this,
                        new String[]{Manifest.permission.RECORD_AUDIO},
                        REQUEST_RECORD_PERMISSION);
    }

    @Override
    public void onEvent(int arg0, Bundle arg1) {
        Log.i(LOG_TAG, "onEvent");
    }

    @Override
    public void onPartialResults(Bundle arg0) {
        Log.i(LOG_TAG, "onPartialResults");
    }

    @Override
    public void onReadyForSpeech(Bundle arg0) {
        Log.i(LOG_TAG, "onReadyForSpeech");
    }

    @Override
    public void onResults(Bundle results) {
        Log.i(LOG_TAG, "onResults");
        ArrayList<String> matches = results
                .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        String text = "";
        for (String result : matches) {
            text += result + "\n";
            Log.println(Log.ASSERT,"tag",text);
        }
        if (text.contains("étape")&&text.contains("suivante") || text.contains("etape")&&text.contains("suivante")) {
            //returnedText.setText("REUSSI");
            nextStep(null);

        } else if(text.contains("étape")&&text.contains("précédente") || text.contains("etape")&&text.contains("précédente") || text.contains("étape")&&text.contains("precedente")){
            previousStep(null);
           // returnedText.setText(text);

        }
    }

    public void nextStep(View view){
        if (this.recette.getIndexCourant() < this.recette.getEtapes().size()-1) {
            recette.nextEtape();
            returnedText.setText(this.recette.getEtapeCourante().getDescriptif());
            titreEtape.setText("Etape "+Integer.toString(this.recette.getIndexCourant()+1)+"/"+Integer.toString(this.recette.getEtapes().size() )+ ":");
            imageEtape.setImageResource(this.recette.getEtapeCourante().getURLphoto());
            mTts=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status == TextToSpeech.SUCCESS) {
                        int ttsLang = mTts.setLanguage(Locale.FRANCE);

                        if (ttsLang == TextToSpeech.LANG_MISSING_DATA
                                || ttsLang == TextToSpeech.LANG_NOT_SUPPORTED) {
                            Log.println(Log.ASSERT,"TTS", "The Language is not supported!");
                        } else {
                            Log.println(Log.ASSERT,"TTS", "Language Supported." + new String(" " + ttsLang));
                            Log.println(Log.ASSERT, "taglog", new String("" + (mTts.isLanguageAvailable(Locale.US))) + " " + Locale.getAvailableLocales().length);
                            String myText1 = recette.getEtapeCourante().getDescriptif();
                            String myText2 = "I hope so, because it's time to wake up.";
                            mTts.speak(myText1, TextToSpeech.QUEUE_FLUSH, null);
                        }
                        Log.println(Log.ASSERT,"TTS", "Initialization success.");
                    } else {
                        Toast.makeText(getApplicationContext(), "TTS Initialization failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            });




            // Log.println(Log.ASSERT, "taglog", new String("" + (mTts.isLanguageAvailable(Locale.US))) + " " + Locale.getAvailableLocales().length);

            // mTts.speak(myText2, TextToSpeech.QUEUE_ADD, null);
        } else {
            returnedText.setText("RECETTE TERMINEE");
            imageEtape.setImageResource(0);
            Button next = (Button) view.findViewById(R.id.nextStep);
            next.setText("Retour");
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                    Intent intent = new Intent( VoiceRecognitionActivity.this,LetsCookActivity.class);
                    startActivity(intent);
                }
            });
            mTts=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status == TextToSpeech.SUCCESS) {
                        int ttsLang = mTts.setLanguage(Locale.FRANCE);

                        if (ttsLang == TextToSpeech.LANG_MISSING_DATA
                                || ttsLang == TextToSpeech.LANG_NOT_SUPPORTED) {
                            Log.println(Log.ASSERT,"TTS", "The Language is not supported!");
                        } else {
                            Log.println(Log.ASSERT,"TTS", "Language Supported." + new String(" " + ttsLang));
                            Log.println(Log.ASSERT, "taglog", new String("" + (mTts.isLanguageAvailable(Locale.US))) + " " + Locale.getAvailableLocales().length);
                            String myText1 = "Vous avez terminé la recette !";
                            String myText2 = "I hope so, because it's time to wake up.";
                            mTts.speak(myText1, TextToSpeech.QUEUE_FLUSH, null);
                        }
                        Log.println(Log.ASSERT,"TTS", "Initialization success.");
                    } else {
                        Toast.makeText(getApplicationContext(), "TTS Initialization failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void previousStep(View view){
        if (this.recette.getIndexCourant() > 0) {
            if(returnedText.getText().equals("RECETTE TERMINEE")){
                Button next = (Button) this.findViewById(R.id.nextStep);
                next.setText("Etape suivante");
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        nextStep(view);
                    }
                });
            }else{
                recette.previousEtape();
            }
            returnedText.setText(this.recette.getEtapeCourante().getDescriptif());
            titreEtape.setText("Etape "+Integer.toString(this.recette.getIndexCourant()+1)+"/"+Integer.toString(this.recette.getEtapes().size() )+ ":");
            imageEtape.setImageResource(this.recette.getEtapeCourante().getURLphoto());
            mTts=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status == TextToSpeech.SUCCESS) {
                        int ttsLang = mTts.setLanguage(Locale.FRANCE);

                        if (ttsLang == TextToSpeech.LANG_MISSING_DATA
                                || ttsLang == TextToSpeech.LANG_NOT_SUPPORTED) {
                            Log.println(Log.ASSERT,"TTS", "The Language is not supported!");
                        } else {
                            Log.println(Log.ASSERT,"TTS", "Language Supported." + new String(" " + ttsLang));
                            Log.println(Log.ASSERT, "taglog", new String("" + (mTts.isLanguageAvailable(Locale.US))) + " " + Locale.getAvailableLocales().length);
                            String myText1 = recette.getEtapeCourante().getDescriptif();
                            String myText2 = "I hope so, because it's time to wake up.";
                            mTts.speak(myText1, TextToSpeech.QUEUE_FLUSH, null);
                        }
                        Log.println(Log.ASSERT,"TTS", "Initialization success.");
                    } else {
                        Toast.makeText(getApplicationContext(), "TTS Initialization failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            });




            // Log.println(Log.ASSERT, "taglog", new String("" + (mTts.isLanguageAvailable(Locale.US))) + " " + Locale.getAvailableLocales().length);

            // mTts.speak(myText2, TextToSpeech.QUEUE_ADD, null);
        }


    }

    @Override
    public void onRmsChanged(float rmsdB) {
        Log.i(LOG_TAG, "onRmsChanged: " + rmsdB);
        progressBar.setProgress((int) rmsdB);
    }

    public static String getErrorText(int errorCode) {
        String message;
        switch (errorCode) {
            case SpeechRecognizer.ERROR_AUDIO:
                message = "Audio recording error";
                break;
            case SpeechRecognizer.ERROR_CLIENT:
                message = "Client side error";
                break;
            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                message = "Insufficient permissions";
                break;
            case SpeechRecognizer.ERROR_NETWORK:
                message = "Network error";
                break;
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                message = "Network timeout";
                break;
            case SpeechRecognizer.ERROR_NO_MATCH:
                message = "No match";
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                message = "RecognitionService busy";
                break;
            case SpeechRecognizer.ERROR_SERVER:
                message = "error from server";
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                message = "No speech input";
                break;
            default:
                message = "Didn't understand, please try again.";
                break;
        }
        return message;
    }
}
