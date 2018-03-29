package com.example.macstudent.c0699233_test2;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by Test on 4/23/2017.
 */

public class DBHelper extends SQLiteOpenHelper {




       //version number to upgrade database version
        //each time if you Add, Edit table, you need to change the
        //version number.
        private static final int DATABASE_VERSION = 4;

        // Database Name
        private static final String DATABASE_NAME = "PEmployee_Detail.db";

        public DBHelper(Context context ) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //All necessary tables you like to create will create here

            String CREATE_TABLE_STUDENT = "CREATE TABLE " + Employee.TABLE  + "("
                    + Employee.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                    + Employee.KEY_name + " TEXT, "
                    + Employee.KEY_bday + " TEXT, "
                    + Employee.KEY_salary+ " TEXT )";

            db.execSQL(CREATE_TABLE_STUDENT);

              System.out.print("created");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Drop older table if existed, all data will be gone!!!
            db.execSQL("DROP TABLE IF EXISTS " + Employee.TABLE);

            // Create tables again
            onCreate(db);

        }

    }

