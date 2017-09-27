package com.gloriousfury.africantime.adapter;

/**
 * Created by OLORIAKE KEHINDE on 11/16/2016.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crevation.nglocaltime.Time;
import com.crevation.nglocaltime.hausa.Hausa;
import com.crevation.nglocaltime.igbo.Igbo;
import com.crevation.nglocaltime.yoruba.Yoruba;
import com.gloriousfury.africantime.R;
import com.gloriousfury.africantime.model.TimeView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class TimeListAdapter extends RecyclerView.Adapter<TimeListAdapter.ViewHolder> {
    Context context;
    private ArrayList<TimeView> feed_list;
    int TYPE_HAUSA = 1;
    int TYPE_IGBO = 2;
    int TYPE_YORUBA = 3;

    String HAUSA = "Hausa";
    String IGBO = "Igbo";
    String YORUBA = "Yoruba";


    public TimeListAdapter(Context context, ArrayList<TimeView> feed_list) {
        this.context = context;
        this.feed_list = feed_list;


    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView album_title, artist, noOfSongs;
        ImageView song_background;
        RelativeLayout backgroundRelativeLayout;

        public ViewHolder(View view) {
            super(view);
            view.setClickable(true);
            view.setOnClickListener(this);
//            title = (TextView) view.findViewById(menu_item);

//            album_title = (TextView) view.findViewById(R.id.album_name);
//            artist = (TextView) view.findViewById(R.id.artist);
//            song_background = (ImageView) view.findViewById(R.id.song_background);
//
////            noOfSongs = (TextView) view.findViewById(R.id.no_of_songs);
//            backgroundRelativeLayout = (RelativeLayout) view.findViewById(R.id.rel_info_layout);


        }

        @Override
        public void onClick(View v) {

        }
    }

    //
    public class LangViewHolderA extends ViewHolder implements View.OnClickListener {

        TextView timeText, digitalTime, language;

        public LangViewHolderA(final View view) {
            super(view);
            view.setClickable(true);
            view.setOnClickListener(this);

            timeText = (TextView) view.findViewById(R.id.time_text);
            digitalTime = (TextView) view.findViewById(R.id.digital_time);
            language = (TextView) view.findViewById(R.id.language);
        }


        @Override
        public void onClick(View v) {


        }
    }


    public class LangViewHolderB extends ViewHolder implements View.OnClickListener {
        TextView timeText, digitalTime, language;

        public LangViewHolderB(final View view) {
            super(view);

            view.setClickable(true);
            view.setOnClickListener(this);
            timeText = (TextView) view.findViewById(R.id.time_text);
            digitalTime = (TextView) view.findViewById(R.id.digital_time);
            language = (TextView) view.findViewById(R.id.language);


        }


        @Override
        public void onClick(View v) {

        }
    }


    public class LangViewHolderC extends ViewHolder implements View.OnClickListener {
        TextView timeText, digitalTime, language;

        public LangViewHolderC(final View view) {
            super(view);

            view.setClickable(true);
            view.setOnClickListener(this);
            timeText = (TextView) view.findViewById(R.id.time_text);
            digitalTime = (TextView) view.findViewById(R.id.digital_time);
            language = (TextView) view.findViewById(R.id.language);


        }


        @Override
        public void onClick(View v) {

        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = null;


        switch (viewType) {

            case 1:

                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.local_time_view2, parent, false);

                return new LangViewHolderA(v);


            case 2:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.local_time_view, parent, false);

                return new LangViewHolderB(v);

            case 3:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.local_time_view3, parent, false);

                return new LangViewHolderC(v);

            default:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.local_time_view, parent, false);

                return new ViewHolder(v);


        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {


        if (holder.getItemViewType() == TYPE_HAUSA) {
            LangViewHolderA Holder = (LangViewHolderA) holder;

            Time yoruba = new Yoruba();

            //you can as well use other languages available
            Time igbo = new Igbo();
            Time hausa = new Hausa();

            String yorubaTime = yoruba.getTime("4:00");
            String currentTime = yoruba.getCurrentTime();
            String calendarTime = yoruba.getTime(Calendar.getInstance());
            String dateTime = hausa.getTime(new Date());


            Holder.timeText.setText(dateTime);
            Holder.language.setText("-" + feed_list.get(position).getLanguage());


        } else if (holder.getItemViewType() == TYPE_YORUBA) {
            LangViewHolderC Holder = (LangViewHolderC) holder;
            Time yoruba = new Yoruba();

            //you can as well use other languages available
            Time igbo = new Igbo();
            Time hausa = new Hausa();

            String yorubaTime = yoruba.getTime("4:00");
            String currentTime = yoruba.getCurrentTime();
            String calendarTime = yoruba.getTime(Calendar.getInstance());
            String dateTime = yoruba.getTime(new Date());

            Holder.language.setText("-" + feed_list.get(position).getLanguage());

            Holder.timeText.setText(dateTime);


        } else if (holder.getItemViewType() == TYPE_IGBO) {
            LangViewHolderB Holder = (LangViewHolderB) holder;
            Time yoruba = new Yoruba();

            //you can as well use other languages available
            Time igbo = new Igbo();
            Time hausa = new Hausa();

            String yorubaTime = yoruba.getTime("4:00");
            String currentTime = yoruba.getCurrentTime();
            String calendarTime = yoruba.getTime(Calendar.getInstance());
            String dateTime = igbo.getTime(new Date());

            Holder.language.setText("-" + feed_list.get(position).getLanguage());

            Holder.timeText.setText(dateTime);


        }
    }

//
//    public void setAudioListData(ArrayList<Albums> albumArraylist) {
//        if (albumArraylist != null) {
////            feed_list.clear();
//            this.feed_list = albumArraylist;
//            notifyDataSetChanged();
//
//        }
//
//    }


    @Override
    public int getItemViewType(int position) {
        int viewType = 0;
        if (feed_list.get(position).getLanguage().contentEquals(HAUSA)) {
            viewType = TYPE_HAUSA;

        } else if (feed_list.get(position).getLanguage().contentEquals(YORUBA)) {
            viewType = TYPE_YORUBA;
        } else if (feed_list.get(position).getLanguage().contentEquals(IGBO)) {
            viewType = TYPE_IGBO;
        }

        return viewType;
    }

    @Override
    public int getItemCount() {

        if (feed_list != null) {
            return feed_list.size();
        } else {

            return 0;
        }

    }


}


