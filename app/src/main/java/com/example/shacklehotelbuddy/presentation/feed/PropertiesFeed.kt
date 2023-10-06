package com.example.shacklehotelbuddy.presentation.feed

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.shacklehotelbuddy.R
import com.example.shacklehotelbuddy.domain.model.PropertyModel
import com.example.shacklehotelbuddy.ui.theme.ShackleHotelBuddyTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PropertiesFeedView(
    navController: NavController,
    propertiesList: State<List<PropertyModel>>,
    isLoading: State<Boolean>,
) {
    Scaffold(topBar = { TopAppBarView(navController) }) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Content(propertiesList, isLoading)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarView(navController: NavController) {
    CenterAlignedTopAppBar(
        title = { TopAppBarTitleText() },
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }
            ) {
                Icon(Icons.Outlined.ArrowBack, null, tint = Color.Black)
            }
        }
    )
}


@Composable
fun TopAppBarTitleText() {
    Text(
        text = stringResource(id = R.string.search_result),
        color = Color.Black,
        style = ShackleHotelBuddyTheme.typography.bodyBoldMedium
    )
}


@Composable
fun Content(propertiesList: State<List<PropertyModel>>, isLoading: State<Boolean>) {
    if (isLoading.value) {
        ProgressView()
    } else {
        if (propertiesList.value.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(
                    items = propertiesList.value
                ) {
                    PropertyItem(
                        property = it
                    )
                }
            }
        } else {
            EmptyPlaceholder()
        }
    }
}


@Composable
fun EmptyPlaceholder() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.no_data),
            style = ShackleHotelBuddyTheme.typography.bodyMedium,
            color = ShackleHotelBuddyTheme.colors.grayText
        )
    }
}


@Composable
fun ProgressView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PropertyItem(
    property: PropertyModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        GlideImage(
            model = property.propertyImage,
            contentDescription = "property_image",
            modifier = Modifier
                .fillMaxWidth()
                .size(200.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .absolutePadding(0.dp, 8.dp, 0.dp, 8.dp)
        ) {

            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = property.name ?: "",
                    style = ShackleHotelBuddyTheme.typography.bodyMediumBold,
                    color = ShackleHotelBuddyTheme.colors.black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = property.locationName ?: "",
                    style = ShackleHotelBuddyTheme.typography.bodyMedium,
                    color = ShackleHotelBuddyTheme.colors.grayText,
                    maxLines = 10,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = property.priceString ?: "",
                    style = ShackleHotelBuddyTheme.typography.bodyMediumBold,
                    color = ShackleHotelBuddyTheme.colors.black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Row {
                Image(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = "rating",
                    modifier = Modifier
                        .height(20.dp)
                        .width(20.dp)
                )
                Text(
                    modifier = Modifier
                        .background(Color.Transparent),
                    text = "4",
                    style = ShackleHotelBuddyTheme.typography.bodyMedium,
                    color = ShackleHotelBuddyTheme.colors.black
                )
            }
        }
    }
}

