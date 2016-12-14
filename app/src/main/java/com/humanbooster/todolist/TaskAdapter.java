package com.humanbooster.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.humanbooster.todolist.database.Task;

import java.util.List;

/**
 * Created by martin on 02/01/16.
 */
public class TaskAdapter extends ArrayAdapter<Task>
   {

   private List<Task> tasks ;

   public TaskAdapter(Context context, List<Task> tasks)
      {
      super(context, R.layout.todo_item, tasks) ;
      this.tasks = tasks ;
      }

   public void setTasks(List<Task> tasks)
      {
      this.tasks.clear() ;
      this.tasks.addAll(tasks) ;
      this.notifyDataSetChanged() ;
      }

   @Override
   public View getView(int position, View convertView, ViewGroup parent)
      {
      LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      View row = inflater.inflate(R.layout.todo_item, parent, false) ;

      TextView title = (TextView) row.findViewById(R.id.title) ;
      TextView desc = (TextView) row.findViewById(R.id.desc) ;

      Task task = getItem(position) ;

      title.setText(task.getTitle()) ;
      desc.setText(task.getDesc()) ;

      return row ;
      }
   }
