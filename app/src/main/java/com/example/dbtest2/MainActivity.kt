package com.example.dbtest2

//import android.R

//import android.R
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.sql.*
import java.util.jar.Manifest


class MainActivity : AppCompatActivity() {
    var etSql: EditText? = null
    private var connection: Connection? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //인터넷 퍼미션을 요구하도록 함
//        ActivityCompat.requestPermissions(
//            this,
//            arrayOf(Manifest.permission.INTERNET),
//            PackageManager.PERMISSION_GRANTED
//        )


        //레이아웃의 에디트텍스트를 가져옴
        etSql = findViewById<View>(R.id.sql_et) as EditText
        try {
            //연결시도
            Class.forName(Classes)
            connection = DriverManager.getConnection(url, username, password)
            //연결성공 후 SUCCESS문자 표시
            etSql!!.setText("SUCCESS")
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
            //실패시 에러 표시
            etSql!!.setText("ERROR")
        } catch (e: SQLException) {
            e.printStackTrace()
            etSql!!.setText("ERROR")
        }
    }

    fun sqlButton(view: View?) {
        if (connection != null) {
            var statement: Statement? = null
            try {
                statement = connection!!.createStatement()
                //쿼리문을 이용해 가져올 데이터값을 정한다.
                val resultSet =
                    statement.executeQuery("SELECT userName FROM userTbl WHERE userID = 'kim'")
                while (resultSet.next()) {
                    //가져온 모든 값들을 next()메소드로 모두 순회하여 띄우도록 한다.
                    etSql!!.setText(resultSet.getString(1))
                }
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        } else {
            etSql!!.setText("Connection is null")
        }
    }

    companion object {
        private const val ip = "127.0.0.1" //접속할 서버측의 IP, 현재는 로컬에서 진행하니 이 컴퓨터의 IP주소를 할당하면 된다.
        private const val port =
            "1433" //SQL 구성 관리자에서 TCP/IP 구성 중 모든 IP포트를 설정하는 구간에서 동적 포트를 적으면 된다.
        private const val Classes = "net.sourceforge.jtds.jdbc.Driver"
        private const val database = "androidDB" //접속할 데이터베이스 이름
        private const val username = "younsoo" //서버의 ID PW
        private const val password = "carloveff35"
        private const val url = "jdbc:jtds:sqlserver://" + ip + ":" + port + "/" + database
    }
}