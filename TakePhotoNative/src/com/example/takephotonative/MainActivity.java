package com.example.takephotonative;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private static final int ACTION_TAKE_PHOTO = 1;

	private ImageView imageView;
	private Bitmap imageBitmap;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		imageView = (ImageView) findViewById(R.id.imageview);
		imageBitmap = null;

		Button photoButton = (Button) findViewById(R.id.photobutton);
		photoButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				takePictureIntent(ACTION_TAKE_PHOTO);
			}
		});
	}

	private void takePictureIntent(int actionCode) {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(takePictureIntent, actionCode);
	}

	private void handleCameraPhoto(Intent intent) {
		Bundle extras = intent.getExtras();
		imageBitmap = (Bitmap) extras.get("data");
		imageView.setImageBitmap(imageBitmap);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == ACTION_TAKE_PHOTO && resultCode == RESULT_OK) {
			handleCameraPhoto(data);
		}
	}
}