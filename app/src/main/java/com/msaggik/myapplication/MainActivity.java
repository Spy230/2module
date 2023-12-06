package com.msaggik.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    float robotCost = 35000; //стоимость робота
    float monthlyStipend = 1700; //стипендия
    int initialAmount = 700; //на счету
    int percentFree = 100; //сколько можно отложить
    float bankInterestRate = 9; //банк под 9%
    float[] monthlyPaymentList = new float[90];
    private TextView monthCountTextView;
    private TextView monthlyPaymentDetailsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        monthCountTextView = findViewById(R.id.countOut);
        monthlyPaymentDetailsTextView = findViewById(R.id.mayMounthOut);

        int months = calculateMonths(bankInterestRate, monthlyStipend, percentFree, robotCost, initialAmount, monthlyPaymentList);
        monthCountTextView.setText(months + " months");

        String monthlyPaymentsList = "";
        for (int i = 0; i < months; i++) {
            monthlyPaymentsList += Float.toString(monthlyPaymentList[i]) + ", ";
        }
        monthlyPaymentDetailsTextView.setText("первоначальный взнос: " + initialAmount + " монет, ежемесячные выплаты (монет): " + monthlyPaymentsList);
    }

    public int calculateMonths(float bankInterestRateYear, float monthlyStipend, int percentFree, float robotCost, int initialAmount, float[] monthlyPaymentArray) {
        float interestRateMonth = bankInterestRateYear / 12; // подсчет ежемесячной процентной ставки по накоплениям
        float percentFreeFloat = initialAmount;
        int count = 0; // счетчик месяцев накоплений

        // алгоритм расчета накоплений
        while (percentFreeFloat < robotCost) {
            count++;
            percentFreeFloat += monthlyStipend;
            monthlyPaymentArray[count - 1] = percentFreeFloat;
            percentFreeFloat *= (1 + interestRateMonth / 100);
        }

        return count;
    }
}