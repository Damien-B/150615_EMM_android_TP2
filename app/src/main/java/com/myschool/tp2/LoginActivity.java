package com.myschool.tp2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void login(View view){
        EditText email = (EditText)findViewById(R.id.login_email);
        EditText password = (EditText)findViewById(R.id.login_password);


        SharedPreferences settings = getSharedPreferences(email.getText().toString(), 0);
        if(!settings.getString("user_email", "").equals("")) {
            if (password.getText().toString().equals(settings.getString("user_password", ""))) {
                SharedPreferences current = getSharedPreferences("CURRENT_LOGIN", 0);
                SharedPreferences.Editor editor = current.edit();
                editor.putString("user_email", email.getText().toString());
                editor.apply();
                Intent intent = new Intent(this, UserHome.class);
                startActivity(intent);
            }else{
                Toast toast = Toast.makeText(this, "mot de passe incorrecte", Toast.LENGTH_SHORT);
                toast.show();
            }
        }else{
            Toast toast = Toast.makeText(this, "email incorrecte", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
