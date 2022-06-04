package com.example.mohammed.afinal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Mohammed on 08/03/2018.
 */

public class Display extends AppCompatActivity {
    DBnew mdb;
    Button ins;
    ListView lv;
    Button re;
    String distinationSelection;
    String originSelection;
    int state;
    SQLiteDatabase db;
    Cursor cur;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        mdb = new DBnew(Display.this, "DB", null, 1);
        db = mdb.getReadableDatabase();

        Bundle bun = getIntent().getExtras();//جبنه المعلومات اللي تجي من المين اكتفتي
        int s = (int) bun.get("selection");//نجيب السلكشن يعني الطريقة اللي نسوي بيهه السورت
        originSelection = bun.get("originSelection").toString();
        distinationSelection = bun.get("distinationSelection").toString();
        state = Integer.valueOf(bun.get("state").toString());
        String cols[] = {"id", "COMPANY", "ORIGIN", "DESTINATION", "PLANE", "FLIGHT_NUM", "FLIGHT_NAME", "LND_DATE", "SEATS", "SERVICE", "COST"};
        ins = (Button) findViewById(R.id.ins);
        lv = (ListView) findViewById(R.id.lv);
        re = findViewById(R.id.re);


        if (s == 0) {//نشوف شنو نوع السلكشن مال السورت
            if (state == 3) {//بعدين نشوف شنو الحالة مال الاختيار
                //اخلي النتائج بالكيرسر
                cur = db.query("FL", cols, "ORIGIN='" + originSelection + "' AND DESTINATION='" + distinationSelection + "'", null, null, null, "id");
            } else if (state == 0) {
                cur = db.query("FL", cols, null, null, null, null, "id");
            } else if (state == 2) {
                cur = db.query("FL", cols, "ORIGIN='" + originSelection + "'", null, null, null, "id");
            } else if (state == 1) {
                cur = db.query("FL", cols, "DESTINATION='" + distinationSelection + "'", null, null, null, "id");

            }
            //ابدي من بداية البيانات
            cur.move(1);
            int i = cur.getCount();//اشوم جم بيانية موجودة بالكيرسر
            ArrayList<String> str = new <String>ArrayList();

            int j = 0;
            if (cur.getCount() == 0) {//اذا جانت الداتابيس فارغة
                Toast.makeText(this, "There are no contents in this Day!", Toast.LENGTH_LONG).show();
            } else {//هنا عرض البيانات
                String result = "";
                while (j < i) {//طالما اكو مواقع بالكيرسر
                    for (int z = 0; z < 11; z++) {
                        result += cur.getString(z) + ",,";//كل موقع بالكيرسر اجيب بياناته و احط بينهن فارزتين علمود افرق بينهن بغدين
                    }
                    str.add(result);//اضيف بيانات للاري لست
                    cur.moveToNext();//ننتقل للموقع اللي بعده بالكيرسر
                    j++;
                }
                String[] export = new String[str.size()];//سوينه مصفوفة سترنك بحجم اللست
                for (int y = 0; y < str.size(); y++) {
                    export[y] = str.get(y).toString();//راح نخلي بيهه عناصر اللست
                }
                ListAdapter ls = new List_item(this, export);
                lv.setAdapter(ls);//عيننه اللست ادبتر للست فير علمود تطلع
            }

        } else if (s == 1) {

            if (state == 3) {
                cur = db.query("FL", cols, "ORIGIN='" + originSelection + "' AND DESTINATION='" + distinationSelection + "'", null, null, null, "FLIGHT_NUM");
            } else if (state == 0) {
                cur = db.query("FL", cols, null, null, null, null, "FLIGHT_NUM");
            } else if (state == 2) {
                cur = db.query("FL", cols, "ORIGIN='" + originSelection + "'", null, null, null, "FLIGHT_NUM");
            } else if (state == 1) {
                cur = db.query("FL", cols, "DESTINATION='" + distinationSelection + "'", null, null, null, "FLIGHT_NUM");
            }
            cur.move(1);
            int i = cur.getCount();
            ArrayList str = new ArrayList();

            int j = 0;

            if (cur.getCount() == 0) {
                Toast.makeText(this, "There are no contents in this Day!", Toast.LENGTH_LONG).show();
            } else {
                String result = "";
                while (j < i) {
                    for (int z = 0; z < 11; z++) {
                        result += cur.getString(z) + ",,";
                    }
                    str.add(result);
                    cur.moveToNext();
                    j++;
                }
                String[] export = new String[str.size()];
                for (int y = 0; y < str.size(); y++) {
                    export[y] = str.get(y).toString();
                }
                //   ListAdapter ls=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,str);
                ListAdapter ls = new List_item(this, export);
                // lv = (ListView) findViewById(R.id.lv);
                lv.setAdapter(ls);
            }
        } else if (s == 2) {

            if (state == 3) {
                cur = db.query("FL", cols, "ORIGIN='" + originSelection + "' AND DESTINATION='" + distinationSelection + "'", null, null, null, "COST");
            } else if (state == 0) {
                cur = db.query("FL", cols, null, null, null, null, "COST");
            } else if (state == 2) {
                cur = db.query("FL", cols, "ORIGIN='" + originSelection + "'", null, null, null, "COST");
            } else if (state == 1) {
                cur = db.query("FL", cols, "DESTINATION='" + distinationSelection + "'", null, null, null, "COST");
            }
            cur.move(1);
            int i = cur.getCount();
            ArrayList str = new ArrayList();

            int j = 0;

            if (cur.getCount() == 0) {
                Toast.makeText(this, "There are no contents in this Day!", Toast.LENGTH_LONG).show();
            } else {
                String result = "";
                while (j < i) {
                    for (int z = 0; z < 11; z++) {
                        result += cur.getString(z) + ",,";
                    }
                    str.add(result);
                    cur.moveToNext();
                    j++;
                }
                String[] export = new String[str.size()];
                for (int y = 0; y < str.size(); y++) {
                    export[y] = str.get(y).toString();
                }
                ListAdapter ls = new List_item(this, export);
                lv.setAdapter(ls);
            }
        } else if (s == 3) {
            if (state == 3) {
                cur = db.query("FL", cols, "ORIGIN='" + originSelection + "' AND DESTINATION='" + distinationSelection + "'", null, null, null, "SERVICE");
            } else if (state == 0) {
                cur = db.query("FL", cols, null, null, null, null, "SERVICE");
            } else if (state == 2) {
                cur = db.query("FL", cols, "ORIGIN='" + originSelection + "'", null, null, null, "SERVICE");
            } else if (state == 1) {
                cur = db.query("FL", cols, "DESTINATION='" + distinationSelection + "'", null, null, null, "SERVICE");
            }
            cur.move(1);
            int i = cur.getCount();
            ArrayList str = new ArrayList();

            int j = 0;

            if (cur.getCount() == 0) {
                Toast.makeText(this, "There are no contents in this Day!", Toast.LENGTH_LONG).show();
            } else {
                String result = "";
                while (j < i) {
                    for (int z = 0; z < 11; z++) {
                        result += cur.getString(z) + ",,";
                    }
                    str.add(result);
                    cur.moveToNext();
                    j++;
                }
                String[] export = new String[str.size()];
                for (int y = 0; y < str.size(); y++) {
                    export[y] = str.get(y).toString();
                }
                ListAdapter ls = new List_item(this, export);
                lv.setAdapter(ls);
            }
        } else if (s == 4) {

            if (state == 3) {
                cur = db.query("FL", cols, "ORIGIN='" + originSelection + "' AND DESTINATION='" + distinationSelection + "'", null, null, null, "COMPANY");
            } else if (state == 0) {
                cur = db.query("FL", cols, null, null, null, null, "COMPANY");
            } else if (state == 2) {
                cur = db.query("FL", cols, "ORIGIN='" + originSelection + "'", null, null, null, "COMPANY");
            } else if (state == 1) {
                cur = db.query("FL", cols, "DESTINATION='" + distinationSelection + "'", null, null, null, "COMPANY");
            }

            cur.move(1);
            int i = cur.getCount();
            ArrayList str = new ArrayList();

            int j = 0;

            if (cur.getCount() == 0) {
                Toast.makeText(this, "There are no contents in this Day!", Toast.LENGTH_LONG).show();
            } else {
                String result = "";
                while (j < i) {
                    for (int z = 0; z < 11; z++) {
                        result += cur.getString(z) + ",,";
                    }
                    str.add(result);
                    cur.moveToNext();
                    j++;
                }
                String[] export = new String[str.size()];
                for (int y = 0; y < str.size(); y++) {
                    export[y] = str.get(y).toString();
                }
                ListAdapter ls = new List_item(this, export);
                lv.setAdapter(ls);
            }
        }

        ins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInsertion = new Intent(Display.this, DBInsertion.class);//يوديمه للامسيرت
                startActivity(goInsertion);

            }
        });

    }
}
