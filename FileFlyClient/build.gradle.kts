plugins {
	id("application")
	id("java")
}
application {
	mainClass = "com.fileflyclient.Main"
}
dependencies {
	implementation project(":FileFlyRequestTypes")	
}
