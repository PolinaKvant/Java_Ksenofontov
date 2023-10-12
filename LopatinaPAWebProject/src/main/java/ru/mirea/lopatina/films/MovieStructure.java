package ru.mirea.lopatina.films;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieStructure {
    @JsonProperty("title")
    private String title;

    public String getTitle(){
        return title;
    }

    @JsonProperty("year")
    private int year;

    public int getYear(){
        return year;
    }

    @JsonProperty("cast")
    private List<String> cast;
    public List<String> getCast(){
        return cast;
    }
    @JsonProperty("genres")
    private List<String> genres;

    @JsonProperty("href")
    private String href;

    @JsonProperty("extract")
    private String extract;

    @JsonProperty("thumbnail")
    private String thumbnail;

    @JsonProperty("thumbnail_width")
    private int thumbnailWidth;

    @JsonProperty("thumbnail_height")
    private int thumbnailHeight;


    @Override
    public String toString() {
        return "MovieDTO{" +
                "title='" + title + '\'' +
                ", year=" + year +
                ", cast=" + cast +
                ", genres=" + genres +
                ", href='" + href + '\'' +
                ", extract='" + extract + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", thumbnailWidth=" + thumbnailWidth +
                ", thumbnailHeight=" + thumbnailHeight +
                '}';
    }
}
