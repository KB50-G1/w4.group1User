package pidal.alfonso.w4group1user;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import pidal.alfonso.w4group1user.DatabaseHelpers.OfficeHelper;
import pidal.alfonso.w4group1user.Models.Office;

public class OfficeDetailActivity extends Activity {

    protected static OfficeHelper officeHelper;
    private          Office       office;

    TextView officeID;
    TextView officeAddress;
    TextView officePhone;
    TextView officeType;

    public static final String OFFICE_ID = "office_id";
    int officeIDintent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office_detail);

        officeHelper = new OfficeHelper(this);

        officeIDintent = getIntent().getIntExtra(OFFICE_ID, 1);

        officeID = (TextView) findViewById(R.id.office_detail_id);
        officeAddress = (TextView) findViewById(R.id.office_detail_address);
        officePhone = (TextView) findViewById(R.id.office_detail_phone);
        officeType = (TextView) findViewById(R.id.office_detail_type);
    }

    @Override
    protected void onResume() {
        super.onResume();

        office = officeHelper.getOffice(officeIDintent);

        officeID.setText(Integer.toString(office.getOfficeID()));
        officeAddress.setText(office.getAddress());
        officePhone.setText(Integer.toString(office.getPhoneNumber()));
        officeType.setText(office.getOfficeType().toString());
    }

    public void phoneNumberClick(View view) {
        // Create new intent to dial the number shown on screen and fires the activity.
        Intent i = new Intent(android.content.Intent.ACTION_CALL, Uri.parse("tel:" + Integer.toString(office.getPhoneNumber())));
        startActivity(i);
    }

    public void addressClick(View view) {

        String map = "http://maps.google.com/maps?q=" + office.getAddress();
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(map));
        startActivity(i);
    }
}
