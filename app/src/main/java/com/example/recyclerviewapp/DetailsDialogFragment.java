package com.example.recyclerviewapp;

import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Dialog;

import androidx.core.content.pm.PackageInfoCompat;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;

import android.view.LayoutInflater;
import android.view.View;
import android.content.pm.PackageManager;
import java.util.List;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsDialogFragment extends DialogFragment
{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        PackageManager packageManager = getActivity().getPackageManager();
        List<PackageInfo> installedApps = packageManager.getInstalledPackages(0);
        int adapterPosition = getArguments().getInt("AdapterPosition");
        PackageInfo packageInfo = installedApps.get(adapterPosition);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_details, null);

        ImageView ivIconDialogView = view.findViewById(R.id.ivIconDialog);
        TextView tvNameDialogView = view.findViewById(R.id.tvNameDialog);
        TextView tvPackageView = view.findViewById(R.id.tvPackageDialog);
        TextView tvVersionDialogView = view.findViewById(R.id.tvVersionDialog);
        TextView tvVersionCodeDialogView = view.findViewById(R.id.tvCodeVersionDialog);

        String name = packageInfo.applicationInfo.loadLabel(packageManager).toString();
        String packageName = packageInfo.packageName;
        String version = getResources().getString(R.string.text_detailsDialogVersionPrefix) + " " + packageInfo.versionName;
        String codeVersion = Long.toString((int)(PackageInfoCompat.getLongVersionCode(packageInfo) & 0x00000000ffffffff));
        Drawable icon = packageInfo.applicationInfo.loadIcon(packageManager);

        tvNameDialogView.setText(name);
        tvPackageView.setText(packageName);
        tvVersionDialogView.setText(version);
        tvVersionCodeDialogView.setText(codeVersion);
        ivIconDialogView.setImageDrawable(icon);

        builder.setView(view)
                .setNegativeButton(getResources().getText(R.string.text_detailsDialogNegBtn), null);
        return builder.create();
    }
}
