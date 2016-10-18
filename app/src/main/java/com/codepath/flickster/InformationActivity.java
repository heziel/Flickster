package com.codepath.flickster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.codepath.flickster.models.Movie;
import com.squareup.picasso.Picasso;

public class InformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Movie movie = getIntent().getParcelableExtra("movie");

        TextView tvTitle = (TextView) findViewById(R.id.tvDetailTitle);
        tvTitle.setText(movie.getOriginalTitle());

        TextView tvDetailPopularity = (TextView) findViewById(R.id.tvDetailpopularity);
        tvDetailPopularity.setText("Popularity Score:" + (int) movie.getPopularity()/40 + "%");

        TextView tvDetailVoteCount = (TextView) findViewById(R.id.tvDetailVoteCount);
        tvDetailVoteCount.setText("Vote Count:" + movie.getVoteCount());

        TextView tvOverview = (TextView) findViewById(R.id.tvDetailOverview);
        tvOverview.setText(movie.getOverView());

        //Uri myUri = Uri.parse(movie.getBackdropPath());
        ImageView ivPoster = (ImageView) findViewById(R.id.ivDetailImage);
        Picasso.with(getBaseContext()).load(movie.getPosterPath()).into(ivPoster);

        RatingBar rtVote = (RatingBar) findViewById(R.id.rtDetail);
        rtVote.setRating((float)  movie.getRating()/2);

        // for displaying trailer
        int id = movie.getId();

    }

}
