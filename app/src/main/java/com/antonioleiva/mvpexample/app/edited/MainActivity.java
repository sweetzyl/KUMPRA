

package com.antonioleiva.mvpexample.app.edited;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.antonioleiva.mvpexample.app.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements MainView, AdapterView.OnItemClickListener {

    private ListView listView;
    private ProgressBar progressBar;
    private MainPresenter presenter;
    private ArrayAdapter mAdapter;

    EditText metItem;


    EditText metSubItem;


    ListView mListItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        metItem = (EditText) findViewById(R.id.item);
        metSubItem = (EditText) findViewById(R.id.quantity);
        mListItem = (ListView) findViewById(R.id.listView);
        mListItem.setOnItemClickListener(this);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                new ArrayList<String>());
        //mListItem.setAdapter(mAdapter);



    }

    @Override protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        listView.setVisibility(View.INVISIBLE);
    }

    @Override public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        listView.setVisibility(View.VISIBLE);
    }

    @Override public void setItems(List<String> items) {
        String item = metItem.getText().toString();
        String quantity = metSubItem.getText().toString();

        if(!TextUtils.isEmpty(item)){
            if(!TextUtils.isEmpty(quantity)){
                mAdapter.add(item);
                mAdapter.add(quantity);
                mListItem.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
                metItem.setText(" ");
                metSubItem.setText(" ");
            }


        }else {
            Toast.makeText(this, "Please Input",Toast.LENGTH_SHORT).show();
        }

    }

    @Override public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        presenter.onItemClicked(position);
    }
}
