package com.soucheng.view;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;
import com.soucheng.activity.R;
import com.soucheng.activity.SearchActivity;

/**
 * @author lichen
 */
public class RealEstateViewLoader extends ViewLoader {

    private Spinner locationSpinner;
    private SearchView searchView;

    public RealEstateViewLoader(Context context, View view) {
        super(context, view);
    }

    @Override
    public void load() {
        locationSpinner = (Spinner) view.findViewById(R.id.locationSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.locations, R.layout.location_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(adapter);

        searchView = (SearchView) view.findViewById(R.id.searchView);
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(context, SearchActivity.class);
                intent.putExtra("search", query);
                context.startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

}
