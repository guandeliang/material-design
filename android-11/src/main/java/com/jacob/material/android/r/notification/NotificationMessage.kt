package com.jacob.material.android.r.notification

data class NotificationMessage (
        val id: Long,
        val content: String,
        val timestamp: Long,
        val sender: NotificationContact
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NotificationMessage

        if (id != other.id) return false
        if (content != other.content) return false
        if (timestamp != other.timestamp) return false
        if (sender != other.sender) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + content.hashCode()
        result = 31 * result + timestamp.hashCode()
        result = 31 * result + sender.hashCode()
        return result
    }


}
