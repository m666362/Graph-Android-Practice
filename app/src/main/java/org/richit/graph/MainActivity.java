package org.richit.graph;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

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
    Button addElementButton;
    int count = 0;
    LineDataSet lineDataSet;
    LineData lineData;
    LineChart chart;
    XAxis xAxis;
    YAxis left;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        addElementButton = findViewById( R.id.addElement );
        addElementButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numbers.add( new Entry( (float) count, (float) Math.cos( (double) count*3.1416/180 ) ) );
                setGraph();
                count = count + 30;
            }
        } );


        //Button

    }

    private void setGraph() {
        //todo: lineDataSet
        lineDataSet = new LineDataSet( numbers, "Example" );
        lineDataSet.setColor( Color.RED );
        lineDataSet.setDrawFilled( true );
        lineDataSet.setFillColor( Color.RED );

        //todo: LineData
        lineData = new LineData(lineDataSet);

        //todo: LineChart
        chart = findViewById( R.id.chart );
        chart.setData(lineData);
        chart.invalidate();

        //todo: XAxis
        xAxis = chart.getXAxis();
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
        left = chart.getAxisLeft();
        left.setDrawLabels(false); // no axis labels
        left.setDrawAxisLine(false); // no axis line
        left.setDrawGridLines(false); // no grid lines
        left.setDrawZeroLine(true); // draw a zero line
        chart.getAxisRight().setEnabled(false); // no right axis
    }

    public void addElement(View view) {
        Toast.makeText( this, ""+count++, Toast.LENGTH_SHORT ).show();
    }
}
