package com.sqlcontent.userontent;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    Button btn;
    EditText etxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.button);
        etxt = (EditText) findViewById(R.id.editText);

        myDb = new DatabaseHelper(this);

        int W = 1 << 0; //1
        int S = 1 << 1; //2
        final int B = 1 << 2; //4
        int V = 1 << 3; //8
        final int user1 = W | B | V; //13
        int user2 = user1 | S; //15
        int user3 = S | V;  //10
        int user4 = W | user3; //11

        for(int i=0;i<50;i++){
            myDb.insertData("user1", user1);
        }

        for(int i=0;i<50;i++){
            myDb.insertData("user2", user2);
        }
        for(int i=0;i<50;i++){
            myDb.insertData("user3", user3);
        }
        for(int i=0;i<50;i++){
          myDb.insertData("user4", user4);
       }



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long starttime = System.currentTimeMillis();
                //Log.e("start",""+starttime);

                Cursor cursor = myDb.getAllData(B);
                while(cursor.moveToNext()){
                  //  Log.d("ID",cursor.getString(0));
                  //  Log.d("name",cursor.getString(1));
                   // int ID = cursor.getString(0);
                    int cur_content = cursor.getInt(2) & (~B);
                    Log.d("new",""+cur_content);

                    myDb.updateData(cursor.getString(0),cur_content);
                }
                long endTime = System.currentTimeMillis();
                Log.e("duration",""+(endTime-starttime));
            }
        });
    }


}
