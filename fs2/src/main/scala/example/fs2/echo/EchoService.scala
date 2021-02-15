package example.fs2.echo

import cats.effect.Sync
import example.echo.echo.{EchoServiceFs2Grpc, Request, Response}
import io.grpc.Metadata

class EchoService[F[_] : Sync] extends EchoServiceFs2Grpc[F, Metadata] {
  override def echo(request: Request, ctx: Metadata): F[Response] = Sync[F].pure(Response(request.input))
}
