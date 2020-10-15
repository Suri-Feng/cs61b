import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by hug.
 */
public class Experiments {

    public static void experiment1() {

            Random r = new Random();
            List<Double> yValues = new ArrayList<>();
            List<Double> y2Values = new ArrayList<>();
            List<Integer> xValues = new ArrayList<>();

            BST b = new BST();
            ExperimentHelper eh = new ExperimentHelper();
            for (int x = 0; x < 5000; x += 1) {

                xValues.add(x);

                b.add(r.nextInt());
                double thisY = b.averageDepth();
                yValues.add(thisY);

                double thisY2 = eh.optimalAverageDepth(x);
                y2Values.add(thisY2);

                System.out.println(b.size());
            }

            XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("N").yAxisTitle("average depth").build();
            chart.addSeries("Average Depth vs. N", xValues, yValues);
            chart.addSeries("Optimal Average Depth vs. N", xValues, y2Values);

            new SwingWrapper(chart).displayChart();

    }

    public static void experiment2() {

        Random r = new Random();
        List<Double> yValues = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();

        BST b = new BST();
        ExperimentHelper eh = new ExperimentHelper();

        for (int x = 0; x < 5000; x += 1) {
            b.add(r.nextInt());
        }

        for (int x = 0; x < 4500; x += 1) {

            xValues.add(x);
            //b.getRandomKey();
            eh.insertNDelete(b);
            double thisY = b.averageDepth();
            yValues.add(thisY);

        }

        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("No. of Operations").yAxisTitle("average depth").build();
        chart.addSeries("Average Depth vs. No. of Operations", xValues, yValues);

        new SwingWrapper(chart).displayChart();


    }

    public static void experiment3() {


        Random r = new Random();
        List<Double> yValues = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();

        BST b = new BST();
        ExperimentHelper eh = new ExperimentHelper();

        for (int x = 0; x < 5000; x += 1) {
            b.add(r.nextInt());
        }

        for (int x = 0; x < 4500; x += 1) {

            xValues.add(x);
            //b.getRandomKey();
            eh.insertNDelete2(b);
            double thisY = b.averageDepth();
            yValues.add(thisY);

        }

        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("No. of Operations").yAxisTitle("average depth").build();
        chart.addSeries("Average Depth vs. No. of Operations", xValues, yValues);

        new SwingWrapper(chart).displayChart();


    }

    public static void main(String[] args) {
        //experiment1();
        //experiment2();
        experiment3();
    }
}
