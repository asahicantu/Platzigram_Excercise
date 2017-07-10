package com.kororia.fshop.post.repository;

/**
 * Created by Asahi on 7/8/2017.
 */

public interface PostRepository {
    void uploadPhoto(byte[] imageByte, String photoName);

    void getPhoto(String photoName);
}
