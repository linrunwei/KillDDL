package app.killddl.killddl;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

//import com.jjoe64.graphview.GraphView;
//import com.jjoe64.graphview.ValueDependentColor;
//import com.jjoe64.graphview.series.BarGraphSeries;
//import com.jjoe64.graphview.series.DataPoint;

public class AnalyticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);
//        GraphView graph = (GraphView) findViewById(R.id.analytics_graph);
//        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[] {
//                new DataPoint(0, -1),
//                new DataPoint(1, 5),
//                new DataPoint(2, 3),
//                new DataPoint(3, 2),
//                new DataPoint(4, 6)
//        });
//        graph.addSeries(series);
//
//// styling
//        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
//            @Override
//            public int get(DataPoint data) {
//                return Color.rgb((int) data.getX()*255/4, (int) Math.abs(data.getY()*255/6), 100);
//            }
//        });
//
//        series.setSpacing(50);
//
//// draw values on top
//        series.setDrawValuesOnTop(true);
//        series.setValuesOnTopColor(Color.RED);
////series.setValuesOnTopSize(50);
    }
}

