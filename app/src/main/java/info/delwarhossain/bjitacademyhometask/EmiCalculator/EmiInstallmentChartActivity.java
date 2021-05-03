package info.delwarhossain.bjitacademyhometask.EmiCalculator;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import info.delwarhossain.bjitacademyhometask.Adapter.EmiInstallmentChartAdapter;
import info.delwarhossain.bjitacademyhometask.BaseActivity;
import info.delwarhossain.bjitacademyhometask.Model.InstallmentItemModel;
import info.delwarhossain.bjitacademyhometask.R;

public class EmiInstallmentChartActivity extends BaseActivity {
    private int loanAmountValue, loanPeriodValue, loanPeriodInMonth;
    private double interestRateValue, monthlyInterestRateValue;
    private String selectedPeriodTypeValue, loanStartDate;
    private ArrayList<String> paymentDates;

    private RecyclerView recyclerView;
    private InstallmentItemModel installmentItemModel;
    private EmiInstallmentChartAdapter emiInstallmentChartAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = getLayoutInflater().inflate(R.layout.activity_installment_chart, frameLayout);
        menuTitleText.setText("EMI Installment Chart");
        navigationView.setCheckedItem(R.id.nav_emi_calculator);


        recyclerView = findViewById(R.id.installmentDateRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        emiInstallmentChartAdapter = new EmiInstallmentChartAdapter(this, getList());
        recyclerView.setAdapter(emiInstallmentChartAdapter);


    }

    private List<InstallmentItemModel> getList(){
        List<InstallmentItemModel> installmentItemModelList = new ArrayList<>();

        Bundle extras = getIntent().getExtras();

        loanAmountValue = extras.getInt("loanAmountValue");
        interestRateValue = extras.getDouble("interestRateValue");

        monthlyInterestRateValue = interestRateValue/100/12;

        loanPeriodValue = extras.getInt("loanPeriodValue");
        selectedPeriodTypeValue = extras.getString("selectedPeriodTypeValue");
        loanStartDate = extras.getString("loanStartDate");

        if(selectedPeriodTypeValue.equals("Years")){
            loanPeriodInMonth = loanPeriodValue*12;
        }else{
            loanPeriodInMonth = loanPeriodValue;
        }

        double monthlyInstallment = (loanAmountValue * monthlyInterestRateValue * Math.pow(1+monthlyInterestRateValue, loanPeriodInMonth))/(Math.pow(1+monthlyInterestRateValue, loanPeriodInMonth)-1);
        int roundMonthlyInstallment = (int) Math.round(monthlyInstallment);


        String loanStartDateString[] = loanStartDate.split(" ");

        Calendar cal = Calendar.getInstance();
        cal.set(Integer.parseInt(loanStartDateString[2]), getMonthInt(loanStartDateString[1]), Integer.parseInt(loanStartDateString[0]));
        cal.add(Calendar.MONTH, 1);
        paymentDates = new ArrayList<String>();

        int serial = 1;
        int openingBalance = loanAmountValue;

        while (loanPeriodInMonth > 0){
            String nextMonth = makeDateString(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
            String dateString[] = nextMonth.split(" ");

            int[] emiElements = getEmiElements(openingBalance, monthlyInterestRateValue, roundMonthlyInstallment, loanPeriodInMonth);

            int totalPayingAmount;
            if(loanPeriodInMonth == 1){
                totalPayingAmount = openingBalance+(int)Array.get(emiElements,0);
            }else{
                totalPayingAmount = roundMonthlyInstallment;
            }

            installmentItemModelList.add(
                    new InstallmentItemModel(
                            makeDateString(Integer.parseInt(dateString[2]), getMonthInt(dateString[1]), Integer.parseInt(dateString[0])),
                            serial,
                            (int)Array.get(emiElements,0),
                            openingBalance,
                            (int)Array.get(emiElements, 1),
                            totalPayingAmount,
                            (int)Array.get(emiElements,2)
                    )
            );

            openingBalance = (openingBalance+(int)Array.get(emiElements,0)) - roundMonthlyInstallment;
            cal.add(Calendar.MONTH, 1);
            loanPeriodInMonth--;
            serial++;
        }
        return installmentItemModelList;
    }

    private int[] getEmiElements(int openingBalance, double monthlyInterestRateValue, int monthlyInstallment, int loanPeriodInMonth){
        double presentInterest = openingBalance*monthlyInterestRateValue;
        int roundPresentInterest = (int) Math.round(presentInterest);
        int presentPrincipleAmount, presentEndingBalance;

        if(loanPeriodInMonth == 1){
            presentPrincipleAmount = (monthlyInstallment-roundPresentInterest) + (openingBalance+roundPresentInterest) - monthlyInstallment;
            presentEndingBalance = 0;
        }else{
            presentPrincipleAmount = monthlyInstallment-roundPresentInterest;
            presentEndingBalance = (openingBalance+roundPresentInterest) - monthlyInstallment;
        }

        int[] returnDataArray = {roundPresentInterest, presentPrincipleAmount, presentEndingBalance};

        return returnDataArray;
    }

    private int getMonthInt(String month){
        if(month.equals("JAN"))
            return 0;
        if(month.equals("FEB") )
            return 1;
        if(month.equals("MAR"))
            return 2;
        if(month.equals("APR"))
            return 3;
        if(month.equals("MAY"))
            return 4;
        if(month.equals("JUN"))
            return 5;
        if(month.equals("JUL"))
            return 6;
        if(month.equals("AUG"))
            return 7;
        if(month.equals("SEP"))
            return 8;
        if(month.equals("OCT"))
            return 9;
        if(month.equals("NOV"))
            return 10;
        if(month.equals("DEC"))
            return 11;


        return 0;
    }

    private String makeDateString(int year, int month, int day){
        return day + " " + getMonthFormat(month+1) + " " + year;
    }

    private String getMonthFormat(int month){
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";


        return "JAN";
    }
}
