package com.example.macstudent.c0699233_test2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

/**
 * Created by macstudent on 2017-08-10.
 */

public class Emp_detail extends Activity {


    EditText name,bday,salary,tax,hr,fsalary;
    String sal;
    private int _Student_Id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emp_detail);


        name = (EditText) findViewById(R.id.edt_name);
        bday = (EditText) findViewById(R.id.edt_bday);
        salary = (EditText) findViewById(R.id.edt_salary);
        tax = (EditText) findViewById(R.id.edt_tax);
        hr = (EditText) findViewById(R.id.edt_hr);
        fsalary = (EditText) findViewById(R.id.edt_fsalary);

        _Student_Id = 0;
        Intent intent = getIntent();
        _Student_Id = intent.getIntExtra("student_Id", 0);
        Emp_repo repo = new Emp_repo(this);
        Employee student = new Employee();
        student = repo.getStudentById(_Student_Id);

        name.setText("Name is" + " " + student.name);
        bday.setText("Bday is" + " " +student.bday);
        salary.setText("Salary is" + " " + student.salary);


        int value=Integer.parseInt(student.salary);

        int t1 = (value/13);
        int t2 = (value/5);
        int fsal = value -(t1+t2);

        tax.setText("Tax is "+ " " +String.valueOf(t1));
        hr.setText("pt is "+ " " + String.valueOf(t2));
        fsalary.setText("Final Salary"+ " " + String.valueOf(fsal));

    }
}
