package com.cutebird;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cutebird.models.LetterModel;

import java.util.List;

/**
 * Created by islam on 3/29/16.
 */
public class LetterAdapter extends RecyclerView.Adapter<LetterAdapter.MyViewHolder> {
    private List<LetterModel> letterModels;
    private Context context;

    public LetterAdapter(Context context, List<LetterModel> letterModels) {
        this.context = context;
        this.letterModels = letterModels;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.media_select_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        LetterModel letterModel = letterModels.get(position);
        holder.tvTitle.setText(letterModel.getDescription());
    }

    @Override
    public int getItemCount() {
        return letterModels.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvArtist, tvAlbum, tvTitle;
        public ImageView ivOption, ivIcon;

        public MyViewHolder(View view) {
            super(view);

            tvArtist = (TextView) view.findViewById(R.id.row_artist);
            tvAlbum = (TextView) view.findViewById(R.id.row_album);
            tvTitle = (TextView) view.findViewById(R.id.row_title);

            ivOption = (ImageView) itemView.findViewById(R.id.row_options_button);
            ivIcon = (ImageView) itemView.findViewById(R.id.row_icon);
        }


    }
}


