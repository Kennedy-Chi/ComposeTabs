package com.anunobi.composetabs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anunobi.composetabs.ui.theme.ComposeTabsTheme
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @ExperimentalPagerApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTabsTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen()
                }
            }
        }
    }
}

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun MainScreen(){
    val tabs = listOf(
        TabItem.Music,
        TabItem.Movies,
        TabItem.Books
    )
    val pagerState = rememberPagerState()
    Scaffold(
       topBar = { TopBar() }
    ){
        Column{
            Tabs(tabs = tabs, pagerState = pagerState)
            TabsContent(tabs = tabs, pagerState = pagerState)
        }
    }
}

@Composable
fun TopBar(){
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name), fontSize = 18.sp,)},
        backgroundColor = Color.White,
        contentColor = Color.Red,
        elevation = 5.dp
    )
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar()
}


@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState){
    val scope = rememberCoroutineScope()
    // OR ScrollableTabRow()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = Color.Red,
        contentColor = Color.White,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }
    ) {
        tabs.forEachIndexed{ index, tab ->
            //OR Tab()
            LeadingIconTab(
                icon = { Icon(painter = painterResource(id = tab.icon), contentDescription = "")},
                selected = pagerState.currentPage == index,
                text = { Text(tab.title) },
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}


@ExperimentalPagerApi
@Composable
fun TabsContent(tabs: List<TabItem>, pagerState: PagerState){
    HorizontalPager(count = tabs.size, state = pagerState) {page ->
        tabs[page].screen()
    }
}



//------------------THE TABS SCREEN-------------------
@Composable
fun MusicScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
            .wrapContentSize(Alignment.Center)
    ){
        Text(
            text = "Music View",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MusicScreenPreview(){
    MusicScreen()
}


@Composable
fun MoviesScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
            .wrapContentSize(Alignment.Center)
    ){
        Text(
            text = "Movies View",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MoviesScreenPreview(){
    MoviesScreen()
}

@Composable
fun BooksScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
            .wrapContentSize(Alignment.Center)
    ){
        Text(
            text = "Books View",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BooksScreenPreview(){
    BooksScreen()
}
//---------X---------THE TABS SCREEN---------X----------
