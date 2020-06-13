package com.jacob.material.applaunch

import java.io.Serializable

data class Thrones(
    var id:Int,
    var fileName: String,
    var title: String,
    var summary: String
) : Serializable