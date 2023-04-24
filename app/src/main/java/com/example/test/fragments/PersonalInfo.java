package com.example.test.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import com.example.test.R;
import com.example.test.utils.FragmentChanger;
import com.example.test.utils.MyDBHelper;

import java.util.Calendar;

public class PersonalInfo extends Fragment {

    public PersonalInfo() {
        // Required empty public constructor
    }


    private Button btnPersonalInfoSubmit;
    private EditText firstName, lastName, phoneNumber;
    private RadioGroup radioGroup;
    private ImageView calendar;
    private TextView date;
    private RadioButton radioButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personal_info, container, false);

        btnPersonalInfoSubmit = view.findViewById(R.id.btn_personal_info_submit);
        firstName = view.findViewById(R.id.edit_first_name);
        lastName = view.findViewById(R.id.edit_last_name);
        phoneNumber = view.findViewById(R.id.edit_phone_number);
        radioGroup = view.findViewById(R.id.radio_gender);
        calendar = view.findViewById(R.id.button_select_date);
        date = view.findViewById(R.id.text_selected_date);


//        To select date of birth
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                // Update the TextView with the selected date
                                String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                                date.setText(selectedDate);
                            }
                        }, year, month, dayOfMonth);

                datePickerDialog.show();
            }
        });


        btnPersonalInfoSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields(firstName, lastName, phoneNumber, radioGroup, date)) {
                    String fName = firstName.getText().toString();
                    String lName = lastName.getText().toString();
                    String pNumber = phoneNumber.getText().toString();
                    int radioId = radioGroup.getCheckedRadioButtonId();
                    Log.d("myTag", radioId+"");
                    radioButton = view.findViewById(radioId);
                    String gender = radioButton.getText().toString();
                    String dob = date.getText().toString();
                    MyDBHelper myDBHelper = new MyDBHelper(getContext());
                    myDBHelper.addPersonalInfo(fName, lName, pNumber, gender, dob);
                    FragmentChanger.changeFragment(getActivity(), new EmployeeInfo());
                }
            }
        });


        return view;
    }

    private boolean validateFields(EditText firstName, EditText lastName, EditText phoneNumber, RadioGroup radioGroup, TextView textView) {
        // Validate first name
        if (TextUtils.isEmpty(firstName.getText().toString().trim())) {
            firstName.setError("First name is required");
            firstName.requestFocus();
            return false;
        }

        // Validate last name
        if (TextUtils.isEmpty(lastName.getText().toString().trim())) {
            lastName.setError("Last name is required");
            lastName.requestFocus();
            return false;
        }

        // Validate phone number
        if (TextUtils.isEmpty(phoneNumber.getText().toString().trim())) {
            phoneNumber.setError("Phone number is required");
            phoneNumber.requestFocus();
            return false;
        }

        // Validate gender
        int selectedGenderId = radioGroup.getCheckedRadioButtonId();
        if (selectedGenderId == -1) {
            Toast.makeText(getContext(), "Please select a gender", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(textView.getText().toString())) {
            Toast.makeText(getContext(), "Please select a date", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

}