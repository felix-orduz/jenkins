import groovy.json.JsonSlurper

def createFolder(){
    println("Creating folder");
}

def releaseScript = readFileFromWorkspace('JDSL/projects.json')

def config = new JsonSlurper().parseText(releaseScript)
println(config)
config["systems"].each { system ->

    createFolder();

    folder(system['name']) {

        if(system['displayName'] != null ){
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
