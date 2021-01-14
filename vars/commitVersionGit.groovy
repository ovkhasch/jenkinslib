def call(String version, String scope) {
  if (version != '') {      
    sh "git commit -m 'updating version to ${version}'"
    sh "git tag ${version}"
    sh "git push"
  }
}
