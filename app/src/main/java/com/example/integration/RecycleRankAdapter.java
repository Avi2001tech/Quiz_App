package com.example.integration;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

//import static com.example.integration.Leaderboard.arrRank;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecycleRankAdapter extends RecyclerView.Adapter<RecycleRankAdapter.ViewHolder> {


    Leaderboard context;
//    ArrayList<RankModel> arrRank;
    ArrayList<RankerClass> arrRank;

//    List<RankerClass> listOfRankers;
    RecycleRankAdapter(Leaderboard context, ArrayList<RankerClass> arrRank){
        this.context = context;
        this.arrRank=arrRank;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rank_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.userImg.setImageResource(arrRank.get(position).img);
        Log.d("position is", "onBindViewHolder: "+position);
        holder.rankName.setText(arrRank.get(position).username);
        holder.rankScore.setText(arrRank.get(position).score);
//        holder.userRank.setText(arrRank.get(position).rank);

    }

    @Override
    public int getItemCount() {
        Log.d("ok", "getItemCount: "+arrRank.size());
        return arrRank.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView rankName,rankScore,userRank;
//        ImageView userImg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d("running", "ViewHolder: ");

            rankName = itemView.findViewById(R.id.rankName);
            rankScore=itemView.findViewById(R.id.rankScore);
//            userRank= itemView.findViewById(R.id.userRank);
//            userImg = itemView.findViewById(R.id.userImg);

        }
    }
}
