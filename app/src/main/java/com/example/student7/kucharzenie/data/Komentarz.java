package com.example.student7.kucharzenie.data;

        import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Komentarz {
    public int id;
    public int recipeId;
    public int ownerId;
    public String text;
    public String created;
}