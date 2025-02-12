package com.pragmatic.sauce.pages;

public class ProductDetail {

    private  String name;
    private String description;
    private String priceWithCurrency;
    private String imageAlt;
    private String imageSrc;

    public String getName() {
        return name;
    }

    public ProductDetail setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductDetail setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPriceWithCurrency() {
        return priceWithCurrency;
    }

    public ProductDetail setPriceWithCurrency(String priceWithCurrency) {
        this.priceWithCurrency = priceWithCurrency;
        return this;
    }


    public String getImageAlt() {
        return imageAlt;
    }

    public ProductDetail setImageAlt(String imageAlt) {
        this.imageAlt = imageAlt;
        return this;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public ProductDetail setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
        return this;
    }
}
