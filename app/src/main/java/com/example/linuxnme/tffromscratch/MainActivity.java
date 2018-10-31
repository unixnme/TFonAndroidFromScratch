
package com.example.linuxnme.tffromscratch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.tensorflow.contrib.android.TensorFlowInferenceInterface;

public class MainActivity extends AppCompatActivity {

    private static final String MODEL_FILE ="file:///android_asset/_basicTFonAndroid.pb";
    private static final String[] INPUT_NODES = {"modelInputA", "modelInputB"};
    private static final String[] OUTPUT_NODES = {"modelOutputAB"};
    private static final int[] INPUT_DIM = {1};
    private TensorFlowInferenceInterface inferenceInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView) findViewById(R.id.textview);
        float[] modelInputA = {3};
        float[] modelInputB = {4};

        inferenceInterface = new TensorFlowInferenceInterface(getAssets(), MODEL_FILE);
        inferenceInterface.feed(INPUT_NODES[0], modelInputA, INPUT_DIM[0]);
        inferenceInterface.feed(INPUT_NODES[1], modelInputB, INPUT_DIM[0]);
        inferenceInterface.run(OUTPUT_NODES);
        float[] modelOutputAB = new float[1];
        inferenceInterface.fetch(OUTPUT_NODES[0], modelOutputAB);

        textView.setText(String.valueOf(modelOutputAB[0]));
    }
}
