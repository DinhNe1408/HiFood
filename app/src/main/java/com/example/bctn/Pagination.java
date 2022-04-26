package com.example.bctn;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class Pagination extends RecyclerView.OnScrollListener {

    private LinearLayoutManager linearLayoutManager;

    public Pagination(LinearLayoutManager linearLayoutManager) {
        this.linearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = linearLayoutManager.getChildCount();
        int totalItemCount = linearLayoutManager.getItemCount();
        int firstvisibleItemCount = linearLayoutManager.findFirstVisibleItemPosition();

        if (isLoading() || isLastPage())
            return;

        if (firstvisibleItemCount >= 0 && (visibleItemCount + firstvisibleItemCount) >= totalItemCount) {
            loadMoreItem();
        }
    }

    public abstract void loadMoreItem();

    public abstract boolean isLoading();

    public abstract boolean isLastPage();


}
