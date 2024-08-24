package com.example.timecalculator

import android.annotation.SuppressLint

class MathematicalOperations() {
    @SuppressLint("SuspiciousIndentation")
    public fun transformation(string: String) : Int {

        if (isValidTimeFormatHour(string)) {
            val parts = string.split("h", "m", "s")
            var hours = 0
            var minutes = 0
            var seconds = 0
            if (parts[0].toInt() < 24) {
                hours = parts[0].toInt()
            } else return 0
            if (parts[1].toInt() < 60) {
                minutes = parts[1].toInt()
            } else return 0
            if (parts[2].toInt() < 60) {
                seconds = parts[2].toInt()
            } else return 0
            return hours * 3600 + minutes * 60 + seconds
        } else if (isValidTimeFormatMinute(string)) {
            val parts = string.split("m", "s")
            var minutes = 0
            var seconds = 0
                if (parts[0].toInt() < 60) {
                    minutes = parts[0].toInt()
                } else return 0
                if (parts[1].toInt() < 60) {
                    seconds = parts[1].toInt()
                } else return 0
            return minutes * 60 + seconds
        } else if (isValidTimeFormatSecond(string)) {
            val seconds = string.split("s")[0].toInt()
            if (seconds < 60) {
                return seconds
            } else return 0
        }
        return 0
    }

    fun addTime(time1: Int, time2: Int): Int {
        return time1 + time2
    }
    fun subtractTime(time1: Int, time2: Int): Int {
        return time1 - time2
    }

    fun isValidTimeFormatHour(input: String): Boolean {
        val regex = Regex("""^\d+h\d+m\d+s$""")
        return regex.matches(input)
    }

    fun isValidTimeFormatMinute(input: String): Boolean {
        val regex = Regex("""^\d+m\d+s$""")
        return regex.matches(input)
    }
    fun isValidTimeFormatSecond(input: String): Boolean {
        val regex = Regex("""^\d+s$""")
        return regex.matches(input)
    }

    fun formatTime(seconds: Int): String {
        val hours = seconds / 3600
        val minutes = (seconds % 3600) / 60
        val remainingSeconds = seconds % 60

        return String.format("%02dh%02dm%02ds", hours, minutes, remainingSeconds)
    }
}