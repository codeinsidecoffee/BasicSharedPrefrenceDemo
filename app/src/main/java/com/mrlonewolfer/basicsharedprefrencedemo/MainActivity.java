package com.mrlonewolfer.basicsharedprefrencedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String FILE_NAME = "SaveMe";
    private static final String PREF_NAME ="Name";
    private static final String PREF_AGE ="Age" ;
    private static final String PREF_STATE ="State" ;

    EditText edtName,edtAge;
    CheckBox chbState;
    Button btnSubmit;
    String name;
    int age;
    Boolean state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtName=findViewById(R.id.edtName);
        edtAge=findViewById(R.id.edtAge);
        chbState=findViewById(R.id.chbState);
        btnSubmit=findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);

        retriveDataFromStorePrefrenceData();
    }

    private void retriveDataFromStorePrefrenceData() {
        SharedPreferences sharedPreferences=getSharedPreferences(FILE_NAME,MODE_PRIVATE);
        name=sharedPreferences.getString(PREF_NAME,"");
        age=sharedPreferences.getInt(PREF_AGE,0);
        state=sharedPreferences.getBoolean(PREF_STATE,false);

        edtName.setText(name);
        edtAge.setText(age+"");
        chbState.setChecked(state);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnSubmit){
           storeDataInSharedPrefrence();
        }
    }

    private void storeDataInSharedPrefrence() {
        name=edtName.getText().toString();
        age= Integer.parseInt(edtAge.getText().toString());
        state=chbState.isChecked();

        SharedPreferences sharedPreferences=getSharedPreferences(FILE_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(PREF_NAME,name);
        editor.putInt(PREF_AGE,age);
        editor.putBoolean(PREF_STATE,state);
        editor.commit();
        Toast.makeText(this, "Your Data is Succussfully Stored.", Toast.LENGTH_SHORT).show();
        edtName.setText("");
        edtAge.setText("");
        chbState.setChecked(false);
    }
}
