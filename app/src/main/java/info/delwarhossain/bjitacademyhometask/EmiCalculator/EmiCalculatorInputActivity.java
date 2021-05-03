package info.delwarhossain.bjitacademyhometask.EmiCalculator;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;

import info.delwarhossain.bjitacademyhometask.BaseActivity;
import info.delwarhossain.bjitacademyhometask.R;

public class EmiCalculatorInputActivity extends BaseActivity {
    private EditText loanAmountField, interestRateField, loanPeriodField;
    private RadioGroup loanPeriodGroupField;
    private RadioButton selectedPeriodType;

    private DatePickerDialog datePickerDialog;
    private Button datePickerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = getLayoutInflater().inflate(R.layout.activity_emi_calculator_input, frameLayout);
        menuTitleText.setText("EMI Calculation");
        navigationView.setCheckedItem(R.id.nav_emi_calculator);

        initDatePicker();
        datePickerBtn = findViewById(R.id.loanStartDatePickerBtn);
        datePickerBtn.setText(getTodayDate());
    }

    private String getTodayDate(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return  makeDateString(year, month, day);
    }

    private String makeDateString(int year, int month, int day){
        return day + " " + getMonthFormat(month) + " " + year;
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

    public void openDatePicker(View view){
        datePickerDialog.show();
    }

    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;
                String date = makeDateString(year, month, day);
                datePickerBtn.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }

    public void calculateInstallment(View view){
        loanAmountField = findViewById(R.id.loanAmountField);
        interestRateField = findViewById(R.id.interestRateField);
        loanPeriodField = findViewById(R.id.loanPeriodField);
        loanPeriodGroupField = findViewById(R.id.periodTypeGroup);
        selectedPeriodType = findViewById(loanPeriodGroupField.getCheckedRadioButtonId());




        if(TextUtils.isEmpty(loanAmountField.getText()) || TextUtils.isEmpty(interestRateField.getText().toString()) || TextUtils.isEmpty(loanPeriodField.getText().toString())){
            hideKeyboard(view);
            Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
        }else{
            hideKeyboard(view);

            //Getting input fields values
            int loanAmountValue = Integer.parseInt(loanAmountField.getText().toString());
            double interestRateValue = Double.parseDouble(interestRateField.getText().toString());
            int loanPeriodValue = Integer.parseInt(loanPeriodField.getText().toString().trim());
            String selectedPeriodTypeValue = selectedPeriodType.getText().toString().trim();

            Intent intent = new Intent(EmiCalculatorInputActivity.this, EmiCalculationResultActivity.class);
            intent.putExtra("loanAmountValue", loanAmountValue);
            intent.putExtra("interestRateValue", interestRateValue);
            intent.putExtra("loanPeriodValue", loanPeriodValue);
            intent.putExtra("selectedPeriodTypeValue", selectedPeriodTypeValue);
            intent.putExtra("loanStartDate", datePickerBtn.getText());
            startActivity(intent);
        }
    }

    private void hideKeyboard(View view){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}