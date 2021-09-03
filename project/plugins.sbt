addSbtPlugin("com.thesamet" % "sbt-protoc" % "1.0.0-RC4")
addSbtPlugin("com.lightbend.akka.grpc" % "sbt-akka-grpc" % "2.1.0")
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.8.0")
addSbtPlugin("org.lyranthe.fs2-grpc" % "sbt-java-gen" % "0.8.0")

libraryDependencies += "com.thesamet.scalapb.zio-grpc" %% "zio-grpc-codegen" % "0.4.2"
