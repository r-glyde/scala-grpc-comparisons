package example.zio.echo

import scalapb.zio_grpc.{ServerMain, ServiceList}

object EchoServer extends ServerMain {
  override def port: Int = 4770

  override def services: ServiceList[zio.ZEnv] = ServiceList.add(EchoService)
}
