package example.scalapb.echo

import io.grpc.{Server, ServerBuilder}
import example.echo.echo.EchoServiceGrpc

import scala.concurrent.ExecutionContext

object EchoServer {

  def main(args: Array[String]): Unit = {

    val server: Server =
      ServerBuilder
        .forPort(4770)
        .addService(EchoServiceGrpc.bindService(EchoService, ExecutionContext.global))
        .build
        .start

    server.awaitTermination()
  }

}
