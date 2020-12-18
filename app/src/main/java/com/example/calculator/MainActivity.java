package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //regioon Muutujate deklareerimine: boolean muutujai on vaja operaatori valiku jaoks, double muutujad on valitud numbri väärtuste jaoks
    //txtResult on tüübi poolest TextView komponent ehk sellega tehakse tööd.
    boolean multiply, divide, subtract, addition, exponent, remainder;
    double value1, value2;
    private TextView result;
    //endregion


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Komponendi vaate sidumine koodi poolega.
        result = findViewById(R.id.txt_result);

    }

    public void onDigitValue(View view) {
        //region Nuppude tegevus-numbrid. Kui kasutaja on numbri nupule vajutanud, siis kirjutatakse selle väärtus tekstiväljale.
        //Koma märgi puhul kontrollitakse, kas kasutaja on juba ühe korra koma kasutanud või mitte. Puhasta (C) märgi kasutamise puhul
        //kirjutatakse tekstiväli tühja väärtusega üle ja nullitakse muutujate väärtused.
        switch (view.getId()){
            case R.id.button0:
                result.setText(String.format("%s0", result.getText()));
                break;
            case R.id.button1:
                result.setText(String.format("%s1", result.getText()));
                break;
            case R.id.button2:
                result.setText(String.format("%s2", result.getText()));
                break;
            case R.id.button3:
                result.setText(String.format("%s3", result.getText()));
                break;
            case R.id.button4:
                result.setText(String.format("%s4", result.getText()));
                break;
            case R.id.button5:
                result.setText(String.format("%s5", result.getText()));
                break;
            case R.id.button6:
                result.setText(String.format("%s6", result.getText()));
                break;
            case R.id.button7:
                result.setText(String.format("%s7", result.getText()));
                break;
            case R.id.button8:
                result.setText(String.format("%s8", result.getText()));
                break;
            case R.id.button9:
                result.setText(String.format("%s9", result.getText()));
                break;
            case R.id.btn_coma:
                if(!result.getText().toString().contains("."))
                    result.setText(String.format("%s.", result.getText()));
                break;
            case R.id.btn_clr:
                result.setText("");
                value1 = 0.0;
                value2 = 0.0;
                break;
        }
        //endregion

    }

    public void onOperator(View view) {
        //region Tehete märgid.
        /*Kasutaja poolt sisestatud arv määratakse muutuja value1 väärtuseks kuna input tuleb String tüübina, siis on vaja tehete sooritamiseks
        * see teisendada, seejärel puhastatakse sisestusväli. Switch/case kasutatakse selleks, et määrata millist tehet kasutaja sooritab- kasutatakse boolean */
        if(result.length()==0) return;
        try {
            value1 = Double.parseDouble(result.getText().toString());
            result.setText("");
            switch (view.getId()){
                case R.id.btn_pls:
                    addition = true;// +
                    break;
                case R.id.btn_min:
                    subtract = true;// -
                    break;
                case R.id.btn_mul:
                    multiply = true;// *
                    break;
                case R.id.btn_div:
                    divide = true;// /
                    break;
                case R.id.btn_remain:
                    remainder = true;// %
                    break;
                case R.id.btn_expo:
                    exponent = true;// ^
                    break;

            }
        }catch (NumberFormatException ex){ ex.printStackTrace(); }
        //endregion
    }

    public void onEnter(View view) {
        //region Võrdusmärk.
        /*Kui kasutaja soovib vastust saada, siis määratakse kasutaja poolt sisestatud arv muutuja value2 väärtuseks, jälle tuleb see teisendada
        * korrektsesse andmetüüpi, et oleks võimalik sooritada matemaatiline tehe. Vastavalt boolean väätrusele valitakse arvutus tehe, mida sooritatakse.
        * Tehte väärtust kuvatakse kasutajale tekstiväljal ning lõpus muudetakse booleani värtus tagasi algsesse*/
        if(result.length()==0) return;
        try{
            value2 = Double.parseDouble(result.getText().toString());
            if(addition){
                result.setText(String.valueOf(value1 + value2));
                addition = false;
            }
            if(subtract){
                result.setText(String.valueOf(value1 - value2));
                subtract = false;
            }
            if(multiply){
                result.setText(String.valueOf(value1 * value2));
                multiply = false;
            }
            if(divide){
                result.setText(String.valueOf(value1 / value2));
                divide = false;
            }
            if(remainder){
                result.setText(String.valueOf(value1 % value2));
                remainder = false;
            }
            if(exponent){
                result.setText(String.valueOf(Math.pow(value1, value2)));
                exponent = false;
            }

        }catch (NumberFormatException ex){ ex.printStackTrace(); }
        //endregion
    }

    public void onPosNeg(View view) {
        if(result.length()==0) return;
        try {
            double resValue=Double.parseDouble(result.getText().toString());
            if (resValue > 0) result.setText(String.valueOf(resValue * -1));
            else result.setText(String.valueOf(Math.abs(resValue)));
        }catch (NumberFormatException ex){ ex.printStackTrace(); }
    }

    public void onSqrt(View view) {
        if(result.length()==0) return;
        try {
            value1=Double.parseDouble(result.getText().toString());
            if (Double.isNaN(Math.sqrt(value1))){
                result.setText(getResources().getString(R.string.err));
            }else{
                result.setText(String.valueOf(Math.sqrt(value1)));
            }

        }catch (NumberFormatException ex){ ex.printStackTrace(); }
    }
}