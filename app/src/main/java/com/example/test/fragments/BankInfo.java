package com.example.test.fragments;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.test.MainActivity;
import com.example.test.R;
import com.example.test.utils.FragmentChanger;
import com.example.test.utils.MyDBHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BankInfo extends Fragment {

    public BankInfo() {
        // Required empty public constructor
    }

    private EditText bName, aNo, iCode;
    private Spinner bAdd;
    private ImageView cam;
    private final int CAMERA_REQUEST_CODE = 101;

    private Button btnBankInfoSubmit;
    String imagePath;

    String branchName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bank_info, container, false);

        bName = view.findViewById(R.id.bank_name);
        aNo = view.findViewById(R.id.bank_account_no);
        iCode = view.findViewById(R.id.ifsc_code);
        bAdd = view.findViewById(R.id.branch_name);
        cam = view.findViewById(R.id.camera_button);
        btnBankInfoSubmit = view.findViewById(R.id.btn_bank_info_submit);



        bAdd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                branchName = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);

            }
        });

        btnBankInfoSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields(bName, bAdd,aNo, iCode)) {
                    String bankName = bName.getText().toString();
                    String accountNo = aNo.getText().toString();
                    String ifscCode = iCode.getText().toString();
                    MyDBHelper myDBHelper = new MyDBHelper(getContext());
                    myDBHelper.addBankInfo(bankName, branchName, accountNo, ifscCode, imagePath);
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });


        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            // Save the image to local storage
            imagePath = saveImageToStorage(imageBitmap);
            Log.d("myTag", imagePath);

            // Update the ImageView with the captured image
            cam.setImageBitmap(imageBitmap);
        }
    }

    private String saveImageToStorage(Bitmap bitmap) {
        // Get the directory for storing images
        File directory = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        // Create a new file with a unique name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = "IMG_" + timeStamp + ".jpg";
        File file = new File(directory, fileName);

        // Save the image to the file
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Return the path to the file
        return file.getAbsolutePath();
    }



    private boolean validateFields(EditText bankName, Spinner branch, EditText accountNo, EditText ifcoCode) {
        // Validate first name
        if (TextUtils.isEmpty(bankName.getText().toString().trim())) {
            bankName.setError("Bank name is required");
            bankName.requestFocus();
            return false;
        }

        // Validate last name
        if (TextUtils.isEmpty(accountNo.getText().toString().trim())) {
            accountNo.setError("Account No is required");
            accountNo.requestFocus();
            return false;
        }

        // Validate phone number
        if (TextUtils.isEmpty(ifcoCode.getText().toString().trim())) {
            ifcoCode.setError("Phone number is required");
            ifcoCode.requestFocus();
            return false;
        }

        if (branch.getSelectedItemPosition() == 0) {
            Toast.makeText(getContext(), "Please select Branch", Toast.LENGTH_SHORT).show();
            branch.requestFocus();
            return false;
        }


        return true;
    }

}