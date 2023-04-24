package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.test.fragments.BankInfo;
import com.example.test.fragments.Details;
import com.example.test.fragments.PersonalInfo;
import com.example.test.models.BankInfoModel;
import com.example.test.models.EmployeeInfoModel;
import com.example.test.models.PersonalInfoModel;
import com.example.test.utils.DetailsAdapter;
import com.example.test.utils.MyDBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {

    private FloatingActionButton btnAdd;
    private RecyclerView recyclerView;
    ArrayList<PersonalInfoModel> personalInfoModelArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MyDBHelper myDBHelper = new MyDBHelper(this);
        ArrayList<PersonalInfoModel> personalInfoModelArrayList = myDBHelper.readPersonalData();
        ArrayList<BankInfoModel> bankInfoModelArrayList = myDBHelper.readBankInfo();





        btnAdd = findViewById(R.id.btn_add);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DetailsAdapter detailsAdapter = new DetailsAdapter(this, personalInfoModelArrayList);
        recyclerView.setAdapter(detailsAdapter);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new PersonalInfo();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }


}