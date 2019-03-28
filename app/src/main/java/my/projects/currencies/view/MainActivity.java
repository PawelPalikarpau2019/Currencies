package my.projects.currencies.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import my.projects.currencies.R;
import my.projects.currencies.adapter.CurrencyAdapter;
import my.projects.currencies.listener.CurrencyItemClickListener;
import my.projects.currencies.model.Currencies;
import my.projects.currencies.model.Currency;
import my.projects.currencies.service.CurrenciesService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Callback<Currencies>, CurrencyItemClickListener {

    private TextView lvDateRight;
    private ListView lvCurrency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvDateRight = findViewById(R.id.lvDateRight);
        lvCurrency = findViewById(R.id.lvCurrency);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadCurrencyExchangeData();
    }

    private void loadCurrencyExchangeData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://data.fixer.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        String accessKey = "f80f31ae23e1e259d1ee5431665dbdf0";
        String symbols = "USD,AUD,CAD,PLN,MXN";
        Call<Currencies> call;

        CurrenciesService service = retrofit.create(CurrenciesService.class);
        call = service.loadCurrencies(accessKey);
//        call = service.loadCurrencies(accessKey, symbols);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Currencies> call, Response<Currencies> response) {
        //Toast.makeText(this, response.body().getBase(), Toast.LENGTH_LONG).show();
        Currencies currencies = response.body();
        String formattedDate = null;
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(currencies.getDate());
            formattedDate = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).format(date);
            lvDateRight.setText(String.format(" %s", formattedDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        lvCurrency.setAdapter(new CurrencyAdapter(this, currencies.getCurrencyList(), formattedDate, this));
    }

    @Override
    public void onFailure(Call<Currencies> call, Throwable t) {
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCurrencyItemClick(Currency c, String date) {
        //Toast.makeText(this, c.getName(), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("currency_date", date);
        intent.putExtra("currency_name", c.getName());
        intent.putExtra("currency_rate", c.getRate());

        startActivity(intent);
    }
}
