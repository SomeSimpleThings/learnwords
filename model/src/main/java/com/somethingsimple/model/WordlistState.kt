package com.somethingsimple.model

import com.somethingsimple.model.vo.Word

sealed class WordlistState {
    data class Success(val data: List<Word>?) : WordlistState()
    data class Error(val error: Throwable) : WordlistState()
    data class Loading(val progress: Int?) : WordlistState()
}
