package com.vivek.githubassignment.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName

data class Repo(
    @PrimaryKey var id: Int,
    var node_id: String,
    var name: String,
    var full_name: String,
    @Embedded var owner: Owner?,
    var private: Boolean,
    var html_url: String,
    var description: String?,
    var fork: Boolean,
    var url: String,
    var created_at: String,
    var updated_at: String,
    var pushed_at: String,
    var homepage: String?,
    var size: Int,
    var stargazers_count: Int,
    var watchers_count: Int,
    var language: String?,
    var forks_count: Int,
    var open_issues_count: Int,
    var master_branch: String,
    var default_branch: String,
    var score: Double,
    var visibility: String,
    @Embedded var license: License?,
    var topics: List<String> = emptyList(), // Default to empty list
    var forks_url: String,
    var keys_url: String,
    var collaborators_url: String,
    var teams_url: String,
    var hooks_url: String,
    var issue_events_url: String,
    var events_url: String,
    var assignees_url: String,
    var branches_url: String,
    var tags_url: String,
    var blobs_url: String,
    var git_tags_url: String,
    var git_refs_url: String,
    var trees_url: String,
    var statuses_url: String,
    var languages_url: String,
    var stargazers_url: String,
    var contributors_url: String,
    var subscribers_url: String,
    var subscription_url: String,
    var commits_url: String,
    var git_commits_url: String,
    var comments_url: String,
    var issue_comment_url: String,
    var contents_url: String,
    var compare_url: String,
    var merges_url: String,
    var archive_url: String,
    var downloads_url: String,
    var issues_url: String,
    var pulls_url: String,
    var milestones_url: String,
    var notifications_url: String,
    var labels_url: String,
    var releases_url: String,
    var deployments_url: String,
    var git_url: String,
    var ssh_url: String,
    var clone_url: String,
    var svn_url: String,
    var forks: Int,
    var open_issues: Int,
    var watchers: Int,
    var mirror_url: String?,
    var has_issues: Boolean,
    var has_projects: Boolean,
    var has_pages: Boolean,
    var has_wiki: Boolean,
    var has_downloads: Boolean,
    var has_discussions: Boolean,
    var archived: Boolean,
    var disabled: Boolean,
    var allow_merge_commit: Boolean,
    var allow_squash_merge: Boolean,
    var allow_rebase_merge: Boolean,
    var allow_auto_merge: Boolean,
    var delete_branch_on_merge: Boolean,
    var allow_forking: Boolean,
    var is_template: Boolean,
    var web_commit_signoff_required: Boolean
) {
    companion object {
        var nullObject = Repo(
            id = -1,
            node_id = "",
            name = "No Repo",
            full_name = "No Repo",
            owner = null,
            private = false,
            html_url = "",
            description = null,
            fork = false,
            url = "",
            created_at = "",
            updated_at = "",
            pushed_at = "",
            homepage = null,
            size = 0,
            stargazers_count = 0,
            watchers_count = 0,
            language = null,
            forks_count = 0,
            open_issues_count = 0,
            master_branch = "",
            default_branch = "",
            score = 0.0,
            visibility = "public",
            license = null,
            topics = emptyList(),
            forks_url = "",
            keys_url = "",
            collaborators_url = "",
            teams_url = "",
            hooks_url = "",
            issue_events_url = "",
            events_url = "",
            assignees_url = "",
            branches_url = "",
            tags_url = "",
            blobs_url = "",
            git_tags_url = "",
            git_refs_url = "",
            trees_url = "",
            statuses_url = "",
            languages_url = "",
            stargazers_url = "",
            contributors_url = "",
            subscribers_url = "",
            subscription_url = "",
            commits_url = "",
            git_commits_url = "",
            comments_url = "",
            issue_comment_url = "",
            contents_url = "",
            compare_url = "",
            merges_url = "",
            archive_url = "",
            downloads_url = "",
            issues_url = "",
            pulls_url = "",
            milestones_url = "",
            notifications_url = "",
            labels_url = "",
            releases_url = "",
            deployments_url = "",
            git_url = "",
            ssh_url = "",
            clone_url = "",
            svn_url = "",
            forks = 0,
            open_issues = 0,
            watchers = 0,
            mirror_url = null,
            has_issues = false,
            has_projects = false,
            has_pages = false,
            has_wiki = false,
            has_downloads = false,
            has_discussions = false,
            archived = false,
            disabled = false,
            allow_merge_commit = false,
            allow_squash_merge = false,
            allow_rebase_merge = false,
            allow_auto_merge = false,
            delete_branch_on_merge = false,
            allow_forking = false,
            is_template = false,
            web_commit_signoff_required = false
        )
    }
}

data class Owner(
    @PrimaryKey @SerializedName("id") var owner_id: Int,
    @SerializedName("login") var owner_login: String,
    @SerializedName("node_id") var owner_node_id: String,
    @SerializedName("avatar_url") var owner_avatar_url: String,
    @SerializedName("html_url") var owner_html_url: String,
    @SerializedName("type") var owner_type: String,
    @SerializedName("site_admin") var owner_site_admin: Boolean
) {
    companion object {
        var nullObject = Owner(
            owner_id = -1,
            owner_login = "Unknown",
            owner_node_id = "",
            owner_avatar_url = "",
            owner_html_url = "",
            owner_type = "User",
            owner_site_admin = false
        )
    }
}

data class License(
    @PrimaryKey @SerializedName("id") var license_key: String,
    @SerializedName("name") var license_name: String,
    @SerializedName("url") var license_url: String?,
    @SerializedName("spdx_id") var license_spdx_id: String?,
    @SerializedName("node_id") var license_node_id: String,
    @SerializedName("html_url") var license_html_url: String
) {
    companion object {
        var nullObject = License(
            license_key = "no_key",
            license_name = "No License",
            license_url = null,
            license_spdx_id = null,
            license_node_id = "no_node_id",
            license_html_url = "https://example.com/no_license"
        )
    }
}

//class Converters {
//    @TypeConverter
//    fun fromOwnerToString(owner: Owner?): String? {
//        return owner?.let {
//            "${it.owner_id},${it.owner_login},${it.owner_node_id},${it.owner_avatar_url},${it.owner_html_url},${it.owner_type},${it.owner_site_admin}"
//        }
//    }
//
//    @TypeConverter
//    fun fromStringToOwner(data: String?): Owner? {
//        return data?.let {
//            var parts = it.split(",")
//            Owner(
//                owner_id = parts[0].toInt(),
//                owner_login = parts[1],
//                owner_node_id = parts[2],
//                owner_avatar_url = parts[3],
//                owner_html_url = parts[4],
//                owner_type = parts[5],
//                owner_site_admin = parts[6].toBoolean()
//            )
//        }
//    }
//
//    @TypeConverter
//    fun fromLicenseToString(license: License?): String? {
//        return license?.let {
//            "${it.license_key},${it.license_name},${it.license_url ?: ""},${it.license_spdx_id ?: ""},${it.license_node_id},${it.license_html_url}"
//        }
//    }
//
//    @TypeConverter
//    fun fromStringToLicense(data: String?): License? {
//        return data?.let {
//            var parts = it.split(",")
//            License(
//                license_key = parts[0],
//                license_name = parts[1],
//                license_url = parts[2],
//                license_spdx_id = parts[3],
//                license_node_id = parts[4],
//                license_html_url = parts[5]
//            )
//        }
//    }
//
//    @TypeConverter
//    fun fromTopicsList(topics: List<String>): String {
//        return topics.joinToString(",")
//    }
//
//    @TypeConverter
//    fun toTopicsList(data: String): List<String> {
//        return data.split(",").map { it.trim() }
//    }
//}
