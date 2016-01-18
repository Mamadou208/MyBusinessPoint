package com.mamadoudiallo.mybusinesspoint;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.mamadoudiallo.mybusinesspoint.model.BusinessPoint;

import java.util.HashMap;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    FloatingActionButton update;
    Button markCompleted;
    TextView subject, detail, teacher, grade, status;
    private int selectedItemId;

    DBController controller = new DBController(this);
    BusinessPoint businessPoint = new BusinessPoint();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);
        subject = (TextView) findViewById(R.id.txtsubject);
        detail = (TextView) findViewById(R.id.txtdetail);
        teacher = (TextView) findViewById(R.id.txtteacher);
        grade = (TextView) findViewById(R.id.txtgrade);
        status = (TextView) findViewById(R.id.txtstatus);

        markCompleted = (Button) findViewById(R.id.btn_achieved);
        update = (FloatingActionButton) findViewById(R.id.update_btn);

        Intent i = getIntent();
        selectedItemId = (int)i.getLongExtra("selectedItemId", 1L) +1;

        markCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                        controller = new DBController(getApplicationContext());
                        SQLiteDatabase db = controller.getWritableDatabase();
                        ContentValues cv = new ContentValues();
                        cv.put("status", 1);

                        db.update("business_points", cv, "id=" + selectedItemId, null);

                        Toast.makeText(DetailActivity.this,
                                "Updated successfully", Toast.LENGTH_SHORT)
                                .show();

                } catch (Exception ex) {
                    Log.d("Error", ex.getMessage().toString());
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                intent.putExtra("selectedItemId", selectedItemId);
                intent.putExtra("isFromDetail", true);
                startActivity(intent);

            }
        });

        displayDetail();

    }

    public void displayDetail(){

        businessPoint =controller.getBusinessPointById(selectedItemId);
        subject.setText(businessPoint.getSubject());
        detail.setText(businessPoint.getDetail());
        teacher.setText(businessPoint.getTeacher());
        grade.setText(businessPoint.getGrade());
        if(Integer.parseInt(businessPoint.getStatus().toString()) == 1){

            status.setText("Completed");

        } else{
            status.setText("Uncompleted");
        }


    }
}
