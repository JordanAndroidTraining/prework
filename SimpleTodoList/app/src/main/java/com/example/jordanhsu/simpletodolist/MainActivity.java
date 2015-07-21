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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    public static final String MAIN_ACTIVITY = "mainActivity";

    private ArrayList<String> todoList;
    private TodoListAdapter todoAdapter;
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoList = new ArrayList<String>();
        todoAdapter = new TodoListAdapter(this, 0, todoList);
        ListView listView = (ListView) findViewById(R.id.itemList);
        listView.setAdapter(todoAdapter);
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
        String inputText = et.getText().toString();

        // Clear input
        et.setText("");

        Log.d(MAIN_ACTIVITY, inputText);

        if (inputText.isEmpty()){
            Log.d(MAIN_ACTIVITY, "Nothing input");
            // show hint if input is empty
            Toast.makeText(this,"Please give me something !", Toast.LENGTH_LONG).show();
        }else {
           // append to view
            todoList.add(inputText);
            todoAdapter.notifyDataSetChanged();
        }
    }
}
