package example.akka.echo

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import com.typesafe.config.ConfigFactory

import scala.concurrent.ExecutionContext

object EchoServer {

  def main(args: Array[String]): Unit = {
    implicit val system: ActorSystem = ActorSystem("EchoService")
    implicit val ec: ExecutionContext = system.dispatcher

    val handler = example.echo.EchoServiceHandler(EchoService)
    val binding = Http().newServerAt("0.0.0.0", 4770).bind(handler)

    binding.foreach { binding => println(s"gRPC server bound to: ${binding.localAddress}") }
  }

}
