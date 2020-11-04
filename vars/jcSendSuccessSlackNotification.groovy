def call(Map args) {
  slackSend channel: "${args.channel}", color: 'good', message: "Job Name - ${args.JOB_NAME} \n Build URL - ${args.BUILD_URL} \nRelease Tag - ${args.releaseVersion} \nStatus - *`Success`*"
}
