plugins {
    id("f1.jvm.library")
}

dependencies {
    api(projects.core.resource)
    api(projects.domain.models)
}
