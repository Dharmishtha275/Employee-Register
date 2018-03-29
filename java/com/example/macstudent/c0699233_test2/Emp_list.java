package com.example.macstudent.c0699233_test2;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by macstudent on 2017-08-10.
 */

public class Emp_list extends ListActivity {

    TextView student_Id;

    Button btnAdd,btnGetAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emp_list);

            Emp_repo repo = new Emp_repo(this);

            ArrayList<HashMap<String, String>> studentList =  repo.getStudentList();
            if(studentList.size()!=0) {
                ListView lv = getListView();
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        student_Id = (TextView) view.findViewById(R.id.student_Id);
                        String studentId = student_Id.getText().toString();
                        Intent objIndent = new Intent(getApplicationContext(),Emp_detail.class);
                        objIndent.putExtra("student_Id", Integer.parseInt( studentId));
                        startActivity(objIndent);
                    }
                });
                ListAdapter adapter = new SimpleAdapter( Emp_list.this,studentList, R.layout.emp_entry,
                        new String[] {"id","name"}, new int[] {R.id.student_Id, R.id.student_name});
                setListAdapter(adapter);
            }
            else
            {
                Toast.makeText(this,"No Employee!",Toast.LENGTH_SHORT).show();
            }

        }
    }



