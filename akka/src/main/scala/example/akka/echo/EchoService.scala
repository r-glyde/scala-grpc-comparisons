package example.akka.echo
import example.echo.{Request, Response}

import scala.concurrent.Future

object EchoService extends example.echo.EchoService {
  override def echo(in: Request): Future[Response] = Future.successful(Response(in.input))
}
