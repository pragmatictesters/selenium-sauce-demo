package com.pragmatic.sauce.pages;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ProductDetail {

    private  String name;
    private String description;
    private String priceWithCurrency;
    private BigDecimal price;
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

        BigDecimal price = new BigDecimal(priceWithCurrency.replace("$", ""))
                .setScale(2, RoundingMode.HALF_UP);
        setPrice(price);
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

    public BigDecimal getPrice() {
        return price;
    }

    private ProductDetail setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
