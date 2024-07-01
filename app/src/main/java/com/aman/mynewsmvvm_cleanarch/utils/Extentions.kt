package com.aman.mynewsmvvm_cleanarch.utils

import androidx.appcompat.widget.SearchView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

fun SearchView.getQueryTextChangeStateFlow(): StateFlow<String> {

    val query = MutableStateFlow("")
/*
*  An extension function allows you to add functionality to a class without modifying its source code.
*   Inside an extension function, you can refer to the instance of the class it is extending using the this keyword implicitly.
*   don't need to explicitly reference this when calling methods or accessing properties
* */
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {

        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String): Boolean {
            query.value = newText
            return true
        }

    })

    return query
}