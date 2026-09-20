package com.novaos.privacycenter.adapter;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.novaos.privacycenter.R;
import java.util.List;

public class AppPermissionAdapter extends RecyclerView.Adapter<AppPermissionAdapter.ViewHolder> {

    private final List<ApplicationInfo> mApps;
    private final PackageManager mPm;

    public AppPermissionAdapter(Context context, List<ApplicationInfo> apps) {
        mApps = apps;
        mPm = context.getPackageManager();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_app_permission, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ApplicationInfo app = mApps.get(position);
        holder.name.setText(app.loadLabel(mPm));
        holder.icon.setImageDrawable(app.loadIcon(mPm));
        holder.summary.setText(app.packageName);
        
        // Mock network control
        holder.networkToggle.setChecked(true);
        holder.networkToggle.setOnCheckedChangeListener((v, isChecked) -> {
            // Logic to restrict network access would go here
        });
    }

    @Override
    public int getItemCount() {
        return mApps.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView name;
        TextView summary;
        Switch networkToggle;

        ViewHolder(View v) {
            super(v);
            icon = v.findViewById(R.id.app_icon);
            name = v.findViewById(R.id.app_name);
            summary = v.findViewById(R.id.permission_summary);
            networkToggle = v.findViewById(R.id.network_toggle);
        }
    }
}
