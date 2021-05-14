package com.seamfix.myapplication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class AppViewmodel: ViewModel() {
    private var _appList = MutableLiveData<ArrayList<App>>()
    val appList:LiveData<ArrayList<App>> = _appList

    private var _param = MutableLiveData<Data>()
    val param:LiveData<Data> = _param

    var retrofitInstance: AppsApi = RetrofitClient.getRetrofit().create(AppsApi::class.java)

//    fun getApps(): MutableLiveData<ArrayList<App>> {
//        retrofitInstance.getApps()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(object: Observer<ArrayList<App>> {
//                override fun onComplete() {}
//                override fun onSubscribe(d: Disposable) {}
//                override fun onError(e: Throwable) {
//                    Log.e("Check Error Message", "$e")
//                }
//                override fun onNext(t: ArrayList<App>) {
//                    _appList.value = t
//                }
//            })
//        return _appList
//}

    fun getAllParams(): MutableLiveData<Data> {
        retrofitInstance.getAllParams()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: Observer<Data>{
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(t: Data) {
                    _param.value = t
                    Log.i("Check feedback", "$t")
                }
                override fun onError(e: Throwable) {
                    Log.e("Check Error Message", "$e")
                }
                override fun onComplete() {}
            })
        return _param
    }
}