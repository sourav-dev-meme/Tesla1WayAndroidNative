//package com.memeinfotech.tesla1way;
//
//import android.app.AlertDialog;
//import android.app.DatePickerDialog;
//import android.app.Dialog;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Color;
//import android.graphics.Typeface;
//import android.net.Uri;
//import android.os.AsyncTask;
//import android.os.Environment;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.WindowManager;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.DatePicker;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.PopupMenu;
//import android.widget.ProgressBar;
//import android.widget.RelativeLayout;
//import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.memeinfotech.app.meme.background.JSONParser;
//import com.memeinfotech.app.meme.constant.MeMeKeyConstant;
//import com.memeinfotech.app.meme.dataHandler.MeMeSharedPreferences;
//import com.memeinfotech.app.meme.commonViews.ImageUploaderActivty;
//import com.memeinfotech.app.meme.R;
//import com.memeinfotech.app.meme.utility.MeMeUtility;
//import com.memeinfotech.app.meme.models.FamilyMember;
//
//import org.apache.http.HttpException;
//import org.apache.http.NameValuePair;
//import org.apache.http.message.BasicNameValuePair;
//import org.json.JSONException;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//import java.net.URISyntaxException;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.List;
//
//
//public class AddingFamilyMemberActivity extends AppCompatActivity
//{
//    private final int _SELECT_PHOTO = 1;
//    private Bitmap _profileBitmap = null;
//    private ImageView _profilePic;
//    private FamilyMember familyMember;
//    private int _year;
//    private int _month;
//    private int _day;
//    private Calendar _calendar;
//    private Typeface face;
//    private EditText nameEditText;
//    private EditText phoneEditText;
//    private EditText emailEditText;
//    private EditText middleNameEditText;
//    private EditText lastNameEditText;
//    private Spinner titleSpinner;
//    private TextView dateOfBirthTextView;
//    private Spinner bloodGroupSpinner;
//    private Spinner relationshipSpinner;
//    private ProgressBar progressBar;
//    private RelativeLayout saveButton;
//    private RelativeLayout cancelButton;
//    private TextView saveTextView;
//    private boolean isShowFamilyMemberDetail;
//    private RelativeLayout dateLayout;
//    private LinearLayout saveCanceLayout;
//    private Button okButton;
//    private Menu menu;
//    boolean update;
//    private ImageView optionMenuImage;
//    private Uri imageUri;
//    private InputStream profileImageStream;
//    private Bitmap targetBitmap;
//    private TextView titleTextView;
//    String familyMemberID = "0";
//    private Spinner genderSpinner;
//    private final int upload_photo = 25;
//    private String newlyUploadedImageUrl = "";
//    private LinearLayout parrentLayoutForValidation;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_adding_family_member);
//        //getSupportActionBar().hide();
//
//        //Restrict soft keyboard opening
//        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//        //Change font
//        face = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
//        titleTextView = (TextView) findViewById(R.id.family_action_title_textView);
//        titleTextView.setTypeface(face);
//
//        isShowFamilyMemberDetail = getIntent().getExtras().getBoolean(MeMeKeyConstant.PREF_SHOW_FAMILY_DETAIL_FLAG);
//
//        if (isShowFamilyMemberDetail)
//        {
//            familyMember = (FamilyMember) getIntent().getSerializableExtra(MeMeKeyConstant.PREF_FAMILY_MEMBER_OBJECT);
//            familyMemberID = familyMember.getFamilyMemberId();
//        }
//
//        parrentLayoutForValidation = (LinearLayout) findViewById(R.id.field_layout);
//        progressBar = (ProgressBar) findViewById(R.id.user_profile_1_progressBar2);
//        saveButton = (RelativeLayout) findViewById(R.id.user_profile_1_save_button);
//        cancelButton = (RelativeLayout) findViewById(R.id.user_profile_1_skip_button);
//        saveTextView = (TextView) findViewById(R.id.user_profile_save_btn);
//        dateLayout = (RelativeLayout) findViewById(R.id.dob_date_layout);
//        _profilePic = (ImageView) findViewById(R.id.family_imageView);
//        titleSpinner = (Spinner) findViewById(R.id.title_spinner);
//        genderSpinner = (Spinner) findViewById(R.id.gender_spinner);
//        bloodGroupSpinner = (Spinner) findViewById(R.id.user_profile_BloodGroup);
//        relationshipSpinner = (Spinner) findViewById(R.id.user_profile_relation);
//        saveCanceLayout = (LinearLayout) findViewById(R.id.save_cancel_layout);
//        okButton = (Button)findViewById(R.id.ok_button);
//        optionMenuImage = (ImageView) findViewById(R.id.option_menu_imageView);
//        nameEditText = (EditText) findViewById(R.id.family_name_editText);
//        nameEditText.setTypeface(face);
//
//        middleNameEditText = (EditText) findViewById(R.id.family_middle_name_text);
//        middleNameEditText.setTypeface(face);
//
//        lastNameEditText = (EditText) findViewById(R.id.family_last_name_text);
//        lastNameEditText.setTypeface(face);
//
//        dateOfBirthTextView = (TextView) findViewById(R.id.family_date_of_birth);
//        dateOfBirthTextView.setTypeface(face);
//
//        phoneEditText = (EditText) findViewById(R.id.family_phone_number);
//        phoneEditText.setTypeface(face);
//
//        emailEditText = (EditText) findViewById(R.id.family_email_id);
//        emailEditText.setTypeface(face);
//
//        //initialise calender
//        _calendar = Calendar.getInstance();
//        _year = _calendar.get(Calendar.YEAR);
//        _month = _calendar.get(Calendar.MONTH);
//        _day = _calendar.get(Calendar.DAY_OF_MONTH);
//
//        //Add one family view when activity start
//        AddInsuranceView(null);
//
//        MeMeUtility.focusSpinner(lastNameEditText, genderSpinner, false);
//        MeMeUtility.focusSpinner(emailEditText, relationshipSpinner, true);
//
//        inflateFamilyMemberData();
//    }
//
//    private void inflateFamilyMemberData()
//    {
//        titleTextView.setText("New Member");
//
//        if (!isShowFamilyMemberDetail)
//        {
//            optionMenuImage.setVisibility(View.GONE);
//            return;
//        }
//
//        genderSpinner.setSelection(((ArrayAdapter<CharSequence>) genderSpinner.getAdapter()).getPosition(familyMember.getGender()));
//        titleSpinner.setSelection(((ArrayAdapter<CharSequence>) titleSpinner.getAdapter()).getPosition(familyMember.getTitle()));
//        bloodGroupSpinner.setSelection(((ArrayAdapter<CharSequence>) bloodGroupSpinner.getAdapter()).getPosition(familyMember.getBloodGroup()));
//        relationshipSpinner.setSelection(((ArrayAdapter<CharSequence>) relationshipSpinner.getAdapter()).getPosition(familyMember.getRelationship()));
//
//        nameEditText.setText(familyMember.getFirstName());
//        middleNameEditText.setText(familyMember.getMiddleName());
//        lastNameEditText.setText(familyMember.getLastName());
//        dateOfBirthTextView.setText(familyMember.getDob());
//        phoneEditText.setText(familyMember.getPhoneNumber());
//        emailEditText.setText(familyMember.getEmail());
//        showHideUi(false, View.GONE, View.VISIBLE);
//        _profilePic = (ImageView) findViewById(R.id.family_imageView);
//        titleTextView.setText("Member Details");
//        /*if (familyMember.getProfileImageUrl().length() > 1)
//        {
//            DownloadImageTask downloadImageTask = new DownloadImageTask(_profilePic);
//            downloadImageTask.execute(familyMember.getProfileImageUrl());
//        }*/
//        Bitmap bitmap = MeMeUtility.getBitmapFromFile(familyMember.getFamilyMemberId());
//
//        if (bitmap != null)
//        {
//            _profilePic.setImageBitmap(MeMeUtility.getRoundedShape(bitmap));
//        }
//    }
//
//    private void showHideUi(boolean enable, int saveButtonVisibility, int okButtonVisibility)
//    {
//        titleSpinner.setEnabled(enable);
//        titleSpinner.setClickable(enable);
//        bloodGroupSpinner.setEnabled(enable);
//        bloodGroupSpinner.setClickable(enable);
//        relationshipSpinner.setEnabled(enable);
//        relationshipSpinner.setClickable(enable);
//        genderSpinner.setEnabled(enable);
//        genderSpinner.setClickable(enable);
//
//        _profilePic.setEnabled(enable);
//        _profilePic.setClickable(enable);
//        dateLayout.setEnabled(enable);
//        dateLayout.setClickable(enable);
//
//        nameEditText.setEnabled(enable);
//        middleNameEditText.setEnabled(enable);
//        lastNameEditText.setEnabled(enable);
//        dateOfBirthTextView.setEnabled(enable);
//        phoneEditText.setEnabled(enable);
//        emailEditText.setEnabled(enable);
//
//        saveCanceLayout.setVisibility(saveButtonVisibility);
//        okButton.setVisibility(okButtonVisibility);
//    }
//
//    //Open option menu
//    public void openMenu(View view)
//    {
//        //IndividualUserProfile_1.this.openOptionsMenu();
//
//        final PopupMenu popup = new PopupMenu(this, view);
//        final MenuInflater inflater = popup.getMenuInflater();
//        if (saveCanceLayout.getVisibility() != View.VISIBLE)
//        {
//            inflater.inflate(R.menu.menu_family_member_detail_screen, popup.getMenu());
//        }
//        else
//        {
//            inflater.inflate(R.menu.menu_family_member_add, popup.getMenu());
//        }
//
//        popup.show();
//        menu = popup.getMenu();
//        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
//        {
//            @Override
//            public boolean onMenuItemClick(MenuItem item)
//            {
//                if (item.getTitle().equals("Edit"))
//                {
//                    showHideUi(true, View.VISIBLE, View.GONE);
//                    update = true;
//
//                    titleTextView.setText("Edit Member Details");
//
//                    /*isEditProfileCall = true;
//                    showHideEditOption(View.VISIBLE, true, 0.5f, View.GONE, View.GONE);
//                    saveTextView.setText("Update");
//                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);*/
//                }
//                else if (item.getTitle().equals("Cancel"))
//                {
//                    /*Intent dashboardIntent = new Intent(IndividualUserProfile_1.this, DashBoardActivity.class);
//                    dashboardIntent.putExtra("show_mobile_verification_dialog", false);
//                    startActivity(dashboardIntent);*/
//                    finish();
//                }
//                else if (item.getTitle().equals("Update"))
//                {
//                    AddFamilyMemberBackground addFamilyMemberBackground = new AddFamilyMemberBackground(titleSpinner.getSelectedItem().toString(), nameEditText.getText().toString()
//                            ,middleNameEditText.getText().toString(), lastNameEditText.getText().toString(), dateOfBirthTextView.getText().toString(), bloodGroupSpinner.getSelectedItem().toString()
//                            ,phoneEditText.getText().toString(), emailEditText.getText().toString(), relationshipSpinner.getSelectedItem().toString(), genderSpinner.getSelectedItem().toString());
//                    addFamilyMemberBackground.execute();
//
//                    progressBar.setVisibility(View.VISIBLE);
//                    saveTextView.setText("Saving...");
//                    saveButton.setEnabled(false);
//                    saveButton.setClickable(false);
//                    cancelButton.setClickable(false);
//                    cancelButton.setEnabled(false);
//                    //saveOnClick(null);
//                }
//                else if (item.getTitle().equals("Remove"))
//                {
//
//                    AlertDialog.Builder builder = new AlertDialog.Builder(AddingFamilyMemberActivity.this);
//
//                    // Pass null as the parent view because its going in the dialog layout
//                    builder.setMessage("Do you want to remove this Member?").setPositiveButton("yes", new DialogInterface.OnClickListener()
//                            {
//                                @Override
//                                public void onClick(DialogInterface dialog, int id)
//                                {
//                                    RemoveAllergyBackground removeAllergyBackground = new RemoveAllergyBackground(familyMember.getFamilyMemberId());
//                                    removeAllergyBackground.execute();
//                                }
//                            })
//                            .setNegativeButton("No", new DialogInterface.OnClickListener()
//                            {
//                                public void onClick(DialogInterface dialog, int id)
//                                {
//                                }
//                            });
//                    AlertDialog removeDialog = builder.create();
//                    removeDialog.show();
//                    /*RemoveAllergyBackground removeAllergyBackground = new RemoveAllergyBackground(familyMember.getFamilyMemberId());
//                    removeAllergyBackground.execute();*/
//                    //saveOnClick(null);
//                }
//                return false;
//            }
//        });
//    }
//
//    private void openRemoveFamilyMemberConfirmationDialog()
//    {
//
//    }
//
//    public  void addNewFamilyMemberCard(View v)
//    {
//        /*Intent returnIntent = new Intent();
//        String name = nameEditText.getText().toString() + " " + middleNameEditText.getText().toString() + " " + lastNameEditText.getText().toString();
//
//        returnIntent.putExtra("member_name",name.replace("  +", " "));
//        returnIntent.putExtra("member_no", phoneEditText.getText().toString());
//        returnIntent.putExtra("member_mail", emailEditText.getText().toString());
//        returnIntent.putExtra("profileBitmap", _profileBitmap);
//        setResult(Activity.RESULT_OK,returnIntent);
//        finish();*/
//        /*(String title, String firstName, String middleName, String lastName,
//                  String dob, String bloodGroup, String phoneNumber, String email, String relationship)*/
//
//        if (nameEditText.getText().toString().length() > 0 && lastNameEditText.getText().toString().length() > 0 && !genderSpinner.getSelectedItem().toString().equalsIgnoreCase("Select gender")
//                && !dateOfBirthTextView.getText().toString().equalsIgnoreCase("Date of birth") && !relationshipSpinner.getSelectedItem().toString().equalsIgnoreCase("Select relationship")) {
//            AddFamilyMemberBackground addFamilyMemberBackground = new AddFamilyMemberBackground(titleSpinner.getSelectedItem().toString(), nameEditText.getText().toString()
//                    , middleNameEditText.getText().toString(), lastNameEditText.getText().toString(), dateOfBirthTextView.getText().toString(), bloodGroupSpinner.getSelectedItem().toString()
//                    , phoneEditText.getText().toString(), emailEditText.getText().toString(), relationshipSpinner.getSelectedItem().toString(), genderSpinner.getSelectedItem().toString());
//            addFamilyMemberBackground.execute();
//
//            progressBar.setVisibility(View.VISIBLE);
//            saveTextView.setText("Saving...");
//            saveButton.setEnabled(false);
//            saveButton.setClickable(false);
//            cancelButton.setClickable(false);
//            cancelButton.setEnabled(false);
//            MeMeUtility.setValidationIndicator(parrentLayoutForValidation, MeMeKeyConstant.COLOR_GENERAL);
//        } else {
//            MeMeUtility.setValidationIndicator(parrentLayoutForValidation, MeMeKeyConstant.COLOR_ERROR);
//            Toast.makeText(AddingFamilyMemberActivity.this, "All the highlighted fields are mandatory.", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    @Override
//    protected Dialog onCreateDialog(int id)
//    {
//        if (id == 999)
//        {
//            return new DatePickerDialog(this, myDateListener, _year, _month, _day);
//        }
//        return null;
//    }
//
//    public void setDateOfBirth(View v)
//    {
//        showDialog(999);
//    }
//
//    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener()
//    {
//        @Override
//        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3)
//        {
//            /*arg1 = _year;
//            arg2 = _month;
//            arg3 = _day;*/
//
//            TextView dateOfBirthTextView = (TextView) findViewById(R.id.family_date_of_birth);
//            dateOfBirthTextView.setText(arg3 +"/" + (arg2 + 1) + "/" + arg1);
//            dateOfBirthTextView.setTextColor(Color.parseColor("#212121"));
//        }
//    };
//
//
//    //Add family view when user touch Add new button
//    public void AddInsuranceView(View v)
//    {
//        /*Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
//
//        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.insurance_layout_holder);
//        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = inflater.inflate(R.layout.adding_family_layout, null);
//        System.out.println("zzz view trace:" + view);
//        linearLayout.addView(view);
//
//        //_profilePic = (ImageView) view.findViewById(R.id.family_imageView);
//        TextView titleTextView = (TextView) view.findViewById(R.id.family_title_textView);
//        titleTextView.setTypeface(face);
//
//        EditText nameEditText = (EditText) view.findViewById(R.id.family_name_editText);
//        nameEditText.setTypeface(face);
//
//        EditText middleNameEditText = (EditText) view.findViewById(R.id.family_middle_name_text);
//        middleNameEditText.setTypeface(face);
//
//        EditText lastNameEditText = (EditText) view.findViewById(R.id.family_last_name_text);
//        lastNameEditText.setTypeface(face);
//
//        TextView dateOfBirthTextView = (TextView) view.findViewById(R.id.family_date_of_birth);
//        dateOfBirthTextView.setTypeface(face);
//
//        EditText phoneEditText = (EditText) view.findViewById(R.id.family_phone_number);
//        phoneEditText.setTypeface(face);
//
//        EditText emailEditText = (EditText) view.findViewById(R.id.family_email_id);
//        emailEditText.setTypeface(face);*/
//    }
//
//    //Remove family view
//    public void removeInsuranceView(View view)
//    {
//        /*LinearLayout linearLayout = (LinearLayout) findViewById(R.id.insurance_layout_holder);
//
//        //linearLayout.removeView(view);
//        if (linearLayout.getChildCount() == 1)
//        {
//            return;
//        }
//        else
//        {
//           linearLayout.removeView((View) view.getParent().getParent().getParent());
//        }*/
//    }
//
//    //Provide the Screen for picking image from phone,sd card,drive etc...
//    public void pickImage(View view)
//    {
//        /*Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
//        photoPickerIntent.setType("image*//*");
//        //startActivityForResult(photoPickerIntent, _SELECT_PHOTO);
//
//        photoPickerIntent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(photoPickerIntent, "Select Picture"), 1);*/
//        _profilePic = (ImageView) view;
//
//
//        Intent intent = new Intent(AddingFamilyMemberActivity.this, ImageUploaderActivty.class);
//        intent.putExtra("type", "family_member");
//
//        if (familyMember != null)
//        {
//            intent.putExtra("img_fileName", familyMember.getFamilyMemberId());
//        }
//        else
//        {
//            intent.putExtra("img_fileName", "temp");
//        }
//
//        startActivityForResult(intent, upload_photo);
//    }
//
//    //receive all Activity result like contact picker image picker
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent)
//    {
//        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
//
//        switch(requestCode)
//        {
//            case _SELECT_PHOTO:
//            {
//                if(resultCode == RESULT_OK)
//                {
//                    try
//                    {
//
//                        imageUri = imageReturnedIntent.getData();
//                        profileImageStream = getContentResolver().openInputStream(imageUri);
//                        targetBitmap = BitmapFactory.decodeStream(profileImageStream);
//                        System.out.println("targetBitmap Trace::" + targetBitmap);
//                        _profilePic.setImageBitmap(MeMeUtility.getRoundedShape(targetBitmap));
//                        //System.out.println("image trace "  + selectedImage);
//                    }
//                    catch (FileNotFoundException e)
//                    {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            break;
//            case upload_photo:
//            {
//                if(resultCode == RESULT_OK)
//                {
//                    newlyUploadedImageUrl = imageReturnedIntent.getExtras().getString("img_url");
//                    System.out.println("uploaded Image url trace : "  + newlyUploadedImageUrl);
//
//                    byte[] byteArray = imageReturnedIntent.getByteArrayExtra(MeMeKeyConstant.EXTRA_SEARCH_DOCTOR_BITMAP);
//                    Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
//
//                    //Bitmap bmp = (Bitmap) imageReturnedIntent.getParcelableExtra(MeMeKeyConstant.EXTRA_SEARCH_DOCTOR_BITMAP);
//                    if(bmp != null)
//                        _profilePic.setImageBitmap(MeMeUtility.getRoundedShape(bmp));
//                }
//            }
//
//            break;
//        }
//    }
//
//    /*private class DownloadImageTask extends AsyncTask<String, Void, Bitmap>
//    {
//        ImageView bmImage;
//
//        public DownloadImageTask(ImageView bmImage)
//        {
//            this.bmImage = bmImage;
//        }
//
//        protected Bitmap doInBackground(String... urls)
//        {
//            String urldisplay = urls[0];
//            Bitmap mIcon11 = null;
//
//            try
//            {
//                InputStream in = new java.net.URL(urldisplay).openStream();
//                mIcon11 = BitmapFactory.decodeStream(in);
//            }
//            catch (Exception e)
//            {
//                Log.e("Error", e.getMessage());
//                e.printStackTrace();
//            }
//            return mIcon11;
//        }
//
//        protected void onPostExecute(Bitmap result)
//        {
//            if (result != null)
//            {
//                bmImage.setImageBitmap(MeMeUtility.getRoundedShape(result));
//            }
//
//            bmImage.setVisibility(View.VISIBLE);
//            //imageLoadingProgresBar.setVisibility(View.GONE);
//            //imageLoadingTextView.setVisibility(View.GONE);
//        }
//    }*/
//
//    public void cancelOnclick(View view)
//    {
//        finish();
//    }
//
//    public class AddFamilyMemberBackground extends AsyncTask<String,String,String>
//    {
//        //private static final String createDoctorStep1="http://1-dot-meme-1080.appspot.com/rest/createDoctorStep1";
//        //private static final String createDoctorStep1 = "http://1-dot-meme-1080.appspot.com/rest/post/setFamilyMemberInfo";
//        //private static final String createDoctorStep1 = "http://meme-1080.appspot.com/rest/post/setFamilyMemberInfo";
//        //private static final String createDoctorStep1 = "https://users-dot-meme-care-prod.appspot.com/rest/v1/post/setFamilyMemberInfo";
//        private static final String createDoctorStep1 = "https://users-dot-meme-care-prod.appspot.com/rest/v1/post/setFamilyMemberInfo";
//
//        String title;
//        String firstName;
//        String middleName;
//        String lastName;
//        String dob;
//        String bloodGroup;
//        String phoneNumber;
//        String email;
//        String relationship;
//        private String uploadedImageURL = "";
//        String gender;
//
//        public AddFamilyMemberBackground(String title, String firstName, String middleName, String lastName,
//                                      String dob, String bloodGroup, String phoneNumber, String email, String relationship, String gender)
//        {
//            this.title = title;
//            this.firstName = firstName;
//            this.middleName = middleName;
//            this.lastName = lastName;
//            this.dob = dob;
//            this.bloodGroup = bloodGroup;
//            this.phoneNumber = phoneNumber;
//            this.email = email;
//            this.relationship = relationship;
//            this.gender = gender;
//        }
//
//        @Override
//        protected String doInBackground(String... values)
//        {
//            if (newlyUploadedImageUrl.length() > 0)
//            {
//                uploadedImageURL = newlyUploadedImageUrl;
//            }
//            else
//            {
//                if (familyMember != null)
//                    uploadedImageURL = familyMember.getProfileImageUrl();
//            }
//
//            /*=========================================================================================================================================================*/
//
//            List<NameValuePair> params = new ArrayList<NameValuePair>();
//            params.add(new BasicNameValuePair("title", title));
//            //System.out.println("addingFamily  title:" + title);
//            params.add(new BasicNameValuePair("first_name", firstName));
//            //System.out.println("addingFamily  firstName:" + firstName);
//            params.add(new BasicNameValuePair("middle_name", middleName));
//            //System.out.println("addingFamily  middleName:" + middleName);
//            params.add(new BasicNameValuePair("last_name", lastName));
//            //System.out.println("addingFamily  lastName:" + lastName);
//            params.add(new BasicNameValuePair("dob", dob));
//            //System.out.println("addingFamily  dob:" + dob);
//            params.add(new BasicNameValuePair("blood_group", bloodGroup));
//            //System.out.println("addingFamily  bloodGroup:" + bloodGroup);
//            params.add(new BasicNameValuePair("phone_number", phoneNumber));
//            //System.out.println("addingFamily  phoneNumber:" + phoneNumber);
//            params.add(new BasicNameValuePair("email", email));
//            //System.out.println("addingFamily  email:" + email);
//            params.add(new BasicNameValuePair("relationship",relationship));
//            //System.out.println("addingFamily  relationship:" + relationship);
//            String userId = MeMeSharedPreferences.getStringPreference(AddingFamilyMemberActivity.this, MeMeKeyConstant.PREF_USER_ID);
//            params.add(new BasicNameValuePair("userId", userId));
//            params.add(new BasicNameValuePair("profile_image_url", uploadedImageURL));
//            params.add(new BasicNameValuePair("family_member_id",familyMemberID));
//            params.add(new BasicNameValuePair("gender",gender));
//
//            /*if (update)
//            {
//                params.add(new BasicNameValuePair("family_member_id",familyMember.getFamilyMemberId()));
//            }*/
//
//            //System.out.println("addingFamily  userId:" + userId);
//            // params.add(new BasicNameValuePair("userID", MeMeSharedPreferences.getStringPreference(AddingFamilyMemberActivity.this, MeMeKeyConstant.PREF_USER_ID)));
//            //params.add(new BasicNameValuePair("family_member_id","3"));//during update you need to send this parameter
//
//            System.out.println("userId trace::::" + MeMeSharedPreferences.getStringPreference(AddingFamilyMemberActivity.this, MeMeKeyConstant.PREF_USER_ID));
//
//            JSONParser jsonParser = new JSONParser();
//            String json = "";
//            try
//            {
//                json = jsonParser.getJSONFromUrl(createDoctorStep1, params);
//                System.out.println("AddFamilyMember | doInBackground :::" + json);
//            }
//            catch (URISyntaxException e)
//            {
//                e.printStackTrace();
//            }
//            catch (HttpException e)
//            {
//                e.printStackTrace();
//            }
//            catch (JSONException e)
//            {
//                e.printStackTrace();
//            }
//            return json;
//
//        }
//
//        @Override
//        protected void onPostExecute(String s)
//        {
//            super.onPostExecute(s);
//
//            if (s != null)
//            {
//                if (s.contains("success"))
//                {
//                    //Updating the flag to load the latest family information in dashboard
//                    MeMeUtility.setUserInfoApiCallRequired(true);
//
//                    if (uploadedImageURL.length() > 0)
//                    {
//
//                        String[] returnArr = s.split(",");
//                        String memberId = returnArr[1];
//                        File oldFile = new File(Environment.getExternalStorageDirectory() + File.separator + "temp.jpg");
//                        File toFile = new File(Environment.getExternalStorageDirectory() + File.separator + memberId + ".jpg");
//                        oldFile.renameTo(toFile);
//                        System.out.println("toFile trace: " + toFile);
//                        System.out.println("oldFile.getName() trace: " + oldFile.getName());
//
//                        MeMeSharedPreferences.addStringPreference(AddingFamilyMemberActivity.this, uploadedImageURL, memberId);
//                    }
//
//                    finish();
//                }
//                else if (s.contains("<html>"))
//                {
//                    AddFamilyMemberBackground addFamilyMemberBackground = new AddFamilyMemberBackground(title,  firstName, middleName, lastName, dob, bloodGroup, phoneNumber, email, relationship, gender);
//                    addFamilyMemberBackground.execute();
//                }
//                else
//                {
//                    showError();
//                }
//            }
//            else
//            {
//                showError();
//            }
//        }
//    }
//
//    private void showError()
//    {
//        Toast.makeText(AddingFamilyMemberActivity.this, "Server Error. Try again.", Toast.LENGTH_LONG).show();
//        progressBar.setVisibility(View.GONE);
//        saveTextView.setText("Save");
//        saveButton.setEnabled(true);
//        saveButton.setClickable(true);
//        cancelButton.setClickable(true);
//        cancelButton.setEnabled(true);
//    }
//
//    //Remove allergy from server
//    public class RemoveAllergyBackground extends AsyncTask<String,String,String>
//    {
//        //private static final String removeFamilyMember = "http://1-dot-meme-1080.appspot.com/rest/removeFamilymember?familyMemberId=";
//        private static final String removeFamilyMember = "https://users-dot-meme-care-prod.appspot.com/rest/v1/post/removeFamilymember?familyMemberId=";
//        String familyMemberId;
//
//        public RemoveAllergyBackground(String familyMemberId)
//        {
//            this.familyMemberId = familyMemberId;
//        }
//
//        @Override
//        protected String doInBackground(String... values)
//        {
//            List<NameValuePair> params = new ArrayList<NameValuePair>();
//            //params.add(new BasicNameValuePair("allergyId",allergyId));
//            JSONParser jsonParser = new JSONParser();
//            String json = "";
//            try
//            {
//                json = jsonParser.getJSONFromUrl(removeFamilyMember + familyMemberId, params);
//                System.out.println("Remove FamilyMember | doInBackground :::" + json);
//            }
//            catch (URISyntaxException e){ e.printStackTrace(); }
//            catch (HttpException e){e.printStackTrace();}
//            catch (JSONException e){ e.printStackTrace();}
//            return json;
//        }
//
//        @Override
//        protected void onPostExecute(String s)
//        {
//            super.onPostExecute(s);
//            if (s.equals("success"))
//            {
//                //Updating the flag to load the latest family information in dashboard
//                MeMeUtility.setUserInfoApiCallRequired(true);
//
//                finish();
//            }
//            else
//            {
//                Toast.makeText(getApplicationContext(), "Server Error. Try again.", Toast.LENGTH_LONG).show();
//            }
//        }
//    }
//}