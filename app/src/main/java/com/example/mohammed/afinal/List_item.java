package com.example.mohammed.afinal;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

class List_item extends ArrayAdapter<String> {
    Button re;//بتن الحجز
    int selected;//عدد المفاعد المحجوزة
    int scost;//سنكل كوست
    int tcost;//توتل كوست
    DBnew mdb;
    int id;
    TextView tvv; //عرض عدد المفاعد المحجوزة
    TextView tvvv;//عرض السنكل كوست
    TextView tvvvv;//عرض التوتل كوست
    SQLiteDatabase db;

    List_item(Context context, String[] flights) {
        super(context, R.layout.list_item, flights);

        mdb = new DBnew(context, "DB", null, 1);//ناخذ انستانس من الداتابيس اللي سويناهه
        db = mdb.getReadableDatabase();//ناخذ نسخة قابلة للقراءة من الداتا بيس
    }

    int count = 0;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inf = LayoutInflater.from(getContext());//حتى اسوي اجيب اللست ايتم

        final View cust = inf.inflate(R.layout.list_item, parent, false);//اسوي انفليت للست ايتم
        re = cust.findViewById(R.id.re);//حتى نعين البتن الموجود بالايتم


        final String item = getItem(position);//نجيب مكان الايتم يعني تسلسله

        final String[] items = item.split(",,");//راح نسوي سبلت للبيانات اللي جاية
        final int poss = position;//اخذنه نسخة من الموقع لان المتغير الاساسي ممكن يتغير

        re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showw(items, poss);//فنكشن تطلعلنه الاليرت
            }
        });



        count = position * 11 + 1;//منا راح نشوف موفع البيانات اللي نريديهن من السترنك الحاوي على معلومات الكويري اللي من الداتا بيس
        int company = Integer.valueOf(items[count]);//اول موقع
        String origin = items[count + 1];
        String distination = items[count + 2];
        String plane = items[count + 3];
        String flightNum = items[count + 4];
        String flightName = items[count + 5];
        String LandingDate = items[count + 6];
        String seats = items[count + 7];
        String service = items[count + 8];
        String cost = items[count + 9];//لحد هنا استخدمنهه 10 مواقع، لازم نزود موقع اضافي علمود حقل الايدي لان منريد نعرضه بس هو موجود

        //راح نجيب العناصر الموجودة بالليوت و نخليهه بمتغيرات
        ImageView img_company = cust.findViewById(R.id.comp);
        TextView tv_origin = (TextView) cust.findViewById(R.id.origin);
        TextView tv_distination = (TextView) cust.findViewById(R.id.distination);
        TextView tv_plane = (TextView) cust.findViewById(R.id.plain);
        TextView tv_flightNum = (TextView) cust.findViewById(R.id.flt_num);
        TextView tv_flightName = (TextView) cust.findViewById(R.id.flt_name);
        TextView tv_LandingDate = (TextView) cust.findViewById(R.id.lnd_date);
        TextView tv_seats = (TextView) cust.findViewById(R.id.seats);
        TextView tv_service = (TextView) cust.findViewById(R.id.service);
        TextView tv_cost = (TextView) cust.findViewById(R.id.cost);
        //Hallilouia !!  @_@

        //نحط المعلومات بمتغيرات عناصر الليوت
        if (company == 1) {//نحدد الشركة المختارة حتى نجيب الصورة مالتهه
            img_company.setBackgroundResource(R.drawable.iraqiah);

        } else if (company == 2) {
            img_company.setBackgroundResource(R.drawable.fly);

        } else if (company == 3) {
            img_company.setBackgroundResource(R.drawable.egiptair);

        } else if (company == 4) {
            img_company.setBackgroundResource(R.drawable.emirates);

        } else if (company == 5) {
            img_company.setBackgroundResource(R.drawable.qatar);

        }

        tv_origin.setText(origin);
        tv_distination.setText(distination);
        tv_plane.setText(plane);
        tv_flightNum.setText(flightNum);
        tv_flightName.setText(flightName);
        tv_LandingDate.setText(LandingDate);
        tv_seats.setText(seats);
        tv_service.setText(service);
        tv_cost.setText(cost);

        //نزود البزشن
        position++;
        return cust;//ارجع اللست ايتم الكامل
    }



    public void showw(String[] items, int position) {//فويد الشو الخاص باختيا عدد المفاعد و تحديد الكلفة الكلية

        int count = 0;

        count = position * 11 + 1;
        id = Integer.parseInt(items[count - 1]);
        scost = Integer.parseInt(items[count + 9]);
        final int seats = Integer.parseInt(items[count + 7]);//عدد المفاعد من الداتا بيس

        AlertDialog.Builder adb = new AlertDialog.Builder(getContext());//سوينه اليرت بلدر
        adb.setTitle("Booking tickets");//التايتل مالته
        LayoutInflater inf = LayoutInflater.from(getContext());//جبنه اللست ايتم

        final View win = inf.inflate(R.layout.window, null, false);//سوناله افليت
        adb.setView(win);//خلينه عناصر بالاليرت
        //جبنه العناصر خليناهن بمتغيرات
        SeekBar seekBar = win.findViewById(R.id.seatsNum);
        Button button = win.findViewById(R.id.Reserv);
        tvv = win.findViewById(R.id.sel);
        tvvvv = win.findViewById(R.id.scost);
        tvvv = win.findViewById(R.id.tcost);

        tvvvv.setText(scost + "$");//عرضنه السنكل كوست
        seekBar.setMax(seats);//نسوي الماكس مال السسيكبار على عدد المفاعد اللي عدنه
        selected = 0;
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {//نسوي جينج لسنر على السيك بار
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                selected = i;
                tcost = i * scost;//نغير التوتل كوست بكل مرة نغير بيهه قيمة السيك بار
                tvv.setText("" + i);//نعرض عدد المقاعد المختارة
                tvvv.setText(tcost + "$");//نعرض التزتل برايس

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        adb.show();//نعرض الاليرت

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = seats - selected;
                upup(num, id);

                //نبعث المعلومات بايميل
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, "someone@gmail.com");
                intent.putExtra(Intent.EXTRA_CC, "someone@gmail.com");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Booking Ticket");
                intent.putExtra(Intent.EXTRA_TEXT, "number of seats to reserve " + selected + "\n \n With Total Cost of :" + tcost + "$");
                intent.setType("message/rfc822");

                getContext().startActivity(Intent.createChooser(intent, "Send mail..."));


            }


        });
    }


    public void upup(int seats, int id) {
        db.execSQL("UPDATE FL SET SEATS=" + seats + " WHERE id=" + id + ";");//نسوي ابديت لعدد المفاعد  بالداتابيس
    }
}
