plugins {
    id("f1.jvm.library")
}

dependencies {
    api(projects.core.datetime)
    api(projects.core.resource)
}
