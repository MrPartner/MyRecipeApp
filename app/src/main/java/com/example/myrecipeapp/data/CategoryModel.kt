package com.example.myrecipeapp.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class CategoryModel(
    @SerializedName("idCategory") val idCategory: String,
    @SerializedName("strCategory") val strCategory: String,
    @SerializedName("strCategoryThumb") val strCategoryThumb: String,
    @SerializedName("strCategoryDescription") val strCategoryDescription: String
) : Parcelable

data class CategoriesResponse(val categories: List<CategoryModel>)