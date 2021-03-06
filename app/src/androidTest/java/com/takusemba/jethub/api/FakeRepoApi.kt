package com.takusemba.jethub.api

import com.takusemba.jethub.model.ReadMe
import com.takusemba.jethub.model.Repo

class FakeRepoApi : RepoApi {

  override suspend fun getRepo(owner: String, repo: String): Repo {
    return Repo.createRepo()
  }

  override suspend fun getReadMe(owner: String, repo: String): ReadMe {
    return ReadMe.createReadMe()
  }
}