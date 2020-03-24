package com.example.appalarm;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       button = findViewById(R.id.button);
       button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
      });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu manu)
    {
        getMenuInflater().inflate(R.menu.main,manu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item1){
        int id=item1.getItemId();
        if(id==R.id.id_aboutapp){
            Intent intent =new Intent(this ,AboutApp.class);
            startActivity(intent);
            return true;
        }
        if(id==R.id.id_setting){
            Intent intent =new Intent(this ,Setting.class);
            startActivity(intent);
            return true;
        }
        return true;
    }
   private void openActivity2() {
        Intent intent =new Intent(this ,Activity2.class);
        startActivity(intent);
    }
}
