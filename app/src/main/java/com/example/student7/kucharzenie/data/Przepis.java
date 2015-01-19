package com.example.student7.kucharzenie.data;

        import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

        import java.io.Serializable;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Przepis implements Serializable {
    public int id;
    public int ownerId;
    public String title;
    public String introduction;
    public String ingredients;
    public String steps;
    public String created;
    public String preparationMinutes;
    public String cookingMinutes;
    public String servings;
}