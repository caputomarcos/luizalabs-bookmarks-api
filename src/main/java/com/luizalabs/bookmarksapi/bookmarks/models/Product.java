package com.luizalabs.bookmarksapi.bookmarks.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Product {
    private Long id;

    @JsonProperty private double price;

    @JsonProperty private String image;

    @JsonProperty private String brand;

    @JsonProperty private String title;

    @JsonProperty private double reviewScore;
}
