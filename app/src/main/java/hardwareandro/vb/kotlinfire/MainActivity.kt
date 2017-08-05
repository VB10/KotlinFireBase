package hardwareandro.vb.kotlinfire

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

     private var  mdatabase:FirebaseDatabase?=null
    private  var mRef : DatabaseReference ?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mdatabase= FirebaseDatabase.getInstance()
        mRef= mdatabase!!.getReference("hardware")

        mRef!!.child("hardware").child("andro").
                addValueEventListener(object:ValueEventListener{
                    override fun onCancelled(p0: DatabaseError?) {

                    }

                    override fun onDataChange(p0: DataSnapshot?) {
                        var value:String = p0!!.getValue() as String
                        txts.setText(value)
                    }

                })






        }



public fun sendMessage(view: View){

        mRef!!.child("hardware").child("andro").push().setValue(et_msg.text.toString())


    }
}
