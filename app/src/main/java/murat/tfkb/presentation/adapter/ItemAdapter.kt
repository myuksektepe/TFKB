package murat.tfkb.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import murat.tfkb.R
import murat.tfkb.databinding.ListItemBinding
import murat.tfkb.domain.model.SearchResultItem

class ItemAdapter(
    private var itemList: List<SearchResultItem>
) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    internal var onItemSelected: (position: Int, item: SearchResultItem) -> Unit = { _, _ -> }

    override fun getItemCount(): Int = itemList.size

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int = position

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val bindig = ListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(bindig)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        itemList[position].let {
            holder.bind(
                it,
                position,
                onItemSelected
            )
        }
    }

    inner class ViewHolder(
        private val binding: ListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            searchResultItem: SearchResultItem,
            position: Int,
            onItemSelected: (position: Int, item: SearchResultItem) -> Unit,
        ) {
            val context = binding.listItemRoot.context

            // Title
            binding.txtListItemArtistName.text = searchResultItem.artistName
            binding.txtListItemTrackName.text = searchResultItem.trackName
            binding.txtListItemWrapperType.text = "${searchResultItem.wrapperType} - ${searchResultItem.kind}"

            // Image
            Glide.with(context)
                .load(searchResultItem.artworkUrl100)
                .apply(RequestOptions().override(70, 100))
                .centerCrop()
                .placeholder(R.drawable.no_image_placeholder)
                .error(R.drawable.no_image_placeholder)
                .into(binding.imgListItemImage)

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(_itemList: List<SearchResultItem>) {
        itemList = _itemList
        notifyDataSetChanged()
    }
}