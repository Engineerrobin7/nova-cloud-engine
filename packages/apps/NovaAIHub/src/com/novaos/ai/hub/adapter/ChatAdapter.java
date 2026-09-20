package com.novaos.ai.hub.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.novaos.ai.hub.R;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private final List<ChatMessage> mMessages;

    public static class ChatMessage {
        public String sender;
        public String text;
        public ChatMessage(String sender, String text) {
            this.sender = sender;
            this.text = text;
        }
    }

    public ChatAdapter(List<ChatMessage> messages) {
        mMessages = messages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_message, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChatMessage msg = mMessages.get(position);
        holder.sender.setText(msg.sender);
        holder.text.setText(msg.text);
    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView sender;
        TextView text;
        ViewHolder(View v) {
            super(v);
            sender = v.findViewById(R.id.msg_sender);
            text = v.findViewById(R.id.msg_text);
        }
    }
}
