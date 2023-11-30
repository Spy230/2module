package com.msaggik.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    float robotCost = 35_000; //стоимость робота
    float monthlyStipend = 1_700; //стипендия
    int initialAmount = 700; //на счету
    int percentFree = 100; //сколько можно отложить
    float bankInterestRate = 9; //банк под 9%
    float[] monthlyPaymentList = new float[60];
    private TextView monthCountTextView;
    private TextView monthlyPaymentDetailsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        monthCountTextView = findViewById(R.id.countOut);
        monthlyPaymentDetailsTextView = findViewById(R.id.mayMounthOut);

        monthCountTextView.setText(calculateMonths(bankInterestRate, monthlyStipend, percentFree, robotCost, initialAmount, monthlyPaymentList) + " months");
        String monthlyPaymentsList = "";
        for (float list : monthlyPaymentList) {
            if (list > 0) {
                monthlyPaymentsList += Float.toString(list) + ", ";
            } else {
                break;
            }
        }
        monthlyPaymentDetailsTextView.setText("первоначальный взнос" + initialAmount + "монет, ежемесячные выплаты ( монет): " + monthlyPaymentsList);
    }


    public int calculateMonths(float bankInterestRateYear, float monthlyStipend, int percentFree, float robotCost, int initialAmount, float[] monthlyPaymentArray) {
        float bankInterestRateMonth = bankInterestRateYear / 12;
        float mortgageCosts = (initialAmount * monthlyStipend) / 100;
        float total = robotCost - initialAmount;
        int monthCount = 0;
        while (total > 0) {
            monthCount++;
            total = (total + (total * bankInterestRateMonth / 100) - mortgageCosts);
            if (total > 0) {
                monthlyPaymentArray[monthCount - 1] = Math.min(mortgageCosts, total);
            }
        }
        return monthCount;
    }
}
