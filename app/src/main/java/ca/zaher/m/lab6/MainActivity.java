package ca.zaher.m.lab6;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class MainActivity extends AppCompatActivity {

    private EditText etEnterCurrency;
    private String baseCurrency;
    private ArrayList<CurrencyItem> currencyItems;
    private CurrencyAdapter adapter;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lvCurrencies = findViewById(R.id.lvCurrencies);
        etEnterCurrency = findViewById(R.id.etEnterCurrency);
        Button btnSearch = findViewById(R.id.btnSearch);
        currencyItems = new ArrayList<>();

        adapter = new CurrencyAdapter(this, R.layout.item, currencyItems);
        lvCurrencies.setAdapter(adapter);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseCurrency = etEnterCurrency.getText().toString();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        getCurrency();
                    }
                };
                Thread thread = new Thread(null, runnable, "background");
                thread.start();
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });
    }

    public void getCurrency() {
        final String url = "https://api.fixer.io/latest?base=";
        String urlWithBase = url.concat(TextUtils.isEmpty(baseCurrency) ? "USD" : baseCurrency);
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, urlWithBase, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        try {
                            response = response.getJSONObject("rates");
                            currencyItems.clear();
                            for (int i = 0; i < response.names().length(); i++) {
                                String key = response.names().getString(i);
                                double value = Double.parseDouble(response.get(response.names().getString(i)).toString());
                                currencyItems.add(new CurrencyItem(key, value));
                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error retrieving data", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueueSingleton.getmInstance(getApplicationContext()).addToRequestQueue(request);
    }
}
