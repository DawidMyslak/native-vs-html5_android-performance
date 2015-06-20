package com.example.currencyratesnative;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class DetailsActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.currency_details);

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			String name = extras.getString("name");
			String code = extras.getString("code");
			String rate = extras.getString("rate");
			String date = extras.getString("date");

			EditText nameText = (EditText) findViewById(R.id.nametext);
			nameText.setText(name);

			EditText codeText = (EditText) findViewById(R.id.codetext);
			codeText.setText(code);

			EditText rateText = (EditText) findViewById(R.id.ratetext);
			rateText.setText(rate);

			EditText dateText = (EditText) findViewById(R.id.datetext);
			dateText.setText(date);
		}
	}
}
