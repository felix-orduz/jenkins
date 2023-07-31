import groovy.json.JsonSlurper

def createJob(pipelineName, application){
    print("New Application");
    print(application);
    multibranchPipelineJob(pipelineName) {
        displayName(application["name"]);
        description(application["description"]);

        branchSources {
            github {
                id(pipelineName)
                repoOwner(application['repoOwner'])
                repository(application['respository'])
            }
        }

        factory {
            remoteJenkinsFileWorkflowBranchProjectFactory{
                remoteJenkinsFile("pipelines/nodejs.groovy")
                localMarker("README.md")
                remoteJenkinsFileSCM {
                    gitSCM {
                        userRemoteConfigs {
                            userRemoteConfig {
                                name("")
                                url("https://github.com/felix-orduz/jenkins.git")
                                refspec("")
                                credentialsId("")
                            }
                        }
                        branches {
                            branchSpec {
                                name('main')
                            }
                        }
                        browser {} // required, but doesn't require configuration
                        gitTool('git')

                        extensions {
                            excludeFromPoll()
                            //excludeFromPoll()
                            excludeFromChangeSet()
                            // ignoreNotifyCommit()
                            // disableRemotePoll()

                        }
                    }
                }
            }
        }

        orphanedItemStrategy {
            discardOldItems {
                numToKeep(20)
            }
        }
    }
}

def createFolder(name, displayNameValue, descriptionValue){

    println("Creating folder");

    folder(name) {

        if(displayNameValue != null ){
            displayName(displayNameValue);
        }

        if(descriptionValue != null ){
            description(descriptionValue);
        }
    }
}

def releaseScript = readFileFromWorkspace('JDSL/projects.json');
def config = new JsonSlurper().parseText(releaseScript);
println(config)
config["systems"].each { system ->

    createFolder(system['name'], system['displayName'], system['description']);

    system["aplications"].each{ application  ->

        createJob("/${system['name']}/${application["name"]}", application);

    }
}
