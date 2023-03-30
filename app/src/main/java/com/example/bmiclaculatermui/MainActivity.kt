package com.example.bmiclaculatermui


import android.app.AlertDialog


import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View

import android.widget.Toast

import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.example.bmiclaculatermui.databinding.ActivityMainBinding




class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var isclear: Boolean = false
    private lateinit var viewmodel:ViewModelActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewmodel =ViewModelProvider(this)[(ViewModelActivity::class.java)]
        viewmodel.value01.observe(this, Observer {
            binding.textview1.text=it.toString()
        })
        viewmodel.text.observe(this, Observer {
            binding.textview2.text=it.toString()
        })
        viewmodel.message.observe(this, Observer {
            Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
        })


        binding.calculate.setOnClickListener(this)
    }
    override fun onClick(View: View?) {

        when (View?.id) {
            R.id.calculate -> {
//                if (binding.hight.editableText.isEmpty() && binding.weight.editableText.isEmpty()) {
//                    binding.hight.requestFocus()
//                    Toast.makeText(this, "please Enter weight and height", Toast.LENGTH_SHORT)
//                        .show()
//                } else if (binding.hight.editableText.isEmpty()) {
//                    Toast.makeText(this, "please enter the hieght", Toast.LENGTH_SHORT).show()
//                } else if (binding.weight.editableText.isEmpty()) {
//                    binding.weight.requestFocus()
//                    Toast.makeText(this, "please Enter the weight", Toast.LENGTH_SHORT).show()
//                }

                if (isclear) {
                    isclear = false
                    binding.calculate.text = "calculate"
                    binding.textview1.setText("")
                    binding.textview2.setText("")
                    binding.weight.isEnabled = true
                    binding.hight.isEnabled = true
                    binding.hight.editableText.clear()
                    binding.weight.editableText.clear()

                    Toast.makeText(this, "clear now", Toast.LENGTH_SHORT).show()
                }
//
                else {
                    if (binding.hight.text!!.isNotEmpty() && binding.weight.text!!.isNotEmpty()) {
                        if (!isclear) {
                            isclear = true
                            binding.calculate.setText("clear")
                            binding.weight.isEnabled = false
                            binding.hight.isEnabled = false


                        }
                    }
                    viewmodel.calculatebmi()

                }
            }


        }

    }

        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            val inflater = menuInflater
            inflater.inflate(R.menu.item, menu)

            return super.onCreateOptionsMenu(menu)
        }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {

            when (item.itemId) {
                R.id.item1 -> {
                    val intent = Intent(this, thirdActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "about developer", Toast.LENGTH_SHORT).show()
                    return true
                }
                R.id.item2 -> {
                    val intent = Intent(this, secoundActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "BMI Chart", Toast.LENGTH_SHORT).show()
                    return true
                }


                R.id.item4 -> {
                   // val intent = Intent(this, secoundActivity::class.java)
                   // startActivity(intent)
                    Toast.makeText(this, "contect to user", Toast.LENGTH_SHORT).show()
                    return true
                }


                R.id.item5 -> {

                    Toast.makeText(this, "what is BMI", Toast.LENGTH_SHORT).show()



                  val intent=Intent(this,webview::class.java)
                    startActivity(intent)




                }




                R.id.subitem1 -> {
                    // val intent = Intent(this, secoundActivity::class.java)
                    // startActivity(intent)
                    Toast.makeText(this, "Dail phone no. of developer", Toast.LENGTH_SHORT).show()

                    val intent=Intent(Intent.ACTION_DIAL, Uri.parse("tel:7570077927"))
                    startActivity(intent)
                    return true
                }




                R.id.subitem2 -> {
                    // val intent = Intent(this, secoundActivity::class.java)
                    // startActivity(intent)
                    Toast.makeText(this, "calling to user", Toast.LENGTH_SHORT).show()


                    if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.CALL_PHONE)==PackageManager.PERMISSION_GRANTED){

                        val intent=Intent(Intent.ACTION_CALL)
                        intent.data=Uri.parse("tel:6387511508")
                        startActivity(intent)


                    }
                    else{
                        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE), 1001)
                    }
                }





                R.id.subitem3 -> {
                    Toast.makeText(this, "send to email", Toast.LENGTH_SHORT).show()

                    val intent=Intent(Intent.ACTION_SENDTO).apply {
                        data= Uri.parse("mailto:")
                        putExtra(Intent.EXTRA_EMAIL, arrayOf("abhishekshuklagola82@gmail.com"))
                        putExtra(Intent.EXTRA_SUBJECT,"Related to BMI app")
                    }
                    startActivity(intent)

                }











            }


            return super.onOptionsItemSelected(item)
        }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode==1001){
            if(grantResults.isNotEmpty() && permissions[0].equals(PackageManager.PERMISSION_GRANTED)){

            }else{
                Toast.makeText(this, "Please give permission", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBackPressed() {

       val alertDialog= AlertDialog.Builder(this)
        alertDialog.setTitle(resources.getString(R.string.app_name))
        alertDialog.setMessage("Do you want to exit?")
        alertDialog.setPositiveButton("Exit",object:DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, p1: Int) {

             finish()

            }

        })

        alertDialog.setNegativeButton("No",object:DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, p1: Int) {

            }

        })
        var alert =alertDialog.create()
        alertDialog.show()

            
        }
    }












