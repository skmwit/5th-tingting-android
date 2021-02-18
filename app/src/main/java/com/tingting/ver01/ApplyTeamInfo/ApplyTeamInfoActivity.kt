package com.tingting.ver01.ApplyTeamInfo

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.tingting.ver01.BR
import com.tingting.ver01.R
import com.tingting.ver01.databinding.ActivityApplyTeamInfoBinding
import com.tingting.ver01.model.CodeCallBack
import com.tingting.ver01.model.ModelMatching
import com.tingting.ver01.model.ModelTeam
import com.tingting.ver01.model.matching.ShowAppliedTeamInfoResponse
import com.tingting.ver01.model.team.lookIndivisualTeam.IndivisualTeamResponse
import com.tingting.ver01.searchTeam.searchTeamMemberInfo.SearchTeamMemberInfoAdapter
import com.tingting.ver01.teamInfo.TeamInfoRecyclerViewMargin
import com.tingting.ver01.viewModel.ApplyTeamInfoViewModel
import kotlinx.android.synthetic.main.activity_search_team_info.*

class ApplyTeamInfoActivity : AppCompatActivity() {

    lateinit var applyTeamMemberAdapter: ApplyTeamInfoAdapter
    lateinit var dataBinding: ActivityApplyTeamInfoBinding

    var otherTeamId = 0
    var myTeamId = 0
    var matchingNumber = 0
    var isAccepted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_apply_team_info)

        dataBinding.viewmodel =
            ViewModelProviders.of(this).get(ApplyTeamInfoViewModel::class.java)

        // 처리
        val size = resources.getDimensionPixelSize(R.dimen.wide_size)

        otherTeamId = intent.getIntExtra("matchingTeamId", 0)
        myTeamId = intent.getIntExtra("myTeamId", 0)
        matchingNumber = intent.getIntExtra("matchingId", 0)
        isAccepted = intent.getBooleanExtra("acceptValue",false)
        dataBinding.viewmodel?.fetchData(otherTeamId,myTeamId)

        dataBinding.lifecycleOwner = this


        // back button event
        dataBinding.back.setOnClickListener {
            finish()
        }
        //Edit Team info button click

        //init screen
        dataBinding.acceptBtn.setOnClickListener {

            ModelMatching.getInstance().receiveHeart(otherTeamId, object:CodeCallBack{
                override fun onSuccess(code: String, value: String) {
                    try{
                        if(code.equals("201")){
                            Toast.makeText(applicationContext, "매칭 신청하기 성공", Toast.LENGTH_LONG).show()
                        }
                        else if(code.equals("400")){
                            Toast.makeText(applicationContext, "매칭 정보가 없거나 이미 전원이 하트를 보냈습니다!", Toast.LENGTH_LONG).show()

                        }else if(code.equals("403")){
                            Toast.makeText(applicationContext, "팀에 속해있지 않습니다!", Toast.LENGTH_LONG).show()
                        }
                        else if(code.equals("500")){
                            Toast.makeText(applicationContext, "매칭 신청하기 실패", Toast.LENGTH_LONG).show()

                        }
                    }catch (e:Exception){
                    }
                }
            })
        }
        setAcceptBtnVisible(isAccepted)
        setObserver()
        setApdater()

    }


    fun setObserver() {
        dataBinding.viewmodel?.applyTeamData?.observe(this, Observer {
            applyTeamMemberAdapter.update(it.data)
            initview(it)
        })
    }

    fun setApdater() {
        var data = dataBinding.viewmodel

        if (data != null) {
            val deco = TeamInfoRecyclerViewMargin(10)
            teamRecyclerView.addItemDecoration(deco)

            applyTeamMemberAdapter = ApplyTeamInfoAdapter(dataBinding.viewmodel!!, this)
            val layoutManager = GridLayoutManager(this, 2)
            teamRecyclerView.layoutManager = layoutManager
            teamRecyclerView.adapter = applyTeamMemberAdapter
        }
    }

    fun initview(item : ShowAppliedTeamInfoResponse) {
        dataBinding.setVariable(BR.teaminfoItem,item)
        dataBinding.executePendingBindings()

        var gender = item.data.teamInfo.gender
        var maxNum = item.data.teamInfo.max_member_number


        dataBinding.totalNumberInfo.text = maxNum.toString() + ":" + maxNum.toString()
    }

    fun setAcceptBtnVisible(acceptValue : Boolean){
        if(acceptValue){
            dataBinding.acceptBtn.visibility = View.GONE
        }else{
            dataBinding.acceptBtn.visibility = View.VISIBLE
        }

    }
}


