package com.example.parsetagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseFile;
import com.parse.ParseUser;

import java.io.File;

public class UserProfile extends AppCompatActivity {
    ImageView ivProfileImage;
    TextView tvName;
    Button btnUploadProfileImage;
    private File photoFile;
    private String photoFileName = "photo.jpg";
    private String fileName = "photo.jpg";
    private String TAG = "tag";
    public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Post post = getIntent().getParcelableExtra("post");

        ivProfileImage = findViewById(R.id.ivProfileImage);
        tvName = findViewById(R.id.tvName);
        btnUploadProfileImage = findViewById(R.id.btnUploadProfile);

//        ParseFile profileImage = post.getUser().getProfileImage(); // error: on a null reference

//        if(profileImage != null ) {
//            Utilities.roundedImage(this,profileImage.getUrl(),ivProfileImage,100);
//        }

        btnUploadProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserProfile.this, "capture image button was clicked", Toast.LENGTH_SHORT).show();
                launchCamera();

                if (photoFile == null || ivProfileImage.getDrawable()== null ) {
                    Toast.makeText(UserProfile.this,"No Image attached", Toast.LENGTH_SHORT).show();
                }
                User currentUser = (User) ParseUser.getCurrentUser();
//                savePost(description,currentUser, photoFile);
                saveProfilePicture(currentUser,photoFile);

            }
        });
    }

    private void launchCamera() {
        // create Intent to take a picture and return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Create a File reference for future access
        photoFile = getPhotoFileUri(photoFileName);

        // wrap File object into a content provider
        // required for API >= 24
        // See https://guides.codepath.com/android/Sharing-Content-with-Intents#sharing-files-with-api-24-or-higher
        Uri fileProvider = FileProvider.getUriForFile(UserProfile.this, "com.codepath.fileprovider", photoFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);

        // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
        // So as long as the result is not null, it's safe to use the intent.
        if (intent.resolveActivity(getPackageManager()) != null) {
            // Start the image capture intent to take photo
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // by this point we have the camera photo on disk
                Bitmap takenImage = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                // RESIZE BITMAP, see section below
                // Load the taken image into a preview
                ivProfileImage.setImageBitmap(takenImage);
            } else { // Result was a failure
                Toast.makeText(this, "Picture wasn't taken!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private File getPhotoFileUri(String photoFileName) {
        // Get safe storage directory for photos
        // Use `getExternalFilesDir` on Context to access package-specific directories.
        // This way, we don't need to request external read/write runtime permissions.
        File mediaStorageDir = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), TAG);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()) {
            Log.d(TAG, "failed to create directory");
        }

        // Return the file target for the photo based on filename
        return (new File(mediaStorageDir.getPath() + File.separator + fileName));


    }

    private void saveProfilePicture(User currentUser, File photoFile) {
        currentUser.setProfileImage(new ParseFile(photoFile));
    }
}