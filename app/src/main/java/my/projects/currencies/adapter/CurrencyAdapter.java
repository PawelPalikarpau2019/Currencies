package my.projects.currencies.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import my.projects.currencies.listener.CurrencyItemClickListener;
import my.projects.currencies.R;
import my.projects.currencies.model.Currency;

public class CurrencyAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<Currency> currencyList;
    private String date;
    private CurrencyItemClickListener currencyItemClickListener;

    public CurrencyAdapter(Context context, List<Currency> currencyList, String date, CurrencyItemClickListener currencyItemClickListener) {
        this.currencyList = currencyList;
        this.date = date;
        this.currencyItemClickListener = currencyItemClickListener;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return currencyList.size();
    }

    @Override
    public Object getItem(int i) {
        return currencyList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View currencyItemView = layoutInflater.inflate(R.layout.currency_item, null);
        TextView tvName = currencyItemView.findViewById(R.id.tvName);
        TextView tvRate = currencyItemView.findViewById(R.id.tvRate);

        final Currency c = currencyList.get(i);
        tvName.setText(c.getName());
        tvRate.setText(String.valueOf(c.getRate()));

        currencyItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currencyItemClickListener.onCurrencyItemClick(c, date);
            }
        });
        return currencyItemView;
    }
}
