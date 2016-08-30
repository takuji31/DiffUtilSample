package jp.takuji31.diffutilsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.util.DiffUtil
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

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
