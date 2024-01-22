package com.develop.nowasteinmyfridge.data.model

import com.google.firebase.firestore.PropertyName

data class UserCreate(

    @get:PropertyName("first_name")
    @set:PropertyName("first_name")
    var firstName: String = "",

    @get:PropertyName("last_name")
    @set:PropertyName("last_name")
    var lastName: String = "",

    @get:PropertyName("gender")
    @set:PropertyName("gender")
    var gender: String = "",

    @get:PropertyName("email")
    @set:PropertyName("email")
    var email: String = "",

    @get:PropertyName("password")
    @set:PropertyName("password")
    var password: String = "",

    @get:PropertyName("birthday")
    @set:PropertyName("birthday")
    var birthday: String = "",

    @get:PropertyName("profile_image_url")
    @set:PropertyName("profile_image_url")
    var profileImageUrl: String? = null
)
