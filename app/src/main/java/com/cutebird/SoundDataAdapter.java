package com.cutebird;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by islam on 3/29/16.
 */
public class SoundDataAdapter extends RecyclerView.Adapter<SoundDataAdapter.MyViewHolder> {
    private List<SoundDataModel> soundDataModels;
    private Context context;

    public SoundDataAdapter(List<SoundDataModel> soundDataModels, Context context) {
        this.soundDataModels = soundDataModels;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.media_select_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SoundDataModel dataModel = soundDataModels.get(position);
    /*    holder.tvDate.setText(forecastday.getDate());
        holder.tvDegreeHigh.setText(forecastday.getDegreeHigh());
        holder.tvCondition.setText(forecastday.getCondition());
        //load image from url and set it in image view
        Util.getInstance().loadImage(context, holder.ivForecast, forecastday.getImageLink(), R.drawable.error_image);
*/
    }

    @Override
    public int getItemCount() {
        return 0;
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


