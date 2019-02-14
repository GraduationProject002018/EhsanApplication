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
public class RecyclerViewMembership {
    private Context Mcontext;
    private MembershipAdapter membershipAdapter;
    public void setConfig(Context context,RecyclerView recyclerView,  List<Memberships> membership, List<String> keys)
    {
        Mcontext=context;

        membershipAdapter= new MembershipAdapter(membership,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(membershipAdapter);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView ItemType;
        private TextView ItemFeature;
        private String key;

        public ViewHolder(View itemView) {
            super(itemView);
            ItemType = (TextView) itemView.findViewById(R.id.TypeMembership);
            ItemFeature = (TextView) itemView.findViewById(R.id.FeatureMembership);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Mcontext,membershipDetails.class);
                    intent.putExtra("key",key);
                    intent.putExtra("type",ItemType.getText().toString());
                    intent.putExtra("feature",ItemFeature.getText().toString());


                    Mcontext.startActivity(intent);
                }
            });
        }

        public void bind(Memberships membership, String key) {
            ItemType.setText(membership.getType());
            ItemFeature.setText(membership.getFeature());
            this.key = key;
        }
    }
    //we start
    class MembershipAdapter extends RecyclerView.Adapter<RecyclerViewMembership.ViewHolder> {
        private List<Memberships> MListMembership;
        private List<String> MKeys;

        public MembershipAdapter(List<Memberships> MListMembership, List<String> MKeys) {
            this.MListMembership = MListMembership;
            this.MKeys = MKeys;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //done
            View view = LayoutInflater.from(Mcontext).inflate(R.layout.membership_list, parent, false);

            return new ViewHolder((ViewGroup) view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerViewMembership.ViewHolder holder, int position) {
            holder.bind(MListMembership.get(position), MKeys.get(position));



        }

        @Override
        public int getItemCount() {//done
            //return 0;
            return MListMembership.size();
        }
    }


}
