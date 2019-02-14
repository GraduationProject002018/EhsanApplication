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

public class RecyclerViewActivity {
    private Context Vcontext;
    private eventAdapter EeventAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<Events> event, List<String> keys)
    {
        Vcontext=context;
        EeventAdapter= new eventAdapter(event,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(EeventAdapter);

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView ItemTitle;
        private TextView ItemDate;
        private TextView ItemDescription;
        private String key;

        public ViewHolder(View itemView) {
            super(itemView);
            ItemTitle = (TextView) itemView.findViewById(R.id.titleId);
            ItemDate = (TextView) itemView.findViewById(R.id.dateId);
            ItemDescription = (TextView) itemView.findViewById(R.id.descId);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Vcontext,eventDetails.class);
                    intent.putExtra("key",key);
                    intent.putExtra("title",ItemTitle.getText().toString());
                    intent.putExtra("date",ItemDate.getText().toString());
                    intent.putExtra("Desc",ItemDescription.getText().toString());

                    Vcontext.startActivity(intent);
                }
            });
        }

        public void bind(Events event, String key) {
            ItemTitle.setText(event.getTitle());
            ItemDate.setText(event.getDate());
            ItemDescription.setText(event.getDescription());
            this.key = key;
        }
    }
//we start
    class eventAdapter extends RecyclerView.Adapter<ViewHolder> {
        private List<Events> VListevents;
        private List<String> VKeys;

        public eventAdapter(List<Events> VListevents, List<String> VKeys) {
            this.VListevents = VListevents;
            this.VKeys = VKeys;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //ViewHolder eventsview= new ViewHolder(parent);'
            View view =(LayoutInflater.from(Vcontext).inflate(R.layout.events_list_item, parent, false));
            return new ViewHolder((ViewGroup) view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.bind(VListevents.get(position), VKeys.get(position));
        }

        @Override
        public int getItemCount() {
            //return 0; nothing will be shown aseel
            return VListevents.size(); //to show in the screen
        }
    }

}

