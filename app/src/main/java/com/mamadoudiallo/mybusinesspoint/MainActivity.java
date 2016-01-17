package com.mamadoudiallo.mybusinesspoint;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.mamadoudiallo.mybusinesspoint.model.BusinessPoint;

public class MainActivity extends Activity {

    public Button add, view, update, delete;
    public EditText placeid, subject, detail, teacher, grade;
    private int selectedItemId;

    BusinessPoint businessPoint = new BusinessPoint();
    DBController controller = new DBController(this);
    TextView infotext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        placeid = (EditText) findViewById(R.id.edplaceid);
        subject = (EditText) findViewById(R.id.edplace);
        detail = (EditText) findViewById(R.id.edcountry);
        teacher = (EditText) findViewById(R.id.edteacher);
        grade = (EditText) findViewById(R.id.edgrade);


        add = (Button) findViewById(R.id.btnadd);
        update = (Button) findViewById(R.id.btnupdate);
        delete = (Button) findViewById(R.id.btndelete);
        view = (Button) findViewById(R.id.btnview);
        infotext = (TextView) findViewById(R.id.txtresulttext);

        Intent i = getIntent();
        selectedItemId = i.getIntExtra("selectedItemId", 0);

        if (selectedItemId >= 1) {
            fullForm();
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, PlaceList.class);
                startActivity(i);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (subject.getText().toString().trim().equals("") || detail.getText().toString().trim().equals("")) {
                        infotext.setText("Please insert subject name and detail..");
                    } else {
                        controller = new DBController(getApplicationContext());
                        SQLiteDatabase db = controller.getWritableDatabase();
                        ContentValues cv = new ContentValues();
                        cv.put("subject", subject.getText().toString());
                        cv.put("detail", detail.getText().toString());
                        cv.put("teacher", teacher.getText().toString());
                        cv.put("grade", Integer.parseInt(grade.getText().toString()));
                        cv.put("status", 0);
                        db.insert("business_points", null, cv);
                        db.close();
                        infotext.setText("Place added Successfully");
                    }
                } catch (Exception ex) {
                    infotext.setText(ex.getMessage().toString());
                }
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if ((subject.getText().toString().trim().equals("") && detail.getText().toString().trim().equals("")) || placeid.getText().toString().trim().equals("")) {
                        infotext.setText("Please insert values to update..");
                    } else {
                        controller = new DBController(getApplicationContext());
                        SQLiteDatabase db = controller.getWritableDatabase();
                        ContentValues cv = new ContentValues();
                        cv.put("subject", subject.getText().toString());
                        cv.put("detail", detail.getText().toString());
                        cv.put("teacher", teacher.getText().toString());
                        cv.put("grade", Integer.parseInt(grade.getText().toString()));

                        db.update("business_points", cv, "id=" + placeid.getText().toString(), null);
                        Toast.makeText(MainActivity.this,
                                "Updated successfully", Toast.LENGTH_SHORT)
                                .show();
                        infotext.setText("Updated Successfully");
                    }
                } catch (Exception ex) {
                    infotext.setText(ex.getMessage().toString());
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (placeid.getText().toString().trim().equals("")) {
                        infotext.setText("Please insert subject ID to delete..");
                    } else {
                        controller = new DBController(getApplicationContext());
                        SQLiteDatabase db = controller.getWritableDatabase();
                        db.delete("business_points", "id=" + placeid.getText().toString(), null);
                        Toast.makeText(MainActivity.this,
                                "deleted successfully", Toast.LENGTH_SHORT)
                                .show();
                        infotext.setText("Deleted Successfully");
                    }
                } catch (Exception ex) {
                    infotext.setText(ex.getMessage().toString());
                }
            }
        });
    }

    public void fullForm() {
        businessPoint = controller.getBusinessPointById(selectedItemId);
        placeid.setText(Integer.toString(businessPoint.getId()));
        subject.setText(businessPoint.getSubject());
        detail.setText(businessPoint.getDetail());
        teacher.setText(businessPoint.getTeacher());
        grade.setText(businessPoint.getGrade());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}