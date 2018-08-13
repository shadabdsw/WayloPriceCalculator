package com.darkshadows.shady.waylopricecalculator;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    Float hotelprice, discount, duration, tax, rooms;
    Float hotelpricecal, afterdurationcal, taxcal, hoteltotalcal, waylototalcal, discountcal;
    TextView textViewHotelPrice, textViewDiscount, textViewDuration, textViewTax, textViewTotalPrice;
    Button buttonCalculate;

    RadioGroup radioGroup;
    RadioButton radioPerNight, radioTotalTax;

    private TextInputLayout editTextHotelPrice;
    private TextInputLayout editTextDiscount;
    private TextInputLayout editTextDuration;
    private TextInputLayout editTextTax;
    private TextInputLayout editTextRooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewHotelPrice = (TextView) findViewById(R.id.tvHotelPrice);
        textViewDiscount = (TextView) findViewById(R.id.tvDiscount);
        textViewDuration = (TextView) findViewById(R.id.tvDuration);
        textViewTax = (TextView) findViewById(R.id.tvTax);
        textViewTotalPrice = (TextView) findViewById(R.id.tvTotalPrice);

        editTextHotelPrice = (TextInputLayout) findViewById(R.id.etHotelPrice);
        editTextDiscount = (TextInputLayout) findViewById(R.id.etDiscount);
        editTextDuration = (TextInputLayout) findViewById(R.id.etDuration);
        editTextTax = (TextInputLayout) findViewById(R.id.etTax);
        editTextRooms = (TextInputLayout) findViewById(R.id.etRooms);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        radioPerNight = (RadioButton) findViewById(R.id.radioPerNight);
        radioTotalTax = (RadioButton) findViewById(R.id.radioTotalTax);

        buttonCalculate = (Button) findViewById(R.id.btnCalculate);

        //CalculateButtonFunction

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextHotelPrice.getEditText().getText().toString().length() == 0 || editTextDiscount.getEditText().getText().toString().length() == 0 || editTextDuration.getEditText().getText().toString().length() == 0 || editTextTax.getEditText().getText().toString().length() == 0 || editTextRooms.getEditText().getText().toString().length() == 0) {

                    validateHotelPrice();
                    validateDiscount();
                    validateDuration();
                    validateTax();
                    validateRooms();

                } else {

                    validateHotelPrice();
                    validateDiscount();
                    validateDuration();
                    validateTax();
                    validateRooms();

                    hotelprice = Float.parseFloat(editTextHotelPrice.getEditText().getText().toString());
                    discount = Float.parseFloat(editTextDiscount.getEditText().getText().toString());
                    duration = Float.parseFloat(editTextDuration.getEditText().getText().toString());
                    tax = Float.parseFloat(editTextTax.getEditText().getText().toString());
                    rooms = Float.parseFloat(editTextRooms.getEditText().getText().toString());

                    hotelpricecal = hotelprice - (hotelprice * discount / 100);
                    textViewHotelPrice.setText(new DecimalFormat("##.##").format(hotelpricecal));

                    afterdurationcal = hotelpricecal * duration;
                    textViewDuration.setText(new DecimalFormat("##.##").format(afterdurationcal));

                    int id = radioGroup.getCheckedRadioButtonId();

                    switch (id) {
                        case R.id.radioPerNight:
                            taxcal = tax * duration;
                            textViewTax.setText(new DecimalFormat("##.##").format(taxcal));
                            break;

                        case R.id.radioTotalTax:
                            taxcal = tax;
                            textViewTax.setText(new DecimalFormat("##.##").format(taxcal));
                            break;
                    }

                    waylototalcal = (afterdurationcal + taxcal) * rooms;
                    textViewTotalPrice.setText(new DecimalFormat("##.##").format(waylototalcal));

                    discountcal = ((hotelprice * duration + taxcal) * rooms) - waylototalcal;
                    textViewDiscount.setText(new DecimalFormat("##.##").format(discountcal));

                    //ExpediaTotal
                    hoteltotalcal = (hotelprice * duration) + taxcal;
                }
            }
        });

    }

    //CopyToClipboard

    public void onClickHotelPriceTextView(View view) {
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("simple text", textViewHotelPrice.getText());
        clipboardManager.setPrimaryClip(clip);
        Toast.makeText(getApplicationContext(), "Copied to clipboard.", Toast.LENGTH_SHORT).show();
    }

    public void onClickDiscountTextView(View view) {
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("simple text", textViewDiscount.getText());
        clipboardManager.setPrimaryClip(clip);
        Toast.makeText(getApplicationContext(), "Copied to clipboard.", Toast.LENGTH_SHORT).show();
    }

    public void onClickDurationTextView(View view) {
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("simple text", textViewDuration.getText());
        clipboardManager.setPrimaryClip(clip);
        Toast.makeText(getApplicationContext(), "Copied to clipboard.", Toast.LENGTH_SHORT).show();
    }

    public void onClickTaxTextView(View view) {
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("simple text", textViewTax.getText());
        clipboardManager.setPrimaryClip(clip);
        Toast.makeText(getApplicationContext(), "Copied to clipboard.", Toast.LENGTH_SHORT).show();
    }

    public void onClickTotalPriceTextView(View view) {
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("simple text", textViewTotalPrice.getText());
        clipboardManager.setPrimaryClip(clip);
        Toast.makeText(getApplicationContext(), "Copied to clipboard.", Toast.LENGTH_SHORT).show();
    }

    //ValidationToCheckIfFieldsAreNotEmpty

    public boolean validateHotelPrice() {
        String stringHotelPrice = editTextHotelPrice.getEditText().getText().toString().trim();

        if (stringHotelPrice.isEmpty()) {
            editTextHotelPrice.setError("Field can't be empty!");
            return false;
        } else {
            editTextHotelPrice.setError(null);
            return true;
        }
    }

    public boolean validateDiscount() {
        String stringDiscount = editTextDiscount.getEditText().getText().toString().trim();

        if (stringDiscount.isEmpty()) {
            editTextDiscount.setError("Field can't be empty!");
            return false;
        } else {
            editTextDiscount.setError(null);
            return true;
        }
    }

    public boolean validateDuration() {
        String stringDuration = editTextDuration.getEditText().getText().toString().trim();

        if (stringDuration.isEmpty()) {
            editTextDuration.setError("Field can't be empty!");
            return false;
        } else {
            editTextDuration.setError(null);
            return true;
        }
    }

    public boolean validateTax() {
        String stringTax = editTextTax.getEditText().getText().toString().trim();

        if (stringTax.isEmpty()) {
            editTextTax.setError("Field can't be empty!");
            return false;
        } else {
            editTextTax.setError(null);
            return true;
        }
    }

    public boolean validateRooms() {
        String stringRooms = editTextRooms.getEditText().getText().toString().trim();

        if (stringRooms.isEmpty()) {
            editTextRooms.setError("Field can't be empty!");
            return false;
        } else {
            editTextRooms.setError(null);
            return true;
        }
    }

    //ToClearFields

    public void onClickClearFields(View view) {
        if (editTextHotelPrice.getEditText().getText().toString().isEmpty() && editTextDiscount.getEditText().getText().toString().isEmpty() && editTextDuration.getEditText().getText().toString().isEmpty() && editTextTax.getEditText().getText().toString().isEmpty() && editTextRooms.getEditText().getText().toString().isEmpty()) {
            Toast.makeText(this, "Fields are already empty!", Toast.LENGTH_SHORT).show();
        } else {
            editTextHotelPrice.getEditText().setText("");
            editTextDiscount.getEditText().setText("");
            editTextDuration.getEditText().setText("");
            editTextTax.getEditText().setText("");
            editTextRooms.getEditText().setText("");
        }
    }

    //ToShareResultDataStoredOnTextViews

    public void onClickShare(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "Expedia = " + (new DecimalFormat("##.##").format(hoteltotalcal)) + "\nWaylo = " + (new DecimalFormat("##.##").format(waylototalcal)) + "\nYou save " + (new DecimalFormat("##.##").format(discountcal)));
        startActivity(Intent.createChooser(intent, "Share using"));
//        textViewDiscount.setText(new DecimalFormat("##.##").format(discountcal));

    }


    //ToMakeUseOfRadioButton

    public void onClickCheckedButton(View view) {

//        int id = radioGroup.getCheckedRadioButtonId();
//
//        switch (id) {
//            case R.id.radioPerNight:
//                break;
//
//            case R.id.radioTotalTax:
//                break;
//        }

    }

}
