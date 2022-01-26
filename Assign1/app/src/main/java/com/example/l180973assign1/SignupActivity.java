package com.example.l180973assign1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Hashtable;

public class SignupActivity extends AppCompatActivity {
    private EditText name;
    private EditText group;
    private EditText contact;
    private EditText loc;
    private EditText status;
    private Button reg;
    Hashtable<String, String> saveDonor;
//    Donor saveDonor;
//    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = (EditText) findViewById(R.id.name);
        group = (EditText) findViewById(R.id.group);
        contact = (EditText) findViewById(R.id.contact);
        loc = (EditText) findViewById(R.id.location);
        status = (EditText) findViewById(R.id.status);
        reg = (Button) findViewById(R.id.Button);


    }

    public void submit(View view)
    {
        saveDonor = new Hashtable<>();
        saveDonor.put("NAME", name.getText().toString());
        saveDonor.put("BGROUP", group.getText().toString());
        saveDonor.put("CONTACT", contact.getText().toString());
        saveDonor.put("LOC", loc.getText().toString());
        saveDonor.put("STATUS", status.getText().toString());
        INoteDAO db = new NotesDB(SignupActivity.this);
        db.save(saveDonor);


//        reference =  FirebaseDatabase.getInstance().getReference().child("Donor");
//
//        String Nam = name.getText().toString();
//        String BG = group.getText().toString();
//        String Con = contact.getText().toString();
//        String Loc = loc.getText().toString();
//        String Stat = status.getText().toString();
//        saveDonor = new Donor(Nam,BG,Con,Loc,Stat);
//        reference.push().setValue(saveDonor);
        Intent intent = new Intent(SignupActivity.this, MainActivity.class);
        startActivity(intent);
    }
}