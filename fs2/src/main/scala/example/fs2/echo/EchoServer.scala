package example.fs2.echo

import cats.effect.{ExitCode, IO, IOApp}
import cats.syntax.all._
import example.echo.echo.EchoServiceFs2Grpc
import io.grpc._
import org.lyranthe.fs2_grpc.java_runtime.implicits._

object EchoServer extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = {
    val service = new EchoService[IO]
    val server: ServerBuilder[_] = ServerBuilder
      .forPort(4770)
      .addService(EchoServiceFs2Grpc.bindService(service))

    server
      .resource[IO]
      .use { server =>
        IO(server.start()) >> IO.never
      }
      .as(ExitCode.Success)
  }
}
