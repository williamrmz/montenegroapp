package com.example.appnuevo.ui.dialogs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appnuevo.models.ProductSelect;

public class SearchProductViewModel extends ViewModel {
    private MutableLiveData<ProductSelect> products = new MutableLiveData<>();

    public void setProducts(ProductSelect product){
        products.setValue(product);
    }

    public LiveData<ProductSelect> getProducts(){
        return products;
    }



}
