package jp.takuji31.diffutilsample

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import jp.takuji31.diffutilsample.Status.*

enum class Status {
    INTERESTED, LIKE, LOVE;
}

data class Artist(val name: String, val status: Status) {
    companion object {
        val list: List<Artist> = listOf(
                Artist(name = "小倉唯", status = LOVE),
                Artist(name = "雨宮天", status = LOVE),
                Artist(name = "水瀬いのり", status = LIKE),
                Artist(name = "Trysail", status = LIKE),
                Artist(name = "Minami", status = LIKE),
                Artist(name = "佐倉綾音", status = LOVE),
                Artist(name = "田村ゆかり", status = INTERESTED),
                Artist(name = "ワルキューレ", status = INTERESTED),
                Artist(name = "水樹奈々", status = INTERESTED)
        )
    }
}

class DiffCallback(val oldList: List<Artist>, val newList: List<Artist>) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Pair<Status, Status> {
        return Pair(oldList[oldItemPosition].status, newList[newItemPosition].status)
    }
}

class Adapter() : RecyclerView.Adapter<Adapter.ViewHolder>() {

    var items = listOf<Artist>()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.nameTextView.text = item.name
        holder.statusTextView.text = when (item.status) {
            Status.INTERESTED -> ""
            Status.LIKE -> "Like"
            Status.LOVE -> "Love"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(View.inflate(parent.context, R.layout.recycler_view_row, parent))
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView by lazy {
            itemView.findViewById(R.id.nameTextView) as TextView
        }
        val statusTextView by lazy {
            itemView.findViewById(R.id.statusTextView) as TextView
        }
    }
}

class MainActivity : AppCompatActivity() {

    val recylerView by lazy {
        findViewById(R.id.recyclerView) as RecyclerView
    }

    val adapter by lazy {
        Adapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recylerView.layoutManager = LinearLayoutManager(this)
        recylerView.adapter = adapter

        adapter.items = Artist.list
        adapter.notifyDataSetChanged()
    }
}
