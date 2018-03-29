package com.example.macstudent.c0699233_test2;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;

/**
 * Created by macstudent on 2017-08-10.
 */

public class Add_Employee extends Activity {



    Button btnSave, btnlist;
    Button btn_time, btn_image;
    ImageView imgPoster;
    Bitmap poster;
    EditText name, bday, salary, price;
    private int _Student_Id = 0;
    Calendar myCalendar = Calendar.getInstance();
    String cameraPath;
    Uri imgUri;
    Bitmap bmp;
    String posterImage;
    static Bitmap bm;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_employee);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnlist = (Button) findViewById(R.id.btnlist);

        name = (EditText) findViewById(R.id.edt_name);
        bday = (EditText) findViewById(R.id.edt_bday);
        salary = (EditText) findViewById(R.id.edt_salary);



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Emp_repo repo = new Emp_repo(Add_Employee.this);
                Employee student = new Employee();

                student.name = name.getText().toString();
                student.student_ID = _Student_Id;
                student.bday = bday.getText().toString();
                student.salary = salary.getText().toString();



                if (_Student_Id == 0) {
                    _Student_Id = repo.insert(student);

                    Toast.makeText(getApplicationContext(), "New Employee Insert", Toast.LENGTH_SHORT).show();
                } else {

                    repo.update(student);
                    Toast.makeText(getApplicationContext(), "Employee Record updated", Toast.LENGTH_SHORT).show();

                }
                //finish();
            }
        });

        btnlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Emp_list.class);
                startActivity(i);
            }
        });



    }







}
