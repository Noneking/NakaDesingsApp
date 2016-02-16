package com.example.nonek.nakadesingsapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity implements Button.OnClickListener {

    //Components
    private TextInputLayout emailWrapper, passwordWrapper;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;

    private Pattern pattern;
    private Matcher matcher;
    private Intent intent;

    //Variables
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private String emailWrapperHint="Email";
    private String emailWrapperError="Not a valid email address";
    private String passwordWrapperHint="Password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initComponents();
    }

    public void initComponents(){

        emailWrapper= (TextInputLayout) findViewById(R.id.login_email_wrapper);
        passwordWrapper= (TextInputLayout) findViewById(R.id.login_password_wrapper);

        editTextEmail= (EditText) findViewById(R.id.login_editText_email);
        editTextPassword= (EditText) findViewById(R.id.login_editText_password);

        buttonLogin= (Button) findViewById(R.id.login_button_login);

        initListeners();
    }

    public void initListeners(){

        buttonLogin.setOnClickListener(this);

        initOperations();
    }

    public void initOperations(){

        emailWrapper.setHint(emailWrapperHint);
        passwordWrapper.setHint(passwordWrapperHint);

        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button_login:
                hideKeyboard();

                if(!validateEmail(editTextEmail.getText().toString())){
                    emailWrapper.setError(emailWrapperError);
                }else{
                    emailWrapper.setErrorEnabled(false);
                    passwordWrapper.setErrorEnabled(false);

                    intent=new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }

    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}