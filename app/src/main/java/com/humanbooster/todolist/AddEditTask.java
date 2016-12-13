package com.humanbooster.todolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.humanbooster.todolist.database.Task;

public class AddEditTask extends Activity {

    private Integer currentEditId;

    private EditText title;
    private EditText desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        title = (EditText) findViewById(R.id.input_title);
        desc = (EditText) findViewById(R.id.input_desc);

        if(getIntent().getSerializableExtra("task") != null){
            Task task = (Task) getIntent().getSerializableExtra("task");
            title.setText(task.getTitle());
            desc.setText(task.getDesc());
            currentEditId = task.getId();
        }
    }

    public void onSave(View v){
        Task task = new Task(currentEditId, title.getText().toString(), desc.getText().toString());
        Intent result = new Intent();
        result.putExtra("task", task);
        setResult(RESULT_OK, result);
        finish();
    }


}
