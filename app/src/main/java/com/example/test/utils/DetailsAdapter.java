package com.example.test.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.fragments.Details;
import com.example.test.models.BankInfoModel;
import com.example.test.models.PersonalInfoModel;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.ViewHolder> {

    Context context;
    ArrayList<PersonalInfoModel> personalInfoModelArrayList;
    ArrayList<BankInfoModel> bankInfoModelArrayList;


    public DetailsAdapter(Context context, ArrayList<PersonalInfoModel> personalInfoModelArrayList, ArrayList<BankInfoModel> bankInfoModelArrayList) {
        this.context = context;
        this.personalInfoModelArrayList = personalInfoModelArrayList;
        this.bankInfoModelArrayList = bankInfoModelArrayList;
    }

    public DetailsAdapter(Context context, ArrayList<PersonalInfoModel> personalInfoModelArrayList) {
        this.context = context;
        this.personalInfoModelArrayList = personalInfoModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_details_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        PersonalInfoModel model = personalInfoModelArrayList.get(position);
//        BankInfoModel model1 = bankInfoModelArrayList.get(position);


        holder.name.setText(model.getFirstName()+" "+model.getLastName());
        holder.gender.setText(model.getGender());
        holder.phoneNumber.setText(model.getPhoneNumber());
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDate;
        try {
            birthDate = dateFormat.parse(model.getDob());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(birthDate);
        int birthYear = cal.get(Calendar.YEAR);
        int age = currentYear - birthYear;
        holder.age.setText(String.valueOf(age));



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "Click on "+model.getFirstName()+" "+model.getLastName(), Toast.LENGTH_SHORT).show();
                Details details = new Details();
                Bundle bundle = new Bundle();
                bundle.putInt("ID", model.getId());
                Log.d("myTag", "sending id "+ model.getId());
                FragmentManager fragmentManager = ((AppCompatActivity) v.getContext()).getSupportFragmentManager();
                details.setArguments(bundle);
                fragmentManager.beginTransaction()
                        .replace(R.id.container, details)
                        .addToBackStack(null)
                        .commit();
            }
        });




    }


    @Override
    public int getItemCount() {
        return personalInfoModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CircularImageView circularImageView;
        private TextView name, phoneNumber, gender, age;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            circularImageView = itemView.findViewById(R.id.profile);
            name = itemView.findViewById(R.id.name);
            phoneNumber = itemView.findViewById(R.id.phone_number);
            gender = itemView.findViewById(R.id.gender);
            age = itemView.findViewById(R.id.age);


        }

    }

}

