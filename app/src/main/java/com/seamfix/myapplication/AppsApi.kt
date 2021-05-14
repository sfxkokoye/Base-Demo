package com.seamfix.myapplication

import io.reactivex.Observable
import retrofit2.http.GET

interface AppsApi {
    @GET ("apps")
    fun getApps(): Observable<ArrayList<App>>

    @GET ("/base-test/resources/test/get-content/demoapp.json")
    fun getAllParams(): Observable<Data>
}