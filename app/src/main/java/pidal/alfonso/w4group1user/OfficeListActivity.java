package pidal.alfonso.w4group1user;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import pidal.alfonso.w4group1user.DatabaseHelpers.OfficeHelper;
import pidal.alfonso.w4group1user.Models.Office;


public class OfficeListActivity extends ListActivity {

    protected static OfficeHelper officeHelper;

    List<Office> officeList;

    protected final static String IDintent = "office_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_office_list);

        officeHelper = new OfficeHelper(this);

        officeList = officeHelper.getAllOffices();

        ArrayAdapter adapter = new ArrayAdapter<Office>(
                this,
                android.R.layout.simple_list_item_1,
                officeList
        );

        adapter.notifyDataSetChanged();

        setListAdapter(adapter);

    }


    @Override
    protected void onResume() {
        super.onResume();

        officeList = officeHelper.getAllOffices();

        ArrayAdapter adapter = new ArrayAdapter<Office>(
                this,
                android.R.layout.simple_list_item_1,
                officeList
        );

        adapter.notifyDataSetChanged();

        setListAdapter(adapter);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        Office office = officeList.get(position);

        Intent intent = new Intent(this, OfficeDetailActivity.class);
        intent.putExtra(IDintent, office.getOfficeID());
        startActivity(intent);
    }

}
