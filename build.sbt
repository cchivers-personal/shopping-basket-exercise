name := "mercator-technical-test"

version := "0.1"

scalaVersion := "2.13.8"

libraryDependencies ++= Seq(
  "com.beachape"  %% "enumeratum" % "1.7.0",
  "org.scalatest" %% "scalatest"  % "3.2.11" % Test
)

scalacOptions ++= Seq(
  "-feature",
  "-deprecation",
  "-language:postfixOps",
  "-language:higherKinds",
  "-Xfatal-warnings",
  "-Ywarn-value-discard",
  "-Ywarn-unused:imports",
  "-Ymacro-annotations"
)
