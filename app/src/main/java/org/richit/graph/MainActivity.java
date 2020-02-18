package org.richit.graph;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<Entry> numbers = new ArrayList<Entry>(  );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        for (float i=0; i<=480; i=i+10f){
            numbers.add( new Entry( i, (float)(Math.sin( (double) i*3.1416/180 )+5) ) );
        }
        //todo: lineDataSet
        LineDataSet lineDataSet = new LineDataSet( numbers, "Example" );
        lineDataSet.setColor( Color.RED );
        lineDataSet.setDrawFilled( true );
        lineDataSet.setFillColor( Color.RED );

        //todo: LineData
        LineData lineData = new LineData(lineDataSet);

        //todo: LineChart
        LineChart chart = findViewById( R.id.chart );
        chart.setData(lineData);
        chart.invalidate();

        //todo: XAxis
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition( XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(10f);
        xAxis.setTextColor(Color.GRAY);
//        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        // set a custom value formatter
        xAxis.setValueFormatter( new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return "Day "+(int)value;
            }
        } );

        //todo: YAxis
        YAxis left = chart.getAxisLeft();
        left.setDrawLabels(false); // no axis labels
        left.setDrawAxisLine(false); // no axis line
        left.setDrawGridLines(false); // no grid lines
        left.setDrawZeroLine(true); // draw a zero line
        chart.getAxisRight().setEnabled(false); // no right axis

    }
}
