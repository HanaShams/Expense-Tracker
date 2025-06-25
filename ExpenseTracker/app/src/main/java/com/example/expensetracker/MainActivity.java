package com.example.expensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText expenseDescriptionEditText, expenseAmountEditText;
    private RadioGroup categoryRadioGroup;
    private Button addExpenseButton, clearExpensesButton;
    private TextView totalExpensesTextView;
    private TextView[] expenseItems = new TextView[5];
    private int expenseIndex = 0;
    private double totalExpenses = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expenseDescriptionEditText = findViewById(R.id.expenseDescriptionEditText);
        expenseAmountEditText = findViewById(R.id.expenseAmountEditText);
        categoryRadioGroup = findViewById(R.id.categoryRadioGroup);
        addExpenseButton = findViewById(R.id.addExpenseButton);
        clearExpensesButton = findViewById(R.id.clearExpensesButton);
        totalExpensesTextView = findViewById(R.id.totalExpensesTextView);

        expenseItems[0] = findViewById(R.id.expenseItem1);
        expenseItems[1] = findViewById(R.id.expenseItem2);
        expenseItems[2] = findViewById(R.id.expenseItem3);
        expenseItems[3] = findViewById(R.id.expenseItem4);
        expenseItems[4] = findViewById(R.id.expenseItem5);

        addExpenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExpense();
            }
        });

        clearExpensesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearExpenses();
            }
        });
    }

    private void addExpense() {
        String description = expenseDescriptionEditText.getText().toString();
        String amountStr = expenseAmountEditText.getText().toString();
        if (description.isEmpty() || amountStr.isEmpty() || expenseIndex >= expenseItems.length) {
            return;
        }

        double amount = Double.parseDouble(amountStr);
        int selectedCategoryId = categoryRadioGroup.getCheckedRadioButtonId();
        RadioButton selectedCategoryButton = findViewById(selectedCategoryId);
        String category = selectedCategoryButton == null ? "" : selectedCategoryButton.getText().toString();

        String expenseText = String.format("%s: $%.2f (%s)", description, amount, category);
        expenseItems[expenseIndex].setText(expenseText);
        expenseIndex++;

        totalExpenses += amount;
        totalExpensesTextView.setText(String.format("Total: $%.2f", totalExpenses));

        expenseDescriptionEditText.setText("");
        expenseAmountEditText.setText("");
        categoryRadioGroup.clearCheck();
    }

    private void clearExpenses() {
        for (TextView expenseItem : expenseItems) {
            expenseItem.setText("");
        }
        expenseIndex = 0;
        totalExpenses = 0.0;
        totalExpensesTextView.setText("Total: $0.00");
    }
}