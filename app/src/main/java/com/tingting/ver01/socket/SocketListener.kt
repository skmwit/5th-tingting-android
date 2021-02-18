package com.tingting.ver01.socket

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.tingting.ver01.view.Auth.LoginActivity
import com.tingting.ver01.view.Main.MainActivity
import io.socket.client.Manager
import io.socket.emitter.Emitter
import org.json.JSONException


class SocketListener(){

        var sockManager = Manager()

        val onConnect : Emitter.Listener = Emitter.Listener {

            var data   = JsonObject()
            data.addProperty("userId" ,"1")
            MainActivity.msocket.emit("enroll", data)
            Log.d("socketConnect","connect2")
        }



    val onReConnect= Emitter.Listener { args ->

        Log.d("reconnect33","reconnect")

     //LoginActivity.msocket  = IO.socket("http://13.209.77.221");
    //    LoginActivity.msocket.connect()
        //sockManager.reconnectionDelay(2000)

        sockManager.reconnection()

        val data = socketData("1")
        MainActivity.msocket.emit("enroll", Gson().toJson(data))
        Log.d("testEmmit22", Gson().toJson(data).toString())



    }

    val onReLoad = Emitter.Listener {
        args ->
     //   LoginActivity.msocket  = IO.socket("http://13.209.77.221");
        MainActivity.msocket.connect()

        val data = socketData("1")
        MainActivity.msocket.emit("enroll", Gson().toJson(data))
        Log.d("testEmmit", Gson().toJson(data).toString())
        Log.d("reconnect22","reconnect")
    }






}