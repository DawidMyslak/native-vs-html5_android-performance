package com.example.currencyratesnative;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	private ListView listView;
	private ArrayAdapter<String> listViewData;
	private List<Currency> currencyList;
	
	Long startDate;
	Long endDate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.currency_list);

		listView = (ListView) findViewById(R.id.listview);
		listViewData = new ArrayAdapter<String>(this, R.layout.currency_row,
				R.id.textview);
		currencyList = new ArrayList<Currency>();
		listView.setAdapter(listViewData);

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long id) {
				Currency currency = currencyList.get(position);
				Intent intent = new Intent(getBaseContext(),
						DetailsActivity.class);
				intent.putExtra("name", currency.name);
				intent.putExtra("code", currency.code);
				intent.putExtra("rate", currency.rate);
				intent.putExtra("date", currency.date);
				startActivity(intent);
			}
		});

		startDate = System.currentTimeMillis();
		
		new JSONAsyncTaskyncTask()
				.execute("http://dejvo.com/currency/?key=c9398e6d05fbaee28322ab8a01c810f8&callback=callback");
	}

	class JSONAsyncTaskyncTask extends AsyncTask<String, String, Void> {
		private ProgressDialog progressDialog = new ProgressDialog(
				MainActivity.this);
		InputStream inputStream;
		String response;

		protected void onPreExecute() {
			progressDialog.setMessage("Trwa ³adowanie...");
			progressDialog.show();
			progressDialog.setOnCancelListener(new OnCancelListener() {
				public void onCancel(DialogInterface arg0) {
					JSONAsyncTaskyncTask.this.cancel(true);
				}
			});
		}

		@Override
		protected Void doInBackground(String... params) {
			String url_select = params[0];

			ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();

			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(url_select);
				httpPost.setEntity(new UrlEncodedFormEntity(param));
				HttpResponse httpResponse = httpClient.execute(httpPost);
				HttpEntity httpEntity = httpResponse.getEntity();
				inputStream = httpEntity.getContent();
			} catch (Exception e) {
			}

			try {
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(inputStream, "utf-8"), 8);
				StringBuilder stringBuilder = new StringBuilder();

				String line = null;
				while ((line = bufferedReader.readLine()) != null) {
					stringBuilder.append(line + "\n");
				}

				inputStream.close();

				response = stringBuilder.toString();
				response = response.substring(9, response.length() - 3);
			} catch (Exception e) {
			}

			return null;
		}

		protected void onPostExecute(Void v) {
			endDate = System.currentTimeMillis();
			
			try {
				JSONArray jsonArray = new JSONArray(response);

				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);

					String name = jsonObject.getString("name");
					String code = jsonObject.getString("code");
					String rate = jsonObject.getString("rate");
					String date = jsonObject.getString("date");
					currencyList.add(new Currency(name, code, rate, date));

					listViewData.add(name);
				}
			} catch (Exception e) {
			}
			
			this.progressDialog.dismiss();
			
			AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
			alertDialog.setTitle("Alert");
			alertDialog.setMessage(Long.toString(endDate - startDate));
			alertDialog.show();
		}
	}

	class Currency {
		public String name;
		public String code;
		public String rate;
		public String date;

		public Currency(String name, String code, String rate, String date) {
			this.name = name;
			this.code = code;
			this.rate = rate;
			this.date = date;
		}
	}
}
