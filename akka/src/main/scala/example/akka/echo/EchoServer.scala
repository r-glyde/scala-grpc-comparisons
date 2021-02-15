package example.akka.echo

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import com.typesafe.config.ConfigFactory

import scala.concurrent.ExecutionContext

object EchoServer {

  def main(args: Array[String]): Unit = {
    val conf = ConfigFactory
      .parseString("akka.http.server.preview.enable-http2 = on")
      .withFallback(ConfigFactory.defaultApplication())

    implicit val system: ActorSystem = ActorSystem("EchoService", conf)
    implicit val ec: ExecutionContext = system.dispatcher

    val handler = example.echo.EchoServiceHandler(EchoService)
    val binding = Http().newServerAt("0.0.0.0", 4770).bind(handler)

    binding.foreach { binding => println(s"gRPC server bound to: ${binding.localAddress}") }
  }

}
