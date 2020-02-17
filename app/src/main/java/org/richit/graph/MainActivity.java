package org.richit.graph;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<Entry> numbers = new ArrayList<Entry>(  );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        for (int i=0; i<10; i++){
            numbers.add( new Entry( i, (float)i*5 ) );
        }
        LineDataSet lineDataSet = new LineDataSet( numbers, "Example" );
        lineDataSet.setColor( R.color.colorAccent);
        LineData lineData = new LineData(lineDataSet);
        LineChart chart = findViewById( R.id.chart );
        chart.setData(lineData);
        chart.invalidate();
    }
}
