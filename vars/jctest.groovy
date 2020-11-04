def call() {
  node {
    stage("hello") {
      sh '''
        echo "shared library"
        '''
    }
  }
}
