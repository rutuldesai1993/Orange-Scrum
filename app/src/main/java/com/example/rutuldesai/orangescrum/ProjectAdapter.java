package com.example.rutuldesai.orangescrum;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {

    private Context context;
    private TextView project;
    private List<ProjectModel> list;

    public ProjectAdapter(Context context, List<ProjectModel> list){
        this.context = context;
        this.list=list;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v =
                LayoutInflater.from(context).inflate(R.layout.single_item, viewGroup,false);
        project=(TextView) v.findViewById(R.id.ProjectNameTv);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ProjectModel project = list.get(i);
       viewHolder.textProjectName.setText(project.getPROJECT_NAME());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context , ProjectDetailActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textProjectName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textProjectName=itemView.findViewById(R.id.ProjectNameTv);
        }
    }
}
