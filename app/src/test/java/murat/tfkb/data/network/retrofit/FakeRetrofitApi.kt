package murat.tfkb.data.network.retrofit

import murat.tfkb.domain.model.SearchResultItem
import murat.tfkb.domain.model.SearchResults

class FakeRetrofitApi : RetrofitApi {

    private val item1 = SearchResultItem(
        wrapperType = "track",
        kind = "song",
        artistId = 319089613,
        collectionId = 1441161170,
        trackId = 1441161311,
        artistName = "Jesus Culture",
        collectionName = "Awakening - Live from Chicago",
        trackName = "Dance (feat. Kim Walker-Smith)",
        collectionCensoredName = "Awakening - Live from Chicago",
        trackCensoredName = "Dance (feat. Kim Walker-Smith) [Live]",
        artistViewUrl = "https=//music.apple.com/us/artist/jesus-culture/319089613?uo=4",
        collectionViewUrl = "https=//music.apple.com/us/album/dance-feat-kim-walker-smith-live/1441161170?i=1441161311&uo=4",
        trackViewUrl = "https=//music.apple.com/us/album/dance-feat-kim-walker-smith-live/1441161170?i=1441161311&uo=4",
        previewUrl = "https=//audio-ssl.itunes.apple.com/itunes-assets/AudioPreview125/v4/c8/36/b3/c836b3b1-9430-a14b-57a4-c95f5b8bc940/mzaf_4563035836444892549.plus.aac.p.m4a",
        artworkUrl30 = "https=//is4-ssl.mzstatic.com/image/thumb/Music128/v4/0f/75/da/0f75da75-6892-a96f-e1ab-1be5ff37bfb6/source/30x30bb.jpg",
        artworkUrl60 = "https=//is4-ssl.mzstatic.com/image/thumb/Music128/v4/0f/75/da/0f75da75-6892-a96f-e1ab-1be5ff37bfb6/source/60x60bb.jpg",
        artworkUrl100 = "https=//is4-ssl.mzstatic.com/image/thumb/Music128/v4/0f/75/da/0f75da75-6892-a96f-e1ab-1be5ff37bfb6/source/100x100bb.jpg",
        collectionPrice = 12.99,
        trackPrice = 1.29,
        releaseDate = "2011-11-29T12=00=00Z",
        collectionExplicitness = "notExplicit",
        trackExplicitness = "notExplicit",
        discCount = 1,
        discNumber = 1,
        trackCount = 16,
        trackNumber = 16,
        trackTimeMillis = 318440,
        country = "USA",
        currency = "USD",
        primaryGenreName = "Christian",
        isStreamable = true
    )

    private val item2 = SearchResultItem(
        wrapperType = "track",
        kind = "song",
        artistId = 277293880,
        collectionId = 1440818588,
        trackId = 1440818653,
        artistName = "Lady Gaga",
        collectionName = "The Fame",
        trackName = "Just Dance (feat. Colby O'Donis)",
        collectionCensoredName = "The Fame",
        trackCensoredName = "Just Dance (feat. Colby O'Donis)",
        artistViewUrl = "https://music.apple.com/us/artist/lady-gaga/277293880?uo=4",
        collectionViewUrl = "https://music.apple.com/us/album/just-dance-feat-colby-odonis/1440818588?i=1440818653&uo=4",
        trackViewUrl = "https://music.apple.com/us/album/just-dance-feat-colby-odonis/1440818588?i=1440818653&uo=4",
        previewUrl = "https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview126/v4/59/1d/67/591d677d-477c-0253-ff62-dd776ed64ff8/mzaf_16736154942926151065.plus.aac.p.m4a",
        artworkUrl30 = "https://is1-ssl.mzstatic.com/image/thumb/Music124/v4/3d/4a/3b/3d4a3b05-54fb-198b-addd-de92f9d22ccf/source/30x30bb.jpg",
        artworkUrl60 = "https://is1-ssl.mzstatic.com/image/thumb/Music124/v4/3d/4a/3b/3d4a3b05-54fb-198b-addd-de92f9d22ccf/source/60x60bb.jpg",
        artworkUrl100 = "https://is1-ssl.mzstatic.com/image/thumb/Music124/v4/3d/4a/3b/3d4a3b05-54fb-198b-addd-de92f9d22ccf/source/100x100bb.jpg",
        collectionPrice = 9.99,
        trackPrice = 1.29,
        releaseDate = "2008-01-01T12:00:00Z",
        collectionExplicitness = "notExplicit",
        trackExplicitness = "notExplicit",
        discCount = 1,
        discNumber = 1,
        trackCount = 15,
        trackNumber = 1,
        trackTimeMillis = 242017,
        country = "USA",
        currency = "USD",
        primaryGenreName = "Pop",
        isStreamable = true
    )


    private val item3 = SearchResultItem(
        wrapperType = "track",
        kind = "song",
        artistId = 156987,
        collectionId = 277635758,
        trackId = 277635828,
        artistName = "Jason Mraz",
        collectionName = "We Sing. We Dance. We Steal Things",
        trackName = "I'm Yours",
        collectionCensoredName = "We Sing. We Dance. We Steal Things",
        trackCensoredName = "I'm Yours",
        artistViewUrl = "https://music.apple.com/us/artist/jason-mraz/156987?uo=4",
        collectionViewUrl = "https://music.apple.com/us/album/im-yours/277635758?i=277635828&uo=4",
        trackViewUrl = "https://music.apple.com/us/album/im-yours/277635758?i=277635828&uo=4",
        previewUrl = "https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview115/v4/97/83/e2/9783e2a6-0d65-b64c-84d8-717c2a46705c/mzaf_4931291184297103334.plus.aac.p.m4a",
        artworkUrl30 = "https://is3-ssl.mzstatic.com/image/thumb/Music115/v4/15/d7/28/15d72897-ad11-897c-81a6-986a4fd901ca/source/30x30bb.jpg",
        artworkUrl60 = "https://is3-ssl.mzstatic.com/image/thumb/Music115/v4/15/d7/28/15d72897-ad11-897c-81a6-986a4fd901ca/source/60x60bb.jpg",
        artworkUrl100 = "https://is3-ssl.mzstatic.com/image/thumb/Music115/v4/15/d7/28/15d72897-ad11-897c-81a6-986a4fd901ca/source/100x100bb.jpg",
        collectionPrice = 11.99,
        trackPrice = 1.29,
        releaseDate = "2008-04-15T07:00:00Z",
        collectionExplicitness = "explicit",
        trackExplicitness = "notExplicit",
        discCount = 1,
        discNumber = 1,
        trackCount = 12,
        trackNumber = 2,
        trackTimeMillis = 242040,
        country = "USA",
        currency = "USD",
        primaryGenreName = "Pop",
        isStreamable = true
    )

    private val list = mutableListOf<SearchResultItem>(item1, item2, item3, item1, item2)

    override suspend fun getResults(term: String, limit: Int, entity: String): SearchResults {
        /*
        val results = SearchResults(
            resultCount = limit,
            results = list.subList(0, limit)
        )
         */

        val filteredList = list.filter {
            it.collectionCensoredName.contains(term) or it.trackCensoredName.contains(term)
        } as MutableList

        val results = SearchResults(
            resultCount = filteredList.size,
            results = filteredList.subList(0, limit)
        )
        return results
    }
}