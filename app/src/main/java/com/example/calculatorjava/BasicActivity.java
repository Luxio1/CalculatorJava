package com.example.calculatorjava;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.mariuszgromada.math.mxparser.*;

public class BasicActivity extends AppCompatActivity {
    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9,
            buttonAC, buttonC, buttonMultiply, buttonDivide, buttonDot,
            buttonPlus, buttonMinus, buttonPlusMinus, buttonEquals,
            buttonBracket;

    TextView textExpression, textResult;
    Boolean rightBracket; // false - left true - right

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic);

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);

        buttonAC = (Button) findViewById(R.id.buttonAC);
        buttonC = (Button) findViewById(R.id.buttonC);
        buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
        buttonDivide = (Button) findViewById(R.id.buttonDivide);
        buttonDot = (Button) findViewById(R.id.buttonDot);
        buttonPlus = (Button) findViewById(R.id.buttonExit);
        buttonMinus = (Button) findViewById(R.id.buttonMinus);
        buttonPlusMinus = (Button) findViewById(R.id.buttonPlusMinus);
        buttonEquals = (Button) findViewById(R.id.buttonEquals);
        buttonBracket = (Button) findViewById(R.id.buttonBracket);

        textExpression = (TextView) findViewById(R.id.textExpression);
        textResult = (TextView) findViewById(R.id.textResult);

        rightBracket = false;

        clearAll();

        if (savedInstanceState != null) {
            textExpression.setText(savedInstanceState.getString("expression"));
            textResult.setText(savedInstanceState.getString("result"));
            rightBracket = savedInstanceState.getBoolean("bracket");
        }

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeExpression("0");
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeExpression("1");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeExpression("2");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeExpression("3");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeExpression("4");
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeExpression("5");
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeExpression("6");
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeExpression("7");
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeExpression("8");
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeExpression("9");
            }
        });

        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char last = getLastExpressionSign();
                if (!Character.isDigit(last)) {
                    deleteLastCharacter();
                }
                writeExpression("/");
            }
        });

        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char last = getLastExpressionSign();
                if (!Character.isDigit(last)) {
                    deleteLastCharacter();
                }
                writeExpression("*");
            }
        });

        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char last = getLastExpressionSign();
                if (!Character.isDigit(last)) {
                    deleteLastCharacter();
                }
                writeExpression("+");
            }
        });

        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char last = getLastExpressionSign();
                if (!Character.isDigit(last)) {
                    deleteLastCharacter();
                }
                writeExpression("-");
            }
        });

        buttonPlusMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char lastSign = getLastExpressionSign();
                if (lastSign == '+') {
                    deleteLastCharacter();
                    writeExpression("-");
                } else if (lastSign == '-') {
                    deleteLastCharacter();
                    writeExpression("+");
                } else {
                    char last = getLastExpressionSign();
                    if (!Character.isDigit(last)) {
                        deleteLastCharacter();
                    }
                    writeExpression("+");
                }
            }
        });

        buttonAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAll();
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearExpression();
                textExpression = textResult;
            }
        });

        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char last = getLastExpressionSign();
                if (!Character.isDigit(last)) {
                    deleteLastCharacter();
                }
                writeExpression(".");
            }
        });

        buttonBracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char last;
                if (textExpression.getText().toString().length() > 0) {
                    last = getLastExpressionSign();
                } else {
                    last = 'x'; //to make last not '(' and not ')'
                }

                if (last != '(' && last != ')') {
                    if (!rightBracket) {
                        writeExpression("(");
                        rightBracket = true;
                    } else {
                        writeExpression(")");
                        rightBracket = false;
                    }
                }
            }
        });

        buttonEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String expression = textExpression.getText().toString();

                Expression e = new Expression(expression);
                String result = String.valueOf(e.calculate());
                String err = "Wrong expression";
                if(result.contentEquals("NaN")){
                    textExpression.setText("");
                    textResult.setText(err);
                } else {
                    textResult.setText(result);
                    textExpression.setText(result);
                }
            }
        });

    }

    public void writeExpression(String value) {
        String expression = textExpression.getText().toString();
        expression = expression + value;
        textExpression.setText(expression);
    }

    public void deleteLastCharacter() {
        String expression = textExpression.getText().toString();
        String trimmedExpression = expression.substring(0, expression.length() - 1);
        textExpression.setText(trimmedExpression);
    }

    public void clearExpression() {
        textExpression.setText("");
    }

    public void clearResult() {
        textResult.setText("");

    }

    public void clearAll() {
        clearExpression();
        clearResult();
    }

    public char getLastExpressionSign() {
        String expression = textExpression.getText().toString();
        return expression.charAt(expression.length() - 1);
    }

    public boolean isLastSignDigit() {
        char lastSign = getLastExpressionSign();
        return Character.isDigit(lastSign);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        //outState.putString();
        super.onSaveInstanceState(outState);
        outState.putString("expression", textExpression.getText().toString());
        outState.putString("result", textResult.getText().toString());
        outState.putBoolean("bracket", rightBracket);
    }


}
