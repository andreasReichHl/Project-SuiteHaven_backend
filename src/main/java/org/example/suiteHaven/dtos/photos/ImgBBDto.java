package org.example.suiteHaven.dtos.photos;

public record ImgBBDto(DataInfo data) {


    public record DataInfo(String url, ThumbInfo thumb, ImageInfo image) {
    }

    public record ThumbInfo(String url) {
    }

    public record ImageInfo(String filename) {
    }


}