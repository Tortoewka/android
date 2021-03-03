package com.example.myandroidprojekt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private String firstFactor;
    private String secondFactor;
    private float Resultat = 0;
    TextView textViewResult;
    private String tvResultText = "0";
    private char Operation = ' ';
    TextView tvResult;

    private final View.OnClickListener muClickListener = (view) -> {
        switch (view.getId()) {
            case R.id.btn_c: {
                initCalc();
                break;
            }
            case R.id.btn_one: {
                addCharToParam("1");
                break;
            }
            case R.id.btn_two: {
                addCharToParam("2");
                break;
            }
            case R.id.btn_three: {
                addCharToParam("3");
                break;
            }
            case R.id.btn_four: {
                addCharToParam("4");
                break;
            }
            case R.id.btn_five: {
                addCharToParam("5");
                break;
            }
            case R.id.btn_six: {
                addCharToParam("6");
                break;
            }
            case R.id.btn_seven: {
                addCharToParam("7");
                break;
            }
            case R.id.btn_eight: {
                addCharToParam("8");
                break;
            }
            case R.id.btn_nine: {
                addCharToParam("9");
                break;
            }
            case R.id.btn_zero: {
                addCharToParam("0");
                break;
            }
            case R.id.btn_dot: {
                addCharToParam(".");
                break;
            }
            case R.id.btn_result: {
                if (Operation == '!') return;

                String resultText = "";
                try {
                    if (!firstFactor.equals("") && !secondFactor.equals("")) {
                        float first = Float.parseFloat(firstFactor);
                        float second = Float.parseFloat(secondFactor);

                        if (Operation != ' ' && tvResultText.contains("=")) {
                            first = Resultat;
                            firstFactor = "" + Resultat;
                            setTvResult("" + Resultat + Operation + secondFactor);
                        }
                        addCharToText(" = ");

                        switch (Operation) {
                            case '+':
                                Resultat = first + second;
                                break;
                            case '-':
                                Resultat = first - second;
                                break;
                            case '*':
                                Resultat = first * second;
                                break;
                            case '/':
                                if (second == 0) {
                                    throw new ArithmeticException("человек ты делишь на 0");
                                }
                                Resultat = first / second;
                                break;
                            default:
                                Resultat = 0;
                        }
                        resultText = "" + Resultat;
                        if (Float.isInfinite(Resultat)) {
                            throw new ArithmeticException("Переполнение");
                        }
                        resultText = splitZero(resultText);

                    }

                    addCharToText(resultText);
                } catch (Exception ex) {
                    addCharToText(" : " + ex.getMessage());
                    Operation = '!';
                }
                break;
            }
            case R.id.btn_multiplication: {
                if (Operation == '!' || Operation != ' ' || firstFactor.equals("")) return;

                Operation = '*';
                addCharToText(" * ");
                break;
            }
            case R.id.btn_addition: {
                if (Operation == '!' || Operation != ' ' || firstFactor.equals("")) return;
                Operation = '+';
                addCharToText(" + ");
                break;
            }
            case R.id.btn_subtraction: {
                if (Operation == '!' || Operation != ' ' || firstFactor.equals("")) return;
                Operation = '-';
                addCharToText(" - ");
                break;
            }
            case R.id.btn_division: {
                if (Operation == '!' || Operation != ' ' || firstFactor.equals("")) return;
                Operation = '/';
                addCharToText(" / ");
                break;

            }


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.textViewResult);
        Button btn_one = findViewById(R.id.btn_one);
        Button btn_c = findViewById(R.id.btn_c);
        Button btn_two = findViewById(R.id.btn_two);
        Button btn_three = findViewById(R.id.btn_three);
        Button btn_four = findViewById(R.id.btn_four);
        Button btn_five = findViewById(R.id.btn_five);
        Button btn_six = findViewById(R.id.btn_six);
        Button btn_seven = findViewById(R.id.btn_seven);
        Button btn_eight = findViewById(R.id.btn_eight);
        Button btn_nine = findViewById(R.id.btn_nine);
        Button btn_zero = findViewById(R.id.btn_zero);
        Button btn_addition = findViewById(R.id.btn_addition);
        Button btn_subtraction = findViewById(R.id.btn_subtraction);
        Button btn_multiplication = findViewById(R.id.btn_multiplication);
        Button btn_division = findViewById(R.id.btn_division);
        Button btn_dot = findViewById(R.id.btn_dot);
        Button btn_result = findViewById(R.id.btn_result);

        btn_one.setOnClickListener(muClickListener);
        btn_c.setOnClickListener(muClickListener);
        btn_two.setOnClickListener(muClickListener);
        btn_three.setOnClickListener(muClickListener);
        btn_four.setOnClickListener(muClickListener);
        btn_five.setOnClickListener(muClickListener);
        btn_six.setOnClickListener(muClickListener);
        btn_seven.setOnClickListener(muClickListener);
        btn_eight.setOnClickListener(muClickListener);
        btn_nine.setOnClickListener(muClickListener);
        btn_zero.setOnClickListener(muClickListener);
        btn_addition.setOnClickListener(muClickListener);
        btn_division.setOnClickListener(muClickListener);
        btn_multiplication.setOnClickListener(muClickListener);
        btn_subtraction.setOnClickListener(muClickListener);
        btn_result.setOnClickListener(muClickListener);
        btn_dot.setOnClickListener(muClickListener);
    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    private final View.OnClickListener keyBackClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (Operation == '!') return;

            if (tvResultText.contains("=")) return;

            if (tvResultText.length() == 1) {
                firstFactor = "";
                setTvResult("0");
            } else if (
                    tvResultText.charAt(tvResultText.length() - 1) != '+' && tvResultText.charAt(tvResultText.length() - 1) != '*' &&
                            tvResultText.charAt(tvResultText.length() - 1) != '/' && tvResultText.charAt(tvResultText.length() - 1) != '-'
            ) {
                if (Operation == ' ') {
                    if (!firstFactor.equals("")) {
                        firstFactor = firstFactor.substring(0, firstFactor.length() - 1);
                    }
                } else {
                    if (!secondFactor.equals("")) {
                        secondFactor = secondFactor.substring(0, secondFactor.length() - 1);
                    }
                }
                setTvResult(tvResultText.substring(0, tvResultText.length() - 1));
            } else {
                Operation = ' ';
                secondFactor = "";
                setTvResult(tvResultText.substring(0, tvResultText.length() - 1));
            }
        }
    };


    private String splitZero(String resultText) {
        boolean exist0 = false;
        if (resultText.contains(".") || resultText.contains(",")) {
            exist0 = true;
        }
        while (exist0) {
            if (resultText.charAt(resultText.length() - 1) == '0' && resultText.length() != 1) {
                resultText = resultText.substring(0, resultText.length() - 1);
            } else if (resultText.charAt(resultText.length() - 1) == ',' || resultText.charAt(resultText.length() - 1) == '.') {
                resultText = resultText.substring(0, resultText.length() - 1);
                exist0 = false;
            } else {
                exist0 = false;
            }
        }
        return resultText;
    }


    private void addCharToParam(String key) {
        if (tvResultText.contains("=")) {
            initCalc();
        }
        if (Operation == '!') {
            return;
        } else if (Operation == ' ') {
            if ((!key.equals(".") || !firstFactor.contains(".")) && firstFactor.length() < 15) {
                if (firstFactor.length() == 0 && key.equals(".")) key = "0" + key;
                firstFactor += key;
                addCharToText(key);
            }
        } else {
            if ((!key.equals(".") || !secondFactor.contains(".")) && secondFactor.length() < 15) {
                if (secondFactor.length() == 0 && key.equals(".")) key = "0" + key;
                secondFactor += key;
                addCharToText(key);
            }
        }


    }


    private void addCharToText(String key) {

        if (textViewResult.getText() == "0") {
            setTvResult(key);
        } else {
            setTvResult(textViewResult.getText() + key);
        }
    }


    private void setTvResult(String text) {
        tvResultText = text;
        textViewResult.setText(text);

    }


    private void initCalc() {
        firstFactor = "";
        secondFactor = "";
        Resultat = 0f;
        Operation = ' ';
        setTvResult("0");
    }

}
