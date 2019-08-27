package com.example.recyclerviewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import androidx.core.content.pm.PackageInfoCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements DataAdapter.DataAdapterListener
{
    PackageManager packageManager;
    List<PackageInfo> installedApps;
    List<App> apps = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        packageManager = getPackageManager();
        installedApps = packageManager.getInstalledPackages(0);

        setInitialData();
        RecyclerView recyclerView = findViewById(R.id.rvList);
        DataAdapter adapter = new DataAdapter(this, apps);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnViewHolderClick(DataAdapter.ViewHolder viewHolder, View view)
    {
        Bundle bundle = new Bundle();
        bundle.putInt("AdapterPosition", viewHolder.getAdapterPosition());

        FragmentManager fragmentManager = getSupportFragmentManager();
        DetailsDialogFragment dialog = new DetailsDialogFragment();
        dialog.setArguments(bundle);
        dialog.show(fragmentManager, "Dialog");
    }

    private void setInitialData()
    {
        for (PackageInfo packageInfo : installedApps)
        {
            apps.add(new App(
                    packageInfo.applicationInfo.loadLabel(packageManager).toString(),
                    packageInfo.packageName,
                    packageInfo.versionName,
                    Long.toString(PackageInfoCompat.getLongVersionCode(packageInfo)),
                    packageInfo.applicationInfo.loadIcon(packageManager)));
        }
    }
}
