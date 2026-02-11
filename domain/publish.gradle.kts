afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])

                groupId = "com.ldileh.movie.domain"
                artifactId = "domain"
                version = "0.0.1"
            }

            create<MavenPublication>("debug") {
                from(components["debug"])

                groupId = "com.ldileh.movie.domain"
                artifactId = "domain"
                version = "0.0.1"
            }
        }
    }
}