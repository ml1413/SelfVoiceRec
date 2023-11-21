package com.ooommm.seifvoicerec.listRecord

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.ooommm.seifvoicerec.R
import com.ooommm.seifvoicerec.database.RecordingItem
import com.ooommm.seifvoicerec.player.PlayerFragment
import com.ooommm.seifvoicerec.removeDialod.RemoveDialogFragment
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

class ListRecordAdapter : RecyclerView.Adapter<ListRecordAdapter.ViewHolder>() {

    var data = listOf<RecordingItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var vName: TextView = itemView.findViewById(R.id.file_name)
        var vLength: TextView = itemView.findViewById(R.id.file_length_text)
        var cardView: CardView = itemView.findViewById(R.id.card_view)
        var ivTrash: ImageView = itemView.findViewById(R.id.iv_trash)
        var reRecording: ImageView = itemView.findViewById(R.id.re_recording)

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view: View = layoutInflater.inflate(
                    R.layout.list_item_record,
                    parent,
                    false
                )
                return ViewHolder(view)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val content: Context = holder.itemView.context
        val recordingItem = data[position]
        val itemDuration: Long = recordingItem.length
        val minutes = TimeUnit.MILLISECONDS.toMinutes(itemDuration)
        val second =
            TimeUnit.MILLISECONDS.toSeconds(itemDuration) - TimeUnit.MINUTES.toSeconds(minutes)

        holder.vName.text = recordingItem.name
        holder.vLength.text = String.format("%02d:%02d", minutes, second)

        holder.ivTrash.setOnClickListener {
            removeItemDialog(recordingItem, content)
        }

        holder.cardView.setOnClickListener {
            val filePath = recordingItem.filePath

            val file = File(filePath)
            if (file.exists()) {
                try {
                    playRecord(filePath, content)
                } catch (e: IOException) {

                }
            } else {
                Toast.makeText(content, "Аудиофайл не найден", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun getItemCount() = data.size

    private fun playRecord(filePath: String, context: Context?) {
        val playerFragment: PlayerFragment = PlayerFragment().newInstance(filePath)
        val transaction: FragmentTransaction = (context as FragmentActivity)
            .supportFragmentManager
            .beginTransaction()
        playerFragment.show(transaction, "dialog_playback")
    }

    private fun removeItemDialog(
        recordingItem: RecordingItem,
        context: Context?
    ) {
        val removeDialogFragment = RemoveDialogFragment()
            .newInstance(
                recordingItem.id,
                recordingItem.filePath
            )
        val beginTransaction = (context as FragmentActivity)
            .supportFragmentManager
            .beginTransaction()
        removeDialogFragment.show(beginTransaction, "dialog_remove")

    }
}













