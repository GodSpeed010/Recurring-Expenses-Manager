package com.example.c1recurringbills

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.c1recurringbills.ui.theme.C1RecurringBillsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            C1RecurringBillsTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    // Toolbar
    val scaffoldState = rememberScaffoldState()

    Scaffold(scaffoldState = scaffoldState, topBar = {
        TopAppBar(title = { Text("Recurring Expenses") })
    }) {
        Content()
    }

    // Footer (optional
}

@Composable
fun Content() {
    val recurringBills = listOf(
        RecurringBillModel("Netflix", "$7.99", "Monthly", "$482.99"),
        RecurringBillModel("Dropbox", "$7.99", "Monthly", "$643.99"),
        RecurringBillModel("Spotify", "$7.99", "Monthly", "$42.99"),
        RecurringBillModel("Amazon Prime", "$7.99", "Monthly", "$64.99"),
        RecurringBillModel("Apple Music", "$7.99", "Monthly", "$7.99"),
        RecurringBillModel("Youtube Premium", "$11.99", "Monthly", "$23.98"),
        RecurringBillModel("Disney+", "$1.99", "Monthly", "$1.99"),
        RecurringBillModel("Hulu", "$6.99", "Monthly", "$12.98"),
    )

    Column(
        Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        //Title section
        Card(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(6.dp),
            elevation = 4.dp
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "$999.99",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = "Spent On Recurring Charges This Month",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        Surface(
            elevation = 2.dp,
            shape = RoundedCornerShape(8.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                itemsIndexed(recurringBills) { index, bill ->
                    RecurringBill(bill)

                    if (index < recurringBills.lastIndex) {
                        Divider(color = Color.Gray, modifier = Modifier.alpha(0.4f))
                    }
                }
            }

        }
    }
}

@Composable
fun RecurringBill(
    recurringBill: RecurringBillModel,
) {
    Row(
        modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            shape = CircleShape,
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 11.dp, vertical = 2.dp),
                text = recurringBill.companyName[0].toString(),
                fontSize = 30.sp,
                style = TextStyle.Default.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Spacer(Modifier.width(10.dp))
        Column(Modifier.weight(1f)) {
            Text(
                text = recurringBill.companyName,
                fontWeight = FontWeight.Bold
            )
            Text(recurringBill.cost + " " + recurringBill.frequency)
            Text(recurringBill.totalSpent + " total spent", Modifier.alpha(0.4f))
        }
        Button(onClick = {}) {
            Text("Cancel")
        }
    }
}

@Preview
@Composable
fun PreviewApp() {
    App()
}

@Preview
@Composable
fun PreviewContent() {
    Content()
}

@Preview
@Composable
fun PreviewRecurringBill() {
    val bill = RecurringBillModel("Netflix", "$7.99", "Monthly", "$999.99")
    RecurringBill(bill)
}