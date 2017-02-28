package com.example.harsh.sherlocked.extra;

/**
 * Created by Harsh on 25-12-2016.
 */

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class SeasonResponse {

    @SerializedName("seasons")
    private List<Season> results;

    public List<Season> getResults() {
        return results;
    }

    public void setResults(List<Season> results) {
        this.results = results;
    }

    public List<Season> modify( List<Season> result){

        result.remove(0);
        return result;

    }
}
