package google.louco.com.bakingapp.di;

import google.louco.com.bakingapp.mvp.model.RetrofitDate;

public class RetrofitAplication {

    private static RetrofitComponent retrofitComponent = null;

    public static RetrofitComponent getComponent(){
        if(retrofitComponent == null){
            retrofitComponent = getBuild();
            return retrofitComponent;
        }else{
            return retrofitComponent;
        }
    }

    private static RetrofitComponent getBuild(){
        return DaggerRetrofitComponent.builder()
                .retrofitDate(new RetrofitDate())
                .build();
    }
}
