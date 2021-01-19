  
def call(String version, List<String> modules) {
  def image
  env.dockerfile = 'src/main/docker/Dockerfile.jvm'

  modules.each {
      env.module = it
      if (version != '') {      
        docker.withRegistry('', 'docker-hub') {
          image = docker.build("""${module}:${version}""", "-f ${env.module}/${env.dockerfile} ./${env.module}")
        }
        docker.withRegistry('https://registry.kaiburr.com', 'chef01-registry') {
          image.push()
          if (BRANCH_NAME == 'master') {
            image.push('latest')
          }
        }
      } else {
        docker.withRegistry('', 'docker-hub') {
          image = docker.build("${module}", "-f ${env.module}/${env.dockerfile} ./${env.module}")
        }
        docker.withRegistry('https://registry.kaiburr.com', 'chef01-registry') {
          image.push()
        }      
      }
  }
}
