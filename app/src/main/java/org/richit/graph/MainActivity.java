package org.richit.graph;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
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

    String TAG = this.getClass().getSimpleName();
    ArrayList<Entry> numbers = new ArrayList<Entry>(  );
    Button addElementButton;
    int count = 0;
    LineDataSet lineDataSet;
    LineData lineData;
    LineChart chart;
    XAxis xAxis;
    YAxis left;
    EditText inputDataET;
    Button saveButton;
    String num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        addElementButton = findViewById( R.id.addElement );
        addElementButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = LayoutInflater.from( MainActivity.this ).inflate( R.layout.input_data, null );
                inputDataET = view.findViewById( R.id.rateDayET );
                saveButton = view.findViewById( R.id.saveButton );
                final AlertDialog alertDialog = new
                        AlertDialog.Builder( MainActivity.this )
                        .setTitle( "Input " )
                        .setView( view )
                        .show();

                saveButton.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        num = inputDataET.getText().toString();
                        if (num != null && !num.isEmpty()){
                            numbers.add( new Entry( (float) count++, (float) Float.parseFloat( inputDataET.getText().toString().trim() ) ) );
                            setGraph();
                            alertDialog.dismiss();
                        }
                        else {
                            Log.d( TAG, "onClick: " + "faul" );
                        }
                    }
                } );

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
        lineDataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        lineDataSet.setCubicIntensity( 0.1f );
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
