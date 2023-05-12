package com.example.sqlearning

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import com.example.sqlearning.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {
    lateinit var binding : ActivityUpdateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val email = intent.getStringExtra("email")!!
        val nama = intent.getStringExtra("nama")!!
        val nim = intent.getStringExtra("nim")!!
        val password = intent.getStringExtra("password")!!

        val mahasiswa  = Mahasiswa(email, nama, nim, password)

        binding.inputEmail.setText(mahasiswa.email)
        binding.inputNama.setText(mahasiswa.nama)
        binding.inputNim.setText(mahasiswa.nim)
        binding.inputPassword.setText(mahasiswa.password)

        binding.btnDelete.setOnClickListener(){
            val db = MahasiswaHelper(this)
            db.hapusData(mahasiswa.email)
            setResult(Activity.RESULT_OK)
            finish()
        }

        binding.btnUpdate.setOnClickListener(){
            val updateMahasiswa = Mahasiswa(
                binding.inputEmail.text.toString(),
                binding.inputNama.text.toString(),
                binding.inputNim.text.toString(),
                binding.inputPassword.text.toString()
            )
            val db = MahasiswaHelper(this)
            db.updateData(updateMahasiswa)
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home -> {
                onBackPressed()
                true
            }else -> super.onOptionsItemSelected(item)
        }
    }
}