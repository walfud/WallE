package com.walfud.walle.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Use as {@code android.widget.ListView}, extends from {@code android.support.v7.widget.RecyclerView}
 * <p>
 * You can easily listen the user choice by {@code setOnSelectListener}
 * <p>
 * Created by walfud on 2015/9/24.
 */
public class SelectView extends RecyclerView {

    public static final String TAG = "SelectView";

    private OnSelectListener mOnSelectListener;

    public SelectView(Context context) {
        this(context, null);
    }

    public SelectView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * All your listener on the {@code ViewHolder.itemView} will be ignore(removed).
     * <p>
     * Example:
     * <p>
     * {@code setAdapter(new SelectList.Adapter<SelectList.ViewHolder>() {...}}
     * @param adapter
     */
    @Override
    public void setAdapter(final RecyclerView.Adapter adapter) {
        super.setAdapter(new RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                final RecyclerView.ViewHolder viewHolder = adapter.onCreateViewHolder(parent, viewType);
                viewHolder.itemView.setOnClickListener(v -> {
                    if (mOnSelectListener != null) {
                        mOnSelectListener.onSelect(v, viewHolder.getAdapterPosition());
                    }
                });
                return viewHolder;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                adapter.onBindViewHolder(holder, position);
            }

            @Override
            public int getItemCount() {
                return adapter.getItemCount();
            }
        });
    }

    public void setOnSelectListener(OnSelectListener listener) {
        mOnSelectListener = listener;
    }

    // Helper

    /**
     * Easy to create a view holder
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * For unify the grammar
     * @param <T>
     */
    public static abstract class Adapter<T extends ViewHolder> extends RecyclerView.Adapter<T> {
    }

    public interface OnSelectListener {
        void onSelect(View view, int position);
    }
}
