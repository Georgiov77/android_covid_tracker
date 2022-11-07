package com.unipi.vlachos.covidtrackerapplicationgr;

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailedVacs extends AppCompatActivity {

    TextView tvTotalVac,tvFirstDose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_vacs);

        tvTotalVac = findViewById(R.id.totalvac);
        tvFirstDose = findViewById(R.id.firstdose);

        fetchData();
    }

    private void fetchData() {

        String url = "https://covid-19-greece.herokuapp.com/total-vaccinations";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONObject total_vaccinations = object.getJSONObject("total-vaccinations");
                    String totalvaccinations = total_vaccinations.getString("totalvaccinations");
                    String totaldistinctpersons = total_vaccinations.getString("totaldistinctpersons");
                    tvTotalVac.setText(totalvaccinations);
                    tvFirstDose.setText(totaldistinctpersons);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    public int parse(String myString) {
        int foo;
        try {
            foo = Integer.parseInt(myString);
        }
        catch (NumberFormatException e)
        {
            foo = 0;
        }
        return foo;
    }


}