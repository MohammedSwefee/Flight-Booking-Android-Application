package com.example.mohammed.afinal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohammed on 09/03/2018.
 */

public class DBInsertion extends AppCompatActivity {
    DBnew mdb;
    SQLiteDatabase db;
    Spinner company;
    Spinner origin;
    Spinner distination;
    String originSelection = "None";
    String distinationSelection = "None";
    EditText plane;
    EditText flightNum;
    EditText departureDate;
    EditText LandingDate;
    EditText seats;
    EditText service;
    EditText cost;
    String compan;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertion);
        mdb = new DBnew(DBInsertion.this, "DB", null, 1);
        db = mdb.getWritableDatabase();

        company = (Spinner) findViewById(R.id.comp);
        origin = (Spinner) findViewById(R.id.org_in);
        distination = (Spinner) findViewById(R.id.dis_in);
        plane = (EditText) findViewById(R.id.pln);
        flightNum = (EditText) findViewById(R.id.flt_num);
        departureDate = (EditText) findViewById(R.id._date);
        LandingDate = (EditText) findViewById(R.id.lnd_date);
        seats = (EditText) findViewById(R.id.sts);
        service = (EditText) findViewById(R.id.srvc);
        cost = (EditText) findViewById(R.id.cost);

        //نسوي لست ميحتاج
        List<String> list1 = new ArrayList<String>();
        list1.add("Al-iraqiah");
        list1.add("Fly Baghdad");
        list1.add("Egiptair");
        list1.add("Emirates");
        list1.add("Qatar Airways");

        ArrayAdapter<String> comp_adp = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, list1);
        comp_adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        company.setAdapter(comp_adp);

        company.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                compan = company.getSelectedItem().toString();
                if (compan == "Al-iraqiah") {
                    compan = "1";
                } else if (compan == "Fly Baghdad") {
                    compan = "2";
                } else if (compan == "Egiptair") {
                    compan = "3";
                } else if (compan == "Emirates") {
                    compan = "4";
                } else if (compan == "Qatar Airways") {
                    compan = "5";

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                compan = "None";
            }
        });


        List<String> list = new ArrayList<String>();
        list.add("None");
        list.add("Baghdad");
        list.add("Basrah");
        list.add("Najaf");
        list.add("Arbil");
        list.add("Sulaimaniah");
        list.add("Dohuke");
        ArrayAdapter<String> org_adp = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, list);
        org_adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        origin.setAdapter(org_adp);
        distination.setAdapter(org_adp);

        origin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                originSelection = origin.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                originSelection = "None";
            }
        });
        distination.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                distinationSelection = distination.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                distinationSelection = "None";
            }
        });

    }

    public void ins(View view) {//الاستدعاء مال هذا الفويد من الاكس ام ال

        if ((compan != "") && (originSelection != "") && (distinationSelection != "") && (plane.getText().toString() != "") && (flightNum.getText().toString() != "") && (departureDate.getText().toString() != "") && (LandingDate.getText().toString() != "") && (seats.getText().toString() != "") && (service.getText().toString() != "") && (cost.getText().toString() != "")) {
            ContentValues cv = new ContentValues();
            cv.put("COMPANY", compan);
            cv.put("ORIGIN", originSelection);
            cv.put("DESTINATION", distinationSelection);
            cv.put("PLANE", plane.getText().toString());
            cv.put("FLIGHT_NUM", flightNum.getText().toString());
            cv.put("FLIGHT_NAME", departureDate.getText().toString());
            cv.put("LND_DATE", LandingDate.getText().toString());
            cv.put("SEATS", seats.getText().toString());
            cv.put("SERVICE", service.getText().toString());
            cv.put("COST", cost.getText().toString());

            long id = db.insert("FL", null, cv);//ن
            Toast.makeText(this, String.valueOf(id), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "The coolumns musn't have null fields", Toast.LENGTH_SHORT).show();
        }
    }

    public void del(View view) {//هذا همينه ^_^
        db.delete("FL", null, null);
        Toast.makeText(this, "Hahaahahah you deleted it again sucker!", Toast.LENGTH_SHORT).show();
    }

}
