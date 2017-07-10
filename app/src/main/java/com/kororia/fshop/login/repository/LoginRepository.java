package com.kororia.fshop.login.repository;

import android.app.Activity;

import com.kororia.fshop.model.User;

/**
 * Created by Asahi on 7/1/2017.
 */

public interface LoginRepository {

    void login(User user, Activity activity);

    void createAccount(User user);
}
