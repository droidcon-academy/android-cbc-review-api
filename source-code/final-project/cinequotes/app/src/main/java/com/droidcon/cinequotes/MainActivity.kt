package com.droidcon.cinequotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.droidcon.cinequotes.data.Quotes
import com.droidcon.cinequotes.model.Quote
import com.droidcon.cinequotes.ui.theme.CineQuotesTheme
import com.droidcon.cinequotes.ui.in_app_review.InAppReview

class MainActivity : ComponentActivity() {

    private lateinit var inAppReview: InAppReview

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inAppReview = InAppReview()

        setContent {
            CineQuotesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CineQuotesApp()
                }
            }
        }

        inAppReview.requestReview(this)
    }
}

@Composable
fun CineQuotesApp() {
    Column {
        TopBar()
        QuotesList(
            Quotes().loadQuotes()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    CenterAlignedTopAppBar(
        title = {
        Text(
            text = "CineQuotes",
            style = MaterialTheme.typography.headlineLarge
        )
    })
}

@Composable
fun QuotesList(quotesList:List<Quote>, modifier: Modifier = Modifier) {
   LazyColumn(modifier = modifier) {
       items(quotesList) {
           QuoteCard(
               quote = it,
               modifier = Modifier.padding(6.dp)
           )
       }
   }
}

@Composable
fun QuoteCard(quote: Quote, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column(modifier = Modifier.background(color = MaterialTheme.colorScheme.primary)) {
            Image(
                painter = painterResource(id = quote.movieImage),
                contentDescription = stringResource(id = quote.movieQuote),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = LocalContext.current.getString(quote.movieQuote),
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = LocalContext.current.getString(quote.movieActor),
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .padding(5.dp)
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = LocalContext.current.getString(quote.movieTitle),
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .padding(5.dp)
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}