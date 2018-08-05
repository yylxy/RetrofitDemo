package com.example.lyxs9.retrofitdemo;

import com.example.lyxs9.retrofitdemo.demo.GetRequest_Interface;
import com.example.lyxs9.retrofitdemo.demo.Translation;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitTest {
    
    public void test1() {
        //创建Retrofit实例
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/")
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
                .build();
        //创建一个代理对象
        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);
        
        //接口调用
        Call<Translation> call = request.request();
        //执行与回调
        call.enqueue(new Callback<Translation>() {
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                try {
                    if (response != null) {
                        System.out.println(response.body());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
            @Override
            public void onFailure(Call<Translation> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
    
    public void test2() {
        //获取服务的代理
        GetRequest_Interface service = getPoxy(GetRequest_Interface.class);
        //返回的是RxJava Observable 对象
        Observable<Translation> observable = service.getCall();
        //设置订阅
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Translation>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    
                    }
                    
                    @Override
                    public void onNext(Translation translation) {
                        translation.show();
                    }
                    
                    @Override
                    public void onError(Throwable e) {
                    
                    }
                    
                    @Override
                    public void onComplete() {
                    
                    }
                });
        
    }
    
    //获取代理对象
    private <T> T getPoxy(Class<T> tClass) {
        //创建Retrofit实例
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/")
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
                .build();
        //创建一个代理对象
        return retrofit.create(tClass);
    }
}
