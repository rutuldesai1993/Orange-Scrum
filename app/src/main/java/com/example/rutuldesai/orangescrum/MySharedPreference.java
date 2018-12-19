package com.example.rutuldesai.orangescrum;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreference {

    private static final String OC_PASSWORD = "oc_password";
    private static final String OC_CHECKED = "oc_checked";
    private static final String OC_EMAIL = "oc_email";
    SharedPreferences preferences;
    static MySharedPreference mySharedPreference;
    public static final String OC_PREFERENCES = "ocpreferences";
    public static final String OC_AUTH_TOKEN = "oc_authtoken";


    private MySharedPreference(Context context) {
        preferences = context.getSharedPreferences(OC_PREFERENCES,Context.MODE_PRIVATE);
    }

    public static MySharedPreference getInstance(Context context) {

         if(mySharedPreference == null)
             mySharedPreference = new MySharedPreference(context);
         return mySharedPreference;
    }

    public void saveAuthToken(String token)
    {
        preferences.edit().putString(OC_AUTH_TOKEN,token).commit();
    }

    public String getAuthToken()
    {
        return preferences.getString(OC_AUTH_TOKEN,null);
    }

    public void  saveLogin(String email,String password)
    {
        preferences.edit().putString(OC_EMAIL,email).commit();
        preferences.edit().putString(OC_PASSWORD,password).commit();
    }

    public String getEmail()
    {
        return preferences.getString(OC_EMAIL,null);
    }
    public String getOcPassword()
    {
        return preferences.getString(OC_PASSWORD,null);
    }
    public void saveChecked(Boolean checked){
        preferences.edit().putBoolean(OC_CHECKED,checked).commit();
    }
    public boolean getChecked(){
        return preferences.getBoolean(OC_CHECKED, false);
    }
}
