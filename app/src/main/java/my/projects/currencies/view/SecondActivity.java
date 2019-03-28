package my.projects.currencies.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import my.projects.currencies.R;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView tvDate = findViewById(R.id.tvDate);
        TextView tvName = findViewById(R.id.tvName);
        TextView tvRate = findViewById(R.id.tvRate);

        Intent intent = getIntent();
        String currencyDate = intent.getStringExtra("currency_date");
        String currencyName = intent.getStringExtra("currency_name");
        double currencyRate = intent.getDoubleExtra("currency_rate", 0);

        tvDate.setText(String.format("Dzień : %s", currencyDate));
        tvName.setText(String.format("%s: ", currencyName.toUpperCase()));
        tvRate.setText(String.valueOf(currencyRate));
    }
}
