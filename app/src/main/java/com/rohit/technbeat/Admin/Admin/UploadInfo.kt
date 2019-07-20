package com.rohit.technbeat.Admin.Admin
class UploadInfo {

    var name: String = ""
    var url: String = ""

    constructor() {}

    constructor(name: String, url: String) {
        this.name = name
        this.url = url
    }
}