lazy val root = (project in file(".")).settings(
  inThisBuild(
    List(
      organization := "$organization$",
      scalaVersion := "2.13.1"
    )
  ),
  name := "$name$",
  libraryDependencies := Seq(
    library.kafkaClients,
    library.kafkaStreams,
    library.log4jCore,
    library.typesafeConfig,
    library.kafkaTest,
    library.scalaTest
  ),
//  assemblyJarName in assembly := "$name;format="norm,word"$-.jar",
  mainClass in assembly := Some("$organization$.$name;format="norm,word"$.App")
)

lazy val library = new {

  val version = new {
    val kafkaVersion   = "2.5.0"
    val scalaTest      = "3.1.1"
    val log4jCore      = "2.11.1"
    val typesafeConfig = "1.4.0"
  }

  val kafkaClients   = "org.apache.kafka"         % "kafka-clients"        % version.kafkaVersion
  val kafkaStreams   = "org.apache.kafka"         %% "kafka-streams-scala" % version.kafkaVersion
  val log4jCore      = "org.apache.logging.log4j" % "log4j-core"           % version.log4jCore
  val typesafeConfig = "com.typesafe"             % "config"               % version.typesafeConfig

  val kafkaTest = "org.apache.kafka" % "kafka-streams-test-utils" % version.kafkaVersion
  val scalaTest = "org.scalatest"    %% "scalatest"               % version.scalaTest % "test"
}

assemblyMergeStrategy in assembly := {
  case "module-info.class" => MergeStrategy.discard
  case manifest if manifest.contains("MANIFEST.MF") =>
    // We don't need manifest files since sbt-assembly will create
    // one with the given settings
    MergeStrategy.discard
  case referenceOverrides if referenceOverrides.contains("reference-overrides.conf") =>
    // Keep the content for all reference-overrides.conf files
    MergeStrategy.concat
  case x =>
    // For all the other files, use the default sbt-assembly merge strategy
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}