package com.example.macstudent.c0699233_test2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by macstudent on 2017-08-10.
 */

public class Emp_repo {
    private DBHelper dbHelper;

    public Emp_repo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(Employee student) {

        //Open connection to write data

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Employee.KEY_name, student.name);
        values.put(Employee.KEY_bday,student.bday);
        values.put(Employee.KEY_salary, student.salary);


        // Inserting Row
        long student_Id = db.insert(Employee.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) student_Id;
    }

    public void delete(int student_Id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Employee.TABLE, Employee.KEY_ID + "= ?",
                new String[] { String.valueOf(student_Id) });
        db.close(); // Closing database connection
    }

    public void update(Employee student) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Employee.KEY_name, student.name);
        values.put(Employee.KEY_bday,student.bday);
        values.put(Employee.KEY_salary, student.salary);


        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Employee.TABLE, values, Employee.KEY_ID + "= ?", new String[] { String.valueOf(student.student_ID) });
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>> getStudentList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Employee.KEY_ID + "," +
                Employee.KEY_name + "," +
                Employee.KEY_bday + "," +
                Employee.KEY_salary +
               " FROM " + Employee.TABLE;

        //Student student = new Student();
        ArrayList<HashMap<String, String>> studentList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> student = new HashMap<String, String>();
                student.put("id", cursor.getString(cursor.getColumnIndex(Employee.KEY_ID)));
                student.put("name", cursor.getString(cursor.getColumnIndex(Employee.KEY_name)));
                student.put("bday", cursor.getString(cursor.getColumnIndex(Employee.KEY_bday)));
                student.put("salary", cursor.getString(cursor.getColumnIndex(Employee.KEY_salary)));


                studentList.add(student);

            } while (cursor.moveToNext());
        }

        db.close();
        return studentList;

    }

    public Employee getStudentById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        String selectQuery =  "SELECT  " +
                Employee.KEY_ID + "," +
                Employee.KEY_name + "," +
                Employee.KEY_bday + "," +
                Employee.KEY_salary +
               " FROM " + Employee.TABLE  + " WHERE " +
                Employee.KEY_ID + "=?";
        int iCount =0;
        Employee student = new Employee();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                student.student_ID =cursor.getInt(cursor.getColumnIndex(Employee.KEY_ID));
                student.name =cursor.getString(cursor.getColumnIndex(Employee.KEY_name));
                student.bday=cursor.getString(cursor.getColumnIndex(Employee.KEY_bday));
                student.salary= cursor.getString(cursor.getColumnIndex(Employee.KEY_salary));


                //  student.image =cursor.getBlob(cursor.getColumnIndex(Movie.KEY_image));
                //byte[] image = cursor.getBlob(1);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return student;
    }
       /* public Bitmap getImage(int i){
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            String qu = "select img  from table where feedid=" + i ;
          String qury =  "SELECT  " + Movie.KEY_image  + " FROM " + Movie.TABLE  + " WHERE " +
                  Movie.KEY_ID + "=?";
            Cursor cur = db.rawQuery(qury, null);

            if (cur.moveToFirst()){
                byte[] imgByte = cur.getBlob(0);
                cur.close();
                return BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
            }
            if (cur != null && !cur.isClosed()) {
                cur.close();
            }

            return null ;
        }*/
}

