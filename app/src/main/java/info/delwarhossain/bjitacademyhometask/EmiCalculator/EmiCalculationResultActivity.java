package info.delwarhossain.bjitacademyhometask.EmiCalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import info.delwarhossain.bjitacademyhometask.BaseActivity;
import info.delwarhossain.bjitacademyhometask.R;

public class EmiCalculationResultActivity extends BaseActivity {
    private TextView loanAmountResultText, interestRateResultText, loanPeriodResultText, monthlyInstallmentResultText, totalPayableResultText, totalInterestResultText;
    int loanAmountValue, loanPeriodValue;
    double interestRateValue;
    String selectedPeriodTypeValue, loanStartDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_emi_calculation_result);

        View rootView = getLayoutInflater().inflate(R.layout.activity_emi_calculation_result, frameLayout);
        menuTitleText.setText("EMI Calculation Result");
        navigationView.setCheckedItem(R.id.nav_emi_calculator);

        Bundle extras = getIntent().getExtras();

        loanAmountValue = extras.getInt("loanAmountValue");
        interestRateValue = extras.getDouble("interestRateValue");
        loanPeriodValue = extras.getInt("loanPeriodValue");
        selectedPeriodTypeValue = extras.getString("selectedPeriodTypeValue");
        loanStartDate = extras.getString("loanStartDate");


        double monthlyInterestRate = interestRateValue/100/12;

        int loanPeriodInMonth;
        if(selectedPeriodTypeValue.equals("Years")){
            loanPeriodInMonth = loanPeriodValue*12;
        }else{
            loanPeriodInMonth = loanPeriodValue;
        }


        double monthlyInstallment = (loanAmountValue * monthlyInterestRate * Math.pow(1+monthlyInterestRate, loanPeriodInMonth))/(Math.pow(1+monthlyInterestRate, loanPeriodInMonth)-1);
        int roundMonthlyInstallment = (int) Math.round(monthlyInstallment);

        int totalPayableAmount = roundMonthlyInstallment*loanPeriodInMonth;
        int totalInterestPayableAmount = totalPayableAmount-loanAmountValue;


        monthlyInstallmentResultText = findViewById(R.id.monthlyPaymentResultText);
        monthlyInstallmentResultText.setText(Integer.toString(roundMonthlyInstallment));

        loanAmountResultText = findViewById(R.id.loanAmountResultText);
        loanAmountResultText.setText(Integer.toString(loanAmountValue));

        interestRateResultText = findViewById(R.id.interestRateResultText);
        interestRateResultText.setText(Double.toString(interestRateValue)+"%");

        loanPeriodResultText = findViewById(R.id.loanPeriodResultText);
        loanPeriodResultText.setText(Integer.toString(loanPeriodValue) + " " + selectedPeriodTypeValue.toString().trim());

        totalPayableResultText = findViewById(R.id.totalPayableResultText);
        totalPayableResultText.setText(Integer.toString(totalPayableAmount));

        totalInterestResultText = findViewById(R.id.totalInterestResultText);
        totalInterestResultText.setText(Integer.toString(totalInterestPayableAmount));
    }

    public void viewInstallmentChart(View view){
        Intent intent = new Intent(EmiCalculationResultActivity.this, EmiInstallmentChartActivity.class);
        intent.putExtra("loanAmountValue", loanAmountValue);
        intent.putExtra("interestRateValue", interestRateValue);
        intent.putExtra("loanPeriodValue", loanPeriodValue);
        intent.putExtra("selectedPeriodTypeValue", selectedPeriodTypeValue);
        intent.putExtra("loanStartDate", loanStartDate);
        startActivity(intent);
    }
}
