plugins {
	id("application")
	id("java")
}
application {
	mainClass = "com.fileflyserver.Main"
}
dependencies {
	implementation(project(":FileFlyRequestTypes"))	
}
