package edu.aku.dmu.uen_ec.adapter;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import edu.aku.dmu.uen_ec.R;
import edu.aku.dmu.uen_ec.databinding.UploadListAdapterBinding;
import edu.aku.dmu.uen_ec.model.SyncModel;

public class Upload_list_adapter extends RecyclerView.Adapter<Upload_list_adapter.UploadListViewHolder> {
    List<SyncModel> uploadlist;
    UploadListViewHolder holder;

    public Upload_list_adapter(List<SyncModel> uploadlist) {
        this.uploadlist = uploadlist;
    }

    private int checkStatus(int i) {
        switch (i) {
            case 1:
                return Color.RED;
            case 2:
                return Color.YELLOW;
            case 3:
                return Color.GREEN;
            case 4:
                return Color.GRAY;
            default:
                return Color.BLACK;
        }
    }

    public void updatesyncList(List<SyncModel> uploadlist) {
        this.uploadlist = uploadlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UploadListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.upload_list_adapter, parent, false);
        return new UploadListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UploadListViewHolder holder, int position) {
        this.holder = holder;
        this.holder.bindUser(this.uploadlist.get(position));
    }

    @Override
    public int getItemCount() {
        return uploadlist.size() > 0 ? uploadlist.size() : 0;
    }

    public class UploadListViewHolder extends RecyclerView.ViewHolder {
        UploadListAdapterBinding binding;

        public UploadListViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public void bindUser(SyncModel model) {
            binding.ustatusColor.setBackgroundColor(checkStatus(model.getstatusID()));
            binding.utvTableName.setText(model.gettableName());
            binding.utvStatus.setText(model.getstatus());
            binding.utvMsg.setText(model.getmessage());
            if (model.getstatusID() == 1 || model.getstatusID() == 3 || model.getstatusID() == 4) {
                binding.upb.setVisibility(View.GONE);
            } else {
                binding.upb.setVisibility(View.VISIBLE);
            }
        }
    }
}
