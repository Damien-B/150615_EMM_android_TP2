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


public class CreateAccount extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_account, menu);
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

    public void create(View view){
        EditText email = (EditText)findViewById(R.id.email);
        EditText name = (EditText)findViewById(R.id.name);
        EditText password = (EditText)findViewById(R.id.password);
        if(email.getText().toString().equals("")){
            Toast toast = Toast.makeText(this, "Champ email vide", Toast.LENGTH_SHORT);
            toast.show();
        }else if(name.getText().toString().equals("")){
            Toast toast = Toast.makeText(this, "Champ nom vide", Toast.LENGTH_SHORT);
            toast.show();
        }else if(password.getText().toString().equals("")){
            Toast toast = Toast.makeText(this, "Champ mot de passe vide", Toast.LENGTH_SHORT);
            toast.show();
        }else{
            SharedPreferences user = getSharedPreferences(email.getText().toString(), 0);
            SharedPreferences.Editor editor = user.edit();
            editor.putString("user_email", email.getText().toString());
            editor.putString("user_name", name.getText().toString());
            editor.putString("user_password", password.getText().toString());
            editor.commit();


            SharedPreferences current = getSharedPreferences("CURRENT_LOGIN", 0);
            SharedPreferences.Editor editor2 = current.edit();
            editor2.putString("user_email", email.getText().toString());
            editor2.commit();

            Intent intent = new Intent(this, UserHome.class);
            startActivity(intent);
        }
    }
}
