package com.mamadoudiallo.mybusinesspoint;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

public class PlaceList extends Activity {
    private SimpleCursorAdapter dataAdapter;
    DBController controller = new DBController(this);
    ListView ls;
    TextView infotext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.placeslist);
        ls = (ListView) findViewById(R.id.placeslist);
        infotext = (TextView) findViewById(R.id.txtresulttext);

        //Generate ListView from SQLite Database
        displayListView();
    }

    private void displayListView() {
        try {
            //Cursor cursor = controller.();
            List<HashMap<String, String>> data = controller.getAllPlace();
            if (data.size() != 0) {
                // The desired columns to be bound
                String[] columns = new String[]{
                        controller.id,
                        controller.subject,
                        //controller.detail,
                        //controller.teacher,
                        // controller.grade,
                        //controller.status

                };

                // the XML defined views which the data will be bound to
                int[] to = new int[]{
                        R.id.txtplaceid,
                        R.id.txtplacename,
                        //R.id.txt
                };

                // create the adapter using the cursor pointing to the desired data
                //as well as the layout information
                SimpleAdapter adapter = new SimpleAdapter(PlaceList.this, data, R.layout.rows, columns, to);
                ls = (ListView) findViewById(R.id.placeslist);
                // Assign adapter to ListView
                ls.setAdapter(adapter);


                ls.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> listView, View view,
                                            int position, long id) {

                        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                        intent.putExtra("selectedItemId", id);
                        /*intent.putExtra("subject", controller.subject);
                        intent.putExtra("teacher", provincedesclabel);
                        intent.putExtra("grade", prvImg);
                        intent.putExtra("detail", dialogmsg);*/


                        startActivity(intent);

                    }
                });
                String length = String.valueOf(data.size());
                infotext.setText(length + " places");
            } else {
                infotext.setText("No data in database");
            }
        } catch (Exception ex) {
            infotext.setText(ex.getMessage().toString());
        }
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
