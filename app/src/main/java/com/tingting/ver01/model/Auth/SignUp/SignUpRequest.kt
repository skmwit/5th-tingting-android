package com.tingting.ver01.model.Auth.SignUp


data class SignUpRequest(val local_id:String, val password :String, val gender:String,
                         val name:String, val birth:String, val authenticated_address : String, val height:String)
