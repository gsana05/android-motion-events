package com.example.motionevents

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.example.motionevents.models.User
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.okButton
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val user1 = User("Gareth", 28, "Software developer")
        val user2 = User("Paul", 45, "Doctor")
        val user3 = User("Luke", 21, "Butcher")
        val user4 = User("Dan", 66, "retired")

        text_view_1.text = user1.name
        text_view_2.text = user2.name
        text_view_3.text = user3.name
        text_view_4.text = user4.name

        val TAG = "ZoomLayout"
        var downX = 0f
        var downY = 0f

        text_view_1.setOnTouchListener(View.OnTouchListener { v, event ->


            Log.d("TAGGGG", "onTouch entered here ${event.action}")

            when (event.action) {
                MotionEvent.ACTION_UP -> {
                    Log.d(TAG, "page model up")
                    //textView.setOnTouchListener(null)

                    val x = event.rawX
                    val y = event.rawY

                    val touchRadius = sqrt((x - downX).pow(2) + (y - downY).pow(2))

                    if (touchRadius > 10f) {
                        //textView.setOnTouchListener(null);
                        Log.d(TAG, "page model false")
                        false
                    } else {
                        //textView.setOnTouchListener(null);
                        Log.d(TAG, "page model true")
                        alert(user1.name) {
                            okButton { }
                        }.show()
                        true
                    }

                }
                MotionEvent.ACTION_DOWN -> {
                    Log.d(TAG, "page model down")

                    downX = event.rawX
                    downY = event.rawY
                    true
                }
                else -> {
                    Log.d("TAGGGG", "nothing")
                    true
                }
            }
        })

        text_view_2.setOnTouchListener(View.OnTouchListener { v, event ->


            Log.d("TAGGGG", "onTouch entered here ${event.action}")

            when (event.action) {
                MotionEvent.ACTION_UP -> {
                    Log.d(TAG, "page model up")
                    //textView.setOnTouchListener(null)

                    val x = event.rawX
                    val y = event.rawY

                    val touchRadius = sqrt((x - downX).pow(2) + (y - downY).pow(2))

                    if (touchRadius > 10f) {
                        //textView.setOnTouchListener(null);
                        Log.d(TAG, "page model false")
                        false
                    } else {
                        //textView.setOnTouchListener(null);
                        Log.d(TAG, "page model true")
                        alert(user2.name) {
                            okButton { }
                        }.show()
                        true
                    }

                }
                MotionEvent.ACTION_DOWN -> {
                    Log.d(TAG, "page model down")

                    downX = event.rawX
                    downY = event.rawY
                    true
                }
                else -> {
                    Log.d("TAGGGG", "nothing")
                    true
                }
            }
        })

        text_view_3.setOnTouchListener(View.OnTouchListener { v, event ->


            Log.d("TAGGGG", "onTouch entered here ${event.action}")

            when (event.action) {
                MotionEvent.ACTION_UP -> {
                    Log.d(TAG, "page model up")
                    //textView.setOnTouchListener(null)

                    val x = event.rawX
                    val y = event.rawY

                    val touchRadius = sqrt((x - downX).pow(2) + (y - downY).pow(2))

                    if (touchRadius > 10f) {
                        //textView.setOnTouchListener(null);
                        Log.d(TAG, "page model false")
                        false
                    } else {
                        //textView.setOnTouchListener(null);
                        Log.d(TAG, "page model true")
                        alert(user3.name) {
                            okButton { }
                        }.show()
                        true
                    }

                }
                MotionEvent.ACTION_DOWN -> {
                    Log.d(TAG, "page model down")

                    downX = event.rawX
                    downY = event.rawY
                    true
                }
                else -> {
                    Log.d("TAGGGG", "nothing")
                    true
                }
            }
        })

        text_view_4.setOnTouchListener(View.OnTouchListener { v, event ->


            Log.d("TAGGGG", "onTouch entered here ${event.action}")

            when (event.action) {
                MotionEvent.ACTION_UP -> {
                    Log.d(TAG, "page model up")
                    //textView.setOnTouchListener(null)

                    val x = event.rawX
                    val y = event.rawY

                    val touchRadius = sqrt((x - downX).pow(2) + (y - downY).pow(2))

                    if (touchRadius > 10f) {
                        //textView.setOnTouchListener(null);
                        Log.d(TAG, "page model false")
                        false
                    } else {
                        //textView.setOnTouchListener(null);
                        Log.d(TAG, "page model true")
                        alert(user4.name) {
                            okButton { }
                        }.show()
                        true
                    }

                }
                MotionEvent.ACTION_DOWN -> {
                    Log.d(TAG, "page model down")

                    downX = event.rawX
                    downY = event.rawY
                    true
                }
                else -> {
                    Log.d("TAGGGG", "nothing")
                    true
                }
            }
        })

    }
}