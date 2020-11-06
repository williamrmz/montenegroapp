package com.example.appnuevo.ui.dialogs;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appnuevo.models.ProductSelect;

public class SearchProductViewModel extends AndroidViewModel {

    private MutableLiveData<ProductSelect> products = new MutableLiveData<>();

    public SearchProductViewModel(@NonNull Application application) {
        super(application);
    }

    public void setProducts(ProductSelect product){
        products.setValue(product);
    }

    public LiveData<ProductSelect> getProducts(){
        return products;
    }



}
