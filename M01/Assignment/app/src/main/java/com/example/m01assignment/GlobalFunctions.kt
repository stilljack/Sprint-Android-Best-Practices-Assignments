package com.example.m01assignment

import com.example.m01assignment.viewmodel.Mortgage
import kotlin.math.pow
import kotlin.math.roundToInt

/*
*
* To calculate an amortized mortgage payment, you'll need to collect the following information in your UI:
Purchase price
Down payment
Interest Rate
Loan length
Consider the best way to set up your UI to get the right kind of input (i.e., decide between EditText, Spinner, etc for each item).
* Use RxJava (RxBinding is fine if you want to use it!) to make the UI responsive to a change in the input.
*
*
You can calculate the payment amount using the formula A = Pr(1+r)^n/((1+r)^n - 1).
Set up an RxJava-based Retrofit client against the random number API described at [https://qrng.anu.edu.au/API/api-demo.php] to retrieve simulated mortgage rates. Get at least two numbers and scale them between reasonable mortgage rates. For example, you might get two numbers and use the higher one as a 30 year fixed rate and the lower one as a 15 year fixed rate. Return these values in your UI either as a guide for the user or directly in your UI widgets.
Stretch goals
Create an amortization table that shows the remaining balance, the total payment, the principal paid, and the interest paid for each month.
Polish the UI. Consider using TextInputLayout for any EditText fields.
*
*
* */

//so lets try to write a dumb function for this

// A  = Principle x first months interest, next su
/*
*  P = principle
*  R = percentage rate of interest
* T = time in months
* dp: CharSequence, ir: CharSequence, ll: CharSequence, pp: CharSequence -
* downpayment, interest rate, loan length, purchase price
*
* */
fun doAmorFromInputs(mortgage: Mortgage):Double {

        val t =mortgage.loanLength*12.00
        val pr =mortgage.principalAmount -mortgage.downpayment
        val r = mortgage.interestRate

        val top = pr*(1+r).pow(t)
        val bottom = (1+r).pow(t-1)
    val answer =  top.div(bottom)

return  answer


}