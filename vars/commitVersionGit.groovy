def call(String version, String scope) {
  if (version != '') {      
    sh "git diff-index --quiet HEAD || git commit -a -m 'updating version to ${version}'"
    sh "git tag -fm 'updating version to ${version}' ${version}"
    sh 'git config --local credential.helper "!f() { echo username=$GIT_AUTH_USR; echo password=$GIT_AUTH_PSW; }; f"'
    sh "git push --follow-tags origin HEAD:${BRANCH_NAME}"
    sh "git config --local --unset credential.helper"
  }
}
