package com.example.m01assignment.viewmodel

data class Mortgage (
        var downpayment:Double,
        var interestRate:Double,
        var loanLength:Int,
        var principalAmount:Double,
        val paymentsOwed:MutableList<Double>? = mutableListOf()




)