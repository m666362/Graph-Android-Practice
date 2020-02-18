
package org.richit.graph;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class PieActivity extends AppCompatActivity {

    String TAG = this.getClass().getSimpleName();
    ArrayList<PieEntry> numbers = new ArrayList<PieEntry>(  );
    Button addElementpieButton;
    int count = 0;
    PieDataSet pieDataSet;
    PieData pieData;
    PieChart pieChart;
    XAxis xAxis;
    YAxis left;
    EditText inputPieDataET;
    Button savePieButton;
    String num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_pie );

        addElementpieButton = findViewById( R.id.addElementpie );
        addElementpieButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = LayoutInflater.from( PieActivity.this ).inflate( R.layout.input_data, null );
                inputPieDataET = view.findViewById( R.id.rateDayET );
                savePieButton = view.findViewById( R.id.saveButton );
                final AlertDialog alertDialog = new
                        AlertDialog.Builder( PieActivity.this )
                        .setTitle( "Input " )
                        .setView( view )
                        .show();

                savePieButton.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        num = inputPieDataET.getText().toString();
                        if (num != null && !num.isEmpty()){
                            numbers.add( new PieEntry( (float) Float.parseFloat( inputPieDataET.getText().toString().trim() ), "Data" + ++count ) );
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

    }


    private void setGraph() {
        //todo: lineDataSet
        pieDataSet = new PieDataSet( numbers, "Pie Example" );
        pieDataSet.setColor( Color.RED );
        //todo: LineData
        pieData = new PieData(pieDataSet);

        //todo: LineChart
        pieChart = findViewById( R.id.pieChart );
        pieChart.setData(pieData);
        pieDataSet.setColors( ColorTemplate.COLORFUL_COLORS);
        pieChart.animateXY(5000, 5000);
        pieChart.invalidate();

    }

}
