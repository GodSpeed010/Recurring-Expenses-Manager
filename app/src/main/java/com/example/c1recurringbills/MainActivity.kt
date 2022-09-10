package com.example.c1recurringbills

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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
        RecurringBill("Netflix", "$7.99", "Monthly", "$482.99"),
        RecurringBill("Dropbox", "$7.99", "Monthly", "$482.99"),
        RecurringBill("Dropbox", "$7.99", "Monthly", "$482.99"),
    )

    Column(
        Modifier.padding(8.dp)
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

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(6.dp))
                .shadow(2.dp)
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

@Composable
fun RecurringBill(recurringBill: RecurringBill) {
    Row(
        modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = recurringBill.companyName[0].toString(),
            fontSize = 30.sp,
            style = TextStyle.Default.copy(
                fontWeight = FontWeight.Bold
            )
        )
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
    val bill = RecurringBill("Netflix", "$7.99", "Monthly", "$999.99")
    RecurringBill(bill)
}