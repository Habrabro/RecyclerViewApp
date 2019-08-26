package com.example.recyclerviewapp;

import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder>
{
    private LayoutInflater inflater;
    private List<App> apps;

    DataAdapter(Context context, List<App> apps)
    {
        this.apps = apps;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position)
    {
        App app = apps.get(position);
        holder.ivIconView.setImageDrawable(app.getImage());
        holder.tvNameView.setText(app.getName());
        holder.tvPackageView.setText(app.getPack());
    }

    @Override
    public int getItemCount()
    {
        return apps.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        final TextView tvNameView, tvPackageView;
        final ImageView ivIconView;

        ViewHolder(View view)
        {
            super(view);

            ivIconView = view.findViewById(R.id.ivIcon);
            tvNameView = view.findViewById(R.id.tvName);
            tvPackageView = view.findViewById(R.id.tvPackage);
        }
    }
}
