import groovy.json.JsonSlurper

def createFolder(name, displayNameValue, descriptionValue){

    println("Creating folder");

    folder(name) {

        if(displayNameValue != null ){
            displayName(displayNameValue)
        }

        if(descriptionValue != null ){
            description(descriptionValue)
        }
    }
}

def releaseScript = readFileFromWorkspace('JDSL/projects.json')

def config = new JsonSlurper().parseText(releaseScript)
println(config)
config["systems"].each { system ->

    createFolder(system['name'], system['displayName'], system['description']);

    config["aplications"].each{ application  ->

        print("New Application");
        print(application);
        multibranchPipelineJob("/${system['name']}/${application["name"]}") {
            displayName(application["name"])
            description(application["decription"])
        }

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
