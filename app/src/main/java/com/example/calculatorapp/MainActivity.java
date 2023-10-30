package com.example.calculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    private String currentText = "";
    private String operator = "";
    private double operand1 = 0.0;
    private boolean newInput = true; // Flag to track new input

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        initializeDigitButtons();
        initializeOperatorButtons();
        initializeSpecialButtons();
    }

    private void initializeDigitButtons() {
        // Initialize buttons for digits (0-9)
        for (int i = 0; i <= 9; i++) {
            int buttonId = getResources().getIdentifier("button" + i, "id", getPackageName());
            Button button = findViewById(buttonId);

            final int digit = i; // Create a final copy of i

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handleDigitClick(digit);
                }
            });
        }
    }

    private void initializeOperatorButtons() {
        // Initialize operator buttons (+, -, *, /)
        Button buttonAdd = findViewById(R.id.buttonAdd);
        Button buttonSubtract = findViewById(R.id.buttonSubtract);
        Button buttonMultiply = findViewById(R.id.buttonMultiply);
        Button buttonDivide = findViewById(R.id.buttonDivide);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOperatorClick("+");
            }
        });

        buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOperatorClick("-");
            }
        });

        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOperatorClick("*");
            }
        });

        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOperatorClick("/");
            }
        });
    }

    private void initializeSpecialButtons() {
        // Initialize special buttons (Clear, Equals, Sign, Back, Sqrt)
        Button buttonClear = findViewById(R.id.buttonClear);
        Button buttonEquals = findViewById(R.id.buttonEquals);
        Button buttonSign = findViewById(R.id.buttonSign);
        Button buttonBack = findViewById(R.id.buttonBack);
        Button buttonSqrt = findViewById(R.id.buttonSqrt);

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearDisplay();
            }
        });

        buttonEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult();
            }
        });

        buttonSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSign();
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleBack();
            }
        });

        buttonSqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSqrt();
            }
        });
    }

    private void handleDigitClick(int digit) {
        if (newInput) {
            currentText = String.valueOf(digit);
            newInput = false;
        } else {
            currentText += String.valueOf(digit);
        }
        display.setText(currentText);
    }

    private void handleOperatorClick(String newOperator) {
        if (!currentText.isEmpty()) {
            calculateResult();
            operand1 = Double.parseDouble(currentText);
            operator = newOperator;
            newInput = true;
        }
    }

    private void calculateResult() {
        if (!currentText.isEmpty() && !operator.isEmpty()) {
            double result = 0.0;
            double operand2 = Double.parseDouble(currentText);

            switch (operator) {
                case "+":
                    result = operand1 + operand2;
                    break;
                case "-":
                    result = operand1 - operand2;
                    break;
                case "*":
                    result = operand1 * operand2;
                    break;
                case "/":
                    if (operand2 != 0) {
                        result = operand1 / operand2;
                    } else {
                        // Handle division by zero
                        clearDisplay();
                        display.setText("Error");
                        return;
                    }
                    break;
            }

            display.setText(String.valueOf(result));
            currentText = String.valueOf(result);
            operand1 = result;
        }
    }

    private void clearDisplay() {
        display.setText("");
        currentText = "";
        operator = "";
        operand1 = 0.0;
        newInput = true;
    }

    private void handleSign() {
        if (!currentText.isEmpty()) {
            double currentValue = Double.parseDouble(currentText);
            currentValue = -currentValue; // Change the sign
            currentText = String.valueOf(currentValue);
            display.setText(currentText);
        }
    }

    private void handleBack() {
        if (!currentText.isEmpty()) {
            currentText = currentText.substring(0, currentText.length() - 1);
            display.setText(currentText);
        }
    }

    private void handleSqrt() {
        if (!currentText.isEmpty()) {
            double currentValue = Double.parseDouble(currentText);
            if (currentValue >= 0) {
                currentValue = Math.sqrt(currentValue);
                currentText = String.valueOf(currentValue);
                display.setText(currentText);
            } else {
                // Handle taking the square root of a negative number (if needed)
                display.setText("Error");
            }
        }
    }
}
