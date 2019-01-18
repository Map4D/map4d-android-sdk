package com.map4dsdk.demo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.map4dsdk.demo.utils.DemoDetails;
import com.map4dsdk.demo.utils.DemoDetailsList;
import com.map4dsdk.demo.utils.ViewAdapter;

public class CollectionsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    /**
     * A array adapter that shows a {@link ViewAdapter} containing details about the demo.
     */
    private static class CustomArrayAdapter extends ArrayAdapter<DemoDetails> {

        /**
         * @param demos An array containing the details of the demos to be displayed.
         */
        public CustomArrayAdapter(Context context, DemoDetails[] demos) {
            super(context, R.layout.feature, R.id.title, demos);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewAdapter viewAdapter;
            if (convertView instanceof ViewAdapter) {
                viewAdapter = (ViewAdapter) convertView;
            } else {
                viewAdapter = new ViewAdapter(getContext());
            }

            DemoDetails demo = getItem(position);

            viewAdapter.setTitleId(demo.titleId);
            viewAdapter.setIcon(demo.drawableId);

            return viewAdapter;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collections_acitvity);
        getSupportActionBar().setTitle(R.string.collections);
        ListView list = findViewById(R.id.listView);

        ListAdapter adapter = new CustomArrayAdapter(this, DemoDetailsList.demos);

        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        DemoDetails demo = (DemoDetails) parent.getAdapter().getItem(position);
        startActivity(new Intent(this, demo.activityClass));
    }
}
