package example.scalapb.echo

import example.echo.echo.{EchoServiceGrpc, Request, Response}

import scala.concurrent.Future

object EchoService extends EchoServiceGrpc.EchoService {
  override def echo(request: Request): Future[Response] = Future.successful(Response(request.input))
}
