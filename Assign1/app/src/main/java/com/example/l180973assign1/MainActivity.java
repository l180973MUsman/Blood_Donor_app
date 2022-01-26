package com.example.l180973assign1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {

    SearchView search;
    ListView list;
    DonorAdapter donorAdapter;
    ArrayList<Hashtable<String, String>> arrayList;
    ArrayList<Donor> arrayList1;
    ArrayList<Hashtable<String, String>> loadlist;
    FloatingActionButton signup;


    String author;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search = findViewById(R.id.search_bar);
        list = findViewById(R.id.listView);

        signup = (FloatingActionButton)findViewById(R.id.add);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });


//        ArrayList<Donor> donorlist = new ArrayList<Donor>();
//        donorAdapter = new DonorAdapter(this, R.layout.list,donorlist);
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Donor");
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot obj : snapshot.getChildren()){
//                    Donor dobj = obj.getValue(Donor.class);
//                    Donor d = new Donor(dobj.getName(),dobj.getB_group(),dobj.getContact(),dobj.getLoc(),dobj.getStatus());
//                    donorlist.add(d);
//                }
//                donorAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        INoteDAO db = new NotesDB(MainActivity.this);
        loadlist = db.load();
        ArrayList<Donor> donorlist = convert(loadlist);
        // New Adapter for Donor
        donorAdapter = new DonorAdapter(this, R.layout.list,donorlist);
        list.setAdapter(donorAdapter);


    }

    public ArrayList<Donor> convert (ArrayList<Hashtable<String, String>> Slist){
        ArrayList<Donor> dlist = new ArrayList<Donor>();
        String [] columns = null;
        for(Hashtable<String,String> obj : Slist){
            dlist.add(new Donor(obj.get("NAME"), obj.get("BGROUP"), obj.get("CONTACT"), obj.get("LOC"), obj.get("STATUS")));
        }

        return dlist;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        MenuItem item = menu.findItem(R.id.action_searchbar);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                donorAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

}