package com.mvpretrofitexample.screens.textspeach;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mvpretrofitexample.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by SukamalD on 19-01-2018.
 */


public class SpeakingFragment extends Fragment implements TextSpeakViewinterface {

    private TextSpeakLogicImpl speakLogic;
    @BindView(R.id.et_text)
    EditText etText;
    @BindView(R.id.btn_speak)
    Button btnSpeak;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        speakLogic = new TextSpeakLogicImpl(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_speking,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @OnClick(R.id.btn_speak)
    public void onBtnSpeakClick(){
        String text = etText.getText().toString();
        readFile();
//        speakLogic.speakOut(text);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public void displayMessage(String msg) {

    }

    @Override
    public void speakOut() {
    }

    private void readFile(){
        try {
            String line;
            InputStream inputStream = getContext().getAssets().open("sample.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while((line = bufferedReader.readLine()) != null){
                Toast.makeText(getContext(),line,Toast.LENGTH_SHORT).show();
                addToTalkList(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getContext(),"ERROR " + e,Toast.LENGTH_SHORT).show();

        }
    }

    private void addToTalkList(final String msg){

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                speakLogic.speakOut(msg);
            }
        });

    }
}
