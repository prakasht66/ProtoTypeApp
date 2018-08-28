package app.hamcr7.mapr.prototypeapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.hussain_chachuliya.customcamera.CustomCamera;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import id.zelory.compressor.Compressor;

public class PostToBlog extends AppCompatActivity {

    private Toolbar newPostToolbar;
    private Button camButton;
    private Button addImageButton;

    private ImageView newPostImage;
    private ImageView cancelImage;
    private EditText newPostTitle;
    private EditText newPostDesc;
    private EditText categorytxt;
    private TextView newPostSentTxt;

    private Uri postImageUri = null;

    private ProgressBar newPostProgress;

    private StorageReference storageReference;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;

    private String current_user_id;
    private Bitmap bitmap;
    private Bitmap compressedImageFile;

    private static final String ALLOWED_CHARACTERS ="0123456789qwertyuiopasdfghjklzxcvbnm";
    private String mCurrentPhotoPath;
    private ArrayList<String> cadCatList = new ArrayList<>();
    private String cadCatString = "";
    private String imagePath="";
    private static final int CAMERA_REQUEST = 1888;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
//    @BindView(R.id.rv_interest_multi)
//    ChipRecyclerView rvInterestMulti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_to_blog);

//        ButterKnife.bind(this);
//        setInterestAdapterMulti();

        storageReference = FirebaseStorage.getInstance().getReference();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        current_user_id = firebaseAuth.getCurrentUser().getUid();

        newPostToolbar = findViewById(R.id.new_post_toolbar);
        setSupportActionBar(newPostToolbar);
        getSupportActionBar().setTitle("Add New Post");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        categorytxt=findViewById(R.id.editCat);
        newPostTitle = findViewById(R.id.new_post_tit);
        newPostDesc = findViewById(R.id.new_post_desc);
        newPostImage = findViewById(R.id.new_post_image);
        cancelImage=findViewById(R.id.cancel_Image);
        newPostSentTxt = findViewById(R.id.post_txt);
        newPostProgress = findViewById(R.id.new_post_progress);
        camButton=findViewById(R.id.btn_cam);
        addImageButton=findViewById(R.id.btn_add_img);
        cadCatString=RandomName(10);
        camButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CustomCamera.init()
                        .with(PostToBlog.this)
                        .setRequiredMegaPixel(1.5f)
                        .setPath(Environment.getExternalStorageDirectory().getAbsolutePath() + "/CustomCamera")
                        .setImageName(cadCatString)
                        .start();
            }
        });

        newPostImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setMinCropResultSize(512, 512)
                        .setAspectRatio(1, 1)
                        .start(PostToBlog.this);

            }
        });
        cancelImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (newPostImage !=null)
                {
                    newPostImage.setVisibility(View.INVISIBLE);
                    newPostImage=null;
                    cancelImage.setVisibility(View.INVISIBLE);
                }
            }
        });
        newPostSentTxt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final String title = newPostTitle.getText().toString();
                final String desc = newPostDesc.getText().toString();
                final String cadCatString =categorytxt.getText() .toString();

                if(!TextUtils.isEmpty(desc) && !TextUtils.isEmpty(title)){


//                    if (postImageUri==null)
//                    {
//                        postImageUri= getImageUri(PostToBlog.this,bitmap);
//                    }
                    newPostProgress.setVisibility(View.VISIBLE);
                    File fileImage=null;
                    ///
                    try {
                        //create a file to write bitmap data
                        fileImage = new File(getApplicationContext().getCacheDir(), "TestFile");
                        fileImage.createNewFile();
//Convert bitmap to byte array
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
                        byte[] bitmapdata = bos.toByteArray();

//write the bytes in file
                        FileOutputStream fos = new FileOutputStream(fileImage);
                        fos.write(bitmapdata);
                        fos.flush();
                        fos.close();
                        //
                    }
                    catch (IOException ex)
                    {

                    }




                    final String randomName = UUID.randomUUID().toString();

                    // PHOTO UPLOAD
                   // File newImageFile = new File(postImageUri.getPath());
                    try {

                        compressedImageFile = new Compressor(PostToBlog.this)
                                .setMaxHeight(720)
                                .setMaxWidth(720)
                                .setQuality(50)
                                .compressToBitmap(fileImage);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    compressedImageFile.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] imageData = baos.toByteArray();

                    // PHOTO UPLOAD

                    UploadTask filePath = storageReference.child("Post_images").child(randomName + ".jpg").putBytes(imageData);


                    filePath.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull final Task<UploadTask.TaskSnapshot> task) {

                            final String downloadUri = task.getResult().getDownloadUrl().toString();

                            if(task.isSuccessful()){

                                File newThumbFile = new File(postImageUri.getPath());
                                try {

                                    compressedImageFile = new Compressor(PostToBlog.this)
                                            .setMaxHeight(100)
                                            .setMaxWidth(100)
                                            .setQuality(1)
                                            .compressToBitmap(newThumbFile);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                compressedImageFile.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                                byte[] thumbData = baos.toByteArray();

                                UploadTask uploadTask = storageReference.child("Post_images/thumbs")
                                        .child(randomName + ".jpg").putBytes(thumbData);

                                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                        String downloadthumbUri = taskSnapshot.getDownloadUrl().toString();

                                        Map<String, Object> postMap = new HashMap<>();
                                        postMap.put("image_url", downloadUri);
                                        postMap.put("image_thumb", downloadthumbUri);
                                        postMap.put("tit", title);
                                        postMap.put("desc", desc);
                                        postMap.put("cat", cadCatString);
                                        postMap.put("user_id", current_user_id);
                                        postMap.put("timestamp", FieldValue.serverTimestamp());

                                        firebaseFirestore.collection("Posts").add(postMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentReference> task) {

                                                if(task.isSuccessful()){

                                                    Toast.makeText(PostToBlog.this, "Post was added", Toast.LENGTH_LONG).show();
                                                    Intent mainIntent = new Intent(PostToBlog.this, MainActivity.class);
                                                    startActivity(mainIntent);
                                                    finish();

                                                } else {


                                                }

                                                newPostProgress.setVisibility(View.INVISIBLE);

                                            }
                                        });

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                        //Error handling

                                    }
                                });


                            } else {

                                newPostProgress.setVisibility(View.INVISIBLE);

                            }

                        }
                    });


                }

            }
        });


    }
    @Override
    protected void onPostCreate( Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CustomCamera.IMAGE_SAVE_REQUEST){
            if(resultCode == RESULT_OK){
                Toast.makeText(PostToBlog.this,
                        "Image is saved at: " + data.getStringExtra(CustomCamera.IMAGE_PATH),
                        Toast.LENGTH_SHORT).show();
                imagePath= String.format("%s/%s",
                        data.getStringExtra(CustomCamera.IMAGE_PATH),
                        "");


                try {
                     bitmap = BitmapFactory.decodeFile(imagePath);
                    if (bitmap!=null)
                    {
                        newPostImage.setImageBitmap(bitmap);
                        newPostImage.setVisibility(View.VISIBLE);
                        cancelImage.setVisibility(View.VISIBLE);
                    }


                }
                catch (OutOfMemoryError e) {
                    try {

                        String anotherPath = String.format("%s/%s",
                                CustomCamera.IMAGE_PATH,
                                cadCatString);
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = 2;
                        Bitmap bitmap = BitmapFactory.decodeFile(anotherPath, options);
                        newPostImage.setImageBitmap(bitmap);
                    } catch (Exception excepetion) {
                        excepetion.printStackTrace();
                    }
                }

            }
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                postImageUri = result.getUri();
                newPostImage.setImageURI(postImageUri);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();

            }
        }

    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path+".jpg");
    }
    public String RandomName(int MAX_LENGTH) {
        final Random random=new Random();
        final StringBuilder sb=new StringBuilder(MAX_LENGTH);
        for(int i=0;i<MAX_LENGTH;++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        return sb.toString();
    }

    private void createDirectoryAndSaveFile(Bitmap imageToSave, String fileName) {

        File direct = new File(Environment.getExternalStorageDirectory() + "/TestDir");

        if (!direct.exists()) {
            File wallpaperDirectory = new File("/sdcard/DirName/");
            wallpaperDirectory.mkdirs();
        }

        File file = new File(new File("/sdcard/DirName/"), fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            imageToSave.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //For Cad Category Chip View Selection
//    public void setInterestAdapterMulti() {
//        List<UserListData> userListData = new ArrayList<>();
//        String[] interestArray = getResources().getStringArray(R.array.Customization_Types);
//        for (int i = 0; i < interestArray.length; i++) {
//            UserListData guestUserListData = new UserListData();
//            guestUserListData.setName(interestArray[i]);
//            guestUserListData.setSelected(false);
//            userListData.add(guestUserListData);
//        }
//        CadCategoryAdapter interestAdapterMulti = new CadCategoryAdapter(this,
//                userListData, rvInterestMulti.isMultiChoiceMode());
//        rvInterestMulti.setAdapter(interestAdapterMulti);
//    }
    public void selectGuestUserListData(List<UserListData> modifiedListUserData) {
        cadCatList = new ArrayList<>();
        for (int i = 0; i < modifiedListUserData.size(); i++) {
            if (modifiedListUserData.get(i).isSelected()) {
                cadCatList.add(modifiedListUserData.get(i).getName());
            }

        }
        cadCatString = cadCatList.toString().replaceAll("[\\[.\\].\\s+]", "");
    }
    }
