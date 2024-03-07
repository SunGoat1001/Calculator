package com.example.easycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tvResult, tvSolution;
    MaterialButton btnAC, btnBrackOp, btnBrackCl;
    MaterialButton btnDivided, btnMultiply, btnPlus, btnMinus, btnEquals;
    MaterialButton btn0, btn1, btn2, btn3, btn4, tbn5, btn6, btn7, btn8, btn9;
    MaterialButton btnDot, btnBackSpace;
    MaterialButton btnHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult = findViewById(R.id.result_tv);
        tvSolution = findViewById(R.id.solution_tv);

        assignId(btnAC, R.id.ac_button);
        assignId(btnBrackOp, R.id.open_bracket_button);
        assignId(btnBrackCl, R.id.close_bracket_button);
        assignId(btnDivided, R.id.divided_button);
        assignId(btnMultiply, R.id.times_button);
        assignId(btnPlus, R.id.add_button);
        assignId(btnMinus, R.id.minus_button);
        assignId(btnEquals, R.id.equals_button);
        assignId(btn0, R.id.zero_button);
        assignId(btn1, R.id.one_butotn);
        assignId(btn2, R.id.two_button);
        assignId(btn3, R.id.three_button);
        assignId(btn4, R.id.four_button);
        assignId(tbn5, R.id.five_button);
        assignId(btn6, R.id.six_button);
        assignId(btn7, R.id.seven_button);
        assignId(btn8, R.id.eight_button);
        assignId(btn9, R.id.nine_button);
        assignId(btnDot, R.id.dot_button);
        assignId(btnBackSpace, R.id.backspace_button);
    }

    void assignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MaterialButton button = (MaterialButton) v;
        String buttonText = button.getText().toString();
        String dataToCalculate = tvSolution.getText().toString();

        if(buttonText.equals("AC")) {
            tvSolution.setText("");
            tvResult.setText("");
            return;
        }

        if(buttonText.equals("=")) {
            tvSolution.setText(tvResult.getText());
            tvResult.setText("");
            return;
        }

        if(buttonText.equals("C")) {
            if (dataToCalculate.length() >= 1) {
                dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
            }
        } else {
            dataToCalculate = dataToCalculate + buttonText;
        }
        tvSolution.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if (!finalResult.equals("Err")) {
            tvResult.setText(finalResult);
        }
    }

    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if (finalResult.endsWith(".0")) {
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        } catch (Exception e) {
            return "Err";
        }
    }
}