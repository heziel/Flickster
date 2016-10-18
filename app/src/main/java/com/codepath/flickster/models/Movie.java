package com.codepath.flickster.models;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


/**
 * Created by Hezi Eliyahu on 13/10/2016.
 */
public class Movie implements Parcelable {

    private String posterPath;
    private String backdropPath;
    private String originalTitle;
    private String overView;
    private double rating;
    private double popularity;
    private int voteCount;
    private int id;

    protected Movie(Parcel in) {
        posterPath = in.readString();
        backdropPath = in.readString();
        originalTitle = in.readString();
        overView = in.readString();
        rating = in.readDouble();
        popularity = in.readDouble();
        voteCount = in.readInt();
        id = in.readInt();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(posterPath);
        dest.writeString(backdropPath);
        dest.writeString(originalTitle);
        dest.writeString(overView);
        dest.writeDouble(rating);
        dest.writeDouble(popularity);
        dest.writeInt(voteCount);
        dest.writeInt(id);
    }

   /* Getters */

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s",posterPath);
    }

    public String getBackdropPath(){
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverView() {
        return overView;
    }

    public double getRating(){ return rating; }

    public double getPopularity(){ return popularity; }

    public int getVoteCount(){ return voteCount; }

    public int getId(){ return id; }

    public Movie ( JSONObject jsonObject) throws JSONException{
        this.posterPath = jsonObject.getString("poster_path");
        this.originalTitle = jsonObject.getString("original_title");
        this.overView = jsonObject.getString("overview");
        this.backdropPath = jsonObject.getString("backdrop_path");
        this.rating = jsonObject.getDouble("vote_average");
        this.popularity = jsonObject.getDouble("popularity");
        this.voteCount = jsonObject.getInt("vote_count");
        this.id =  jsonObject.getInt("id");
    }

    public static ArrayList<Movie> fromJSONArray(JSONArray array){

        ArrayList<Movie> results = new ArrayList<>();

        for ( int i = 0; i < array.length(); i++) {
            try {
               results.add( new Movie(array.getJSONObject(i)) );
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return results;
    }


}
