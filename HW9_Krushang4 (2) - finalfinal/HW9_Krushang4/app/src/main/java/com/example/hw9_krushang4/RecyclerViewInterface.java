package com.example.hw9_krushang4;

import android.view.View;

public interface RecyclerViewInterface {
    void itemOnClick(int position);

    void addFavourite(int position,View view);
}