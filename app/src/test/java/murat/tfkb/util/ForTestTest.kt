package murat.tfkb.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ForTestTest{

    @Test
    fun testSum(){
        val a = (0..7).random()
        val b = (0..17).random()
        val result = ForTest.sum(a,b)
        val expected = a+b
        assertThat(result).isEqualTo(expected)
    }
}