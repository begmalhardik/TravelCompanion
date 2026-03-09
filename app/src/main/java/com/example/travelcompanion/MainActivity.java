package com.example.travelcompanion;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travelcompanion.converters.CurrencyConverter;
import com.example.travelcompanion.converters.TemperatureConverter;
import com.example.travelcompanion.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupCategorySpinner();

        binding.btnConvert.setOnClickListener(v -> performConversion());

    }


    private void performConversion() {

        String input = binding.inputValue.getText().toString().trim();

        if(input.isEmpty()) {
            Toast.makeText(this, "Please enter a value", Toast.LENGTH_SHORT).show();
            return;
        } // checking if the input field is empty or not

        double value;

        try {
            value = Double.parseDouble(input);
        } catch (Exception e) {
            // if it is non-numeric
            Toast.makeText(this, "Invalid number", Toast.LENGTH_SHORT).show();
            return;
        }

        String category = binding.categorySpinner.getSelectedItem().toString();
        String from = binding.fromUnitSpinner.getSelectedItem().toString();
        String to =  binding.toUnitSpinner.getSelectedItem().toString();

        if(from.equals(to)) {
            binding.tvResult.setText(String.valueOf(value));
            Toast.makeText(this, "Same units selected", Toast.LENGTH_SHORT).show();
            return;
        }

        double result = 0;

        if(category.equals("Currency")) {
            result = CurrencyConverter.convert(from, to, value);
        }

        if(category.equals("Temperature")) {
            result = TemperatureConverter.convert(from, to, value);
        }

        binding.tvResult.setText(String.format("%.2f", result));
    }

    private void setupCategorySpinner() {

        String[] categories = {"Currency", "Fuel", "Temperature"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                categories
        );

        binding.categorySpinner.setAdapter(adapter);
    } // setting spinner value
}
