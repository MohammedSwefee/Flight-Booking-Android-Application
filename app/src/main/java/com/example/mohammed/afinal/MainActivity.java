package com.example.mohammed.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public int selected;
    Spinner origin;
    Spinner distination;
    Button Show;
    RadioGroup srt;
    int selection;
    String originSelection = "None";
    String distinationSelection = "None";

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //نربط الودجتات الموجودات باللايوت
        origin = (Spinner) findViewById(R.id.origin);
        distination = (Spinner) findViewById(R.id.distination);
        Show = (Button) findViewById(R.id.run);
        srt = (RadioGroup) findViewById(R.id.radioGroup); //مجموع الاختياؤات اللي نجدد من خلالهه شلون نسوي سورت

        //نسوي لست و نربطهه بالسبنر مال الاورجن و الدستنيشن
        List<String> list = new ArrayList<String>();
        list.add("None");
        list.add("Baghdad");
        list.add("Basrah");
        list.add("Najaf");
        list.add("Arbil");
        list.add("Sulaimaniah");
        list.add("Dohuke");
        ArrayAdapter<String> org_adp = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, list);//ادبتر خاص باللستة
        org_adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//حتى نعدل طريقة عرض الادبتر
        origin.setAdapter(org_adp); //ربطناه بالاورجن
        distination.setAdapter(org_adp);//ربطناه بالدستنيشن


        //اخلي لسنر على السبنر مال الاورجن
        origin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                originSelection = origin.getSelectedItem().toString();//منا اعين القيمة اللي حددهه اليوزر
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                originSelection = "None";//حتى اذا ما اختار شي يرجع نن
            }
        });

        //و اخلي لسنر على الدستنيشن
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

        //البتن الرئيسي اللي يودي على صفحة الدسبلي
        Show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selected = srt.getCheckedRadioButtonId();//هنا نحدد بيا طريقة نسوي السورت
                if (selected == -1) {
                    selection = 0;
                } else if (selected == R.id.srtDate) {
                    selection = 1;
                } else if (selected == R.id.srtCost) {
                    selection = 2;
                } else if (selected == R.id.srtService) {
                    selection = 3;
                } else if (selected == R.id.srtCompany) {
                    selection = 4;
                }

                Intent goToDisplay = new Intent(MainActivity.this, Display.class); //نعرف انتنت

                //الستيت حتى نعرف هو محدد شغلة لو ثنينهن لو ولا شغلة بالنسبة للاورجن و الدستنيشن
                int state;
                if (originSelection != "None" && distinationSelection != "None") {
                    state = 3;
                } else if (originSelection != "None" && distinationSelection == "None") {
                    state = 2;

                } else if (originSelection == "None" && distinationSelection != "None") {
                    state = 1;

                } else {
                    state = 0;
                }

                goToDisplay.putExtra("selection", selection);//هنا نودي الطريقة السورت
                goToDisplay.putExtra("originSelection", originSelection);//نودي الاورجن
                goToDisplay.putExtra("distinationSelection", distinationSelection);//نودي الدستنيشن
                goToDisplay.putExtra("state", state);//نودي الحالة مال الاختيار

                startActivity(goToDisplay);//ستارت على الاكتفتي مال الدسبلي

            }
        });
    }

}
