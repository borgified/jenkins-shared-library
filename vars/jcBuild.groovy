def call(Map args) {
  node
  {
    stage("Docker login")
    {
      sh '''
        eval $(aws ecr get-login --no-include-email --region us-west-2 )
        eval $(aws ecr get-login --no-include-email --region us-west-2 | sed 's|https://||')
        '''
    }
    stage("Build")
    {
      try
      {
        sh """
          ./buildsvc.sh
          git push origin HEAD:${args.BRANCH}
        """
      }
      catch ( Exception e)
      {
        println(e.getMessage());
        jcSendFailureSlackNotification(channel: "jc-testing", currentStep: "Build Failed", errorMsg: e.getMessage(), JOB_NAME: "${args.JOB_NAME}", BUILD_URL: "${args.BUILD_URL}")
        error "Job Run failed, please read logs..."
      }
    }
  }
}
