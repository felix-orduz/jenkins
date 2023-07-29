projects["projects"].each { project ->
  folder("/${project["name"]}")
  project["repos"].each {
    repo ->
    if(repo["repoType"]=="bitbucket"){
      multibranchPipelineJob("/${project["name"]}/${repo["name"]}") {
        displayName(repo["name"])
        description(repo["decription"])
        branchSources {
          branchSource{
            source{
              bitbucket{
                id(repo["repoOwner"]+repo["repository"]+repo["name"])
                repoOwner(repo["repoOwner"])
                repository(repo["repository"])
                credentialsId(repo["credentialsId"])
                traits {
                  bitbucketBranchDiscovery{
                    strategyId(1)
                  }
                  bitbucketTagDiscovery{}
                }
              }
            }
          }
        }
        orphanedItemStrategy {
          discardOldItems {}
        }
        factory {
          remoteJenkinsFileWorkflowBranchProjectFactory {
            remoteJenkinsFile("pipelines/${repo["projectType"]}.Jenkinsfile")
            localMarker('Jenkinsfile.json')
            remoteJenkinsFileSCM {
              gitSCM {
                userRemoteConfigs    {
                  userRemoteConfig {
                    name('')
                    url('git@bitbucket.org:grupo_asd_dd/pipelines_jenkins.git')
                    refspec('')
                    credentialsId('Bitbucket-SSH')
                  }
                }
                branches {
                  branchSpec {
                    name('main')
                  }
                }
                browser {} // required, but doesn't require configuration
                gitTool('/usr/bin/env git')

                extensions {
                    excludeFromPoll()
                    excludeFromChangeSet()
                    // ignoreNotifyCommit()
                    // disableRemotePoll()

                } // or wherever makes sense
              }
            }
          }
        }
      }
    }
  }
}
