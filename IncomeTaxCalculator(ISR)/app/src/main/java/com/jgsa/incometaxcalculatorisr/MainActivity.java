package com.jgsa.incometaxcalculatorisr;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    // Variables para las vistas de activity_main.xml
    private Spinner spinnerCalculation;
    private EditText editIncome;
    private EditText editPreviousPayments;
    private TextView txtIncome;
    private TextView txtLowerLimit;
    private TextView txtExcess;
    private TextView txtRate;
    private TextView txtTax;
    private TextView txtFixedFee;
    private TextView txtIncurred;
    private TextView txtPrevPayments;
    private TextView txtWithholding;
    private TextView txtISR;
    private Button btnCalculate;
    private Context context;

    // Variables para el control de la aplicación
    private int period = 0;
    private float lowerLimit, fixedFee, withholding, rate;
    private float excess, tax, taxIncurred, isr, tax_to_pay;
    private float income, previousPayments;

    // Variables del ISR
    float [][] weekly = {
            {0.01f, 0.01f, 114.24f, 0f, 1.92f, 93.73f},
            {114.25f, 114.25f, 407.33f, 2.17f, 6.4f, 93.73f},
            {114.25f, 407.34f, 610.96f, 2.17f, 6.4f, 93.66f},
            {114.25f, 610.97f, 799.68f, 2.17f, 6.4f, 93.66f},
            {114.25f, 799.69f, 814.66f, 2.17f, 6.4f, 90.44f},
            {114.25f, 814.67f, 969.5f, 2.17f, 6.4f, 88.06f},
            {969.51f, 969.51f, 1023.75f, 56.91f, 10.88f, 88.06f},
            {969.51f, 1023.76f, 1086.19f, 56.91f, 10.88f, 81.55f},
            {969.51f, 1086.20f, 1228.57f, 56.91f, 10.88f, 74.83f},
            {969.51f, 1228.58f, 1433.32f, 56.91f, 10.88f, 67.83f},
            {969.51f, 1433.33f, 1638.07f, 56.91f, 10.88f, 58.38f},
            {969.51f, 1638.08f, 1699.88f, 56.91f, 10.88f, 50.12f},
            {969.51f, 1699.89f, 1703.80f, 56.91f, 10.88f, 0f},
            {1703.81f, 1703.81f, 1980.58f, 136.85f, 16f, 0f},
            {1980.59f, 1980.59f, 2371.32f, 181.09f, 17.92f, 0f},
            {2371.33f, 2371.33f, 4782.61f, 251.16f, 21.36f, 0f},
            {4782.62f, 4782.62f, 7538.09f, 766.15f, 23.52f, 0f},
            {7538.10f, 7538.10f, 14391.44f, 1414.28f, 30f, 0f},
            {14391.45f, 14391.45f, 19188.61f, 3470.25f, 32f, 0f},
            {19188.62f, 57565.76f, 5005.35f, 34f, 0f},
            {57565.77f, 57565.77f, 10000000f, 18053.63f, 35f, 0f}
    };

    float [][] forthnightly = {
            {0.01f, 0.01f, 244.80f, 0.00f, 1.92f, 200.85f},
            {244.81f, 244.81f, 872.85f, 4.65f, 6.40f, 200.85f},
            {244.81f, 872.86f, 1309.20f, 4.65f, 6.40f, 200.70f},
            {244.81f, 1309.21f, 1713.60f, 4.65f, 6.40f, 200.70f},
            {244.81f, 1713.61f, 1745.70f, 4.65f, 6.40f, 193.80f},
            {244.81f, 1745.71f, 2077.50f, 4.65f, 6.40f, 188.70f},
            {2077.51f, 2077.51f, 2193.75f, 121.95f, 10.88f, 188.70f},
            {2077.51f, 2193.76f, 2327.55f, 121.95f, 10.88f, 174.75f},
            {2077.51f, 2327.56f, 2632.65f, 121.95f, 10.88f, 160.35f},
            {2077.51f, 2632.66f, 3071.40f, 121.95f, 10.88f, 145.35f},
            {2077.51f, 3071.41f, 3510.15f, 121.95f, 10.88f, 125.10f},
            {2077.51f, 3510.16f, 3642.60f, 121.95f, 10.88f, 107.40f},
            {2077.51f, 3642.61f, 3651.00f, 121.95f, 10.88f, 0.00f},
            {3651.01f, 3651.01f, 4244.10f, 293.25f, 16.00f, 0.00f},
            {4244.11f, 4244.11f, 5081.40f, 388.05f, 17.92f, 0.00f},
            {5081.41f, 5081.41f, 10248.45f, 538.20f, 21.36f, 0.00f},
            {10248.46f, 10248.46f, 16153.05f, 1641.75f, 23.52f, 0.00f},
            {16153.06f, 16153.06f, 30838.80f, 3030.60f, 30.00f, 0.00f},
            {30838.81f, 30838.81f, 41118.45f, 7436.25f, 32.00f, 0.00f},
            {41118.46f, 41118.46f, 123355.20f, 10725.75f, 34.00f, 0.00f},
            {123355.21f, 123355.21f, 10000000f, 38686.35f, 35.00f, 0.00f}
    };

    float [][] monthly = {
            {0.01f, 0.01f, 496.07f, 0.00f, 1.92f, 407.02f},
            {496.08f, 496.08f, 1768.96f, 9.52f, 6.40f, 407.02f},
            {496.08f, 1768.97f, 2653.38f, 9.52f, 6.40f, 406.83f},
            {496.08f, 2653.39f, 3472.84f, 9.52f, 6.40f, 406.62f},
            {496.08f, 3472.85f, 3537.87f, 9.52f, 6.40f, 392.77f},
            {496.08f, 3537.88f, 4210.41f, 9.52f, 6.40f, 382.46f},
            {4210.42f, 4446.15f, 247.24f, 10.88f, 382.46f},
            {4210.42f, 4446.16f, 4717.18f, 247.24f, 10.88f, 354.23f},
            {4210.42f, 4717.19f, 5335.42f, 247.24f, 10.88f, 324.87f},
            {4210.42f, 5335.43f, 6224.67f, 247.24f, 10.88f, 294.63f},
            {4210.42f, 6224.68f, 7113.90f, 247.24f, 10.88f, 253.54f},
            {4210.42f, 7113.91f, 7382.33f, 247.24f, 10.88f, 217.61f},
            {4210.42f, 7382.34f, 7399.42f, 247.24f, 10.88f, 0.00f},
            {7399.43f, 7399.43f, 8601.50f, 594.21f, 16.00f, 0.00f},
            {8601.51f, 8601.51f, 10298.35f, 786.54f, 17.92f, 0.00f},
            {10298.36f, 10298.36f, 20770.29f, 1090.61f, 21.36f, 0.00f},
            {20770.30f, 20770.30f, 32736.83f, 3327.42f, 23.52f, 0.00f},
            {32736.84f, 32736.84f, 62500.00f, 6141.95f, 30.00f, 0.00f},
            {62500.01f, 62500.01f, 83333.33f, 15070.90f, 32.00f, 0.00f},
            {83333.34f, 83333.34f, 250000.00f, 21737.57f, 34.00f, 0.00f},
            {250000.01f, 250000.01f, 10000000f, 78404.23f, 35.00f, 0.00f}
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        initViews();
        context = MainActivity.this;
        ArrayAdapter<CharSequence> adapterCalculation = getAdapter(R.array.calculation_periods);
        spinnerCalculation.setAdapter(adapterCalculation);

        spinnerCalculation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("ISR - UI", spinnerCalculation.getSelectedItem().toString() + " calculation selected.");
                period = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                period = 0;
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("ISR - UI", "Button has been pressed.");
                try {
                    income = Float.parseFloat(editIncome.getText().toString());
                    Log.d("ISR - Getting values", "Income = " + income);
                    previousPayments = Float.parseFloat(editPreviousPayments.getText().toString());
                    Log.d("ISR - Getting values", "Previous payments = " + previousPayments);
                    calculaISR(income, previousPayments);
                    setTextViews();
                    Log.d("ISR - Activity", "End of calculation");
                } catch (NumberFormatException e) {
                    Log.e("ISR - Error", getResources().getString(R.string.error_invalid_income));
                    Toast.makeText(context, R.string.error_invalid_income, Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private void setTextViews() {
        Locale locale = new Locale("es", "MX");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        Log.d("ISR - UI", "Setting values to TextViews");
        txtIncome.setText(currencyFormatter.format(income));
        txtLowerLimit.setText(currencyFormatter.format(lowerLimit));
        txtExcess.setText(currencyFormatter.format(excess));
        txtRate.setText("" + rate);
        txtTax.setText(currencyFormatter.format(tax));
        txtFixedFee.setText(currencyFormatter.format(fixedFee));
        txtIncurred.setText(currencyFormatter.format(taxIncurred));
        txtPrevPayments.setText(currencyFormatter.format(previousPayments));
        txtWithholding.setText(currencyFormatter.format(withholding));
        txtISR.setText(currencyFormatter.format(tax_to_pay));
    }

    private boolean emptyEdits() {
        return editIncome.getText().length() == 0 || editPreviousPayments.getText().length() == 0;
    }

    private void initViews(){
        this.spinnerCalculation = (Spinner) findViewById(R.id.spinner_calculation);
        this.editIncome = (EditText) findViewById(R.id.edit_income);
        this.editPreviousPayments = (EditText) findViewById(R.id.edit_previous_payments);
        this.txtIncome = (TextView) findViewById(R.id.txt_income);
        this.txtLowerLimit = (TextView) findViewById(R.id.txt_lower_limit);
        this.txtExcess = (TextView) findViewById(R.id.txt_excess);
        this.txtRate = (TextView) findViewById(R.id.txt_rate);
        this.txtTax = (TextView) findViewById(R.id.txt_tax);
        this.txtFixedFee = (TextView) findViewById(R.id.txt_fixed_fee);
        this.txtIncurred = (TextView) findViewById(R.id.txt_tax_incurred);
        this.txtPrevPayments = (TextView) findViewById(R.id.txt_prev_payment);
        this.txtWithholding = (TextView) findViewById(R.id.txt_withholding);
        this.txtISR = (TextView) findViewById(R.id.txt_ISR);
        this.btnCalculate = (Button) findViewById(R.id.btn_calculate);
    }

    private void calculaISR(float income, float previousPayments) {
        Log.d("ISR - Calculating", "Looking for value in the table.");
        switch (period) {
            case 0:
                setValues(monthly, income);
                break;
            case 1:
                setValues(weekly, income);
                break;
            case 2:
                setValues(forthnightly, income);
             break;
        }
        excess = income - lowerLimit;
        Log.d("ISR - Calculating", "Excess = " + excess);
        tax = Math.round(excess * rate / 100);
        Log.d("ISR - Calculating", "Tax = " + tax);
        taxIncurred = tax + fixedFee;
        Log.d("ISR - Calculating", "Tax Incurred = " + taxIncurred);
        isr = taxIncurred - previousPayments;
        Log.d("ISR - Calculating", "Tax without prev. payments = " + isr);
        tax_to_pay = isr - withholding;
        Log.d("ISR - Calculating", "Tax to pay = " + tax_to_pay);
    }


    private void setValues(float[][] matrix, float income) {
        int row = searchInterval(matrix, income);
        lowerLimit = matrix[row][0];
        fixedFee = matrix[row][3];
        rate = matrix[row][4];
        withholding = matrix[row][5];
    }

    private int searchInterval(float[][] matrix, float number){
        for (int i = 0; i < matrix.length; i++) {
            if(number >= matrix[i][1] && number <= matrix[i][2]) {
                Log.d("ISR - Calculating ", "Interval found: [" + matrix[i][1] + "," + matrix[i][2] + "]");
                return i;
            }
        }
        Log.e("ISR - Calculating ", "Interval not found");
        return -1;
    }



    private ArrayAdapter<CharSequence> getAdapter(int res) {
        return ArrayAdapter.createFromResource(this.context, res, android.R.layout.simple_spinner_dropdown_item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
