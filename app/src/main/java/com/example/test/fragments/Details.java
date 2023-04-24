package com.example.test.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.MainActivity;
import com.example.test.R;
import com.example.test.models.BankInfoModel;
import com.example.test.models.EmployeeInfoModel;
import com.example.test.models.PersonalInfoModel;
import com.example.test.utils.DetailsAdapter;
import com.example.test.utils.FragmentChanger;
import com.example.test.utils.MyDBHelper;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

public class Details extends Fragment {

    public Details() {
        // Required empty public constructor
    }

    private int id;
    private int p;
    private CircularImageView profile;
    private TextView name, dOB, gender, age, pNumber, eNo, eName, designation, accountType, experience, bankName, branchName, accountNo, ifscCode;
    private EditText editName, editdOB, editgender, editage, editpNumber, editeNo, editeName, editdesignation, editaccountType, editexperience, editbankName, editbranchName, editaccountNo, editifscCode;
    private ImageView btnEdit;
    private Button btnUpdate;

    private boolean isEditVisible = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        Bundle bundle = getArguments();
        id = bundle.getInt("ID");
        Log.d("myTag", "Recieve " + id);

        profile = view.findViewById(R.id.detail_profile);
        name = view.findViewById(R.id.detail_name);
        dOB = view.findViewById(R.id.date_of_birth);
        gender = view.findViewById(R.id.gender);
        pNumber = view.findViewById(R.id.detail_phone_number);
        age = view.findViewById(R.id.age);
        eNo = view.findViewById(R.id.detail_employee_no);
        eName = view.findViewById(R.id.detail_employee_name);
        designation = view.findViewById(R.id.detail_designation);
        accountType = view.findViewById(R.id.details_account_tye);
        experience = view.findViewById(R.id.details_experience);
        bankName = view.findViewById(R.id.detail_bank_name);
        branchName = view.findViewById(R.id.detail_branch_name);
        accountNo = view.findViewById(R.id.detail_bank_account_no);
        ifscCode = view.findViewById(R.id.detail_ifsc_code);

        editName = view.findViewById(R.id.detail_edit_name);
        editdOB = view.findViewById(R.id.detail_edit_date_of_birth);
        editgender = view.findViewById(R.id.edit_gender);
        editage = view.findViewById(R.id.edit_age);
        editpNumber = view.findViewById(R.id.detail_edit_phone_number);
        editeNo = view.findViewById(R.id.edit_detail_employee_no);
        editeName = view.findViewById(R.id.edit_detail_employee_name);
        editdesignation = view.findViewById(R.id.edit_detail_designation);
        editaccountType = view.findViewById(R.id.edit_details_account_tye);
        editaccountNo = view.findViewById(R.id.edit_detail_bank_account_no);
        editexperience = view.findViewById(R.id.edit_details_experience);
        editbankName = view.findViewById(R.id.edit_detail_bank_name);
        editbranchName = view.findViewById(R.id.edit_detail_branch_name);
        editifscCode = view.findViewById(R.id.edit_detail_ifsc_code);

        btnEdit = view.findViewById(R.id.btn_edit);
        btnUpdate = view.findViewById(R.id.update_details);


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFieldsVisibility();
            }
        });






        fetchData();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDBHelper dbHelper = new MyDBHelper(getContext());
                PersonalInfoModel personalInfoModel = new PersonalInfoModel();
                personalInfoModel.setId(id); // Set the ID of the record to update
                String n = editName.getText().toString();
                String[] sp = n.split(" ");
                personalInfoModel.setFirstName(sp[0]);
                personalInfoModel.setLastName(sp[1]);
                personalInfoModel.setPhoneNumber(editpNumber.getText().toString());
                personalInfoModel.setGender(editgender.getText().toString());
                personalInfoModel.setDob(editdOB.getText().toString());


                EmployeeInfoModel employeeInfoModel = new EmployeeInfoModel();
                employeeInfoModel.setId(id);
                employeeInfoModel.setEmployeeNo(editeNo.getText().toString());
                employeeInfoModel.setEmployeeName(editeName.getText().toString());
                employeeInfoModel.setDesignation(editdesignation.getText().toString());
                employeeInfoModel.setAccountType(editaccountType.getText().toString());
                employeeInfoModel.setDesignation(editexperience.getText().toString());
                dbHelper.updateEmployeeInfo(employeeInfoModel);

                BankInfoModel bankInfoModel = new BankInfoModel();
                bankInfoModel.setBankName(editbankName.getText().toString());
                bankInfoModel.setBranchName(editbranchName.getText().toString());
                bankInfoModel.setAccountNo(editaccountNo.getText().toString());
                bankInfoModel.setIfscCode(editifscCode.getText().toString());
                bankInfoModel.setPhotoPath("");


                startActivity(new Intent(getContext(), MainActivity.class));
            }
        });


        return view;
    }

    private void fetchData() {
        MyDBHelper myDBHelper = new MyDBHelper(getContext());
        PersonalInfoModel personalInfoModel = myDBHelper.readPersonalData(id);
        name.setText(personalInfoModel.getFirstName() + " " + personalInfoModel.getLastName());
        dOB.setText(personalInfoModel.getDob());
        gender.setText(personalInfoModel.getGender());
        pNumber.setText(personalInfoModel.getPhoneNumber());
        int a = FragmentChanger.getAge(personalInfoModel);
        age.setText(a + "");
        editName.setText(personalInfoModel.getFirstName() + " " + personalInfoModel.getLastName());
        editdOB.setText(personalInfoModel.getDob());
        editgender.setText(personalInfoModel.getGender());
        editpNumber.setText(personalInfoModel.getPhoneNumber());
        editage.setText(a + "");


        EmployeeInfoModel employeeInfoModel = myDBHelper.readEmployeeInfo(id);
        eNo.setText(employeeInfoModel.getEmployeeNo());
        eName.setText(employeeInfoModel.getEmployeeName());
        designation.setText(employeeInfoModel.getDesignation());
        accountType.setText(employeeInfoModel.getAccountType());
        experience.setText(employeeInfoModel.getExperience());

        editeNo.setText(employeeInfoModel.getEmployeeNo());
        editName.setText(employeeInfoModel.getEmployeeName());
        editdesignation.setText(employeeInfoModel.getDesignation());
        editaccountType.setText(employeeInfoModel.getAccountType());
        editexperience.setText(employeeInfoModel.getExperience());

        BankInfoModel bankInfoModel = myDBHelper.readBankInfo(id);
        bankName.setText(bankInfoModel.getBankName());
        branchName.setText(bankInfoModel.getBranchName());
        accountNo.setText(bankInfoModel.getAccountNo());
        ifscCode.setText(bankInfoModel.getIfscCode());

        editbankName.setText(bankInfoModel.getBankName());
        editbranchName.setText(bankInfoModel.getBranchName());
        editaccountNo.setText(bankInfoModel.getAccountNo());
        editifscCode.setText(bankInfoModel.getIfscCode());
        Log.d("myTag", bankInfoModel.getPhotoPath());

    }

    private void EditTextVisible() {
        EditText[] allEditTexts = {editName, editdOB, editgender, editage, editpNumber, editeNo, editeName, editdesignation, editaccountType, editaccountNo, editexperience, editbankName, editbranchName, editifscCode};
        Button btn = btnUpdate;
        btn.setVisibility(View.VISIBLE);
        for (EditText editText : allEditTexts) {
            editText.setVisibility(View.VISIBLE);
        }
    }

    private void EditTextInVisible() {
        EditText[] allEditTexts = {editName, editdOB, editgender, editage, editpNumber, editeNo, editeName, editdesignation, editaccountType, editaccountNo, editexperience, editbankName, editbranchName, editifscCode};
        Button button = btnUpdate;
        button.setVisibility(View.GONE);
        for (EditText editText : allEditTexts) {
            editText.setVisibility(View.GONE);
        }
    }

    private void textViewVisible() {
        TextView[] allEditTexts = {name, dOB, gender, age, pNumber, eNo, eName, designation, accountType, experience, bankName, branchName, accountNo, ifscCode};

        for (TextView editText : allEditTexts) {
            editText.setVisibility(View.VISIBLE);
        }
    }

    private void textViewInVisible() {
        TextView[] allEditTexts = {name, dOB, gender, age, pNumber, eNo, eName, designation, accountType, experience, bankName, branchName, accountNo, ifscCode};

        for (TextView editText : allEditTexts) {
            editText.setVisibility(View.GONE);
        }
    }

    private void toggleFieldsVisibility() {
        if (isEditVisible) {
            btnEdit.setImageResource(R.drawable.ic_edit);
            EditTextInVisible();
            textViewVisible();
        } else {
            btnEdit.setImageResource(R.drawable.ic_cancel);
            EditTextVisible();
            textViewInVisible();
        }
        isEditVisible = !isEditVisible;
    }
}
