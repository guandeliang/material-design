package com.jacob.material.android.r.inset

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jacob.material.android.r.R

class ConversationAdapter : RecyclerView.Adapter<MessageHolder>() {
    private var dataList:MutableList<ChatMessage> = mutableListOf()

    init {
        dataList.add(ChatMessage(ChatMessage.SELF_STRING, R.string.message_01))
        dataList.add(ChatMessage(ChatMessage.OTHER_IMAGE, R.drawable.motion_four_one))
        dataList.add(ChatMessage(ChatMessage.SELF_STRING, R.string.message_02))
        dataList.add(ChatMessage(ChatMessage.SELF_IMAGE, R.drawable.motion_four_two))
        dataList.add(ChatMessage(ChatMessage.OTHER_STRING, R.string.message_03))
        dataList.add(ChatMessage(ChatMessage.OTHER_IMAGE, R.drawable.motion_four_three))
        dataList.add(ChatMessage(ChatMessage.SELF_STRING, R.string.message_04))
        dataList.add(ChatMessage(ChatMessage.SELF_IMAGE, R.drawable.motion_four_four))
        dataList.add(ChatMessage(ChatMessage.OTHER_STRING, R.string.message_05))
        dataList.add(ChatMessage(ChatMessage.SELF_IMAGE, R.drawable.motion_four_five))
        dataList.add(ChatMessage(ChatMessage.SELF_STRING, R.string.message_06))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = when (viewType) {
            ChatMessage.SELF_STRING -> {
                inflater.inflate(R.layout.inset_animation_message_bubble_text_self, parent, false)
            }
            ChatMessage.SELF_IMAGE -> {
                inflater.inflate(R.layout.inset_animation_message_bubble_image_self, parent, false)
            }
            ChatMessage.OTHER_STRING -> {
                inflater.inflate(R.layout.inset_animation_message_bubble_text_other, parent, false)
            }
            else -> {
                inflater.inflate(R.layout.inset_animation_message_bubble_image_other, parent, false)
            }
        }
        return MessageHolder(view)
    }

    override fun onBindViewHolder(holder: MessageHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemViewType(position: Int): Int {
        return dataList[position].messageType
    }

    override fun getItemCount(): Int = dataList.size

}

class MessageHolder(var view: View) : RecyclerView.ViewHolder(view){
    fun bind(data:ChatMessage){
        when(data.messageType){
            ChatMessage.SELF_STRING, ChatMessage.OTHER_STRING ->{
                var textView:TextView = view.findViewById(R.id.text_view);
                textView.setText(data.resId);
            }
            ChatMessage.SELF_IMAGE, ChatMessage.OTHER_IMAGE ->{
                var imageView: ImageView = view.findViewById(R.id.image_view);
                imageView.setImageResource(data.resId);
            }
        }
    }
}
