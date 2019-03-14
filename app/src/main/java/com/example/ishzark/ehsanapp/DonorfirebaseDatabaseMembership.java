package com.example.ishzark.ehsanapp;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DonorfirebaseDatabaseMembership extends RecyclerView.Adapter<DonorfirebaseDatabaseMembership.ExampleViewHolder> {
    private ArrayList<Memberships> mExampleList;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder{
        private ProgressBar progressBar;
        private TextView membershiptype;
        private TextView features;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            progressBar=itemView.findViewById(R.id.level1);
            membershiptype=itemView.findViewById(R.id.levelview1);
            features=itemView.findViewById(R.id.benefits0);

        }
    }
    public DonorfirebaseDatabaseMembership(ArrayList<Memberships> examplelist){
        mExampleList=examplelist;

    }
    private List<Memberships>membershipList = new ArrayList<>();
    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder exampleViewHolder, int i) {
        Memberships exampleitem=mExampleList.get(i);
        exampleViewHolder.progressBar.setVisibility(View.VISIBLE);
        exampleViewHolder.features.setText(exampleitem.getFeature());
        exampleViewHolder.membershiptype.setText(exampleitem.getType());

    }
    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.membershiplistadapter,viewGroup,false);
        ExampleViewHolder evh=new ExampleViewHolder(v);

        return evh;
    }
    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

}
