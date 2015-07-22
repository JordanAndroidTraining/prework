package com.example.jordanhsu.simpletodolist;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainActivity extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemLongClickListener{

    public static final String MAIN_ACTIVITY = "mainActivity";
    public static final String TODO_LIST_FILE_NAME = "todoListData2";

    private ArrayList<String> todoList;
    private TodoListAdapter todoAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoList = loadTodoList();
        todoAdapter = new TodoListAdapter(this, 0, todoList);
        listView = (ListView) findViewById(R.id.itemList);
        listView.setAdapter(todoAdapter);

        listView.setOnItemLongClickListener(this);

        Button addBtn = (Button) findViewById(R.id.addBtn);
        addBtn.setOnClickListener(this);
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

    public void addEventHandler() {
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
            saveTodoList(todoList);
            todoAdapter.notifyDataSetChanged();
        }
    }

    public void removeEventHandler(int position){
        todoList.remove(position);
        todoAdapter.notifyDataSetChanged();
        saveTodoList(todoList);
    }

    @Override
    public void onClick(View v) {
        int clickedResId = v.getId();
        switch (clickedResId){
            case R.id.addBtn:
                addEventHandler();
                break;
            case R.id.deleteRowBtn:
                int clickedPosition = listView.getPositionForView(v);
                Log.d(MAIN_ACTIVITY,"Del Btn Clicked: " + clickedPosition);
                removeEventHandler(clickedPosition);
                break;
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View v, int position, long id) {
        removeEventHandler(position);
        return true;
    }

    public void saveTodoList(ArrayList data){
        try {
            FileOutputStream fos = openFileOutput(TODO_LIST_FILE_NAME, Context.MODE_PRIVATE);
            ObjectOutputStream of = new ObjectOutputStream(fos);
            of.writeObject(data);
            of.flush();
            of.close();
            fos.close();
        }
        catch (Exception e) {
            Log.e("InternalStorage", e.getMessage());
        }
    }

    public ArrayList<String> loadTodoList(){
        ArrayList<String> todoData = new ArrayList<String>();
        FileInputStream fis;
        try {
            fis = openFileInput(TODO_LIST_FILE_NAME);
            ObjectInputStream oi = new ObjectInputStream(fis);
            todoData = (ArrayList<String>) oi.readObject();
            oi.close();
        } catch (Exception e) {
            Log.e("InternalStorage", e.getMessage());
        }
        return todoData;
    }

}
