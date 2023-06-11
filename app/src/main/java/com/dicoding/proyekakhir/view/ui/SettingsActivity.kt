package com.dicoding.proyekakhir.view.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import com.dicoding.proyekakhir.R
import com.dicoding.proyekakhir.alarm.AlarmReceiver
import com.dicoding.proyekakhir.databinding.ActivitySettingsBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.switchmaterial.SwitchMaterial

class SettingsActivity : AppCompatActivity() {

    companion object {
        private const val PREFS_NAME = "github_user"
//
//        private const val ALARM_TYPE = "alarm_repeat"
//        private const val ALARM_TIME = "09:00"
//        private const val ALARM_MESSAGE = "reminder"

        private const val SWITCH_STATE = "onState"
    }

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var alarmReceiver: AlarmReceiver
    private lateinit var binding: ActivitySettingsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        val switchTheme = findViewById<SwitchCompat>(R.id.toggle_reminder)

        val sharedPreferences = getSharedPreferences("Mode", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val nightMode =sharedPreferences.getBoolean("night",false)

        if (nightMode){
            switchTheme.isChecked = true
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }

        switchTheme.setOnCheckedChangeListener{button, isChecked->
            if(!isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                editor.putBoolean("night",false)
                editor.apply()
                switchTheme.text = "Enable Dark Mode"
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                editor.putBoolean("night",true)
                editor.apply()
                switchTheme.text = "Disable Dark Mode"
            }
        }

        with(binding) {
            btnArrowBack.setOnClickListener {
                finish()
                onBackPressed()
            }

            changeLanguage.setOnClickListener {
                val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(intent)
                slideToLeft()
            }

//            toggleReminder.apply {
//                isChecked = sharedPreferences.getBoolean(SWITCH_STATE, false)
//                alarmReceiver = AlarmReceiver()
//                setOnCheckedChangeListener { _, isChecked ->
//                    with(alarmReceiver) {
//                        val reminderOn = resources.getString(R.string.reminder_on)
//                        val reminderOff = resources.getString(R.string.reminder_off)
//
//                        if (isChecked) {
//                            setOnRepeatingAlarm(
//                                context, ALARM_TYPE, ALARM_TIME, ALARM_MESSAGE
//                            )
//                            Snackbar.make(toggleReminder, reminderOn, Snackbar.LENGTH_SHORT)
//                                .setBackgroundTint(context.getColor(R.color.colorDarkGray))
//                                .setTextColor(context.getColor(R.color.colorWhite))
//                                .show()
//                        } else {
//                            setOffRepeatingAlarm(
//                                context
//                            )
//                            Snackbar.make(toggleReminder, reminderOff, Snackbar.LENGTH_SHORT)
//                                .setBackgroundTint(context.getColor(R.color.colorDarkGray))
//                                .setTextColor(context.getColor(R.color.colorWhite))
//                                .show()
//                        }
//                    }
//                    saveChange(isChecked)
//                }
//            }
        }
    }

    override fun onBackPressed() {
        finish()
        slideToRight()
    }

    private fun saveChange(value: Boolean) {
        val editor = sharedPreferences.edit()
        with(editor) {
            putBoolean(SWITCH_STATE, value)
            apply()
        }
    }

    private fun slideToLeft() {
        overridePendingTransition(
            R.anim.slide_from_right_animation,
            R.anim.slide_to_left_animation
        )
    }

    private fun slideToRight() {
        overridePendingTransition(
            R.anim.slide_from_left_animation,
            R.anim.slide_to_right_animation
        )
    }
}