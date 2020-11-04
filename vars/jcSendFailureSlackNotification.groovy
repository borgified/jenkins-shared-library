def call(Map args) {
  slackSend channel: "${args.channel}", color: 'danger', message: "Job Name - ${args.JOB_NAME}\n Build URL - ${args.BUILD_URL}console \n Failed at step - ${args.currentStep} \nStatus - *`Failed`* \n Exception-${args.errorMsg}"
}
