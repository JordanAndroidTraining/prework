package com.example.jordanhsu.simpletodolist;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.jordanhsu.simpletodolist.R.id.todoInput;


public class MainActivity extends ActionBarActivity {

    public static final String MAIN_ACTIVITY = "mainActivity";

    private ArrayList<String> todoArray;
    private ArrayAdapter<String> todoAdapter;
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView todoItem = (ListView) findViewById(R.id.todoItem);

        todoArray = new ArrayList<String>();
        todoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,todoArray);
        todoItem.setAdapter(todoAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void addBtnClickHandler(View view) {
        EditText et = (EditText) findViewById(R.id.todoInput);


        Log.d(MAIN_ACTIVITY, et.getText().toString());

        todoArray.add(et.getText().toString());
        todoAdapter.notifyDataSetChanged();

    }
}
