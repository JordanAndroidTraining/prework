package com.example.jordanhsu.simpletodolist;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by jordanhsu on 7/21/15.
 */
public class TodoListAdapter extends ArrayAdapter<String> {

    Context context;
    ArrayList<String> todoList;

    public TodoListAdapter(Context context, int resource, ArrayList<String> todoList) {
        super(context, resource, todoList);
        this.context = context;
        this.todoList = todoList;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.todo_item,null);

        TextView tv = (TextView) view.findViewById(R.id.todoContent);
        tv.setText(todoList.get(position));

        // Handle remove btn
        Button delBtn = (Button) view.findViewById(R.id.deleteRowBtn);
        delBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("xxx", "positon: " + String.valueOf(position));
                todoList.remove(position);
                notifyDataSetChanged();
            }
        });
        return view;
    }
}
