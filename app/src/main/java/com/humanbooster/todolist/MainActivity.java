package com.humanbooster.todolist ;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.humanbooster.todolist.database.DatabaseHelper;
import com.humanbooster.todolist.database.DatabaseService;
import com.humanbooster.todolist.database.Task;

public class MainActivity extends AppCompatActivity
   {

   private TaskAdapter adapter = null ;
   private DatabaseService db ;

   private static int CREATE_TASK = 1 ;
   private static int EDIT_TASK = 2 ;

   @Override
   protected void onCreate(Bundle savedInstanceState)
      {
      super.onCreate(savedInstanceState) ;
      setContentView(R.layout.activity_main) ;
      Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar) ;
      setSupportActionBar(toolbar) ;

      ListView lv = (ListView) findViewById(R.id.todo_list) ;
      lv.setOnItemClickListener
         (
         new AdapterView.OnItemClickListener()
            {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
               {
               Task task = adapter.getItem(position) ;

               Intent i = new Intent(MainActivity.this, AddEditTask.class) ;
               i.putExtra("task", task) ;
               startActivityForResult(i, EDIT_TASK) ;
               }
            }
         ) ;

      db = new DatabaseService(new DatabaseHelper(this)) ;
      adapter = new TaskAdapter(this, db.getAllTask()) ;
      lv.setAdapter(adapter) ;
      }

   @Override
   public boolean onCreateOptionsMenu(Menu menu)
      {
      getMenuInflater().inflate(R.menu.menu_main, menu) ;
      return true;
      }

   @Override
   public boolean onOptionsItemSelected(MenuItem item)
      {
      int id = item.getItemId() ;

      if (id == R.id.add)
         {
         Intent i = new Intent(this, AddEditTask.class) ;
         startActivityForResult(i, CREATE_TASK) ;
         }
      else if (id == R.id.delete)
         {
         db.removeAll() ;
         refreshListView() ;
         }

      return super.onOptionsItemSelected(item) ;
      }

   private void refreshListView()
      {
      adapter.setTasks(db.getAllTask()) ;
      }

   @Override
   protected void onActivityResult(int requestCode, int resultCode, Intent data)
      {
      if (resultCode == RESULT_OK)
         {
         Task t = (Task) data.getExtras().getSerializable("task") ;

         if (requestCode == CREATE_TASK)
            {
            db.addTask(t) ;
            }
         else if (requestCode == EDIT_TASK)
            {
            db.update(t) ;
            }
         refreshListView() ;
         }
      }
   }
