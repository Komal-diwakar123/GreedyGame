package com.example.greedygame.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greedygame.R;
import com.example.greedygame.adapter.ReddItAdapter;
import com.example.greedygame.model.ResponseModel;
import com.example.greedygame.viewmodel.ReddItViewModel;

public class ReddItActivity extends AppCompatActivity{

    ProgressDialog progressBar;
    ReddItViewModel reddItViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showProgress();
        reddItViewModel = new ViewModelProvider(this).get(ReddItViewModel.class);
        initializeViewModel(reddItViewModel);
    }


    private void initializeViewModel(ReddItViewModel model) {

        model.init(this);
        model.getReddItResponse();
        model.getRedItLiveData().observe(this, reponseModel -> {
            if(reponseModel != null) {
                updateAdapter(reponseModel);
                progressBar.dismiss();
            }
        });
    }

    private void updateAdapter(ResponseModel responseModel) {
        RecyclerView recyclerView = findViewById(R.id.image_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ReddItAdapter adapter = new ReddItAdapter(responseModel.getData().getChildrenModelList(),this);
        recyclerView.setAdapter(adapter);
    }


    private void showProgress(){
        progressBar = ProgressDialog.show(this, "Breathe in Breathe out", "We are fetching your data...", true);
    }
}