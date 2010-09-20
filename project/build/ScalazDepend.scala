import sbt._
import java.util.jar.Attributes.Name._


class ScalazDepend(info: ProjectInfo) extends ProguardProject(info) {
  val scalaToolsSnapshots = "Scala Tools Snapshots" at "http://scala-tools.org/repo-snapshots/"

  override def packageOptions =
    List(
      ManifestAttributes(
        (IMPLEMENTATION_TITLE, "Scalaz Depend")
      , (IMPLEMENTATION_URL, "http://github.com/scalaz/scalaz-depend/")
      , (IMPLEMENTATION_VENDOR, "Scalaz")
      , (SEALED, "true")
      , (MAIN_CLASS, "scalaz.Main")
      )
    )

  override def managedStyle = ManagedStyle.Maven

  override def packageDocsJar = defaultJarPath("-javadoc.jar")

  override def packageSrcJar = defaultJarPath("-sources.jar")

  override def packageTestSrcJar = defaultJarPath("-test-sources.jar")

  override def outputPattern = "[conf]/[type]/[artifact](-[revision])(-[classifier]).[ext]"

  val sourceArtifact = Artifact(artifactID, "src", "jar", Some("sources"), Nil, None)

  val docsArtifact = Artifact(artifactID, "docs", "jar", Some("javadoc"), Nil, None)

  val specs = "org.scala-tools.testing" %% "specs" % "1.6.5-SNAPSHOT" withSources

  val scalacheck = "org.scala-tools.testing" %% "scalacheck" % "1.8-SNAPSHOT" withSources

  override def compileOptions = super.compileOptions ++ Seq(Unchecked)

  override def allDependencyJars = (
    super.allDependencyJars +++
    Path.fromFile(buildScalaInstance.compilerJar) +++
    Path.fromFile(buildScalaInstance.libraryJar)
  )
  
  override def proguardOptions = List(
    "-dontoptimize"
  , "-dontobfuscate"
  , proguardKeepAllScala
  , "-keep interface scala.ScalaObject"
  , "-keep class ch.epfl.** { *; }"
  , "-keepclasseswithmembers public class * { public static void main(java.lang.String[]); }"
  )
}

