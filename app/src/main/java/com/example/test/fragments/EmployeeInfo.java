package com.example.test.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import com.example.test.R;
import com.example.test.utils.FragmentChanger;
import com.example.test.utils.MyDBHelper;

public class EmployeeInfo extends Fragment {


    public EmployeeInfo() {
        // Required empty public constructor
    }


    private Button btnEmployeeInfoSubmit;
    private EditText employeeNo, employeeName, employeeDesignation;
    private Spinner accountType, experience;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_employee_info, container, false);
        employeeNo = view.findViewById(R.id.employee_no_input);
        employeeName = view.findViewById(R.id.employee_name_input);
        employeeDesignation = view.findViewById(R.id.designation_input);
        accountType = view.findViewById(R.id.account_type_spinner);
        experience = view.findViewById(R.id.work_experience_spinner);
        btnEmployeeInfoSubmit = view.findViewById(R.id.btn_employee_info_submit);

        final String[] aType = {""};
        final String[] eYears = {""};
        accountType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                aType[0] = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        experience.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                eYears[0] = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnEmployeeInfoSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                if (valid(employeeNo, employeeName, employeeDesignation, accountType, experience)) {
                    String eNo = employeeNo.getText().toString();
                    String eName = employeeName.getText().toString();
                    String eDesignation = employeeDesignation.getText().toString();
                    String accountType = aType[0];
                    String experience = eYears[0];
                    MyDBHelper myDBHelper = new MyDBHelper(getContext());
                    myDBHelper.addEmployeeInfo(eNo, eName, eDesignation, accountType, experience);
                    FragmentChanger.changeFragment(getActivity(), new BankInfo());
                }
            }
        });

        return view;
    }

    private boolean isEditTextEmpty(EditText editText) {
        String text = editText.getText().toString().trim();
        return TextUtils.isEmpty(text);
    }

    private boolean valid(EditText editText, EditText editText1, EditText ed3, Spinner spinner1, Spinner spinner2) {
        if (isEditTextEmpty(editText)) {
            editText.setError("Name is required");
            editText.requestFocus();
            return false;
        }
        if (isEditTextEmpty(editText1)) {
            editText1.setError("Name is required");
            editText1.requestFocus();
            return false;
        }

        if (isEditTextEmpty(ed3)) {
            ed3.setError("Designation is required");
            ed3.requestFocus();
            return false;
        }

        if (spinner1.getSelectedItemPosition() == 0) {
            Toast.makeText(getContext(), "Please select an Account type", Toast.LENGTH_SHORT).show();
            spinner1.requestFocus();
            return false;
        }

        if (spinner2.getSelectedItemPosition() == 0) {
            Toast.makeText(getContext(), "Please select your experience", Toast.LENGTH_SHORT).show();
            spinner2.requestFocus();
            return false;
        }

        return true;
    }


}