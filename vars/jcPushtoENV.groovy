// jcPushtoENV(ENV: "scrum-koala", service: "service-usersynch", region: "us-west-2", image: "service-login-syncuserstoauth0", tag: "2.2.15")
def call(Map args) {
  node
  {
    stage("Push to ${args.ENV}")
    {
      sh """
        ecs scale  "${args.ENV}-blue-CLUSTER" "${args.ENV}-blue-${args.service}" 0 --region "${args.region}" --ignore-warnings --timeout 400
        sleep 35
        ecs deploy  "${args.ENV}-blue-CLUSTER" "${args.ENV}-blue-${args.service}" --image "${args.service}" "524422562296.dkr.ecr.us-west-2.amazonaws.com/${args.image}:${args.tag}" --region "${args.region}" --timeout 500
        sleep 15
        ecs scale  "${args.ENV}-blue-CLUSTER" "${args.ENV}-blue-${args.service}" 1 --region "${args.region}" --ignore-warnings --timeout 400
        sleep 35
        """
    }
  }
}
