package com.example.allmovies.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView

//ViewHolder base para todos los RV adapter
abstract class BaseConcatHolder<T>(itemview: View): RecyclerView.ViewHolder(itemview)  {
    abstract fun bind(adapter: T)

}