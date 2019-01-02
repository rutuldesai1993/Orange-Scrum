package com.example.rutuldesai.orangescrum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuDashboardActivity extends AppCompatActivity {

    void MenuDashBoardActivity(){

    }
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_dashboard);
        back= (Button)findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuDashboardActivity.this, DashBoardActivity.class);
                startActivity(intent);
            }
        });
    }
}
