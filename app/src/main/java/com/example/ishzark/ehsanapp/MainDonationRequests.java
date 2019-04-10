package com.example.ishzark.ehsanapp;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.Query;

import java.util.List;
public class MainDonationRequests {
    private Context Mcontext;
    private requestAdapter RequestAdapter;
    public void setConfig(Context context,RecyclerView recyclerView,  List<DonateItems> items, List<String> keys)
    {
        Mcontext=context;

        RequestAdapter= new requestAdapter(items,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(RequestAdapter);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView ItemType;
        private TextView DonorName;
        private TextView phone;
        private String key;
        private TextView itemstatus;
        private TextView Quantity;
        private TextView itemdetails;


        public ViewHolder(View itemView) {
            super(itemView);
            ItemType = (TextView) itemView.findViewById(R.id.ItemType);
            DonorName = (TextView) itemView.findViewById(R.id.DonorName);
            phone = (TextView) itemView.findViewById(R.id.Phone);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Mcontext,donationrequestDetails.class);
                    intent.putExtra("key",key);
                    intent.putExtra("item_type",ItemType.getText().toString());
                    intent.putExtra("donor_name",DonorName.getText().toString());
                    intent.putExtra("phone",phone.getText().toString());
                 /*   intent.putExtra("quantity",Quantity.getText());
                    intent.putExtra("item_status",itemstatus.getText().toString());
                    intent.putExtra("itemDetails",itemdetails.getText().toString());*/



                    Mcontext.startActivity(intent);
                }
            });
        }

        public void bind(DonateItems items, String key) {
            ItemType.setText(items.getItem_type());
            DonorName.setText(items.getDonor_name());
            phone.setText(items.getPhone());
         /*   itemstatus.setText(items.getItem_status());
            itemdetails.setText(items.getItemDetails());
            Quantity.setText(items.getQuantity());*/


            this.key = key;
        }
    }
    //we start
    class requestAdapter extends RecyclerView.Adapter<MainDonationRequests.ViewHolder> {
        private List<DonateItems> MList;
        private List<String> MKeys;

        public requestAdapter(List<DonateItems> MList, List<String> MKeys) {
            this.MList = MList;
            this.MKeys = MKeys;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //done
            View view = LayoutInflater.from(Mcontext).inflate(R.layout.donationrequests_list, parent, false);
            //ViewHolder eventsview= new ViewHolder(parent);
            return new ViewHolder((ViewGroup) view);
        }

        @Override
        public void onBindViewHolder(@NonNull MainDonationRequests.ViewHolder holder, int position) {
            holder.bind(MList.get(position), MKeys.get(position));



        }

        @Override
        public int getItemCount() {//done
            //return 0;
            return MList.size();
        }
    }


}
