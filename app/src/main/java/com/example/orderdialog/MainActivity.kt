package com.example.orderdialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Window
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_order.*


class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private lateinit var dialogOrder: Dialog
    private lateinit var timer: CountDownTimer
    var count = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        dialogOrder = Dialog(this, R.style.Theme_AppCompat_Light_Dialog_Alert)
        dialogOrder.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogOrder.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialogOrder.setContentView(R.layout.dialog_order)
        dialogOrder.setCanceledOnTouchOutside(false)
        dialogOrder.setCancelable(false)




    }

    override fun onResume() {
        super.onResume()

       timer =  object : CountDownTimer(10000, 1000) {


            @SuppressLint("SetTextI18n")


            override fun onTick(millisUntilFinished: Long) {

                dialogOrder.tvSecond.text =
                    " (${(millisUntilFinished / 1000).toString().replace(".", "")})s"

                ++count
                dialogOrder.progressBar.progress = count

            }

            override fun onFinish() {
                count = 0
                edit_get_interval.text.clear()
                dialogOrder.dismiss()

            }

        }.also { timer = it }



        btn_order.setOnClickListener {

            count = 0

            if (edit_get_interval.text.toString() != "") {
                dialogOrder.show()
                dialogOrder.progressBar.max = edit_get_interval.text.toString().toInt() / 1000


                dialogOrder.tvAccept.setOnClickListener {

                    dialogOrder.dismiss()
                    edit_get_interval.text.clear()
                    timer.cancel()

                }

                timer.start()

            } else {
                Toast.makeText(this, "Intervalni kiriting...", Toast.LENGTH_SHORT).show()
            }

        }
    }

}