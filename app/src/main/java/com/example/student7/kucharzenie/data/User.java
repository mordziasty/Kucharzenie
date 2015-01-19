package com.example.student7.kucharzenie.data;

        import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
        import com.fasterxml.jackson.annotation.JsonProperty;

        import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true) //prevents crashes after changing json structure
public class User implements Serializable {
    public int id;

    @JsonProperty("session_id")
    public String sessionId;
}