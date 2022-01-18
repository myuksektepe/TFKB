package murat.tfkb.domain.use_case

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import murat.tfkb.data.repository.FakeRemoteDataSource
import murat.tfkb.domain.model.ResultState
import murat.tfkb.domain.model.SearchResultItem
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetSearchResultTest {
    private lateinit var getSearchResult: GetSearchResult
    private lateinit var fakeRemoteDataSource: FakeRemoteDataSource

    @Before
    fun setUp() {
        fakeRemoteDataSource = FakeRemoteDataSource()
        getSearchResult = GetSearchResult(fakeRemoteDataSource)
    }

    @Test
    fun `Get Results`() = runBlocking {

        val term = "Dance"
        val limit = (1..5).random()
        val entity = ""

        var success = false
        var size: Int? = null
        var list: MutableList<SearchResultItem>? = null

        getSearchResult(term, limit, entity).collectLatest {
            when (it) {
                is ResultState.SUCCESS -> {
                    success = true
                    size = it.data.size
                    list = it.data
                }
                is ResultState.ERROR -> {
                    success = false
                }
            }
        }
        System.out.println("Request Limit: $limit")
        System.out.println("Result Items Size: $size")
        System.out.println("Result Items: $list")

        assertThat(success).isTrue()
        assertThat(size).isEqualTo(limit)
    }

}