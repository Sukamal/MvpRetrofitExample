package com.mvpretrofitexample.screens.login;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.mvpretrofitexample.MainActivity;
import com.mvpretrofitexample.R;
import com.mvpretrofitexample.screens.parent.ParentActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginViewInterface{

    private LoginLogicImpl loginLogic;

    @BindView(R.id.til_userName)
    TextInputLayout tilUsername;
    @BindView(R.id.til_pwd)
    TextInputLayout tilPassword;
    @BindView(R.id.et_UserName)
    EditText etUserName;
    @BindView(R.id.et_pwd)
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupUI();
        initDependencies();
    }

    private void setupUI(){
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    private void initDependencies(){
        loginLogic = new LoginLogicImpl(this);
    }

    @OnClick(R.id.btn_Login)
    public void submit(){
        String userName = etUserName.getText().toString();
        String password = etPassword.getText().toString();
        if(userName == null || userName.isEmpty()){
            etUserName.setError("Please enter username");
            etUserName.requestFocus();
        }else if(password == null || password.isEmpty()){
            etPassword.setError("Please enter password");
            etPassword.requestFocus();

        }else{
            loginLogic.doLogin(userName,password);
        }
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
    public void gotoNextScreen() {
        startActivity(new Intent(LoginActivity.this, ParentActivity.class));
    }
}
