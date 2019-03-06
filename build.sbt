name      := "fs2_playground"
version   := "0.1.0-SNAPSHOT"

scalaVersion in ThisBuild := "2.12.6"
scalacOptions in ThisBuild ++= Seq(
  "-language:_",
  "-Ypartial-unification",
  "-Xfatal-warnings"
)


addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.6")
addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full)


//libraryDependencies ++= Seq(
//  "com.github.mpilquist" %% "simulacrum"     % "0.12.0",
//  "org.scalaz"           %% "scalaz-core"    % "7.2.22"
//)

// https://mvnrepository.com/artifact/org.scalatest/scalatest
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test


// available for Scala 2.11, 2.12
libraryDependencies += "co.fs2" %% "fs2-core" % "1.0.3" // For cats 1.1.0 and cats-effect 0.10

// optional I/O library
libraryDependencies += "co.fs2" %% "fs2-io" % "1.0.3"



