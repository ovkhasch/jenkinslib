def call(String version, String scope) {
  if (version != '') {      
    sh "git commit -a -m 'updating version to ${version}'"
    sh "git tag ${version}"
    sh "git push"
  }
}
