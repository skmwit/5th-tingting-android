package com.tingting.ver01.model.profile
import androidx.annotation.Keep


@Keep
data class LookMyTeamInfoProfileResponse(
          val `data`: Data = Data()
) {
          @Keep
          data class Data(
                    val teamInfo: TeamInfo = TeamInfo(),
                    val teamMatchings: List<TeamMatching> = listOf(),
                    val teamMembers: List<TeamMember> = listOf()
          ) {
                    @Keep
                    data class TeamInfo(
                              val chat_address: String = "", // 2k3qioeiqoi
                              val gender: Int = 0, // 1
                              val id: Int = 0, // 17
                              val tags: List<String> = listOf(), // "술게임 선호"
                              val is_verified: Int = 0, // 1
                              val max_member_number: Int = 0, // 2
                              val name: String = "", // 여자팀4
                              val owner_id: Int = 0, // 32
                              val place: String = ""
                    )

                    @Keep
                    data class TeamMatching(
                              val id: Int = 0, // 6
                              val sendTeam: SendTeam = SendTeam()
                    ) {
                              @Keep
                              data class SendTeam(
                                        val id: Int = 0, // 16
                                        val max_member_number: Int = 0, // 2
                                        val membersInfo: List<MembersInfo> = listOf(),
                                        val name: String = "", // 남자팀3
                                        val owner_id: Int = 0, // 28
                                        val place: String = ""
                              ) {
                                        @Keep
                                        data class MembersInfo(
                                                  val id: Int = 0, // 28
                                                  val name: String = "", // 팡팡
                                                  val thumbnail: String = "" // s222fsvsefsedsv.png
                                        )
                              }
                    }

                    @Keep
                    data class TeamMember(
                              val id: Int = 0, // 32
                              val name: String = "", // 텐텐
                              val thumbnail: String = "" // dtt234234edsv.png
                    )
          }
}