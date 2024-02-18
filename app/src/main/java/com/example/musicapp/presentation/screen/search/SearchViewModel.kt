package com.example.musicapp.presentation.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.domain.models.DailyDomain
import com.example.musicapp.domain.usecases.FetchAllUseCase
import com.example.musicapp.presentation.model.MusicUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


data class SearchUiState(
    val query: String = "",
    val music: List<DailyDomain> = emptyList(),
    val isLoading: Boolean = false
)

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val fetchAllUseCase: FetchAllUseCase
) : ViewModel() {

    private val searQueryFlow = MutableStateFlow("")

    private val _uiStateFlow = MutableStateFlow(SearchUiState())
    val uiStateFlow: StateFlow<SearchUiState> = _uiStateFlow.asStateFlow()

    init {
        searQueryFlow.onEach { query ->
            _uiStateFlow.tryEmit(
                _uiStateFlow.value.copy(
                    query = query, isLoading = true
                )
            )
        }
            .debounce(300).onEach(
                ::startSearch
            ).launchIn(viewModelScope)
    }

    private fun startSearch(query: String) {
        viewModelScope.launch {
            val content = fetchAllUseCase.invoke()
            val result = content.filter { it.title.contains(query, ignoreCase = true) }
            val music = result.map { it }
            _uiStateFlow.tryEmit(
                _uiStateFlow.value.copy(
                    music = music,
                    isLoading = false
                )
            )
        }
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
