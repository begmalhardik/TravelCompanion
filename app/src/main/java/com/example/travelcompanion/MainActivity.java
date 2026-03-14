package com.example.travelcompanion;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.travelcompanion.converters.CurrencyConverter;
import com.example.travelcompanion.converters.FuelConverter;
import com.example.travelcompanion.converters.TemperatureConverter;
import com.example.travelcompanion.databinding.ActivityMainBinding;
import com.example.travelcompanion.model.UnitType;
import com.example.travelcompanion.utils.ValidationUtils;

/*
    Things to do -
    1. README.md file (at last, i will add)
    2. three different enums files
    3. clean the architecture
*/

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private final String[] currencyUnits = { "USD", "AUD", "EUR", "JPY", "GBP"};

    private final String[] temperatureUnits = { "CELSIUS", "FAHRENHEIT", "KELVIN"};

    private final String[] fuelUnits = { "MPG",
            "KM_PER_LITER",
            "GALLON",
            "LITER",
            "NAUTICAL_MILE",
            "KILOMETER"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.btnConvert.setAlpha(0.5f);
        binding.btnConvert.setEnabled(false);

        binding.etInputValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateInputs();
            }
        });

        binding.fromUnitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                validateInputs();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        binding.toUnitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                validateInputs();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        settingSpinners();

        binding.btnConvert.setOnClickListener(v -> performConversion());

    }


    private void performConversion() {

        String input = binding.etInputValue.getText().toString().trim();

//        if(input.isEmpty()) {
//            Toast.makeText(this, "Please enter a value", Toast.LENGTH_SHORT).show();
//            return;
//        }

        // checking if the input field is empty or not
        if(ValidationUtils.isEmpty(input)) {
            Toast.makeText(this, "Enter a value", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!ValidationUtils.isNumeric(input)) {
            Toast.makeText(this, "Invalid number", Toast.LENGTH_SHORT).show();
            return;
        }

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

        UnitType fromUnit = UnitType.valueOf(from.toUpperCase());
        UnitType toUnit = UnitType.valueOf(to.toUpperCase());

        if (ValidationUtils.isNegative(value) && !category.equals("Temperature")) {
            Toast.makeText(this, "Negative values not allowed for this conversion", Toast.LENGTH_SHORT).show();
            return;
        }

        if(fromUnit.equals(toUnit)) {
            binding.tvResult.setText(String.valueOf(value));
            Toast.makeText(this, "Same units selected", Toast.LENGTH_SHORT).show();
            return;
        }

        double result = 0;

        if(category.equals("Currency")) {
            result = CurrencyConverter.convert(fromUnit, toUnit, value);
        }

        if(category.equals("Temperature")) {
            result = TemperatureConverter.convert(fromUnit, toUnit, value);
        }

        if(category.equals("Fuel")) {
            result = FuelConverter.convert(fromUnit, toUnit, value);
        }

        binding.tvResult.setText(String.format("%.2f", result));
    }

    private void settingSpinners() {

        String[] categories = {"Currency", "Fuel", "Temperature"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                categories
        );

        binding.categorySpinner.setAdapter(adapter);

        // default category
        binding.categorySpinner.setSelection(0);

        setupUnitSpinner(currencyUnits);

        // listen for category change
        binding.categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String category = categories[position];

                if (category.equals("Currency")) {
                    setupUnitSpinner(currencyUnits);
                } else if (category.equals("Fuel")) {
                    setupUnitSpinner(fuelUnits);
                } else {
                    setupUnitSpinner(temperatureUnits);
                }

                validateInputs();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    } // setting category spinner values

    private void validateInputs() {

        String input = binding.etInputValue.getText().toString().trim();

        boolean validInput = ValidationUtils.isNumeric(input) && Double.parseDouble(input) != 0;

        boolean fromSelected = binding.fromUnitSpinner.getSelectedItem() != null;
        boolean toSelected = binding.toUnitSpinner.getSelectedItem() != null;

        binding.btnConvert.setAlpha(1f);
        binding.btnConvert.setEnabled(validInput && fromSelected && toSelected);
    }

    private void setupUnitSpinner(String[] units) {

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                units
        );

        binding.fromUnitSpinner.setAdapter(adapter);
        binding.toUnitSpinner.setAdapter(adapter);

        // default selection for currency
        binding.fromUnitSpinner.setSelection(0); // USD
        binding.toUnitSpinner.setSelection(1);   // AUD
    }
}
