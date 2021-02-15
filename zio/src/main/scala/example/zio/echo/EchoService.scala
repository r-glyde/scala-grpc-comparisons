package example.zio.echo

import example.echo.echo.{Request, Response, ZioEcho}
import io.grpc.Status
import zio._

object EchoService extends ZioEcho.ZEchoService[zio.ZEnv, Any] {
  override def echo(request: Request): ZIO[zio.ZEnv with Any, Status, Response] =
    ZIO.succeed(Response(request.input))
}
