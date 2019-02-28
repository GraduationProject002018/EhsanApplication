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
public class RecyclerAdmin{
    private Context Acontext;
    private AdminAdapter adminAdapter;
    public void setConfig(Context context,RecyclerView recyclerView,  List<Admins> admin, List<String> keys)
    {
        Acontext=context;

        adminAdapter= new AdminAdapter(admin,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adminAdapter);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView ItemName;
        private TextView ItemNumber;
        private String key;

        public ViewHolder(View itemView) {
            super(itemView);
            ItemName = (TextView) itemView.findViewById(R.id.AdminName);
            ItemNumber = (TextView) itemView.findViewById(R.id.AdminNumber);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Acontext,adminDetails.class);
                    intent.putExtra("key",key);
                    intent.putExtra("name",ItemName.getText().toString());
                    intent.putExtra("phone",ItemNumber.getText().toString());


                    Acontext.startActivity(intent);
                }
            });
        }

        public void bind(Admins admin, String key) {
            ItemName.setText(admin.getName());
            ItemNumber.setText(admin.getPhone());
            this.key = key;
        }
    }
    //we start
    class AdminAdapter extends RecyclerView.Adapter<ViewHolder> {
        private List<Admins> AListMembership;
        private List<String> AKeys;

        public AdminAdapter(List<Admins> AListMembership, List<String> AKeys) {
            this.AListMembership = AListMembership;
            this.AKeys = AKeys;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //done
            View view = LayoutInflater.from(Acontext).inflate(R.layout.admin_list, parent, false);

            return new ViewHolder((ViewGroup) view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerAdmin.ViewHolder holder, int position) {
            holder.bind(AListMembership.get(position), AKeys.get(position));



        }

        @Override
        public int getItemCount() {//done
            //return 0;
            return AListMembership.size();
        }
    }


}
