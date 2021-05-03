package info.delwarhossain.bjitacademyhometask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import info.delwarhossain.bjitacademyhometask.Adapter.FinancialTermAdapter;
import info.delwarhossain.bjitacademyhometask.Model.FinancialTermModel;

public class FinancialTermActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private FinancialTermAdapter termAdapter;
    private List<FinancialTermModel> termList;

    private ConstraintLayout term_progress_bar_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.activity_financial_term, frameLayout);

        menuTitleText.setText("Important Financial Terms");
        navigationView.setCheckedItem(R.id.nav_financial_terms);
        recyclerView = findViewById(R.id.terms_recycler_view);
        term_progress_bar_container = findViewById(R.id.term_progress_bar_container);

        getTermList();

        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //termAdapter = new FinancialTermAdapter(this, termList);
        //recyclerView.setAdapter(termAdapter);
    }

    private void getTermList(){
        termList = new ArrayList<>();

        String url = "https://dummyapi.delwarhossain.info/api/financial-terms";

        RequestQueue queue = Volley.newRequestQueue(FinancialTermActivity.this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray dataArray = response.getJSONArray("data");
                    for (int i = 0; i < dataArray.length(); i++){
                        JSONObject jsonObject = dataArray.getJSONObject(i);
                        termList.add(new FinancialTermModel(jsonObject.getInt("id"), jsonObject.getString("title"), jsonObject.getString("details")));

                        termAdapter = new FinancialTermAdapter(FinancialTermActivity.this, termList);
                        recyclerView.setLayoutManager(new LinearLayoutManager((FinancialTermActivity.this)));
                        recyclerView.setAdapter(termAdapter);
                    }

                    term_progress_bar_container.setVisibility(View.GONE);

                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(FinancialTermActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                term_progress_bar_container.setVisibility(View.GONE);
                Toast.makeText(FinancialTermActivity.this, "Failed to get data from server!", Toast.LENGTH_LONG).show();
            }
        });

        queue.add(jsonObjectRequest);
    }
}