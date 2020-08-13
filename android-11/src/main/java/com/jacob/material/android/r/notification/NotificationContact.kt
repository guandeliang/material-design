package com.jacob.material.android.r.notification

data class NotificationContact (
        val id: Long,
        val name: String,
        val description: String,
        val icon: Int
) {

    companion object {

        const val URI_CATEGORY_SHORTCUT:String ="shortcut"
        const val URI_CATEGORY_PENDING:String ="pending"
        const val URI_CATEGORY_REPLY:String ="reply"
    }

    val shortcutId = "contact_$id"
    val shortcutActionUri = "https://com.jacob.material/$URI_CATEGORY_SHORTCUT/$id"
    val notificationPendingUri = "https://com.jacob.material/$URI_CATEGORY_PENDING/$id"
    val notificationReplyUri = "https://com.jacob.material/$URI_CATEGORY_REPLY/$id"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NotificationContact

        if (id != other.id) return false
        if (name != other.name) return false
        if (icon != other.icon) return false
        if (description != other.description) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + icon
        return result
    }

}
