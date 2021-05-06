package sg.edu.rp.c346.id20047518.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    TextView tvAmt;
    EditText etAmt;
    TextView tvNOP;
    EditText etNOP;
    ToggleButton tbSVS;
    ToggleButton tbGST;
    TextView tvDst;
    EditText etDst;
    RadioGroup rgPay;
    Button btnSplit;
    Button btnReset;
    TextView tvResult1;
    TextView tvResult2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvAmt = findViewById(R.id.tvAmt);
        etAmt = findViewById(R.id.etAmt);
        tvNOP = findViewById(R.id.tvNOP);
        etNOP = findViewById(R.id.etNOP);
        tbSVS = findViewById(R.id.tbSVS);
        tbGST = findViewById(R.id.tbGST);
        tvDst = findViewById(R.id.tvDst);
        etDst = findViewById(R.id.etDst);
        rgPay = findViewById(R.id.rgPay);
        btnSplit = findViewById(R.id.btnSplit);
        btnReset = findViewById(R.id.btnReset);
        tvResult1 = findViewById(R.id.tvResult1);
        tvResult2 = findViewById(R.id.tvResult2);

        /*btnSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amt = etAmt.getText().toString();
                String pax = etNOP.getText().toString();
                String dis = etDst.getText().toString();
                myN

                double totBill = (amt/100)*(100-dis);
                tvResult.setText((int) totBill);
            }
        });*/

        btnSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etAmt.getText().toString().trim().length() != 0 && etNOP.getText().toString().trim().length() != 0) {
                    double origAmt = Double.parseDouble(etAmt.getText().toString());
                    double newAmt = 0.0;
                    if (!tbSVS.isChecked() && !tbGST.isChecked()) {
                        newAmt = origAmt;
                    } else if (tbSVS.isChecked() && !tbGST.isChecked()) {
                        newAmt = origAmt * 1.1;
                    } else if (!tbSVS.isChecked() && tbGST.isChecked()) {
                        newAmt = origAmt * 1.07;
                    } else {
                        newAmt = origAmt * 1.17;
                    }
//Discount
                    if (etDst.getText().toString().trim().length() != 0) {
                        newAmt *= 1 - Double.parseDouble(etDst.getText().toString()) / 100;
                    }
                    String mode = " in cash";
                    if (rgPay.getCheckedRadioButtonId() == R.id.rbPN) {
                        mode = " via PayNow to 912345678";
                    }
                    tvResult1.setText("Total Bill: $" + String.format("%.2f", newAmt));
                    int numPerson = Integer.parseInt(etNOP.getText().toString());
                    if (numPerson != 1)
                        tvResult2.setText("Each Pays: $" + String.format("%.2f", newAmt / numPerson) + mode);
                    else
                        tvResult2.setText("Each Pays: $" + newAmt + mode);
                }
            }
        });

    }};