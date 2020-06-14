package com.jacob.material.elegantlaunch

import java.io.Serializable

data class Thrones(
    var id:Int,
    var fileName: String,
    var title: String,
    var summary: String
) : Serializable


data class City(
        var country: String,
        var name: String,
        var lat: String,
        var lng: String
) : Serializable