package com.example.musicapp.presentation.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.domain.usecases.SearchByQueryUseCases
import com.example.musicapp.presentation.model.MusicUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


data class SearchUiState(
    val query: String = "",
    val music: List<MusicUi> = emptyList(),
    val isLoading: Boolean = false
)

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchByQueryUseCases: SearchByQueryUseCases
) : ViewModel() {

    private val searQueryFlow = MutableStateFlow("")

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _uiStateFlow = MutableStateFlow(SearchUiState())
    val uiStateFlow: StateFlow<SearchUiState> = _uiStateFlow.asStateFlow()


    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _music = MutableStateFlow(listOf<Music>())
    val music = searchText
        .combine(_music) { text, music ->
            if (text.isBlank()) {
                music
            } else {
                music.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _music.value
        )

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun onValueChange(value: String) {
        searQueryFlow.tryEmit(value)
    }
}



data class Music(
    val firstName: String,
    val lastName: String,
) {

    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            "$firstName$lastName",
            "$firstName$lastName",
            "${firstName.first()}${lastName.first()}",
        )
        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}
