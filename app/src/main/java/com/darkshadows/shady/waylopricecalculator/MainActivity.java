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
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    Float hotelprice, discount, duration, tax, rooms, hotelpricecal, afterdurationcal, taxcal, hoteltotalcal, waylototalcal, discountcal;
    TextView textViewHotelPrice, textViewDiscount, textViewDuration, textViewTax, textViewTotalPrice;
    Button buttonCalculate;

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

        buttonCalculate = (Button) findViewById(R.id.btnCalculate);

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
//                    editTextDiscount.setText("0.92");
                    discount = Float.parseFloat(editTextDiscount.getEditText().getText().toString());
                    hotelpricecal = hotelprice * discount;
                    textViewHotelPrice.setText(new DecimalFormat("##.##").format(hotelpricecal));
                    duration = Float.parseFloat(editTextDuration.getEditText().getText().toString());
                    afterdurationcal = hotelpricecal * duration;
                    textViewDuration.setText(new DecimalFormat("##.##").format(afterdurationcal));
                    tax = Float.parseFloat(editTextTax.getEditText().getText().toString());
                    rooms = Float.parseFloat(editTextRooms.getEditText().getText().toString());
                    taxcal = tax * duration;
                    textViewTax.setText(new DecimalFormat("##.##").format(taxcal));
                    waylototalcal = (afterdurationcal + taxcal) * rooms;
                    textViewTotalPrice.setText(new DecimalFormat("##.##").format(waylototalcal));
                    discountcal = ((hotelprice * duration + taxcal) * rooms) - waylototalcal;
                    textViewDiscount.setText(new DecimalFormat("##.##").format(discountcal));
                    hoteltotalcal = (hotelprice * duration) + taxcal;
                }
            }
        });

    }

    public void onClickHotelPrice(View view) {
        ClipboardManager clipboardManager = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("simple text", textViewHotelPrice.getText());
        clipboardManager.setPrimaryClip(clip);
        Toast.makeText(getApplicationContext(), "Copied to clipboard.", Toast.LENGTH_SHORT).show();
    }

    public void onClickDiscount(View view) {
        ClipboardManager clipboardManager = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("simple text", textViewDiscount.getText());
        clipboardManager.setPrimaryClip(clip);
        Toast.makeText(getApplicationContext(), "Copied to clipboard.", Toast.LENGTH_SHORT).show();
    }

    public void onClickDuration(View view) {
        ClipboardManager clipboardManager = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("simple text", textViewDuration.getText());
        clipboardManager.setPrimaryClip(clip);
        Toast.makeText(getApplicationContext(), "Copied to clipboard.", Toast.LENGTH_SHORT).show();
    }

    public void onClickTax(View view) {
        ClipboardManager clipboardManager = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("simple text", textViewTax.getText());
        clipboardManager.setPrimaryClip(clip);
        Toast.makeText(getApplicationContext(), "Copied to clipboard.", Toast.LENGTH_SHORT).show();
    }

    public void onClickTotalPrice(View view) {
        ClipboardManager clipboardManager = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("simple text", textViewTotalPrice.getText());
        clipboardManager.setPrimaryClip(clip);
        Toast.makeText(getApplicationContext(), "Copied to clipboard.", Toast.LENGTH_SHORT).show();
    }

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


    public void onClickShare(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "Expedia = " + hoteltotalcal + "\nWaylo = " + waylototalcal + "\nYou save " + discountcal);
        startActivity(Intent.createChooser(intent, "Share using"));
    }


}
