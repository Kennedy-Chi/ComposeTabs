package com.anunobi.composetabs

import androidx.compose.runtime.Composable

typealias ComposableFun = @Composable () -> Unit

sealed class TabItem(var icon: Int, var title: String, var screen: ComposableFun){
    object Music: TabItem(R.drawable.ic_baseline_library_music_24, "Music", { MusicScreen()})
    object Movies: TabItem(R.drawable.ic_baseline_local_movies_24, "Movies", { MoviesScreen()})
    object Books: TabItem(R.drawable.ic_baseline_menu_book_24, "Books", { BooksScreen()})
}
