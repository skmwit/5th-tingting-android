package com.tingting.ver01.model.team.UpdateTeam

data class UpdateMyTeaminfo(val place:String, val password : String , val gender:Int, val name:String, val max_memeber_number : Int
, val tagIds : List<Int>, val chat_address:String)