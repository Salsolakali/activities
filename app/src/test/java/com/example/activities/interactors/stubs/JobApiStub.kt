package com.example.activities.interactors.stubs

import com.example.activities.features.home.domain.model.JobPosition
import java.util.*

object JobApiStub {
    val testServiceData: List<JobPosition>
        get() = listOf(getBeer())

    fun getBeer(): JobPosition {
        return JobPosition(
            "c25080a2-83ec-4f62-bd6c-ad8c88c76b10",
            "Full Time",
            "https://jobs.github.com/positions/c25080a2-83ec-4f62-bd6c-ad8c88c76b10",
            Date(),
            "CLMBR",
            "http://www.clmbr.com",
            "Denver",
            "Lead React Developer",
            "\\u003cp\\u003eCLMBR- Lead React Developer\\u003c/p\\u003e\\n\\u003cp\\u003eDenver, CO 80206\\u003c/p\\u003e\\n\\u003cp\\u003eThe Company \\u0026amp; Role\\u003c/p\\u003e\\n\\u003cp\\u003eWith a mission to revolutionize the climbing machine and empower every person to achieve more with greater efficiency, CLMBR has designed the most efficient, connected machine engineered with the end user in mind. CLMBR is built to support ",
            "\u003cp\u003e\u003ca href=\"mailto:haylee@clmbr.com\"\u003ehaylee@clmbr.com\u003c/a\u003e\u003c/p\u003e\n",
            "            https://jobs.github.com/rails/active_storage/blobs/eyJfcmFpbHMiOnsibWVzc2FnZSI6IkJBaHBBazJnIiwiZXhwIjpudWxsLCJwdXIiOiJibG9iX2lkIn19--6864b79a3a3687c7070833b0ae171ada2dcf40e9/CLMBR_LOGO_BLACK.png"
        )
    }
}