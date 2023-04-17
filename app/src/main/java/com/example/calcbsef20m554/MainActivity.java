package com.example.calcbsef20m554;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultTv,solutionTv;
    MaterialButton buttonC,buttonBrackOpen,buttonBrackClose;
    MaterialButton buttonDivide,buttonMultiply,buttonPlus,buttonMinus,buttonEquals;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAC,buttonDot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.result_tv);

        assignID(buttonC,R.id.button_c);
        assignID(buttonBrackOpen,R.id.button_open_bracket);
        assignID(buttonBrackClose,R.id.button_close_bracket);
        assignID(buttonDivide,R.id.button_divide);
        assignID(buttonMultiply,R.id.button_multiply);
        assignID(buttonPlus,R.id.button_plus);
        assignID(buttonMinus,R.id.button_minus);
        assignID(buttonEquals,R.id.button_equal);
        assignID(button0,R.id.button_zero);
        assignID(button1,R.id.button_one);
        assignID(button2,R.id.button_two);
        assignID(button3,R.id.button_three);
        assignID(button4,R.id.button_four);
        assignID(button5,R.id.button_open_five);
        assignID(button6,R.id.button_close_six);
        assignID(button7,R.id.button_seven);
        assignID(button8,R.id.button_open_eight);
        assignID(button9,R.id.button_close_nine);
        assignID(buttonAC,R.id.button_ac);
        assignID(buttonDot,R.id.button_dot);





    }

    void assignID(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        MaterialButton button =(MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = resultTv.getText().toString();

        if(buttonText.equals("AC")){
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            resultTv.setText(resultTv.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else{
            dataToCalculate = dataToCalculate+buttonText;
        }
        resultTv.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Err")){
            solutionTv.setText(finalResult);
        }

    }

    String getResult(String data){
        try{
            Context context  = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }

}