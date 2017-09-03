package com.example.sobiya.tablelayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i=101;i<=124;i++) {
            TableLayout tb = (TableLayout) findViewById(R.id.tb);
            TableRow tr = new TableRow(this);
            TextView tv0 = new TextView(this);
            tv0.setText("  1  ");
            tv0.setPadding(20, 0, 0, 0);
            tr.addView(tv0);
            TextView tv1 = new TextView(this);
            tv1.setText("  192.168.0."+i+" ");
            tv1.setPadding(20, 0, 0, 0);
            tr.addView(tv1);
            TextView tv2 = new TextView(this);
            tv2.setText("  "+i+"  ");
            tv2.setPadding(20, 0, 0, 0);
            tr.addView(tv2);
            TextView tv3 = new TextView(this);
            tv3.setText(" ON ");
            tv3.setPadding(20, 0, 0, 0);
            tr.addView(tv3);
            TextView tv4 = new TextView(this);
            tv4.setText(" 2017-08-27 07:56:29 ");
            tv4.setPadding(20, 0, 0, 0);
            tr.addView(tv4);
            tb.addView(tr);
        }

    }
}
