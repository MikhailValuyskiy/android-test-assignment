package com.example.shacklehotelbuddy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shacklehotelbuddy.domain.model.SearchModel
import com.example.shacklehotelbuddy.presentation.feed.PropertiesFeedView
import com.example.shacklehotelbuddy.presentation.search.Screen
import com.example.shacklehotelbuddy.presentation.search.SearchScreen
import com.example.shacklehotelbuddy.presentation.search.SearchViewModel
import com.example.shacklehotelbuddy.ui.theme.ShackleHotelBuddyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShackleHotelBuddyTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.SearchWithHistoryScreen.route
                ) {
                    composable(route = Screen.SearchWithHistoryScreen.route) {
                        val viewModel: SearchViewModel = hiltViewModel()
                        val searchHistory = viewModel.searchHistory.collectAsState(emptyList())

                        SearchScreen(searchHistory.value, navController, onSearchClick = {
                            viewModel.saveSearchRequest(it)
                            navController.currentBackStackEntry?.savedStateHandle?.apply {
                                set("searchQuery", it)
                            }
                            navController.navigate(Screen.PropertiesListScreen.route)
                        })
                    }

                    composable(
                        route = Screen.PropertiesListScreen.route
                    ) {
                        val searchQuery =
                            navController.previousBackStackEntry?.savedStateHandle?.get<SearchModel>(
                                "searchQuery"
                            )
                        val viewModel: SearchViewModel = hiltViewModel()

                        viewModel.getProperties(
                            checkInDate = searchQuery?.checkInDate ?: "",
                            checkOutDate = searchQuery?.checkOutDate ?: "",
                            adultNumber = searchQuery?.adultsNumber ?: 0,
                            childrenNumber = searchQuery?.childrenNumber ?: 0
                        )

                        val propertiesList = viewModel.propertiesList.collectAsState()
                        val isLoading = viewModel.isInProgress.collectAsState()
                        PropertiesFeedView(navController = navController, propertiesList, isLoading)

                    }
                }
            }
        }
    }
}
