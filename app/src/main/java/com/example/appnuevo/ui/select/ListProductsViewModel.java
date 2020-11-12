package com.example.appnuevo.ui.select;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appnuevo.adapters.ProductsSelectedAdapter;
import com.example.appnuevo.models.ProductSelect;

import java.util.ArrayList;

public class ListProductsViewModel extends ViewModel {
    //private ProductsSelectedAdapter productsSelectedAdapter;
    //private LiveData<ArrayList<ProductSelect>> allProducts;
    //private MutableLiveData<String> mText;
    private MutableLiveData<ArrayList<ProductSelect>> allProducts;

    public ListProductsViewModel() {
    }

    /*public LiveData<String> getText() {
        return mText;
    }
     */
}
