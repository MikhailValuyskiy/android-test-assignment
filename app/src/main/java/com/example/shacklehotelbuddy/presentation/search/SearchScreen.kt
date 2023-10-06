package com.example.shacklehotelbuddy.presentation.search

import android.app.DatePickerDialog
import android.view.KeyEvent
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.shacklehotelbuddy.R
import com.example.shacklehotelbuddy.domain.model.SearchModel
import com.example.shacklehotelbuddy.ui.theme.ShackleHotelBuddyTheme
import com.example.shacklehotelbuddy.ui.theme.Teal
import com.example.shacklehotelbuddy.ui.theme.White
import java.util.Calendar

@Composable
fun SearchScreen(
    searchModels: List<SearchModel>,
    navController: NavController,
    onSearchClick: (SearchModel) -> Unit
) {

    var adultsNumberText by remember { mutableStateOf("") }
    var childrenNumberText by remember { mutableStateOf("") }
    var checkInDateText by remember { mutableStateOf("") }
    var checkOutDateText by remember { mutableStateOf("") }

    // Calendar
    val calendar = Calendar.getInstance()
    val checkInYear = calendar[Calendar.YEAR]
    val checkInMonth = calendar[Calendar.MONTH]
    val checkInDayOfMonth = calendar[Calendar.DAY_OF_MONTH]

    val checkOutYear = calendar[Calendar.YEAR]
    val checkOutMonth = calendar[Calendar.MONTH]
    val checkOutDayOfMonth = calendar[Calendar.DAY_OF_MONTH]

    val checkedInDatePicker = DatePickerDialog(
        LocalContext.current,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
            checkInDateText = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
        }, checkInYear, checkInMonth, checkInDayOfMonth
    )
    checkedInDatePicker.datePicker.minDate = calendar.timeInMillis

    val context = LocalContext.current

    // TODO Move to method
    val checkedOutDatePicker = DatePickerDialog(
        LocalContext.current,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
            checkOutDateText = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
        }, checkOutYear, checkOutMonth, checkOutDayOfMonth
    )
    checkedOutDatePicker.datePicker.minDate = calendar.timeInMillis

    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.background),
                contentScale = ContentScale.FillWidth
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.fillMaxHeight()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.85f, true),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.select_guests_date_and_time),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent)
                        .padding(16.dp),
                    style = ShackleHotelBuddyTheme.typography.bodyHigher,
                    color = ShackleHotelBuddyTheme.colors.white
                )
                Card(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = White),
                    shape = RoundedCornerShape(16.dp),
                ) {

                    Column {

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.height(60.dp)
                        ) {

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(16.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.event_upcoming),
                                    contentDescription = stringResource(R.string.content_desc_checkedinimage),
                                    modifier = Modifier
                                        .height(20.dp)
                                        .width(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))

                                Text(
                                    modifier = Modifier
                                        .background(Color.Transparent),
                                    text = stringResource(R.string.check_in_date),
                                    style = ShackleHotelBuddyTheme.typography.bodyMedium,
                                    color = ShackleHotelBuddyTheme.colors.grayText
                                )
                            }
                            Spacer(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(1.dp)
                                    .background(ShackleHotelBuddyTheme.colors.grayBorder)
                            )

                            OutlinedTextField(
                                value = checkInDateText,
                                enabled = false,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .clickable { checkedInDatePicker.show() },
                                onValueChange = { checkInDateText = it },
                                placeholder = {
                                    Text(
                                        text = stringResource(id = R.string.date_hint),
                                        style = ShackleHotelBuddyTheme.typography.bodyMedium,
                                        color = ShackleHotelBuddyTheme.colors.grayText
                                    )
                                },
                                textStyle = ShackleHotelBuddyTheme.typography.bodyMedium,
                                maxLines = 1,
                                colors = textFieldColors()
                            )
                        }

                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(ShackleHotelBuddyTheme.colors.grayBorder)
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.height(60.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(16.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.event_available),
                                    contentDescription = stringResource(R.string.content_desc_checkedoutimage),
                                    modifier = Modifier
                                        .height(20.dp)
                                        .width(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))

                                Text(
                                    modifier = Modifier
                                        .background(Color.Transparent),
                                    text = stringResource(R.string.check_out_date),
                                    style = ShackleHotelBuddyTheme.typography.bodyMedium,
                                    color = ShackleHotelBuddyTheme.colors.grayText
                                )
                            }
                            Spacer(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(1.dp)
                                    .background(ShackleHotelBuddyTheme.colors.grayBorder)
                            )

                            OutlinedTextField(
                                value = checkOutDateText,
                                enabled = false,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .clickable { checkedOutDatePicker.show() },
                                onValueChange = { checkOutDateText = it },
                                placeholder = {
                                    Text(
                                        text = stringResource(id = R.string.date_hint),
                                        style = ShackleHotelBuddyTheme.typography.bodyMedium,
                                        color = ShackleHotelBuddyTheme.colors.grayText
                                    )
                                },
                                textStyle = ShackleHotelBuddyTheme.typography.bodyMedium,
                                maxLines = 1,
                                colors = textFieldColors()
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(ShackleHotelBuddyTheme.colors.grayBorder)
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.height(60.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(16.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.person),
                                    contentDescription = stringResource(R.string.content_desc_adultsimage),
                                    modifier = Modifier
                                        .height(20.dp)
                                        .width(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))

                                Text(
                                    modifier = Modifier
                                        .background(Color.Transparent),
                                    text = stringResource(R.string.adults),
                                    style = ShackleHotelBuddyTheme.typography.bodyMedium,
                                    color = ShackleHotelBuddyTheme.colors.grayText
                                )
                            }
                            Spacer(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(1.dp)
                                    .background(ShackleHotelBuddyTheme.colors.grayBorder)
                            )

                            OutlinedTextField(
                                value = adultsNumberText,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .onPreviewKeyEvent {
                                        if (it.key == Key.Tab && it.nativeKeyEvent.action == KeyEvent.ACTION_DOWN) {
                                            focusManager.moveFocus(FocusDirection.Down)
                                            true
                                        } else {
                                            false
                                        }
                                    },
                                onValueChange = { adultsNumberText = it },
                                placeholder = {
                                    Text(
                                        text = stringResource(id = R.string.adult_hint),
                                        style = ShackleHotelBuddyTheme.typography.bodyMedium,
                                        color = ShackleHotelBuddyTheme.colors.grayText
                                    )
                                },
                                textStyle = ShackleHotelBuddyTheme.typography.bodyMedium,
                                maxLines = 1,
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number,
                                    imeAction = ImeAction.Next
                                ),
                                colors = textFieldColors(),
                                keyboardActions = KeyboardActions(
                                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                                )
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(ShackleHotelBuddyTheme.colors.grayBorder)
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.height(60.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(16.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.supervisor_account),
                                    contentDescription = stringResource(R.string.content_desc_childrenimage),
                                    modifier = Modifier
                                        .height(20.dp)
                                        .width(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))

                                Text(
                                    modifier = Modifier
                                        .background(Color.Transparent),
                                    text = stringResource(R.string.children),
                                    style = ShackleHotelBuddyTheme.typography.bodyMedium,
                                    color = ShackleHotelBuddyTheme.colors.grayText
                                )
                            }
                            Spacer(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(1.dp)
                                    .background(ShackleHotelBuddyTheme.colors.grayBorder)
                            )

                            OutlinedTextField(
                                value = childrenNumberText,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .onPreviewKeyEvent {
                                        if (it.key == Key.Tab && it.nativeKeyEvent.action == KeyEvent.ACTION_DOWN) {
                                            focusManager.moveFocus(FocusDirection.Down)
                                            true
                                        } else {
                                            false
                                        }
                                    },
                                onValueChange = { childrenNumberText = it },
                                placeholder = {
                                    Text(
                                        text = stringResource(id = R.string.adult_hint),
                                        style = ShackleHotelBuddyTheme.typography.bodyMedium,
                                        color = ShackleHotelBuddyTheme.colors.grayText
                                    )
                                },
                                textStyle = ShackleHotelBuddyTheme.typography.bodyMedium,
                                maxLines = 1,
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number,
                                    imeAction = ImeAction.Done
                                ),
                                colors = textFieldColors(),
                                keyboardActions = KeyboardActions(
                                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                                )
                            )
                        }

                    }

                }

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent)
                        .absolutePadding(16.dp, 12.dp, 0.dp, 8.dp),
                    text = stringResource(R.string.recent_searches),
                    style = ShackleHotelBuddyTheme.typography.bodyBoldMedium,
                    color = ShackleHotelBuddyTheme.colors.white
                )
                LazyColumn {
                    items(items = searchModels) {
                        SearchQueryItem(
                            model = it,
                            navController
                        )
                    }
                }

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.15f, true),
                verticalAlignment = Alignment.Bottom
            ) {

                Button(colors = ButtonDefaults.buttonColors(containerColor = Teal),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(60.dp)
                        .border(
                            width = 5.dp, color = Teal,
                            shape = RoundedCornerShape(20.dp)
                        ),
                    onClick = {
                        focusManager.clearFocus()
                        if (checkInDateText.isNotEmpty() &&
                            checkOutDateText.isNotEmpty() &&
                            adultsNumberText.isNotEmpty()
                        ) {

                            val searchQuery = SearchModel(
                                checkInDate = checkInDateText,
                                checkOutDate = checkOutDateText,
                                adultsNumber = adultsNumberText.toInt(),
                                childrenNumber = if (childrenNumberText.isNotEmpty()) childrenNumberText.toInt() else 0,
                                time = System.currentTimeMillis()
                            )

                            onSearchClick(searchQuery)
                        } else {
                            Toast.makeText(
                                context, context.getString(R.string.please_enter_valid_information),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }) {
                    Text(
                        text = stringResource(R.string.search),
                        style = ShackleHotelBuddyTheme.typography.bodyBoldMedium,
                        color = ShackleHotelBuddyTheme.colors.white
                    )
                }
            }
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchQueryItem(
    model: SearchModel,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .absolutePadding(16.dp, 6.dp, 16.dp, 6.dp)
            .fillMaxWidth()
            .height(48.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        shape = RoundedCornerShape(8.dp),
        onClick = {
            navController.currentBackStackEntry?.savedStateHandle?.apply {
                set("searchQuery", model)
            }
            navController.navigate(Screen.PropertiesListScreen.route)
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .absolutePadding(8.dp, 0.dp, 8.dp, 0.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.manage_history),
                contentDescription = stringResource(R.string.searchinimage),
                modifier = Modifier
                    .height(20.dp)
                    .width(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))

            Spacer(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
                    .background(ShackleHotelBuddyTheme.colors.grayBorder)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                text = "${model.checkInDate} - ${model.checkOutDate}    ${model.adultsNumber} adults, ${model.childrenNumber} children",
                style = ShackleHotelBuddyTheme.typography.bodyMedium,
                color = ShackleHotelBuddyTheme.colors.grayText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun textFieldColors() = OutlinedTextFieldDefaults.colors(
    focusedTextColor = ShackleHotelBuddyTheme.colors.grayText,
    disabledTextColor = ShackleHotelBuddyTheme.colors.grayText,
    cursorColor = ShackleHotelBuddyTheme.colors.grayText,
    focusedBorderColor = Color.Transparent,
    unfocusedBorderColor = Color.Transparent,
    disabledBorderColor = Color.Transparent,
)


sealed class Screen(val route: String) {
    object PropertiesListScreen : Screen("properties_screen")
    object SearchWithHistoryScreen : Screen("search_with_history_screen")
}
