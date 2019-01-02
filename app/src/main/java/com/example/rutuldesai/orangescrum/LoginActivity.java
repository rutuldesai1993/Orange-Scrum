package com.example.rutuldesai.orangescrum;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private static final String LOGIN_URL = "http://203.88.143.51:880/Orangescrum/api/v2.0/login";
    private EditText emailEt,passwordEt;
    private Button btnLogin; private CheckBox rememberMeCb;
    String email,password;
    private TextView forgotpassword,actionbartitle;
    public final static String api_access_code ="Q11i62s0";
    MySharedPreference preference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
        View view =getSupportActionBar().getCustomView();
        actionbartitle=(TextView)findViewById(R.id.actionbarTv);
        actionbartitle.setText("Login");
        ImageButton imageButton= (ImageButton)view.findViewById(R.id.action_bar_back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        emailEt = (EditText) findViewById(R.id.emailText);
        passwordEt = (EditText) findViewById(R.id.passwordText);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        rememberMeCb =(CheckBox) findViewById(R.id.rememberMeCheckBox);
        preference = MySharedPreference.getInstance(LoginActivity.this);
        forgotpassword = (TextView) findViewById(R.id.ForgotPasswordTv);

        forgotpassword.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });

        if(preference.getChecked()) {
            if (preference.getEmail() != null && preference.getOcPassword() != null) {
                emailEt.setText(preference.getEmail());
                passwordEt.setText(preference.getOcPassword());
            }
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = emailEt.getText().toString();
                password = passwordEt.getText().toString();


                StringRequest request = new StringRequest(Request.Method.POST,
                        LOGIN_URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            System.out.println(response);

                            JSONObject reader = new JSONObject(response);

                            String status = reader.getString("status");

                            JSONObject results = reader.getJSONObject("results");

                            String auth_token = results.getString("auth_token");
                            //System.out.println(auth_token);


                            preference.saveAuthToken(auth_token);


                            if (status.equals("OK")) {
                                preference.saveLogin(email,password);
                                preference.saveChecked(rememberMeCb.isChecked());

                                startActivity(new Intent(getApplicationContext(), DashBoardActivity.class));
                            } else {
                                Toast.makeText(getApplicationContext(), reader.getString("msg"), Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    public byte[] getBody() throws AuthFailureError {

                        StringBuilder builder = new StringBuilder();
                        builder.append("{\"email\":\"");
                        builder.append(email + "\",");
                        builder.append("\"password\":\"");
                        builder.append(password + "\",");
                        builder.append("\"api_access_code\":\"");
                        builder.append(api_access_code + "\"}");

                        String LOGIN_JSON = builder.toString();

                        System.out.println(LOGIN_JSON);

                        return LOGIN_JSON.getBytes();
                    }
                };
                Volley.newRequestQueue(LoginActivity.this).add(request);
            }
        });
    }

}
