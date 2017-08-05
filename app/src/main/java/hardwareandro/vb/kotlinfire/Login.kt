package hardwareandro.vb.kotlinfire

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    //kişileri öğreneceğimiz yapı
    private  var mAuth:FirebaseAuth ?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        //firebase ile bağlayıp bilgileri çekiyoruz
        mAuth= FirebaseAuth.getInstance()
    }

      fun sendLogin(view: View){

        mAuth!!.signInWithEmailAndPassword(txtEmail.text.toString(),txtPassword.text.toString())
                .addOnCompleteListener(this){
                    task ->

                    if (task.isSuccessful){

                        //eğer user varsa sistemde direk login
                    var intent = Intent(this,MainActivity::class.java)
                        startActivity(intent)


                    }
                    else {
                        //eğer kullanıcı yoksa yeni kayıt oluşturuyoruz
                        CreateUser(txtEmail.text.toString(),txtPassword.text.toString())

                    }
                }


    }


      fun CreateUser(email: String,password: String ){
        mAuth!!.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this){
                    task ->
                    if (task.isSuccessful){
                        //gelen mail ve şifreye göre kayıt oluşturuyoruz
                        Toast.makeText(applicationContext,"Yeni kayıt oluşturuldu",Toast.LENGTH_SHORT)

                    }
                    else{
                        //internet vs olmama durumundaki hata
                        Toast.makeText(applicationContext,"hata",Toast.LENGTH_LONG)

                    }
                }
    }
}
