package com.codepath.flickster.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.codepath.flickster.R;
import com.codepath.flickster.models.Movie;
import com.squareup.picasso.Picasso;
import java.util.List;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;


/**
 * Created by Hezi Eliyahu on 13/10/2016.
 */
public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    public MovieArrayAdapter(Context context, List<Movie> movies) {

        super(context, android.R.layout.simple_list_item_1, movies);
    }

    /*  Heterogenous ListView */
    @Override
    public int getItemViewType(int position) {
        double rating = getItem(position).getRating();
        return rating > 5 ? 1 :0 ;
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount();
    }

    /*  viewHolder */
    private static class ViewHolder {
        ImageView ivImage;
        TextView tvTitle;
        TextView tvOverview;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // get the item data for this position
        Movie movie = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder = null; // view lookup cache stored in tag

        int type = getItemViewType(position);

        // check if the existing view being used
        if (convertView == null) {

            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();

            // Get the data item type for this position
            convertView = getInflatedLayoutFromType(type);

            //LayoutInflater inflater = LayoutInflater.from(getContext());
            //convertView = inflater.inflate(R.layout.item_movie, parent, false);

            viewHolder.ivImage = (ImageView) convertView.findViewById(R.id.ivMovieImage);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);

            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);

        } else {

            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // populate data
        viewHolder.tvTitle.setText(movie.getOriginalTitle());
        viewHolder.tvOverview.setText(movie.getOverView());

        // get orientation
        int orientation = getContext().getResources().getConfiguration().orientation;

        if ( orientation == 1 && type == 0 ) {
            Picasso.with(getContext()).load(movie.getPosterPath()).error(R.drawable.large_movie_poster).transform(new RoundedCornersTransformation(20, 20)).into(viewHolder.ivImage);
        }
        else if( orientation == 1 && type == 1 ) {

            viewHolder.tvTitle.setText("");
            viewHolder.tvOverview.setText("");
            Picasso.with(getContext()).load(movie.getBackdropPath()).error(R.drawable.small_movie_poster).
                    resize(500,500).transform(new RoundedCornersTransformation(20, 20)).into(viewHolder.ivImage);

        }
        else{
            Picasso.with(getContext()).load(movie.getBackdropPath()).error(R.drawable.small_movie_poster).into(viewHolder.ivImage);
        }
        // return view
        return convertView;
    }

    private View getInflatedLayoutFromType(int type) {

        LayoutInflater inflater = LayoutInflater.from(getContext());

        if (type == 0) {
            return LayoutInflater.from(getContext()).inflate(R.layout.item_movie, null);
        } else if (type == 1) {
            return LayoutInflater.from(getContext()).inflate(R.layout.item_movie, null);
        } else {
            return null;
        }

    }

}