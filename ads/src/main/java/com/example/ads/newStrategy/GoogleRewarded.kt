package com.example.ads.newStrategy

import android.content.Context
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import java.util.Stack


class GoogleRewarded(context: Context?) {
    private val totalLevels = 4
    private var adUnits: ArrayList<ArrayList<Any>>? = null
    private val reward5 = "ca-app-pub-9507635869843997/2420894293"
    private val reward4 = "ca-app-pub-9507635869843997/4344350059"
    private val rewardHigh = "ca-app-pub-9507635869843997/1307123172"
    private val rewardMed = "ca-app-pub-9507635869843997/9596676735"
    private val rewardAll = "ca-app-pub-9507635869843997/5845133985"

    init {
        instantiateList()
        loadInitialRewards(context)
    }

    private fun instantiateList() {
        adUnits = ArrayList()
        adUnits!!.add(0, ArrayList(listOf(reward5, Stack<RewardedAd>())))
        adUnits!!.add(1, ArrayList(listOf(reward4, Stack<RewardedAd>())))
        adUnits!!.add(2, ArrayList(listOf(rewardHigh, Stack<RewardedAd>())))
        adUnits!!.add(3, ArrayList(listOf(rewardMed, Stack<RewardedAd>())))
        adUnits!!.add(4, ArrayList(listOf(rewardAll, Stack<RewardedAd>())))
    }

    fun loadInitialRewards(context: Context?) {
        RewardedAdLoad(context, totalLevels)
    }

    fun getMediumAd(activity: Context?): RewardedAd? {
        return getRewardedAd(activity, 1)
    }

    fun getHighFloorAd(activity: Context?): RewardedAd? {
        return getRewardedAd(activity, 2)
    }

    fun getDefaultAd(activity: Context?): RewardedAd? {
        return getRewardedAd(activity, 0)
    }

    fun getRewardedAd(activity: Context?, maxLevel: Int): RewardedAd? {
        for (i in totalLevels downTo 0) {
            if (maxLevel > i) {
                break
            }
            val list = adUnits!![i]
            val adunitid = list[0] as String
            val stack = list[1] as Stack<RewardedAd>
            RewardedAdLoadSpecific(activity, adunitid, stack)
            if (stack != null && !stack.isEmpty()) {
                return stack.pop()
            }
        }
        return null
    }

    fun RewardedAdLoad(activity: Context?, level: Int) {
        if (level < 0) {
            return
        }
        if (adUnits!!.size < level) {
            return
        }
        val list = adUnits!![level]
        val adunitid = list[0] as String
        val stack = list[1] as Stack<RewardedAd>
        val adRequest = AdRequest.Builder().build()
        RewardedAd.load(activity, adunitid, adRequest, object : RewardedAdLoadCallback() {
            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                super.onAdFailedToLoad(loadAdError)
                RewardedAdLoad(activity, level - 1)
            }

            override fun onAdLoaded(rewardedAd: RewardedAd) {
                super.onAdLoaded(rewardedAd)
                stack.push(rewardedAd)
            }
        })
    }

    fun RewardedAdLoadSpecific(activity: Context?, adUnitId: String?, stack: Stack<RewardedAd>?) {
        val adRequest = AdRequest.Builder().build()
        RewardedAd.load(activity, adUnitId, adRequest, object : RewardedAdLoadCallback() {
            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                super.onAdFailedToLoad(loadAdError)
            }

            override fun onAdLoaded(rewardedAd: RewardedAd) {
                super.onAdLoaded(rewardedAd)
                stack!!.push(rewardedAd)
            }
        })
    }
}