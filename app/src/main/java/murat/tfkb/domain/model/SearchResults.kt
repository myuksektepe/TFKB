package murat.tfkb.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class SearchResults(
    val resultCount: Int,
    val results: List<SearchResultItem>
) : Parcelable