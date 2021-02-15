name := "grpc-comparisons"
version := "0.1.0"

Global / onChangedBuildSource := ReloadOnSourceChanges

ThisBuild / scalaVersion := "2.13.4"

lazy val root = project.in(file("."))
  .settings(publish / skip := true)
  .aggregate(akka, scalagrpc, zio)

lazy val akka = project.in(file("akka"))
  .enablePlugins(AkkaGrpcPlugin, JavaAppPackaging)
  .settings(dockerSettings)
  .settings(Compile / PB.protoSources += file("./proto"))

lazy val scalagrpc = project.in(file("scalapb-grpc"))
  .enablePlugins(JavaAppPackaging)
  .settings(dockerSettings)
  .settings(
    Compile / PB.targets := Seq(scalapb.gen() -> (Compile / sourceManaged).value / "scalapb"),
    Compile / PB.protoSources += file("./proto")
  )
  .settings(
    libraryDependencies ++= Seq(
      "io.grpc" % "grpc-netty" % scalapb.compiler.Version.grpcJavaVersion,
      "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion
    )
  )

lazy val zio = project.in(file("zio"))
  .enablePlugins(JavaAppPackaging)
  .settings(dockerSettings)
  .settings(
    Compile / PB.protoSources += file("./proto"),
    Compile / PB.targets := Seq(
      scalapb.gen(grpc = true) -> (sourceManaged in Compile).value,
      scalapb.zio_grpc.ZioCodeGenerator -> (sourceManaged in Compile).value
    )
  )
  .settings(
    libraryDependencies ++= Seq(
      "io.grpc" % "grpc-netty" % "1.34.0",
      "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion
    )
  )

lazy val fs2 = project.in(file("fs2"))
  .enablePlugins(Fs2Grpc, JavaAppPackaging)
  .settings(dockerSettings)
  .settings(Compile / PB.protoSources += file("./proto"))
  .settings(libraryDependencies += "io.grpc" % "grpc-netty-shaded" % scalapb.compiler.Version.grpcJavaVersion)

lazy val dockerSettings = Seq(
  dockerBaseImage := "openjdk:11.0.10-jre",
  dockerUpdateLatest := true,
  packageName in Docker := s"grpc-comparisons-${name.value}"
)



