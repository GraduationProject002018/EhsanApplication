package com.example.ishzark.ehsanapp;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
public class MainDonationRequests2 {
    private Context Mcontext;
    private requestAdapter RequestAdapter;
    public void setConfig(Context context,RecyclerView recyclerView,  List<PrepaidInvoice> pre, List<String> keys)
    {
        Mcontext=context;

        RequestAdapter= new requestAdapter(pre,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(RequestAdapter);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView donationAmount;
        private TextView DonorName;
        private String key;

        public ViewHolder(View itemView) {
            super(itemView);
            donationAmount = (TextView) itemView.findViewById(R.id.ItemType);
            DonorName = (TextView) itemView.findViewById(R.id.DonorName);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Mcontext,donationrequestDetails2.class);
                    intent.putExtra("key",key);
                    intent.putExtra("donationAmount",donationAmount.getText().toString());
                    intent.putExtra("donor",DonorName.getText().toString());



                    Mcontext.startActivity(intent);
                }
            });
        }

        public void bind(PrepaidInvoice pre, String key) {
            donationAmount.setText(pre.getDonationAmount());
            DonorName.setText(pre.getDonorName());
            this.key = key;
        }
    }
    //we start
    class requestAdapter extends RecyclerView.Adapter<ViewHolder> {
        private List<PrepaidInvoice> MList;
        private List<String> MKeys;

        public requestAdapter(List<PrepaidInvoice> MList, List<String> MKeys) {
            this.MList = MList;
            this.MKeys = MKeys;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //done
            View view = LayoutInflater.from(Mcontext).inflate(R.layout.donationrequests_list2, parent, false);
            //ViewHolder eventsview= new ViewHolder(parent);
            return new ViewHolder((ViewGroup) view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.bind(MList.get(position), MKeys.get(position));



        }

        @Override
        public int getItemCount() {//done
            //return 0;
            return MList.size();
        }
    }


}
