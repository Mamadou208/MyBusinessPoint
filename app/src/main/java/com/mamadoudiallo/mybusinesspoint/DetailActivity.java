package com.mamadoudiallo.mybusinesspoint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.mamadoudiallo.mybusinesspoint.model.BusinessPoint;

import java.util.HashMap;
import java.util.List;

public class DetailActivity extends AppCompatActivity {


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

        Intent i = getIntent();
        selectedItemId = (int)i.getLongExtra("selectedItemId", +1L);

        displayDetail();

    }

    public void displayDetail(){
        businessPoint =controller.getBusinessPointById(selectedItemId);
        subject.setText(businessPoint.getSubject());
        detail.setText(businessPoint.getDetail());
        teacher.setText(businessPoint.getTeacher());
        grade.setText(businessPoint.getGrade());
        status.setText(businessPoint.getStatus());

    }
}
