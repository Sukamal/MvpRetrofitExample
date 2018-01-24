package com.mvpretrofitexample.screens.textspeach;

import android.content.Context;
import android.media.AudioManager;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

/**
 * Created by SukamalD on 19-01-2018.
 */

public class TextSpeakLogicImpl implements TextSpeakLogicInterface{

    private Context mContext;
    private TextToSpeech textToSpeech;
    private AudioManager mAudioMgr;


    public TextSpeakLogicImpl(Context context){
        this.mContext = context;
        initTextToSpeak();
    }

    private void initTextToSpeak(){
        mAudioMgr = (AudioManager)mContext.getSystemService(Context.AUDIO_SERVICE);
        mAudioMgr.setMode(AudioManager.MODE_IN_COMMUNICATION);
        mAudioMgr.setSpeakerphoneOn(true);

        textToSpeech = new TextToSpeech(mContext, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.UK);
                    textToSpeech.setSpeechRate(0.5f);
                    textToSpeech.setPitch(0.5f);
                }
            }
        });

    }

    @Override
    public void speakOut(String text) {
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);

    }

    @Override
    public void stopSpeak() {
        if(textToSpeech != null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }

}
