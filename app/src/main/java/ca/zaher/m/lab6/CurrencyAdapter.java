package ca.zaher.m.lab6;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by zaher on 2018-03-06.
 */

public class CurrencyAdapter extends ArrayAdapter<CurrencyItem> {
    private ArrayList<CurrencyItem> currencyItemArrayList;

    public CurrencyAdapter(@NonNull Context context, int textViewResourceId, ArrayList<CurrencyItem> currencyItemArrayList) {
        super(context, textViewResourceId, currencyItemArrayList);
        this.currencyItemArrayList = currencyItemArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item, null);
        }
        CurrencyItem item = currencyItemArrayList.get(position);
        if (item != null) {
            TextView textView = view.findViewById(R.id.tvBase);
            TextView textView1 = view.findViewById(R.id.tvValue);
            textView.setText(item.getBase());
            textView1.setText(String.valueOf(item.getValue()));
        }
        return view;
    }
}
