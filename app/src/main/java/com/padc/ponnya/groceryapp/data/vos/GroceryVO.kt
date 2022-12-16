package com.padc.ponnya.groceryapp.data.vos

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class GroceryVO(
    val name: String? = "",
    val description: String? = "",
    val amount: Int? = 0,
    var image: String? = ""
)