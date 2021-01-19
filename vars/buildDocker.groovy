  
def call(String repo, String args, String version) {
  def image

  if (version != '') {      
    docker.withRegistry('', 'docker-hub') {
      image = docker.build("""${repo}:${version}""", args)
    }
    docker.withRegistry('https://registry.kaiburr.com', 'chef01-registry') {
      image.push()
      if (BRANCH_NAME == 'master') {
        image.push('latest')
      }
    }
  } else {
    docker.withRegistry('', 'docker-hub') {
      image = docker.build(repo, args)
    }
    docker.withRegistry('https://registry.kaiburr.com', 'chef01-registry') {
      image.push()
    }      
  }
/*
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
  */
}
