// String basePath = 'example1'
// String repo = 'sheehan/grails-example'

// folder(basePath) {
//     description 'This example shows basic folder/job creation.'
// }

import utilities.MyUtilities
import groovy.json.JsonSlurper

def create_folder

def releaseScript = readFileFromWorkspace('JDSL/projects.json')
// ${WORKSPACE}
// hudson.FilePath workspace = hudson.model.Executor.currentExecutor().getCurrentWorkspace()
def config = new JsonSlurper().parseText(releaseScript)
println(config)
config["systems"].each { system ->
    folder(system['name']) {

        if(system['displayName'] !== null ){
            displayName(system['displayName'])
        }

        description(system['description'])
    }
}
// config["organizations"].each { org ->
//   org["repositories"].each { repo ->
//     multibranchPipelineJob("${org["name"]}_${repo}") {
//       orphanedItemStrategy {
//         discardOldItems {
//           daysToKeep(365) // keep one year
//         }
//       }

//       branchSources {
//         github {
//           id("${org["name"]}_${repo}")                    // create a unique ID, see below
//           apiUri('https://git.corp.company.tild/api/v3')  // this is for a GH:E, no need to specify for public GH
//           scanCredentialsId("${org['user']}_git_token")   // this is the ID of the token in jenkins credentials
//           repoOwner(org['name'])
//           repository(repo)
//         }
//       }
//     }
//   }
// }
