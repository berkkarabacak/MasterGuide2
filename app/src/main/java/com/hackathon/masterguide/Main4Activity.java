package com.hackathon.masterguide;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Main4Activity extends ListActivity {

    ListView list;

    Integer[] itemname = {
            1,
            2,
            3,
            4,
            5
    };
    Integer[] imgid={
            R.mipmap.profilepic1,
            R.mipmap.profilepic2,
            R.mipmap.profilepic3,
            R.mipmap.profilepic4,
            R.mipmap.profilepic5,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
      /*  this.setListAdapter(new ArrayAdapter<String>(
                this, R.layout.mylist,
                R.id.Itemname, itemname)); */
        CustomListAdapter adapter=new CustomListAdapter(this, itemname, imgid);
        list=(ListView)findViewById(android.R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                final int Slecteditem= itemname[+position];
               // Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

                final Button button3 = (Button) findViewById(R.id.traveller);
                button3.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent i = new Intent(Main4Activity.this, Main5Activity.class);
                        i.putExtra("id",Slecteditem);
                        startActivity(i);
                    }
                });

            }
        });


    }
}
