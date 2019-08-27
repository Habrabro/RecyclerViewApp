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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsDialogFragment extends DialogFragment
{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        Bundle bundle = getArguments();
        String packageName = bundle.getString("Package");
        PackageManager packageManager = getActivity().getPackageManager();
        PackageInfo packageInfo = null;
        try { packageInfo = packageManager.getPackageInfo(packageName, 0); }
        catch (PackageManager.NameNotFoundException ex)
        {
            Toast.makeText(getContext(), "App not found", Toast.LENGTH_LONG).show();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_details, null);

        ImageView ivIconDialogView = view.findViewById(R.id.ivIconDialog);
        TextView tvNameDialogView = view.findViewById(R.id.tvNameDialog);
        TextView tvPackageView = view.findViewById(R.id.tvPackageDialog);
        TextView tvVersionDialogView = view.findViewById(R.id.tvVersionDialog);
        TextView tvVersionCodeDialogView = view.findViewById(R.id.tvCodeVersionDialog);

        String name = packageInfo.applicationInfo.loadLabel(packageManager).toString();
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
