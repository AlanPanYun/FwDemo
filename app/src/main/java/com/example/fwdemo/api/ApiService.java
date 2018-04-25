package com.example.fwdemo.api;

import com.example.fwdemo.bean.BookDetail;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by qk14 on 2018/3/28.
 */

public interface ApiService {

    @GET("/book/{bookId}")
    Observable<BookDetail> getBookDetail(@Path("bookId") String bookId);
}
