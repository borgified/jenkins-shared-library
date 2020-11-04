def call(Map args) {
  node
  {
    stage("Code Checkout")
    {
      try
      {
        startTime = System.currentTimeMillis()
          println "Branch var - ${args.branch}"
          println "Build Plan Start Time => "+startTime

          step([$class: 'WsCleanup'])
                         //            git  branch: 'jenkins-pipeline', url: "${args.git_location}"
                         checkout poll: false, scm: [$class: 'GitSCM', branches: [[name: "*/${args.branch}"]], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'LocalBranch', localBranch: "${args.branch}"], [$class: 'UserExclusion', excludedUsers: '''jenkins
                                                                                                                                                                                                                              devopsatmsh
                                                                                                                                                                                                                                DevOps
                                                                                                                                                                                                                                devops''']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '83dbdd19-8fbd-441c-9a68-efbafc192ab6', url: "${args.git_location}"]]]

      }
      catch ( Exception e)
      {
        println(e.getMessage());
          //jcSendFailureSlackNotification(channel: "jc-testing", currentStep: "Code Checkout", errorMsg: e.getMessage(), JOB_NAME: env.JOB_NAME, BUILD_URL: env.BUILD_URL)
          jcSendFailureSlackNotification(msg: "Code Checkout",e.getMessage())
          error "Jon Run failed, please read logs..."
      }
    }
  }
}
