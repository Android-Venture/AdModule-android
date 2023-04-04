package com.example.admanager.models

data class AdRequestParamModel(
    //TODO: Avoid using snake case instead use Camel case for kotlin and snake case for xml

    var banner_id: String? = null,
    var native_id: String?= null,
    var intersitial_id: String?= null,
    var app_open_ad_id: String?= null,
    var intersitial_capping:Int ? = null,
    var banner_ad_status : Boolean ? = null,
    var native_ad_status:Boolean ? = null,
    var intersitial_ad_status:Boolean ? = null,
    var app_open_ad_status:Boolean?=null

)